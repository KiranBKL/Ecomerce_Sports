<%@page import="java.util.*"%>
<%@page import="dto.*"%>
<%@page import="dao.*"%>
<%@page import="service.*"%>
<%
String mail = (String) request.getSession().getAttribute("user");
if (mail != null) {
    request.setAttribute("auth",mail);
}
adminService as=new adminService();
ArrayList<User> kart_list=as.viewAllUsers();

%>


<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Home</title>

<style type="text/css">

.table tbody td{
vertical-align: middle;
}
.btn-incre, .btn-decre{
box-shadow: none;
font-size: 25px;
}
</style>
</head>
<body >
<%@include file="includes/adminnav.jsp"%>
<div class="container my-3">
		<div class="d-flex py-3"><h3>Total Price: $ ${(total>0)?dcf.format(total):0} </h3> <a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a></div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Email</th>
					<th scope="col">Phone Number</th>
					<th scope="col">View</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				
						<%
				if (kart_list != null) {
					for (User c : kart_list) {
				%>
				<tr>
					<td><%=c.getUserName()%></td>
					<td><%=c.getEmailId()%></td>
					<td><%=c.getPhoneNumber() %></td>
					<td>
						<form action="AdminProcessor" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%= c.getEmailId()%>" class="form-input">
							
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td><a href="AdminProcessor?action=removeUser&emailId=<%=c.getEmailId() %>" class="btn btn-sm btn-danger">Remove</a></td>
				</tr>

				<%
				}}%>
				
			</tbody>
		</table>
	</div>



<%@include file="includes/footer.jsp"%>
</body>
</html>