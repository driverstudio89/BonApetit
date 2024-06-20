package com.bonappetit.service;

import com.bonappetit.config.UserSession;
import com.bonappetit.model.dto.UserLoginDto;
import com.bonappetit.model.dto.UserRegisterDto;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;
    private final RecipeRepository recipeRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserSession userSession, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
        this.recipeRepository = recipeRepository;
    }


    public boolean register(UserRegisterDto userRegisterDto) {
        Optional<User> byUsernameAndEmail = userRepository.findByUsernameOrEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail());

        if (byUsernameAndEmail.isPresent()) {
            return false;
        }
        User user = modelMapper.map(userRegisterDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean login(UserLoginDto userLoginDto) {
        if (userSession.isLoggedIn()) {
            return false;
        }

        Optional<User> byUsername = userRepository.findByUsername(userLoginDto.getUsername());
        if (byUsername.isEmpty()) {
            return false;
        }

        if (!passwordEncoder.matches(userLoginDto.getPassword(), byUsername.get().getPassword())) {
            return false;
        }
        User user = byUsername.get();

        userSession.login(user.getId(), user.getUsername());

        return true;
    }

    @Transactional
    public void addToFavorites(long id, long recipeId) {

        User user = userRepository.findById(userSession.getId()).get();

        Recipe recipe = recipeRepository.findById(recipeId).get();

        user.getFavoriteRecipes().add(recipe);
        }

    public Set<Recipe> findFavorites(long id) {

        return userRepository.findById(id)
                .map(User::getFavoriteRecipes).orElseGet(HashSet::new);
    }
}
