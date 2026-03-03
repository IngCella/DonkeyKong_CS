package it.unibs.pajc.setup;

import java.awt.event.ActionListener;

public class ControllerSetup {
    private final ModelSetup modelSetup;
    private final ViewSetup viewSetup;

    public ControllerSetup(ModelSetup model, ViewSetup view) {
        this.modelSetup = model;
        this.viewSetup = view;
    }
    
    public void addBtnStartListener(ActionListener actionListener) {
        viewSetup.addBtnStartListener(actionListener);
    }
    
    public void addBtnGoListener(ActionListener actionListener) {
        viewSetup.addBtnGoListener(actionListener);
    }
    
    public void setVisible(boolean b) {
        viewSetup.setVisible(b);
    }
    
    // Getters Client
    public String getClientName(){
        return viewSetup.getClientUsername();
    }
    
    public String getClientIP(){
        return viewSetup.getClientIP();
    }
    
    public int getClientPort(){
        return viewSetup.getClientPort();
    }
    
    // Getters Server
    public String getServerName(){
        return viewSetup.getServerUsername();
    }
    
    public int getServerPort(){
        return viewSetup.getServerPort();
    }
}
