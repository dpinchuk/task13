import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306";
    private static final String DB = "auction_db";
    private static final String USER = "dpinchuk";
    private static final String PASS = "dmss111278";
    private static Connection connection;
    private static Controller controller;

    public static void main(String[] args) throws IOException, SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        try {
            connection = DriverManager.getConnection(URL + "/" + DB, USER, PASS);
            controller = new Controller(connection);
            controller.selectItem();
            connection.close();
        } catch (MySQLSyntaxErrorException e) {
            System.out.println("Database connection error...");
        }
    }

}