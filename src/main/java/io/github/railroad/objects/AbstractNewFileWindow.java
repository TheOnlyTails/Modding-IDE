package io.github.railroad.objects;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static io.github.railroad.utility.UIUtils.createButton;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.exists;
import static java.nio.file.Paths.get;

public abstract class AbstractNewFileWindow {

    public String filePath;
    public Label pathName;
    public String title;
    public String message;

    public AbstractNewFileWindow(String title, String message) {
        this.title = title;
        this.message = message;
        makeWindow();
    }

    public void fileDialogBox(Stage window) {
        final FileChooser fileChooser = new FileChooser();

        final File file = fileChooser.showSaveDialog(window);
        if (file != null) {
            filePath = file.getAbsolutePath();
            pathName.setText(filePath);
        }
        //TODO make a remembering classpath
        fileChooser.setInitialDirectory(new File(""));
    }

    protected Button saveFile(Stage window) {
        return createButton(message, event -> {
            final Path path = get(filePath);
            if (!exists(path)) {
                try {
                    createFile(path);
                } catch (IOException exception) {
                    throw new RuntimeException("Unable to save a file", exception);
                }
            }
            window.close();
        });
    }

    public void makeWindow() {
        final Stage window = new Stage();
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(100);
        window.setResizable(false);

        pathName = new Label("File Path");

        final Button yesBtn = saveFile(window);
        fileDialogBox(window);

        final VBox layout = new VBox(10);
        layout.getChildren().addAll(pathName, yesBtn);
        layout.setAlignment(Pos.CENTER);

        final Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
