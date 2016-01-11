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

		<title>My JSP 'librarian.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript">
	function order(){
	window.location.href="${pageContext.request.contextPath }/servlet/UpdateServlet";
	}
	
	</script>
	</head>

	<body>
		<c:if test="${empty(list)}">No records!</c:if>
		<c:if test="${!empty(list)}">
			<table width="80%" border="1" align="center">
				<form
					action="${pageContext.request.contextPath }/servlet/SearchServlet">
					<tr>
						<td colspan="3">
							<input type="text" name="search" />
							<input type="submit" value="Search" />
						</td>
					</tr>
				</form>
				<form
					action="${pageContext.request.contextPath }/servlet/UpdateServlet">
					<tr>
						<td onclick="order()">
							Title
						</td>
						<td>
							Status
						</td>
						<td>
							User
						</td>
					</tr>
					<c:forEach var="book" items="${list}">
						<tr>
							<td>
								${book.title }
							</td>
							<td>
								<c:choose>
									<c:when test="${book.status=='available'}">
								${book.status }
							</c:when>
									<c:otherwise>
										<select name="status">
											<c:if test="${book.status=='checkout'}">
												<option value="checkout,${book.title },${book.owner }"
													selected>
													checkout
												</option>
												<option value="available,${book.title },${book.owner }">
													available
												</option>
												<option value="reserved,${book.title },${book.owner }">
													reserved
												</option>
											</c:if>
											<c:if test="${book.status=='reserved'}">
												<option value="reserved,${book.title },${book.owner }"
													selected>
													reserved
												</option>
												<option value="available,${book.title },${book.owner }">
													available
												</option>
												<option value="checkout,${book.title },${book.owner }">
													checkout
												</option>
											</c:if>
										</select>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								${book.owner }
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="3">
							<input type="submit" value="update" />
						</td>
					</tr>
				</form>
			</table>
		</c:if>
	</body>
</html>
