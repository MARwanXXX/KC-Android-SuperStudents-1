package com.example.rock_paper_scissors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    ImageView c_image, p_image;
    Button b_rock, b_paper, b_scissors;
    TextView score, com, you;
    MediaPlayer mediaPlayer;
    ToggleButton dark;
    LinearLayout layout;

    int HumanScore,ComputerScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();

        layout = findViewById(R.id.layout);
        dark = findViewById(R.id.dark);
        c_image = findViewById(R.id.c_image);
        p_image = findViewById(R.id.p_image);
        b_rock = findViewById(R.id.b_rock);
        b_paper = findViewById(R.id.b_paper);
        b_scissors = findViewById(R.id.b_scissors);
        score = findViewById(R.id.score);
        com = findViewById(R.id.com);
        you = findViewById(R.id.you);

        dark.setText(null);
        dark.setTextOn(null);
        dark.setTextOff(null);

        mediaPlayer = MediaPlayer.create(MainActivity2.this, R.raw.mysong);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();



        b_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                p_image.setImageResource(R.drawable.rockk3);
                String message = play_turn("rock");
                Toast.makeText(MainActivity2.this, message, Toast.LENGTH_SHORT).show();
                score.setText("You:" + Integer.toString(HumanScore) + " / Computer:" + Integer.toString(ComputerScore));


            }
        });

        b_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                p_image.setImageResource(R.drawable.paper3);
                String message = play_turn("paper");
                Toast.makeText(MainActivity2.this, message, Toast.LENGTH_SHORT).show();
                score.setText("You:" + Integer.toString(HumanScore) + " / Computer:" + Integer.toString(ComputerScore));

            }
        });

        b_scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                p_image.setImageResource(R.drawable.scissors3);
                String message = play_turn("scissors");
                Toast.makeText(MainActivity2.this, message, Toast.LENGTH_SHORT).show();
                score.setText("You: " + Integer.toString(HumanScore) + " / Computer: " + Integer.toString(ComputerScore));

            }
        });

        dark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
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

    public String play_turn(String player_choice) {

        String computer_choice = "";
        Random r = new Random();

        //choose12or3
        int computer_choice_number = r.nextInt(3) + 1;

        if (computer_choice_number == 1) {
            computer_choice = "rock";
        } else if (computer_choice_number == 2) {
            computer_choice = "paper";
        } else if (computer_choice_number == 3) {
            computer_choice = "scissors";
        }

        // set the computer image based on his choice
        if (computer_choice == "rock") {
            c_image.setImageResource(R.drawable.rockk2);
        } else if (computer_choice == "paper") {
            c_image.setImageResource(R.drawable.paper2);
        } else if (computer_choice == "scissors") {
            c_image.setImageResource(R.drawable.scissors2);
        }


        if (computer_choice == player_choice) {
            return "Draw.Nobody won.";
        } else if (player_choice == "rock" && computer_choice == "scissors") {
            HumanScore++;
            return "Rock crushes scissors.You win!";
        } else if (player_choice == "rock" && computer_choice == "paper") {
            ComputerScore++;
            return "Paper covers rock.Computer wins!";
        } else if (player_choice == "scissors" && computer_choice == "rock") {
            ComputerScore++;
            return "Rock crushes scissors.Computer wins!";
        } else if (player_choice == "scissors" && computer_choice == "paper") {
            HumanScore++;
            return "Scissors cuts paper.You win!";
        } else if (player_choice == "paper" && computer_choice == "rock") {
            HumanScore++;
            return "Paper covers rock.You win!";
        } else if (player_choice == "paper" && computer_choice == "scissors") {
            ComputerScore++;
            return "Scissors cuts paper.Computer wins!";
        }else return "not sure";

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