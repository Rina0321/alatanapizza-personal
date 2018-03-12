package com.internousdev.alatanapizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.alatanapizza.dto.ProductDTO;
import com.internousdev.alatanapizza.util.DBConnector;
import com.internousdev.alatanapizza.util.DateUtil;


public class ProductListDAO {
    private DBConnector db = new DBConnector();
    private Connection con = db.getConnection();



    /* ���i���X�g���̎擾 */
    // ���[�U�[�ɕ\�����郊�X�g
    public ArrayList<ProductDTO> getProductInfo() throws SQLException {
       ArrayList<ProductDTO> productList = new ArrayList<>();

       //�X�e�[�^�X�������A���iID���~����
       String sql = "SELECT * FROM product_info WHERE status = 1 ORDER BY Product_id ASC";

       try {
          PreparedStatement ps = con.prepareStatement(sql);

          ResultSet rs = ps.executeQuery();

          while(rs.next()) {
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
             productList.add(dto);

          }
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          con.close();
       }
       return productList;
    }


    // ���[�U�[�ɔ�\���̃��X�g
    public ArrayList<ProductDTO> getProductHideInfo() throws SQLException {
       ArrayList<ProductDTO> productList = new ArrayList<>();

       //�X�e�[�^�X���L���A���iID���~����
       String sql = "SELECT * FROM product_info WHERE status = 1 ORDER BY Product_id ASC";

       try {
          PreparedStatement ps = con.prepareStatement(sql);

          ResultSet rs = ps.executeQuery();

          while(rs.next()) {
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
             productList.add(dto);

          }
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          con.close();
       }
       return productList;
    }


    // ���iID�����ɑ��݂��Ă��邩�`�F�b�N
    public boolean existsProductId(String productId) throws SQLException {
       boolean result = false;

       String sql = "SELECT * FROM product_info WHERE product_id = ?";

       try {
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1, productId);
          ResultSet rs = ps.executeQuery();

          if(rs.next()) {
             result = true;
          }
       } catch (SQLException e) {
          e.printStackTrace();
       } finally {
          con.close();
       }
       return result;
    }


    // �V���i��o�^
    public int productRegist(String product_id, String product_name, String product_name_kana, String product_description, Integer category_id,Integer msize_price,Integer lsize_price, Integer price, String image_file_path, String image_file_name, String release_date, String release_company) throws SQLException {
       DateUtil dateUtil = new DateUtil();

       String sql = "INSERT INTO product_info (" + "product_id," + "product_name," + "product_name_kana,"
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

    /* �g�b�s���O���̎擾 */
    public ArrayList<ProductDTO> getToppingInfo() throws SQLException {
        ArrayList<ProductDTO> toppingList = new ArrayList<>();

        //���iID���~����
        String sql = "SELECT * FROM m_topping WHERE ORDER BY Product_id ASC";

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
           }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           con.close();
        }
        return toppingList;
    }

 }