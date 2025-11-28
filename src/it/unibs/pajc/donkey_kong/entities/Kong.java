package it.unibs.pajc.donkey_kong.entities;

public class Kong extends Default {

    private boolean getting = false;
    private boolean full = false;
    private boolean throwing = false;

    public Kong(int x, int y, int width, int height, int speedX, int speedY) {
        super(x, y, width, height, speedX, speedY);
    }

    public boolean isGetting() {
        return getting;
    }

    public boolean isFull() {
        return full;
    }

    public boolean isThrowing() {
        return throwing;
    }

    public void get() {
        this.getting = true;
        this.full = false;
        this.throwing = false;
    }
    
    public void full() {
        this.full = true;
        this.getting = false;
        this.throwing = false;
    }

    public void throwed() {
        this.throwing = true;
        this.getting = false;
        this.full = false;
    }
}
