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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import model.Patient;
import model.Treatment;
import view.tm.AddTreatTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class AddTreatmentManageController {
    public JFXButton btnTreatment;
    public JFXButton btnClear;
    public Label lblDate;
    public Label lblTime;
    public TextField txtPName;
    public TextField txtPAge;
    public TextField txtPContact;
    public ComboBox<String> cmbPatientIds;
    public ComboBox<String> cmbTreatIds;
    public TextField txtPMail;
    public TextField txtTreat;
    public TextField txtTFee;
    public TableView<AddTreatTM> tblTreatment;
    public TableColumn colPId;
    public TableColumn colPName;
    public TableColumn colPAge;
    public TableColumn colPContact;
    public TableColumn colTId;
    public TableColumn colTreatment;
    public TableColumn colCharge;
    public TableColumn colTDate;
    public TableColumn colTTime;
    public JFXDatePicker txtTrDate;
    public JFXTimePicker txtTrTime;


    public void initialize(){

        colPId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colPContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colTId.setCellValueFactory(new PropertyValueFactory<>("tId"));
        colTreatment.setCellValueFactory(new PropertyValueFactory<>("treat"));
        colTDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colCharge.setCellValueFactory(new PropertyValueFactory<>("fee"));

        try {

            loadDateAndTime();
            loadPatientIds();
            loadTreatmentIds();

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

        cmbTreatIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setTreatmentData(newValue);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        /*tblAppointment.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
        appointmentSelectedRowForRemove = (int) newValue;
    });*/

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

    private void setTreatmentData(String treatmentId) throws SQLException, ClassNotFoundException {
        Treatment treatment = new TreatmentController().getTreatment(treatmentId);
        if (treatment==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set").show();
        }else{
            txtTreat.setText(treatment.gettTreat());
            txtTFee.setText(String.valueOf(treatment.gettFee()));
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

    private void loadTreatmentIds() throws SQLException, ClassNotFoundException {
        List<String> treatmentIds = new AddTreatmentController().getTreatmentIds();
        cmbTreatIds.getItems().addAll(treatmentIds);
    }

    private void loadPatientIds() throws SQLException, ClassNotFoundException {
        List<String> patientIds = new AddTreatmentController().getPatientIds();
        cmbPatientIds.getItems().addAll(patientIds);
    }


    ObservableList<AddTreatTM> obList = FXCollections.observableArrayList();
    public void addTreatment_OnAction(ActionEvent actionEvent) {

        String patName = txtPName.getText();
        String patAge = txtPAge.getText();
        String patContact = txtPContact.getText();
        String treatment = txtTreat.getText();
        String tDate = txtTrDate.getEditor().getText();
        String tTime = txtTrTime.getEditor().getText();
        Double treatFee = Double.valueOf(txtTFee.getText());

        AddTreatTM addTreatTM = new AddTreatTM(
                cmbPatientIds.getValue(),
                patName,
                patAge,
                patContact,
                cmbTreatIds.getValue(),
                treatment,
                tDate,
                tTime,
                treatFee
        );

        obList.add(addTreatTM);
        tblTreatment.setItems(obList);




    }

    public void clearTreatment_OnAction(ActionEvent actionEvent) {
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
    }
}
