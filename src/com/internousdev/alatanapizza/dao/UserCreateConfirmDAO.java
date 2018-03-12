/* package com.internousdev.alatanapizza.dao;

import java.sql.Connection;

import com.internousdev.alatanapizza.util.DBConnector;


public class UserCreateConfirmDAO {

	private DBConnector dbConnector=new DBConnector();
	private Connection connection=dbConnector.getConnection();

	 private boolean result;



	 ログインID重複確認メソッド
	public boolean getLoginUserId(String loginUserId) {

		String sql="SELECT*FROM user_info where user_id=?";

		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, loginUserId);

			ResultSet resultSet=preparedStatement.executeQuery();

			if (resultSet.next()) {
            	result=true;
			}

		} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
}  */



