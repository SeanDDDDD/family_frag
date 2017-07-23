package com.example.sean_duan.family_frag.Topdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by 67342 on 2017/7/20.
 */

public class Top_Textview extends android.support.v7.widget.AppCompatTextView{

    public Top_Textview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public Top_Textview(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
         Paint paint = new Paint();
        //背景为白色
        paint.setColor(Color.WHITE);
        canvas.drawRect(0,0,this.getWidth(),this.getHeight(),paint);
        //将边框设为黑色
         paint.setColor(Color.rgb(232,232,232));
         //画TextView的4个边  
        //canvas.drawLine(0, 0, this.getWidth() - sroke_width, 0, paint);//左边
        paint.setStrokeWidth((float) 10.0);//设置画笔
        canvas.drawLine(0,0,this.getWidth(),0,paint);//上边
        //canvas.drawLine(this.getWidth() - sroke_width, 0, this.getWidth() - sroke_width, this.getHeight() - sroke_width, paint);//右边
        paint.setStrokeWidth((float) 8.0);
        canvas.drawLine(0, this.getHeight() , this.getWidth() , this.getHeight(), paint);//下边
//        paint.setColor(Color.BLACK);
//        paint.setStrokeWidth((float) 20.0);
//        canvas.drawText(this.getText(),);

         super.onDraw(canvas);
        }
}
