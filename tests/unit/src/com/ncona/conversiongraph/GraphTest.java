package com.ncona.conversiongraph;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.ncona.conversiongraph.models.Measure;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class GraphTest {
    @Test
    public void onDrawDrawsEverything() {
        // Mocks
        final Canvas c = mock(Canvas.class);
        final Graph instance = mock(Graph.class);
        doCallRealMethod().when(instance).onDraw(c);

        // Call
        instance.onDraw(c);

        // Verifications
        verify(instance).initializePaint();
        verify(instance).drawLabels(c);
        verify(instance).drawPlane(c, 5);
        verify(instance).drawValues(c, 5);
    }

    @Test
    public void drawLabelsDrawsAllLabelsSeparatedCorrectly() {
        // Mocks
        final List<Measure> m = new ArrayList<Measure>();
        m.add(new Measure("uno", 0));
        m.add(new Measure("dos", 0));
        final Canvas c = mock(Canvas.class);
        final Paint p = mock(Paint.class);
        final Graph instance = mock(Graph.class);
        doCallRealMethod().when(instance).drawLabels(c);
        instance.paint = p;
        instance.measures = m;

        // Call
        instance.drawLabels(c);

        // Verifications
        verify(c).drawText("uno", 0, 36, p);
        verify(c).drawText("dos", 0, 72, p);
    }

    @Test
    public void drawLabelsReturnsWidthOfLongestLabel() {
        // Mocks
        final List<Measure> m = new ArrayList<Measure>();
        m.add(new Measure("tres", 0));
        m.add(new Measure("cuatro", 0));
        final Canvas c = mock(Canvas.class);
        final Paint p = mock(Paint.class);
        when(p.measureText("tres")).thenReturn((float)10);
        when(p.measureText("cuatro")).thenReturn((float)6);
        final Graph instance = mock(Graph.class);
        doCallRealMethod().when(instance).drawLabels(c);
        instance.paint = p;
        instance.measures = m;

        // Call
        assertEquals(
            "Returns the width of the longest label",
            10,
            (int)instance.drawLabels(c)
        );
    }

    @Test
    public void drawValuesDrawsValues() {
        // Mocks
        final List<Measure> m = new ArrayList<Measure>();
        m.add(new Measure("uno", 100));
        m.add(new Measure("dos", 10));
        final Canvas c = mock(Canvas.class);
        final Paint p = mock(Paint.class);
        final Graph instance = mock(Graph.class);
        doCallRealMethod().when(instance).drawValues(c, 5);
        when(instance.getWidth()).thenReturn(200);
        instance.paint = p;
        instance.measures = m;

        // Call
        instance.drawValues(c, 5);

        // Verifications
        verify(c).drawRect(5, 20, 200, 36, p);
        verify(c).drawRect(5, 56, 24, 72, p);
    }
}
