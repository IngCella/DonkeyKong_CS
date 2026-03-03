// All rights to Lorenzo M. Cella
package it.unibs.pajc.donkey_kong;
import java.net.UnknownHostException;

import it.unibs.pajc.setup.*;

public class DonkeyKong {

    public static void main(String[] args) throws UnknownHostException {
        ModelSetup modelSetup = new ModelSetup();
        ViewSetup viewSetup = new ViewSetup(modelSetup);
        ControllerSetup controllerSetup = new ControllerSetup(modelSetup, viewSetup);
        
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view, controllerSetup);

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                viewSetup.frame.setVisible(true);
            }
        });
    }
}
