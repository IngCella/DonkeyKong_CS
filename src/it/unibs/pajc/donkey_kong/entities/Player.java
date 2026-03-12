package it.unibs.pajc.donkey_kong.entities;

public class Player extends Default {

	private String username;
    private int start_y;
    private boolean jumping = false;
    private boolean movingDx = true;
    private boolean movingSx = false;
    private boolean climbing = false;
    private boolean falling = false;

    public Player(int x, int y, int width, int height, int speedX, int speedY, String username) {
        super(x, y, width, height, speedX, speedY);
        this.username = username;
    }

    public boolean isJumping() {
        return jumping;
    }

    public boolean isMovingDx() {
        return movingDx;
    }

    public boolean isMovingSx() {
        return movingSx;
    }

    public boolean isClimbing() {
        return climbing;
    }

    public void setClimbing(boolean climbing) {
        this.climbing = climbing;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void jump() {
        this.jumping = true;
        this.start_y = getY();
    }

    public void moveDx() {
        this.movingDx = true;
        this.movingSx = false;
        this.climbing = false;
        this.falling = false;
        setX(getX() + getSpeedX());
    }

    public void moveSx() {
        this.movingSx = true;
        this.movingDx = false;
        this.climbing = false;
        this.falling = false;
        setX(getX() - getSpeedX());
    }

    public void climb() {
        this.climbing = true;
        this.movingDx = false;
        this.movingSx = false;
        this.falling = false;
    }

    public void fall() {
        this.falling = true;
        this.movingDx = false;
        this.movingSx = false;
        this.climbing = false;
    }

    public void update() {
        if (jumping) {
            setY(getY() - getSpeedY());
            // Se la y ha raggiunto l'altezza massima del salto
            if ((start_y - getY()) >= 42) {
                jumping = false;
            }
        }
    }
}
