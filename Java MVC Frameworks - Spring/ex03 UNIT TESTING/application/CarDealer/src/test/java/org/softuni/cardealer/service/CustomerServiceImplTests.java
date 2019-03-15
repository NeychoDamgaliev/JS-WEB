package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Customer;
import org.softuni.cardealer.domain.models.service.CustomerServiceModel;
import org.softuni.cardealer.repository.CustomerRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Neycho Damgaliev on 3/13/2019.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class CustomerServiceImplTests {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerServiceModel expected;
    private Customer customer;

    @Before
    public void init() {
        this.expected = mock(CustomerServiceModel.class);
        this.customer = mock(Customer.class);
    }

    @Test
    public void saveCustomerWithValidData_returnsValidResult() {
        Mockito.when(modelMapper.map(expected, Customer.class))
                .thenReturn(customer);
        Mockito.when(customerRepository.saveAndFlush(customer))
                .thenReturn(customer);
        Mockito.when(modelMapper.map(customer, CustomerServiceModel.class))
                .thenReturn(expected);

        CustomerServiceModel actual = customerService.saveCustomer(expected);


        Mockito.verify(modelMapper).map(expected, Customer.class);
        Mockito.verify(customerRepository).saveAndFlush(customer);
        Mockito.verify(modelMapper).map(customer, CustomerServiceModel.class);

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void saveCustomerWithNullValues_throwsException() {
        Mockito.when(modelMapper.map(eq(null),Customer.class))
                .thenThrow(new Exception());

        customerService.saveCustomer(null);
    }

    @Test
    public void editCustomerWithValidData_returnCorrectResult() {
        LocalDate date = LocalDate.now();
        Mockito.when(expected.getId()).thenReturn("ValidId");
        Mockito.when(expected.getBirthDate()).thenReturn(date);
        Mockito.when(expected.getName()).thenReturn("ValidName");
        Mockito.when(expected.isYoungDriver()).thenReturn(true);

        Mockito.when(customerRepository.findById(anyString())).thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.saveAndFlush(customer)).thenReturn(customer);
        Mockito.when(modelMapper.map(customer,CustomerServiceModel.class)).thenReturn(expected);

        CustomerServiceModel actual = this.customerService.editCustomer(expected);

        Mockito.verify(customerRepository).findById("ValidId");
        Mockito.verify(customer).setName("ValidName");
        Mockito.verify(customer).setBirthDate(date);
        Mockito.verify(customer).setYoungDriver(true);

        Mockito.verify(customerRepository).saveAndFlush(customer);
        Mockito.verify(modelMapper).map(customer,CustomerServiceModel.class);
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = Exception.class)
    public void editCustomerWithInvalidData_throwsException() {
        Mockito.when(customerRepository.findById(anyString())).thenReturn(Optional.empty());
        Mockito.when(customerRepository.saveAndFlush(null)).thenThrow(new Exception());
        CustomerServiceModel actual = this.customerService.editCustomer(expected);
    }

    @Test(expected = Exception.class)
    public void editCustomerWithNull_throwsException() {
        Mockito.when(customerRepository.findById(null)).thenThrow(new Exception());
        CustomerServiceModel actual = this.customerService.editCustomer(expected);
    }

    @Test
    public void deleteCustomerWithValidId_returnsCorrectValues() {
        Mockito.when(customerRepository.findById("ValidId")).thenReturn(Optional.of(customer));
        Mockito.when(modelMapper.map(customer,CustomerServiceModel.class))
                .thenReturn(expected);

        CustomerServiceModel actual = this.customerService.deleteCustomer("ValidId");

        Mockito.verify(customerRepository).findById("ValidId");
        Mockito.verify(customerRepository).delete(customer);
        Mockito.verify(modelMapper).map(customer,CustomerServiceModel.class);
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = Exception.class)
    public void deleteCustomerWithInvalidId_throwsException() {
        Mockito.when(customerRepository.findById("InvalidId"))
                .thenReturn(Optional.empty());
        Mockito.doThrow(new Exception()).when(customerRepository).delete(null);

        customerService.deleteCustomer("InvalidId");
    }

    @Test(expected = Exception.class)
    public void deleteCustomerWithNull_throwsException() {
        Mockito.when(customerRepository.findById(null))
                .thenReturn(Optional.empty());
        Mockito.doThrow(new Exception())
                .when(customerRepository)
                .delete(null);

        customerService.deleteCustomer(null);
    }

    @Test
    public void findCustomerByIdWithValidId_returnsCorrectResult() {
        Mockito.when(customerRepository.findById("ValidId")).thenReturn(Optional.of(customer));
        Mockito.when(modelMapper.map(customer,CustomerServiceModel.class))
                .thenReturn(expected);

        CustomerServiceModel actual = customerService.findCustomerById("ValidId");

        Mockito.verify(customerRepository).findById("ValidId");
        Mockito.verify(modelMapper).map(customer,CustomerServiceModel.class);
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = Exception.class)
    public void findCustomerByIdWithInvalidId_throwsExceptio(){
        Mockito.when(customerRepository.findById("InvlidId"))
                .thenReturn(Optional.empty());
        Mockito.when(modelMapper.map(null,CustomerServiceModel.class))
                .thenThrow(new Exception());

        customerService.findCustomerById("InvalidId");
    }

    @Test(expected = Exception.class)
    public void findCustomerByIdWithNull_throwsExceptio(){
        Mockito.when(customerRepository.findById(null))
                .thenReturn(Optional.empty());
        Mockito.when(modelMapper.map(null,CustomerServiceModel.class))
                .thenThrow(new Exception());

        customerService.findCustomerById(null);
    }
}