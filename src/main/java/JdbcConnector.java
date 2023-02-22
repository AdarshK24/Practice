import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcConnector {
	public Connection getJdbcConnection(String jndiTag) throws SQLException, NamingException {
		Connection connection = null;
		Context initialContext = new InitialContext();
		DataSource ds = (DataSource) initialContext.lookup("java:jboss/jdbc/" + jndiTag);
		if (ds != null) {
			connection = ds.getConnection();
//			log.warn(CONNECTION_ESTABLISHED);
		} else {
//			log.error(DATASOURCE_FAILED);
		}
		return connection;

	}

}
