package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Book;

public class BookDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private DaoUtils du = null;
	
	public BookDao(){
		du = new DaoUtils();
	}

	public List selectBooks() {

		List list = new ArrayList<Book>();

		try {
			conn = du.getConnection();
			ps = conn.prepareStatement("select * from mybook");
			rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setStatus(rs.getString("status"));
				book.setOwner(rs.getString("owner"));
				list.add(book);
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			du.free(rs, ps, conn);

		}

		return list;

	}

	public List selectBooksSearch(String search) {

		List list = new ArrayList<Book>();

		try {
			conn = du.getConnection();
			ps = conn
					.prepareStatement("select * from mybook where title like ?");
			ps.setString(1, "%"+search+"%");
			rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setStatus(rs.getString("status"));
				book.setOwner(rs.getString("owner"));
				list.add(book);
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			du.free(rs, ps, conn);

		}

		return list;
	}

	public void updateBookOwner(String bookTitle, String owner) {

		try {
			conn = du.getConnection();
			ps = conn
					.prepareStatement("update mybook set owner=? where title=?");
			ps.setString(1, owner);
			ps.setString(2, bookTitle);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			du.free(rs, ps, conn);

		}

	}

	public void updateBookStatus(String bookTitle, String status) {

		try {
			conn = du.getConnection();
			ps = conn
					.prepareStatement("update mybook set status=? where title=?");
			ps.setString(1, status);
			ps.setString(2, bookTitle);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			du.free(rs, ps, conn);

		}

	}
}

this is from branch temp
