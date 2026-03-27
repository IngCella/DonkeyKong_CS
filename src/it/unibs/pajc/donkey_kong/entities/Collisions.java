package it.unibs.pajc.donkey_kong.entities;

import it.unibs.pajc.donkey_kong.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Collisions implements Serializable {

    private Model model;

    private Player player1;
    private Player player2;
    private Kong kong;
    private Floor floor;
    private Floor[] bars;
    private Ladder[] ladders;
    private ArrayList<Barrel> barrels;

    public Collisions(Model model) {
        this.model = model;
    }
    
    // Metodo per verificare che un oggetto tocchi almeno un pavimento
    public boolean isTouching(Default obj) {
        // Se tocca ritorna true
        return obj.collides(floor)
                || obj.collides(bars[0])
                || obj.collides(bars[1])
                || obj.collides(bars[2])
                || obj.collides(bars[3])
                || obj.collides(bars[4]); // Altrimenti false
    }

    public void update() {
    	player1 = model.getPlayer1();
		player2 = model.getPlayer2();
        kong = model.getKong();
        floor = model.getFloor();
        bars = model.getBars();
        ladders = model.getLadders();
        barrels = model.getBarrels();
    	
        // COLLISIONI DEL PLAYER1 E PLAYER2
    	if (player1.collideLeft()) {
            player1.setX(0);
        }
        // Se collide con il bordo di destra la posizione x diventa la larghezza della finestra - la larghezza del player
        if (player1.collideRight()) {
            player1.setX(model.GameWidth - player1.getWidth());
        }
        // Se non tocca nessun pavimento e non sta saltando, arrampicandosi applica la gravità
        if (!isTouching(player1) && !player1.isJumping() && !player1.isClimbing()) {
            player1.setY(player1.getY() + player1.getSpeedY());
        }
        // Se collide con una scala sale o scende
        for (Ladder ladder : ladders) {
            if (player1.collides(ladder) && player1.isClimbing()) {
                if (player1.getY() >= ladder.getY() - player1.getHeight()) {
                    player1.setY(player1.getY() - player1.getSpeedY());
                }
            } else if (player1.collides(ladder) && player1.isFalling()) {
                if (player1.getY() <= ladder.getY() + player1.getHeight()) {
                    player1.setY(player1.getY() + player1.getSpeedY());
                }
            }
        }
        
        for (int i = 0; i < barrels.size(); i++) {
        	// Collisione con il player
            if (player1.collides(barrels.get(i))) {
                // Se collide perde una vita e il barile si distrugge
                player1.setLifes(player1.getLifes() - 1);
                barrels.get(i).setAlive(false);

                // Se le vite sono finite muore
                if (player1.getLifes() == 0) {
                    player1.setAlive(false);
                }
            }
        }
    	
        // Se collide con il bordo di sinistra la sua posizione x diventa 0
        if (player2.collideLeft()) {
            player2.setX(0);
        }
        // Se collide con il bordo di destra la posizione x diventa la larghezza della finestra - la larghezza del player
        if (player2.collideRight()) {
            player2.setX(model.GameWidth - player2.getWidth());
        }
        // Se non tocca nessun pavimento e non sta saltando, arrampicandosi applica la gravità
        if (!isTouching(player2) && !player2.isJumping() && !player2.isClimbing()) {
            player2.setY(player2.getY() + player2.getSpeedY());
        }
        // Se collide con una scala sale o scende
        for (Ladder ladder : ladders) {
            if (player2.collides(ladder) && player2.isClimbing()) {
                if (player2.getY() >= ladder.getY() - player2.getHeight()) {
                    player2.setY(player2.getY() - player2.getSpeedY());
                }
            } else if (player2.collides(ladder) && player2.isFalling()) {
                if (player2.getY() <= ladder.getY() + player2.getHeight()) {
                    player2.setY(player2.getY() + player2.getSpeedY());
                }
            }
        }
        
        for (int i = 0; i < barrels.size(); i++) {
        	// Collisione con il player
            if (player2.collides(barrels.get(i))) {
                // Se collide perde una vita e il barile si distrugge
                player2.setLifes(player2.getLifes() - 1);
                barrels.get(i).setAlive(false);

                // Se le vite sono finite muore
                if (player2.getLifes() == 0) {
                    player2.setAlive(false);
                }
            }
        }
        
        // COLLISIONI DEI BARILI
        for (int i = 0; i < barrels.size(); i++) {
            // Se il barile ha superato kong e sta sopra alla prima barra rossa allora è visible
            if (!((barrels.get(i).getX() < kong.getX() + kong.getWidth() + 5) && barrels.get(i).getY() <= 75)) {
                barrels.get(i).setVisible();
            }

            // Effetti di Kong con i Barrel
            int kw = kong.getWidth()/4;
            if(barrels.get(i).collides(kong)){
                if(barrels.get(i).getX() < kong.getX() + kw){
                    kong.get();
                }
                else if(barrels.get(i).getX() < kong.getX() + (kw*3)){
                    kong.full();
                }
                else if(barrels.get(i).getX() < kong.getX() + (kw*4)+5){
                    kong.throwed();
                }
            }

            // Collisione con i bordi
            if (barrels.get(i).collideRight()) {
                barrels.get(i).setX(model.GameWidth - barrels.get(i).getWidth());
                barrels.get(i).setSpeedX(barrels.get(i).getSpeedX() * -1);
            }
            // Se collide con l'ultimo bordo sinistro...
            if (barrels.get(i).collideLeft() && barrels.get(i).getY() >= model.GameHeight - 100) {
                // ...ed è uscito di un po' muore
                if (barrels.get(i).getX() < (0 - barrels.get(i).getWidth())) {
                    barrels.get(i).setAlive(false);
                }
            } // Altrimenti rimbalaza
            else if (barrels.get(i).collideLeft() && barrels.get(i).isVisible()) {
                barrels.get(i).setSpeedX(barrels.get(i).getSpeedX() * -1);
            }

            // Se tocca i pavimenti non cade
            if (!isTouching(barrels.get(i)) && barrels.get(i).isVisible()) {
                barrels.get(i).setY(barrels.get(i).getY() + barrels.get(i).getSpeedY());
            }
        }
    }
}
