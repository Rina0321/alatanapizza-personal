package com.internousdev.alatanapizza.action;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.ChangePasswordCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ChangePasswordCompleteAction extends ActionSupport implements SessionAware{
	private String newpass;
	private String userid;
	private String answer;
	private Map<String,Object>session;
	private ChangePasswordCompleteDAO dao=new ChangePasswordCompleteDAO();
	public String execute() throws SQLException{
		System.out.println("----------");
		System.out.println(newpass);
		System.out.println(userid);
		System.out.println(answer);
		System.out.println("---------");


		int result=dao.changeComplete(newpass,userid,answer);
		if(result>0){
			return SUCCESS;
		}else{
			return ERROR;

		}



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

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
