package com.ncona.conversiongraph.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MeasureTest {
    @Test
    public void constructorSetsValues() {
        final Measure m = new Measure("hello", 1);

        assertEquals("Label was set from constructor", "hello", m.label);
        assertEquals("Set from constructor", 1, m.value);
    }
}
