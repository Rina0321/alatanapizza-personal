package com.internousdev.alatanapizza.dao;
	//カート内の削除DAOクラス（担当：上原）
	//①ログインしている場合
//			（１）全削除メソッド
//			（２）チェックしたものだけを削除するメソッド
	//②ログインしていない場合（ゲストユーザー）
//			（１）全削除メソッド
//			（２）チェックしたものだけを削除するメソッド

	//※４つのメソッドを作るのがめんどいと思ったが、これしか方法はないのでござる。
	//なお、ログインしているか否かは、しっかりと判別する必要あり。
	//（SQLでカラムを使用する際に、user_idなのか、temp_idなのか入力する必要があるから！）

	//又、カート内だけではなく、購入後のカート全削除の場合も、この(１)メソッドを使用。
	//↑使用場所（PurchaseCompleteAction)
	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.alatanapizza.util.DBConnector;


	public class CartDeleteDAO {

		private DBConnector db=new DBConnector();
		private Connection con=db.getConnection();



		//①－(1)　ログインしているときに、全削除　＆　決済後のカート情報削除------------------------------
		public int AlldeleteCart(String userId){

			String sql="DELETE FROM cart_info WHERE userId=?";
			int res =0;
			try{
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, userId);
				res=ps.executeUpdate();


			}catch(SQLException e){
				e.printStackTrace();

			}return res;
		}

		//①－(2)　ログインしているときに、チェックしたものだけを削除--------------------------------------
		public int PartDeleteCart(String userId,String productid){

			String sql="DELETE FROM cart_info WHERE userId=? AND id=?";
			int res=0;
			try{
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,userId);
				ps.setString(2,productid);
				res=ps.executeUpdate();

			}catch(SQLException e){
				e.printStackTrace();

			}return res;
		}


		//②－(1)　ログインしていない時、全削除----------------------------------------------------------
		public int AlldeleteCartGest(String tempUserId){

			String sql="DELETE FROM cart_info WHERE temp_user?_id";
			int res=0;

			try{
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, tempUserId);
				res=ps.executeUpdate();


			}catch(SQLException e){
				e.printStackTrace();

			}return res;
		}

		//②－(2)　ログインしていない時に、チェックしたものだけを削除---------------------------------------
			public int PartDeleteCartGest(String tempUserId,String productid){

				String sql="DELETE FROM cart_info WHERE userId=? AND id=?";
				int res=0;

				try{
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setString(1,tempUserId);
					ps.setString(2,productid);
					res=ps.executeUpdate();


				}catch(SQLException e){
					e.printStackTrace();

				}return res;
			}





	}

