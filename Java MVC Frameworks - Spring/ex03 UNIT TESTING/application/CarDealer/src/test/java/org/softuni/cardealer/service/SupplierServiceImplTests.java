package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Neycho Damgaliev on 3/10/2019.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SupplierServiceImplTests {

    private SupplierService supplierService;
    private SupplierServiceModel arranged;
    @Autowired
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;


    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.supplierService = new SupplierServiceImpl(this.supplierRepository, this.modelMapper);
        this.arranged = new SupplierServiceModel();
    }

    @Test
    public void supplierService_saveSupplierWithCorrectValues_ReturnsCorrect() {

        this.arranged = new SupplierServiceModel();
        this.arranged.setName("Pesho");
        this.arranged.setImporter(true);

        SupplierServiceModel actual = this.supplierService.saveSupplier(this.arranged);

        SupplierServiceModel expected = this.modelMapper.map(
                this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.isImporter(), actual.isImporter());
    }

    @Test(expected = Exception.class)
    public void supplierService_saveSupplierWithNullValue_throwsException() {
        this.arranged = new SupplierServiceModel();
        this.arranged.setName(null);
        this.arranged.setImporter(true);

        this.supplierService.saveSupplier(this.arranged);
    }

    @Test
    public void supplierService_editSupplierWithCorrectValues_ReturnsCorrectResult() {

        Supplier savedSupplier = this.createAndSaveSupplier();


        SupplierServiceModel supplierToBeEdited = new SupplierServiceModel();
        supplierToBeEdited.setId(savedSupplier.getId());
        supplierToBeEdited.setName("Gosho");
        supplierToBeEdited.setImporter(false);


        SupplierServiceModel actual = this.supplierService.editSupplier(supplierToBeEdited);

        SupplierServiceModel expected = this.modelMapper.map(
                this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        Assert.assertEquals("Expected 'ID' is different than actual 'ID'", expected.getId(), actual.getId());
        Assert.assertEquals("Expected 'NAME' is different than actual 'NAME'", expected.getName(), actual.getName());
        Assert.assertEquals("Expected 'IMPORTER' is different than actual 'IMPORTER'", expected.isImporter(), actual.isImporter());
    }

    @Test(expected = Exception.class)
    public void supplierService_editSupplierWithNullValues_ThrowsException() {

        Supplier savedSupplier = this.createAndSaveSupplier();


        SupplierServiceModel supplierToBeEdited = new SupplierServiceModel();
        supplierToBeEdited.setId(savedSupplier.getId());
        supplierToBeEdited.setName(null);
        supplierToBeEdited.setImporter(false);

        this.supplierService.editSupplier(supplierToBeEdited);

    }

    @Test
    public void supplierService_deleteSupplierWithValidId_ReturnsCorrectSupplier(){
        Supplier supplier = this.createAndSaveSupplier();

        SupplierServiceModel actual = this.supplierService.deleteSupplier(supplier.getId());
        SupplierServiceModel expected = this.modelMapper.map(supplier,SupplierServiceModel.class);

        Assert.assertEquals("Expected 'ID' is different than actual 'ID'", expected.getId(), actual.getId());
        Assert.assertEquals("Expected 'NAME' is different than actual 'NAME'", expected.getName(), actual.getName());
        Assert.assertEquals("Expected 'IMPORTER' is different than actual 'IMPORTER'", expected.isImporter(), actual.isImporter());
    }

    @Test(expected = Exception.class)
    public void supplierService_deleteSupplierWithInvalidId_ThrowsError(){
        this.createAndSaveSupplier();
        SupplierServiceModel actual = this.supplierService.deleteSupplier("InvalidID");
    }

    @Test
    public void supplierService_findSupplierWithValidId_ReturnsCorrectSupplier(){
        Supplier supplier = this.createAndSaveSupplier();

        SupplierServiceModel actual = this.supplierService.findSupplierById(supplier.getId());
        SupplierServiceModel expected = this.modelMapper.map(supplier,SupplierServiceModel.class);

        Assert.assertEquals("Expected 'ID' is different than actual 'ID'", expected.getId(), actual.getId());
        Assert.assertEquals("Expected 'NAME' is different than actual 'NAME'", expected.getName(), actual.getName());
        Assert.assertEquals("Expected 'IMPORTER' is different than actual 'IMPORTER'", expected.isImporter(), actual.isImporter());
    }

    @Test(expected = Exception.class)
    public void supplierService_findSupplierWithInvalidId_ThrowsError(){

        this.createAndSaveSupplier();
        SupplierServiceModel actual = this.supplierService.findSupplierById("InvalidID");
    }

    private Supplier createAndSaveSupplier() {
        Supplier supplierToBeSaved = new Supplier();
        supplierToBeSaved.setName("Pesho");
        supplierToBeSaved.setImporter(true);

        return this.supplierRepository.saveAndFlush(supplierToBeSaved);
    }

}