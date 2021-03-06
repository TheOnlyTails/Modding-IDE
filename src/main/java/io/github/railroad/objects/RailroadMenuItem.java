package io.github.railroad.objects;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

public class RailroadMenuItem extends MenuItem {

    private RailroadMenuItem(Builder propertiesIn) {
        setAccelerator(propertiesIn.acceleratorKey);
        setDisable(propertiesIn.defaultDisabled);
        setGraphic(propertiesIn.graphic);
        setId(propertiesIn.id);
        setMnemonicParsing(propertiesIn.shouldParseText);
        setOnAction(propertiesIn.action);
        setOnMenuValidation(propertiesIn.menuValidation);
        setParentMenu(propertiesIn.parent);
        setParentPopup(propertiesIn.contextMenu);
        setStyle(propertiesIn.style);
        setText(propertiesIn.text);
        setUserData(propertiesIn.customUserData);
        setVisible(propertiesIn.defaultVisible);
    }

    public static class Builder {
        private KeyCombination acceleratorKey;
        private boolean defaultDisabled, shouldParseText = true, defaultVisible = true;
        private ImageView graphic;
        private String id, style, text;
        private EventHandler<ActionEvent> action;
        private EventHandler<Event> menuValidation;
        private Menu parent;
        private ContextMenu contextMenu;
        private Object customUserData;

        private Builder(String textIn) {
            text = textIn;
        }

        public static Builder create(String textIn) {
            return new Builder(textIn);
        }

        public Builder setAccelerationKey(KeyCombination combination) {
            acceleratorKey = combination;
            return this;
        }

        public Builder disable() {
            defaultDisabled = true;
            return this;
        }

        public Builder setGraphic(ImageView graphicIn) {
            graphic = graphicIn;
            return this;
        }

        public Builder setID(String idIn) {
            id = idIn;
            return this;
        }

        public Builder parseText(boolean parse) {
            shouldParseText = parse;
            return this;
        }

        public Builder setActionEvent(EventHandler<ActionEvent> event) {
            action = event;
            return this;
        }

        public Builder setMenuValidation(EventHandler<Event> event) {
            menuValidation = event;
            return this;
        }

        public Builder setParentMenu(Menu parentIn) {
            parent = parentIn;
            return this;
        }

        public Builder setContextMenu(ContextMenu menu) {
            contextMenu = menu;
            return this;
        }

        public Builder setStyle(String styleIn) {
            style = styleIn;
            return this;
        }

        public Builder setText(String textIn) {
            text = textIn;
            return this;
        }

        public Builder setCustomUserData(Object data) {
            customUserData = data;
            return this;
        }

        public Builder setVisible(boolean visible) {
            defaultVisible = visible;
            return this;
        }

        public RailroadMenuItem build() {
            if (id == null) {
                id = text;
            }
            return new RailroadMenuItem(this);
        }
    }
}
