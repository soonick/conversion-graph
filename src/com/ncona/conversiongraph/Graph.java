/**
 * @author Adrian Ancona Novelo<soonick5@yahoo.com.mx>
 **/

package com.ncona.conversiongraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import com.ncona.conversiongraph.models.Measure;
import com.ncona.conversiongraph.views.BarsView;
import java.util.List;

public class Graph extends View
{
    /**
     * Label size
     */
    final static private int LABEL_SIZE = 16;

    /**
     * Separation between labels
     */
    final static private int LABEL_SEPARATION = 20;

    /**
     * List of measures that will be graphed
     */
    protected List<Measure> measures;

    /**
     * Paint used for drawing
     */
    protected Paint paint;

    /**
     * View that takes care of drawing the actual bars in the graph
     */
    protected BarsView barsView;

    /**
     * Initialize the view
     */
    public Graph(final Context context) {
        super(context);
        barsView = new BarsView();
    }

    /**
     * Sets the measures to be used when drawing
     * @param measures
     */
    public void setMeasures(final List<Measure> measures) {
        this.measures = measures;
    }

    /**
     * Draws graph
     * @param canvas
     */
    @Override
    public void onDraw(final Canvas c) {
        initializePaint();
        final float planeStart = drawLabels(c) + 5;
        drawPlane(c, planeStart);
        barsView.draw(c, planeStart, (float)getWidth(), measures);
    }

    /**
     * Initialize the paint that will be used for drawing
     */
    protected void initializePaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(LABEL_SIZE);
        paint.setAntiAlias(true);
    }

    /**
     * Draws labels
     * @param c - The canvas where we will draw
     * @return maxWidth - The width of the longest label. (The x coordinate of
     *                    the end of the label.
     */
    protected float drawLabels(final Canvas c) {
        float maxWidth = 0;
        float currentWidth;

        int i = LABEL_SEPARATION + LABEL_SIZE;
        for (final Measure m : measures) {
            currentWidth = paint.measureText(m.label);
            if (currentWidth > maxWidth) {
                maxWidth = currentWidth;
            }

            for (int j = 0; j < m.values.length; j++) {
                c.drawText(m.label, 0, i, paint);
                i += LABEL_SEPARATION + LABEL_SIZE;
            }
        }

        return maxWidth;
    }

    /**
     * Draws the plane where the values will be drawn
     * @param c - The canvas where we will draw
     * @param planeStart - The x coordinate where the plane will start
     */
    protected void drawPlane(final Canvas c, final float planeStart) {
        c.drawLine(planeStart, 0, planeStart, getHeight(), paint);
        c.drawLine(planeStart, 1, getWidth(), 1, paint);
    }
}
