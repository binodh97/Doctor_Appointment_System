package controller;

import com.jfoenix.controls.JFXButton;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DurationDV;
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

public class AdminHomePageController {
    public AnchorPane AdminHomePageContext;
    public Label mainBar;
    public AnchorPane buttonPane;
    public JFXButton patientView;
    public JFXButton doctorView;
    public JFXButton nurseView;
    public JFXButton appointmentView;
    public JFXButton logOut;
    public JFXButton back;
    public Label lblDate;
    public Label lblTime;
    public JFXButton TreatmentView;
    public AnchorPane MainPane;
    public JFXButton receptionManage;
    public JFXButton helpView;
    public JFXButton reportView;

    public void initialize(){
        loadDateAndTime();
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO,e->{
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

    public void patientView_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/PatientView.fxml"));
        pane = fxmlLoader.load();
        MainPane.getChildren().setAll(pane);

    }

    public void doctorView_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/DoctorView.fxml"));
        pane = fxmlLoader.load();
        MainPane.getChildren().setAll(pane);

    }

    public void nurseView_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/NurseView.fxml"));
        pane = fxmlLoader.load();
        MainPane.getChildren().setAll(pane);
    }

    public void appointmentView_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/AppointmentView.fxml"));
        pane = fxmlLoader.load();
        MainPane.getChildren().setAll(pane);

    }

    public void treatmentView_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/TreatmentView.fxml"));
        pane = fxmlLoader.load();
        MainPane.getChildren().setAll(pane);

    }

    public void receptionManage_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/ReceptionManage.fxml"));
        pane = fxmlLoader.load();
        MainPane.getChildren().setAll(pane);

    }


    public void btnBack_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/LogInPage.fxml"));
        pane = fxmlLoader.load();
        AdminHomePageContext.getChildren().setAll(pane);
    }


    public void logOut_OnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
