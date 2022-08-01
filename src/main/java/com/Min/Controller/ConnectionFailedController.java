package com.Min.Controller;

import com.Min.Model.DataModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConnectionFailedController{
    @FXML
    Button returnButton;
    Stage window;
    DataModel dataModel;

    public void initModel(DataModel model){
        if (this.dataModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.dataModel = model ;
        this.dataModel.setCurrentScene("ConnectionFailedScreen");
    }
}
