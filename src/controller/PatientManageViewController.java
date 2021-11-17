package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Patient;
import util.ValidationUtil;
import view.tm.PatientTM;

import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

public class PatientManageViewController {
    public AnchorPane patientContext;
    public JFXButton delete;
    public JFXButton update;
    public JFXButton save;
    public JFXButton search;
    public TableView<PatientTM> tblPatient;
    public TableColumn colPId;
    public TableColumn colPName;
    public TableColumn colPAge;
    public TableColumn colPGender;
    public TableColumn colPAddress;
    public TableColumn colPContact;
    public TableColumn colPMail;
    public TextField txtPId;
    public TextField txtPName;
    public TextField txtPAge;
    public TextField txtPGender;
    public TextField txtPAddress;
    public TextField txtPContact;
    public TextField txtPMail;
    public TextField txtPSearch;


    LinkedHashMap<TextField,Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(P00-)[0-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,30}$");
    Pattern agePattern = Pattern.compile("^[0-9]{1,3}$");
    Pattern genderPattern = Pattern.compile("^(M|F){1}$");
    Pattern addressPattern = Pattern.compile("^[A-z.0-9/, '']{6,70}$");
    Pattern contactPattern = Pattern.compile("^[0-9]{3}[-]?[0-9]{7}$");
    Pattern mailPattern = Pattern.compile("^[A-z0-9._]{3,}[0-9]*(@)[a-z]*(.com|lk|org)$");



    public void initialize(){

        try{

            colPId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colPName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colPAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            colPGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colPAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colPContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            colPMail.setCellValueFactory(new PropertyValueFactory<>("mail"));

            patientToTable(new PatientController().getAllPatient());

            storeValidations();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void storeValidations() {

        map.put(txtPId,idPattern);
        map.put(txtPName,namePattern);
        map.put(txtPAge,agePattern);
        map.put(txtPGender,genderPattern);
        map.put(txtPAddress,addressPattern);
        map.put(txtPContact,contactPattern);
        map.put(txtPMail,mailPattern);

    }

    public void patientToTable(ArrayList<Patient> allPatient) {
        ObservableList<PatientTM> patientList = FXCollections.observableArrayList();
        allPatient.forEach(e -> {
            patientList.add(new PatientTM(e.getpId(),e.getpName(),e.getpAge(),e.getpGender(),e.getpAddress(),e.getpContact(),e.getpMail()));
        });
        tblPatient.setItems(patientList);
    }

    //==================================================================================================================

    public void btnSavePatient_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Patient patient = new Patient(
                txtPId.getText(),
                txtPName.getText(),
                txtPAge.getText(),
                txtPGender.getText(),
                txtPAddress.getText(),
                txtPContact.getText(),
                txtPMail.getText()
        );

        if( PatientController.savePatient(patient)){
            cancelDetail();
            patientToTable(new PatientController().getAllPatient());
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
            cancelDetail();
        }
    }

    public void updatePatient_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Patient patient = new Patient(
                txtPId.getText(),
                txtPName.getText(),
                txtPAge.getText(),
                txtPGender.getText(),
                txtPAddress.getText(),
                txtPContact.getText(),
                txtPMail.getText()
        );

        if (new PatientController().updatePatient(patient)) {
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            cancelDetail();
            patientToTable(new PatientController().getAllPatient());

        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }
    }

    public void patientSearch_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String patientId = txtPSearch.getText();
        Patient patient = new PatientController().getPatient(patientId);
        if (patient==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set").show();
        }else{
            setData(patient);
        }
    }

    public void deletePatient_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to Delete?", yes, no);
        alert.setTitle("Confirmation alert");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            if (new PatientController().deletePatient(txtPId.getText())) ;
            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
            cancelDetail();
            patientToTable(new PatientController().getAllPatient());
        } else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
            cancelDetail();
        }
    }

    public void cancelDetailOnAction(ActionEvent actionEvent) {
        cancelDetail();
    }

    void setData(Patient patient) {
        txtPId.setText(patient.getpId());
        txtPName.setText(patient.getpName());
        txtPAge.setText(patient.getpAge());
        txtPGender.setText(patient.getpGender());
        txtPAddress.setText(patient.getpAddress());
        txtPContact.setText(patient.getpContact());
        txtPMail.setText(patient.getpMail());
    }

    private void cancelDetail() {
        txtPId.clear();
        txtPName.clear();
        txtPAge.clear();
        txtPGender.clear();
        txtPAddress.clear();
        txtPContact.clear();
        txtPMail.clear();
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

