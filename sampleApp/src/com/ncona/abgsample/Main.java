package com.ncona.abgsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.ncona.abg.Graph;
import com.ncona.abg.models.Measure;
import java.util.ArrayList;
import java.util.List;

public class Main extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Graph abg = new Graph(this);
        List<Measure> measures = new ArrayList<Measure>();
        measures.add(new Measure("Leads", 10000));
        measures.add(new Measure("Prospects", 3000));
        measures.add(new Measure("Customers", 500));
        abg.setMeasures(measures);
        setContentView((View)abg);
    }
}
