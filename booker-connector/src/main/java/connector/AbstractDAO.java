package connector;

import common.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by phiha on 24/07/2017.
 */
public class AbstractDAO {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    protected Connection connection;
    public AbstractDAO() throws SQLException {
        Configuration config = Configuration.getInstance();
        String url = config.getConfig("mysql.DB_URL");
        String username = config.getConfig("mysql.username");
        String password = config.getConfig("mysql.password");
        connection = DriverManager.getConnection(url, username, password);
        LOG.info("Connecting database");
    }
}
