package hr.tvz.zr.menzastudent.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database {
    private static Connection connectToDatabase() throws SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(new FileReader("dat/database.properties"));
        String databaseURL = configuration.getProperty("databaseURL");
        String databaseUsername = configuration.getProperty("databaseUsername");
        String databasePassword = configuration.getProperty("databasePassword");
        Connection connection = DriverManager
                .getConnection(databaseURL, databaseUsername, databasePassword);
        return connection;
    }

    public static Integer getRacuniWithinLast(Integer minutes, String naziv) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(uuid) as broj FROM racuni WHERE menza = ?"
                + " and vrijeme_izdavanja between dateadd(minute, -?, now()) and now() and suma > 0");
        stmt.setString(1, naziv);
        stmt.setString(2, minutes.toString());
        ResultSet rs = stmt.executeQuery();
        rs.next();
        Integer result = rs.getInt("broj");
        disconnectFromDatabase(connection);
        return result;
    }

    private static void disconnectFromDatabase(Connection connection){
        try{
            if(!connection.equals(null) && !connection.isClosed())
                connection.close();
        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
