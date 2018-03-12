package com.internousdev.alatanapizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.alatanapizza.util.DBConnector;
import com.internousdev.alatanapizza.util.DateUtil;


/**
 * ログインフラグ？
 * ログインした状態であるかの判定が必要かどうか不安
 * とりあえずデータ格納できるようには書いた
 * @author internousdev
 *
 */
public class DestinationDAO {
	/**
	 * DBにコネクトするため
	 */
	private DBConnector dbConnector = new DBConnector();
	private Connection connection=dbConnector.getConnection();
	/**
	 * 更新日をつけるためのインポート
	 */
	private DateUtil dateUtil=new DateUtil();
	/**
	 * sql文生成
	 */
	private String sql="INSERT INTO destination_info("
			+ "user_id"
			+ ",family_name"
			+ ",first_name"
			+ ",family_name_kana"
			+ ",first_name_kana"
			+ ",email"
			+ ",tel_number"
			+ ",user_address"
			+ ")VALUES(?,?,?,?,?,?,?,?,?)";
	/**
	 * 値定義
	 * @param userId
	 * @param familyName
	 * @param firstName
	 * @param familyNameKana
	 * @param firstNameKana
	 * @param email
	 * @param telNumber
	 * @param userAddress
	 * @throws SQLException
	 */

	public void createDestination(
			String userId,
			String familyName,
			String firstName,
			String familyNameKana,
			String firstNameKana,
			String email,
			String telNumber,
			String userAddress)throws SQLException{
		/**
		 * IDはSQLファイルのほうでAUTO_INCREMENTがされているので
		 * その他の項目をpreparedStatementでセットさせてる
		 */

		try{
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, familyName);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, familyNameKana);
			preparedStatement.setString(5, firstNameKana);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, telNumber);
			preparedStatement.setString(8, userAddress);
			preparedStatement.setString(9, dateUtil.getDate());

			preparedStatement.execute();

		}catch(Exception e){
			/**
			 * エラーなったらエラー文出す
			 */
			e.printStackTrace();
		}finally{
			/**
			 * 切断
			 */
			connection.close();
		}
	}

}
