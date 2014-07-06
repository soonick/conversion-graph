/**
 * @author Adrian Ancona Novelo<soonick5@yahoo.com.mx>
 **/

package com.ncona.conversiongraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import com.ncona.conversiongraph.models.Legend;
import com.ncona.conversiongraph.models.Measure;
import com.ncona.conversiongraph.views.BarsView;
import com.ncona.conversiongraph.views.LegendsView;
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
    final static private int LABEL_SEPARATION = 10;

    /**
     * Margin between the labels and the plane
     */
    final static private int LABELS_PLANE_MARGIN = 5;

    /**
     * Margin against the limit of the canvas
     */
    final static private int MARGIN = 10;

    /**
     * List of measures that will be graphed
     */
    protected List<Measure> measures;

    /**
     * Legends that will be shown on top of the graph
     */
    protected Legend[] legends;

    /**
     * Paint used for drawing
     */
    protected Paint paint;

    /**
     * View that takes care of drawing the actual bars in the graph
     */
    protected BarsView barsView;

    /**
     * View that takes care of drawing the legends
     */
    protected LegendsView legendsView;

    /**
     * Initialize the view
     */
    public Graph(final Context context) {
        super(context);
        barsView = new BarsView();
        legendsView = new LegendsView();
    }

    /**
     * Sets the measures to be used when drawing
     * @param measures
     */
    public void setMeasures(final List<Measure> measures) {
        this.measures = measures;
    }

    /**
     * Sets the legend
     * @param legend
     */
    public void setLegends(final Legend[] legends) {
        this.legends = legends;
    }

    /**
     * Draws graph
     * @param canvas
     */
    @Override
    public void onDraw(final Canvas c) {
        initializePaint();
        int top = legendsView.draw(c, this.legends);
        final int left = drawLabels(c, top) + MARGIN;
        drawPlane(c, left, top);
        barsView.draw(c, left, getWidth(), top, measures, legends);
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
     * @param top - y coordinate from which we will start drawing
     * @return maxWidth - The width of the longest label. (The x coordinate of
     *                    the end of the label.
     */
    protected int drawLabels(final Canvas c, final int top) {
        int maxWidth = 0;
        int currentWidth;

        for (final Measure m : measures) {
            currentWidth = (int)paint.measureText(m.label);
            if (currentWidth > maxWidth) {
                maxWidth = currentWidth;
            }
        }

        int halfLabel = LABEL_SIZE / 2;
        int currentTop = top;
        int currentBottom = 0;
        int labelPosition = 0;
        for (final Measure m : measures) {
            // Calculate where to draw the label
            currentTop = currentTop + MARGIN;
            currentBottom = currentTop + (m.values.length * LABEL_SIZE) +
                    ((m.values.length - 1) * MARGIN);
            labelPosition = currentTop + ((currentBottom - currentTop) / 2) +
                halfLabel;

            currentWidth = (int)paint.measureText(m.label);
            c.drawText(
                m.label,
                MARGIN + maxWidth - currentWidth,
                labelPosition,
                paint
            );

            currentTop = currentBottom;
        }

        return maxWidth + MARGIN;
    }

    /**
     * Draws the plane where the values will be drawn
     * @param c - The canvas where we will draw
     * @param left - The x coordinate where the plane will start
     * @param top - The y coordinate where the plane will start
     */
    protected void drawPlane(final Canvas c, final float left, final int top) {
        c.drawLine(left, top, left, getHeight(), paint);
        c.drawLine(left, top, getWidth(), top, paint);
    }
}
