package com.internousdev.alatanapizza.util;

import java.util.ArrayList;

import com.internousdev.alatanapizza.dto.ProductDTO;



/**
 * ページネーションが必要なリストのために、1ページごとの情報を保持するためのクラス
 * @version 1.0
 */
public class PageObject {

	/**
	 * ページを識別するID
	 */
	private int pageId;

	/**
	 * 当該1ページに掲載される商品のリスト
	 */
	private ArrayList<ProductDTO> paginatedItemList = new ArrayList<ProductDTO>();




	/**
	 * ページネートされた商品リストを取得するメソッド
	 * @return  paginatedItemList
	 */
	public ArrayList<ProductDTO> getPaginatedList() {
			return paginatedItemList;
			}

	/**
	 * ページネートされた商品リストを格納するメソッド
	 * @param list ページネーションが完了した1ページごとのリスト
	 */
	public void setPaginatedList(ArrayList<ProductDTO> list) {
			paginatedItemList = list;
	}

	/**
	 * AllPagesクラスのpaginate()において使われる
	 * これによってDTOのインスタンスが当該ページにひとつずつ格納されていく
	 * @param list これからページネーション処理を行うリスト
	 * @param j 繰り返し用変数
	 */
	public void addPaginatedList(ArrayList<ProductDTO> list, int j) {
			paginatedItemList.add((ProductDTO)list.get(j));
			}



	/**
	 * ページID取得メソッド
	 * @return pageId
	 */
	public int getPageId() {
		return pageId;
	}

	/**
	 * ページID格納メソッド
	 * @param pageId セットする pageId
	 */
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
}