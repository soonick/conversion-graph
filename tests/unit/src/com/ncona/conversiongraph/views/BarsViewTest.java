package com.ncona.conversiongraph.views;

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
        doCallRealMethod().when(instance).draw(c, 0f, 10000f, m);
        when(instance.getMaxValue()).thenReturn(1000);
        final Paint p = mock(Paint.class);
        instance.paint = p;
        when(p.measureText("1000")).thenReturn(300f);
        instance.measures = m;

        instance.draw(c, 0f, 10000f, m);

        // 9700 is the width of the screen minus 300 for the text
        verify(instance).drawValue(20, 9690, "1000");
        verify(instance).drawValue(56, 969, "100");
        verify(instance).drawValue(92, 4845, "500 (50%)");
        verify(instance).drawValue(128, 387, "40 (40%)");
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
}
