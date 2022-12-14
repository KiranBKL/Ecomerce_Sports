<%@page import="java.util.*"%>
<%@page import="dto.*"%>
<%@page import="service.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String mail =(String) request.getSession().getAttribute("user");
if (mail != null) {
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0);
    request.setAttribute("auth", mail);
}
else
{
	response.sendRedirect("index.jsp");
}
UserService us = new UserService();
ArrayList<Item> products =us.viewAllItems();
%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<%@include file="includes/head.jsp"%>
</head>
<body >
<%@include file="includes/navbar.jsp"%>

<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Item p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="product-image/<%=p.getFile() %>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getItemName() %></h5>
						<h6 class="price">Price: $<%=p.getRate() %></h6>
						<h6 class="category">Brand: <%=p.getBrand() %></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a class="btn btn-dark" href="UserProcessor?action=addCart&id=<%=p.getItemId()%>">Add to Cart</a> <a
								class="btn btn-primary" href="UserProcessor?action=buyItem&id=<%=p.getItemId()%>">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no proucts");
			}
			%>

		</div>
	</div>
<%@include file="includes/footer.jsp"%>
</body>
</html>