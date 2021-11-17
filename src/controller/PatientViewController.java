package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Patient;
import view.tm.PatientTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientViewController {
    public TableView<PatientTM> tblPatient;
    public TableColumn colPId;
    public TableColumn colPName;
    public TableColumn colPAge;
    public TableColumn colPGender;
    public TableColumn colPAddress;
    public TableColumn colPContact;
    public TableColumn colPMail;
    public JFXButton managePatient;
    public AnchorPane patientViewContext;

    public void initialize(){

        try {

            colPId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colPName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colPAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            colPGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colPAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colPContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            colPMail.setCellValueFactory(new PropertyValueFactory<>("mail"));

            patientToTable(new PatientController().getAllPatient());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void patientToTable(ArrayList<Patient> allPatient) {
        ObservableList<PatientTM> patientList = FXCollections.observableArrayList();
        allPatient.forEach(e -> {
            patientList.add(new PatientTM(e.getpId(),e.getpName(),e.getpAge(),e.getpGender(),e.getpAddress(),e.getpContact(),e.getpMail()));
        });
        tblPatient.setItems(patientList);

    }

    public void managePatient_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/PatientManageView.fxml"));
        pane = fxmlLoader.load();
        patientViewContext.getChildren().setAll(pane);
    }
}
