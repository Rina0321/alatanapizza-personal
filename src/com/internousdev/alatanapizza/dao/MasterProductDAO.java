package com.internousdev.alatanapizza.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.alatanapizza.dto.ProductDTO;
import com.internousdev.alatanapizza.util.DBConnector;
import com.internousdev.alatanapizza.util.DateUtil;

public class MasterProductDAO {
	private DBConnector dbConnector=new DBConnector();

	private Connection connection=dbConnector.getConnection();

	private ProductDTO productDTO =new ProductDTO();

	private DateUtil dateUtil=new DateUtil();

	private String sql="INSERT INTO item_info_transaction (item_name, item_price, item_stock, insert_date) VALUES (?, ?, ?, ?)";

	public ProductDTO productDTO(String itemName, String itemPrice, String itemStock) throws SQLException{

		try{
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, itemName);
			ps.setString(2, itemPrice);
			ps.setString(3, itemStock);
			ps.setString(4, dateUtil.getDate());

			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}

		return productDTO;
	}
}
