package it.unibs.pajc.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// La classe Terminal estende la classe Thread, siccome dovrà gestire lo scambio di messaggi, in questo modo ciò
// può essere fatto su un thread parallelo a quello principale
public class Terminal extends Thread implements Receiver {

    protected int port; // Porta sulla quale avviene la comunicazione
    protected Socket socket; // Socket
    protected ObjectOutputStream output; // Stream per mandare oggetti serializzabili in output
    protected ObjectInputStream input; // Stream per ricevere oggetti serializzabili in input
    protected Receiver destination; // Ricevitore del messaggio
    protected boolean isClosed = true; // Flag che indica lo stato della connessione

    public Terminal(String name, int port, Receiver destination) {
        super(name);
        this.port = port;
        this.destination = destination;
        setDaemon(true); // In questo modo il thread viene terminato forzatamente al termine dell'app
        // In pratica quando il main() termina il Server/Client viene chiuso anche se è ancora in ascolto
    }

    @Override
    public void run() {
        Message message;
        try {
        	
        	// Inizializzazione degli stream
            System.out.println("full duplex socket port " + socket.getPort());
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            isClosed = false;

            // Loop di ascolto fino a quando il thread non viene interrotto o arriva un messaggio nullo
            do {
                message = (Message) input.readObject(); // Si blocca qui fino a quando non arriva un messaggio (questa
                // è l'utilità di star facendo tutto su un thread parallelo, così da non bloccare quello principale
                // ovvero quello della UI)
                
                destination.accept(message, this); // Passa il messaggio al controller
            } while (!this.isInterrupted() && message != null);

            // Chiudi la connessione
            isClosed = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void accept(Message message, Receiver from) {
        // Arriva dal Controller da inoltrare al Terminal corrispondente
        try {
        	// Se la connessione non è chiusa invia il messaggio
            if (!isClosed) {
            		output.reset(); // Reset del flusso di output (della cache)
            						// Così da non inviare riferimenti a oggetti precedenti (IMPORTANTE)
            		
            		output.writeObject(message); // Serializza e invia il messaggio via TCP/IP
            		output.flush(); // Svuota il buffer forzando l'invio immediato
            } else {
                System.out.println("Invio di messaggio, ma l'altro non c'è");
            }
        } catch (IOException e) {
        	System.out.println("Errore durante l'invio del messaggio: " + e.getMessage());
        }
    }

    public void close() {
        try {
            isClosed = true;
            this.interrupt(); // Interrompe il thread
            
            if (output != null) {
            	output.close();
            }
            if (input != null) {
            	input.close();
            }
            if (socket != null && !socket.isClosed()) {
            	socket.close();
            }
            
            System.out.println("Connessione chiusa correttamente.");
        } catch (IOException e) {
            System.out.println("Errore durante la chiusura del terminale: " + e.getMessage());
        }
    }

}
