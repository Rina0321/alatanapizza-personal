package com.internousdev.alatanapizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.alatanapizza.dto.CategoryDTO;
import com.internousdev.alatanapizza.util.DBConnector;


public class CategoryDAO {
    private List<CategoryDTO> categoryList = new ArrayList<>();

    public List<CategoryDTO> getCategoryInfo() {

    	DBConnector db = new DBConnector();
       Connection con = db.getConnection();
       String sql = "SELECT * FROM m_category";

       try {
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();

          while(rs.next()) {
             CategoryDTO dto = new CategoryDTO();
             dto.setId(rs.getString("id"));
             dto.setCategory_id(rs.getString("category_id"));
             dto.setCategory_name(rs.getString("category_name"));
             dto.setCategory_description(rs.getString("category_description"));
             dto.setInsert_date(rs.getString("insert_date"));
             dto.setUpdate_date(rs.getString("update_date"));
             this.categoryList.add(dto);
          }
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          try {
             con.close();
          } catch (SQLException e) {
             e.printStackTrace();
          }      }

       return categoryList;
    }
 }

