package com.ncona.conversiongraph;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.ncona.conversiongraph.models.Measure;
import com.ncona.conversiongraph.views.BarsView;
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
        final List<Measure> m = new ArrayList<Measure>();
        final Canvas c = mock(Canvas.class);
        final BarsView bv = mock(BarsView.class);
        final Graph instance = mock(Graph.class);
        doCallRealMethod().when(instance).onDraw(c);
        when(instance.getWidth()).thenReturn(400);
        instance.barsView = bv;
        instance.measures = m;

        // Call
        instance.onDraw(c);

        // Verifications
        verify(instance).initializePaint();
        verify(instance).drawLabels(c);
        verify(instance).drawPlane(c, 5);
        verify(bv).draw(c, 5, 400, m);
    }

    @Test
    public void drawLabelsDrawsAllLabelsSeparatedCorrectly() {
        // Mocks
        final List<Measure> m = new ArrayList<Measure>();
        final int[] values = { 0 };
        m.add(new Measure("uno", values));
        m.add(new Measure("dos", values));
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
        final int[] values = { 0 };
        m.add(new Measure("tres", values));
        m.add(new Measure("cuatro", values));
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
}
