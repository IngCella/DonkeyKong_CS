package it.unibs.pajc.networking;

import it.unibs.pajc.donkey_kong.Model;

// Classe che gestisce il messaggio che contentiene un oggetto di tipo model
public class ModelMessage implements Message<Model> {

	private Model model;
	
	public ModelMessage(Model model) {
		this.model = model;
	}
	
	// Apro il messaggio e aggiorno il model con il contenuto
	@Override
	public void open(Model model, Receiver sender) {
		model.setStartGame(true);
		model.sync(this.model);
	}
}
