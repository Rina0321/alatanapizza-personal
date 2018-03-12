package com.internousdev.alatanapizza.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

//import com.internousdev.alatanapizza.dao.UserCreateConfirmDAO;
import com.opensymphony.xwork2.ActionSupport;


public class UserCreateConfirmAction extends ActionSupport implements SessionAware {

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

	private Map<String, Object> session;

	// private String result;

	private ArrayList<String> errMsgList=new ArrayList<>();

	private ArrayList<String> errMsgListId=new ArrayList<>();
	private ArrayList<String> errMsgListPass=new ArrayList<>();
	private ArrayList<String> errMsgListName=new ArrayList<>();
	private ArrayList<String> errMsgListKana=new ArrayList<>();
	private ArrayList<String> errMsgListSex=new ArrayList<>();
	private ArrayList<String> errMsgListMail=new ArrayList<>();
	private ArrayList<String> errMsgListQuestion=new ArrayList<>();
	private ArrayList<String> errMsgListAnswer=new ArrayList<>();


	//private String errId1;
	private String errId2;
	private String errId3;
	private String errPass1;
	private String errPass2;
	private String errName1;
	private String errName2;
	private String errKana1;
	private String errKana2;
	private String errKana3;
	private String errKana4;
	private String errSex;
	private String errMail1;
	private String errMail2;
	private String errQuestion;
	private String errAnswer;


	//private UserCreateConfirmDAO userCreateConfirmDAO = new UserCreateConfirmDAO();




