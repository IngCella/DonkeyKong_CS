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
    private PnlServer pnlServer;
    private PnlClient pnlClient;
    private PnlHome pnlHome;
	private ModelSetup model;
	
	/**
	 * Create the application.
	 */
	public ViewSetup(ModelSetup model) {
		initialize();
		this.model = model;
		
		pnlClient.getTxtClientUsername().setText(model.getClientUsername());
		pnlClient.getTxtClientIP().setText(model.getClientIP());
		pnlClient.getTxtClientPort().setText("" + model.getClientPort());
        
		pnlServer.getTxtServerUsername().setText(model.getServerUsername());
		pnlServer.getTxtServerIP().setText(model.getServerIP());
		pnlServer.getTxtServerPort().setText("" + model.getServerPort());

        // Client
        model.addPropertyChangeListener(ModelSetup.PROP_CLIENTUSERNAME, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                pnlClient.getTxtClientUsername().setText(model.getClientUsername());
            }
        });

        model.addPropertyChangeListener(ModelSetup.PROP_CLIENTIP, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            		pnlClient.getTxtClientIP().setText(model.getClientIP());
            }
        });

        model.addPropertyChangeListener(ModelSetup.PROP_CLIENTPORT, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            		pnlClient.getTxtClientPort().setText("" + model.getClientPort());
            }
        });

        // Server
        model.addPropertyChangeListener(ModelSetup.PROP_SERVERUSERNAME, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                pnlServer.getTxtServerUsername().setText(model.getServerUsername());
            }
        });

        model.addPropertyChangeListener(ModelSetup.PROP_SERVERPORT, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            		pnlServer.getTxtServerPort().setText("" + model.getServerPort());
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
        pnlServer = new PnlServer(cardLayout, mainPanel);
        pnlClient = new PnlClient(cardLayout, mainPanel);

        // Aggiunta al CardLayout
        mainPanel.add(pnlHome, "HOME");
        mainPanel.add(pnlServer, "SERVER");
        mainPanel.add(pnlClient, "CLIENT");

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
	}
	
	// Listeners
	public void addBtnStartListener(ActionListener actionListener) {
		pnlServer.getBtnStart().addActionListener(actionListener);
    }
	
	public void addBtnGoListener(ActionListener actionListener) {
		pnlClient.getBtnGo().addActionListener(actionListener);
    }

	// Getters Client
    public String getClientUsername() {
        return pnlClient.getTxtClientUsername().getText();
    }

    public String getClientIP() {
        return pnlClient.getTxtClientIP().getText();
    }

    public int getClientPort() {
        int port = 55555;
        
        try {
            port = Integer.parseInt(pnlClient.getTxtClientPort().getText(), 10);
        } 
        catch (NumberFormatException e) {
            System.out.println("Errore lettura porta, usata 55555");
        }
        
        return port;
    }
    
    // Getters Server
    String getServerUsername() {
        return pnlServer.getTxtServerUsername().getText();
    }

    int getServerPort() {
        int port = 55555;
        
        try {
            port = Integer.parseInt(pnlServer.getTxtServerPort().getText(), 10);
        } 
        catch (NumberFormatException e) {
            System.out.println("Errore lettura porta, usata 55555");
        }
        
        return port;
    }

	public void setVisible(boolean b) {
		this.setVisible(b);
	}
}
