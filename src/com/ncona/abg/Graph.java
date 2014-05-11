package com.ncona.abg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import com.ncona.abg.models.Measure;
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
     * Value text size
     */
    final static private int VALUE_SIZE = 12;

    /**
     * Margin from the value bar and the caption
     */
    final static private int VALUE_MARGIN = 5;

    /**
     * Context from which this view is being used
     */
    protected Context context;

    /**
     * List of measures that will be graphed
     */
    protected List<Measure> measures;

    /**
     * Paint used for drawing
     */
    protected Paint paint;

    /**
     * Initialize the view
     */
    public Graph(final Context ctx) {
      super(ctx);
      context = ctx;
    }

    /**
     * Sets the measures to be used when drawing
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
        drawValues(c, planeStart);
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
            c.drawText(m.label, 0, i, paint);
            i += LABEL_SEPARATION + LABEL_SIZE;
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

    /**
     * Draws the plane where the values will be drawn
     * @param c - The canvas where we will draw
     * @param planeStart - The x coordinate where the plane starts
     */
    protected void drawValues(final Canvas c, final float planeStart) {
        int maxValue = 0;
        float maxValueWidth = 0;
        float currentValueWidth = 0;

        for (final Measure m : measures) {
            if (m.value > maxValue) {
                maxValue = m.value;
            }

            currentValueWidth = paint.measureText(Integer.toString(m.value));
            if (currentValueWidth > maxValueWidth) {
                maxValueWidth = currentValueWidth;
            }
        }

        int i = LABEL_SEPARATION;
        final int maxWidth = getWidth() - (int)(maxValueWidth + planeStart);
        int currentWidth;
        int currentPercentage;
        float currentTextSize;
        paint.setTextSize(VALUE_SIZE);

        for (final Measure m : measures) {
            currentWidth = (m.value * maxWidth) / maxValue;
            c.drawRect(
                planeStart, i,
                planeStart + currentWidth, i + LABEL_SIZE,
                paint
            );
            c.drawText(
                Integer.toString(m.value),
                planeStart + currentWidth + VALUE_MARGIN, i + VALUE_SIZE,
                paint
            );
            currentTextSize = paint.measureText(Integer.toString(m.value));
            if (i != LABEL_SEPARATION) {
                currentPercentage = (m.value * 100) / maxValue;
                c.drawText(
                    "(" + currentPercentage + "%)",
                    planeStart + currentWidth + currentTextSize + VALUE_MARGIN,
                    i + VALUE_SIZE,
                    paint
                );
            }
            i += LABEL_SEPARATION + LABEL_SIZE;
        }
    }
}
