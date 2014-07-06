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
     * Paint used for drawing
     */
    protected Paint paint;

    /**
     * Initialize the paint
     */
    public LegendsView() {
        initializePaint();
    }

    /**
     * Initialize the paint that will be used for drawing
     */
    protected void initializePaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(TEXT_SIZE);
        paint.setAntiAlias(true);
    }

    /**
     * Draws legends on top of the given canvas
     * @param canvas - Canvas where we will draw
     * @param legends - Legends to be drawn
     * @return The y coordinate for the bottom of the legends. This is so the
     *          graph knows to start drawing below this point.
     */
    public int draw(final Canvas canvas, final Legend[] legends) {
        int top = MARGIN;
        int left = MARGIN;
        int colorRight = left + TEXT_SIZE;
        int legendLeft = left + TEXT_SIZE + LEGENDS_MARGIN;

        for (int i = 0; i < legends.length; i++) {
            canvas.drawRect(left, top, colorRight, top + TEXT_SIZE, paint);
            canvas.drawText(legends[i].label, legendLeft, top + TEXT_SIZE, paint);
            top += TEXT_SIZE + LEGENDS_MARGIN;
        }

        return top;
    }
}
