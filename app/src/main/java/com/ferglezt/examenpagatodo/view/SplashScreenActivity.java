package com.ferglezt.examenpagatodo.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.ferglezt.examenpagatodo.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    public static final int SPLASH_SCREEN_DELAY = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        try {
            getSupportActionBar().hide();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_splash_screen);

        //Start the splash delay
        Timer timer = new Timer();
        timer.schedule(splashScreenTimerTask(LoginActivity.class), SPLASH_SCREEN_DELAY);

    }

    //Returns a TimerTask that will pass to the next activity
    private TimerTask splashScreenTimerTask(final Class nextActivity) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent intent = new Intent().setClass(
                        SplashScreenActivity.this, nextActivity);
                startActivity(intent);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        return task;
    }
}
