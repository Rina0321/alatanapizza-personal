package com.internousdev.alatanapizza.action;
//決済確認画面　担当：上原


//<result>
//ERROR→ログインからやり直し（login.jspへ）
//SUCCESS→決済完了画面へ（settlement.jsp）
//DESTINATION→宛先新規登録画面へ（destinationRegister.jsp)
//other→カートが空です（cart.jspへ）

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.CartInfoDAO;
import com.internousdev.alatanapizza.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class BuyItemCompleteAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session; //
	private int totalPrice = 0; // 合計金額
	private int productCount; // 個数
	private List<CartInfoDTO> cartList = new ArrayList<CartInfoDTO>(); // カート情報一覧
	private ArrayList<DestinationInfoDTO> destinationInfoListDTO = new ArrayList<DestinationInfoDTO>();
	// ↑宛先情報一覧（ログイングループのをぱくる）

	public String execute() throws SQLException {
		String result = ERROR;
		System.out.println("PurchaseInfoAction--------------");

		// 決済情報取得メソッド
		// 確認画面の商品情報は、カート情報をそのままもってきている。（かねごん担当のCartInfoDAO）
		// だから、このActionにはDAOはないです。
		// 宛先情報とカーと情報をただのせるだけのページ。

		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		cartList = cartInfoDAO.showUserCartList(session.get("userId").toString());

		// ログインしてなければログインに飛ばす
		if (!session.containsKey("loginFlg")) {
			return ERROR; // ■login.jspへ
		}
		if (session.containsKey("loginFlg")) {
			DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
			destinationInfoListDTO = destinationInfoDAO.obtainingDestinationInfo(session.get("userId").toString());
		}

		if (destinationInfoListDTO.size() > 0) {
			result = SUCCESS;// ■決済完了画面へ（settlement.jsp）
		} else {
			result = "DESTINATION"; // ■宛先新規登録画面へ（destinationRegister.jsp)
			return result;
		}

		/**
		 * // 宛先情報取得メソッド 上の文に書き換えてみた↑ こっちがもともとの文面 if ((boolean)
		 * session.get("loginFlg")) { DestinationInfoDAO destinationInfoDAO =
		 * new DestinationInfoDAO(); destinationInfoListDTO =
		 * destinationInfoDAO.obtainingDestinationInfo(session.get("userId").
		 * toString()); }
		 *
		 * if (destinationInfoListDTO.size() > 0) { result = SUCCESS;//
		 * ■settlement.jspへ
		 *
		 * } else if (!(boolean) session.get("loginFlg")) { result = ERROR; //
		 * ■login.jspへ kessai = 1; return result;
		 *
		 * } else { result = "DESTINATION";
		 *
		 * }
		 *
		 */

		// カートに何も入っていない場合
		if (cartList.size() == 0) {
			return "other";// ■cart.jspへ。
		}
		for (CartInfoDTO dto : cartList) {
			totalPrice += dto.getPrice() * dto.getProductCount();
		}
		return result;
	}

	/**
	 * @return session
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	/**
	 * @param session
	 *            セットする session
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * @return cartInfoDTOList
	 */
	public List<CartInfoDTO> getCartList() {
		return cartList;
	}

	/**
	 * @param cartInfoDTOList
	 *            セットする cartInfoDTOList
	 */
	public void setCartList(List<CartInfoDTO> cartList) {
		this.cartList = cartList;
	}

	/**
	 * @return totalPrice
	 */
	public int getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            セットする totalPrice
	 */
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ArrayList<DestinationInfoDTO> getDestinationInfoListDTO() {
		return destinationInfoListDTO;
	}

	public void setDestinationInfoListDTO(ArrayList<DestinationInfoDTO> destinationInfoListDTO) {
		this.destinationInfoListDTO = destinationInfoListDTO;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

}

