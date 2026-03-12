package it.unibs.pajc.networking;

import it.unibs.pajc.donkey_kong.Model;
import it.unibs.pajc.donkey_kong.entities.Player;

// Classe che gestisce il messaggio che contentiene un oggetto di tipo model
public class ClientInputMessage implements Message<Model> {

	private Player player;
	
	public ClientInputMessage(Player player) {
		this.player = player;
	}
	
	// Apro il messaggio e aggiorno il model con il contenuto
	@Override
	public void open(Model model, Receiver sender) {
		// System.out.println("Arrivato il player x: " + this.player.getX()); // Debug
	    model.setPlayer2(this.player);
	}
}
