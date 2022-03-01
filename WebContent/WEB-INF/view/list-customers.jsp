<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>

<html>

<head>
	<title>Customers List</title>
	
	<!-- reference css sheet -->
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id = "container">
		<div id = "content">


			<!-- Add search field to search customer -->

			<form:form action="search" method="GET">
				
				Search customer: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-button" />
				
			</form:form>

			<!-- add new button : Add Customer -->
			
			<input type="button" value = "Add Customer"
			onClick = "window.location.href='showFormForAdd'; return false;"
			class="add-button"
			/>
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- looop over and print customer -->
				<c:forEach var="tempCustomer" items="${customers}">
				
				<!-- Construct an "Update" link with customer id -->
				<c:url var="updateLink" value="/customer/showFormForUpdate">
					<c:param name="customerId" value="${tempCustomer.id}"></c:param>
				</c:url>
				
				
				<!-- Construct an "Delete" link with customer id -->
				<c:url var="deleteLink" value="/customer/delete">
					<c:param name="customerId" value="${tempCustomer.id}"></c:param>
				</c:url>
				
					<tr>
						<td>${tempCustomer.firstName }</td>
						<td>${tempCustomer.lastName }</td>
						<td>${tempCustomer.email }</td>
						<td>
							<!-- Display update link -->
							<a href="${updateLink}">Update</a> 
							|
							<a href="${deleteLink}" 
							onClick = "if (!confirm(('Are you sure want to delete this customer?'))) return false"
							>Delete</a> 
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
			
		</div>
	</div>

</body>

</html>