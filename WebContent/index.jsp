<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Torandium Trading Corp</title>
  <link rel="stylesheet" href="css/styles.css?v=1.0">
  <!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
	<header>		
	<h1>Torandium Trading Corp</h1>
	</header>
	
	<div class="pagecontainer">
		
		<div class="addSecurity">
			<h3>Add New Security</h3>
			<form action="/IndexController">
			<input type="hidden" name="action" value="addSecurity">
			<input type="text" name="security" value=""><br>
			<input type="submit" value="Commit Security">
			</form>
		</div>

		<div class="buySell">
			<h3>Buy & Sell Security</h3>
			<form action="/IndexController">
			<input type="hidden" name="action" value="addOrder">
			Security: <select name="security">
			<option value="Ericsson">Ericsson</option>
			</select><br>
			Buy: <input type="radio" name="buyOrSell" value="1" checked>
			Sell: <input type="radio" name="buyOrSell" value="0"><br>
			Price: <input type="text" name="price" value=""><br>
			Amount: <input type="text" name="amount" value=""><br>
			<input type="submit" value="Commit Order">

		</div>

		<div class="listPappers">
			<h3>List Security History</h3>
			<form action="/IndexController">
			<input type="hidden" name="action" value="viewTrades">
			Security: <select name="security">
			<option value="Ericsson">Ericsson</option>
			</select><br>
			<input type="submit" value="Show History">
			</form>
		</div>

	</div>

	<footer>
		<h4>Footer goes here</h1>
	</footer>
<body>
  <script src="js/scripts.js"></script>
</body>
</html>