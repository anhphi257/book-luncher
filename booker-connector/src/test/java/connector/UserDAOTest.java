package connector;

import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by phiha on 24/07/2017.
 */
public class UserDAOTest {
    @Test
    public void test01connection() throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
    }
}
