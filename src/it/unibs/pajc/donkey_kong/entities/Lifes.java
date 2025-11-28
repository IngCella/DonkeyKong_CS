package it.unibs.pajc.donkey_kong.entities;

public class Lifes extends Default {

    private int lifes = 3;

    public Lifes(int x, int y, int width, int height, int speedX, int speedY) {
        super(x, y, width, height, speedX, speedY);
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }
}
