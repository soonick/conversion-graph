package com.ncona.conversiongraph.models;

public class Measure
{
    /**
     * Text that will be shown as a label for this measure
     */
    public String label;

    /**
     * Value for this measure
     */
    public int value;

    /**
     * Constructor that sets the label and value
     * @param label
     * @param value
     */
    public Measure(final String l, final int v) {
        label = l;
        value = v;
    }
}
