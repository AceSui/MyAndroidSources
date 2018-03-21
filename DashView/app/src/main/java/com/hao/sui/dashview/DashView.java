package com.hao.sui.dashview;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;

/**
 * Created by sh on 2018/3/20.
 */

public class DashView extends View {
    float x1=100;
    float y1=100;

    float x2=300;
    float y2=300;

    int color= Color.parseColor("#555555");
    int strokeWith=3;
    int radius=5;
    boolean isShiledTitleBar=false;

    private Activity mContext;

    private DrawMode mode;
    public DashView(Context context){
        super(context);
    }
    public DashView(Activity context,float x1,float y1,float x2,float y2) {
        super(context);
        this.mContext=context;
        this.x1=x1;
        this.y1=y1;

        this.x2=x2;
        this.y2=y2;

        float x_del=Math.abs(x1-x2);
        float y_del=Math.abs(y1-y2);
        if(x_del>y_del){
            mode= DrawMode.Horizontal;
        }
        else{
            mode= DrawMode.Vertical;
        }
    }

    public DashView(Activity context, View startView, View endView){
        super(context);
        this.mContext=context;
        int []location=new int[2];
        startView.getLocationInWindow(location);

        x1=location[0]+startView.getWidth()/2;
        y1=location[1]+startView.getHeight()/2;

        endView.getLocationInWindow(location);
        x2=location[0]+endView.getWidth()/2;
        y2=location[1]+endView.getHeight()/2;


        Log.e("DashView","DashView");
        Log.e("x1",x1+"");
        Log.e("y1",y1+"");
        Log.e("x2",x2+"");
        Log.e("y2",y2+"");
        float x_del=Math.abs(x1-x2);//
        float y_del=Math.abs(y1-y2);//判断窗体方位，选择绘制样式
        if(x_del>y_del){
            mode= DrawMode.Horizontal;
            if(x1>x2){
                x1-=startView.getWidth()/2;
                x2+=endView.getWidth()/2;
            }
            else{
                x1+=startView.getWidth()/2;
                x2-=endView.getWidth()/2;
            }
        }
        else{
            mode= DrawMode.Vertical;
            if(y1>y2){
                y1-=startView.getHeight()/2;
                y2+=endView.getHeight()/2;
            }
            else{
                y1+=startView.getHeight()/2;
                y2-=endView.getHeight()/2;
            }

        }
    }

    public void shieldTitle(boolean isShield){
        if(isShiledTitleBar){
            if(!isShield){
                isShiledTitleBar=false;
                y1+=(ScreenTool.getHeadHeight(mContext)+ScreenTool.getStatusBarHeight(mContext));
                y2+=(ScreenTool.getHeadHeight(mContext)+ScreenTool.getStatusBarHeight(mContext));
            }
        }
        else{
            if(isShield){
                isShiledTitleBar=true;
                y1-=(ScreenTool.getHeadHeight(mContext)+ScreenTool.getStatusBarHeight(mContext));
                y2-=(ScreenTool.getHeadHeight(mContext)+ScreenTool.getStatusBarHeight(mContext));
            }
        }
    }

    public void setColor(String color){
        this.color=Color.parseColor(color);
    }

    public void setStrokeWidth(int width){
        this.strokeWith=width;
    }
    public void setEndPointRadius(int radius){
        this.radius=radius;
    }
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(strokeWith);
        paint.setAntiAlias(true);

        PathEffect effects=new DashPathEffect(new float[]{10,3},1);
        paint.setPathEffect(effects);

        paint.setStyle(Paint.Style.STROKE);
        Path path2=new Path();
        path2.moveTo(x1,y1);
        float quaX=0;
        float quaY=0;
        if(mode.equals(DrawMode.Horizontal)){
            if(y1>y2){
                quaY=y2;
                quaX=x1+1*(x2-x1)/4;
            }else{
                quaY=y1;
                quaX=x1+1*(x2-x1)/4;
            }
        }
        else {
            if(x1>x2){
                quaX=x2;
                quaY=y1+3*(y2-y1)/4;
            }else{
                quaX=x1;
                quaY=y1+3*(y2-y1)/4;
            }
        }

        path2.lineTo(quaX,quaY);
        path2.lineTo(x2,y2);

        canvas.drawPath(path2,paint);
        Paint paint2=new Paint();
        paint2.setColor(color);
        paint2.setStrokeWidth((float) 60.0);
        canvas.drawCircle(x2,y2,radius,paint2);
    }


    enum DrawMode{
        Horizontal,//水平方向连线
        Vertical//竖直方向连线
    }
}
