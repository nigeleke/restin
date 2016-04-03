package com.nigeleke.restin.service;

import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationConfigTest {

    @Before
    public void setUp() {
        instance = new ApplicationConfig();
    }

    @Test
    public void testGetClasses() {
        // Don't worry about JsonProvider result.  Exists or not is ok.
        Set<Class<?>> result = instance.getClasses();
        assertTrue((1 <= result.size()) && (result.size() <= 2));
        assertTrue(result.contains(AnEntityFacadeREST.class));
    }

    private ApplicationConfig instance;
}