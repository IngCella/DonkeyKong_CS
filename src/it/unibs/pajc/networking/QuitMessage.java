package it.unibs.pajc.networking;

import it.unibs.pajc.donkey_kong.Model;

public class QuitMessage implements Message<Model> {
    @Override
    public void open(Model model, Receiver sender) {
        // Quando il Server riceve questo, inverte lo stato della pausa
        model.setGameQuitted2(true);
    }
}
