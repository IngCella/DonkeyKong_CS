package it.unibs.pajc.networking;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Terminal {

    public Server(String name, int port, Receiver destination) {
        super(name, port, destination);
    }

    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(port); // Crea la server socket
            socket = serverSocket.accept(); // Blocca il thread fino a quando l'altro non si è connesso
            serverSocket.close(); // Chiude non accetta più le connessioni
            
            super.run(); // Chiama il metodo run di terminal (avviando il loop di ricezione dei messaggi)
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
}