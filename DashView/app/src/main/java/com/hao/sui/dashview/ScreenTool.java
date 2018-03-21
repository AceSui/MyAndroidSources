package com.hao.sui.dashview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;

/**
 * Created by sh on 2018/3/21.
 */

public class ScreenTool {
    /*状态栏总高度*/
    public static int getStatusBarHeight(Context context){
        int statusBarHeight2 = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight2 = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight2;
    }

    /*状态栏与标题栏的总高度*/
    public static int getHeadHeight(Activity context){
        return context.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }

    /*屏幕总高度*/
    public static int getScreenHeight(Activity context){
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Log.e("getScreenHeight",""+ dm.heightPixels);

        return dm.heightPixels;
    }
}
