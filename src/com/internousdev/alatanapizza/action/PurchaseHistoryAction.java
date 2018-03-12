package com.internousdev.alatanapizza.action;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.PurchaseHistoryDAO;
import com.internousdev.alatanapizza.dto.PurchaseHistoryDTO;
import com.opensymphony.xwork2.ActionSupport;
//★注文履歴★(担当：上原）
//マイページからとんでくる！



public class PurchaseHistoryAction extends ActionSupport implements SessionAware{

	 //セッション
	public Map<String, Object> session;

	//商品購入履歴取得DAO
	private PurchaseHistoryDAO purchaseHistoryDAO = new PurchaseHistoryDAO();

	 //商品購入履歴格納DTO List
	public ArrayList<PurchaseHistoryDTO> historyList = new ArrayList<PurchaseHistoryDTO>();

	 //1 = 全件削除
	 //2 = 個別ボタン削除
	 //3 = チェックボックス削除

	private String deleteFlg;		//削除フラグ
	private String checkFlg;		//チェックリストの削除フラグ
	private String message;         //削除メッセージ
	private int id;			    	//個別削除id取得 DAOメソッドの戻り値
	private  List<String> checkList;//checkBoxの値
	private int sort;				//ソート




	 // 商品購入履歴取得メソッド
	public String execute()throws SQLException{

		//ログインしてなければログインページに移動
		if (!session.containsKey("userId")) {
			return ERROR;//■login.jspへ
		}

		String result = SUCCESS;
		String userId = (session.get("userId").toString());//sessionからuserId取得

		//もし削除フラグがなかったら、購入履歴を表示する。　
		//DAOのメソッドを呼びだそう！→getPurchaseHistory　
		//historyList=日付順の注文履歴

		//1
		if(deleteFlg == null){
			//↓DAOのメソッド↓
			historyList = purchaseHistoryDAO.getPurchaseHistory(userId);

			//↓iteratorで廻してあげる。iteratorはnewする必要がなく、iterator()を呼び出せばよい。
			Iterator<PurchaseHistoryDTO> it = historyList.iterator();

			if(!(it.hasNext())){
				historyList = null;
			}
		//2
		} else if(deleteFlg.equals("1")){  //全件削除メソッド（下にあるよお） //deleteFlg="1"
			delete();		//★
			historyList = purchaseHistoryDAO.getPurchaseHistory(userId);

		//3
		} else if(checkFlg.equals("2")){	//選択した項目を削除 //checkFlg="2"

			deleteChoose(checkList);//★
			historyList = purchaseHistoryDAO.getPurchaseHistory(userId);
		}



		//if (!(checkList == null)) {
		//for (String deleteId : checkList) {
		//	dao.deleteHistoryInfo(deleteId);
		//}

		//result = "delete";//購入履歴画面（SUCCESSと同じ効果）
	//}
		//




		 //ソート機能↓------------------------------------------------------
		if(sort == 1){ //デフォルトの注文日順
																	//System.out.println("注文日！");
		}else if(sort == 2){//値段の高い順
																//System.out.println("値段！！");
			historyList = purchaseHistoryDAO.sortPriceDesc(userId);

		}else if(sort == 3){//値段の安い順
			historyList = purchaseHistoryDAO.sortPriceAsc(userId);
		}
		return result;
	}


	  //全件削除メソッド----------------------------------------------------------
		public void delete() throws SQLException{

		String user_id = session.get("userId").toString();

		int res = purchaseHistoryDAO.deleteHistory(user_id);
		System.out.println("削除しようとする件数："+res);
		if(res > 0){
											//System.out.println("削除した");
			historyList = null;
			setMessage("注文履歴をすべて削除しました");
		}else if(res == 0){
										//System.out.println("削除失敗");
			setMessage("履歴の削除に失敗しました。");
		}

	}



	public void deleteChoose(List<String> checkList) throws SQLException{
		//jspからもってきたchooseList <s:checkbox name="checkList,,,,,>
		checkList = this.checkList;

		//何件削除したかもらう
			int res = purchaseHistoryDAO.deleteChoose(checkList);

		//削除したときのメッセージ
			if(res > 0){
				setMessage(res + "件削除しました");
			} else if(res == 0){
				setMessage("削除できませんでした！");
			}
	}



/*----------------------------------------------------------------

 ----------------------------------------------------------------*/



	 //deleteFlg
	public String getDeleteFlg(){
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg){
		this.deleteFlg = deleteFlg;
	}

	 //jspからIDもってくる
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}


	 //削除メッセージ
	public String getMessage(){
		return message;
	}
	public void setMessage(String message){
		this.message = message;
	}


	 // session
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session){
		this.session = session;
	}


	 //checkBoxの値
	public List<String> getChooseList(){
		return chooseList;
	}
	public void setChooseList(List<String> chooseList){
		this.chooseList = chooseList;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}


