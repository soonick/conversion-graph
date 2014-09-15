package com.ncona.conversiongraph.views;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import com.ncona.conversiongraph.models.Legend;
import com.ncona.conversiongraph.models.Measure;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class BarsViewTest {
    @Test
    public void drawValuesCallsDrawValueWithCorrectArguments() {
        // Mocks
        final Canvas c = mock(Canvas.class);
        final List<Measure> m = new ArrayList<Measure>();
        final int[] values = { 1000, 100 };
        final int[] values2 = { 500, 40 };
        m.add(new Measure("First", values));
        m.add(new Measure("Second", values2));
        final BarsView instance = mock(BarsView.class);
        doCallRealMethod().when(instance).draw(c, 0, 10000, 0, m, null);
        when(instance.getMaxValue()).thenReturn(1000);
        final Paint p = mock(Paint.class);
        instance.paint = p;
        when(p.measureText("1000")).thenReturn(300f);
        instance.measures = m;
        instance.captionSize = 12;
        instance.barHeight = 16;
        instance.barMargin = 10;
        instance.captionMargin = 5;
        instance.roundedPixels = 4;

        instance.draw(c, 0, 10000, 0, m, null);

        // 9690 is the screen width minus 300 for the text and 10 for margin
        verify(instance).drawValue(10, 9690, "1000");
        verify(instance).drawValue(36, 969, "100");
        verify(instance).drawValue(62, 4845, "500 (50%)");
        verify(instance).drawValue(88, 387, "40 (40%)");
    }

    @Test
    public void drawSetsCorrectColorsOnPaint() {
        // Mocks
        final Canvas c = mock(Canvas.class);
        final Legend l1 = new Legend("hello", Color.YELLOW);
        final Legend l2 = new Legend("hello", Color.RED);
        final Legend[] legends = { l1, l2 };
        final List<Measure> m = new ArrayList<Measure>();
        final int[] values = { 1000, 100 };
        final int[] values2 = { 500, 40 };
        m.add(new Measure("First", values));
        m.add(new Measure("Second", values2));
        final BarsView instance = mock(BarsView.class);
        doCallRealMethod().when(instance).draw(c, 0, 10000, 0, m, legends);
        when(instance.getMaxValue()).thenReturn(1000);
        final Paint p = mock(Paint.class);
        instance.paint = p;
        when(p.measureText("1000")).thenReturn(300f);

        instance.draw(c, 0, 10000, 0, m, legends);

        verify(p, times(2)).setColor(Color.YELLOW);
        verify(p, times(2)).setColor(Color.RED);
    }

    @Test
    public void getMaxValueGetsMaximum() {
        // Mocks
        final List<Measure> m = new ArrayList<Measure>();
        final int[] values = { 1003, 1002 };
        m.add(new Measure("uno", values));
        final BarsView instance = mock(BarsView.class);
        doCallRealMethod().when(instance).getMaxValue();
        instance.measures = m;

        // Call
        assertEquals(
            "Gets the maximum value of the first measure",
            1003,
            instance.getMaxValue()
        );
    }

    @Test
    public void drawPrettyRectangleDrawsTwoRectanglesOneOfThemWithRoundedCorners() {
        // Mocks
        final Canvas canvas = mock(Canvas.class);
        final Paint paint = mock(Paint.class);
        final RectF firstRectangle = mock(RectF.class);
        final RectF secondRectangle = mock(RectF.class);

        final BarsView instance = mock(BarsView.class);
        doCallRealMethod().when(instance).drawPrettyRectangle(0, 0, 500, 20);
        when(instance.createRectangle(0, 0, 4, 20)).thenReturn(firstRectangle);
        when(instance.createRectangle(0, 0, 500, 20)).thenReturn(secondRectangle);
        instance.canvas = canvas;
        instance.paint = paint;
        instance.captionSize = 12;
        instance.barHeight = 16;
        instance.barMargin = 10;
        instance.captionMargin = 5;
        instance.roundedPixels = 4;

        instance.drawPrettyRectangle(0, 0, 500, 20);

        verify(canvas).drawRect(firstRectangle, paint);
        verify(canvas).drawRoundRect(secondRectangle, 4, 4, paint);
    }
}
