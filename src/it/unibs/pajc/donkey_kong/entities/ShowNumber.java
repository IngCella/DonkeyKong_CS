package it.unibs.pajc.donkey_kong.entities;

public class ShowNumber extends Default {

    private int number;

    public ShowNumber(int x, int y, int width, int height, int speedX, int speedY, int number) {
        super(x, y, width, height, speedX, speedY);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
