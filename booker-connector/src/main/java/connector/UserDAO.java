package connector;

import common.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by phiha on 24/07/2017.
 */
public class UserDAO extends AbstractDAO{
//    private static Logger LOG = LoggerFactory.getLogger(UserDAO.class);

    public UserDAO() throws SQLException {
        super();
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql  = String.format("SELECT * FROM users WHERE users.username = %s", username);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFbID(rs.getString("fb_id"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            return user;
        }
        return null;
    }

    public User getUserByFbId(int fbId) throws SQLException {
        String sql  = String.format("SELECT * FROM users WHERE users.username = %d", fbId);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFbID(rs.getString("fb_id"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            return user;
        }
        return null;
    }
}
