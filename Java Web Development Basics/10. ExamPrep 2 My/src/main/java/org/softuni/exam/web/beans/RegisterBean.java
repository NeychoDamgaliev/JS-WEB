package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.softuni.exam.domain.models.binding.UserRegisterBindingModel;
import org.softuni.exam.domain.models.service.UserServiceModel;
import org.softuni.exam.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

@Named("registerBean")
@RequestScoped
public class RegisterBean extends BaseBean {
    private UserRegisterBindingModel userRegisterBindingModel;

    private UserService userService;

    private ModelMapper modelMapper;

    private UIComponent registerButton;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return this.userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public UIComponent getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(UIComponent registerButton) {
        this.registerButton = registerButton;
    }

    public void register() {

        if (!this.userRegisterBindingModel.getPassword()
                .equals(this.userRegisterBindingModel.getConfirmPassword())) {
            return;
        }
        UserServiceModel userByUsername = null;
        try {
            userByUsername = this.userService.getUserByUsername(this.userRegisterBindingModel.getUsername());
        } catch (Exception ignored) {

        }

        if(userByUsername != null) {
            FacesMessage message = new FacesMessage("Username is allready Taken!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(registerButton.getClientId(context), message);
            return;
        }

            this.userService.createUser(
                    this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class));
            this.redirect("/login");

    }
}
