// All rights to Lorenzo M. Cella
package it.unibs.pajc.donkey_kong;
import java.net.UnknownHostException;

import it.unibs.pajc.setup.*;

public class DonkeyKong {

    public static void main(String[] args) throws UnknownHostException {
        ModelSetup model = new ModelSetup();
        ViewSetup view = new ViewSetup(model);
        ControllerSetup controller = new ControllerSetup(model, view);

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.frame.setVisible(true);
            }
        });
    }
}
