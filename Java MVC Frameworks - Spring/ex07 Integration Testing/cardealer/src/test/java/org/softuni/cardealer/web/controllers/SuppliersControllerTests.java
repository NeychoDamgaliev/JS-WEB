package org.softuni.cardealer.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Neycho Damgaliev on 3/29/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SuppliersControllerTests {

    @Autowired
    private MockMvc mvc;

//    @MockBean
//    private SupplierRepository mockedSupplierRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Before
    public void init() {
        this.supplierRepository.deleteAll();
    }

    @Test
    @WithMockUser("spring")
    public void add_withCorrectValues_createsSupplier() throws Exception {
//        Mockito.when(this.mockedSupplierRepository.saveAndFlush(any()))
//                .thenAnswer(a -> a.getArgument(0));
//        ArgumentCaptor<Supplier> argument = ArgumentCaptor.forClass(Supplier.class);

        this.mvc
                .perform(post("/suppliers/add")
                        .param("name", "correctSupplierName")
                        .param("isImporter", "off")
                );


//        verify(this.mockedSupplierRepository).saveAndFlush(argument.capture());
//        assertEquals("correctSupplierName", argument.getValue().getName());
//        assertEquals(false, argument.getValue().getIsImporter());

        Supplier actual = this.supplierRepository.findAll().get(0);

        assertEquals("correctSupplierName", actual.getName());
        assertEquals(false,actual.getIsImporter());
        assertEquals(1,this.supplierRepository.count());

    }

    @Test
    @WithMockUser("spring")
    public void add_withCorrectValues_redirectsCorrectly() throws Exception {

        this.mvc
                .perform(post("/suppliers/add")
                        .param("name", "correctSupplierName")
                        .param("isImporter", "off")
                ).andExpect(view().name("redirect:all"));

    }


    @Test
    @WithMockUser("spring")
    public void edit_worksCorrectWithValidId() throws Exception {
        Supplier first = new Supplier();
        first.setName("firstName");
        first.setIsImporter(false);

        Supplier second = new Supplier();
        second.setName("secondName");
        second.setIsImporter(true);
        first = this.supplierRepository.saveAndFlush(first);
        second = this.supplierRepository.saveAndFlush(second);

        this.mvc
                .perform(post("/suppliers/edit/{id}", first.getId())
                .param("name", "ValidName")
                .param("isImporter", "true")
                );

        this.mvc
                .perform(post("/suppliers/edit/{id}", second.getId())
                        .param("name", "ValidName2")
                        .param("isImporter", "false")
                );

       Supplier actualFirst = this.supplierRepository.findById(first.getId()).orElse(null);
       Supplier actualSecond = this.supplierRepository.findById(second.getId()).orElse(null);

        assertEquals("ValidName", actualFirst.getName());
        assertEquals(true,actualFirst.getIsImporter());

        assertEquals("ValidName2", actualSecond.getName());
        assertEquals(false,actualSecond.getIsImporter());
    }

    @Test
    @WithMockUser("spring")
    public void edit_redirectsWithValidId() throws Exception {
        Supplier first = new Supplier();
        first.setName("firstName");
        first.setIsImporter(false);

        first = this.supplierRepository.saveAndFlush(first);

        this.mvc
                .perform(post("/suppliers/edit/{id}", first.getId())
                        .param("name", "ValidName")
                        .param("isImporter", "true")
                ).andExpect(view().name("redirect:/suppliers/all"));

    }




    @Test
    @WithMockUser("spring")
    public void delete_withCorrectId_deletesSupplier() throws Exception {

        Supplier supplier = new Supplier();
        supplier.setName("ValidSupplier");
        supplier.setIsImporter(false);

        supplier = this.supplierRepository.saveAndFlush(supplier);


//        Mockito
//                .when(mockedSupplierRepository.findById("ValidId"))
//                .thenReturn(Optional.of(supplier));

//        this.mvc
//                .perform(post("/suppliers/delete/{id}", "ValidId")
//                );
        this.mvc
                .perform(post("/suppliers/delete/{id}", supplier.getId())           );

//        verify(this.mockedSupplierRepository).findById("ValidId");
//        verify(this.mockedSupplierRepository).delete(supplier);

        assertEquals(0,this.supplierRepository.count());
    }


    @Test
    @WithMockUser("spring")
    public void delete_withCorrectId_redirects() throws Exception {

        Supplier supplier = new Supplier();
        supplier.setName("ValidSupplier");
        supplier.setIsImporter(false);

        supplier = this.supplierRepository.saveAndFlush(supplier);

//        Mockito
//                .when(mockedSupplierRepository.findById("ValidId"))
//                .thenReturn(Optional.of(supplier));
//
//        this.mvc
//                .perform(post("/suppliers/delete/{id}", "ValidId"))
//                .andExpect(view().name("redirect:/suppliers/all"));

                this.mvc
                .perform(post("/suppliers/delete/{id}", supplier.getId()))
                .andExpect(view().name("redirect:/suppliers/all"));
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void delete_withINCORRECTId_throwsException() throws Exception {

              this.mvc
                .perform(post("/suppliers/delete/{id}", "IncorrectId")           );
    }

    @Test()
    public void allSuppliers_withNoLoggedUser_redirectsToLogin() throws Exception {

        this.mvc
                .perform(get("/suppliers/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }


    @Test
    @WithMockUser("CorrectUser")
    public void allSuppliers_withCorrectUser_returnsCorrectView() throws Exception {
        this.mvc
                .perform(get("/suppliers/all"))
                .andExpect(view().name("all-suppliers"));
    }

    @Test
    @WithMockUser("CorrectUser")
    public void allSuppliers_withCorrectUser_returnsCorrectAttribute() throws Exception {
        this.mvc
                .perform(get("/suppliers/all"))
                .andExpect(view().name("all-suppliers"))
                .andExpect(model().attributeExists("suppliers"));
    }

    @Test
    @WithMockUser
    public void  fetch_worksCorrect_withValidUser() throws Exception {

        Supplier first = new Supplier();
        first.setName("firstName");
        first.setIsImporter(false);

        Supplier second = new Supplier();
        second.setName("secondName");
        second.setIsImporter(true);
        first = this.supplierRepository.saveAndFlush(first);
        second = this.supplierRepository.saveAndFlush(second);

        String actual = this.mvc
                .perform(get("/suppliers/fetch"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected =
                String.format("[{\"id\":\"%s\",\"name\":\"firstName\",\"isImporter\":false},{\"id\":\"%s\",\"name\":\"secondName\",\"isImporter\":true}]",
                first.getId(),second.getId());
        assertEquals(expected,actual);

    }
}