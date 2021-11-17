package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Patient;
import view.tm.PatientTM;

import java.sql.*;
import java.util.ArrayList;

public class PatientController {

    public static boolean savePatient(Patient patient) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO Patient VALUES(?,?,?,?,?,?,?)");
        stm.setObject(1,patient.getpId());
        stm.setObject(2,patient.getpName());
        stm.setObject(3,patient.getpAge());
        stm.setObject(4,patient.getpGender());
        stm.setObject(5,patient.getpAddress());
        stm.setObject(6,patient.getpContact());
        stm.setObject(7,patient.getpMail());

        return stm.executeUpdate()>0;
    }

    public Patient getPatient(String patientId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM Patient WHERE pId = ?");
        stm.setObject(1, patientId);
        ResultSet set = stm.executeQuery();
        if (set.next()) {
            return new Patient(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6),
                    set.getString(7)
            );
        }else {
            return null;
        }
    }

    public boolean deletePatient(String patientId) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Patient WHERE pId=" +
                "'"+patientId+"'").executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean updatePatient(Patient patient) throws SQLException, ClassNotFoundException {
        PreparedStatement stat = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Patient SET pName=?,pAge=?,pGender=?,pAddress=?,pContact=?,pMail=? WHERE pId=? ");

        stat.setObject(1,patient.getpName());
        stat.setObject(2,patient.getpAge());
        stat.setObject(3,patient.getpGender());
        stat.setObject(4,patient.getpAddress());
        stat.setObject(5,patient.getpContact());
        stat.setObject(6,patient.getpMail());
        stat.setObject(7,patient.getpId());

        return stat.executeUpdate()>0;
        
    }

    public ArrayList<Patient> getAllPatient() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM Patient");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Patient> patients = new ArrayList<>();
        while (resultSet.next()){
            patients.add(new Patient(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return patients;
    }


}
