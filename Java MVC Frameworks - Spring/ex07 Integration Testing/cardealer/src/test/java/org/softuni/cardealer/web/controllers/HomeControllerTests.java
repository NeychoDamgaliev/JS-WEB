package org.softuni.cardealer.web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Neycho Damgaliev on 3/24/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void index_returnsCorrectViewWithNoUserLoggedIn() throws Exception {
        this.mvc
                .perform(get("/"))
                .andExpect(view().name("index"));
    }
    @Test(expected = AssertionError.class)
    @WithMockUser("MockedUser")
    public void index_throwsExceptionWithLoggedUser() throws Exception {
        this.mvc
                .perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser("MockedUser")
    public void home_returnsCorrectViewWithLoggedUser() throws Exception {
        this.mvc
                .perform(get("/home"))
                .andExpect(view().name("home"));
    }

    @Test(expected = AssertionError.class)
    public void homew_throwsExceptionWithNoLoggedUser() throws Exception {
        this.mvc
                .perform(get("/home"))
                .andExpect(view().name("home"));
    }
}