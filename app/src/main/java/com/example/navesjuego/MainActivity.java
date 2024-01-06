package com.example.navesjuego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private EditText nameInput;
    private String selectedLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = mediaPlayer.create(this, R.raw.background_sonido);
        mediaPlayer.start();

        nameInput = findViewById(R.id.nameInput);

        Button easyButton = findViewById(R.id.easyButton);
        Button mediumButton = findViewById(R.id.mediumButton);
        Button hardButton = findViewById(R.id.hardButton);
        Button startButton = findViewById(R.id.startButton);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevel = "Fácil";
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevel = "Intermedio";
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevel = "Difícil";
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    private void startGame() {
        String playerName = nameInput.getText().toString();

        // Puedes pasar el nombre del jugador y el nivel seleccionado a la actividad del juego
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("PLAYER_NAME", playerName);
        intent.putExtra("SELECTED_LEVEL", selectedLevel);
        startActivity(intent);
    }
}