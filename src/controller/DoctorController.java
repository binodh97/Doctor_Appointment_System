package controller;

import db.DbConnection;
import model.Doctor;
import model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorController {

    public static boolean saveDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO Doctor VALUES(?,?,?,?,?,?,?,?)");
        stm.setObject(1,doctor.getdId());
        stm.setObject(2,doctor.getdName());
        stm.setObject(3,doctor.getdAge());
        stm.setObject(4,doctor.getdGender());
        stm.setObject(5,doctor.getdAddress());
        stm.setObject(6,doctor.getdContact());
        stm.setObject(7,doctor.getdSpecialize());
        stm.setObject(8,doctor.getdFees());

        return stm.executeUpdate()>0;
    }

    public boolean updateDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE Doctor SET dName=?, dAge=?, dGender =?, dAddress=?, dContact=?, dSpecialize=?, dFees=? WHERE dId=? "
        );
        statement.setObject(1,doctor.getdName());
        statement.setObject(2,doctor.getdAge());
        statement.setObject(3,doctor.getdGender());
        statement.setObject(4,doctor.getdAddress());
        statement.setObject(5,doctor.getdContact());
        statement.setObject(6,doctor.getdSpecialize());
        statement.setObject(7,doctor.getdFees());
        statement.setObject(8,doctor.getdId());

        return statement.executeUpdate()>0;

    }

    public boolean deleteDoctor(String doctorId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Doctor WHERE dId =" +
                "'"+doctorId+"'").executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    public Doctor getDoctor(String doctorId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM Doctor WHERE dId = ?");
        stm.setObject(1, doctorId);
        ResultSet set = stm.executeQuery();
        if (set.next()) {
            return new Doctor(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6),
                    set.getString(7),
                    set.getDouble(8)
            );
        }else {
            return null;
        }
    }


    public ArrayList<Doctor> getAllDoctor() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM Doctor");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Doctor> doctors = new ArrayList<>();
        while (resultSet.next()){
            doctors.add(new Doctor(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getDouble(8)
            ));
        }
        return doctors;
    }
}
