package foodcart.utility;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Author: Aniket Kumar Mishra
 * A singleton class for database Connection
 */
public class DBConnection {
    private static Connection connection = null;

    private DBConnection() {

    }

    /**
     * Method for establishing connection to the database
     *
     * @return Connection to a database
     */
    public static Connection establishConnection() {
        if (connection != null)
            return connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/foodcart", "root", "root");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    /**
     * close database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}