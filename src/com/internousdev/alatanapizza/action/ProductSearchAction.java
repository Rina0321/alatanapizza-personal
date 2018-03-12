package com.internousdev.alatanapizza.action;

import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.ProductSearchDAO;
import com.internousdev.alatanapizza.dto.ProductSearchDTO;
import com.internousdev.alatanapizza.util.ToHiragana;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author internousdev
 * @author kei-kenmochi
 *
 */

public class ProductSearchAction extends ActionSupport implements SessionAware {
	private String searchWord;
	private String keyword;
	private int categoryId;
	private ProductSearchDAO searchDAO = new ProductSearchDAO();
	private List<ProductSearchDTO> searchDTOList = new ArrayList<ProductSearchDTO>();
	private ToHiragana toHiragana = new ToHiragana();
	private String[] searchWords;
	public Map<String, Object> session;
	private ArrayList<String> msgList = new ArrayList<String>();

	public String execute() throws SQLException {

		String ret = ERROR;

		if (searchWord.length() > 16) {
			msgList.add("16字以内で検索してください");
			ret = SUCCESS;
			return ret;
		} else {
			msgList.add(searchWord);
		}

		/*---------------------------------------------------------
				検索値を全て全角に変換、適切な値に加工
		-----------------------------------------------------------*/
		keyword = Normalizer.normalize(searchWord, Normalizer.Form.NFKC);
		keyword = toHiragana.toZenkakuHiragana(keyword);
		System.out.println(keyword);
		keyword = keyword.trim();
		if (keyword.matches("^[\\p{Punct}]+$")) {
			msgList.add("一般的な検索ワードを使ってください");
			ret = SUCCESS;
			return ret;
		}

		/*---------------------------------------------------------
				ピザを複数検索
		-----------------------------------------------------------*/
		/*
		空白の場所を確認
		*/
		int kuuhakunobasho = keyword.indexOf(" ");

		if (categoryId == 2 && kuuhakunobasho > 0) {

			List<ProductSearchDTO> searchDTOList = new ArrayList<ProductSearchDTO>();

			String[] searchWords = keyword.replace("|", "hogehoge").replace("_", "|_").replace("　", " ")
					.replace("~", "～").replace("%", "|%").split("[\\s]+");
			for (String str : searchWords) {
				System.out.println(str);
			}

			/*
			 * 検索開始
			 *
			 */
			searchDTOList = searchDAO.selectBywords(searchWords);


			for (int i = 0; i < searchDTOList.size(); i++) {

				System.out.println("検索結果は" + searchDTOList.get(i).getProductName());
			}
			ret = SUCCESS;
			return ret;

		}
		/*---------------------------------------------------------
					全件検索(カテゴリ、検索値なし)
		-----------------------------------------------------------*/

		else if (categoryId == 2 && keyword.isEmpty()) {
			setSearchDTOList(searchDAO.allProductInfo());
			ret = SUCCESS;

		}

		/*---------------------------------------------------------
				ひらがな、カタカナ検索
		-----------------------------------------------------------*/
		else if (categoryId == 2
				&& (keyword.matches("^[\\u3040-\\u30FF]+$") || keyword.matches("^[\\u30A0-\\u30FF]+$"))) {
			keyword = toHiragana.toZenkakuHiragana(keyword);
			System.out.println(keyword);
			setSearchDTOList(searchDAO.BySerchWordKana(keyword));
			ret = SUCCESS;
		}

		/*---------------------------------------------------------
				カテゴリ有り、検索値なし
		-----------------------------------------------------------*/
		else if (categoryId > 1 && keyword.isEmpty()) {

			setSearchDTOList(searchDAO.ByProductCategory(categoryId));
			ret = SUCCESS;
		}

		/*---------------------------------------------------------
				カテゴリなし、検索値あり
		-----------------------------------------------------------*/
		else if (categoryId == 2 && !(keyword.isEmpty())) {
			setSearchDTOList(searchDAO.BySerchWord(keyword));
			ret = SUCCESS;
		}

		keyword = getSearchWord();
		return ret;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public ProductSearchDAO getSearchDAO() {
		return searchDAO;
	}

	public void setSearchDAO(ProductSearchDAO searchDAO) {
		this.searchDAO = searchDAO;
	}

	public List<ProductSearchDTO> getSearchDTOList() {
		return searchDTOList;
	}

	public void setSearchDTOList(List<ProductSearchDTO> searchDTOList) {
		this.searchDTOList = searchDTOList;
	}

	public ToHiragana getToHiragana() {
		return toHiragana;
	}

	public void setToHiragana(ToHiragana toHiragana) {
		this.toHiragana = toHiragana;
	}

	public ArrayList<String> getMsgList() {
		return msgList;
	}

	public void setMsgList(ArrayList<String> msgList) {
		this.msgList = msgList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String[] getSearchWords() {
		return searchWords;
	}

	public void setSearchWords(String[] searchWords) {
		this.searchWords = searchWords;
	}

}
