package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import model.LogIn;
import model.Reception;
import util.ValidationUtil;
import view.tm.ReceptionTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class ReceptionManageController {

    public AnchorPane receptionManage;
    public TextField txtRId;
    public TextField txtRName;
    public TextField txtRAge;
    public TextField txtRGender;
    public TextField txtRAddress;
    public TextField txtRContact;
    public TextField txtRMail;
    public TextField txtRSearch;
    public JFXButton cancel;
    public JFXButton delete;
    public JFXButton update;
    public JFXButton save;
    public TextField txtRUserName;
    public TextField txtRPassword;
    public TableView<ReceptionTM> tblReception;
    public TableColumn colRId;
    public TableColumn colRName;
    public TableColumn colRAge;
    public TableColumn colRGender;
    public TableColumn colRAddress;
    public TableColumn colRContact;
    public TableColumn colRMail;
    public TableColumn colRUserName;
    public TableColumn colRPassword;


    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(R00-)[0-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,30}$");
    Pattern agePattern = Pattern.compile("^[0-9]{1,3}$");
    Pattern genderPattern = Pattern.compile("^(M|F){1}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/, ]{6,40}$");
    Pattern contactPattern = Pattern.compile("^[0-9]{3}[-]?[0-9]{7}$");
    Pattern mailPattern = Pattern.compile("^[A-z0-9._]{3,}[0-9]*(@)[a-z]*(.com|lk|org)$");
    Pattern userNamePattern = Pattern.compile("^[A-z0-9/,@ ]{3,30}$");
    Pattern passwordPattern = Pattern.compile("^[A-z0-9/,@ ]{4,15}$");



    public void initialize(){

        try {

            colRId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colRName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colRAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            colRGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colRAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colRContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            colRMail.setCellValueFactory(new PropertyValueFactory<>("mail"));

            receptionToTable(new ReceptionController().getAllReception());

            storeValidations();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void storeValidations() {

        map.put(txtRId,idPattern);
        map.put(txtRName,namePattern);
        map.put(txtRAge,agePattern);
        map.put(txtRGender,genderPattern);
        map.put(txtRAddress,addressPattern);
        map.put(txtRContact,contactPattern);
        map.put(txtRMail,mailPattern);
        map.put(txtRUserName,userNamePattern);
        map.put(txtRPassword,passwordPattern);
    }

    private void receptionToTable(ArrayList<Reception> allReceptions) {
        ObservableList<ReceptionTM> receptionList = FXCollections.observableArrayList();
        allReceptions.forEach(e->{
            receptionList.add(new ReceptionTM(e.getrId(),e.getrName(),e.getrAge(),e.getrGender(),e.getrAddress(),e.getrContact(),e.getrMail()));
        });
        tblReception.setItems(receptionList);
    }

    //=========================================================================================================

    public void btnSaveReception_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Reception reception = new Reception(
                txtRId.getText(),
                txtRName.getText(),
                txtRAge.getText(),
                txtRGender.getText(),
                txtRAddress.getText(),
                txtRContact.getText(),
                txtRMail.getText()
        );

        LogIn logIn = new LogIn(
                txtRId.getText(),
                txtRUserName.getText(),
                new String(Base64.getEncoder().encode(txtRPassword.getText().getBytes())),
                enums.LogIn.RECEPTION.toString()
        );

        if( ReceptionController.saveReception(reception)){
            if (new LogInController().saveReceptionLogin(logIn))
            new Alert(Alert.AlertType.CONFIRMATION,"Saved Successfully").show();
            clearDetails();
            receptionToTable(new ReceptionController().getAllReception());
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }

    }

    public void btnUpdateReception_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Reception reception = new Reception(
                txtRId.getText(),
                txtRName.getText(),
                txtRAge.getText(),
                txtRGender.getText(),
                txtRAddress.getText(),
                txtRContact.getText(),
                txtRMail.getText()
        );

        if (new ReceptionController().updateReception(reception)){
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            clearDetails();
            receptionToTable(new ReceptionController().getAllReception());
        }else {
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }

    }

    public void receptionSearch_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String receptionId = txtRSearch.getText();
        Reception reception = new ReceptionController().getReception(receptionId);
        if (reception==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set").show();
        }else{
            setData(reception);
        }
    }

    public void btnDeleteReception_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to Delete?", yes, no);
        alert.setTitle("Confirmation alert");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.orElse(no) == yes) {
            if (new ReceptionController().deleteReception(txtRId.getText())) ;
            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
            receptionToTable(new ReceptionController().getAllReception());
        } else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }
        clearDetails();

    }

    public void btnCancelDetailOnAction(ActionEvent actionEvent) {
        clearDetails();
    }

    private void clearDetails() {
       txtRId.clear();
       txtRName.clear();
       txtRAge.clear();
       txtRGender.clear();
       txtRAddress.clear();
       txtRContact.clear();
       txtRMail.clear();
       txtRUserName.clear();
       txtRPassword.clear();
    }

    void setData(Reception reception) {
        txtRId.setText(reception.getrId());
        txtRName.setText(reception.getrName());
        txtRAge.setText(reception.getrAge());
        txtRGender.setText(reception.getrGender());
        txtRAddress.setText(reception.getrAddress());
        txtRContact.setText(reception.getrContact());
        txtRMail.setText(reception.getrMail());
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

        Object response = ValidationUtil.validate(map,save);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField){
                TextField errorTextFiled = (TextField) response;
                errorTextFiled.requestFocus();
            }else if (response instanceof Boolean){
                new Alert(Alert.AlertType.INFORMATION,"Saved Successfully").showAndWait();
            }
        }
    }
}
