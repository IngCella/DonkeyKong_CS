package it.unibs.pajc.donkey_kong;

import it.unibs.pajc.donkey_kong.entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Model extends Observable implements Serializable {

    public final int GameWidth = 750;  // Larghezza Finestra
    public final int GameHeight = 600; // Altezza Window

    private Random random;
    
    private boolean startGame = false;
    private boolean timerFinished = false;

    private Player player1;
    private Player player2;
    
    private Kong kong;
    private Peach peach;
    private Heart heart;
    
    private ShowNumber lifes1;
    private ShowNumber lifes2;
    
    private ShowNumber timerNumber;
    private Floor floor;
    private Floor[] bars;
    private Ladder[] ladders;
    private Barrel barrelStack;
    private ArrayList<Barrel> barrels;

    // TODO: rivedere
    private Collisions collisions1;
    private Collisions collisions2;

    private int qta = 17;

    private String[] quit = {"Quit"};
    private ImageIcon gameover = new ImageIcon(getClass().getResource("../assets/gameOver.png"));
    private ImageIcon victory = new ImageIcon(getClass().getResource("../assets/victory.png"));

    public Model() {
        player1 = new Player(5, GameHeight - 65, 32, 32, 4, 2, "");
        kong = new Kong(GameWidth / 2 - 50, -1, 78, 78, 0, 0);
        peach = new Peach(kong.getX() + 110, 25, 32, 50, 0, 0);
        
        heart = new Heart(10, 30, 26, 26, 0, 0);
        lifes1 = new ShowNumber(40, 33, 18, 20, 0, 0, 3);
        timerNumber = new ShowNumber((GameWidth/2)-50, (GameHeight/2)-50, 100, 100, 0, 0, 3);

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

        collisions1 = new Collisions(this);
    }

    public boolean isStartGame() {
		return startGame;
	}

	public void setStartGame(boolean startGame) {
		this.startGame = startGame;
	}
	
	public boolean isTimerFinished() {
		return timerFinished;
	}

	public void setTimerFinished(boolean timerFinished) {
		this.timerFinished = timerFinished;
	}
    
    public Player getPlayer1() {
        return player1;
    }
    
    public Player getPlayer2() {
        return player2;
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

    public ShowNumber getLifes1() {
        return lifes1;
    }
    
    public ShowNumber getLifes2() {
        return lifes2;
    }

    public ShowNumber getTimerNumber() {
		return timerNumber;
	}

	public void setTimerNumber(ShowNumber timerNumber) {
		this.timerNumber = timerNumber;
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
    
    public Collisions getCollisions2() {
		return collisions2;
	}

	public void jump() {
        if (collisions1.isTouching(player1)) {
            player1.jump();
        }
    }

    public void moveDx() {
        player1.moveDx();
    }

    public void moveSx() {
        player1.moveSx();
    }

    public void climb() {
        player1.climb();
    }

    public void fall() {
        player1.fall();
    }

    public void update() {
    	if(startGame) {
    		if(timerFinished) {
    			player1.update();

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

                collisions1.update();
                //collisions2.update();
                
                // Se il player1 muore il gioco finisce
                if (!player1.isAlive()) {
                    // Game Over
                    setChanged();
                    notifyObservers();
                    JOptionPane.showOptionDialog(null, "", "Game Over :(", 0, 0, gameover, quit, 0);
                    System.exit(0);
                }
                
                // Se il player1 raggiunge peach vince
                if (player1.collides(peach)) {
                    // Win
                    JOptionPane.showOptionDialog(null, "", "You Win!", 0, 0, victory, quit, 0);
                    System.exit(0);
                }
                
    		} else {
    			// Timer
    			if(timerNumber.getNumber() > 0) {
        			timerNumber.setNumber(timerNumber.getNumber() - 1);
    			} else {
    				timerFinished = true;
    			}
    		}
    		
    	}

        // Avvisa l'observer
        setChanged();
        notifyObservers();
    }
    
    public void cgd() {
    	setChanged();
        notifyObservers();
    }
    
    public void sync(Model model2) {
    	/** /
    	// startGame = true;
    	// model2.setStartGame(true);
    	
    	player2 = model2.getPlayer2();
    	lifes2 = model2.getLifes2();
    	collisions2 = model2.getCollisions2();
    	/**/
    	
	    // Sincronizza lo stato dei timer
	    this.timerNumber.setNumber(model2.getTimerNumber().getNumber());
	    this.timerFinished = model2.isTimerFinished();
	    
	    // Sincronizza gli altri giocatori/entità
	    this.player2 = model2.getPlayer2();
	    this.lifes2 = model2.getLifes2();
	    this.collisions2 = model2.getCollisions2();
	    
	    // Se necessario, sincronizza anche player1 se il server deve 
	    // avere l'ultima parola sulla posizione
    }
}
