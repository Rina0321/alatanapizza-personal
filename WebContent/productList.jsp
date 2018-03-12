<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>商品一覧</title>
</head>
<body>
	<h3>商品一覧</h3>

	<!-- 検索時のメッセージ
	<s:if test="searchMessage != ''">
		<h2>
			<s:property value="searchMessage" escape="false" />
		</h2>
	</s:if>
-->


<ul>
	<!-- 表示件数1ページ目 -->


	<s:iterator value="displayList">
	<a href="<s:url action="ProductDetailsAction"><s:param name="product_id" value="%{product_id}" /></s:url>">

	<img class="image" src="<s:property value='image_file_path'/>" alt="Photo" width="200" height="170"><br>
	</a>
		商品名:<s:property value="product_name" /><br>
		商品名かな:<s:property value="product_name_kana" /><br>
		<s:if test="category_id==2"><br>
		価格:(M)￥<s:property value="msize_price" />円 &nbsp(L)￥<s:property value="lsize_price" />円<br>
		</s:if>
		<s:if test="category_id==3"><br>
		サイドメニュー:￥<s:property value="price"/>円<br>
		</s:if>
		<s:if test="category_id==4"><br>
		ドリンク:￥<s:property value="price"/>円<br>
		</s:if>
		商品詳細:<s:property value="product_description" /><br>
	</s:iterator>
</ul>



<!-- リストにデータが入っている時-->
<s:if test="number > 0">

<div class="center" style="text-align:center;">
						<!-- ページネーション:1ページ目のみ -->
						<s:if test="pageNum == 1">
						  <span>&laquo;<s:text name="戻る" /></span>
						</s:if>

						<!-- ページネーション:1ページ目以外 -->
						<s:else>
							<a href='<s:url action="ProductListAction">
							<s:param name="pageNum" value="pageNum-1"/>
							</s:url>'>&laquo;<s:text name="戻る" /></a>

						</s:else>


                        <s:property value="pageNum" />


						<!-- ページネーション:最終ページ -->
						<s:if test="pageNum == maxPage">
						<s:text name="進む" />&raquo;
						</s:if>


						<!-- 最終ページ以外 -->
						<s:else>
							<a href='<s:url action="ProductListAction">
							<s:param name="pageNum" value="pageNum+1"/>
							</s:url>'><s:text name="進む" />&raquo;</a>

						</s:else>


				</div>
		</s:if>



<!--
${pageSelect +1}

<s:if test="pageSelect > 0">
	<form action="ItemSearchAction">
		<s:hidden name="searchWord" value="%{searchWord}" />
		<s:hidden name="category" value="%{category}" />
		<s:hidden name="pageSelect" value="%{pageSelect - 1}" />
		<div class=pageback>
			<s:submit value=" 前へ" />
		</div>
	</form>
</s:if>

<s:if test="pageSelect < productListBy9Items.size() -1">
	<form action="ItemSearchAction" method="post">
		<s:hidden name="searchWord" value="%{searchWord}" />
		<s:hidden name="category" value="%{category}" />
		<s:hidden name="pageSelect" value="%{pageSelect + 1}" />
		<s:submit value=" 次へ" />
	</form>
</s:if>


-->
</body>
</html>