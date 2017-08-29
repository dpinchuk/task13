import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

public class MainTest {

    private final String URL = "jdbc:mysql://127.0.0.1:3306";
    private final String DB = "auction_db";
    private final String USER = "dpinchuk";
    private final String PASS = "dmss111278";
    private Connection connection;
    private Controller controller;

    @Test
    public void testConnectionIsValid() throws SQLException {
        connection = DriverManager.getConnection(URL + "/" + DB, USER, PASS);
        assertTrue(connection.isValid(1000));
        connection.close();

    }

    @Test
    public void testIsClosedPositive() throws SQLException {
        connection = DriverManager.getConnection(URL + "/" + DB, USER, PASS);
        connection.close();
        assertTrue(connection.isClosed());
    }

    @Test
    public void testIsReadOnly() throws SQLException {
        connection = DriverManager.getConnection(URL + "/" + DB, USER, PASS);
        assertFalse(connection.isReadOnly());
        connection.close();
    }

    @Test
    public void testGetWarnings() throws SQLException {
        connection = DriverManager.getConnection(URL + "/" + DB, USER, PASS);
        assertNull(connection.getWarnings());
        connection.close();
    }

    @Test
    public void testIsClosedNegative() throws SQLException {
        connection = DriverManager.getConnection(URL + "/" + DB, USER, PASS);
        assertFalse(connection.isClosed());
        connection.close();
    }

    @Test(expected = MySQLSyntaxErrorException.class)
    public void testFakeDB() throws SQLException {
        connection = DriverManager.getConnection(URL + "/" + "fake_db", USER, PASS);
        connection.close();
    }

}