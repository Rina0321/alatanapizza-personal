package com.internousdev.alatanapizza.dto;

public class CategoryDTO {
    private String id; //ID
    private String category_id; //カテゴリーID
    private String category_name; //カテゴリー名
    private String category_description; //カテゴリー詳細
    private String insert_date; //登録日
    private String update_date; //更新日

    public String getId() {
       return this.id;
    }
    public void setId(String id) {
       this.id = id;
    }
    public String getCategory_id() {
       return this.category_id;
    }
    public void setCategory_id(String category_id) {
       this.category_id = category_id;
    }
    public String getCategory_name() {
       return this.category_name;
    }
    public void setCategory_name(String category_name) {
       this.category_name = category_name;
    }
    public String getCategory_description() {
       return this.category_description;
    }
    public void setCategory_description(String category_description) {
       this.category_description = category_description;
    }
    public String getInsert_date() {
       return this.insert_date;
    }
    public void setInsert_date(String insert_date) {
       this.insert_date = insert_date;
    }
    public String getUpdate_date() {
       return this.update_date;
    }
    public void setUpdate_date(String update_date) {
       this.update_date = update_date;
    }
 }
