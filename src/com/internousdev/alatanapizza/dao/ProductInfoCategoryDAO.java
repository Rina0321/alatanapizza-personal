package com.internousdev.alatanapizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.alatanapizza.dto.ProductDTO;
import com.internousdev.alatanapizza.util.DBConnector;

//こちらの商品も一緒にいかがですか？のDAO　担当：上原
//決済確認を押す前に表示する。カートアクション内で。
//
public class ProductInfoCategoryDAO {

	public ArrayList<ProductDTO> notSameCategoryList(String user_id) throws SQLException {
		ArrayList<ProductDTO> categoryList = new ArrayList<ProductDTO>();
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		// カートに入っている商品のカテゴリーIDを検索して、
		// そのカテゴリーを除外した商品をランダムに３件表示
		// 例）ピザ（ｶﾃｺﾞﾘｰ１）がカートに入っていたら、
		// サイドメニュー（ｶﾃｺﾞﾘｰ２）と飲み物（ｶﾃｺﾞﾘｰ３）を表示させる。
		String sql = "SELECT product_name producy_name_kana product image_file_path image_file_name"
				+ "FROM product_info" + "WHERE category_id"
				+ "NOT IN (SELECT category_id FROM cart_info WHERE user_id = ?)" + "ORDER BY RAND() LIMIT 3";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_name_kana(rs.getString("product_name_kana"));
				dto.setImage_file_name(rs.getString("image_file_name"));
				dto.setImage_file_path(rs.getString("image_file_path"));

				categoryList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return categoryList;
	}

}//
