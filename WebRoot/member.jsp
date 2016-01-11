<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'member.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<c:if test="${empty(list)}">No records!</c:if>
		<c:if test="${!empty(list)}">
		<table width="80%" border="1" align="center">
			<form
				action="${pageContext.request.contextPath }/servlet/SearchServlet">
				<tr>
					<td colspan="4">
						<input type="text" name="search" />
						<input type="submit" value="Search" />
					</td>
				</tr>
			</form>
			<form
				action="${pageContext.request.contextPath }/servlet/ReserveServlet">
				<tr>
					<td>
						Title
					</td>
					<td>
						Description
					</td>
					<td>
						Status
					</td>
				</tr>
				<c:forEach var="book" items="${list}">
					<tr>
						<td>
							${book.title }
						</td>
						<td>
							${book.description }
						</td>
						<td>
							${book.status }
						</td>
						<td>
							<c:if test="${book.status=='available'}">
								<input type="checkbox" name="select" value="${book.title }" />
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4">
						<input type="submit" value="Reserve" />
					</td>
				</tr>
			</form>
		</table>
		${errorMsg }
		</c:if>
	</body>
</html>
