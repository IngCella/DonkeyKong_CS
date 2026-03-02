package it.unibs.pajc.setup;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class ViewSetup {

	public JFrame frame;
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlBackground = new JPanel() {
			private Image home;
	        {
	            try {
	                home = ImageIO.read(getClass().getResource("../assets/home.jpg"));
	            } catch (IOException e) {
	                System.out.println("Immagine non trovata");
	            }
	        }

	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            if (home != null) {
	                g.drawImage(home, 0, 0, getWidth(), getHeight(), this);
	            }
	        }
		};
		frame.getContentPane().add(pnlBackground, BorderLayout.CENTER);
		pnlBackground.setLayout(null);
		
		PnlConfig pnlConfig = new PnlConfig();
		pnlConfig.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		pnlConfig.setVisible(false);
		pnlBackground.add(pnlConfig);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(0, 0, 84, 20);
		pnlBackground.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(0, 0, 84, 20);
		pnlBackground.add(btnNewButton_1);
	}
}
