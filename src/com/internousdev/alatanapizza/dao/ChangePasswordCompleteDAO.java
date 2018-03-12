package com.internousdev.alatanapizza.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.internousdev.alatanapizza.util.DBConnector;


public class ChangePasswordCompleteDAO {

	private DBConnector db=new DBConnector();
	Connection con=db.getConnection();
	public Map<String,Object>session;
	private String newpass;
	private String userid;
	private String answer;
	private int result=0;
	private String sql="update user_info set password=? where user_id=? and answer=?";

	public int changeComplete(String newpass,String userid,String answer) throws SQLException{
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,newpass);
			ps.setString(2, userid);
			ps.setString(3, answer);
			ps.executeUpdate();
			result=ps.executeUpdate();


		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		System.out.println(result);
		return result;

	}








	public String getNewpass() {
		return newpass;
	}
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}








	public int getResult() {
		return result;
	}








	public void setResult(int result) {
		this.result = result;
	}







}
