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

    @Test
    public void testIsValid() throws SQLException {
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
    public void testIsClosedNegative() throws SQLException {
        Connection connection = DriverManager.getConnection(URL + "/" + DB, USER, PASS);
        assertFalse(connection.isClosed());
        connection.close();
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
    public void testConnection() throws SQLException {
        connection = DriverManager.getConnection(URL + "/" + DB, USER, PASS);
        connection.close();
        assertFalse(connection.isValid(100));
    }

    @Test(expected = MySQLSyntaxErrorException.class)
    public void testFakeDB() throws SQLException {
        connection = DriverManager.getConnection(URL + "/" + "fakeDB", USER, PASS);
        connection.close();
    }

    @Test(expected = SQLException.class)
    public void testFakeUser() throws SQLException {
        connection = DriverManager.getConnection(URL + "/" + DB, "fakeUser", PASS);
        connection.close();
    }

    @Test(expected = SQLException.class)
    public void testFakePass() throws SQLException {
        connection = DriverManager.getConnection(URL + "/" + DB, USER, "fakePassword");
        connection.close();
    }

    @Test(expected = SQLException.class)
    public void testFake() throws SQLException {
        connection = DriverManager.getConnection(null, null, null);
        connection.close();
    }

}