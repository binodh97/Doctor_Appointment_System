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
import model.Doctor;
import util.ValidationUtil;
import view.tm.DoctorTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class DoctorManageViewController {
    public AnchorPane doctorContext;
    public JFXButton delete;
    public JFXButton update;
    public JFXButton save;
    public JFXButton clear;
    public TableView<DoctorTM> tblDoctor;
    public TableColumn colDId;
    public TableColumn colDName;
    public TableColumn colDAge;
    public TableColumn colDGender;
    public TableColumn colDAddress;
    public TableColumn colDContact;
    public TableColumn colDSpecialize;
    public TableColumn colDFees;

    public TextField txtDcId;
    public TextField txtDcName;
    public TextField txtDcAge;
    public TextField txtDcGender;
    public TextField txtDcAddress;
    public TextField txtDcContact;
    public TextField txtDcSpecialization;
    public TextField txtDcFees;
    public TextField txtDSearch;


    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(D00-)[0-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,30}$");
    Pattern agePattern = Pattern.compile("^[0-9]{1,3}$");
    Pattern genderPattern = Pattern.compile("^(M|F){1}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/, ]{6,40}$");
    Pattern contactPattern = Pattern.compile("^[0-9]{3}[-]?[0-9]{7}$");
    Pattern specializationPattern = Pattern.compile("^[A-z0-9/, ]{6,40}$");
    Pattern feePattern = Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");


    public void initialize(){
        try {
            colDId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colDName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colDAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            colDGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colDAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colDContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            colDSpecialize.setCellValueFactory(new PropertyValueFactory<>("specialize"));
            colDFees.setCellValueFactory(new PropertyValueFactory<>("fees"));

            doctorToTable(new DoctorController().getAllDoctor());

            storeValidations();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void storeValidations() {

        map.put(txtDcId,idPattern);
        map.put(txtDcName,namePattern);
        map.put(txtDcAge,agePattern);
        map.put(txtDcGender,genderPattern);
        map.put(txtDcAddress,addressPattern);
        map.put(txtDcContact,contactPattern);
        map.put(txtDcSpecialization,specializationPattern);
        map.put(txtDcFees,feePattern);

    }

    private void doctorToTable(ArrayList<Doctor> allDoctors) {
        ObservableList<DoctorTM> doctorList = FXCollections.observableArrayList();
        allDoctors.forEach(e->{
            doctorList.add(new DoctorTM(e.getdId(),e.getdName(),e.getdAge(),e.getdGender(),e.getdAddress(),e.getdContact(),e.getdSpecialize(),
                    e.getdFees()));
        });
        tblDoctor.setItems(doctorList);

    }


    //=========================================================================================================

    public void btnSaveDoctor_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Doctor doctor = new Doctor(
                txtDcId.getText(),
                txtDcName.getText(),
                txtDcAge.getText(),
                txtDcGender.getText(),
                txtDcAddress.getText(),
                txtDcContact.getText(),
                txtDcSpecialization.getText(),
                Double.parseDouble(txtDcFees.getText())
        );

        if( DoctorController.saveDoctor(doctor)){
            clearDetails();
            doctorToTable(new DoctorController().getAllDoctor());
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }

    }

    public void btnUpdateDoctor_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Doctor doctor = new Doctor(
                txtDcId.getText(),
                txtDcName.getText(),
                txtDcAge.getText(),
                txtDcGender.getText(),
                txtDcAddress.getText(),
                txtDcContact.getText(),
                txtDcSpecialization.getText(),
                Double.parseDouble(txtDcFees.getText())
        );

        if (new DoctorController().updateDoctor(doctor)){
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            clearDetails();
            doctorToTable(new DoctorController().getAllDoctor());
        }else {
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }

    }

    public void searchDoctor_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String doctorId = txtDSearch.getText();
        Doctor doctor = new DoctorController().getDoctor(doctorId);
        if (doctor==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set").show();
        }else{
            setData(doctor);
        }
    }

    public void btnDeleteDoctor_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to Delete?", yes, no);
        alert.setTitle("Confirmation alert");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.orElse(no) == yes) {
            if (new DoctorController().deleteDoctor(txtDcId.getText())) ;
                    new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                    doctorToTable(new DoctorController().getAllDoctor());
        } else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }
        clearDetails();
    }

    public void btnClearDetails_OnAction(ActionEvent actionEvent) {
        clearDetails();
    }

    void setData(Doctor doctor){
        txtDcId.setText(doctor.getdId());
        txtDcName.setText(doctor.getdName());
        txtDcAge.setText(doctor.getdAge());
        txtDcGender.setText(doctor.getdGender());
        txtDcAddress.setText(doctor.getdAddress());
        txtDcContact.setText(doctor.getdContact());
        txtDcSpecialization.setText(doctor.getdSpecialize());
        txtDcFees.setText(String.valueOf(doctor.getdFees()));
    }

    private void clearDetails() {
        txtDcId.clear();
        txtDcName.clear();
        txtDcAge.clear();
        txtDcGender.clear();
        txtDcAddress.clear();
        txtDcContact.clear();
        txtDcSpecialization.clear();
        txtDcFees.clear();
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
