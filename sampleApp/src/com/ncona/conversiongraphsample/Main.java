package com.ncona.conversiongraphsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.ncona.conversiongraph.Graph;
import com.ncona.conversiongraph.models.Legend;
import com.ncona.conversiongraph.models.Measure;
import java.util.ArrayList;
import java.util.List;

public class Main extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Graph conversiongraph = new Graph(this);
        List<Measure> measures = new ArrayList<Measure>();

        int[] values = { 10000, 100 };
        measures.add(new Measure("Leads", values));
        int[] values2 = { 3000, 50 };
        measures.add(new Measure("Prospects", values2));
        int[] values3 = { 500, 5 };
        measures.add(new Measure("Customers", values3));
        conversiongraph.setMeasures(measures);
        Legend[] legend = {
            new Legend("this month", 4),
            new Legend("Last month", 89),
        };
        conversiongraph.setLegends(legend);
        setContentView((View)conversiongraph);
    }
}
