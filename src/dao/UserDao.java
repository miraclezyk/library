package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;

public class UserDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private DaoUtils du = null;
	
	public UserDao(){
		du = new DaoUtils();
	}

	public User selectUser(String name) {

		User user = null;

		try {
			conn = du.getConnection();
			ps = conn.prepareStatement("select * from myuser where username=?");
			ps.setString(1, name);
			rs = ps.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setIdentity(rs.getString("identity"));
				user.setBookno(rs.getInt("bookno"));
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			du.free(rs, ps, conn);
		}

		return user;

	}

	public void changeBookno(String name, int number) {

		try {
			conn = du.getConnection();
			ps = conn
					.prepareStatement("update myuser set bookno=? where username=?");
			ps.setInt(1, number);
			ps.setString(2, name);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			du.free(rs, ps, conn);
		}
	}

}
