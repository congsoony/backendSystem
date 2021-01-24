package org.edwith.webbe.guestbook.dao;

import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestbookDao {

	private Connection conn;

	public GuestbookDao(Connection conn) {
		this.conn = conn;
	}

	public List<Guestbook> getGuestbooks() {

		List<Guestbook> list = new ArrayList<>();

		String sql = "SELECT * FROM guestbook ORDER BY id DESC";

		try (PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");

				list.add(new Guestbook(id, name, content, regdate));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addGuestbook(Guestbook guestbook) {
		
		String sql = "INSERT INTO guestbook(name,content,regdate) VALUES(?,?,now())";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, guestbook.getName());
			ps.setString(2, guestbook.getContent());
			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
