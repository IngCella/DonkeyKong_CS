package it.unibs.pajc.setup;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
		
		pnlConfig.getTxtUsername().setText(model.getUsername());
		pnlConfig.getTxtIP().setText(model.getIpAddress());
		pnlConfig.getTxtPort().setText("" + model.getPort());
		
		// Client
        model.addPropertyChangeListener(ModelSetup.PROP_USERNAME, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            		pnlConfig.getTxtUsername().setText(model.getUsername());
            }
        });

        model.addPropertyChangeListener(ModelSetup.PROP_IPADDRESS, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            		pnlConfig.getTxtIP().setText(model.getIpAddress());
            }
        });

        model.addPropertyChangeListener(ModelSetup.PROP_PORT, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            		pnlConfig.getTxtPort().setText("" + model.getPort());
            }
        });
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
	
	// Listeners
	public void addBtnJoinListener(ActionListener actionListener) {
		pnlConfig.getBtnStart().addActionListener(actionListener);
    }

	// Getters
    public String getUsername() {
        return pnlConfig.getTxtUsername().getText();
    }

    public String getIpAddress() {
        return pnlConfig.getTxtIP().getText();
    }

    public int getPort() {
        int port = 55555;
        
        try {
            port = Integer.parseInt(pnlConfig.getTxtPort().getText(), 10);
        } 
        catch (NumberFormatException e) {
            System.out.println("Errore lettura porta, usata 55555");
        }
        
        return port;
    }
	
}
