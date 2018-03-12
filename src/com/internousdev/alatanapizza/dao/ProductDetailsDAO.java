package com.internousdev.alatanapizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.alatanapizza.dto.ProductDTO;
//import com.internousdev.alatanapizza.dto.Review2DTO;
import com.internousdev.alatanapizza.util.DBConnector;

public class ProductDetailsDAO {

	// 商品詳細情報取得( getProductDetailsInfo)
	public ProductDTO getProductDetailsInfo(String product_id) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ProductDTO dto = new ProductDTO();
		String sql = "SELECT * FROM product_info where product_id=? AND status = 1";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, product_id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setProduct_id(rs.getInt("product_id"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_name_kana(rs.getString("product_name_kana"));
				dto.setProduct_description(rs.getString("product_description"));
				dto.setCategory_name(rs.getString("category_name"));
				dto.setCategory_id(rs.getInt("category_id"));
				dto.setMsize_price(rs.getInt("msize_price"));
				dto.setLsize_price(rs.getInt("lsize_price"));
				dto.setPrice(rs.getInt("price"));
				dto.setImage_file_path(rs.getString("image_file_path"));
				dto.setImage_file_name(rs.getString("image_file_name"));
				dto.setRelease_date(rs.getString("release_date"));
				dto.setRelease_company(rs.getString("release_company"));
				dto.setRegist_date(rs.getDate("regist_date"));
				dto.setUpdate_date(rs.getDate("update_date"));
				dto.setStock(rs.getInt("stock"));
			} else {
				dto = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return dto;
	}

	// 商品詳細情報取得(getProductDetailsInfoList)
	public List<ProductDTO> getProductDetailsInfoList(String[] productIdList) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		ArrayList<ProductDTO> detailsList = new ArrayList<>();
		for (int i = 0; i < productIdList.length; i++) {

			String sql = "SELECT * FROM product_info where product_id = ? AND status = 1";

			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, String.valueOf(productIdList[i]));
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					ProductDTO dto = new ProductDTO();

					dto.setId(rs.getInt("id"));
					dto.setProduct_id(rs.getInt("product_id"));
					dto.setProduct_name(rs.getString("product_name"));
					dto.setProduct_name_kana(rs.getString("product_name_kana"));
					dto.setProduct_description(rs.getString("product_description"));
					dto.setCategory_id(rs.getInt("category_id"));
					dto.setMsize_price(rs.getInt("msize_price"));
					dto.setLsize_price(rs.getInt("lsize_price"));
					dto.setPrice(rs.getInt("price"));
					dto.setImage_file_path(rs.getString("image_file_path"));
					dto.setImage_file_name(rs.getString("image_file_name"));
					dto.setRelease_date(rs.getString("release_date"));
					dto.setRelease_company(rs.getString("release_company"));
					dto.setRegist_date(rs.getDate("regist_date"));
					dto.setUpdate_date(rs.getDate("update_date"));
					dto.setStock(rs.getInt("stock"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return detailsList;
	}


	// おすすめ商品リスト
	public ArrayList<ProductDTO> getSuggestProductInfo(String product_id) throws SQLException {
		ArrayList<ProductDTO> suggestList = new ArrayList<ProductDTO>();
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		//商品をランダムに3件表示させる
		String sql = "SELECT * FROM product_info WHERE status = 1 AND product_id NOT IN(?) ORDER BY RAND() LIMIT 3";


		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, product_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductDTO dto = new ProductDTO();

				dto.setId(rs.getInt("id"));
				dto.setProduct_id(rs.getInt("product_id"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_name_kana(rs.getString("product_name_kana"));
				dto.setProduct_description(rs.getString("product_description"));
				dto.setCategory_id(rs.getInt("category_id"));
				dto.setMsize_price(rs.getInt("msize_price"));
				dto.setLsize_price(rs.getInt("lsize_price"));
				dto.setPrice(rs.getInt("price"));
				dto.setImage_file_path(rs.getString("image_file_path"));
				dto.setImage_file_name(rs.getString("image_file_name"));
				dto.setRelease_date(rs.getString("release_date"));
				dto.setRelease_company(rs.getString("release_company"));
				dto.setRegist_date(rs.getDate("regist_date"));
				dto.setUpdate_date(rs.getDate("update_date"));
				dto.setStock(rs.getInt("stock"));

				suggestList.add(dto);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return suggestList;
	}

	// レビュー情報取得
	/*public ArrayList<Review2DTO> getReviewInfo(String product_id) throws SQLException {

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		ArrayList<Review2DTO> reviewList = new ArrayList<Review2DTO>();

		String sql = "SELECT * FROM review_info JOIN product_info ON review_info.product_id = product_info.product_id where review_info.product_id=? ORDER BY buy_item_date DESC";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, product_id);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Review2DTO review2DTO = new Review2DTO();

				review2DTO.setUser_id(resultSet.getString("user_id"));
				review2DTO.setProduct_id(resultSet.getInt("product_id"));
				review2DTO.setReview_id(resultSet.getString("review_id"));
				review2DTO.setBuy_item_date(resultSet.getDate("buy_item_date"));
				review2DTO.setEvaluation_count(resultSet.getInt("evaluation_count"));

				reviewList.add(review2DTO);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return reviewList;
	}
*/

	/*
	// 商品情報UPDATE
	public int changeProductData(String product_id, String product_name, String product_name_kana, String product_description, Integer category_id,Integer msize_price,Integer lsize_price, Integer price, String image_file_path, String image_file_name, String release_date, String release_company) throws SQLException {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		DateUtil dateUtil = new DateUtil();

		String sql = "UPDATE  product_info (" + "product_id," + "product_name," + "product_name_kana,"
				+ "product_description," + "category_id," +"msize_price"+"lsize_price"+ "price," + "image_file_path," + "image_file_name,"
				+ "release_date," + "release_company," + "regist_date)" + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		int res = 0;

		try {
			 PreparedStatement ps = con.prepareStatement(sql);
	          ps.setString(1, product_id);
	          ps.setString(2, product_name);
	          ps.setString(3, product_name_kana);
	          ps.setString(4, product_description);
	          ps.setInt(5, category_id);
	          ps.setInt(6, msize_price);
	          ps.setInt(7, lsize_price);
	          ps.setInt(8, price);
	          ps.setString(9, image_file_path);
	          ps.setString(10, image_file_name);
	          ps.setString(11, release_date);
	          ps.setString(12, release_company);
	          ps.setString(13, dateUtil.getDate());

			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return res;
	}
*/
	/*
	// 商品情報表示⇔非表示切り替え
	public int productRestoreHide(String productId) throws SQLException {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		int res = 0;

		String sql = "UPDATE product_info SET " + "status = 1 - status " + "WHERE product_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, productId);

			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return res;
	}
*/
    /* トッピング情報の取得 */
    public ArrayList<ProductDTO> getToppingInfo() throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
        ArrayList<ProductDTO> toppingList = new ArrayList<ProductDTO>();

        String sql = "SELECT * FROM m_topping";

        try {
           PreparedStatement ps = con.prepareStatement(sql);

           ResultSet rs = ps.executeQuery();

           while(rs.next()) {
              ProductDTO dto = new ProductDTO();
              dto.setId(rs.getInt("id"));
              dto.setTopping_id(rs.getInt("topping_id"));
              dto.setTopping_name(rs.getString("topping_name"));
              dto.setMsize_price(rs.getInt("msize_price"));
              dto.setLsize_price(rs.getInt("lsize_price"));
              dto.setInsert_date(rs.getDate("insert_date"));
              dto.setUpdate_date(rs.getDate("update_date"));
              toppingList.add(dto);
           }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           con.close();
        }
        return toppingList;
    }



}