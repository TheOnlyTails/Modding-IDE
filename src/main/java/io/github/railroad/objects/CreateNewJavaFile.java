package io.github.railroad.objects;

import io.github.railroad.utility.UIUtils;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.createFile;
import static java.nio.file.Files.exists;
import static java.nio.file.Paths.get;

public class CreateNewJavaFile extends AbstractNewFileWindow {
    private final JavaClassTypes type;

    public CreateNewJavaFile(String title, String message, JavaClassTypes type) {
        super(title, message);
        this.type = type;
        /*
         * Type 1 is Class, Type 2 is Interface, Type 3 is Enums, Type 4 is Annotation
         */
    }

    @Override
    public void fileDialogBox(Stage window) {
        final FileChooser fileChooser = new FileChooser();

        final FileChooser.ExtensionFilter javaFilter = new FileChooser.ExtensionFilter("Java Files", "*.java");

        fileChooser.getExtensionFilters().add(javaFilter);
        final File file = fileChooser.showSaveDialog(window);
        if (file != null) {
            filePath = file.getAbsolutePath();
            pathName.setText(filePath);
        }
        fileChooser.setInitialDirectory(new File(""));

    }

    //TODO make it open the file in editor after saving
    @Override
    protected Button saveFile(Stage window) {
        return UIUtils.createButton(message, event -> {
            //please put this somewhere else in the event that the user fails to select a path
            if (filePath == null || filePath.equals("File Path")) {
                System.out.println("Input error");
                window.close();
                return;//failed to input
            }
            final Path path = get(filePath);
            if (exists(path)) {
                try {
                    createFile(path);
                    final String code;
                    switch (type) {
                        case CLASS:
                            code = "public class " + path.getFileName().toString().replace(".java", "") + "{ \n \n}";
                           // FileUtils.updateFile(file, code);
                            break;
                        case ENUM:
                            code = "public interface " + path.getFileName().toString().replace(".java", "") + "{ \n \n}";
                            //      FileUtils.updateFile(file, code);
                            break;
                        case INTERFACE:
                            code = "public enum " + path.getFileName().toString().replace(".java", "") + "{ \n \n}";
                            //  FileUtils.updateFile(file, code);
                            break;
                        default:
                            throw new IllegalStateException(type.name() + " is not a supported java file");
                    }
                    window.close();
                } catch (IOException reason) {
                    throw new RuntimeException(reason);
                }
            }
        });
    }

}
