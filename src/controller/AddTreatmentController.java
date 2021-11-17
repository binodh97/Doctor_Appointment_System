package controller;

import db.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddTreatmentController {

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

    public List<String> getTreatmentIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Treatment ").executeQuery();
        List<String> tId = new ArrayList<>();
        while (rst.next()) {
            tId.add(
                    rst.getString(1)
            );
        }
        return tId;
    }


}
