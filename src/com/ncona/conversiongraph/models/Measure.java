/**
 * @author Adrian Ancona Novelo<soonick5@yahoo.com.mx>
 **/

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
     * If you have different values for the same measure you can pass a list.
     * You can have either a list of values of a value but not both.
     */
    public int[] values;

    /**
     * Constructor that sets the label and value
     * @param label
     * @param value
     */
    public Measure(final String l, final int v) {
        label = l;
        value = v;
        values = null;
    }

    /**
     * Constructor that sets the label and values
     * @param label
     * @param values
     */
    public Measure(final String l, final int[] v) {
        label = l;
        values = v;
    }
}
