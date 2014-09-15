package com.ncona.conversiongraph;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.ncona.conversiongraph.models.Legend;
import com.ncona.conversiongraph.models.Measure;
import com.ncona.conversiongraph.views.BarsView;
import com.ncona.conversiongraph.views.LegendsView;
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
        final Legend legend = new Legend("hello", 5);
        final Legend[] legends = { legend };
        final LegendsView lv = mock(LegendsView.class);
        when(lv.draw(c, legends)).thenReturn(0);
        final Graph instance = mock(Graph.class);
        doCallRealMethod().when(instance).onDraw(c);
        when(instance.getWidth()).thenReturn(400);
        instance.barsView = bv;
        instance.measures = m;
        instance.legends = legends;
        instance.legendsView = lv;
        instance.margin = 10;

        // Call
        instance.onDraw(c);

        // Verifications
        verify(instance).initializePaint();
        verify(instance).drawLabels(c, 0);
        verify(instance).drawPlane(c, 10, 0);
        verify(bv).draw(c, 10, 400, 0, m, legends);
    }

    @Test
    public void calculateSizesTakesDensityIntoAccount() {
        // Mocks
        final Graph instance = mock(Graph.class);
        doCallRealMethod().when(instance).calculateSizes(2);

        // Call
        instance.calculateSizes(2);

        // Assertions
        assertEquals("Margin is twice the original", instance.margin, 20);
        assertEquals("Label size is twice the original size", instance.labelSize, 32);
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
        doCallRealMethod().when(instance).drawLabels(c, 0);
        instance.paint = p;
        instance.measures = m;
        instance.margin = 10;
        instance.labelSize = 16;

        // Call
        instance.drawLabels(c, 0);

        // Verifications
        verify(c).drawText("uno", 10, 26, p);
        verify(c).drawText("dos", 10, 52, p);
    }

    @Test
    public void drawLabelsDrawsAllLabelsSeparatedCorrectlyEvenWhenThereIsMoreThanOneValue() {
        // Mocks
        final List<Measure> m = new ArrayList<Measure>();
        final int[] values = { 0, 0 };
        m.add(new Measure("diez", values));
        m.add(new Measure("once", values));
        final Canvas c = mock(Canvas.class);
        final Paint p = mock(Paint.class);
        final Graph instance = mock(Graph.class);
        doCallRealMethod().when(instance).drawLabels(c, 0);
        instance.paint = p;
        instance.measures = m;
        instance.margin = 10;
        instance.labelSize = 16;

        // Call
        instance.drawLabels(c, 0);

        // Verifications
        verify(c).drawText("diez", 10, 39, p);
        verify(c).drawText("once", 10, 91, p);
    }

    @Test
    public void drawLabelsReturnsTheYCoordinateWhereTheLongestLabelEnds() {
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
        doCallRealMethod().when(instance).drawLabels(c, 0);
        instance.paint = p;
        instance.measures = m;
        instance.margin = 10;

        // Call
        assertEquals(
            "Returns the width of the longest label + margin",
            20,
            instance.drawLabels(c, 0)
        );
    }

    @Test
    public void setLegendSavesTheLegends() {
        final Legend legend = new Legend("hello", 5);
        final Legend[] legends = { legend };

        final Graph instance = mock(Graph.class);
        doCallRealMethod().when(instance).setLegends(legends);

        instance.setLegends(legends);

        assertArrayEquals(
            "Legends were saved as an attribute",
            legends,
            instance.legends
        );
    }
}
