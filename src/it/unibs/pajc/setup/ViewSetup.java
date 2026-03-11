package it.unibs.pajc.setup;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	
	private static final String IPV4_REGEX = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";
	
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
    		String username = pnlClient.getTxtClientUsername().getText();

		if(username.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "You must insert an username", "Error", JOptionPane.ERROR_MESSAGE);
			username = null;
		}
		
        return username;
    }

    public String getClientIP() {
    		String ipAddress = pnlClient.getTxtClientIP().getText();
    		
		if(ipAddress.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "You must insert an ip", "Error", JOptionPane.ERROR_MESSAGE);
			ipAddress = null;
		}
		
		if(ipAddress != null && !isValidIPv4Regex(ipAddress)) {
			JOptionPane.showMessageDialog(null, "You must insert a valid ip", "Error", JOptionPane.ERROR_MESSAGE);
			ipAddress = null;
		}
		
        return ipAddress;
    }

    public int getClientPort() {
        int port = 55555;
        
        try {
        		port = Integer.parseInt(pnlClient.getTxtClientPort().getText(), 10);
        } 
        catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, "You must insert a port number", "Error", JOptionPane.ERROR_MESSAGE);
        		port = 0;
        }
        
        if(port <= 49152 || port >= 65535) {
        		JOptionPane.showMessageDialog(null, "You must insert a valid port number\n(49152 -> 65535)", "Error", JOptionPane.ERROR_MESSAGE);
        		port = 0;
        }
        
        return port;
    }
    
    // Getters Server
    String getServerUsername() {
    		String username = pnlServer.getTxtServerUsername().getText();

    		if(username.trim().length() == 0) {
    			JOptionPane.showMessageDialog(null, "You must insert an username", "Error", JOptionPane.ERROR_MESSAGE);
    			username = null;
    		}
    			
        return username;
    }

    int getServerPort() {
        int port = 55555;
        
        try {
            port = Integer.parseInt(pnlServer.getTxtServerPort().getText(), 10);
        } 
        catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, "You must insert a port number", "Error", JOptionPane.ERROR_MESSAGE);
        		port = 0;
        }
        
        if(port <= 49152 || port >= 65535) {
        		JOptionPane.showMessageDialog(null, "You must insert a valid port number\n(49152 -> 65535)", "Error", JOptionPane.ERROR_MESSAGE);
        		port = 0;
        }
        
        return port;
    }
    
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	public static boolean isValidIPv4Regex(String ip) {
	    return ip.matches(IPV4_REGEX);
	}
}
