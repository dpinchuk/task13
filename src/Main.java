import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static final String URL = "jdbc:mysql://127.0.0.1:3306";
    public static final String DB = "auction_db";
    public static final String USER = "dpinchuk";
    public static final String PASS = "dmss111278";
    public static Connection connection;
    public static Controller controller;

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