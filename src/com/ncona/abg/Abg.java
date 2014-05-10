package com.ncona.abg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Abg extends View
{
    /**
     * Context from which this view is being used
     */
    private Context context;

    /**
     * Initialize the view
     */
    public Abg(final Context ctx) {
      super(ctx);
      context = ctx;
    }

    /**
     * Draws everything
     * @param canvas
     */
    @Override
    protected void onDraw(final Canvas canvas) {
        drawHello(canvas);
    }

    /**
     * Draws a hello message
     * @param canvas
     */
    public void drawHello(final Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(20);
        canvas.drawText(
            context.getString(R.string.hello_world),
            (float)10,
            (float)10,
            paint
        );
    }
}
