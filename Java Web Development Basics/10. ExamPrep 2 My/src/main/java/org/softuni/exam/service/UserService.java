package org.softuni.exam.service;

import org.softuni.exam.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {
    UserServiceModel getUserByUsername(String username);

    UserServiceModel getUserById(String id);

    UserServiceModel createUser(UserServiceModel userServiceModel);

    List<UserServiceModel> getAllUsers();

    boolean addFriend(UserServiceModel userServiceModel);
}
