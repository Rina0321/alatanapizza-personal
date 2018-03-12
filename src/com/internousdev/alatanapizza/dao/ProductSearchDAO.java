package com.internousdev.alatanapizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.alatanapizza.dto.ProductSearchDTO;
import com.internousdev.alatanapizza.util.DBConnector;

/**
 * @author internousdev
 * @author kei-kenmochi
 *
 */
public class ProductSearchDAO {
	private DBConnector db = new DBConnector();
	private Connection con = db.getConnection();
	private ArrayList<ProductSearchDTO> searchDTOList = new ArrayList<ProductSearchDTO>();

	/**
	 * 全ての商品を表示
	 *
	 * @return ProductsearchDTOList
	 */
	public ArrayList<ProductSearchDTO> allProductInfo() {
		String sql = "SELECT*FROM product_info";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductSearchDTO searchDTO = new ProductSearchDTO();
				searchDTO.setId(rs.getInt("id"));
				searchDTO.setProductId(rs.getInt("product_id"));
				searchDTO.setProductName(rs.getString("product_name"));
				searchDTO.setProductNameKana(rs.getString("product_name_kana"));
				searchDTO.setProductDescription(rs.getString("product_description"));
				searchDTO.setCategoryId(rs.getInt("category_id"));
				searchDTO.setPrice(rs.getInt("price"));
				searchDTO.setImageFileName(rs.getString("image_file_name"));
				searchDTO.setReleaseDate(rs.getDate("release_date"));
				searchDTO.setReleaseCompany(rs.getString("release_company"));
				searchDTO.setStatus(rs.getShort("status"));
				searchDTO.setRegistDate(rs.getDate("regist_date"));
				searchDTO.setUpdateDate(rs.getDate("update_date"));
				searchDTOList.add(searchDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDTOList;

	}

	/**
	 * 商品IDのみで検索
	 *
	 * @return searchDTOList
	 */
	public ArrayList<ProductSearchDTO> ByPrductId(int uniqueId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "SELECT*FROM product_info WHERE product_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, uniqueId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductSearchDTO serchDTO = new ProductSearchDTO();
				serchDTO.setId(rs.getInt("id"));
				serchDTO.setProductId(rs.getInt("product_id"));
				serchDTO.setProductName(rs.getString("product_name"));
				serchDTO.setProductNameKana(rs.getString("product_name_kana"));
				serchDTO.setProductDescription(rs.getString("product_description"));
				serchDTO.setCategoryId(rs.getInt("category_id"));
				serchDTO.setPrice(rs.getInt("price"));
				serchDTO.setImageFileName(rs.getString("image_file_name"));
				serchDTO.setReleaseDate(rs.getDate("release_date"));
				serchDTO.setReleaseCompany(rs.getString("release_company"));
				serchDTO.setStatus(rs.getShort("status"));
				serchDTO.setRegistDate(rs.getDate("regist_date"));
				serchDTO.setUpdateDate(rs.getDate("update_date"));
				searchDTOList.add(serchDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDTOList;

	}

	/**
	 * 商品IDとカテIDで検索
	 *
	 * @param uniqueId
	 * @param categoryId
	 * @return
	 */
	public ArrayList<ProductSearchDTO> ByPrductIdANDcate(int uniqueId, int categoryId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "SELECT*FROM product_info WHERE product_id=? AND category_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, uniqueId);
			ps.setInt(2, categoryId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductSearchDTO serchDTO = new ProductSearchDTO();
				serchDTO.setId(rs.getInt("id"));
				serchDTO.setProductId(rs.getInt("product_id"));
				serchDTO.setProductName(rs.getString("product_name"));
				serchDTO.setProductNameKana(rs.getString("product_name_kana"));
				serchDTO.setProductDescription(rs.getString("product_description"));
				serchDTO.setCategoryId(rs.getInt("category_id"));
				serchDTO.setPrice(rs.getInt("price"));
				serchDTO.setImageFileName(rs.getString("image_file_name"));
				serchDTO.setReleaseDate(rs.getDate("release_date"));
				serchDTO.setReleaseCompany(rs.getString("release_company"));
				serchDTO.setStatus(rs.getShort("status"));
				serchDTO.setRegistDate(rs.getDate("regist_date"));
				serchDTO.setUpdateDate(rs.getDate("update_date"));
				searchDTOList.add(serchDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDTOList;

	}

	/**
	 * 検索ワードのみで検索
	 *
	 * @param serchWord
	 * @return searchDTOList
	 */
	public ArrayList<ProductSearchDTO> BySerchWord(String serchWord) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "SELECT*FROM product_info WHERE product_name LIKE \'%' ? \'%' OR product_name_kana LIKE \'%' ? \'%' ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, serchWord);
			ps.setString(2, serchWord);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductSearchDTO serchDTO = new ProductSearchDTO();
				serchDTO.setId(rs.getInt("id"));
				serchDTO.setProductId(rs.getInt("product_id"));
				serchDTO.setProductName(rs.getString("product_name"));
				serchDTO.setProductNameKana(rs.getString("product_name_kana"));
				serchDTO.setProductDescription(rs.getString("product_description"));
				serchDTO.setCategoryId(rs.getInt("category_id"));
				serchDTO.setPrice(rs.getInt("price"));
				serchDTO.setImageFileName(rs.getString("image_file_name"));
				serchDTO.setReleaseDate(rs.getDate("release_date"));
				serchDTO.setReleaseCompany(rs.getString("release_company"));
				serchDTO.setStatus(rs.getShort("status"));
				serchDTO.setRegistDate(rs.getDate("regist_date"));
				serchDTO.setUpdateDate(rs.getDate("update_date"));

				searchDTOList.add(serchDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDTOList;

	}

	/**
	 * カテゴリのみで検索
	 *
	 * @param categoryId
	 * @return searchDTOList
	 */
	public ArrayList<ProductSearchDTO> ByProductCategory(int categoryId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<ProductSearchDTO> searchDTOList = new ArrayList<ProductSearchDTO>();
		String sql = "SELECT*FROM product_info WHERE category_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductSearchDTO serchDTO = new ProductSearchDTO();
				serchDTO.setId(rs.getInt("id"));
				serchDTO.setProductId(rs.getInt("product_id"));
				serchDTO.setProductName(rs.getString("product_name"));
				serchDTO.setProductNameKana(rs.getString("product_name_kana"));
				serchDTO.setProductDescription(rs.getString("product_description"));
				serchDTO.setCategoryId(rs.getInt("category_id"));
				serchDTO.setPrice(rs.getInt("price"));
				serchDTO.setImageFileName(rs.getString("image_file_name"));
				serchDTO.setReleaseDate(rs.getDate("release_date"));
				serchDTO.setReleaseCompany(rs.getString("release_company"));
				serchDTO.setStatus(rs.getShort("status"));
				serchDTO.setRegistDate(rs.getDate("regist_date"));
				serchDTO.setUpdateDate(rs.getDate("update_date"));

				searchDTOList.add(serchDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDTOList;

	}

	/**
	 * カテゴリと検索ワード
	 *
	 * @param categoryId
	 * @param serchWord
	 * @return searchDTOList
	 */
	public ArrayList<ProductSearchDTO> ByCategoryANDSerchWord(int categoryId, String serchWord) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<ProductSearchDTO> searchDTOList = new ArrayList<ProductSearchDTO>();
		String sql = "SELECT*FROM product_info WHERE product_name LIKE \'%' ? \'%' AND category_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, serchWord);
			ps.setInt(2, categoryId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductSearchDTO serchDTO = new ProductSearchDTO();
				serchDTO.setId(rs.getInt("id"));
				serchDTO.setProductId(rs.getInt("product_id"));
				serchDTO.setProductName(rs.getString("product_name"));
				serchDTO.setProductNameKana(rs.getString("product_name_kana"));
				serchDTO.setProductDescription(rs.getString("product_description"));
				serchDTO.setCategoryId(rs.getInt("category_id"));
				serchDTO.setPrice(rs.getInt("price"));
				serchDTO.setImageFileName(rs.getString("image_file_name"));
				serchDTO.setReleaseDate(rs.getDate("release_date"));
				serchDTO.setReleaseCompany(rs.getString("release_company"));
				serchDTO.setStatus(rs.getShort("status"));
				serchDTO.setRegistDate(rs.getDate("regist_date"));
				serchDTO.setUpdateDate(rs.getDate("update_date"));

				searchDTOList.add(serchDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDTOList;

	}

	/**
	 * ひらがな検索
	 *
	 * @param serchWord
	 * @return searchDTOList
	 */
	public ArrayList<ProductSearchDTO> BySerchWordKana(String serchWord) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<ProductSearchDTO> searchDTOList = new ArrayList<ProductSearchDTO>();
		String sql = "SELECT*FROM product_info WHERE product_name_kana LIKE \'%' ? \'%'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, serchWord);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductSearchDTO serchDTO = new ProductSearchDTO();
				serchDTO.setId(rs.getInt("id"));
				serchDTO.setProductId(rs.getInt("product_id"));
				serchDTO.setProductName(rs.getString("product_name"));
				serchDTO.setProductNameKana(rs.getString("product_name_kana"));
				serchDTO.setProductDescription(rs.getString("product_description"));
				serchDTO.setCategoryId(rs.getInt("category_id"));
				serchDTO.setPrice(rs.getInt("price"));
				serchDTO.setImageFileName(rs.getString("image_file_name"));
				serchDTO.setReleaseDate(rs.getDate("release_date"));
				serchDTO.setReleaseCompany(rs.getString("release_company"));
				serchDTO.setStatus(rs.getShort("status"));
				serchDTO.setRegistDate(rs.getDate("regist_date"));
				serchDTO.setUpdateDate(rs.getDate("update_date"));

				searchDTOList.add(serchDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDTOList;

	}

	/**
	 * カテゴリとカナで検索
	 *
	 * @param categoryId
	 * @param serchWord
	 * @return searchDTOList
	 */
	public ArrayList<ProductSearchDTO> ByCategoryANDSerchWordKana(int categoryId, String serchWord) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<ProductSearchDTO> searchDTOList = new ArrayList<ProductSearchDTO>();
		String sql = "SELECT*FROM product_info WHERE product_name_kana LIKE \'%' ? \'%' AND category_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, serchWord);
			ps.setInt(2, categoryId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductSearchDTO serchDTO = new ProductSearchDTO();
				serchDTO.setId(rs.getInt("id"));
				serchDTO.setProductId(rs.getInt("product_id"));
				serchDTO.setProductName(rs.getString("product_name"));
				serchDTO.setProductNameKana(rs.getString("product_name_kana"));
				serchDTO.setProductDescription(rs.getString("product_description"));
				serchDTO.setCategoryId(rs.getInt("category_id"));
				serchDTO.setPrice(rs.getInt("price"));
				serchDTO.setImageFileName(rs.getString("image_file_name"));
				serchDTO.setReleaseDate(rs.getDate("release_date"));
				serchDTO.setReleaseCompany(rs.getString("release_company"));
				serchDTO.setStatus(rs.getShort("status"));
				serchDTO.setRegistDate(rs.getDate("regist_date"));
				serchDTO.setUpdateDate(rs.getDate("update_date"));

				searchDTOList.add(serchDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDTOList;

	}

	public ArrayList<ProductSearchDTO> selectBywords(String[] serchWords) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<ProductSearchDTO> searchDTOList = new ArrayList<ProductSearchDTO>();
		String sql = "SELECT*FROM product_info WHERE ";

		for (int s = 0; 0 < serchWords.length; s++) {
			if (s != 0) {
				sql = sql + " AND (product_name LIKE '%" + serchWords[s] + "%' OR product_name_kana LIKE '%"
						+ serchWords[s] + "%') ";

			} else {
				sql = sql + " (product_name LIKE '%" + serchWords[s] + "%' OR product_name_kana LIKE '%" + serchWords[s]
						+ "%') ";

			}
		}

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductSearchDTO serchDTO = new ProductSearchDTO();
				serchDTO.setId(rs.getInt("id"));
				serchDTO.setProductId(rs.getInt("product_id"));
				serchDTO.setProductName(rs.getString("product_name"));
				serchDTO.setProductNameKana(rs.getString("product_name_kana"));
				serchDTO.setProductDescription(rs.getString("product_description"));
				serchDTO.setCategoryId(rs.getInt("category_id"));
				serchDTO.setPrice(rs.getInt("price"));
				serchDTO.setImageFileName(rs.getString("image_file_name"));
				serchDTO.setReleaseDate(rs.getDate("release_date"));
				serchDTO.setReleaseCompany(rs.getString("release_company"));
				serchDTO.setStatus(rs.getShort("status"));
				serchDTO.setRegistDate(rs.getDate("regist_date"));
				serchDTO.setUpdateDate(rs.getDate("update_date"));

				searchDTOList.add(serchDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDTOList;

	}

	/*
	 * public ArrayList<SearchDTO> MultiWords(int categoryId, String[]
	 * serchWord) { String sql =
	 * "SELECT FROM product_info WHERE product_name LIKE \'%'" +serchWord+
	 * "\'%' AND category_id=?";
	 *
	 * for(int i=0;i<=serchWord.length;i++){
	 *
	 * }
	 *
	 * try { PreparedStatement ps = con.prepareStatement(sql); ps.setLong(1,
	 * serchWord); ps.setInt(2, categoryId); ResultSet rs = ps.executeQuery();
	 *
	 * while (rs.next()) { SearchDTO serchDTO = new SearchDTO();
	 * serchDTO.setId(rs.getInt("id"));
	 * serchDTO.setProductId(rs.getInt("product_id"));
	 * serchDTO.setProductName(rs.getString("product_name"));
	 * serchDTO.setProductNameKana(rs.getString("product_name_kana"));
	 * serchDTO.setProductDescription(rs.getString("product_description"));
	 * serchDTO.setCategoryId(rs.getInt("category_id"));
	 * serchDTO.setPrice(rs.getInt("price"));
	 * serchDTO.setImageFileName(rs.getString("image_file_name"));
	 * serchDTO.setReleaseDate(rs.getDate("release_date"));
	 * serchDTO.setReleaseCompany(rs.getString("release_company"));
	 * serchDTO.setStatus(rs.getShort("status"));
	 * serchDTO.setRegistDate(rs.getDate("regist_date"));
	 * serchDTO.setUpdateDate(rs.getDate("update_date"));
	 *
	 * searchDTOList.add(serchDTO);
	 *
	 * } } catch (SQLException e) { e.printStackTrace(); } catch (Exception e) {
	 * e.printStackTrace(); } return searchDTOList;
	 *
	 * }
	 */
}

