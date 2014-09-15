package com.ncona.conversiongraph.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.ncona.conversiongraph.models.Legend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LegendsViewTest {
    @Test
    public void initializePaint() {
        final LegendsView instance = mock(LegendsView.class);
        doCallRealMethod().when(instance).initializePaint();
        instance.textSize = 12;

        instance.initializePaint();

        assertEquals("Color is white", Color.WHITE, instance.paint.getColor());
        assertEquals("Style is fill", Paint.Style.FILL, instance.paint.getStyle());
        assertEquals("Text size is 12", 12, instance.paint.getTextSize(), 0);
        assertTrue("Antialias is on", instance.paint.isAntiAlias());
    }

    @Test
    public void createRectanglePaint() {
        final LegendsView instance = mock(LegendsView.class);
        doCallRealMethod().when(instance).createRectanglePaint(5);

        final Paint res = instance.createRectanglePaint(5);

        assertEquals("Color is correctly set", 5, res.getColor());
        assertEquals("Style is fill", Paint.Style.FILL, res.getStyle());
    }

    @Test
    public void drawDrawsTheLegends() {
        // Mocks
        final Paint paint = mock(Paint.class);
        final Canvas canvas = mock(Canvas.class);
        final Legend legend1 = new Legend("hello", 4);
        final Legend legend2 = new Legend("Something else", 7);
        final Legend[] legends = { legend1, legend2 };

        final LegendsView instance = mock(LegendsView.class);
        instance.paint = paint;
        doCallRealMethod().when(instance).draw(canvas, legends);
        instance.margin = 10;
        instance.legendsMargin = 5;
        instance.textSize = 12;


        instance.draw(canvas, legends);

        verify(canvas).drawRect(10, 10, 22, 22, null);
        verify(canvas).drawRect(10, 27, 22, 39, null);
        verify(canvas).drawText("hello", 27, 22, instance.paint);
        verify(canvas).drawText("Something else", 27, 39, instance.paint);
    }

    @Test
    public void drawReturnsBottomOfLegendsPlusMargin() {
        // Mocks
        final Paint paint = mock(Paint.class);
        final Canvas canvas = mock(Canvas.class);
        final Legend legend1 = new Legend("hello", 4);
        final Legend legend2 = new Legend("Something else", 7);
        final Legend[] legends = { legend1, legend2 };

        final LegendsView instance = mock(LegendsView.class);
        instance.paint = paint;
        doCallRealMethod().when(instance).draw(canvas, legends);
        instance.margin = 10;
        instance.legendsMargin = 5;
        instance.textSize = 12;


        assertEquals(
            "Retuns bottom of all legends",
            instance.draw(canvas, legends),
            54
        );
    }

    @Test
    public void drawUsesLegenColorsToDrawLegendRectangles() {
        final Paint p1 = mock(Paint.class);
        final Paint p2 = mock(Paint.class);
        final Paint paint = mock(Paint.class);
        final Canvas canvas = mock(Canvas.class);
        final Legend legend1 = new Legend("abcd", 4);
        final Legend legend2 = new Legend("efg", 7);
        final Legend[] legends = { legend1, legend2 };

        final LegendsView instance = mock(LegendsView.class);
        instance.paint = paint;
        doCallRealMethod().when(instance).draw(canvas, legends);
        when(instance.createRectanglePaint(4)).thenReturn(p1);
        when(instance.createRectanglePaint(7)).thenReturn(p2);
        instance.margin = 10;
        instance.legendsMargin = 5;
        instance.textSize = 12;

        instance.draw(canvas, legends);

        verify(canvas).drawRect(10, 10, 22, 22, p1);
        verify(canvas).drawRect(10, 27, 22, 39, p2);
    }
}
