package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddAccountController {

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_password;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_cancel;

    @FXML
    void onSaveAccount() {
        String email = txt_email.getText();
        String password = txt_password.getText();
        System.out.println("New Account: " + email + " - " + password);
        btn_save.getScene().getWindow().hide();
    }

    @FXML
    void onCancel() {
        btn_cancel.getScene().getWindow().hide();
    }

}
