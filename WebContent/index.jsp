<!doctype html>
<%@page import="model.Trade"%>
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
	
	<div class="pagecontainer">
	<%
		String error = (String) request.getServletContext().getAttribute("error");
		String success = (String) request.getServletContext().getAttribute("success");
		if(error != null) {
	%>
			<p class='error'><%=error %> </p>
	<%
		}
		if(success != null) {
	%>
			<p class='success'><%=success %></p>
	<%
		}
	%>
		
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
			Price (SEK): <input type="text" name="price" value=""><br>
			Amount: <input type="text" name="amount" value=""><br>
			<input type="submit" value="Commit Order">
			</form>
		</div>

		<div class="listTrades">
			<h3>List trade history</h3>
			<form method="Post">
			<input type="hidden" name="action" value="listHistory">
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
			<%
				String trade_history = (String) request.getServletContext().getAttribute("history_security");	
				if(trade_history != null) {
					%>
					<h2>History for <%=trade_history %></h2>
					<table>
					<thead>
						<tr><th>Seller</th><th>Buyer</th><th>Price</th><th>Amount</th><th>Transaction date</th></tr>
					</thead>
					<tbody>
					<%
						@SuppressWarnings("unchecked")
						ArrayList<Trade> trades = (ArrayList<Trade>)request.getServletContext().getAttribute("history");
						for(Trade trade : trades) {
							%><tr>
								<td><%=trade.getSeller() %></td>
								<td><%=trade.getBuyer() %></td>
								<td><%=trade.getPrice() %> SEK</td>
								<td><%=trade.getAmount() %></td>
								<td><%=trade.getTime() %></td>
							</tr>
							<%
						}
					%>
					</tbody>
					</table>
					<%
				}
			%>
		</div>

	</div>

	<footer>
		<h4>Footer goes here</h4>
	</footer>
<body>
  <script src="js/scripts.js"></script>
</body>
</html>
