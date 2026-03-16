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

    private boolean gameOver1 = false;
    private boolean gameWon1 = false;
    
    private boolean gameOver2 = false;
    private boolean gameWon2 = false;
    
    private Player player1;
    private Player player2;
    
    private Kong kong;
    private Peach peach;
    private Heart heart1;
    private Heart heart2;
    
    private ShowNumber lifes1;
    private ShowNumber lifes2;
    
    private ShowNumber timerNumber;
    private Floor floor;
    private Floor[] bars;
    private Ladder[] ladders;
    private Barrel barrelStack;
    private ArrayList<Barrel> barrels;

    private Collisions collisions;

    private int qta = 17;

    public Model() {
        player1 = new Player(5, GameHeight - 65, 32, 32, 4, 2, "");
        player2 = new Player(5, GameHeight - 65, 32, 32, 4, 2, "");
        
        kong = new Kong(GameWidth / 2 - 50, -1, 78, 78, 0, 0);
        peach = new Peach(kong.getX() + 110, 25, 32, 50, 0, 0);
        
        heart1 = new Heart(10, 30, 26, 26, 0, 0);
        heart2 = new Heart(GameWidth-60, 30, 26, 26, 0, 0);
        
        lifes1 = new ShowNumber(40, 33, 18, 20, 0, 0, 3);
        lifes2 = new ShowNumber(GameWidth-30, 33, 18, 20, 0, 0, 3);
        
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

        collisions = new Collisions(this);
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

    public boolean isGameOver1() {
		return gameOver1;
	}

	public boolean isGameWon1() {
		return gameWon1;
	}

	public boolean isGameOver2() {
		return gameOver2;
	}

	public boolean isGameWon2() {
		return gameWon2;
	}

	public Player getPlayer1() {
        return player1;
    }
    
    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Kong getKong() {
        return kong;
    }

    public Peach getPeach() {
        return peach;
    }

    public Heart getHeart1() {
        return heart1;
    }
    
    public Heart getHeart2() {
    	return heart2;
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
    
	public Collisions getCollisions() {
		return collisions;
	}

	public void jump() {
        if (collisions.isTouching(player1)) {
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
    			player2.update();

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
            
            /*
            // Se il player1 muore il gioco finisce
            if (!player1.isAlive()) {
                // Game Over
                setChanged();
                notifyObservers();
                JOptionPane.showOptionDialog(null, "", "Game Over :(", 0, 0, gameover, quit, 0);
                System.exit(0);
            }
            
            if (!player2.isAlive()) {
                // Game Over
                setChanged();
                notifyObservers();
                JOptionPane.showOptionDialog(null, "", "You Win!", 0, 0, victory, quit, 0);
                System.exit(0);
            }
            
            // Se il player1 raggiunge peach vince
            if (player1.collides(peach)) {
                // Win
                JOptionPane.showOptionDialog(null, "", "You Win!", 0, 0, victory, quit, 0);
                System.exit(0);
            }
            
            if (player2.collides(peach)) {
                // Win
            	JOptionPane.showOptionDialog(null, "", "Game Over :(", 0, 0, gameover, quit, 0);
                System.exit(0);
            }
            */
            
            if (!player1.isAlive() && !gameOver1) {
                gameOver1 = true;
                gameWon2 = true;
            }
            
            if (player1.collides(peach) && !gameWon1) {
                gameWon1 = true;
                gameOver2 = true;
            }
            
            if (!player2.isAlive() && !gameOver1) {
                gameWon1 = true;
                gameOver2 = true;
            }
            
            if (player2.collides(peach) && !gameWon1) {
                gameOver1 = true;
                gameWon2 = true;
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
    
    public void sync(Model model2) {
    	
	    // Sincronizza lo stato dei timer
	    this.timerNumber.setNumber(model2.getTimerNumber().getNumber());
	    this.timerFinished = model2.isTimerFinished();

	    this.gameOver1 = model2.isGameOver2();
	    this.gameWon1 = model2.isGameWon2();
	    
	    // Sincronizza gli altri giocatori/entità
	    this.player2 = model2.getPlayer1();
	    
	    this.lifes1 = model2.getLifes1();
	    this.lifes2 = model2.getLifes2();
	    
	    this.heart1 = model2.getHeart1();
	    this.heart2 = model2.getHeart2();
	    
	    this.kong = model2.getKong();
	    this.barrels = model2.getBarrels();
	    
	    // SIAMO ARRIVATI QUA
	    this.player1.update();
	    this.player2.update();
	    this.collisions.update();
	    
	    setChanged();
        notifyObservers();
    }
}