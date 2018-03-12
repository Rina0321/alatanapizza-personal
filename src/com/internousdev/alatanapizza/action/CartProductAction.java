package com.internousdev.alatanapizza.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.CartInfoDAO;
import com.internousdev.alatanapizza.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class CartProductAction extends ActionSupport implements SessionAware{

	//商品ID
	private int productId;

	//セッション情報
	private Map<String,Object>session;

	//カート内の商品情報リスト
	private ArrayList<CartInfoDTO>cartList = new ArrayList<CartInfoDTO>();

	//カートの商品情報（個数、価格）
	private int productCount;

	private String price;
	private int count;
	private CartInfoDAO dao = new CartInfoDAO();

	//カート内の金額
	private int totalPrice = 0;
	public String execute()throws SQLException{
		int TotalPrice = Integer.parseInt(price);

		//"userId"を定義し、その中に"登録ユーザー"と"ゲストユーザー"を入れて処理する
		String userId;

		//"登録ユーザー"と"ゲストユーザー"のどちらでログインしているか確認し、定義した"userId"に代入する
		if((boolean)session.get("loginFlg")){
			userId =(String)session.get("userId");
		}
		else{
			userId =(String)session.get("tempUserId");
		}

		//カートが重複しているか確認する
		if(dao.isAlreadyIntoCart(userId, productId)){
			count = dao.UpdateProductCount(userId,productId,productCount,totalPrice);
		}else{
			count = dao.putProductIntoCart(userId,productId,productCount,totalPrice);
		}
		cartList = dao.showUserCartList(session.get("userId").toString());


		//検索画面で購入個数をマイナスにした場合は"CountError"を返して別のページに飛ぶ
		if(productCount < 0){
		   return "CountError";
		}
		totalPrice = calcTotalPrice(cartList);
		   return SUCCESS;
		}

	//合計金額計算
	public int calcTotalPrice(ArrayList<CartInfoDTO>cartList){
		int totalPrice = 0;
		for(CartInfoDTO dto : cartList){
		totalPrice += dto.getPrice()*dto.getProductCount();
		System.out.println("合計" + totalPrice + "円");
	}
		return totalPrice;
	}

	//セッションを【取得する】メソッド
	public Map<String,Object>getSession(){
		return session;
	}

	//セッションを【格納する】メソッド
	public void setSession(Map<String,Object>session){
		this.session = session;
	}

	//商品IDを【取得する】メソッド
	public String getProductId(){
		return Integer.valueOf(productId).toString();
	}

	//商品IDを【格納する】メソッド
	public void setProductId(String productId){
		this.productId = Integer.parseInt(productId);
	}

	//商品価格を【取得する】メソッド
	public String getPrice(){
		return price;
	}

	////商品価格を【格納する】メソッド
	public void setPrice(String price){
		this.price = price;
	}

	//カート情報を【取得する】メソッド
	public ArrayList<CartInfoDTO>getCartList(){
		return cartList;
	}

	//カート情報を【格納する】メソッド
	public void setCartList(ArrayList<CartInfoDTO>cartList){
		this.cartList = cartList;
	}

	//合計金額を【取得する】メソッド
	public int getTotalPrice(){
		return totalPrice;
	}

	//合計金額を【格納する】メソッド
	public void setTotalPrice(int totalPrice){
		this.totalPrice = totalPrice;
	}

	//カートの商品個数を【取得する】メソッド
	public int getProductCount(){
		return productCount;
	}

	//カートの商品個数を【格納する】メソッド
	public void setProductCount(int productCount){
		this.productCount = productCount;
	}

	//カート個数を【取得する】メソッド
	public int setCount(){
		return count;
	}

	//カートの個数を【格納する】メソッド
	public void setCount(int count){
		this.count = count;
	}
}