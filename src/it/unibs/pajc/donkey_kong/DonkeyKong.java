// All rights to Lorenzo M. Cella
package it.unibs.pajc.donkey_kong;

public class DonkeyKong {

	
	// CIAO CIAO
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.setVisible(true);
            }
        });
    }
}
