package it.unibs.pajc.donkey_kong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Controller {

    private final Model model;
    private final View view;

    String[] resume = {"Resume"};
    String[] quit = {"Quit"};
    private ImageIcon pause = new ImageIcon(getClass().getResource("../assets/pause.png"));
    private ImageIcon gameover = new ImageIcon(getClass().getResource("../assets/gameOver.png"));

    private Timer timer;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        // Timer che ripete l'update (delay = 16 = 62fps)
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.update();
                view.repaint();
            }
        });

        timer.start();

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
