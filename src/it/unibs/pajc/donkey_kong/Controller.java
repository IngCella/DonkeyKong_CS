package it.unibs.pajc.donkey_kong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

    String[] resume = {"Resume"};
    String[] quit = {"Quit"};
    private ImageIcon pause = new ImageIcon(getClass().getResource("../assets/pause.png"));
    private ImageIcon gameover = new ImageIcon(getClass().getResource("../assets/gameOver.png"));

    private Timer timer;

    public Controller(Model model, View view, ControllerSetup controllerSetup) {
        this.model = model;
        this.view = view;
        this.controllerSetup = controllerSetup;

        // Timer che ripete l'update (delay = 16 = 62fps)
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.update();
                view.repaint();
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
                }
            }
        };
        
        // Listener di Host (quindi Server)
        controllerSetup.addBtnStartListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            		// Fare un metodo che controlli che i dati inseriti siano validi e che, nel caso non vadano bene
            		// venga inserito nelle textfield una scritta rossa magari che avvisi l'errore che quando si vuole scrivere sparisce
            		
            		int port = controllerSetup.getServerPort();
            		String username = controllerSetup.getServerName();
            		
            		if(port != 0 && username != null) {
            			terminal = new Server("Server", port, receiver);
                		
                		controllerSetup.setVisible(false);
                		view.setVisible(true);
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
                		
                		controllerSetup.setVisible(false);
                		view.setVisible(true);
                		timer.start();
            		}
            }
        });

        // Quando viene premuto ESC chiude la finestra
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    JOptionPane.showOptionDialog(null, "", "Game Over :(", 0, 0, gameover, quit, 0);
                    System.exit(0);
                }
            }
        });

        // Quando viene premuta la P il gioco va in pausa
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_P) {
                    timer.stop();
                    JOptionPane.showOptionDialog(null, "", "Pause", 0, 0, pause, resume, 0);
                    timer.start();
                }
            }
        });

        // Quando viene premuta la BARRA SPAZIATRICE Mario salta
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                    model.jump();
                }
            }
        });

        // Quando viene premuta la FRECCIA DESTRA Mario si sposta a destra
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_D) {
                    model.moveDx();
                }
            }
        });

        // Quando viene premuta la FRECCIA SINISTRA Mario si sposta a sinistra
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_A) {
                    model.moveSx();
                }
            }
        });

        // Quando viene premuta la FRECCIA IN ALTO Mario sale la scala
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_W) {
                    model.climb();
                }
            }
        });

        // Quando viene premuta la FRECCIA IN BASSO Mario scende la scala
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_S) {
                    model.fall();
                }
            }
        });
    }
}
