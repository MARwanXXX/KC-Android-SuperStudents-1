package com.example.rock_paper_scissors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class MainActivity3 extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button play = findViewById(R.id.play);
        ToggleButton dark = findViewById(R.id.dark);
        LinearLayout layout = findViewById(R.id.layout);

        mediaPlayer = MediaPlayer.create(MainActivity3.this, R.raw.heh);
        mediaPlayer.start();

        dark.setText(null);
        dark.setTextOn(null);
        dark.setTextOff(null);

        Bundle bundle = getIntent().getExtras();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);

            }
        });

        dark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    layout.setBackgroundResource(R.color.dark);

                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    layout.setBackgroundResource(R.color.warm);
                }
            }
        });

    }

    @Override
    protected void onResume(){

        super.onResume();
        mediaPlayer.start();
    }

    @Override
    protected void onPause(){

        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy(){

        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}