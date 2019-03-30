package org.softuni.cardealer.web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.CarRepository;
import org.softuni.cardealer.repository.PartRepository;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by Neycho Damgaliev on 3/29/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarsControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private PartRepository partRepository;

    @Test
    @WithMockUser("spring")
    public void add_withCorrectValues_createsCar() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setIsImporter(true);
        supplier = supplierRepository.saveAndFlush(supplier);

        Part part = new Part();
        part.setName("Engine");
        part.setPrice(BigDecimal.valueOf(123));
        part.setSupplier(supplier);

        part = partRepository.saveAndFlush(part);

        this.mvc
                .perform(post("/cars/add")
                        .param("make", "Peugeot")
                        .param("model", "308")
                        .param("travelledDistance", "1000")
                        .param("parts",part.getId())
                );

        Car actual = this.carRepository.findAll().get(0);

        assertEquals("Peugeot",actual.getMake());
        assertEquals("308",actual.getModel());
        assertEquals(java.util.Optional.of(1000L).get(),actual.getTravelledDistance());

    }


    @Test
    @WithMockUser("spring")
    public void edit_withCorrectValues_editsCar() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setIsImporter(true);
        supplier = supplierRepository.saveAndFlush(supplier);

        Part part = new Part();
        part.setName("Engine");
        part.setPrice(BigDecimal.valueOf(123));
        part.setSupplier(supplier);

        part = partRepository.saveAndFlush(part);
        List<Part> parts = new ArrayList<>();
        parts.add(part);

        Car car = new Car();
        car.setMake("Peogeot");
        car.setModel("308");
        car.setTravelledDistance(1000L);
        car.setParts(parts);

        Car arrangedCar = this.carRepository.saveAndFlush(car);

        this.mvc
                .perform(post("/cars/edit/{id}", arrangedCar.getId())
                        .param("make", "ValidMake")
                        .param("model", "ValidModel")
                        .param("travelledDistance", "99999")
                );

        Car actual = this.carRepository.findAll().get(0);

        assertEquals("ValidMake",actual.getMake());
    }

    @Test
    @WithMockUser("spring")
    public void delete_withCorrectValues_editsCar() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setIsImporter(true);
        supplier = supplierRepository.saveAndFlush(supplier);

        Part part = new Part();
        part.setName("Engine");
        part.setPrice(BigDecimal.valueOf(123));
        part.setSupplier(supplier);

        part = partRepository.saveAndFlush(part);
        List<Part> parts = new ArrayList<>();
        parts.add(part);

        Car car = new Car();
        car.setMake("Peogeot");
        car.setModel("308");
        car.setTravelledDistance(1000L);
        car.setParts(parts);
        this.carRepository.deleteAll();
        Car arrangedCar = this.carRepository.saveAndFlush(car);

        this.mvc
                .perform(post("/cars/delete/{id}", arrangedCar.getId()));


        assertEquals(0,this.carRepository.count());
    }
}