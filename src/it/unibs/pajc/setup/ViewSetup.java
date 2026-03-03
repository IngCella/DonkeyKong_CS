package it.unibs.pajc.setup;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ViewSetup {

	public JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private PnlConfig pnlConfig;
    private PnlHome pnlHome;
	private ModelSetup model;
	
	/**
	 * Create the application.
	 */
	public ViewSetup(ModelSetup model) {
		initialize();
		this.model = model;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Donkey Kong - Setup");
        frame.setSize(700, 550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Creazione pannelli
        pnlHome = new PnlHome(cardLayout, mainPanel);
        pnlConfig = new PnlConfig(cardLayout, mainPanel);

        // Aggiunta al CardLayout
        mainPanel.add(pnlHome, "HOME");
        mainPanel.add(pnlConfig, "CONFIG");

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
	}
	
	public void pippo(boolean isHost) {
		pnlConfig.setHost(isHost);
	}
	
}
