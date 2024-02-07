package foodcart.utility;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Author: Aniket Kumar Mishra
 */
public class DBConnection {
    private static Connection connection = null;

    /**
     * Method for establishing connection to the database
     *
     * @return Connection
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
            e.printStackTrace();
        }

    }
}