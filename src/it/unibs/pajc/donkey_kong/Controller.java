package it.unibs.pajc.donkey_kong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import it.unibs.pajc.setup.*;
import it.unibs.pajc.networking.*;

public class Controller {

    private final Model model;
    private final View view;
    private final ControllerSetup controllerSetup;
    
    // Networking
    private Terminal terminal;
    private boolean isServer = false;

    String[] quit = {"Quit"};
    private ImageIcon gameover = new ImageIcon(getClass().getResource("../assets/gameOver.png"));

    private Timer delayTimer;
    private Timer timer;

    public Controller(Model model, View view, ControllerSetup controllerSetup) {
        this.model = model;
        this.view = view;
        this.controllerSetup = controllerSetup;
        
        // Creiamo un timer che scatta una sola volta dopo 1000ms
        delayTimer = new Timer(1000, e -> {
        	if(model.isStartGame()) {
        		if(!model.isTimerFinished()) {
        			model.update();
        			view.repaint();
        			
        		} else {
        			((Timer) e.getSource()).stop(); // Ferma questo timer
        		}
        	}
        });

        // Timer che ripete l'update (delay = 16 = 62fps)
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
	            	if(isServer && model.isStartGame() && model.isTimerFinished()) {
	            		model.update();
	                view.repaint();
	                
	                terminal.accept(new ModelMessage(model), null);
	            	} else if(!isServer) {
	            		terminal.accept(new ClientInputMessage(model.getPlayer1()), null);
	            	}
            }
        });

        // Ricevitore del messaggio
        Receiver receiver = new Receiver() {
        	
        	// Il receiver crea l'implementazione del metodo accept che gli permette di leggere
        	// il messaggio da parte del terminal che l'ha inviato
            @Override
            public void accept(Message message, Receiver from) {
                //System.out.println("Ricevuto messaggio: " + message.toString()); // DEBUG
            	
            	// Controlla se il messaggio non è vuoto e ne invia il conenuto al model
                if (message != null) {
                    message.open(model, from);
                    model.setStartGame(true);
                }
            }
        };
        
        // Listener di Host (quindi Server)
        controllerSetup.addBtnStartListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
        		int port = controllerSetup.getServerPort();
        		String username = controllerSetup.getServerName();
        		String ip = controllerSetup.getServerIP();
        		
        		if(port != 0 && username != null) {
        			terminal = new Server("Server", port, receiver);
        			terminal.start();
        			
        			isServer = true;
        			
        			model.getPlayer1().setUsername(username);
        			
        			view.setServerIpAddress(ip);
        			view.setServerPort(port);
            		controllerSetup.setVisible(false);
            		view.setVisible(true);
            		
            		delayTimer.start();
            		timer.start();
        		}
            }
        });
        
        // Listener di Join (quindi Client)
        controllerSetup.addBtnGoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            		String username = controllerSetup.getClientName();
            		int port = controllerSetup.getClientPort();
            		String ip = controllerSetup.getClientIP();
            		
            		if(username != null && port != 0 && ip != null) {
            			terminal = new Client("Client", ip ,port, receiver);
            	        terminal.start();

            	        isServer = false;
            	        
            	        model.getPlayer1().setUsername(username);
            	        model.setStartGame(true);
            	        
                		controllerSetup.setVisible(false);
                		view.setVisible(true);

                		delayTimer.start();
                		timer.start();
            		}
            }
        });

        // Quando viene premuto ESC chiude la finestra
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
	                	if(isServer) {
	                		model.setGameQuitted1(true);
	                	} else {
	                		terminal.accept(new QuitMessage(), null);
	                	}
                }
            }
        });

        // Quando viene premuta la P il gioco va in pausa
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_P) {
	                	if (isServer) {
	                		System.out.println("PAUSA");
	                	    model.togglePaused();
	                	} else {
                	        terminal.accept(new PauseMessage(), null);
	                	}
                }
            }
        });

        // Quando viene premuta la BARRA SPAZIATRICE Mario salta
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_SPACE && !model.isGamePaused()) {
                    model.jump();
                }
            }
        });

        // Quando viene premuta la FRECCIA DESTRA Mario si sposta a destra
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if ((evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_D) && (!model.isGamePaused())) {
                    model.moveDx();
                }
            }
        });

        // Quando viene premuta la FRECCIA SINISTRA Mario si sposta a sinistra
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if ((evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_A) && (!model.isGamePaused())) {
                    model.moveSx();
                }
            }
        });

        // Quando viene premuta la FRECCIA IN ALTO Mario sale la scala
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if ((evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_W) && (!model.isGamePaused())) {
                    model.climb();
                }
            }
        });

        // Quando viene premuta la FRECCIA IN BASSO Mario scende la scala
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if ((evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_S) && (!model.isGamePaused())) {
                    model.fall();
                }
            }
        });
        
    }
}
