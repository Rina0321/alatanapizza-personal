package com.internousdev.alatanapizza.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.DestinationDAO;
import com.opensymphony.xwork2.ActionSupport;

public class DestCompleteAction extends ActionSupport implements SessionAware {
	/**
	 * 値
	 */
	private String userFamilyName;
	private String userFirstName;
	private String userFamilyNameKana;
	private String userFirstNameKana;
	private String email;
	private String telNumber;
	private String userAddress;

	public Map<String,Object> session;
	private DestinationDAO destinationDAO=new DestinationDAO();

	public String execute() throws SQLException{
		destinationDAO.createDestination(
				session.get("userId").toString(),
				session.get("userFamilyName").toString(),
				session.get("userFirstName").toString(),
				session.get("userFamilyNameKana").toString(),
				session.get("userFirstNameKana").toString(),
				session.get("email").toString(),
				session.get("telNumber").toString(),
				session.get("userAddress").toString());

		String result = SUCCESS;

		return result;
	}
	/**
	 * ゲッターセッター
	 * @return
	 */
	public String getUserFamilyName(){
		return userFamilyName;
	}
	public void setUserFamilyName(String userFamilyName){
		this.userFamilyName=userFamilyName;
	}
	public String getUserFirstName(){
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName){
		this.userFirstName=userFirstName;
	}
	public String getUserFamilyNameKana(){
		return userFamilyNameKana;
	}
	public void setUserFamilyNameKana(String userFamilyNameKana){
		this.userFamilyNameKana=userFamilyNameKana;
	}
	public String getUserFirstNameKana(){
		return userFirstNameKana;
	}
	public void setUserFirstNameKana(String userFirstNameKana){
		this.userFirstNameKana=userFirstNameKana;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getTelNumber(){
		return telNumber;
	}
	public void setTelNumber(String telNumber){
		this.telNumber=telNumber;
	}
	public String getUserAddress(){
		return userAddress;
	}
	public void setUserAddress(String userAddress){
		this.userAddress=userAddress;
	}
	@Override
	public void setSession(Map<String,Object> session){
		this.session=session;
	}

}
