package com.internousdev.alatanapizza.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.CartInfoDAO;
import com.internousdev.alatanapizza.dao.LoginDAO;
import com.internousdev.alatanapizza.dto.CartInfoDTO;
import com.internousdev.alatanapizza.dto.LoginDTO;
import com.internousdev.alatanapizza.util.ErrorMessageConstants;

//import com.internousdev.alatanapizza.dao.GoCartInfoDAO;
//import com.internousdev.alatanapizza.dto.GoCartInfoDTO;
//import com.internousdev.alatanapizza.dao.DestinationInfoDAO;
//import com.internousdev.alatanapizza.dto.DestinationInfoDTO;
//import com.internousdev.alatanapizza.dto.UserInfoDTO;
//import com.internousdev.alatanapizza.util.ErrorMessageConstants;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware,ErrorMessageConstants {

	/**
	 * ログインに必要な情報
	 * 登録した内容
	 * →ID,PASSWORD
	 *
	 * カート内容を引継ぎするために仮IDを本IDで上書きするにはどうするか
	 *× →LoginDAOのほうでPreparedStatementにてSQL文でtemp_user_idをuser_idでUpdateしたので解決？
	 *→id書き換えだけだとログインしていたときにカートに入れていた情報が引き継がれない
	 *→条件によってsql文を使い分ける？
	 *
	 * エラーメッセージ
	 *
	 * くらい？
	 */

	private static final String KESSAI="kessai";
	//ユーザーID
	private String userId;
	//パスワード
	private String password;
	//ID保持
	private boolean saveLogin;
	//セッション
	private Map<String,Object>session;
	//エラーメッセージ
	private ArrayList<String> errorMessageList=new ArrayList<>();//errorMessageConstants?
	//決済ページへ
	private int kessai;
	//合計金額
	private int totalPrice;
	//宛先情報一覧
	private ArrayList<DestinationInfoDTO> destinationInfoListDTO= new ArrayList<DestinationInfoDTO>();
	//カートリスト→担当にArrayListを作成してもらう？
	private ArrayList<CartInfoDTO> cartList=new ArrayList<CartInfoDTO>();

	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String email;
	private String telNumber;
	private String userAddress;

	public String execute() throws SQLException{
		String result=ERROR;
		LoginDTO loginDTO=new LoginDTO(); //元のコードはUserInfoDTO、改変の必要出たので以下改変する予定
		LoginDAO loginDAO=new LoginDAO();

		System.out.println(userId);

		//ユーザーID入力チェック
		if(userId==null){
			return "login";
		}
		if(userId.equals("")){ //userIdが空欄
			errorMessageList.add("ユーザーIDを入力してください");
		}else if(userId.length()<1 || userId.length()>8){ //userIdの長さが1以下か8以上のとき
			errorMessageList.add("ユーザーIDは1文字以上8文字以下で入力してください");
		}else if(!userId.matches("^[a-zA-Z0-9]+$")){ //userIdに英数字以外が含まれているとき
			errorMessageList.add("ユーザーIDは半角英数字で入力してください");
		}

		//パスワード入力チェック
		if(password.equals("")){ //passwordが空欄
			errorMessageList.add("パスワードを入力してください");
		}else if(password.length()<1 || password.length()>8){ //passwordの長さが1以下か8以上のとき
			errorMessageList.add("パスワードは1文字以上8文字以下で入力してください");
		}else if(!password.matches("^[a-zA-Z0-9]+$")){ //passwordに英数字以外が含まれているとき
			errorMessageList.add("パスワードは半角英数字で入力してください");
		}

		//ID保存
		if(saveLogin){
			session.put("saveId", userId);
		}else{
			session.remove("saveId");
		}

		if(!userId.equals("") && !password.equals("")){ //どちらも空欄ではないとき
			//ユーザーIDがDBに存在するか確認
			if(!loginDAO.existsUserId(userId)){ //ユーザーIDがない
				errorMessageList.add("IDが正しくありません");
				result=ERROR;
			}else{
				loginDTO=loginDAO.select(userId,password);

				//ログイン判定
				if(userId.equals(loginDTO.getUserId()) && password.equals(loginDTO.getPassword())){ //二つとも一致した場合
					loginDAO.login(loginDTO);

					result=SUCCESS;

					//Mapセッション情報の更新をする
					session.put("userId", loginDTO.getUserId()); //
					session.put("loginFlg", true); //ログインフラグ立て

					CartInfoDAO cartInfoDAO=new CartInfoDAO(); //newカートリスト
					DestinationInfoDAO destinationInfoDAO=new DestinationInfoDAO(); //new宛先
					ArrayList<CartInfoDTO> cartList=new ArrayList<CartInfoDTO>(); //会員用カートリスト
					ArrayList<CartInfoDTO> tempCartList=new ArrayList<CartInfoDTO>(); //ゲスト用カートリスト
					ArrayList<Integer> productIdList=new ArrayList<Integer>(); //整数型　製品リスト
					ArrayList<Integer> tempProductIdList=new ArrayList<Integer>(); //整数型　ゲスト用製品リスト

					//Mapのsessionから取得するのでString型として取得した
					//userIdのカート情報をすべて引き出すメソッドを代入
					cartList=cartInfoDAO.showUserCartList(session.get("userId").toString());
					//tempUserIdのカート情報をすべて引き出すメソッドを代入
					tempCartList=cartInfoDAO.showUserCartList(session.get("tempUserId").toString());
					int i=0;


					//ログイン後のカートの中身を生成
					for(i=0;i<cartList.size();i++){
						productIdList.add(cartList.get(i).getProductId());
					}

					//ゲスト時のカートの中身をリストとして生成
					i=0;
					for(i=0;i<tempCartList.size();i++){
						tempProductIdList.add(tempCartList.get(i).getProductId());
					}

					//カートの中身の重複を確認
					if(cartList.size()<tempCartList.size()){ //ログイン時のカートリスト < ゲスト用のカートリスト
						i=0;
						for(i=0;i<productIdList.size();i++){
							boolean exist=tempProductIdList.contains(productIdList.get(i));
							if(exist){
								cartInfoDAO.changeProductStockId(Integer.valueOf(cartList.get(i).getProductCount()),
										Integer.valueOf(productIdList.get(i)),session.get("userId").toString());
								cartInfoDAO.deleteSeparate(session.get("tempUserId").toString(),
										Integer.valueOf(productIdList.get(i)));
							}else{
								cartInfoDAO.changeUserId(session.get("tempUserId").toString(),
										session.get("userId").toString());
							}
							System.out.println("TEST1"+exist);
						}

					}else{ //ログインカートリスト < ゲスト用カートリスト　以外のケース
						i=0;
						for(i=0;i<tempProductIdList.size();i++){
							boolean exist=productIdList.contains(tempProductIdList.get(i));
							if(exist){
								cartInfoDAO.changeProductStockId(Integer.valueOf(tempCartList.get(i).getProductCount()),
										Integer.valueOf(tempProductIdList.get(i)),session.get("userId").toString());
								cartInfoDAO.deleteSeparate(session.get("tempUserId").toString(),
										Integer.valueOf(tempProductIdList.get(i)));
							}else{
								cartInfoDAO.changeUserId(session.get("tempUserId").toString(),
										session.get("userId").toString());
							}
							System.out.println("TEST2"+ exist);
						}
					}

					//cartInfoDAO内のchangeUserIdメソッドを使用、SQLのUPDATE文にてtempUserIdに一致するtemp_user_idを
					//持つユーザーのuser_idとtemp_user_idをuserIdで上書きしている
					cartInfoDAO.changeUserId(session.get("tempUserId").toString(),session.get("userId").toString());
					//userIdの情報すべてを引き出すメソッドを代入
					cartList=cartInfoDAO.showUserCartList(session.get("userId").toString());
					destinationInfoListDTO=destinationInfoDAO
							.obtainingDestinationInfo(session.get("userId").toString());


					//合計金額の計算
					totalPrice=calcTotalPrice(cartList);

					//カート、宛先情報を引き継ぐ
					System.out.println("kessai:"+kessai);

					if(kessai==1){
						if((boolean) session.get("loginFlg")){
							destinationInfoListDTO=destinationInfoDAO
									.obtainingDestinationInfo(session.get("userId").toString());
						}

						if(destinationInfoListDTO.size()>0){
							result=SUCCESS;
						}else if(!(boolean) session.get("loginFlg")){
							result=ERROR;
							kessai=1;
							return result;
						}else{
							result="destination";
							return result;
						}

						System.out.println("LoginAction:kessaiは1");

						//合計金額の計算
						totalPrice=calcTotalPrice(cartList);//calc???????????適応するものを調べる必要あり
						return KESSAI;
					}

				}else{
					errorMessageList.add("入力されたパスワードが異なります。");
					result=ERROR;
				}
			}
		}

		return result;

	}

	//get set
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}

	public boolean isSaveLogin(){ //booleanだからis
		return saveLogin;
	}
	public void setSaveLogin(boolean saveLogin){
		this.saveLogin=saveLogin;
	}

	public Map<String,Object> getSession(){
		return session;
	}
	public void setSession(Map<String,Object> session){
		this.session=session;
	}

	public ArrayList<String> getErrorMessageList(){
		return errorMessageList;
	}
	public void setErrorMessageList(ArrayList<String> errorMessageList){
		this.errorMessageList=errorMessageList;
	}

	public int getKessai(){
		return kessai;
	}
	public void setKessai(int kessai){
		this.kessai=kessai;
	}

	public String getFamilyName(){
		return familyName;
	}
	public void setFamilyName(String familyName){
		this.familyName=familyName;
	}

	public String getFamilyNameKana(){
		return familyNameKana;
	}
	public void setFamilyNameKana(String familyNameKana){
		this.familyNameKana=familyNameKana;
	}

	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}

	public String getFirstNameKana(){
		return firstNameKana;
	}
	public void setFirstNameKana(String firstNameKana){
		this.firstNameKana=firstNameKana;
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

	public ArrayList<DestinationInfoDTO> getDestinationInfoListDTO(){
		return destinationInfoListDTO;
	}
	public void setDestinationInfoListDTO(ArrayList<DestinationInfoDTO> destinationInfoListDTO){
		this.destinationInfoListDTO=destinationInfoListDTO;
	}

	public ArrayList<CartInfoDTO> getCartList(){
		return cartList;
	}
	public void setCartList(ArrayList<CartInfoDTO> cartList){
		this.cartList=cartList;
	}


	//合計金額を計算するメソッド
	public int calcTotalPrice(ArrayList<CartInfoDTO> cartList){
		int totalPrice = 0; //初期合計金額0
		for(CartInfoDTO cartInfoDTO : cartList){ //拡張for文　for(型 変数名 : 式){文}
			//cartInfoDTOにcartListの値を代入して配列の数だけcartInfoDTOの表示を繰り返す
			totalPrice += cartInfoDTO.getPrice() * cartInfoDTO.getProductCount();
			System.out.println("合計" + totalPrice + "円");
		}
		return totalPrice;
	}

	public int getTotalPrice(){
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice){
		this.totalPrice=totalPrice;
	}
}


