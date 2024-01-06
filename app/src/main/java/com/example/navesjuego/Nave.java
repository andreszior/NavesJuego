package com.example.navesjuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Nave {

    private float x, y;  // Posición de la nave en la pantalla
    private int speed;    // Velocidad de la nave
    private Bitmap naveBitmap;  // Imagen de la nave


    private long lastUpdateTime = System.currentTimeMillis();

    public Nave(Context context, int screenWidth, int screenHeight, int initialSpeed) {
        this.x = screenWidth;
        this.y = (float) Math.random() * screenHeight; // Posición vertical aleatoria
        this.speed = initialSpeed;

        // Cargar la imagen de la nave desde el archivo en res/drawable
        naveBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.nave);
    }

    public void draw(Canvas canvas) {
        // Dibuja la nave en el canvas
        canvas.drawBitmap(naveBitmap, x, y, new Paint());
    }

    public boolean intersects(Nave other) {
        return x < other.x + naveBitmap.getWidth() &&
                x + naveBitmap.getWidth() > other.x &&
                y < other.y + naveBitmap.getHeight() &&
                y + naveBitmap.getHeight() > other.y;
    }


    public void update() {
        // Actualiza la posición de la nave en cada fotograma
        x -= speed;
        // Puedes implementar más lógica según sea necesario
    }

    public void increaseSpeedOverTime(long currentTime) {
        long elapsedTime = currentTime - lastUpdateTime;
        speed += elapsedTime; // Puedes ajustar esto según sea necesario
        lastUpdateTime = currentTime;
    }
}
