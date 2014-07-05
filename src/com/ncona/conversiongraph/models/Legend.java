/**
 * @author Adrian Ancona Novelo<soonick5@yahoo.com.mx>
 **/

package com.ncona.conversiongraph.models;

public class Legend
{
    /**
     * Text that will be shown as a label for this legend
     */
    public String label;

    /**
     * Color for this legend
     */
    public int color;

    /**
     * Constructor that sets the label and color
     * @param label
     * @param color
     */
    public Legend(final String label, final int color) {
        this.label = label;
        this.color = color;
    }
}
