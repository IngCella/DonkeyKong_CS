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
import javax.swing.JFrame;
import javax.swing.JLabel;

public class View extends JFrame implements Observer {

    private final Model model;
    
    private String serverIpAddress;
    private int serverPort;
    
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
    private Image kongGet;
    private Image kongFull;
    private Image kongThrow;
    private Image peach;
    private Image floor;
    private Image bar;
    private Image ladder;
    private Image barrelStack;
    private Image barrel;

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
        
        Player p = model.getPlayer1();
        
        if(model.isStartGame()) {
        	if(model.isTimerFinished()) {
                Heart h = model.getHeart();
                ShowNumber lf = model.getLifes1();
                
                if (lf.getNumber() != 0) {
                    g2.drawImage(heart, h.getX(), h.getY(), h.getWidth(), h.getHeight(), null);
                }
                
                switch (lf.getNumber()) {
                    case 3:
                        g2.drawImage(three, lf.getX(), lf.getY(), lf.getWidth(), lf.getHeight(), null);
                        break;
                    case 2:
                        g2.drawImage(two, lf.getX(), lf.getY(), lf.getWidth(), lf.getHeight(), null);
                        break;
                    case 1:
                        g2.drawImage(one, lf.getX(), lf.getY(), lf.getWidth(), lf.getHeight(), null);
                        break;
                    default:
                        g2.drawImage(zero, lf.getX(), lf.getY(), lf.getWidth(), lf.getHeight(), null);
                        g2.drawImage(brokenHeart, h.getX() - 4, h.getY() - 3, h.getWidth() + 9, h.getHeight() + 9, null);
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

                // Draw del player
                Font font = new Font("Monospaced", Font.BOLD, 20);
                g2.setFont(font);
                g2.setColor(Color.WHITE);
                String username = p.getUsername();
                g2.drawString(username, 8, 28);
            	g2.setColor(Color.BLACK);
            	
                if (p.isMovingDx()) {
                    g2.drawImage(marioRight, p.getX(), p.getY(), p.getWidth(), p.getHeight(), null);
                } else if (p.isMovingSx()) {
                    g2.drawImage(marioLeft, p.getX(), p.getY(), p.getWidth(), p.getHeight(), null);
                } else if (p.isClimbing() || p.isFalling()) {
                    g2.drawImage(marioClimb, p.getX(), p.getY(), p.getWidth(), p.getHeight(), null);
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
            
            String username = p.getUsername().toUpperCase();
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
