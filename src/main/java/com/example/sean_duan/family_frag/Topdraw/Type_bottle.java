package com.example.sean_duan.family_frag.Topdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by 67342 on 2017/7/22.
 */

public class Type_bottle extends android.support.v7.widget.AppCompatSpinner {
    public Type_bottle(Context context) {
        super(context);
    }
    public Type_bottle(Context context, AttributeSet attrs) {

        super(context, attrs );
    }

    public Type_bottle(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle );
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        float circle = this.getHeight()/2;
        canvas.drawCircle(circle,circle,circle,paint);
        canvas.drawCircle(getWidth()-circle,circle,circle,paint);
        canvas.drawRect(circle,0,getWidth()-circle,getHeight(),paint);
        paint.setColor(Color.BLACK);
        float w = getWidth();
        float h = getHeight();
        paint.setStrokeWidth((float)7.0);
        canvas.drawLine(w-h-10,h/2-10,w-h+20,20+h/2,paint);
        canvas.drawLine(w-h+20,20+h/2,w-h+50,h/2-10,paint);
    }
}
