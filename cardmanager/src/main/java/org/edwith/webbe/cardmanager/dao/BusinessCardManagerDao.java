package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessCardManagerDao {

    private static String dburl = "jdbc:mysql://localhost:3306/connectdb";
    private static String dbUser = "connectuser";
    private static String dbpasswd = "connect123!@#";

    public List<BusinessCard> searchBusinessCard(String keyword){
        List<BusinessCard> businessCardList = new ArrayList<BusinessCard>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql ="SELECT * FROM carddb WHERE name LIKE '%"+keyword+"%'";

        try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);

             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){

            while(rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String companyName = rs.getString("company_name");
                Date createDate = rs.getDate("create_date");

                businessCardList.add(new BusinessCard(name, phone, companyName, createDate));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return businessCardList;
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
