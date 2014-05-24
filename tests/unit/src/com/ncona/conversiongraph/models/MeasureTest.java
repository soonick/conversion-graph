package com.ncona.conversiongraph.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MeasureTest {
    @Test
    public void constructorSetsValues() {
        final int[] values = {2, 3};
        final Measure m = new Measure("bye", values);

        assertEquals("Label was set from constructor", "bye", m.label);
        assertEquals("Set from constructor", values, m.values);
    }

    @Test
    public void constructorSetsValue() {
        final Measure m = new Measure("hello", 1);

        assertEquals("Label was set from constructor", "hello", m.label);
        assertEquals("Set from constructor", 1, m.value);
        assertNull("Unset values", m.values);
    }
}
