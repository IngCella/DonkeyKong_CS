package it.unibs.pajc.donkey_kong.entities;

public class Barrel extends Default {

    private boolean visible = false;

    public Barrel(int x, int y, int width, int height, int speedX, int speedY) {
        super(x, y, width, height, speedX, speedY);
    }

    public void setVisible() {
        visible = true;
    }

    public boolean isVisible() {
        return visible;
    }

    public void update() {
        setX(getX() + getSpeedX());

        if (!isAlive()) {
            visible = false;
        }
    }
}
