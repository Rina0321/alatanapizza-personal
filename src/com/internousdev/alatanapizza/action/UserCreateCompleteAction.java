package com.internousdev.alatanapizza.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.UserCreateCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;


public class UserCreateCompleteAction extends ActionSupport implements SessionAware {

	private String loginUserId;
	private String loginPassword;
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String sex;
	private String mail;
	private String secretQuestion;
	private String secretAnswer;

	public Map<String, Object> session;

	private UserCreateCompleteDAO userCreateCompleteDAO=new UserCreateCompleteDAO();

	/*private LoginDAO dao=new LoginDAO();  保留中*/




	public String execute() throws SQLException {

		userCreateCompleteDAO.createUser(
				session.get("loginUserId").toString(),
				session.get("loginPassword").toString(),
		        session.get("familyName").toString(),
		        session.get("firstName").toString(),
		        session.get("familyNameKana").toString(),
		        session.get("firstNameKana").toString(),
		        Integer.parseInt(session.get("sex").toString()),
		        session.get("mail").toString(),
		        Integer.parseInt(session.get("secretQuestion").toString()),
		        session.get("secretAnswer").toString());


		String result=SUCCESS;

		return result;
	}




	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId=loginUserId;
	}


	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword=loginPassword;
	}


	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName=familyName;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}


	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana=firstNameKana;
	}


	public String getFamilyNameKana() {
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana=familyNameKana;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex=sex;
	}


	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail=mail;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion=secretQuestion;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer=secretAnswer;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

}
