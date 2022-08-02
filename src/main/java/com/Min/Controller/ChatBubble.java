package com.Min.Controller;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class ChatBubble extends HBox {
    Label container;
    Text chatText;
    Paint fillColor;

    protected Color backgroundColour;
    public ChatBubble(String message){
        this.container = new Label(message);
        chatText = new Text(message);
    }
}
