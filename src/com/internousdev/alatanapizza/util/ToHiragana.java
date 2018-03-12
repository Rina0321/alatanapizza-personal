package com.internousdev.alatanapizza.util;

/**
 * @author internousdev
 * @author kei-kenmochi
 *
 */
public class ToHiragana {
	private String searchWord;

	  public String toZenkakuHiragana(String searchWord) {
		    StringBuffer sb = new StringBuffer(searchWord);
		    for (int i = 0; i < sb.length(); i++) {
		      char c = sb.charAt(i);
		      if (c >= 'ァ' && c <= 'ン') {
		        sb.setCharAt(i, (char)(c - 'ァ' + 'ぁ'));
		      } else if (c == 'ヵ') {
		        sb.setCharAt(i, 'か');
		      } else if (c == 'ヶ') {
		        sb.setCharAt(i, 'け');
		      } else if (c == 'ヴ') {
		        sb.setCharAt(i, 'う');
		        sb.insert(i + 1, '゛');
		        i++;
		      }
		    }
		    System.out.println(searchWord);
			return sb.toString();
		  }

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}


}
