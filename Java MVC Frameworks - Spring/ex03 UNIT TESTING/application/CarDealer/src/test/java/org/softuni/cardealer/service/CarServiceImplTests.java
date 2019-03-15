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
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.repository.CarRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Neycho Damgaliev on 3/13/2019.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class CarServiceImplTests {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private CarServiceModel expected;
    private Car car;

    @Before
    public void init() {
        this.car = mock(Car.class);
        this.expected = mock(CarServiceModel.class);
    }

    @Test
    public void saveCarWithCorrectValues_returnCorrectResult() {

        Mockito.when(modelMapper.map(expected, Car.class))
                .thenReturn(car);
        Mockito.when(carRepository.saveAndFlush(car))
                .thenReturn(car);
        Mockito.when(modelMapper.map(car, CarServiceModel.class))
                .thenReturn(expected);


        CarServiceModel actual = this.carService.saveCar(expected);

        Mockito.verify(modelMapper).map(expected, Car.class);
        Mockito.verify(carRepository).saveAndFlush(car);
        Mockito.verify(modelMapper).map(car, CarServiceModel.class);

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void saveCarWithNullValues_throwsException() {
        Mockito.when(modelMapper.map(eq(null),any()))
                .thenThrow(new Exception());

        this.carService.saveCar(expected);
    }

    @Test
    public void editCarWithCorrectValues_returnCorrectResult() {
        Mockito.when(expected.getId()).thenReturn("ValidId");
        Mockito.when(expected.getMake()).thenReturn("ValidMake");
        Mockito.when(expected.getModel()).thenReturn("ValidModel");
        Mockito.when(expected.getTravelledDistance()).thenReturn(1000L);

        Mockito.when(carRepository.findById(anyString())).thenReturn(Optional.of(car));
        Mockito.when(carRepository.saveAndFlush(car)).thenReturn(car);
        Mockito.when(modelMapper.map(car,CarServiceModel.class)).thenReturn(expected);

        CarServiceModel actual = this.carService.editCar(expected);

        Mockito.verify(carRepository).findById("ValidId");
        Mockito.verify(car).setMake("ValidMake");
        Mockito.verify(car).setModel("ValidModel");
        Mockito.verify(car).setTravelledDistance(1000L);
        Mockito.verify(carRepository).saveAndFlush(car);
        Mockito.verify(modelMapper).map(car,CarServiceModel.class);
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = Exception.class)
    public void editCarWithInvalidValues_throwsException() {
        Mockito.when(carRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        carService.editCar(expected);
    }

    @Test(expected = Exception.class)
    public void editCarWithNullValues_throwsException() {
        Mockito.when(carRepository.findById(eq(null)))
                .thenThrow(new Exception());
        carService.editCar(null);
    }

    @Test
    public void deleteCarWithValidId_returnCorrectResult() {

        Mockito.when(carRepository.findById(anyString())).thenReturn(Optional.of(car));
        Mockito.when(modelMapper.map(car,CarServiceModel.class)).thenReturn(expected);

        CarServiceModel actual = this.carService.deleteCar("ValidId");

        Mockito.verify(carRepository).findById("ValidId");
        Mockito.verify(carRepository).delete(car);
        Mockito.verify(modelMapper).map(car,CarServiceModel.class);
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = Exception.class)
    public void deleteCarWithInvalidId_throwsException() {
        Mockito.when(carRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        Mockito.doThrow(new Exception()).when(carRepository)
                .delete(eq(null));

        carService.deleteCar("InvalidId");
    }

    @Test(expected = Exception.class)
    public void deleteCarWithNull_throwsException() {
        Mockito.when(carRepository.findById(eq(null)))
                .thenReturn(Optional.empty());

        Mockito.doThrow(new NullPointerException()).when(carRepository)
                .delete(eq(null));

        carService.deleteCar(null);
    }

    @Test
    public void findByIdWithCorrectId_returnsCorrectValues() {
        Mockito.when(carRepository.findById("ValidId"))
                .thenReturn(Optional.of(car));
        Mockito.when(modelMapper.map(car,CarServiceModel.class))
                .thenReturn(expected);

        CarServiceModel actual = carService.findCarById("ValidId");

        Mockito.verify(carRepository).findById("ValidId");
        Mockito.verify(modelMapper).map(car,CarServiceModel.class);
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = Exception.class)
    public void findByIdWithInvalidId_throwsException() {
        Mockito.when(carRepository.findById(anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(modelMapper.map(null,CarServiceModel.class))
                .thenThrow(new Exception());

        carService.findCarById("InvalidId");
    }

    @Test(expected = Exception.class)
    public void findByIdWithNull_throwsException() {
        Mockito.when(carRepository.findById(eq(null)))
                .thenThrow(new Exception());

        carService.findCarById(null);
    }
}