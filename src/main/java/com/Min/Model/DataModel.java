package com.Min.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class DataModel {
    private final StringProperty targetIP = new SimpleStringProperty();

    public StringProperty targetIP() {
        return targetIP;
    }

    public final String getTargetIP() {
        return targetIP.getValue();
    }

    public final void setTargetIP(String newIP) {
        targetIP.setValue(newIP);
    }
}
