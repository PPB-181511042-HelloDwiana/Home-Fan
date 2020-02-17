package com.example.homefan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    boolean fanstatus;
    final int SPEED[]= {0,5000, 3000, 1000, 500};
    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);


        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        imageView= (ImageView) findViewById(R.id.imageView);
        switchButton= (Switch) findViewById(R.id.switch1);
        seekBar= (SeekBar) findViewById(R.id.seekBar2);

        rotateAnimator=ObjectAnimator.ofFloat(imageView, "rotation", 0,360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator ());
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);


        toggleButton.setOnClickListener (new View.OnClickListener () {
            @Override
                    public void onClick (View v) {

                boolean on= ((ToggleButton) v).isChecked();
                if(on) {
                    rotateAnimator.setDuration(SPEED[seekBar.getProgress()]);
                    rotateAnimator.start();
                    fanstatus=true;
                }
                else {
                    rotateAnimator.end();
                    fanstatus=false;
                }
            }
        });

        switchButton.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override

                public void onClick (View v) {
                boolean on= ((Switch) v).isChecked();
                if(on) {
                    gd.setColors (new int[]{ Color.YELLOW, Color.TRANSPARENT});
                    imageView.setBackground (gd);
                }
                else {
                    imageView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                //rotate the fan based on progress parameter
                if(fanstatus=true)
                {
                    rotateAnimator.setDuration (SPEED[progress]);
                    rotateAnimator.start();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch (SeekBar seekbar)
            {
            }
        });

    }


}
