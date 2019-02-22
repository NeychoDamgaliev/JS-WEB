package org.softuni.exam.domain.models.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    private String username;

    private String password;

    public UserLoginBindingModel() {
    }

//    @Size(min = 3, max=10)
    @Length(min = 3,max = 7)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Min(5)
    @Max(10)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
