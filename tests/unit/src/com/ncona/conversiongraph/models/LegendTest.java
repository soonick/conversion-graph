package com.ncona.conversiongraph.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LegendTest {
    @Test
    public void constructorSetsLabelsAndColor() {
        final Legend l = new Legend("hello", 4);

        assertEquals("Label was set", "hello", l.label);
        assertEquals("Color was set", 4, l.color);
    }
}
