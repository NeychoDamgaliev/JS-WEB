package org.softuni.exam.domain.models.binding;

import org.softuni.exam.domain.entities.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserRegisterBindingModel {
    private String username;

    private String password;

    private String confirmPassword;

    private Gender gender;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
