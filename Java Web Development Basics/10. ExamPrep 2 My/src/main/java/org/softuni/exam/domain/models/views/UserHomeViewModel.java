package org.softuni.exam.domain.models.views;

import org.softuni.exam.domain.entities.Gender;

/**
 * Created by Neycho Damgaliev on 2/21/2019.
 */
public class UserHomeViewModel {

    private String id;
    private String username;
    private Gender gender;

    public UserHomeViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
