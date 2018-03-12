package com.internousdev.alatanapizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.alatanapizza.dto.LoginDTO;
import com.internousdev.alatanapizza.util.DBConnector;

/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.alatanapizza.dto.LoginDTO;
import com.internousdev.alatanapizza.util.DBConnector;
	*/



public class LoginDAO {

	private LoginDTO dto=new LoginDTO();
	private DBConnector db=new DBConnector();
	private Connection con=db.getConnection();
	private int exUp;

	public LoginDTO loginUserInfo(String loginId,String loginPassword){

		System.out.println(loginId);
		System.out.println(loginPassword);

		String sql="select * from user_info where user_id = ? and password = ?";

		try{
			PreparedStatement ps=con.prepareStatement(sql);

			ps.setString(1, loginId);
			ps.setString(2, loginPassword);

			ResultSet rs=ps.executeQuery();

			if(rs.next()){
				System.out.println(rs.getString("user_id"));
				System.out.println(rs.getString("password"));

				dto.setLoginId(rs.getString("user_id"));
				dto.setLoginPassword(rs.getString("password"));
				dto.setFamilyName(rs.getString("famiry_name"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setFamilyNameKana(rs.getString("family_name_kana"));
				dto.setFirstNameKana(rs.getString("first_name_kana"));
				dto.setSex(rs.getString("sex"));
				dto.setEmail(rs.getString("email"));
			}else{
				dto.setLoginId("noID");
				dto.setLoginPassword("noPASS");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return dto;

	}

	/**
	 * カート情報引継ぎ
	 * tempUserIdをloginIdで上書き
	 * →いまここ　tempUserIdのカート内容をloginIdに引継ぎ
	 * これだと仮にloginIdに商品があった場合にはその商品は追加されない
	 *
	 * なので、tempUserIdのカート内容をloginIdのカート内に追加してやる形をとる
	 * その後でtempUserIdのカート内容を削除する
	 */
	public int cartInfo(String tempUserId,String loginId){
		String sql="INSERT INTO cart_info(product_id,product_count,price)"
				+ "SELECT cart_info=?,product_count=?,price=?)"
				+ "FROM temp_cart_info";
	//	String sql="update cart_info set user_id=? where user_id=?";

		try{
			PreparedStatement ps=con.prepareStatement(sql);

			ps.setString(1, loginId);
			ps.setString(2, tempUserId);

			exUp=ps.executeUpdate();

		}catch (SQLException e){
			e.printStackTrace();
		}

		return exUp;
	}

	public int getExUp(){
		return exUp;
	}

	public void setExUp(int exUp){
		this.exUp=exUp;
	}
	/*試しのコード
	private DBConnector dbConnector=new DBConnector();
	private Connection connection=dbConnector.getConnection();
	private LoginDTO loginDTO=new LoginDTO();

	public LoginDTO getLoginUserInfo(String loginUserId,String loginPassword){
		String sql="SELECT * FROM login_user_transaction where login_id = ? AND login_pass = ?";
		try{
			PreparedStatement preparedStatement=connection.prepareStatement(sql);

			preparedStatement.setString(1, loginUserId);
			preparedStatement.setString(2, loginPassword);

			ResultSet resultSet=preparedStatement.executeQuery();

			if(resultSet.next()){
				loginDTO.setLoginId(resultSet.getString("login_id"));
				loginDTO.setLoginPassword(resultSet.getString("login_pass"));
				loginDTO.setUserName(resultSet.getString("user_name"));

				if(!(resultSet.getString("login_id").equals(null))){
					loginDTO.setLoginFlg(true);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return loginDTO;
	}
	public LoginDTO getLoginDTO(){
		return loginDTO;
	}
	*/

}
