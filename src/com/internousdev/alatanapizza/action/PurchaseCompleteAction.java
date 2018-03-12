package com.internousdev.alatanapizza.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.CartDeleteDAO;
import com.internousdev.alatanapizza.dao.PurchaseCompleteDAO;
import com.internousdev.alatanapizza.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

//①カート情報取得
//②購入履歴に登録
//③カートテーブルの情報を削除する
public class PurchaseCompleteAction extends ActionSupport implements SessionAware {


	// userId格納
	private String userId;
	// cartInfoDTO格納List
	private ArrayList<CartInfoDTO> cartList = new ArrayList<CartInfoDTO>();
	// session情報格納
	public Map<String, Object> session;
	// カートの合計金額
	private int totalPrice = 0;

	public String execute() throws SQLException {
		String result = ERROR;//(■cart.jspへ　これたぶん)
		/*---------------------------------------------------------
		①カート情報取得（getCartInfo）
		---------------------------------------------------------*/
		PurchaseCompleteDAO purchaseCompleteDAO = new PurchaseCompleteDAO();
		if (session.containsKey("userId")) {
			cartList = purchaseCompleteDAO.getCartInfo(session.get("userId").toString());

			if (cartList.size() == 0) {//カート情報がなかったら
				return "other";//■cart.jspへ
			}

			/* コンソールに処理を表示-------------------------------
			System.out.println("----PurchaseCompleteAction:execute");
			System.out.println(cartList.get(0).getUserId());
			System.out.println(cartList.get(0).getPrice());
			System.out.println(cartList.get(0).getProductId());
			System.out.println(cartList.get(0).getProductCount());
			System.out.println("------------------------");
			/*------------------------------------------------------*/

		/*---------------------------------------------------------
		②購入履歴に登録(setPurchseHistory)
		---------------------------------------------------------*/

			int i = purchaseCompleteDAO.setPurchseHistory(cartList);
			System.out.println(cartList);

		/*---------------------------------------------------------
		③カートテーブル情報を削除
		---------------------------------------------------------*/
			if (cartList.size() == i) {
				CartDeleteDAO deletedao = new CartDeleteDAO();
				deletedao.AlldeleteCart(session.get("userId").toString());
				result = SUCCESS;
			}
		}
		totalPrice = calcTotalPrice(cartList);
		return result;
	}


		//合計金額を計算メソッド
		public int calcTotalPrice(ArrayList<CartInfoDTO> cartList) {
			int totalPrice = 0;
			for (CartInfoDTO dto : cartList) {
				totalPrice += dto.getPrice() * dto.getProductCount();
				System.out.println("合計" + totalPrice + "円");
			}
			return totalPrice;
		}



	//	 ユーザーID
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	//
	public ArrayList<CartInfoDTO> getCartList() {
		return cartList;
	}

	public void setCartList(ArrayList<CartInfoDTO> cartList) {
		this.cartList = cartList;
	}
    //セッション
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}

