package it.unibs.pajc.setup;

import java.awt.event.ActionListener;

public class ControllerSetup {
    private final ModelSetup setupModel;
    private final ViewSetup setupView;

    public ControllerSetup(ModelSetup model, ViewSetup view) {
        this.setupModel = model;
        this.setupView = view;
    }
/*
    public void addBtnJoinListener(ActionListener actionListener) {
        setupView.addBtnJoinListener(actionListener);
    }

    public void addBtnListenListener(ActionListener actionListener) {
        setupView.addBtnListenListener(actionListener);
    }
    
    public void setVisible(boolean b) {
        setupView.setVisible(b);
    }
    
    // Getters Client
    public String getClientName(){
        return setupView.getClientName();
    }
    
    public String getClientIP(){
        return setupView.getClientIP();
    }
    
    public int getClientPort(){
        return setupView.getClientPort();
    }
    
    // Getters Server
    public String getServerName(){
        return setupView.getServerName();
    }
    
    public int getServerPort(){
        return setupView.getServerPort();
    }
    */
}
