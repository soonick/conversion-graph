/**
 * @author Adrian Ancona Novelo<soonick5@yahoo.com.mx>
 **/

package com.ncona.conversiongraph.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.ncona.conversiongraph.models.Legend;

public class LegendsView
{
    /**
     * Text size for the legend
     */
    final static private int TEXT_SIZE = 12;

    /**
     * Margin from the limit of the canvas
     */
    final static private int MARGIN = 10;

    /**
     * Margin between legends
     */
    final static private int LEGENDS_MARGIN = 5;

    /**
     * Text size considering density
     */
    protected int textSize;

    /**
     * Margin considering density
     */
    protected int margin;

    /**
     * Margin between legends considering density
     */
    protected int legendsMargin;

    /**
     * Default color for legend
     */
    final static private int DEFAULT_COLOR = Color.WHITE;

    /**
     * Paint used for drawing
     */
    protected Paint paint;

    /**
     * Initialize the paint
     */
    public LegendsView(final float density) {
        calculateSizes(density);
        initializePaint();
    }

    /**
     * Calculates label size and margin based on screen density
     */
    protected void calculateSizes(final float density) {
        textSize = (int)((float)TEXT_SIZE * density);
        margin = (int)((float)MARGIN * density);
        legendsMargin = (int)((float)LEGENDS_MARGIN * density);
    }

    /**
     * Initialize the paint that will be used for drawing
     */
    protected void initializePaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(DEFAULT_COLOR);
        paint.setTextSize(textSize);
        paint.setAntiAlias(true);
    }

    /**
     * Creates a paint for a legend's rectangle
     * @param color - Color for this paint
     * @return paint
     */
    protected Paint createRectanglePaint(final int color) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);

        return paint;
    }

    /**
     * Draws legends on top of the given canvas
     * @param canvas - Canvas where we will draw
     * @param legends - Legends to be drawn
     * @return The y coordinate for the bottom of the legends. This is so the
     *          graph knows to start drawing below this point.
     */
    public int draw(final Canvas canvas, final Legend[] legends) {
        int top = margin;
        int left = margin;
        int colorRight = left + textSize;
        int legendLeft = left + textSize + legendsMargin;

        for (int i = 0; i < legends.length; i++) {
            canvas.drawRect(left, top, colorRight, top + textSize,
                    createRectanglePaint(legends[i].color));
            canvas.drawText(legends[i].label, legendLeft, top + textSize, paint);
            top += textSize + legendsMargin;
        }

        return top + margin;
    }
}
