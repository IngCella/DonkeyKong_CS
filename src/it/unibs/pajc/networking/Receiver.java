package it.unibs.pajc.networking;

public interface Receiver<C> {

	// Metodo da implementare per gestire l'invio/ricezione del messaggio
    public void accept(Message<C> message, Receiver<C> from);
}
