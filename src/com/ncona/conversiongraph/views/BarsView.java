/**
 * @author Adrian Ancona Novelo<soonick5@yahoo.com.mx>
 **/

package com.ncona.conversiongraph.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.ncona.conversiongraph.models.Legend;
import com.ncona.conversiongraph.models.Measure;
import java.util.List;

public class BarsView
{
    /**
     * Size of the bars captions
     */
    final static private int CAPTION_SIZE = 12;

    /**
     * Height of the bars
     */
    final static private int BAR_HEIGHT = 16;

    /**
     * Separation between bars
     */
    final static private int BAR_MARGIN = 10;

    /**
     * Margin between the bar and its caption
     */
    final static private int CAPTION_MARGIN = 5;

    /**
     * Canvas where bars will be drawn
     */
    protected Canvas canvas;

    /**
     * Coordinate x left limit
     */
    protected float left;

    /**
     * Coordinate x right limit
     */
    protected float right;

    /**
     * List of measures that will be graphed
     */
    protected List<Measure> measures;

    /**
     * Paint used for drawing
     */
    protected Paint paint;

    /**
     * Initialize the paint
     */
    public BarsView() {
        initializePaint();
    }

    /**
     * Initialize the paint that will be used for drawing
     */
    protected void initializePaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(CAPTION_SIZE);
        paint.setAntiAlias(true);
    }

    /**
     * Draws the values inside of the plane
     * @param c - The canvas where we will draw
     * @param left - The x coordinate where the plane starts
     * @param right - The x coordinate where the plane ends
     * @param measures - The measures that will be drawn
     */
    public void draw(final Canvas canvas, final float left, final float right,
            int top, List<Measure> measures, Legend[] legends) {
        this.canvas = canvas;
        this.measures = measures;
        this.left = left;
        this.right = right;

        int maxValue = getMaxValue();

        // The amount of pixels it takes to print the largest caption
        float maxValueWidth = paint.measureText(Integer.toString(maxValue));

        // The width of the longest bar in pixels
        final int maxWidth = (int)right -
                (int)(maxValueWidth + left + (CAPTION_MARGIN * 2));

        top += BAR_MARGIN;

        Measure first = measures.get(0);
        String caption;

        for (final Measure m : measures) {
            for (int j = 0; j < m.values.length; j++) {
                if (legends != null) {
                    paint.setColor(legends[j].color);
                }

                caption = Integer.toString(m.values[j]);
                int barWidth = (m.values[j] * maxWidth) / maxValue;
                int percentage = (m.values[j] * 100) / first.values[j];
                if (percentage != 100) {
                    caption += " (" + percentage + "%)";
                }
                drawValue(top, barWidth, caption);
                top += BAR_MARGIN + BAR_HEIGHT;
            }
        }
    }

    /**
     * Draws a bar with it's caption
     * @param top - The top coordinate where this bar will start
     * @param value - The caption for this bar
     * @param width - The width of the bar
     * @param percentage - The percetage value
     */
    protected void drawValue(final int top, final int width, final String caption) {
        canvas.drawRect(
            left,
            top,
            left + width,
            top + BAR_HEIGHT,
            paint
        );

        canvas.drawText(
            caption,
            left + width + CAPTION_MARGIN,
            top + CAPTION_SIZE,
            paint
        );
    }

    /**
     * Since this is a conversion graph we always expect the first values to be
     * the greatest. We just need to figure out which of the first values is it.
     * @return max - highest measure value
     */
    protected int getMaxValue() {
        int max = 0;

        // The first one
        Measure m = measures.get(0);

        if (m.values != null) {
            for (int i = 0; i < m.values.length; i++) {
                if (m.values[i] > max) {
                    max = m.values[i];
                }
            }
        } else {
            max = m.value;
        }

        return max;
    }
}
