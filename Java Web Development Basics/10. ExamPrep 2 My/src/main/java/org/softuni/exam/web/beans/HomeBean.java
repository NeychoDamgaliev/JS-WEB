package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.service.UserServiceModel;
import org.softuni.exam.domain.models.views.UserHomeViewModel;
import org.softuni.exam.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 2/21/2019.
 */
@Named
@RequestScoped
public class HomeBean extends BaseBean {

    private List<UserHomeViewModel> users;
    private String loggedUserId;

    private UserService userService;
    private ModelMapper modelMapper;


    public HomeBean() {
        this.users = new ArrayList<>();
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String username = (String)session.getAttribute("username");
        this.loggedUserId = (String)session.getAttribute("user-id");

        this.users = this.userService.getAllUsers()
                .stream()
                .filter(u-> !u.getUsername().equals(username)&& u.getFriends().stream().noneMatch(f->f.getUsername().equals(username)))
                .map(u->this.modelMapper.map(u,UserHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserHomeViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserHomeViewModel> users) {
        this.users = users;
    }

    public void addFriend(String id) {
        UserServiceModel user = this.userService.getUserById(loggedUserId);
        UserServiceModel friend = this.userService.getUserById(id);
        user.getFriends().add(friend);
        friend.getFriends().add(user);

        if(!this.userService.addFriend(user) || !this.userService.addFriend(friend)) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        this.redirect("/home");
    }
}
