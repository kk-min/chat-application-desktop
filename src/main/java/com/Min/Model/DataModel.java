package com.Min.Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Model that is used to provide access to data in various controller classes.
 */
public class DataModel {
    /**
     * IP to connect to.
     */
    private final StringProperty targetIP = new SimpleStringProperty();
    /**
     * Window of the application.
     */
    private final ObjectProperty<Stage> window = new SimpleObjectProperty<Stage>();
    /**
     * String describing the current scene.
     */
    private final StringProperty currentScene = new SimpleStringProperty();

    public StringProperty targetIP() {
        return targetIP;
    }

    public final String getTargetIP() {
        return targetIP.get();
    }

    public final void setTargetIP(String newIP) {
        targetIP.set(newIP);
    }

    public ObjectProperty<Stage> window(){
        return window;
    }

    public Stage getWindow() {
        return window.getValue();
    }

    public final void setWindow(Stage newWindow){
        window.setValue(newWindow);
    }

    public StringProperty currentScene(){
        return currentScene;
    }

    public String getCurrentScene(){
        return currentScene.getValue();
    }

    public void setCurrentScene(String newScene){
        currentScene.setValue(newScene);
    }
}
