package org.softuni.cardealer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Neycho Damgaliev on 3/13/2019.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CarServiceImplTests.class,
        PartServiceImplTests.class,
        SupplierServiceImplTests.class,
        CustomerServiceImplTests.class
})

public class MainTests {
    @Test
    public void contextLoad(){

    }
}
