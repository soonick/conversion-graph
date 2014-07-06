package com.ncona.conversiongraph.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

        instance.initializePaint();

        assertEquals("Color is white", Color.WHITE, instance.paint.getColor());
        assertEquals("Style is fill", Paint.Style.FILL, instance.paint.getStyle());
        assertEquals("Text size is 12", 12, instance.paint.getTextSize(), 0);
        assertTrue("Antialias is on", instance.paint.isAntiAlias());
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

        instance.draw(canvas, legends);

        verify(canvas).drawRect(10, 10, 22, 22, paint);
        verify(canvas).drawRect(10, 27, 22, 39, paint);
        verify(canvas).drawText("hello", 27, 22, instance.paint);
        verify(canvas).drawText("Something else", 27, 39, instance.paint);
    }

    @Test
    public void drawDrawsReturnsBottomOfLegends() {
        // Mocks
        final Paint paint = mock(Paint.class);
        final Canvas canvas = mock(Canvas.class);
        final Legend legend1 = new Legend("hello", 4);
        final Legend legend2 = new Legend("Something else", 7);
        final Legend[] legends = { legend1, legend2 };

        final LegendsView instance = mock(LegendsView.class);
        instance.paint = paint;
        doCallRealMethod().when(instance).draw(canvas, legends);

        assertEquals(
            "Retuns bottom of all legends",
            instance.draw(canvas, legends),
            44
        );
    }
}
