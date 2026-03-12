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
		/** /
		System.out.println("Arrivato"); // Debug
		
		this.model.setStartGame(true);
		this.model.setTimerFinished(false);
		model.setStartGame(true);
		
		model.sync(this.model);
		/**/
		// Sincronizza il modello locale (model) con quello ricevuto (this.model)
	    model.sync(this.model);
	    
	    // Ora che i dati sono aggiornati, diciamo al gioco di partire 
	    // (se non era già partito) e di ridisegnare
	    model.setStartGame(true);
	    model.cgd(); // Il metodo che hai aggiunto tu per fare notifyObservers
	}
}
