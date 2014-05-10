package com.ncona.abgsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.ncona.abg.Abg;

public class Main extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Abg abg = new Abg(this);
        setContentView((View)abg);
    }
}
