package com.Min.Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class DataModel {
    private final StringProperty targetIP = new SimpleStringProperty();
    private final ObjectProperty<Stage> window = new SimpleObjectProperty<Stage>();
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
