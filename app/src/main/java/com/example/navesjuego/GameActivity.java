package com.example.navesjuego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private String playerName;
    private String selectedLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        playerName = intent.getStringExtra("PLAYER_NAME");
        selectedLevel = intent.getStringExtra("SELECTED_LEVEL");

        gameView = new GameView(this, playerName, selectedLevel);



        /*
        @Override
        protected void onDestroy() {
            super.onDestroy();
            // Detén el juego cuando la actividad es destruida
            gameView.stop();
        }

         */
    }
}