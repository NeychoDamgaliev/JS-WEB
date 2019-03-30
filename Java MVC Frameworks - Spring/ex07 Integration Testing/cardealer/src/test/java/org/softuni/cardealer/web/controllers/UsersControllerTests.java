package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.softuni.cardealer.domain.entities.User;
import org.softuni.cardealer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Neycho Damgaliev on 3/24/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository mockedUserReposiory;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void login_returnsCorrectViewWithNoLoggedUser() throws Exception {
        this.mvc
                .perform(get("/users/login"))
                .andExpect(view().name("login"));
    }

    @Test(expected = AssertionError.class)
    @WithMockUser("MockedUser")
    public void login_throwsEsceptionWithLoggedUser() throws Exception {
        this.mvc
                .perform(get("/users/login"))
                .andExpect(view().name("login"));
    }

    @Test
    public void register_returnsCorrectViewWithNoLoggedUser() throws Exception {
        this.mvc
                .perform(get("/users/register"))
                .andExpect(view().name("register"));
    }

    @Test(expected = AssertionError.class)
    @WithMockUser("MockedUser")
    public void register_throwsEsceptionWithLoggedUser() throws Exception {
        this.mvc
                .perform(get("/users/register"))
                .andExpect(view().name("register"));
    }

    @Test
    public void register_withCorrectParams_createsUser() throws Exception {
        Mockito.when(this.mockedUserReposiory.save(any()))
                .thenAnswer(a -> a.getArgument(0));
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        this.mvc
                .perform(post("/users/register")
                        .param("username", "correctUsername")
                        .param("password", "correctPass")
                        .param("confirmPassword", "correctPass")
                        .param("email", "valid_email@domain.com")
                );


        verify(this.mockedUserReposiory).save(argument.capture());
        assertEquals("correctUsername", argument.getValue().getUsername());
        assertNotEquals("correctPass", argument.getValue().getPassword());
        assertEquals("valid_email@domain.com", argument.getValue().getEmail());

    }

    @Test
    public void register_withCorrectParams_redirects() throws Exception {
        Mockito.when(this.mockedUserReposiory.save(any()))
                .thenAnswer(a -> a.getArgument(0));

        this.mvc
                .perform(post("/users/register")
                        .param("username", "correctUsername")
                        .param("password", "correctPass")
                        .param("confirmPassword", "correctPass")
                        .param("email", "valid_email@domain.com")
                ).andExpect(view().name("redirect:login"));
    }
}