	public String execute() {

		String result=ERROR;

		if(loginUserId.equals("")
            || loginPassword.equals("")
            || familyName.equals("")
            || firstNameKana.equals("")
            || familyNameKana.equals("")
            || firstNameKana.equals("")
            || sex==null || mail.equals("")
			|| secretQuestion.equals("")
			|| secretAnswer.equals("") )


            errMsgList.add("未入力の項目があります");



	// ユーザーID
	/*if(userCreateConfirmDAO.getLoginUserId(loginUserId)) {
		errId1="そのIDはすでに使われています";
		errMsgListId.add(errId1);
	} */

	if (loginUserId.length() > 8) {
		errId2="ユーザーIDは8文字以内で入力してください";
		errMsgListId.add(errId2);
	}

	if (!loginUserId.matches("^[0-9a-zA-Z]+$")) {
		errId3="ユーザーIDは半角英数字で入力してください";
		errMsgListId.add(errId3);
	}


	// パスワード
	if (loginPassword.length() > 16) {
		errPass1="パスワードは16文字以内で入力してください";
		errMsgListPass.add(errPass1);
	}
	if (!loginPassword.matches("^[0-9a-zA-Z]+$")) {
		errPass2="パスワードは半角英数字で入力してください";
		errMsgListPass.add(errPass2);
	}


	// 名前
	if (familyName.length() > 16) {
		errName1="姓は16文字以内で入力してください";
		errMsgListName.add(errName1);
	}

	if (firstName.length() > 16) {
		errName2="名は16文字以内で入力してください";
		errMsgListName.add(errName2);
	}

	if (familyNameKana.length() > 16) {
		errKana1="(せい)は16文字以内で入力してください";
		errMsgListKana.add(errKana1);
	}

	if (firstNameKana.length() > 16) {
		errKana2="(めい)は16文字以内で入力してください";
		errMsgListKana.add(errKana2);
	}

	if (!familyNameKana.matches("^[ぁ-ん]+$")) {
		errKana3="姓(かな)はひらがなで入力してください";
		errMsgListKana.add(errKana3);
	}

	if (!firstNameKana.matches("^[ぁ-ん]+$")) {
		errKana4="名(かな)はひらがなで入力してください";
		errMsgListKana.add(errKana4);
	}


	// 性別
	if (sex!=null) {
		if (!sex.matches("^[0-1]+$")) {
			errSex="性別を選択してください";
			errMsgListSex.add(errSex);
		}
	}


	// メールアドレス
	if (mail.length()<14 || mail.length()>32) {
		errMail1="メールアドレスは14文字以上32文字以下で入力してください";
		errMsgListMail.add(errMail1);
	}

	if (!mail.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
		errMail2="メールアドレスは半角英数字と半角記号で入力してください";
		errMsgListMail.add(errMail2);
	}


	// 質問
	if (!secretQuestion.matches("^[1-3]+$")) {
		errQuestion="質問を選択してください";
		errMsgListQuestion.add(errQuestion);
	}


	// 答え
	if (secretAnswer.length()>16) {
		errAnswer="答えは16文字以内で入力してください";
		errMsgListAnswer.add(errAnswer);
	}


	if ( errMsgList.isEmpty()
		 && errMsgListId.isEmpty()
		 && errMsgListPass.isEmpty()
		 && errMsgListName.isEmpty()
		 && errMsgListKana.isEmpty()
		 && errMsgListSex.isEmpty()
		 && errMsgListMail.isEmpty()
		 && errMsgListQuestion.isEmpty()
		 && errMsgListAnswer.isEmpty() )

		 result=SUCCESS;

		 session.put("loginUserId", loginUserId);
		 session.put("loginPassword", loginPassword);
		 session.put("familyName", familyName);
		 session.put("firstName", firstName);
		 session.put("familyNameKana", familyNameKana);
		 session.put("firstNameKana", firstNameKana);
		 session.put("sex", sex);
		 session.put("mail", mail);
		 session.put("secretQuestion", secretQuestion);
		 session.put("secretAnswer", secretAnswer) ;



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


	public String getFamilyNameKana() {
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana=familyNameKana;
	}


	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana=firstNameKana;
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


	public ArrayList<String> getErrMsgList() {
		return errMsgList;
	}

	public void setErroMsgList(ArrayList<String> errMsgList) {
		this.errMsgList=errMsgList;
	}


	public ArrayList<String> getErrMsgListId() {
		return errMsgListId;
	}

	public void setErrMsgListId(ArrayList<String> errMsgListId) {
		this.errMsgListId=errMsgListId;
	}


	public ArrayList<String> getErrMsgListPass() {
		return errMsgListPass;
	}

	public void setErrMsgListPass(ArrayList<String> errMsgListPass) {
		this.errMsgListPass=errMsgListPass;
	}


	public ArrayList<String> getErrMsgListName() {
		return errMsgListName;
	}

	public void setErrMsgListName(ArrayList<String> errMsgListName) {
		this.errMsgListName=errMsgListName;
	}


	public ArrayList<String> getErrMsgListKana() {
		return errMsgListKana;
	}

	public void setErrMsgListKana(ArrayList<String> errMsgListKana) {
		this.errMsgListKana=errMsgListKana;
	}


	public ArrayList<String> getErrMsgListSex() {
		return errMsgListSex;
	}

	public void setErrMsgListSex(ArrayList<String> errMsgListSex) {
		this.errMsgListSex=errMsgListSex;
	}


	public ArrayList<String> getErrMsgListMail() {
		return errMsgListMail;
	}

	public void setErrMsgListMail(ArrayList<String> errMsgListMail) {
		this.errMsgListMail=errMsgListMail;
	}


	public ArrayList<String> getErrMsgListQuestion() {
		return errMsgListQuestion;
	}

	public void setErrMsgListQuestion(ArrayList<String> errMsgListQuestion) {
		this.errMsgListQuestion=errMsgListQuestion;
	}


	public ArrayList<String> getErrMsgListAnswer() {
		return errMsgListAnswer;
	}

	public void setErrMsgListAnswer(ArrayList<String> errMsgListAnswer) {
		this.errMsgListAnswer=errMsgListAnswer;
	}


/*	public String getErrId() {
		return errId2;
	}

	public void setErrId(String errId2) {
		this.errId2=errId2;
	}  */
}

