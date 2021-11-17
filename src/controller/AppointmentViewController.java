package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Appointment;
import view.tm.AppointmentvTM;
import view.tm.ReceptionTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentViewController {

    public AnchorPane appointmentViewContext;
    public TableView<AppointmentvTM> tblAppointments;
    public TableColumn colAId;
    public TableColumn colPId;
    public TableColumn colPName;
    public TableColumn colDId;
    public TableColumn colDName;
    public TableColumn colNDate;
    public TableColumn colNTime;
    public TableColumn colADate;
    public TableColumn colATime;
    public TableColumn colCost;




    public void initialize(){
        colAId.setCellValueFactory(new PropertyValueFactory<>("apId"));
        colPId.setCellValueFactory(new PropertyValueFactory<>("idP"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("nameP"));
        colDId.setCellValueFactory(new PropertyValueFactory<>("idD"));
        colDName.setCellValueFactory(new PropertyValueFactory<>("nameD"));
        colNDate.setCellValueFactory(new PropertyValueFactory<>("dateN"));
        colNTime.setCellValueFactory(new PropertyValueFactory<>("timeN"));
        colADate.setCellValueFactory(new PropertyValueFactory<>("dateA"));
        colATime.setCellValueFactory(new PropertyValueFactory<>("timeA"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("ChargeD"));


        try {
            appointmentToTable(new AppointmentController().getAllAppointment());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void appointmentToTable(ArrayList<Appointment> allAppointment) {
        ObservableList<AppointmentvTM> appointmentList = FXCollections.observableArrayList();
        allAppointment.forEach(e->{
            appointmentList.add(new AppointmentvTM(e.getaId(),e.getPatId(),e.getPatName(),e.getDocId(),e.getDocName(),e.getnDate(),e.getnTime(),
                    e.getaDate(),e.getaTime(),e.getCost()));
        });
        tblAppointments.setItems(appointmentList);
    }

}
