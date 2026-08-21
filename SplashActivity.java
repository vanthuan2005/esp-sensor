package com.example.chessgame;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setBackgroundColor(Color.parseColor("#1F2025"));

        TextView crown = new TextView(this);
        crown.setText("♛");
        crown.setTextColor(Color.parseColor("#D6A35B"));
        crown.setTextSize(82);
        crown.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("CHESS");
        title.setTextColor(Color.WHITE);
        title.setTextSize(38);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        TextView sub = new TextView(this);
        sub.setText("Classic Chess Game");
        sub.setTextColor(Color.parseColor("#A9ABB2"));
        sub.setTextSize(15);
        sub.setGravity(Gravity.CENTER);
        sub.setPadding(0, 8, 0, 34);

        ProgressBar progress = new ProgressBar(this);

        root.addView(crown);
        root.addView(title);
        root.addView(sub);
        root.addView(progress);
        setContentView(root);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MenuActivity.class));
            finish();
        }, 2000);
    }
}
