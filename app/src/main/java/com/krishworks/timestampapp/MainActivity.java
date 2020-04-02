package com.krishworks.timestampapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        showTimestamp();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimestamp();
            }
        });
    }

    public void showTimestamp() {
        String lastInternetConnection = "31032020110605";
        long millisDiff;
        long elapsedSecs;
        long dayDiff;
        long hourDiff;
        long minDiff;
        long secDiff;
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        try {
            Date last = sdf.parse(lastInternetConnection);
            Date current = sdf.parse(getCurrentTimestamp());
            millisDiff = current.getTime() - last.getTime();

            int secondsInMillis = 1000;
            elapsedSecs = millisDiff / secondsInMillis;

            int SECS_IN_A_DAY = 86400;
            dayDiff = elapsedSecs / SECS_IN_A_DAY;

            int SECS_IN_A_HOUR = 3600;
            hourDiff = (elapsedSecs % SECS_IN_A_DAY) / SECS_IN_A_HOUR;

            int SECS_IN_A_MIN = 60;
            minDiff = ((elapsedSecs % SECS_IN_A_DAY) % SECS_IN_A_HOUR) / SECS_IN_A_MIN;

            secDiff = ((elapsedSecs % SECS_IN_A_DAY) % SECS_IN_A_HOUR) % SECS_IN_A_MIN;

            textView.setText(lastInternetConnection + "\n" + last + "\n\n" +
                    getCurrentTimestamp() + "\n" + current + "\n\n" +
                    dayDiff + "D " + hourDiff + "h " + minDiff + "m " + secDiff + "s");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date resultDate = new Date(System.currentTimeMillis());
        String timestamp = sdf.format(resultDate);
        return timestamp;
    }
}
