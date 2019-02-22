package org.softuni.exam.domain.models.views;

import org.softuni.exam.domain.entities.Gender;

/**
 * Created by Neycho Damgaliev on 2/21/2019.
 */
public class UserProfileViewModel {

    private String username;
    private Gender gender;

    public UserProfileViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
