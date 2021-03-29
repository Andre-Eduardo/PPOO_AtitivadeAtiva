package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteBase {
	protected Connection conn;

	public Connection open() throws ClassNotFoundException {

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:db/my_database.db");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
