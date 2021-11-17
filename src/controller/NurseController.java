package controller;

import db.DbConnection;
import model.Nurse;
import model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NurseController {

    public static boolean saveNurse(Nurse nurse) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO Nurse VALUES (?,?,?,?,?,?)");
        stm.setObject(1,nurse.getnId());
        stm.setObject(2,nurse.getnName());
        stm.setObject(3,nurse.getnAge());
        stm.setObject(4,nurse.getnGender());
        stm.setObject(5,nurse.getnAddress());
        stm.setObject(6,nurse.getnContact());

        return stm.executeUpdate()>0;
    }

    public Nurse getNurse(String nurseId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM Nurse WHERE nId=?");
        stm.setObject(1, nurseId);
        ResultSet set = stm.executeQuery();
        if (set.next()) {
            return new Nurse(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6)
            );
        } else {
            return null;
        }
    }

    public boolean deleteNurse(String nurseId) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Nurse WHERE nId="+"'"+nurseId+"'").executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean updateNurse(Nurse nurse) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE Nurse SET nName=?, nAge=?, nGender =?, nAddress=?, pContact=? WHERE nId=? ");

        stm.setObject(1,nurse.getnName());
        stm.setObject(2,nurse.getnAge());
        stm.setObject(3,nurse.getnGender());
        stm.setObject(4,nurse.getnAddress());
        stm.setObject(5,nurse.getnContact());
        stm.setObject(6,nurse.getnId());

        return stm.executeUpdate()>0;
    }

    public ArrayList<Nurse> getAllNurse() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Nurse");
        ResultSet set = stm.executeQuery();
        ArrayList<Nurse> nurses = new ArrayList<>();
        while (set.next()){
            nurses.add(new Nurse(
               set.getString(1),
               set.getString(2),
               set.getString(3),
               set.getString(4),
               set.getString(5),
               set.getString(6)
            ));
        }
        return nurses;
    }



















}
