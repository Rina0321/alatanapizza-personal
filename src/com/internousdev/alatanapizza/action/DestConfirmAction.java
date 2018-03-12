package com.internousdev.alatanapizza.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 打ち込んだ情報をMapに格納したい
 * @author internousdev
 *
 *			<s:form action="CompleteDestAction">
				<span>姓</span>
				<s:textfield name="UserFamilyName"/>
				<span>名</span>
				<s:textfield name="UserFirstName"/>
				<span>姓ふりがな</span>
				<s:textfield name="UserFamilyNameKana"/>
				<span>名ふりがな</span>
				<s:textfield name="UserFilstNameKana"/>
				<span>メールアドレス</span>
				<s:textfield name="Email"/>
				<span>電話番号</span>
				<s:textfield name="TelNumber"/>
				<span>郵便番号</span>
				<s:textfield name="PostalCode"/>
				<span>住所</span>
				<s:textfield name="UserAddress"/>
				<s:submit value="登録情報確認画面へ"/>
 *
 *
 * 適切でない文字が入力された際の対応をどうするか？
 *
 */

public class DestConfirmAction extends ActionSupport implements SessionAware {

	private String userFamilyName;
	private String userFirstName;
	private String userFamilyNameKana;
	private String userFirstNameKana;
	private String email;
	private String telNumber;
	private String userAddress;

	public Map<String,Object> session;
	public String errorMessage;

	public String execute(){
		String result= SUCCESS;

		/**
		 * すべての欄が埋まっている場合
		 * データ格納
		 */
		if(!(userFamilyName.equals(""))
				&&!(userFirstName.equals(""))
				&&!(userFamilyNameKana.equals(""))
				&&!(userFirstNameKana.equals(""))
				&&!(email.equals(""))
				&&!(telNumber.equals(""))
				&&!(userAddress.equals(""))){
			session.put("userFamilyName", userFamilyName);
			session.put("userFirstName", userFirstName);
			session.put("userFamilyNameKana", userFamilyNameKana);
			session.put("userFirstNameKana", userFirstNameKana);
			session.put("email", email);
			session.put("telNumber", telNumber);
			session.put("userAddress", userAddress);

		}else{
			/**
			 * 空欄がある場合のエラーメッセージ
			 */
			setErrorMessage("未入力の項目があります。");
			result=ERROR;
		}
		return result;

	}

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
	public String getErrorMessage(){
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage){
		this.errorMessage=errorMessage;
	}

}
