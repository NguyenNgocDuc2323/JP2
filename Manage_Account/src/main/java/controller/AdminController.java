package controller;

import helper.AccountManager;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Account;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
public class AdminController implements Initializable {
    @FXML
    private Button btn_add;

    @FXML
    private Button btn_log;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_reset;

    @FXML
    private Button btn_un_log;

    @FXML
    private TableView<Account> tbl_account;

    @FXML
    private TableColumn<Account, String> tbl_email;

    @FXML
    private TableColumn<Account, String> tbl_password;

    @FXML
    private TableColumn<Account, String> tbl_status;

    @FXML
    private TableColumn<Account, String> tbl_type;

    ObservableList<Account> accountList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountList = FXCollections.observableArrayList(AccountManager.getInstance().getAccounts());
        tbl_account.setItems(accountList);
        tbl_email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        tbl_password.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        tbl_type.setCellValueFactory(cellData -> cellData.getValue().typeAsStringProperty());
        tbl_status.setCellValueFactory(cellData -> cellData.getValue().lockedAsStringProperty());

        tbl_account.setRowFactory(tv -> {
            TableRow<Account> row = new TableRow<>();
            row.itemProperty().addListener((obs, oldItem, newItem) -> {
                if (newItem != null && newItem.isLocked()) {
                    row.setStyle("-fx-background-color: #eee;-fx-text-fill: #fff");
                } else {
                    row.setStyle("");
                }
            });
            return row;
        });
    }


    @FXML
    void onAddAccount(ActionEvent event) {
        Account newAccount = new Account("test01@email.com", "password123", Account.TYPE_STAFF, false);
        AccountManager.getInstance().addAccount(newAccount);
        accountList.add(newAccount);
    }

    @FXML
    void onLockAccount(ActionEvent event) {
        Account account = tbl_account.getSelectionModel().getSelectedItem();
        if (account == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Account Selected");
            alert.setContentText("Please select an account first");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setContentText("Are you sure you want to Lock this account?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                account.setLocked(true);
                System.out.println("Locked account successfully");
                tbl_account.refresh();
            } else {
                System.out.println("Unlocked account failed");
            }
        }
    }

    @FXML
    void unLockAccount(ActionEvent event) {
        Account account = tbl_account.getSelectionModel().getSelectedItem();
        if (account == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Account Selected");
            alert.setContentText("Please select an account first");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setContentText("Are you sure you want to Unlock this account?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                account.setLocked(false);
                System.out.println("Unlocked account successfully");
            }
            else {
                System.out.println("Unlocked account failed");
            }
        }
    }
}
