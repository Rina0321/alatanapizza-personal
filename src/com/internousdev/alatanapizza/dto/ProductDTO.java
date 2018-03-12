package com.internousdev.alatanapizza.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.internousdev.alatanapizza.dao.CategoryDAO;


public class ProductDTO {
    private int id; //ID
    private int product_id; //商品ID
    private int category_id; //カテゴリーID
    private int topping_id; //トッピングID
    private int msize_price; //Mサイズ価格
    private int lsize_price; //Lサイズ価格
    private int price; //サイドメニュー、ドリンク価格
    private int stock; //在庫
    private int status; //ステータス
    private String product_name; //商品名
    private String product_name_kana; //商品かな
    private String product_description; //商品詳細
    private String image_file_path; //画像ファイルパス
    private String image_file_name; //画像ファイル名
    private String release_date; //発売年月日
    private String release_company; //発売会社
    private String category_name; //カテゴリー名
    private String topping_name; //トッピング名
    private Date regist_date; //ピザ登録日
    private Date update_date; //更新日
    private Date insert_date; //トッピング登録日




    public String getCategoryName() {
       return category_name;
    }
    public void setCategoryName(String categoryName) {
       this.category_name = categoryName;
    }

    public void setCategoryName(Integer category_id) {
    	List<CategoryDTO> categoryList = new ArrayList<>();
       CategoryDAO categoryDAO = new CategoryDAO();
       categoryList = categoryDAO.getCategoryInfo();

       for(int i = 0; i < categoryList.size(); i++) {
    	   if(categoryList.get(i).getCategory_id().equals(category_id.toString())) {
				this.category_name = categoryList.get(i).getCategory_name();
          }
       }
    }

    public int getId() {
       return this.id;
    }

    public void setId(int id) {
       this.id = id;
    }

    public int getProduct_id() {
       return this.product_id;
    }

    public void setProduct_id(int product_id) {
       this.product_id = product_id;
    }

    public String getProduct_name() {
       return this.product_name;
    }

    public void setProduct_name(String product_name) {
       this.product_name = product_name;
    }

    public String getProduct_name_kana() {
       return this.product_name_kana;
    }

    public void setProduct_name_kana(String product_name_kana) {
       this.product_name_kana = product_name_kana;
    }

    public String getProduct_description() {
       return this.product_description;
    }

    public void setProduct_description(String product_description) {
       this.product_description = product_description;
    }

    public int getCategory_id() {
       return this.category_id;
    }

    public void setCategory_id(int category_id) {
       this.category_id = category_id;
    }

    public int getMsize_price() {
        return this.msize_price;
     }

     public void setMsize_price(int msize_price) {
        this.msize_price = msize_price;
     }

     public int getLsize_price() {
         return this.lsize_price;
      }

      public void setLsize_price(int lsize_price) {
         this.lsize_price = lsize_price;
      }

    public int getPrice() {
       return this.price;
    }

    public void setPrice(int price) {
       this.price = price;
    }

    public String getImage_file_path() {
        return this.image_file_path;
     }

     public void setImage_file_path(String image_file_path) {
        this.image_file_path= image_file_path;
     }

    public String getImage_file_name() {
       return this.image_file_name;
    }

    public void setImage_file_name(String image_file_name) {
       this.image_file_name = image_file_name;
    }

    public String getRelease_date() {
       return this.release_date;
    }

    public void setRelease_date(String release_date) {
       this.release_date = release_date;
    }

    public String getRelease_company() {
       return this.release_company;
    }

    public void setRelease_company(String release_company) {
       this.release_company = release_company;
    }

    public Date getRegist_date() {
       return this.regist_date;
    }

    public void setRegist_date(Date regist_date) {
       this.regist_date = regist_date;
    }

    public Date getUpdate_date() {
       return this.update_date;
    }

    public void setUpdate_date(Date update_date) {
       this.update_date = update_date;
    }

    public int getStock() {
       return this.stock;
    }

    public void setStock(int stock) {
       this.stock = stock;
    }

    public int getStatus() {
       return this.status;
    }
    public void setStatus(int status) {
       this.status = status;
    }

    public int getTopping_id() {
        return this.topping_id;
     }

     public void setTopping_id(int topping_id) {
        this.topping_id = topping_id;
     }

     public String getTopping_name() {
         return this.topping_name;
      }

      public void setTopping_name(String topping_name) {
         this.topping_name = topping_name;
      }

      public Date getInsert_date() {
          return this.insert_date;
       }

       public void setInsert_date(Date insert_date) {
          this.insert_date = insert_date;
       }

       public String getCategory_name() {
           return this.category_name;
        }

        public void setCategory_name(String category_name) {
           this.category_name = category_name;
        }

 }
