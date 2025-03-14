package jv.chat.FX_GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Left panel (Contacts)
        VBox leftPanel = new VBox(10);
        leftPanel.setPadding(new Insets(10));
        leftPanel.setPrefWidth(400);
        leftPanel.setStyle("-fx-background-color: #2c3e50;");

        Label userLabel = new Label("My Account");
        userLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        HBox userBox = new HBox(10, userLabel);
        userBox.setPadding(new Insets(10));

        ListView<String> contactsList = new ListView<>();
        contactsList.getItems().addAll(
                "Yerassyl Kairatuly",
                "Anchous Anchousovich",
                "Dias Maksatov",
                "PIZDEC DOLGOE IMYA YA V AHUE"
        );


        contactsList.setCellFactory(listView -> new ListCell<>() {
            private final Label nameLabel = new Label();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    nameLabel.setText(item);
                    nameLabel.maxWidthProperty().bind(listView.widthProperty().subtract(20));
                    nameLabel.setStyle("-fx-text-overrun: ellipsis;-fx-wrap-text: false;");

                    // Wrap Label in HBox to ensure width constraints apply
                    HBox cellContainer = new HBox(nameLabel);
                    HBox.setHgrow(nameLabel, Priority.ALWAYS);
                    nameLabel.setEllipsisString("...");

                    setGraphic(cellContainer);
                }
            }
        });

        contactsList.setPrefWidth(200);

        Button profileButton = new Button("Profile");
        profileButton.setPrefWidth(150);

        HBox buttonsBox = new HBox(5, profileButton]);
        buttonsBox.setPadding(new Insets(10));

        leftPanel.getChildren().addAll(userBox, contactsList, buttonsBox);

        // Right panel (Chat Area)
        VBox chatArea = new VBox();
        chatArea.setPadding(new Insets(10));
        chatArea.setStyle("-fx-background-color: #ecf0f1;");

        Label chatHeader = new Label(/*name of the account*/"someone i'm writing to");
        chatHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextArea chatHistory = new TextArea();
        chatHistory.setEditable(false);
        chatHistory.setPrefHeight(screenBounds.getHeight());
        chatHistory.setText("Adam: Hey Sarah\nSarah: Hi Adam");

        TextField messageInput = new TextField();
        messageInput.setPromptText("Type a message...");

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> {
            String message = messageInput.getText().trim();
            if (!message.isEmpty()) {
                chatHistory.appendText("\nYou: " + message);
                messageInput.clear();
            }
        });

        HBox messageBox = new HBox(5, messageInput, sendButton);
        messageBox.setPadding(new Insets(5));

        chatArea.getChildren().addAll(chatHeader, chatHistory, messageBox);

        // Main layout
        HBox mainLayout = new HBox(leftPanel, chatArea);
        Scene scene = new Scene(mainLayout);

        primaryStage.setOnCloseRequest(e -> {
            stop();

        });

        chatArea.setPrefHeight(screenBounds.getHeight());
        chatArea.setPrefWidth(screenBounds.getWidth());

        primaryStage.setFullScreen(true);
        primaryStage.setTitle("JavaFX Chat App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        Platform.exit();
        System.exit(0);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
