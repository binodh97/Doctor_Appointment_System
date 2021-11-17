package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.LogIn;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

public class LogInPageController {

    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public AnchorPane MainContext;

    public void LogIn_OnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        String userName = txtUserName.getText();
        String password = new String(Base64.getEncoder().encode(txtPassword.getText().getBytes()));


        LogIn logIn = new LogInController().getUser(userName,password);

        if (logIn!=null){
            if (logIn.getRole().equals("ADMIN")){
                AnchorPane pane;
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/AdminHomePage.fxml"));
                pane = fxmlLoader.load();
                MainContext.getChildren().setAll(pane);
            }else if (logIn.getRole().equals("RECEPTION")){
                AnchorPane pane;
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/DashBrd.fxml"));
                pane = fxmlLoader.load();
                MainContext.getChildren().setAll(pane);
            }else {
                new Alert(Alert.AlertType.WARNING,"Invalid User Name or Password.");
                txtPassword.clear();
                txtUserName.clear();
            }
        }
    }



    public void moveToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

}
