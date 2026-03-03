package it.unibs.pajc.setup;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelSetup {
    
    private transient final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    // User
    private String username = "Client";
    public static final String PROP_USERNAME = "username";
    
    private String ipAddress = "0.0.0.0";
    public static final String PROP_IPADDRESS = "ipAddress";
    
    private int port = 55555;
    public static final String PROP_PORT = "port";
    
    // PCS
    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }

    public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(property, listener);
    }
    
    // Client Name
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        String oldUsername = this.username;
        this.username = username;
        pcs.firePropertyChange(PROP_USERNAME, oldUsername, username);
    }

    // Client IP
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        String oldIpAddress = this.ipAddress;
        this.ipAddress = ipAddress;
        pcs.firePropertyChange(PROP_IPADDRESS, oldIpAddress, ipAddress);
    }
    
    // Client Port
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        int oldPort = this.port;
        this.port = port;
        pcs.firePropertyChange(PROP_PORT, oldPort, port);
    }
    
    public ModelSetup() {
        try {
        		// TODO
            this.ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ModelSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
