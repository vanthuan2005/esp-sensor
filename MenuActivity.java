package com.example.chessgame;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setBackgroundColor(Color.parseColor("#1F2025"));
        root.setPadding(28, 58, 28, 34);

        TextView crown = new TextView(this);
        crown.setText("♛");
        crown.setTextSize(64);
        crown.setTextColor(Color.parseColor("#D6A35B"));
        crown.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("CHESS");
        title.setTextColor(Color.WHITE);
        title.setTextSize(34);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        TextView sub = new TextView(this);
        sub.setText("CHOOSE GAME MODE");
        sub.setTextColor(Color.parseColor("#9D9FA6"));
        sub.setTextSize(14);
        sub.setGravity(Gravity.CENTER);
        sub.setPadding(0, 5, 0, 34);

        Button human = createButton("♟  PLAY WITH FRIEND");
        Button bot = createButton("♞  PLAY WITH BOT");
        Button sound = createButton("♪  SOUND");

        root.addView(crown);
        root.addView(title);
        root.addView(sub);
        root.addView(human, buttonParams());
        root.addView(bot, buttonParams());
        root.addView(sound, buttonParams());

        human.setOnClickListener(v ->
                startActivity(new Intent(MenuActivity.this, MainActivity.class)));

        bot.setOnClickListener(v ->
                Toast.makeText(this, "Play with bot: Coming soon", Toast.LENGTH_SHORT).show());

        sound.setOnClickListener(v -> showSoundDialog());

        setContentView(root);
    }

    private Button createButton(String text) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextColor(Color.WHITE);
        b.setTextSize(16);
        b.setAllCaps(false);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.parseColor("#2A2C32"));
        bg.setCornerRadius(22);
        bg.setStroke(1, Color.parseColor("#404249"));
        b.setBackground(bg);
        return b;
    }

    private LinearLayout.LayoutParams buttonParams() {
        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, 130);
        p.bottomMargin = 20;
        return p;
    }

    private void showSoundDialog() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(40, 20, 40, 10);

        SeekBar bar = new SeekBar(this);
        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int now = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        bar.setMax(max);
        bar.setProgress(now);

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        box.addView(bar);

        new AlertDialog.Builder(this)
                .setTitle("Sound")
                .setView(box)
                .setPositiveButton("OK", null)
                .show();
    }
}
