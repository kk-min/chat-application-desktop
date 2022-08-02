package com.Min.Controller;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;


/**
 * Superclass for creating chat bubbles.
 */
public abstract class ChatBubble extends HBox {

    Label container;
    Text chatText;
    Paint fillColor;
    protected Color backgroundColour;

    /**
     *
     * @param message Message inside the chat bubble.
     */
    public ChatBubble(String message){
        this.container = new Label(message);
        chatText = new Text(message);
    }
}
