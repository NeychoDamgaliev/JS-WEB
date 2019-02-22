package org.softuni.exam.domain.models.service;

import org.softuni.exam.domain.entities.Gender;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {

    private String id;

    private String username;

    private String password;

    private Gender gender;

    private List<UserServiceModel> friends;

    public UserServiceModel() {
        this.friends = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Gender getGender() {
        return gender;
    }

    public List<UserServiceModel> getFriends() {
        return friends;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setFriends(List<UserServiceModel> friends) {
        this.friends = friends;
    }
}
