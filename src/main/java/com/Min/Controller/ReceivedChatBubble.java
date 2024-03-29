package com.Min.Controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Class for creating chat bubbles for received messages, aligned to the left side of the screen.
 */
public class ReceivedChatBubble extends ChatBubble{

    public ReceivedChatBubble(String message) {
        super(message);
        this.fillColor = Color.LIGHTBLUE;
        BackgroundFill backgroundFill = new BackgroundFill(fillColor, new CornerRadii(2), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.container.setBackground(background);
        container.setWrapText(true);
        container.setPadding(new Insets(5));
        this.container.setAlignment(Pos.CENTER_LEFT);

        getChildren().setAll(container);
        setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(15,10,0,10));
        AnchorPane.setLeftAnchor(this, 5.0);
    }
}
