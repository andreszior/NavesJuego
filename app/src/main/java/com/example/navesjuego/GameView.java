package com.example.navesjuego;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements Runnable {

    private Thread gameThread;
    private boolean isPlaying;

    private List<Nave> naves;
    private Nave naveJugador;
    private String playerName;
    private String selectedLevel;

    public GameView(Context context, String playerName, String selectedLevel) {
        super(context);
        this.playerName = playerName;
        this.selectedLevel = selectedLevel;
        initGame();
    }

    private void initGame() {
        // Inicializa las variables y configuraciones del juego
        isPlaying = true;
        naves = new ArrayList<>();

        // Inicializa las naves con su velocidad inicial según el nivel seleccionado
        int initialSpeed = getInitialSpeed(selectedLevel);
        for (int i = 0; i < 5; i++) {
            Nave nave = new Nave(getContext(), getWidth(), getHeight(), initialSpeed);
            naves.add(nave);
        }
        naveJugador = new Nave(getContext(), getWidth(), getHeight(), initialSpeed);
    }

    private int getInitialSpeed(String level) {
        // Define velocidades iniciales según el nivel
        switch (level) {
            case "Fácil":
                return 5;
            case "Intermedio":
                return 10;
            case "Difícil":
                return 15;
            default:
                return 5;
        }
    }


    @Override
    public void run() {
        while (isPlaying) {
            update();
            postInvalidate(); // Invoca onDraw en el hilo principal
            control();
            handleCollisions(); // Agrega la verificación de colisiones en cada iteración del bucle
        }
    }

    private void control() {
        try {
            // Hace que el hilo duerma durante un breve período de tiempo (en milisegundos)
            Thread.sleep(17); // Este valor puede ajustarse según las necesidades del juego
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        long currentTime = System.currentTimeMillis();

        // Actualiza la lógica del juego, incluyendo la velocidad de las naves
        for (Nave nave : naves) {
            nave.update();
        }

        // Aumenta la velocidad de las naves con el tiempo
        for (Nave nave : naves) {
            nave.increaseSpeedOverTime(currentTime);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Dibuja elementos en la pantalla
        // Dibuja las naves en el canvas
        for (Nave nave : naves) {
            nave.draw(canvas);
        }
    }

    private void handleCollisions() {
        // Verificar colisiones entre la nave del jugador y las naves enemigas
        for (Nave nave : naves) {
            if (nave.intersects(naveJugador)) {
                // Colisión detectada, aquí puedes realizar acciones como decrementar vidas o finalizar el juego
                gameOver();
            }
        }
    }

    private void gameOver() {
        // Realizar acciones al finalizar el juego, por ejemplo, mostrar un mensaje, reiniciar el juego, etc.
        isPlaying = false;
        // Puedes agregar más lógica según sea necesario
    }

    public void start() {
        // Inicia el hilo del juego
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stop() {
        // Detiene el hilo del juego
        isPlaying = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
