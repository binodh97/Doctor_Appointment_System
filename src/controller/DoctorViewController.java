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
import model.Doctor;
import view.tm.DoctorTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorViewController {
    public AnchorPane doctorViewContext;
    public TableView<DoctorTM> tblDoctor;
    public TableColumn colDId;
    public TableColumn colDName;
    public TableColumn colDAge;
    public TableColumn colDGender;
    public TableColumn colDAddress;
    public TableColumn colDContact;
    public TableColumn colDSpecialize;
    public TableColumn colDFees;
    public JFXButton manageDoctor;

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void doctorToTable(ArrayList<Doctor> allDoctors) {

        ObservableList<DoctorTM> doctorList = FXCollections.observableArrayList();
        allDoctors.forEach(e->{
            doctorList.add(new DoctorTM(e.getdId(),e.getdName(),e.getdAge(),e.getdGender(),e.getdAddress(),e.getdContact(),e.getdSpecialize(),
                    e.getdFees()));
        });
        tblDoctor.setItems(doctorList);

    }

    //==================================================================================================================


    public void manageDoctor_OnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/DoctorManageView.fxml"));
        pane = fxmlLoader.load();
        doctorViewContext.getChildren().setAll(pane);

    }
}
