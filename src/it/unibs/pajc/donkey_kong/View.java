package it.unibs.pajc.donkey_kong;

import it.unibs.pajc.donkey_kong.entities.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class View extends JFrame implements Observer {

    private final Model model;
    
    private String serverIpAddress;
    private int serverPort;
    
    private boolean popupShown = false;
    
    private Image icon;
    private Image heart;
    private Image brokenHeart;
    private Image zero;
    private Image one;
    private Image two;
    private Image three;
    private Image marioRight;
    private Image marioLeft;
    private Image marioClimb;
    private Image luigiRight;
    private Image luigiLeft;
    private Image luigiClimb;
    private Image kongGet;
    private Image kongFull;
    private Image kongThrow;
    private Image peach;
    private Image floor;
    private Image bar;
    private Image ladder;
    private Image barrelStack;
    private Image barrel;
    private ImageIcon gameover;
    private ImageIcon victory;
    private ImageIcon pause;
    
    private String[] resume = {"Resume"};
    private String[] quit = {"Quit"};

    public View(Model model) {
        initComponents();
        this.model = model;
        model.addObserver(this);

        try {
            // Icona dell'applicazione
            icon = ImageIO.read(getClass().getResource("../assets/marioVsDonkeyKong.jpg"));
            this.setIconImage(icon);

            // Cuore, Numero delle vite rimaste e pausa
            heart = ImageIO.read(getClass().getResource("../assets/heart.png"));
            brokenHeart = ImageIO.read(getClass().getResource("../assets/brokenHeart.png"));
            zero = ImageIO.read(getClass().getResource("../assets/0.png"));
            one = ImageIO.read(getClass().getResource("../assets/1.png"));
            two = ImageIO.read(getClass().getResource("../assets/2.png"));
            three = ImageIO.read(getClass().getResource("../assets/3.png"));

            // Player - Mario
            marioRight = ImageIO.read(getClass().getResource("../assets/marioRight.png"));
            marioLeft = ImageIO.read(getClass().getResource("../assets/marioLeft.png"));
            marioClimb = ImageIO.read(getClass().getResource("../assets/marioClimb.png"));
            
            // Player - Luigi
            luigiRight = ImageIO.read(getClass().getResource("../assets/luigiRight.png"));
            luigiLeft = ImageIO.read(getClass().getResource("../assets/luigiLeft.png"));
            luigiClimb = ImageIO.read(getClass().getResource("../assets/luigiClimbTest.png"));

            // Kong
            kongGet = ImageIO.read(getClass().getResource("../assets/donkeyGet.png"));
            kongFull = ImageIO.read(getClass().getResource("../assets/donkeyFull.png"));
            kongThrow = ImageIO.read(getClass().getResource("../assets/donkeyThrow.png"));

            // Peach
            peach = ImageIO.read(getClass().getResource("../assets/peach.png"));

            // Pavimento, travi e scale
            floor = ImageIO.read(getClass().getResource("../assets/floor.png"));
            bar = ImageIO.read(getClass().getResource("../assets/bar.png"));
            ladder = ImageIO.read(getClass().getResource("../assets/ladder.png"));

            // Barili
            barrelStack = ImageIO.read(getClass().getResource("../assets/barrelStack.png"));
            barrel = ImageIO.read(getClass().getResource("../assets/barrel.png"));
            
            // Vittoria/Sconfitta
            gameover = new ImageIcon(getClass().getResource("../assets/gameOver.png"));
            victory = new ImageIcon(getClass().getResource("../assets/victory.png"));
            pause = new ImageIcon(getClass().getResource("../assets/pause.png"));
            
        } catch (IOException e) {
            System.out.println("Immagine non trovata");
        }
    }

    public void paintStage(Graphics g) {
        // Draw dello sfondo
    	Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        Player p1 = model.getPlayer1();
        Player p2 = model.getPlayer2();
        
        if(model.isStartGame()) {
        		if(model.isTimerFinished()) {
                Heart h1 = model.getHeart1();
                Heart h2 = model.getHeart2();
                ShowNumber lf1 = model.getLifes1();
                ShowNumber lf2 = model.getLifes2();
                
                // Lifes e Heart dei players
                if (p1.getLifes() != 0) {
                    g2.drawImage(heart, h1.getX(), h1.getY(), h1.getWidth(), h1.getHeight(), null);
                }
                
                switch (p1.getLifes()) {
                    case 3:
                        g2.drawImage(three, lf1.getX(), lf1.getY(), lf1.getWidth(), lf1.getHeight(), null);
                        break;
                    case 2:
                        g2.drawImage(two, lf1.getX(), lf1.getY(), lf1.getWidth(), lf1.getHeight(), null);
                        break;
                    case 1:
                        g2.drawImage(one, lf1.getX(), lf1.getY(), lf1.getWidth(), lf1.getHeight(), null);
                        break;
                    default:
                        g2.drawImage(zero, lf1.getX(), lf1.getY(), lf1.getWidth(), lf1.getHeight(), null);
                        g2.drawImage(brokenHeart, h1.getX() - 4, h1.getY() - 3, h1.getWidth() + 9, h1.getHeight() + 9, null);
                        break;
                }
                
                if(p2.getLifes() != 0) {
                	g2.drawImage(heart, h2.getX(), h2.getY(), h2.getWidth(), h2.getHeight(), null);
                }
                
                switch (p2.getLifes()) {
	                case 3:
	                    g2.drawImage(three, lf2.getX(), lf2.getY(), lf2.getWidth(), lf2.getHeight(), null);
	                    break;
	                case 2:
	                    g2.drawImage(two, lf2.getX(), lf2.getY(), lf2.getWidth(), lf2.getHeight(), null);
	                    break;
	                case 1:
	                    g2.drawImage(one, lf2.getX(), lf2.getY(), lf2.getWidth(), lf2.getHeight(), null);
	                    break;
	                default:
	                    g2.drawImage(zero, lf2.getX(), lf2.getY(), lf2.getWidth(), lf2.getHeight(), null);
	                    g2.drawImage(brokenHeart, h2.getX() - 4, h2.getY() - 3, h2.getWidth() + 9, h2.getHeight() + 9, null);
	                    break;
                }
                
                // Draw del pavimento
                Floor f = model.getFloor();
                g2.drawImage(floor, f.getX(), f.getY(), f.getWidth(), f.getHeight(), null);
                
        			// Draw delle scale
                Ladder[] l = model.getLadders();
                for (Ladder ld : l) {
                    g2.drawImage(ladder, ld.getX(), ld.getY(), ld.getWidth(), ld.getHeight(), null);
                }

                // TODO: Draw degli username
                Font font = new Font("Monospaced", Font.BOLD, 20);
                g2.setFont(font);
                g2.setColor(Color.WHITE);
                String username1 = p1.getUsername();
                String username2 = p2.getUsername();
                
                g2.drawString(username1, 8, 28);
                
                // Calcoliamo quanto è lungo il nome per attaccarlo al bordo destro
                FontMetrics fm = g2.getFontMetrics();
                int username2Width = fm.stringWidth(username2);
                
                // Definiamo un margine dal bordo destro (es. 20 pixel come fai a sinistra)
                int marginRight = 20;
                int rightEdge = getWidth()-marginRight;
                
                g2.drawString(username2, rightEdge-username2Width, 28);
                
	            	g2.setColor(Color.BLACK);
	            	
	            	// Draw del P1
	                if (p1.isMovingDx()) {
	                    g2.drawImage(marioRight, p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight(), null);
	                } else if (p1.isMovingSx()) {
	                    g2.drawImage(marioLeft, p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight(), null);
	                } else if (p1.isClimbing() || p1.isFalling()) {
	                    g2.drawImage(marioClimb, p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight(), null);
	                }
	                
	                // Draw del P2
	                if (p2.isMovingDx()) {
	                    g2.drawImage(luigiRight, p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight(), null);
	                } else if (p2.isMovingSx()) {
	                    g2.drawImage(luigiLeft, p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight(), null);
	                } else if (p2.isClimbing() || p2.isFalling()) {
	                    g2.drawImage(luigiClimb, p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight(), null);
	                }
	
	                // Draw delle barre
	                Floor[] b = model.getBars();
	                for (Floor br : b) {
	                    g2.drawImage(bar, br.getX(), br.getY(), br.getWidth(), br.getHeight(), null);
	                }
	
	                // Draw di Kong
	                Kong k = model.getKong();
	                if (k.isGetting()) {
	                    g2.drawImage(kongGet, k.getX(), k.getY(), k.getWidth(), k.getHeight(), null);
	                } else if (k.isFull()) {
	                    g2.drawImage(kongFull, k.getX(), k.getY(), k.getWidth(), k.getHeight(), null);
	                } else if (k.isThrowing()) {
	                    g2.drawImage(kongThrow, k.getX(), k.getY(), k.getWidth(), k.getHeight(), null);
	                }
	
	                // Draw di Peach
	                Peach pe = model.getPeach();
	                g2.drawImage(peach, pe.getX(), pe.getY(), pe.getWidth(), pe.getHeight(), null);
	
	                // Draw dello stack di barili
	                Barrel bs = model.getBarrelStack();
	                g2.drawImage(barrelStack, bs.getX(), bs.getY(), bs.getWidth(), bs.getHeight(), null);
	
	                // Draw dei barili
	                ArrayList<Barrel> br = model.getBarrels();
	                for (Barrel bl : br) {
	                    if (bl.isVisible()) {
	                        g2.drawImage(barrel, bl.getX(), bl.getY(), bl.getWidth(), bl.getHeight(), null);
	                    }
	                }
	                
	                // Se il gioco è finito e non ho ancora mostrato il popup
	                if (model.isGameOver1() && !popupShown) {
	                    popupShown = true;
	                    // Game Over
	                    JOptionPane.showOptionDialog(null, "", "Game Over :(", 0, 0, gameover, quit, 0);
	                    System.exit(0);
	                } 
	                // Se ho vinto e non ho ancora mostrato il popup
	                else if (model.isGameWon1() && !popupShown) {
	                    popupShown = true;
	                    // Win
	                    JOptionPane.showOptionDialog(null, "", "You Win!", 0, 0, victory, quit, 0);
	                    System.exit(0);
	                }
	                
	                // Pausa
	                //if(model.isGamePaused()) {
	                		//JOptionPane.showOptionDialog(null, "", "Pause", 0, 0, pause, resume, 0);
	                		//model.setGamePaused(false);
	                //}
	                
	                if (model.isGamePaused()) {
	                    g2.setColor(new Color(0, 0, 0, 150)); 
	                    g2.fillRect(0, 0, model.GameWidth, model.GameHeight);
	                    
	                    g2.setColor(Color.WHITE);
	                    g2.setFont(new Font("Helvetica", Font.BOLD, 50));
	                    String pauseText = "PAUSED";
	                    fm = g2.getFontMetrics();
	                    int testX = (model.GameWidth - fm.stringWidth(pauseText)) / 2;
	                    g2.drawString(pauseText, testX, model.GameHeight / 2);
	                }
        	} else {
        		// Timer
        		ShowNumber t = model.getTimerNumber();
        		switch (t.getNumber()) {
	                case 3:
	                	g2.drawImage(three, t.getX(), t.getY(), t.getWidth(), t.getHeight(), null);
	                    break;
	                case 2:
	                	g2.drawImage(two, t.getX(), t.getY(), t.getWidth(), t.getHeight(), null);
	                    break;
	                case 1:
	                	g2.drawImage(one, t.getX(), t.getY(), t.getWidth(), t.getHeight(), null);
	                    break;
	                default:
	                	g2.drawImage(zero, t.getX(), t.getY(), t.getWidth(), t.getHeight(), null);
	                    break;
        		}
        	}
        	
        } else {
        	// Il client non si è ancora  connesso
            Font font = new Font("Monospaced", Font.BOLD, 60);
            g2.setFont(font);
            g2.setColor(Color.ORANGE);
            
            String username = p1.getUsername().toUpperCase();
            String ipAddressPort = (serverIpAddress + ":" + serverPort);
            String shareTxt = "Share your ip with the other player!";
            
            FontMetrics metrics = g2.getFontMetrics(g2.getFont());
            Point usernamePosition = calculateTextPosition(username, metrics);
            Point ipAddressPortPosition = calculateTextPosition(ipAddressPort, metrics);
            
            g2.drawString(username, usernamePosition.x, usernamePosition.y-40);
            g2.drawString(ipAddressPort, ipAddressPortPosition.x, ipAddressPortPosition.y+20);
            
            font = new Font("Monospaced", Font.BOLD, 30);
            g2.setFont(font);
            metrics = g2.getFontMetrics(g2.getFont());
            Point shareTxtPosition = calculateTextPosition(shareTxt, metrics);
            
            g2.drawString(shareTxt, shareTxtPosition.x, shareTxtPosition.y+70);
        }
        
    }
    
	public void setServerIpAddress(String serverIpAddress) {
		this.serverIpAddress = serverIpAddress;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	
	public Point calculateTextPosition(String string, FontMetrics metrics) {
		// Calcola la coordinata X per centrare
        int x = (getWidth() - metrics.stringWidth(string)) / 2;
        
        // Calcola la coordinata Y per centrare verticalmente
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        
        return new Point(x, y);
	}

	@Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        stagePanel = new javax.swing.JPanel(){
            @Override
            public void paint(Graphics g){
                super.paint(g);
                paintStage(g);
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Donkey Kong");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        javax.swing.GroupLayout stagePanelLayout = new javax.swing.GroupLayout(stagePanel);
        stagePanel.setLayout(stagePanelLayout);
        stagePanelLayout.setHorizontalGroup(
            stagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        stagePanelLayout.setVerticalGroup(
            stagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel stagePanel;
    // End of variables declaration//GEN-END:variables
}
