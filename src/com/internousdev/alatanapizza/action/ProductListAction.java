package com.internousdev.alatanapizza.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.alatanapizza.dao.ProductListDAO;
import com.internousdev.alatanapizza.dto.ProductDTO;
import com.internousdev.alatanapizza.util.AllPages;
import com.internousdev.alatanapizza.util.PageObject;
import com.opensymphony.xwork2.ActionSupport;


public class ProductListAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -8716863678316756041L;
	//セッション情報取得
	public Map<String, Object> session;
	//商品情報取得
    private ProductListDAO productListDAO = new ProductListDAO();
    //商品情報格納
    public ArrayList<ProductDTO> productList = new ArrayList<>();
    //productListを9個ごとに格納したList
    private ArrayList<ArrayList<ProductDTO>> productListBy9Items = new ArrayList<>();
    private int pageSelect;
    private int pageCount;
    private List<Integer> pageList = new ArrayList<>();


    /**
	 * 検索数
	 */
	public int number;

	private int maxPage;
	public ArrayList<ProductDTO> displayList = new ArrayList<ProductDTO>();

	private int pageNum=1;



    /* 商品情報取得メソッド */
    public String execute() throws SQLException {

        String result = ERROR;

//    	int all=0;

        productList = productListDAO.getProductInfo();

//        all=productList.size();

        number=productList.size();

        Iterator<ProductDTO> iterator = productList.iterator();
        if(!iterator.hasNext()) {
            this.productList = null;
        }

        //productListを9個ごとに格納
//        ProductListUtil productListUtil = new ProductListUtil();
//        productListBy9Items = productListUtil.devideProductListBy9Items(productList);
//
//        for (int i = 0; i < productListBy9Items.size(); i++) {
//			pageList.add(i + 1);
//		}
//
//        for (int i = 0; i < productListBy9Items.size(); i++) {
//			if (i == pageSelect) {
//				productList = productListBy9Items.get(i);
//				break;
//            }
//        }





        if(number > 0) {
			//ページネーション処理
			ArrayList<PageObject> allPages = new ArrayList<PageObject>();
			AllPages allp = new AllPages();
			allPages=allp.paginate(productList, 9);
			setMaxPage(allp.getMaxPage(productList, 9));
			setDisplayList(allPages.get(pageNum-1).getPaginatedList());
			result = SUCCESS;
		}


		return result;
    }



    @Override
    public void setSession(Map<String, Object> session) {
       this.session = session;
    }

    public Map<String, Object> getSession() {
       return this.session;
    }

    public ProductListDAO getProductListDAO() {
       return this.productListDAO;
    }

    public void setProductListDAO(ProductListDAO productListDAO) {
       this.productListDAO = productListDAO;
    }

    public ArrayList<ProductDTO> getProductList() {
       return this.productList;
    }

    public void setProductList(ArrayList<ProductDTO> productList) {
       this.productList = productList;
    }



    public ArrayList<ArrayList<ProductDTO>> getProductListBy9Items() {
		return productListBy9Items;
	}



	public void setProductListBy9Items(ArrayList<ArrayList<ProductDTO>> productListBy9Items) {
		this.productListBy9Items = productListBy9Items;
	}



	public int getPageSelect() {
       return this.pageSelect;
    }

    public void setPageSelect(int pageSelect) {
       this.pageSelect = pageSelect;
    }

    public List<Integer> getPageList() {
       return this.pageList;
    }

    public void setPageList(List<Integer> pageList) {
       this.pageList = pageList;
    }

    public int getPageCount() {
       return this.pageCount;
    }

    public void setPageCount(int pageCount) {
       this.pageCount = pageCount;
    }



	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	}



	public int getMaxPage() {
		return maxPage;
	}



	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}



	public ArrayList<ProductDTO> getDisplayList() {
		return displayList;
	}



	public void setDisplayList(ArrayList<ProductDTO> displayList) {
		this.displayList = displayList;
	}



	public int getPageNum() {
		return pageNum;
	}



	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


 }