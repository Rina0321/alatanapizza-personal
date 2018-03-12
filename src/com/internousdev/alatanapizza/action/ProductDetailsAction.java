package com.internousdev.alatanapizza.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.ProductDetailsDAO;
import com.internousdev.alatanapizza.dto.ProductDTO;
//import com.internousdev.alatanapizza.dto.Review2DTO;
import com.opensymphony.xwork2.ActionSupport;


public class ProductDetailsAction extends ActionSupport implements SessionAware {

	// セッション情報取得
	public Map<String, Object> session;

	private String id;
	private String product_id;
	private String category_name;
	private String stock;
	private String error;
	private int insertFlg;
	private int msize_price;
	private int lsize_price;
	private int price;
	private int category_id;

	// 購入個数リスト
	private List<Integer> stockList = new ArrayList<Integer>();

	// 商品詳細情報リスト
	public ProductDTO detail = new ProductDTO();
	public List<ProductDTO> detailsList = new ArrayList<ProductDTO>();

	//トッピング情報格納
	public ArrayList<ProductDTO> toppingList = new ArrayList<>();

	// お勧めリスト情報
	public List<ProductDTO> suggestList = new ArrayList<ProductDTO>();

	// レビュー情報リスト
	//public List<Review2DTO> reviewList = new ArrayList<Review2DTO>();

	private ProductDetailsDAO productDetailsDAO = new ProductDetailsDAO();

	//private ProductListDAO productListDAO = new ProductListDAO();

	public String execute() throws SQLException {

		// String[] productIdList = product_id.split(", ", 0);

				// 商品詳細情報取得
				try {
					detail = productDetailsDAO.getProductDetailsInfo(product_id);
					if (detail != null) {
						session.put("d_image_file_path", detail.getImage_file_path());
						session.put("d_product_name_kana", detail.getProduct_name_kana());
						session.put("d_product_name", detail.getProduct_name());
						session.put("d_product_description", detail.getProduct_description());
						session.put("d_product_msize_price", detail.getMsize_price());
						session.put("d_product_lsize_price", detail.getLsize_price());
						session.put("d_product_price", detail.getPrice());
						session.put("d_release_date", detail.getRelease_date());
						session.put("d_release_company", detail.getRelease_company());
						session.put("d_stock", detail.getStock());
						session.put("d_product_id", detail.getProduct_id());
						session.put("d_category_id", detail.getCategory_id());
						session.put("d_category_name", detail.getCategory_name());

					} else {
						return ERROR;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// おすすめリスト情報取得
				try {
					suggestList = productDetailsDAO.getSuggestProductInfo(product_id);
					if (suggestList != null) {
						session.put("s_image_file_path", detail.getImage_file_path());
						session.put("s_product_name_kana", detail.getProduct_name_kana());
						session.put("s_product_name", detail.getProduct_name());
						session.put("s_product_description", detail.getProduct_description());
						session.put("s_product_msize_price", detail.getMsize_price());
						session.put("s_product_lsize_price", detail.getLsize_price());
						session.put("s_product_price", detail.getPrice());
						session.put("s_release_date", detail.getRelease_date());
						session.put("s_release_company", detail.getRelease_company());
						session.put("s_stock", detail.getStock());
						session.put("s_product_id", detail.getProduct_id());
						session.put("s_category_id", detail.getCategory_id());
					} else {
						return ERROR;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// レビュー情報取得メソッド
				/*try {

					reviewList = productDetailsDAO.getReviewInfo(session.get("d_product_id").toString());

				} catch (Exception e) {
					e.printStackTrace();
				}
*/

				// 1から在庫数までの選択表示用List
				for (int i = 1; i <= detail.getStock(); i++) {
					stockList.add(i);
				}

				if (session.get("d_category_id").equals("")) {
					return ERROR;
				}


				// トッピングメニュー
				toppingList = productDetailsDAO.getToppingInfo();
				session.put("toppingList", toppingList);

				String result = SUCCESS;
				return result;
			}



	@Override
    public void setSession(Map<String, Object> session) {
       this.session = session;
    }

    public Map<String, Object> getSession() {
       return this.session;
    }

			public String getProduct_id() {
				return product_id;
			}

			public void setProduct_id(String product_id) {
				this.product_id = product_id;
			}

			public String getStock() {
				return stock;
			}

			public void setStock(String stock) {
				this.stock = stock;
			}

			public int getMsize_price() {
				return msize_price;
			}

			public void setMsize_price(int msize_price) {
				this.msize_price = msize_price;
			}

			public int getLsize_price() {
				return lsize_price;
			}

			public void setLsize_price(int lsize_price) {
				this.lsize_price = lsize_price;
			}

			public int getPrice() {
				return price;
			}

			public void setPrice(int price) {
				this.price = price;
			}

			/**
			 * insertFlgを取得します。
			 *
			 * @return insertFlg
			 */
			public int getInsertFlg() {
				return insertFlg;
			}

			/**
			 * insertFlgを設定します。
			 *
			 * @param insertFlg
			 *            insertFlg
			 */
			public void setInsertFlg(int insertFlg) {
				this.insertFlg = insertFlg;
			}

			public List<Integer> getStockList() {
				return stockList;
			}

			public void setStockList(List<Integer> stockList) {
				this.stockList = stockList;
			}

			public List<ProductDTO> getDetailsList() {
				return detailsList;
			}

			public void setDetailsList(List<ProductDTO> detailsList) {
				this.detailsList = detailsList;
			}

			public ProductDetailsDAO getProductDetailsDAO() {
				return productDetailsDAO;
			}

			public void setProductDetailsDAO(ProductDetailsDAO productDetailsDAO) {
				this.productDetailsDAO = productDetailsDAO;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public int getCategory_id() {
				return category_id;
			}

			public void setCategory_name(String category_name) {
				this.category_name = category_name;
			}

			public String getCategory_name() {
				return category_name;
			}

			public void setCategory_id(int category_id) {
				this.category_id = category_id;
			}

			public String getError() {
				return error;
			}

			public void setError(String error) {
				this.error = error;
			}



			public ProductDTO getDetail() {
				return detail;
			}



			public ArrayList<ProductDTO> getToppingList() {
				return toppingList;
			}



			public List<ProductDTO> getSuggestList() {
				return suggestList;
			}



			public void setDetail(ProductDTO detail) {
				this.detail = detail;
			}



			public void setToppingList(ArrayList<ProductDTO> toppingList) {
				this.toppingList = toppingList;
			}



			public void setSuggestList(List<ProductDTO> suggestList) {
				this.suggestList = suggestList;
			}

	}

