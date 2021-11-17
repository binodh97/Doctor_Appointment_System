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
import model.Treatment;
import util.ValidationUtil;
import view.tm.TreatmentTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class TreatmentManageViewController {
    public AnchorPane TreatmentContext;
    public TextField txtTId;
    public TextField txtTreat;
    public TextField txtTFee;
    public JFXButton clear;
    public JFXButton delete;
    public JFXButton update;
    public JFXButton save;
    public TableView<TreatmentTM> tblTreatment;
    public TableColumn colTId;
    public TableColumn colTreatment;
    public TableColumn colTFee;
    public TextField txtTrSearch;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(T00-)[0-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,30}$");
    Pattern feePattern = Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");


    public void initialize(){
        try {

            colTId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colTreatment.setCellValueFactory(new PropertyValueFactory<>("treat"));
            colTFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

            treatToTable(new TreatmentController().getAllTreatment());
            storeValidations();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void storeValidations() {

        map.put(txtTId,idPattern);
        map.put(txtTreat,namePattern);
        map.put(txtTFee,feePattern);
    }

    private void treatToTable(ArrayList<Treatment> allTreatments) {
        ObservableList<TreatmentTM> treatList = FXCollections.observableArrayList();
        allTreatments.forEach(e->{
            treatList.add(new TreatmentTM(e.gettId(),e.gettTreat(),
                    e.gettFee()));
        });
        tblTreatment.setItems(treatList);

    }

    //=========================================================================================================


    public void treatSearch_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String treatId = txtTrSearch.getText();
        Treatment treatment = new TreatmentController().getTreatment(treatId);
        if (treatment==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set").show();
        }else{
            setData(treatment);
        }
    }

    public void btnClearDetail_OnAction(ActionEvent actionEvent) {
        clearDetails();
    }

    public void btnDeleteTreat_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to Delete?", yes, no);
        alert.setTitle("Confirmation alert");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.orElse(no) == yes) {
            if (new TreatmentController().deleteTreatment(txtTId.getText())) ;
            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
            treatToTable(new TreatmentController().getAllTreatment());
        } else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }
        clearDetails();

    }

    public void btnUpdateTreat_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Treatment treatment = new Treatment(
                txtTId.getText(),
                txtTreat.getText(),
                Double.parseDouble(txtTFee.getText())
        );

        if (new TreatmentController().updateTreatment(treatment)){
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            clearDetails();
            treatToTable(new TreatmentController().getAllTreatment());
        }else {
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }

    }

    public void btnSaveTreat_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Treatment treatment = new Treatment(
                txtTId.getText(),
                txtTreat.getText(),
                Double.parseDouble(txtTFee.getText())
        );

        if( TreatmentController.saveTreatment(treatment)){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved Successfully").show();
            clearDetails();
            treatToTable(new TreatmentController().getAllTreatment());
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }

    }

    void setData(Treatment treatment){
        txtTId.setText(treatment.gettId());
        txtTreat.setText(treatment.gettTreat());
        txtTFee.setText(String.valueOf(treatment.gettFee()));
    }

    private void clearDetails() {
        txtTId.clear();
        txtTreat.clear();
        txtTFee.clear();
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
