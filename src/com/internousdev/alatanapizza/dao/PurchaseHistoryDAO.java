package com.internousdev.alatanapizza.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.alatanapizza.dto.PurchaseHistoryDTO;
import com.internousdev.alatanapizza.util.DBConnector;
//①購入履歴表示（デフォルトは日付順）
//②購入履歴削除(個別削除・全部削除）
//③並び替え（高い順・安い順）
//④検索フォームもつける予定！　→商品グループのやつをぱくる

public class PurchaseHistoryDAO {

/*--------------------------------------------------------------
 * 購入履歴表示メソッド
 * @param userId
 * @purchaseHistoryDTOList
------------------------------------------------ */


	public ArrayList<PurchaseHistoryDTO> getPurchaseHistory(String userId) throws SQLException{
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<PurchaseHistoryDTO> purchaseHistoryDTOList = new ArrayList<PurchaseHistoryDTO>();

		//購入履歴のテーブルに商品テーブルをくっつけて表示！(LEFT JOIN)
		//"product_Id"で紐づけている。　

		//piはproduct_infoの略
		//phiはpurchase_history_infoの略

		//★ ORDER BY regist_date DESC ★デフォルトの日付順！sort == 1
		String sql = "SELECT phi.id, pi.product_name, pi.product_name_kana, pi.image_file_name,  phi.price, phi.product_count, pi.release_company, pi.release_date, phi.regist_date  FROM purchase_history_info phi LEFT JOIN product_info pi ON phi.product_id = pi.product_id  WHERE phi.user_id = ? ORDER BY regist_date DESC";


		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				PurchaseHistoryDTO dto = new PurchaseHistoryDTO();
				dto.setId(rs.getString("id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setPrice(rs.getInt("price"));
				dto.setCount(rs.getInt("product_count"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setReleaseDate(rs.getString("release_date"));
				dto.setRegistDate(rs.getString("regist_date"));
				dto.setImageFileName(rs.getString("image_file_name"));

				purchaseHistoryDTOList.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally{
			con.close();
		}
		return purchaseHistoryDTOList;
	}

	//--------------------------------------------------------------
	//	金額高い順にならびかえるメソッド
	//--------------------------------------------------------------
	public ArrayList<PurchaseHistoryDTO> sortPriceDesc(String userId) throws SQLException{
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<PurchaseHistoryDTO> purchaseHistoryDTOList = new ArrayList<PurchaseHistoryDTO>();

		//phiはpurchase_history_infoの略 注文履歴テーブル
		 //piはproduct_infoの略　商品テーブル


		//★ORDER BY price DESC　値段の高い順 sort == 2
		String sql = "SELECT phi.id, pi.product_name, pi.product_name_kana, pi.image_file_name,  phi.price, phi.product_count, pi.release_company, pi.release_date, phi.regist_date  "
				+ "FROM purchase_history_info phi "
				+ "LEFT JOIN product_info pi "
				+ "ON phi.product_id = pi.product_id  "
				+ "WHERE phi.user_id = ? ORDER BY price DESC";


		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				PurchaseHistoryDTO dto = new PurchaseHistoryDTO();
				dto.setId(rs.getString("id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setPrice(rs.getInt("price"));
				dto.setCount(rs.getInt("product_count"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setReleaseDate(rs.getString("release_date"));
				dto.setRegistDate(rs.getString("regist_date"));
				dto.setImageFileName(rs.getString("image_file_name"));

				purchaseHistoryDTOList.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally{
			con.close();
		}
		return purchaseHistoryDTOList;
	}

	 //--------------------------------------------------------------
	 //	金額安い順にならびかえるメソッド　DESCとASC以外は、高い順と同じ内容
	 //--------------------------------------------------------------
	public ArrayList<PurchaseHistoryDTO> sortPriceAsc(String userId) throws SQLException{
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<PurchaseHistoryDTO> purchaseHistoryDTOList = new ArrayList<PurchaseHistoryDTO>();


		 //piはproduct_infoの略
		 //phiはpurchase_history_infoの略

		//★ORDER BY price ASC 値段の安い順　sort == 3
		String sql = "SELECT phi.id, pi.product_name, pi.product_name_kana, pi.image_file_name,  phi.price, phi.product_count, pi.release_company, pi.release_date, phi.regist_date  FROM purchase_history_info phi LEFT JOIN product_info pi ON phi.product_id = pi.product_id  WHERE phi.user_id = ? ORDER BY price ASC";


		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				PurchaseHistoryDTO dto = new PurchaseHistoryDTO();
				dto.setId(rs.getString("id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setPrice(rs.getInt("price"));
				dto.setCount(rs.getInt("product_count"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setReleaseDate(rs.getString("release_date"));
				dto.setRegistDate(rs.getString("regist_date"));
				dto.setImageFileName(rs.getString("image_file_name"));

				purchaseHistoryDTOList.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally{
			con.close();
		}
		return purchaseHistoryDTOList;
	}

	//--------------------------------------------------------------
	// 全削除するメソッド　
	//--------------------------------------------------------------*/
	public int deleteHistory(String userId) throws SQLException{
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		//(deleteFlg.equals("1"))
		String sql = "DELETE FROM purchase_history_info where user_id = ? ";


		//★result★
			int result = 0;
		try{	PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userId);    //◆userId◆　そのユーザーの履歴をすべて削除

			result = ps.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			con.close();
		}
		return result;

	}



	 //--------------------------------------------------------------
	 //※※◆下記削除予定◆※※
	 //削除ボタンで1件ずつ削除するメソッド	 //
	 //--------------------------------------------------------------
	public int deletePart(int id) throws SQLException{
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "DELETE  FROM purchase_history_info where id = ?";
		PreparedStatement ps;
		int result2 = 0;
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			result2 = ps.executeUpdate();
			System.out.println(result2);

			con.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return result2;
	}

	//---------------------------------------------------------------------------------
	//選択削除メソッド　選択したものだけを削除 こっちを使うよ！
	//jspからのcheckBoxのcheckListを取得
	//<s:checkbox name="checkFlg" value="2" fieldValue="%{id}" />
	//---------------------------------------------------------------------------------
	public int deleteChoose(List<String> checkList) throws SQLException{
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		//(checkFlg.equals("2"))
		String sql = "DELETE  FROM purchase_history_info where id = ?";

		PreparedStatement ps;
		int result3 = 0;
		try{
			ps = con.prepareStatement(sql);

			for(int i = 0;i < checkList.size();i++){
				String checkId = checkList.get(i);
				ps.setString(1, checkId);
				result3 += ps.executeUpdate();
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			con.close();
		}
		return result3;
	}


}



