package it.unibs.pajc.donkey_kong;

import it.unibs.pajc.donkey_kong.entities.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Model extends Observable {

    public final int GameWidth = 750;  // Larghezza Finestra
    public final int GameHeight = 600; // Altezza Finestra

    private Random random;

    private Player player;
    private Kong kong;
    private Peach peach;
    private Heart heart;
    private Lifes lifes;
    private Floor floor;
    private Floor[] bars;
    private Ladder[] ladders;
    private Barrel barrelStack;
    private ArrayList<Barrel> barrels;

    private Collisions collisions;

    private int qta = 17;

    private String[] quit = {"Quit"};
    private ImageIcon gameover = new ImageIcon(getClass().getResource("../assets/gameOver.png"));
    private ImageIcon victory = new ImageIcon(getClass().getResource("../assets/victory.png"));

    public Model() {
        player = new Player(5, GameHeight - 65, 32, 32, 4, 2);
        kong = new Kong(GameWidth / 2 - 50, -1, 78, 78, 0, 0);
        peach = new Peach(kong.getX() + 110, 25, 32, 50, 0, 0);

        heart = new Heart(10, 10, 26, 26, 0, 0);
        lifes = new Lifes(40, 13, 18, 20, 0, 0);

        floor = new Floor(0, GameHeight - 25, GameWidth, 500, 0, 0);

        bars = new Floor[5];
        int y = 75;
        int s = 75;
        for (int i = 0; i < bars.length; i++) {
            if ((i % 2) == 0) {
                bars[i] = new Floor(0, y, GameWidth - s, 25, 0, 0);
            } else {
                bars[i] = new Floor(s, y, GameWidth - s, 25, 0, 0);
            }
            y += 100;
        }

        ladders = new Ladder[5];
        for (int i = 0; i < ladders.length; i++) {
            if ((i % 2) == 0) {
                ladders[i] = new Ladder(GameWidth - 75 - 40 - 30, bars[i].getY() - 5, 40, 105, 0, 0);
            } else {
                ladders[i] = new Ladder(75 + 30, bars[i].getY() - 5, 40, 105, 0, 0);
            }
        }

        barrelStack = new Barrel(kong.getX() - 80, 15, 60, 60, 0, 0);

        int x = 0;
        barrels = new ArrayList<Barrel>(); // = new ArrayList<>();
        for (int i = 0; i < qta; i++) {
            barrels.add(new Barrel(kong.getX() - x, bars[0].getY() - 25 - 5, 20, 20, 3, 2));
            x += 250;
        }

        collisions = new Collisions(this);
    }

    public Player getPlayer() {
        return player;
    }

    public Kong getKong() {
        return kong;
    }

    public Peach getPeach() {
        return peach;
    }

    public Heart getHeart() {
        return heart;
    }

    public Lifes getLifes() {
        return lifes;
    }

    public Floor getFloor() {
        return floor;
    }

    public Floor[] getBars() {
        return bars;
    }

    public Ladder[] getLadders() {
        return ladders;
    }

    public Barrel getBarrelStack() {
        return barrelStack;
    }

    public ArrayList<Barrel> getBarrels() {
        return barrels;
    }

    public void jump() {
        if (collisions.isTouching(player)) {
            player.jump();
        }
    }

    public void moveDx() {
        player.moveDx();
    }

    public void moveSx() {
        player.moveSx();
    }

    public void climb() {
        player.climb();
    }

    public void fall() {
        player.fall();
    }

    public void update() {
        player.update();

        for (Barrel barrel : barrels) {
            barrel.update();
        }

        // Quando un barile muore viene eliminato
        for (int i = 0; i < barrels.size(); i++) {
            if (!(barrels.get(i).isAlive())) {
                barrels.remove(i);
            }
        }

        // Se l'array di barili si svuota viene lanciato un altro barile che viene aggiunto all'array
        if (barrels.size() < qta) {
            barrels.add(new Barrel(kong.getX() - 100, 50, 20, 20, 3, 2));
        }

        collisions.update();
        
        // Se il player muore il gioco finisce
        if (!player.isAlive()) {
            // Game Over
            setChanged();
            notifyObservers();
            JOptionPane.showOptionDialog(null, "", "Game Over :(", 0, 0, gameover, quit, 0);
            System.exit(0);
        }
        
        // Se il player raggiunge peach vince
        if (player.collides(peach)) {
            // Win
            JOptionPane.showOptionDialog(null, "", "You Win!", 0, 0, victory, quit, 0);
            System.exit(0);
        }

        // Avvisa l'observer
        setChanged();
        notifyObservers();
    }
}
