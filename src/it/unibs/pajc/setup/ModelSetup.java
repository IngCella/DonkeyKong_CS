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
    private String clientName = "Client";
    public static final String PROP_CLIENTNAME = "clientName";
    
    private String clientIP = "0.0.0.0";
    public static final String PROP_CLIENTIP = "clientIP";
    
    private int clientPort = 55555;
    public static final String PROP_CLIENTPORT = "clientPort";
    
    // Server
    private String serverName = "Server";
    public static final String PROP_SERVERNAME = "serverName";
    
    private String serverIP = "0.0.0.0";
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
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        String oldName = this.clientName;
        this.clientName = clientName;
        pcs.firePropertyChange(PROP_CLIENTNAME, oldName, clientName);
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
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        String oldServerName = this.serverName;
        this.serverName = serverName;
        pcs.firePropertyChange(PROP_SERVERNAME, oldServerName, serverName);
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
