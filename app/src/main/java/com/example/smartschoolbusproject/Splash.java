package com.example.smartschoolbusproject;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {

    private ImageView splashimg;
    private ProgressBar progressBar;
    private TextView textView;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(30);

        splashimg = (ImageView) findViewById(R.id.splashImg);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.myanimation);
        splashimg.startAnimation(myanim);

        progressAnimation();


}

        public void progressAnimation(){


            final Timer t = new Timer();
            TimerTask tt = new TimerTask(){

                @Override
                public void run()
                {
                    counter++;
                    progressBar.setProgress(counter);

                    if(counter == 30){
                        t.cancel();
                        Intent i = new Intent(Splash.this, LoginActivity .class);
                        startActivity(i);
                    }
                }
            };
            t.schedule(tt, 0, 100);

        }

}
