package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessCardManagerDao {

	private static String dburl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";

	public List<BusinessCard> searchBusinessCard(String keyword) {

		List<BusinessCard> businessCardList = new ArrayList<BusinessCard>();

		String sql = "SELECT * FROM carddb WHERE name LIKE '%" + keyword + "%'";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
		     PreparedStatement ps = conn.prepareStatement(sql);
		     ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String companyName = rs.getString("company_name");
				Date createDate = rs.getDate("create_date");

				businessCardList.add(new BusinessCard(name, phone, companyName, createDate));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return businessCardList;
	}

	public BusinessCard addBusinessCard(BusinessCard businessCard) {

		String sql = "INSERT INTO carddb(name,phone,company_name,create_date) VALUES(?,?,?,now());";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
		     PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, businessCard.getName());
			ps.setString(2, businessCard.getPhone().length() == 0 ? null : businessCard.getPhone());//전화번호가 없는경우
			ps.setString(3, businessCard.getCompanyName());

			ps.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("동일한 전화번호가 존재합니다. 다시 입력해 주세요");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return businessCard;
	}
}
