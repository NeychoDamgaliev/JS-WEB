package org.softuni.cardealer.service;

import com.sun.source.tree.ModuleTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

/**
 * Created by Neycho Damgaliev on 3/10/2019.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class PartServiceImplTests {

    @Mock
    private PartRepository partRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private PartServiceImpl partService;

    private PartServiceModel expected;
    private Part part;

//    private SupplierServiceModel expectedSupplier;
//
    private Supplier supplier;

    @Before
    public void init() {
        expected = mock(PartServiceModel.class);
        part = mock(Part.class);
    }

    @Test
    public void savePartWithCorrectValues_returnsCorrectResult() {
        Mockito.when(modelMapper.map(expected,Part.class))
                .thenReturn(part);
        Mockito.when(partRepository.saveAndFlush(part))
                .thenReturn(part);
        Mockito.when(modelMapper.map(part,PartServiceModel.class))
                .thenReturn(expected);

        PartServiceModel actual = partService.savePart(expected);

        Mockito.verify(modelMapper).map(expected,Part.class);
        Mockito.verify(partRepository).saveAndFlush(part);
        Mockito.verify(modelMapper).map(part,PartServiceModel.class);
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = Exception.class)
    public void savePartWithNull_throwsException() {
        Mockito.when(modelMapper.map(null,Part.class))
                .thenReturn(null);
        Mockito.doThrow(new Exception()).when(partRepository)
                .saveAndFlush(null);

        partService.savePart(null);
    }

    @Test
    public void editPartWithCorrectValues_returnsCorrectResult() {
        Mockito.when(expected.getId()).thenReturn("ValidId");
        Mockito.when(expected.getName()).thenReturn("ValidName");
        Mockito.when(expected.getPrice()).thenReturn(BigDecimal.valueOf(1000));

        Mockito.when(partRepository.findById("ValidId")).thenReturn(Optional.of(part));
        Mockito.when(partRepository.saveAndFlush(part)).thenReturn(part);
        Mockito.when(modelMapper.map(part,PartServiceModel.class)).thenReturn(expected);

        PartServiceModel actual = partService.editPart(expected);

        Mockito.verify(partRepository).findById("ValidId");
        Mockito.verify(part).setName("ValidName");
        Mockito.verify(part).setPrice(BigDecimal.valueOf(1000));
        Mockito.verify(partRepository).saveAndFlush(part);
        Mockito.verify(modelMapper).map(part,PartServiceModel.class);

        Assert.assertEquals(expected,actual);
    }

    @Test(expected = Exception.class)
    public void editCustomerWithInvalidId_throwsException(){
        Mockito.when(expected.getId()).thenReturn("InvalidId");
        Mockito.when(partRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        partService.editPart(expected);
    }

    @Test(expected = Exception.class)
    public void editCustomerWithNullId_throwsException(){
        Mockito.when(expected.getId()).thenReturn(null);
        Mockito.when(partRepository.findById(null))
                .thenReturn(Optional.empty());

        partService.editPart(expected);
    }

    @Test
    public void deletePartWithValidId_returnsCorrectResult(){
        Mockito.when(partRepository.findById("ValidId")).thenReturn(Optional.of(part));
        Mockito.when(modelMapper.map(part,PartServiceModel.class)).thenReturn(expected);

        PartServiceModel actual = partService.deletePart("ValidId");

        Mockito.verify(partRepository).findById("ValidId");
        Mockito.verify(partRepository).delete(part);
        Mockito.verify(modelMapper).map(part,PartServiceModel.class);
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = Exception.class)
    public void deletePartWithInvalidId_throwsException(){
        Mockito.when(partRepository.findById("InvalidId"))
                .thenReturn(Optional.empty());
        Mockito.doThrow(new Exception()). when(partRepository).delete(null);

        partService.deletePart("InvalidId");
    }

    @Test(expected = Exception.class)
    public void deletePartWithNullId_throwsException(){
        Mockito.when(partRepository.findById(null))
                .thenReturn(Optional.empty());
        Mockito.doThrow(new Exception()). when(partRepository).delete(null);

        partService.deletePart("InvalidId");
    }

    @Test
    public void findPartWithValidId_returnsCorrectResult() {
        Mockito.when(partRepository.findById("ValidId")).thenReturn(Optional.of(part));
        Mockito.when(modelMapper.map(part,PartServiceModel.class)).thenReturn(expected);

        PartServiceModel actual = partService.findPartById("ValidId");

        Mockito.verify(partRepository).findById("ValidId");
        Mockito.verify(modelMapper).map(part,PartServiceModel.class);
        Assert.assertEquals(expected,actual);
    }

    @Test(expected = Exception.class)
    public void findPartWithInvalidId_throwsException() {
        Mockito.when(partRepository.findById("InvalidId")).thenReturn(Optional.empty());
        Mockito.when(modelMapper.map(null,PartServiceModel.class)).thenThrow(new Exception());

        partService.findPartById("InvalidId");
    }

    @Test(expected = Exception.class)
    public void findPartWithNullId_throwsException() {
        Mockito.when(partRepository.findById(null)).thenReturn(Optional.empty());
        Mockito.when(modelMapper.map(null,PartServiceModel.class)).thenThrow(new Exception());

        partService.findPartById("InvalidId");
    }

}