package com.internousdev.alatanapizza.action;
//こちらの商品も一緒にいかがですか？のAction　担当：上原
//決済確認を押す前に表示する。カートアクション内で。
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.ProductInfoCategoryDAO;
import com.internousdev.alatanapizza.dto.ProductDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductInfoCategoryAction extends ActionSupport implements SessionAware {

	// セッション情報取得
	public Map<String, Object> session;

	// こちらの商品もどうですか？情報リスト
	public List<ProductDTO> notSameCategoryList = new ArrayList<ProductDTO>();
	public ProductDTO dto = new ProductDTO();
	public ProductInfoCategoryDAO dao = new ProductInfoCategoryDAO();



	public String execute() throws SQLException {

		try {
			notSameCategoryList = dao.notSameCategoryList(session.get("userId").toString());
			if (notSameCategoryList != null) {
				session.put("notSameCategoryList", notSameCategoryList);
				session.put("product_name", dto.getProduct_name());
				session.put("product_name_kana", dto.getProduct_name_kana());
				session.put("image_file_name", dto.getImage_file_name());
				session.put("image_file_path", dto.getImage_file_path());



			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		String result = SUCCESS;
		return result;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

}
