package com.hao.sui.dashview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4;
    RelativeLayout testRl;
    DashView dashView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("onCreate","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        testRl=findViewById(R.id.test);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();


    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e("onWindowFocusChanged","onWindowFocusChanged");

        if(dashView==null){
            Log.e("onWindowFocusChanged","dashView");
            RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout view=new RelativeLayout(this);
            view.setLayoutParams(lp);

            dashView=new DashView(this,tv2,tv1);
            dashView.shieldTitle(true);
            dashView.setColor("#995555");
            dashView.setStrokeWidth(5);
            dashView.setEndPointRadius(10);
            ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            dashView.setLayoutParams(vlp);
            view.addView(dashView);

            dashView=new DashView(this,tv2,tv3);
            dashView.shieldTitle(true);
            dashView.setColor("#559955");
            dashView.setStrokeWidth(5);
            dashView.setEndPointRadius(10);
            dashView.setLayoutParams(vlp);
            view.addView(dashView);

            dashView=new DashView(this,tv2,tv4);
            dashView.shieldTitle(true);
            dashView.setColor("#555599");
            dashView.setStrokeWidth(5);
            dashView.setEndPointRadius(10);
            dashView.setLayoutParams(vlp);
            view.addView(dashView);
            testRl.addView(view);
        }


    }
}
