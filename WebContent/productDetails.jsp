<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品詳細画面</title>

<script src="./js//jquery-1.12.4.min.js">
	$(function() {
		$('input[name="optionsRadios"]:radio').change(function() {
			var radioval = $(this).val();
			if (radioval == 1) {
				$('#inputother').removeAttr('disabled');
			} else {
				$('#inputother').attr('disabled', 'disabled');
			}
		});

	});
</script>
<script>
	function test(){
		alert("OK");
	}
</sctipt>
<script>
	<!--
	function calc_total(){
	  kingaku = 0;
	  for (i=0; i<document.myform.length-1; i++){
	    if (document.myform.elements[i].checked){
	      kingaku += eval(document.myform.elements[i].value);
	    }
	  }
	  document.myform.goukei.value = kingaku + "　円";
	}
	//-->
</script>
<script>
	function outputSelectedValueAndText(obj) {
		var data = document.select.product_count.value;
		//商品詳細の計算式
		document.getElementById("productDetailPrice").value = data
						+ (<s:property value="session.s_product_msize_price"/> + <s:property value="s_product_lsize_price"/> + <s:property value="s_product_price"/>)
						+ $("#select").val();
		var idx = obj.options[idx].value;
		var text = obj.options[idx].text;
		alert(idx);
		alert(text);

		console.log('value= ' + value + ',' + 'text= ' + text);
	}
</script>
</head>
<body>



	<s:form action="GoCartAction" name="select">
		<!--商品詳細情報 -->
		<div class="main">
			<h3>
				<s:property value="session.d_category_name" />
			</h3>
			<div class="DetailsList">
				<div class="detailsList">
					<div class="img">
						<img class="image"
							src='<s:property value="session.d_image_file_path"/>' alt="Photo"
							width="400" height="270">
					</div>
					<table class="detailsTable">
						<tr>
							<td class="nowrap">
								<!-- 商品名 -->
							</td>
							<td><s:property value="session.d_product_name" /></td>
							<td class="nowrap">
								<!-- 商品名かな -->
							</td>
							<td><s:property value="session.d_product_name_kana" /></td>
						</tr>
						<!-- カテゴリーによって表示項目変更 -->
						<tr>
						<s:if test="category_id==2">
							<td class="nowrap">M</td>
							<td><input type="radio" name="optionsRadios"
								id="optionsRadios1" value="1">￥<s:property
									value="session.d_product_msize_price" /></td>
							<td class="nowrap">L</td>
							<td><input type="radio" name="optionsRadios"
								id="optionsRadios1" value="1">￥<s:property
									value="session.d_product_lsize_price" /></td>
						</s:if>
						<s:if test="category_id==3 || category_id==4">
						<td class="nowrap"><!-- サイド・ドリンク --></td>
							<td><input type="radio" name="optionsRadios"
								id="optionsRadios1" value="1">￥<s:property
									value="session.d_product_price" /></td>
						</s:if>
						</tr>
						<tr>
							<td class="nowrap">商品詳細</td>
							<td>:</td>
							<td><s:property value="session.d_product_description" /></td>
						</tr>
					</table>
				</div>
			</div>

			<h4>トッピング</h4>
			<h5>M￥324,L￥432</h5>
			<div class="ToppingList">
				<div class="toppingList">
					<table class="toppingTable">
						<s:iterator value="session.toppingList">

								<input type="checkbox" value="<s:property value='msize_price'/>"  onclick="calc_total();" />
								<s:property value="topping_name" />

						</s:iterator>
					</table>
				</div>
			</div>



			数量
			<s:select name="product_count" list="stockList"
				onchange="outputSelectedValueAndText(this);" />
			選択した商品の金額
			<s:textfield name="productDetailPrice" id="productDetailPrice" />



			<s:hidden name="gocart" value="1" />
			<s:submit value="カートに入れる" />


		</div>
	</s:form>


	<h3>おすすめ関連商品</h3>
	<div>
		<div class="thumbnail clearFix">
			<s:iterator value="suggestList">
				<a
					href="<s:url action="ProductDetailsAction">
				 <s:param name="product_id" value="%{product_id}" />
 			</s:url>">
					<div class="list1">
						<img class="image"
							src="<s:property value='image_file_path'/>" alt="Photo"
							width="250" height="200">
						<table class="detailsTable">
							<tr>
								<td class="nowrap"><!-- 商品名 --></td>
								<td><s:property value="product_name" /></td>
								<td class="nowrap"><!-- 商品かな --></td>
								<td><s:property value="product_name_kana" /></td>
							</tr>

							<tr>
							<s:if test="category_id==2">
								<td class="nowrap">M</td>
								<td>￥<s:property value="msize_price" />
								<td class="nowrap">L</td>
								<td>￥<s:property value="lsize_price" />
								<td class="nowrap">
								</td>
							</s:if>
							<s:if test="category_id==3 || category_id==4">
								<td>￥<s:property value="price" />
								</td>
							</s:if>
							</tr>
						</table>
						<s:hidden name="product_id" value="%{product_id}" />

					</div>
				</a>
			</s:iterator>
		</div>
	</div>


</body>
</html>