package com.internousdev.alatanapizza.action;

import java.io.File;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.MasterProductDAO;
import com.internousdev.alatanapizza.dto.ProductDTO;
import com.internousdev.alatanapizza.util.DateUtil;
import com.opensymphony.xwork2.ActionSupport;

public class MasterAddAction extends ActionSupport implements SessionAware {

	public void setImage(File image) {
		this.image = image;
	}

	private String imageContentType;
	private String imageFileName;

	private String itemName;

	private String itemPrice;

	private String itemStock;

	private File image;

	public File getImage() {
		return image;
	}

	public Map<String, Object> session;

	private DateUtil dateUtil = new DateUtil();

	private MasterProductDAO dao = new  MasterProductDAO();
	private ProductDTO dto = new ProductDTO();

	public String execute() throws SQLException {

		String result = ERROR;

		//文字列が空白でなければsuccessを返す。何か空白が入っている場合は、errorを返す。

		if (itemName.length() != 0 && itemPrice.length() != 0 && itemStock.length() != 0) {
			dto = dao.productDTO (itemName, itemPrice, itemStock);
			session.put("ItemInsert", dto);
			session.put("itemName", itemName);
			session.put("itemPrice", itemPrice);
			session.put("itemStock", itemStock);
			session.put("insertDate", dateUtil.getDate());

			result = SUCCESS;
			return result;
		}
		return result;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemStock() {
		return itemStock;
	}

	public void setItemStock(String itemStock) {
		this.itemStock = itemStock;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
