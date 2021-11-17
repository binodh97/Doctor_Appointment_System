package controller;

import db.DbConnection;
import model.Doctor;
import model.Treatment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TreatmentController {

    public static boolean saveTreatment(Treatment treatment) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO Treatment VALUES(?,?,?)");
        stm.setObject(1,treatment.gettId());
        stm.setObject(2,treatment.gettTreat());
        stm.setObject(3,treatment.gettFee());

        return stm.executeUpdate()>0;
    }

    public boolean updateTreatment(Treatment treatment) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE Treatment SET trTreat=?, trFee=? WHERE trId=? "
        );
        statement.setObject(1,treatment.gettTreat());
        statement.setObject(2,treatment.gettFee());
        statement.setObject(3,treatment.gettId());

        return statement.executeUpdate()>0;

    }

    public boolean deleteTreatment(String treatId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Treatment WHERE trId =" +
                "'"+treatId+"'").executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    public Treatment getTreatment(String treatId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM Treatment WHERE trId = ?");
        stm.setObject(1, treatId);
        ResultSet set = stm.executeQuery();
        if (set.next()) {
            return new Treatment(
                    set.getString(1),
                    set.getString(2),
                    set.getDouble(3)
            );
        }else {
            return null;
        }
    }

    public ArrayList<Treatment> getAllTreatment() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM Treatment");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Treatment> treatments = new ArrayList<>();
        while (resultSet.next()){
            treatments.add(new Treatment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)
            ));
        }
        return treatments;
    }

}
