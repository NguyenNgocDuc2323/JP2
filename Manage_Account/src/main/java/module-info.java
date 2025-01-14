module com.example.manage_account {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.manage_account to javafx.fxml;
    exports com.example.manage_account;
    exports controller;
    opens controller to javafx.fxml;
}