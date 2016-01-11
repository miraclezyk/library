package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public final class DaoUtils {

	private static String jdbcUrl = "jdbc:mysql://localhost:3306/test";
	private static String username = "";
	private static String password = "";

	public DaoUtils() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, username, password);
	}

	public void free(ResultSet rs, PreparedStatement ps, Connection conn) {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
		}

	}
}
