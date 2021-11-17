package controller;

import db.DbConnection;
import model.LogIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInController {

    public boolean saveReceptionLogin(LogIn logIn) throws SQLException, ClassNotFoundException, SQLException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().
                prepareStatement("INSERT INTO Login VALUES (?,?,?,?)");
        statement.setObject(1, logIn.getrId());
        statement.setObject(2, logIn.getUserName());
        statement.setObject(3, logIn.getPassword());
        statement.setObject(4, logIn.getRole());
        return statement.executeUpdate()>0;
    }

    public LogIn getUser(String userName, String password) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM Login WHERE userName= ? AND password= ?");
        statement.setObject(1, userName);
        statement.setObject(2, password);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            return new LogIn(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

}
