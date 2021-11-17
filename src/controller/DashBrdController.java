package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DashBrdController {
    public AnchorPane DashBrdContext;
    public AnchorPane buttonPane;
    public AnchorPane rootPane;
    public Label mainBar;
    public JFXButton patient;
    public JFXButton doctor;
    public JFXButton nurse;
    public JFXButton appointment;
    public JFXButton treatment;
    public JFXButton report;
    public JFXButton logOut;
    public Label lblDate;
    public Label lblTime;
    public JFXButton AppointmentView;
    public JFXButton helpView;
    public JFXButton addTreatment;

    public void initialize(){
        loadDateAndTime();
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

    public void manageDoctor_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/DoctorManageView.fxml"));
        pane = fxmlLoader.load();
        rootPane.getChildren().setAll(pane);

    }

    public void patientsMange_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/PatientManageView.fxml"));
        pane = fxmlLoader.load();
        rootPane.getChildren().setAll(pane);
    }

    public void manageNurse_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/NurseManageView.fxml"));
        pane = fxmlLoader.load();
        rootPane.getChildren().setAll(pane);
    }

    public void appointmentManage_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/AppointmentManageView.fxml"));
        pane = fxmlLoader.load();
        rootPane.getChildren().setAll(pane);
    }

    public void treatmentManage_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/TreatmentManageView.fxml"));
        pane = fxmlLoader.load();
        rootPane.getChildren().setAll(pane);
    }

    public void appointmentView_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/AppointmentView.fxml"));
        pane = fxmlLoader.load();
        rootPane.getChildren().setAll(pane);
    }

    public void addTreatment_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/AddTreatmentManage.fxml"));
        pane = fxmlLoader.load();
        rootPane.getChildren().setAll(pane);

    }

    public void logOut_OnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
