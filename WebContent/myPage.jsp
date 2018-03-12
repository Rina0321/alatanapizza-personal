<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/reset.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mt_style.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/img/favicon.ico">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ochiai_style.css">

<title>マイページ画面</title>
</head>
<body>

	<!-- ヘッダー -->
	<jsp:include page="include_header.jsp" />
	<div class="header"></div>



	<div class="main">

		<div id="secRegist" class="section">

			<div class="sectionInner">

				<div class="sectionHeader">
					<h3>会員登録情報</h3>
					<!-- sectionHeader -->
				</div>

				<div class="contBody">
					<table>
						<tbody>
							<s:iterator value="myPageList">
								<tr>
									<th>氏名</th>
									<td>
										<p>
											<s:property value="familyName" />
											<s:property value="firstName" />
										<p>
									</td>
									<td class="change">
										<!-- <a href="アクション"></> -->
									</td>
								</tr>

								<tr>
									<th>ふりがな</th>
									<td><p>
											<s:property value="familyNameKana" />
											<s:property value="firstNameKana" />
										<p></td>
									<td class="change">
										<!-- <a href="アクション"></> -->
									</td>
								</tr>

								<tr>
									<th>性別</th>
									<td><p>
											<s:if test="sex==0">男性</s:if>
											<s:else>女性</s:else>
										<p></td>
									<td class="change">
										<!-- <a href="アクション"></> -->
									</td>
								</tr>

								<tr>
									<th>メールアドレス</th>
									<td><s:property value="email" /></td>
									<td class="change">
										<!-- <a href="アクション"></> -->
									</td>
								</tr>

								<tr>
									<th>alatanapizza ID</th>
									<td><p>
											<s:property value="userId" />
										</p></td>
									<td class="change">
										<!-- <a href="アクション"></> -->
									</td>
								</tr>

								<tr>
									<th>パスワード</th>
									<td><p>
											<s:property value="password" />
										</p></td>
									<td class="change"><a href="/alatanapizza/ChangePasswordAction"
										class="btn">変更</a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>

					<div class="buttonBox">
						<div class="btnn">
							<a href="/alatanapizza/PurchaseHistoryInfoAction" class="button">購入履歴</a>
						</div>
					</div>
				</div>


			</div>
		</div>
	</div>





	<!-- フッター -->
	<jsp:include page="include_footer.jsp" />


</body>
</html>