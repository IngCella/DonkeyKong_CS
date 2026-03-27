package it.unibs.pajc.networking;

import java.io.IOException;
import java.net.Socket;

// Client estende Terminal
public class Client extends Terminal {
    private String ip; // IP del server a cui collegarsi

    public Client(String name, String ip, int port, Receiver destination) {
        super(name, port, destination);
        this.ip = ip;
    }

    // Override del metodo run dei Thread
    @Override
    public void run() {
        try{
            socket = new Socket(ip, port); // Connessione al server (aspetta fino a quando non lo trova)
            super.run(); // Chiama il metodo run di terminal (avviando il loop di ricezione dei messaggi)
        }
        catch (IOException e) {
        	    // TODO:
            //e.printStackTrace();
        }
    }
}