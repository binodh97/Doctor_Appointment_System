package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Appointment;
import model.AppointmentDetails;
import model.Doctor;
import model.Patient;
import view.tm.AppointmentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentManageViewController {
    public TextField txtPName;
    public TextField txtPAge;
    public TextField txtPContact;
    public TextField txtPMail;
    public TextField txtDName;
    public TextField txtDFees;
    public TextField txtDSpecialize;
    public TextField txtAId;
    public JFXDatePicker txtApDate;
    public JFXTimePicker txtApTime;
    public ComboBox<String> cmbPatientIds;
    public ComboBox<String> cmbDoctorIds;
    public TableView<AppointmentTM> tblAppointment;
    public TableColumn colPId;
    public TableColumn colPName;
    public TableColumn colPContact;
    public TableColumn colAId;
    public TableColumn colADate;
    public TableColumn colATime;
    public TableColumn colDId;
    public TableColumn colDName;
    public TableColumn colCharges;
    public JFXButton sendMail;
    public JFXButton btnAppointment;
    public JFXButton btnClear;


    public Label txtTotal;
    public Label lblDate;
    public Label lblTime;



    int appointmentSelectedRowForRemove = -1;

    public void initialize(){

        colPId.setCellValueFactory(new PropertyValueFactory<>("pId"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("nameP"));
        colPContact.setCellValueFactory(new PropertyValueFactory<>("contactP"));
        colAId.setCellValueFactory(new PropertyValueFactory<>("apId"));
        colADate.setCellValueFactory(new PropertyValueFactory<>("dateA"));
        colATime.setCellValueFactory(new PropertyValueFactory<>("timeA"));
        colDId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDName.setCellValueFactory(new PropertyValueFactory<>("nameD"));
        colCharges.setCellValueFactory(new PropertyValueFactory<>("chargeD"));



        try {
            loadPatientIds();
            loadDoctorIds();
            loadDateAndTime();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        /*============================================*/

        cmbPatientIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setPatientData(newValue);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbDoctorIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setDoctorData(newValue);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });


        tblAppointment.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            appointmentSelectedRowForRemove = (int) newValue;
        });


        /*============================================*/

    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime =  LocalTime.now();
            lblTime.setText(
                    currentTime.getHour()+" : "+currentTime.getMinute()+" : "+currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private void setDoctorData(String doctorId) throws SQLException, ClassNotFoundException {

        Doctor doctor = new DoctorController().getDoctor(doctorId);
        if (doctor==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set").show();
        }else {
            txtDName.setText(doctor.getdName());
            txtDSpecialize.setText(doctor.getdSpecialize());
            txtDFees.setText(String.valueOf(doctor.getdFees()));
        }
    }

    private void setPatientData(String patientId) throws SQLException, ClassNotFoundException {

        Patient patient = new PatientController().getPatient(patientId);
        if (patient==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set").show();
        }else{
            txtPName.setText(patient.getpName());
            txtPAge.setText(patient.getpAge());
            txtPContact.setText(patient.getpContact());
            txtPMail.setText(patient.getpMail());
        }

    }

    private void loadPatientIds() throws SQLException, ClassNotFoundException {

        List<String> patientIds = new AppointmentController().getPatientIds();
        cmbPatientIds.getItems().addAll(patientIds);

    }

    private void loadDoctorIds() throws SQLException, ClassNotFoundException {

        List<String> doctorIds = new AppointmentController().getDoctorIds();
        cmbDoctorIds.getItems().addAll(doctorIds);

    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
    }

    public void sendMail_OnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MailingView.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }



    ObservableList<AppointmentTM> obList = FXCollections.observableArrayList();
    public void addAppointment_OnAction(ActionEvent actionEvent) {

        String patId = cmbPatientIds.getValue();
        String patName = txtPName.getText();
        String patContact = txtPContact.getText();
        String aId = txtAId.getText();
        String aDate = txtApDate.getEditor().getText();
        String aTime = txtApTime.getEditor().getText();
        String drName = txtDName.getText();
        Double drCharges = Double.parseDouble(txtDFees.getText());

        AppointmentTM appointmentTM = new AppointmentTM(

             patId,
             patName,
             patContact,
             aId,
             aDate,
             aTime,
             cmbDoctorIds.getValue(),
             drName,
             drCharges
        );

        int rowNumber = isExists(appointmentTM);


        if (isExists(appointmentTM)==-1){
            obList.add(appointmentTM);
        }else {
            AppointmentTM temp = obList.get(rowNumber);
            AppointmentTM newTM = new AppointmentTM(
                    temp.getpId(),
                    temp.getNameP(),
                    temp.getContactP(),
                    temp.getApId(),
                    temp.getDateA(),
                    temp.getTimeA(),
                    temp.getId(),
                    temp.getNameD(),
                    temp.getChargeD()
            );
            obList.remove(rowNumber);
            obList.add(newTM);
        }


        tblAppointment.setItems(obList);
        payments();

    }
    
    private int isExists(AppointmentTM atm){

        for (int i = 0; i < obList.size(); i++) {
            if (atm.getId().equals(obList.get(i).getId())){
                return i;
            }
        }
        return  -1;
    }

    void payments(){
        double total=0;
        for (AppointmentTM appointmentTM:obList
             ) {
            total+=appointmentTM.getChargeD();
        }
        txtTotal.setText(total+"/=");
    }

    public void clearAppointment_OnAction(ActionEvent actionEvent) {
        if (appointmentSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select a Row").show();
        }else {
            obList.remove(appointmentSelectedRowForRemove);
            payments();
            tblAppointment.refresh();
        }

    }

    public void saveAppointment_OnAction(ActionEvent actionEvent) {
        ArrayList<AppointmentDetails> appointments = new ArrayList<>();
        for (AppointmentTM tempTM:obList
             ) {
                appointments.add(
                        new AppointmentDetails(
                                tempTM.getApId(),
                                tempTM.getpId(),
                                tempTM.getDateA(),
                                tempTM.getChargeD()
                        )
                );
        }

        Appointment appointment = new Appointment(
                txtAId.getText(),
                cmbPatientIds.getValue(),
                txtPName.getText(),
                cmbDoctorIds.getValue(),
                txtDName.getText(),
                lblDate.getText(),
                lblTime.getText(),
                txtApDate.getEditor().getText(),
                txtApTime.getEditor().getText(),
                Double.parseDouble(txtDFees.getText()),
                appointments
        );

        if (new AppointmentController().saveAppointment(appointment)){
            new Alert(Alert.AlertType.CONFIRMATION,"Save Successfully").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    public void searchAppointments_OnAction(ActionEvent actionEvent) {

    }
}
