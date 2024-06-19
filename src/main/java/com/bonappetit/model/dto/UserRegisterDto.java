package com.bonappetit.model.dto;

import jakarta.validation.constraints.*;

public class UserRegisterDto {

    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    @NotEmpty
    private String confirmPassword;

    public UserRegisterDto() {

    }

    public @NotNull @Size(min = 3, max = 20) String getUsername() {
        return username;
    }

    public void setUsername(@NotNull @Size(min = 3, max = 20) String username) {
        this.username = username;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @NotNull @Size(min = 3, max = 20) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 3, max = 20) String password) {
        this.password = password;
    }

    public @NotEmpty String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotEmpty String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
