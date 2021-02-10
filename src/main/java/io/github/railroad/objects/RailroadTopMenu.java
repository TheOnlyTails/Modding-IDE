package io.github.railroad.objects;

import io.github.railroad.config.LanguageConfig;
import io.github.railroad.utility.UIUtils;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class RailroadTopMenu extends MenuBar {
    private final LanguageConfig langConfig;

    public RailroadTopMenu(LanguageConfig langConfigIn, Menu... menus) {
        super(menus);
        langConfig = langConfigIn;
        createMenu();
    }

    public void createMenu() {
        final Menu fileMenu = new Menu(langConfig.get("menu.file"));
        createFileNewMenu(fileMenu);

        final Menu editMenu = new Menu(langConfig.get("menu.edit"));
        createEditMenu(editMenu);

        final Menu searchMenu = new Menu(langConfig.get("menu.search"));
        createSearchMenu(searchMenu);

        final Menu runMenu = new Menu(langConfig.get("menu.run"));
        final Menu viewMenu = new Menu(langConfig.get("menu.view"));
        final Menu helpMenu = new Menu(langConfig.get("menu.help"));
        getMenus().addAll(fileMenu, editMenu, searchMenu, runMenu, viewMenu, helpMenu);
    }

    // TODO: Make proper textures for all these icons. Currently all just programmer
    // art! ;)
    public void createFileNewMenu(Menu fileMenu) {
        final Menu newMenu = new Menu(langConfig.get("menu.file.new"));

        final MenuItem javaProjectItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.new.javaproject"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem projectItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.new.project"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/project.png")).build();

        final MenuItem javaWorkingSetItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.new.javaworkingset"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/working_set.png")).build();

        final MenuItem packageItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.new.package"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/package.png")).build();

        final MenuItem sourceFolderItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.new.sourcefolder"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/source_folder.png")).build();

        final MenuItem file = RailroadMenuItem.Builder.create(langConfig.get("menu.file.new.file"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/file.png"))
                .setActionEvent(event -> new CreateNewFileWindow("Create New File", "Done")).build();

        final MenuItem folderItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.new.folder"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/folder.png")).build();

        final MenuItem clazzItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.new.class"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/class.png"))
                .setActionEvent(event -> new CreateNewJavaFile("Create New Class", "Done", JavaClassTypes.CLASS)).build();

        final MenuItem interfaceItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.new.interface"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/interface.png"))
                .setActionEvent(event -> new CreateNewJavaFile("Create New Interface", "Done", JavaClassTypes.INTERFACE)).build();

        final MenuItem enumItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.new.enum"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/enum.png"))
                .setActionEvent(event -> new CreateNewJavaFile("Create New Enum", "Done", JavaClassTypes.ENUM)).build();

        final MenuItem annotationItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.new.annotation"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/annotation.png")).build();

        newMenu.getItems().addAll(javaProjectItem, projectItem, javaWorkingSetItem, new SeparatorMenuItem(),
                sourceFolderItem, packageItem, file, folderItem, new SeparatorMenuItem(), clazzItem, interfaceItem, enumItem,
                annotationItem);

        fileMenu.getItems().add(newMenu);
    }

    // TODO: pls can someone do assets
    public void createEditMenu(Menu editMenu) {
        final MenuItem undo = RailroadMenuItem.Builder.create(langConfig.get("menu.edit.undo"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem redo = RailroadMenuItem.Builder.create(langConfig.get("menu.edit.redo"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem cut = RailroadMenuItem.Builder.create(langConfig.get("menu.edit.cut"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem copy = RailroadMenuItem.Builder.create(langConfig.get("menu.edit.copy"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem paste = RailroadMenuItem.Builder.create(langConfig.get("menu.edit.paste"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem delete = RailroadMenuItem.Builder.create(langConfig.get("menu.edit.delete"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem selectAll = RailroadMenuItem.Builder.create(langConfig.get("menu.edit.selectAll"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem findReplace = RailroadMenuItem.Builder.create(langConfig.get("menu.edit.findReplace"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        editMenu.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(), delete,
                selectAll, new SeparatorMenuItem(), findReplace);
    }

    public void createSearchMenu(Menu searchMenu) {
        final MenuItem search = RailroadMenuItem.Builder.create(langConfig.get("menu.search.search"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        final Menu textMenu = new Menu(langConfig.get("menu.search.text"));

        final MenuItem workspace = RailroadMenuItem.Builder.create(langConfig.get("menu.search.text.workspace"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem project = RailroadMenuItem.Builder.create(langConfig.get("menu.search.text.project"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem file = RailroadMenuItem.Builder.create(langConfig.get("menu.search.text.file"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        searchMenu.getItems().addAll(search, new SeparatorMenuItem(), textMenu);
        textMenu.getItems().addAll(workspace, project, file);
    }
}
