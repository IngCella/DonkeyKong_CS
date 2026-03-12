package it.unibs.pajc.donkey_kong.entities;

import java.io.Serializable;

public class Default implements Serializable {

    private final int GameWidth = 750;  // Larghezza Finestra
    private final int GameHeight = 600; // Altezza Finestra

    private int x;  // Posizione asse X
    private int y;  // Posizione asse Y
    private int width;  // Larghezza
    private int height;  // Altezza
    private int speedX;  // Velocità asse x
    private int speedY;  // Velocità asse y

    private boolean alive = true;   // In vita

    public Default(int x, int y, int width, int height, int speedX, int speedY) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean collides(Default obj) {
        return !(obj.x + obj.width < x || x + width < obj.x || obj.y + obj.height < y || y + height < obj.y);
    }

    public boolean collideLeft() {
        return (this.x <= 0);
    }

    public boolean collideRight() {
        return (this.x >= GameWidth - width);
    }

    public boolean collideTop() {
        return (this.y <= 0);
    }

    public boolean collideBottom() {
        return (this.y >= GameHeight - height);
    }
}
