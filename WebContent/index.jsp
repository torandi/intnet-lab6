<!doctype html>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Security"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Security> securities = (ArrayList<Security>)request.getServletContext().getAttribute("securities");
%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Torandium Trading Corp</title>
  <link rel="stylesheet" href="css/styles.css?v=1.0">
</head>
<body>
	<header>		
	<h1>Torandium Trading Corp</h1>
	</header>
	
	<jsp:useBean id="date" class="java.util.Date" /> 
	<p>The date/time is <%= date %></p>

	<div class="pagecontainer">
		
		<div class="addSecurity">
			<h3>Add New Security</h3>
			<form method="Post">
			<input type="hidden" name="action" value="addSecurity">
			<input type="text" name="securityName" value=""><br>
			<input type="submit" value="Create security">
			</form>
		</div>

		<div class="buySell">
			<h3>Buy & Sell Security</h3>
			<form method="Post">
			<input type="hidden" name="action" value="buySell">
			
			<label for="buysell_security">Security: </label><select name="security" id="buysell_security">
			<%
			for(Security s : securities){
				%> <option value="<%=s.id()%>"><%=s.getName() %></option>
				<%
			}
			%>
			</select><br>
			
			Buy: <input type="radio" name="buyOrSell" value="True" checked>
			Sell: <input type="radio" name="buyOrSell" value="False"><br>
			Seller/Buyer: <input type="text" name="uid" value=""><br>
			Price: <input type="text" name="price" value=""><br>
			Amount: <input type="text" name="amount" value=""><br>
			<input type="submit" value="Commit Order">
			</form>
		</div>

		<div class="listSecurity">
			<h3>List Security History</h3>
			<form method="Post">
			<input type="hidden" name="action" value="listSecurity">
			<label for="history_security">Security: </label><select name="security" id="history_security">
			<%
			for(Security s : securities){
				%> <option value="<%=s.id()%>"><%=s.getName() %></option>
				<%
			}
			%>
			</select><br>
			<input type="submit" value="Show History">
			</form>
		</div>

	</div>

	<footer>
		<h4>Footer goes here</h4>
	</footer>
<body>
  <script src="js/scripts.js"></script>
</body>
</html>
