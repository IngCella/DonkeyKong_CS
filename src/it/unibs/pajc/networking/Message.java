package it.unibs.pajc.networking;

import java.io.Serializable;

// Interfaccia del messaggio che deve essere serializzabile
public interface Message<C> extends Serializable {
	
	// Metodo open che ogni oggetto che vuole creare un messaggio deve implementare
    public void open(C context, Receiver sender);
}
