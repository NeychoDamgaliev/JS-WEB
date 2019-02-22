package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.service.UserServiceModel;
import org.softuni.exam.domain.models.views.UserProfileViewModel;
import org.softuni.exam.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * Created by Neycho Damgaliev on 2/21/2019.
 */
@Named(value = "userProfile")
@RequestScoped
public class UserProfileBean  extends BaseBean{

    private UserProfileViewModel model;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserProfileBean() {
    }

    @Inject
    public UserProfileBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String username = (String)session.getAttribute("username");
        if(username == null || username.equals("")) {
            this.redirect("/login");
            return;
        }
        UserServiceModel userByUsername = this.userService.getUserByUsername(username);
        if(userByUsername == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        UserProfileViewModel userProfileViewModel = this.modelMapper.map(userByUsername, UserProfileViewModel.class);
        this.model = userProfileViewModel;
    }

    public UserProfileViewModel getModel() {
        return model;
    }

    public void setModel(UserProfileViewModel model) {
        this.model = model;
    }
}
