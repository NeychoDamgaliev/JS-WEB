package residentevil.web.controllers;

import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import residentevil.web.domain.models.binding.UserRegisterBindingModel;
import residentevil.web.domain.models.service.UserServiceModel;
import residentevil.web.domain.models.view.UserListViewModel;
import residentevil.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 3/23/2019.
 */
@Controller
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(ModelAndView modelAndView) {

        return super.view("register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@ModelAttribute(name = "bindingModel") UserRegisterBindingModel bindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView) {
        if (bindingResult.hasErrors() || !bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            modelAndView.addObject("bindingModel", bindingModel);
            return super.view("register", modelAndView);
        }

        boolean registerSuccess =
                this.userService.registerUser(
                        this.modelMapper.map(bindingModel, UserServiceModel.class)
                );
        return super.redirect("/login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login(ModelAndView modelAndView) {
        return super.view("login");
    }

    @GetMapping(value = "/logout")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null) {
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return super.redirect("/");
    }


    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView allUsers(ModelAndView modelAndView) {
        List<UserListViewModel> allUsers =  this.userService.findAllUsers()
                .stream()
                .map(u-> {
                   return this.modelMapper.map(u,UserListViewModel.class);
                })
                .collect(Collectors.toList());

        modelAndView.addObject("users",allUsers);
        return  super.view("/users",modelAndView);
    }
}

