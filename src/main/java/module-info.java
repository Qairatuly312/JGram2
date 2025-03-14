module JGram.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires jjwt;
    requires java.sql;

    opens jv.chat.FX_GUI to javafx.fxml; // Allow FXML to access your package
    exports jv.chat.FX_GUI;
}