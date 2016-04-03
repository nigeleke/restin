package com.nigeleke.restin.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AnEntityTest {

    @Before
    public void setUp() {
        instance = new AnEntity();
    }

    @Test
    public void testToString() {
        instance.setId(Long.valueOf(18870812L));
        instance.setName("Erwin Schrödinger");

        assertThat("com.nigeleke.restin.AnEntity[ id=18870812, name=Erwin Schrödinger]", equalTo(instance.toString()));
    }

    private AnEntity instance;
}