package com.example.anton.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by anton on 04/10/2016.
 */
public class OnTouchActivity extends AppCompatActivity {
    float x1, y1 , x2, y2, a, b;
    String msg1 = "", msg2="";
    ImageView imageLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ontouch_activity);
        final EditText tx = (EditText) findViewById(R.id.text_x);
        tx.setKeyListener(null);
        final EditText ty = (EditText) findViewById(R.id.text_y);
        ty.setKeyListener(null);
        final EditText tDiff = (EditText) findViewById(R.id.diff);
        tDiff.setKeyListener(null);
        final EditText tQuad = (EditText) findViewById(R.id.quadrant);
        tQuad.setKeyListener(null);
        final EditText tMotion = (EditText) findViewById(R.id.motion);
        tMotion.setKeyListener(null);
        imageLogo = (ImageView) findViewById(R.id.logo);

        imageLogo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();
                        tx.setText(x1 + ", "+y1);
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2= event.getY();
                        ty.setText(x2 +", "+y2);
                        a=x1-x2;
                        b=y1-y2;
                        tDiff.setText(Math.abs(a) +", "+Math.abs(b));
                        if (a>0 & b>0 ){msg2="2nd Quadrant";}
                        if (a>0 & b<0){msg2="3rd Quadrant";}
                        if (a<0 & b<0){msg2="4th Quadrant";}
                        if (a<0 & b>0){msg2="1st Quadrant";}
                        if (y1 < y2){msg1 +=" Swiped Bottom";}
                        if (y1 > y2){msg1 +=" Swiped Up";}
                        if (x1 > x2){msg1 +=" Swiped Left";}
                        if (x1 < x2){msg1 +=" Swiped Right";}
                        tQuad.setText(msg1);
                        msg1="";
                        tMotion.setText(msg2);
                        msg2="";
                }return true;
            }
        });
    }
}
