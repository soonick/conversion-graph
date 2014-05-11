package com.ncona.conversiongraph.models;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MeasureTest {
    @Test
    public void constructorSetsValues() {
        Measure m = new Measure("hello", 1);

        assertEquals("hello", m.label);
        assertEquals(1, m.value);
    }
}
