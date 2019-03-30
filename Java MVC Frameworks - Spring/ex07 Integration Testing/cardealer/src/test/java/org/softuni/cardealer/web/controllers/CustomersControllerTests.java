package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Customer;
import org.softuni.cardealer.domain.models.service.CustomerServiceModel;
import org.softuni.cardealer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Neycho Damgaliev on 3/29/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomersControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @WithMockUser("ValidUser")
    public void addCustomer_withCorrectValues_createsCustomer() throws Exception {
        this.mvc
                .perform(post("/customers/add")
                        .param("name", "correctName")
                        .param("birthDate", "2014-02-09")
                );

        Customer actual = this.customerRepository.findAll().get(0);

        assertEquals("correctName", actual.getName());
        assertEquals("2014-02-09", actual.getBirthDate().toString());
    }

    @Test
    @WithMockUser("ValidUser")
    public void addCustomer_withCorrectValues_redirect() throws Exception {
        this.mvc
                .perform(post("/customers/add")
                        .param("name", "correctName")
                        .param("birthDate", "2014-02-09")
                ).andExpect(view().name("redirect:/customers/all"));
    }

    @Test
    @WithMockUser("ValidUser")
    public void allCustomers_returnsCorrectView() throws Exception {
        this.mvc
                .perform(get("/customers/all"))
                .andExpect(view().name("all-customers"));
    }

    @Test
    @WithMockUser("ValidUser")
    public void allCustomers_setsCorrectObject() throws Exception {
        this.mvc
                .perform(get("/customers/all"))
                .andExpect(model().attributeExists("customers"));
    }


    @Test
    @WithMockUser
    public void  allCustomers_worksCorrect_withValidUser() throws Exception {

        Customer first = new Customer();
        first.setName("firstName");
        first.setBirthDate(LocalDate.now().minusYears(10L));
        first.setIsYoungDriver(false);

        first = this.customerRepository.saveAndFlush(first);

        List<CustomerServiceModel> actualCustomers = (List<CustomerServiceModel>) this.mvc
                .perform(get("/customers/all"))
                .andExpect(status().isOk())
                .andReturn().getModelAndView().getModel().get("customers");


        assertEquals(1,actualCustomers.size());
        assertEquals(first.getName(),actualCustomers.get(0).getName());
        assertEquals(first.getBirthDate(),actualCustomers.get(0).getBirthDate());
        assertEquals(first.getIsYoungDriver(),actualCustomers.get(0).getIsYoungDriver());
    }
}