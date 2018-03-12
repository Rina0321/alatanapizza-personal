package com.internousdev.alatanapizza.dto;

public class PurchaseHistoryDTO {

	//<購入履歴DTO>
	//実際に表示する際は、商品テーブルとくっつけた表を表示する！

	// 商品名
	private String productName;

	// ふりがな
	private String productNameKana;

	// 商品画像
	private String imageFileName;

	// 値段
	private int price;

	// 個数
	private int count;

	// 発売会社名
	private String releaseCompany;

	// 発売年月日
	private String releaseDate;

	private String id;

	private String productId;

	private String productcount;

	private String registDate;








	// 商品名
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	// ふりがな
	public String getProductNameKana() {
		return productNameKana;
	}

	public void setProductNameKana(String productNameKana) {
		this.productNameKana = productNameKana;
	}

	// 商品画像




	// 値段
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	//画像
	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}


	// 個数
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// 発売会社名
	public String getReleaseCompany() {
		return releaseCompany;
	}

	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}

	// 発売年月日
	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductId() {
		return 	productId;
		}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public String getRegistDate() {
		return 	registDate;
		}

	public void setProductCount(String productcount) {
		this.productcount = productcount;
	}

	public String getProductCount() {
		return 	productcount;
		}



}
