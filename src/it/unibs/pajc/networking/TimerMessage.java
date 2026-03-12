package it.unibs.pajc.networking;

import it.unibs.pajc.donkey_kong.Model;
import it.unibs.pajc.donkey_kong.entities.ShowNumber;

// Classe che gestisce il messaggio che contentiene un oggetto di tipo model
public class TimerMessage implements Message<Model> {

	private ShowNumber timerNumber;
	
	public TimerMessage(ShowNumber timerNumber) {
		this.timerNumber = timerNumber;
	}
	
	// Apro il messaggio e aggiorno il model con il contenuto
	@Override
	public void open(Model model, Receiver sender) {
		model.setStartGame(true);
		model.setTimerNumber(timerNumber);
	}
}
