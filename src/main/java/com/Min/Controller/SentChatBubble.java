package com.Min.Controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Class for creating chat bubbles for sent messages, aligned to the right side of the screen.
 */
public class SentChatBubble extends ChatBubble{

    public SentChatBubble(String message) {
        super(message);
        this.fillColor = Color.LIGHTGREEN;
        BackgroundFill backgroundFill = new BackgroundFill(fillColor, new CornerRadii(2), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.container.setBackground(background);
        container.setWrapText(true);
        container.setPadding(new Insets(5));
        this.container.setAlignment(Pos.CENTER_RIGHT);

        getChildren().setAll(container);
        setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(15,10,0,10));
        AnchorPane.setRightAnchor(this, 5.0);
    }
}