/*
	//ログインID
	private String loginId;
	//ログインPASSWORD
	private String loginPassword;
	//処理結果を格納
	private String result;
	//ログイン情報を格納
	public Map<String,Object> session;
	//ログイン情報を取得するDAO
	private LoginDAO loginDAO=new LoginDAO();
	//取得したログイン情報を格納するDTO
	private LoginDTO loginDTO=new LoginDTO();
	//以下エラー時用
	public String idErrorMsg;
	public String passwordErrorMsg;
	//i
	public int i;

	//public ArrayList<UserCreateCompleteDTO>
	//private ArrayList<String> errorMessageList=new ArrayList<>();


//手直し中
	public String execute() throws SQLException{
		result =ERROR;

		//ここで一旦情報を解除？
		session.put("unknown", "");
		session.put("idError", "");
		session.put("passwordError", "");

		//IDについて
//		Pattern p1 =Pattern.compile("^[0-9a-zA-Z]*$");
//		Matcher m1= p1.matcher(loginId);

		//エラー対応
		//ここからの注釈部分はとりあえず写経しただけ　後で改変します
//ここから↓
		//ID
		if(m1.find()==false){
			String idErrorMsg="IDは半角英数字で入力してください";
			this.idErrorMsg=idErrorMsg;
		}

		int length1=loginId.getBytes().length;
		if(length1<1/*最小文字数よりも少なかった場合*){
			String idErrorMsg="IDは半角1文字以上8文字以内で入力してください";
			this.idErrorMsg=idErrorMsg;
		}else if(length1>8/*最大文字数よりも多かった場合*){
			String idErrorMsg="IDは半角1文字以上8文字以内で入力してください";
			this.idErrorMsg=idErrorMsg;
		}else if(length1==0/*文字数が0だった場合*){
			String idErrorMsg="IDを入力してください";
			this.idErrorMsg=idErrorMsg;


		}

		//Pass
		Pattern p2=Pattern.compile("^[0-9a-zA-Z]*$");
		Matcher m2=p2.matcher(loginPassword);

		if(m2.find()==false/*最小文字数よりも少なかった場合*){
			String passwordErrorMsg="パスワードは半角英数字で入力してください";
			this.passwordErrorMsg=passwordErrorMsg;
		}

		int length2=loginPassword.getBytes().length;
		if(length2 < 1/*最小文字数よりも少なかった場合*){
			String passwordErrorMsg="パスワードは半角1文字以上16文字以内で入力してください";
			this.passwordErrorMsg=passwordErrorMsg;
		}else if(length2 > 16/*最大文字数よりも多かった場合*){
			String passwordErrorMsg="パスワードは半角1文字以上16文字以内で入力してください";
			this.passwordErrorMsg=passwordErrorMsg;
		}else if(length2 == 0/*文字数が0だった場合*){
			String passwordErrorMsg="パスワードを入力してください";
			this.passwordErrorMsg=passwordErrorMsg;
		}


		loginDTO=loginDAO.loginUserInfo(loginId,loginPassword);

		System.out.println(loginDTO.getLoginId());
		System.out.println(loginDTO.getLoginPassword());

		if(session.containsKey("status")){
		}else{
			session.put("status", "");
		}

		if(!(session.containsKey("temp_user_id"))){
			Random rnd=new Random();
			session.put("temp_user_id", rnd);
		}

		System.out.println(loginDTO.getLoginId());
		System.out.println(loginDTO.getLoginPassword());
//ここまで↑確認しておこう！
		/**
		 * 管理者画面へのログイン機能
		 *
			if(idErrorMsg==null && passwordErrorMsg==null){
				if(loginDTO.getLoginId().equals(""/*ここに管理者用のIDを入れて*)){
					if(loginDTO.getLoginPassword().equals(""/*ここに管理者用のPASSを入れて*)){
						session.put("master_flg", "1");
						session.put("idError", "");
						session.put("passwordError", "");
						result ="master";
						return result;
					}
					/**
					 * 通常ログイン機能
					 * どこからログインしてるかの判定、どこへ戻るのかのコード
					 *
				}else if(loginDTO.getLoginId().equals(loginId)){
					if(loginDTO.getLoginPassword().equals(loginPassword)){
						session.put("trueId", loginId);
						session.put("loginId", loginDTO.getLoginId());//使ってない？
						session.put("loginPass", loginDTO.getLoginPassword());
						session.put("firstName", loginDTO.getFirstName());
						session.put("familyName",loginDTO.getFamilyName());
						session.put("firstNameKana",loginDTO.getFirstNameKana());
						session.put("familyName",loginDTO.getFamilyNameKana());
						session.put("sex",loginDTO.getSex());
						session.put("email", loginDTO.getEmail());

						if(session.get("status")==("settlement"/*決済*)){
							session.put("idError","");
							session.put("passwordError", "");
							session.put("status","");

							i=loginDAO.cartInfo(session.get("temp_user_id").toString(),loginDTO.getLoginId());

							if(i>=0){
								result ="settlement";
								return result;
							}

						}else if(session.get("status")==("cart"/*カート*)){
							session.put("idError", "");
							session.put("passwordError", "");
							session.put("status", "");

							i=loginDAO.cartInfo(session.get("temp_user_id").toString(),loginDTO.getLoginId());

							if(i>=0){
								result ="cart";
								return result;
							}

						}else{
							session.put("idError", "");
							session.put("passwordError", "");
							session.put("status", "");

							i=loginDAO.cartInfo(session.get("temp_user_id").toString(),loginDTO.getLoginId());

							if(i>=0){
								result ="myPage";
								return result;
							}

						}

					}else if(loginDTO.getLoginId().equals("noID") || loginDTO.getLoginPassword().equals("noPASS")){
						session.put("unknown","入力されたIDもしくはパスワードが異なります");
						result = ERROR;
						return result;
					}

				}
					session.put("idError",this.idErrorMsg);
					session.put("passwordError", this.passwordErrorMsg);
					return result;
			}
	}
		@Override
		public void setSession(Map<String,Object> session){
			this.session=session;
		}

		public String getLoginId(){
			return loginId;
		}
		public void setLoginId(String loginId){
			this.loginId=loginId;
		}
		public String getLoginPassword(){
			return loginPassword;
		}
		public void setLoginPassword(String loginPassword){
			this.loginPassword=loginPassword;
		}
		public int getI(){
			return i;
		}
		public void setI(int i){
			this.i=i;
		}

	}
*/
/*		String result = ERROR;
		loginDTO=loginDAO.getLoginUserInfo(loginId,loginPassword);
		session.put("loginUser", loginDTO);

		if(((LoginDTO) session.get("loginUser")).getLoginFlg()){
			result = SUCCESS;
			session.put("login_user_id",loginDTO.getLoginId());
		→これをあとで追加	session.put("loginUser",loginFlg);
			return result;

		}
		return result;
	}
/**
 * カート内容の引継ぎ、仮IDと登録済みIDとのリンク機能、それに関するDBとの通信機能、
 * 未入力欄がある場合のエラーメッセージを追加したい
 * @return
 */
/*
	public String getLoginUserId(){
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId){
		this.loginUserId=loginUserId;
	}
	public String getLoginPassword(){
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword){
		this.loginPassword=loginPassword;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session=session;
	}
*/


