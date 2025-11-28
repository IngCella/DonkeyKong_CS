package it.unibs.pajc.donkey_kong.entities;

import it.unibs.pajc.donkey_kong.Model;
import java.util.ArrayList;

public class Collisions {

    private Model model;

    private Player player;
    private Kong kong;
    private Lifes lifes;
    private Floor floor;
    private Floor[] bars;
    private Ladder[] ladders;
    private ArrayList<Barrel> barrels;

    public Collisions(Model model) {
        this.model = model;

        player = model.getPlayer();
        kong = model.getKong();
        lifes = model.getLifes();
        floor = model.getFloor();
        bars = model.getBars();
        ladders = model.getLadders();
        barrels = model.getBarrels();
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
        // COLLISIONI DEL PLAYER
        // Se collide con il bordo di sinistra la sua posizione x diventa 0
        if (player.collideLeft()) {
            player.setX(0);
        }
        // Se collide con il bordo di destra la posizione x diventa la larghezza della finestra - la larghezza del player
        if (player.collideRight()) {
            player.setX(model.GameWidth - player.getWidth());
        }
        // Se non tocca nessun pavimento e non sta saltando, arrampicandosi applica la gravità
        if (!isTouching(player) && !player.isJumping() && !player.isClimbing()) {
            player.setY(player.getY() + player.getSpeedY());
        }
        // Se collide con una scala sale o scende
        for (Ladder ladder : ladders) {
            if (player.collides(ladder) && player.isClimbing()) {
                if (player.getY() >= ladder.getY() - player.getHeight()) {
                    player.setY(player.getY() - player.getSpeedY());
                }
            } else if (player.collides(ladder) && player.isFalling()) {
                if (player.getY() <= ladder.getY() + player.getHeight()) {
                    player.setY(player.getY() + player.getSpeedY());
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
            
            // Collisione con il player
            if (player.collides(barrels.get(i))) {
                // Se collide perde una vita e il barile si distrugge
                lifes.setLifes(lifes.getLifes() - 0);
                barrels.get(i).setAlive(false);

                // Se le vite sono finite muore
                if (lifes.getLifes() == 0) {
                    player.setAlive(false);
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
