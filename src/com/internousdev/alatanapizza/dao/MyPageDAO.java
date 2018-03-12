package com.internousdev.alatanapizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.alatanapizza.dto.MyPageDTO;
import com.internousdev.alatanapizza.util.DBConnector;

public class MyPageDAO {

	DBConnector db = new DBConnector();
	Connection con = db.getConnection();

	/**
	 * ユーザー情報を取得
	 *
	 * @return myPageList
	 */
	public ArrayList<MyPageDTO> getMyPageUserInfo(String userId) {
		MyPageDTO myPageDTO = new MyPageDTO();
		ArrayList<MyPageDTO> myPageList = new ArrayList<MyPageDTO>();

		String sql1 = "SELECT * FROM user_info where user_id = ? ";

		try {
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, userId);
			ResultSet rs = ps1.executeQuery();

			while (rs.next()) {

				myPageDTO.setUserId(rs.getString("user_id"));

				myPageDTO.setFamilyName(rs.getString("family_name"));

				myPageDTO.setFirstName(rs.getString("first_name"));

				myPageDTO.setFamilyNameKana(rs.getString("family_name_kana"));

				myPageDTO.setFirstNameKana(rs.getString("first_name_kana"));

				myPageDTO.setSex(rs.get???("sex"));

				myPageDTO.setEmail(rs.getString("email"));

				myPageDTO.setPassword(rs.getString("password"));

				myPageDTO.setTel_Number(rs.getString("tel_number"));

				myPageDTO.setUser_Address(rs.getString("user_address"));



				System.out.println("---myPageDTO-----");
				System.out.println(myPageDTO.getPassword());
				System.out.println("--------");

				myPageList.add(myPageDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myPageList;
	}

}
