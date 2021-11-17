package controller;

import db.DbConnection;
import model.Doctor;
import model.Reception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReceptionController {

    public static boolean saveReception(Reception reception) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO Reception VALUES(?,?,?,?,?,?,?)");
        stm.setObject(1,reception.getrId());
        stm.setObject(2,reception.getrName());
        stm.setObject(3,reception.getrAge());
        stm.setObject(4,reception.getrGender());
        stm.setObject(5,reception.getrAddress());
        stm.setObject(6,reception.getrContact());
        stm.setObject(7,reception.getrMail());

        return stm.executeUpdate()>0;
    }

    public boolean updateReception(Reception reception) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE Reception SET rName=?, rAge=?, rGender =?, rAddress=?, rContact=?,rMail=? WHERE rId=? "
        );
        statement.setObject(1,reception.getrName());
        statement.setObject(2,reception.getrAge());
        statement.setObject(3,reception.getrGender());
        statement.setObject(4,reception.getrAddress());
        statement.setObject(5,reception.getrContact());
        statement.setObject(6,reception.getrMail());
        statement.setObject(7,reception.getrId());

        return statement.executeUpdate()>0;

    }

    public boolean deleteReception(String receptionId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Reception WHERE rId =" +
                "'"+receptionId+"'").executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    public Reception getReception(String receptionId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM Reception WHERE rId = ?");
        stm.setObject(1, receptionId);
        ResultSet set = stm.executeQuery();
        if (set.next()) {
            return new Reception(
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

    public ArrayList<Reception> getAllReception() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM Reception");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Reception> receptions = new ArrayList<>();
        while (resultSet.next()){
            receptions.add(new Reception(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return receptions;
    }


}
