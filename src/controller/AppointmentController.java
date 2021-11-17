package controller;

import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Appointment;
import model.AppointmentDetails;
import model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {


    public List<String> getPatientIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Patient ").executeQuery();
        List<String> pid = new ArrayList<>();
        while (rst.next()) {
            pid.add(
                    rst.getString(1)
            );
        }
        return pid;
    }

    public List<String> getDoctorIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Doctor ").executeQuery();
        List<String> did = new ArrayList<>();
        while (rst.next()) {
            did.add(
                    rst.getString(1)
            );
        }
        return did;
    }

    public boolean saveAppointment(Appointment appointment) {

        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.
                    prepareStatement("INSERT INTO Appointment VALUES (?,?,?,?,?,?,?,?,?,?)");

            stm.setObject(1, appointment.getaId());
            stm.setObject(2, appointment.getPatId());
            stm.setObject(3, appointment.getPatName());
            stm.setObject(4, appointment.getDocId());
            stm.setObject(5, appointment.getDocName());
            stm.setObject(6, appointment.getnDate());
            stm.setObject(7, appointment.getnTime());
            stm.setObject(8, appointment.getaDate());
            stm.setObject(9, appointment.getaTime());
            stm.setObject(10, appointment.getCost());


            if (stm.executeUpdate() > 0) {

                if (saveAppointmentDetails(appointment.getaId(), appointment.getAppointments())) {
                    con.commit();
                    return true;
                } else {
                    con.rollback();
                    return false;
                }

            } else {
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();


        } finally {
            try {

                con.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    private boolean saveAppointmentDetails(String aId, ArrayList<AppointmentDetails> appointments) throws SQLException, ClassNotFoundException {
        for (AppointmentDetails temp : appointments
        ) {
            PreparedStatement stm = DbConnection.getInstance().
                    getConnection().prepareStatement("INSERT INTO AppointmentDetails VALUES (?,?,?,?)");
            stm.setObject(1, temp.getApId());
            stm.setObject(2, temp.getPtId());
            stm.setObject(3, temp.getDate());
            stm.setObject(4, temp.getCharge());

            if (stm.executeUpdate() > 0) {
            } else {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Appointment> getAllAppointment() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM Appointment");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Appointment> appointments = new ArrayList<>();
        while (resultSet.next()){
            appointments.add(new Appointment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getDouble(10)
            ));
        }
        return appointments;
    }

}
