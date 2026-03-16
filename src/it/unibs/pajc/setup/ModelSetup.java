package it.unibs.pajc.setup;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelSetup {
    
    private transient final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    // Client
    private String clientUsername = "Bazo";
    public static final String PROP_CLIENTUSERNAME = "clientUsername";
    
    private String clientIP = "127.0.0.1";
    public static final String PROP_CLIENTIP = "clientIP";
    
    private int clientPort = 55555;
    public static final String PROP_CLIENTPORT = "clientPort";
    
    // Server
    private String serverUsername = "Celli";
    public static final String PROP_SERVERUSERNAME = "serverUsername";
    
    private String serverIP = "127.0.0.1";
    public static final String PROP_SERVERIP = "serverIP";
    
    private int serverPort = 55555;
    public static final String PROP_SERVERPORT = "serverPort";
    
    // PCS
    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }

    public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(property, listener);
    }
    
    // Client Name
    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientName(String clientUsername) {
        String oldUsername = this.clientUsername;
        this.clientUsername = clientUsername;
        pcs.firePropertyChange(PROP_CLIENTUSERNAME, oldUsername, clientUsername);
    }

    // Client IP
    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        String oldClientIP = this.clientIP;
        this.clientIP = clientIP;
        pcs.firePropertyChange(PROP_CLIENTIP, oldClientIP, clientIP);
    }
    
    // Client Port
    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        int oldClientPort = this.clientPort;
        this.clientPort = clientPort;
        pcs.firePropertyChange(PROP_CLIENTPORT, oldClientPort, clientPort);
    }
    
    // Server Name
    public String getServerUsername() {
        return serverUsername;
    }

    public void setServerUsername(String serverUsername) {
        String oldServerUsername = this.serverUsername;
        this.serverUsername = serverUsername;
        pcs.firePropertyChange(PROP_SERVERUSERNAME, oldServerUsername, serverUsername);
    }
    
    // Server IP
    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        String oldServerIP = this.serverIP;
        this.serverIP = serverIP;
        pcs.firePropertyChange(PROP_SERVERIP, oldServerIP, serverIP);
    }
    
    // Server Port
    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        int oldServerPort = this.serverPort;
        this.serverPort = serverPort;
        pcs.firePropertyChange(PROP_SERVERPORT, oldServerPort, serverPort);
    }
    
    public ModelSetup() {
    	
    	try {
            this.serverIP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ModelSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    }
}
