<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BasketBall Store</title>
<link type="text/css" rel="stylesheet" href="css/main.css">
</head>
<body>

	<%@include file="Header.jsp" %>
	<%@include file="/nav.jsp" %>	
	
	

	<section id="centro" >
	
		<table id="titoli">
			<tr>
				
				<td>
					<h3>Registrazione</h3>
					<span>Per effettuare la registrazione inserisci i tuoi dati
						qui</span>
				</td>
				
				<td>
					<h3>Login</h3>
					<span>Per effettuare il login inserisci i
						tuoi dati qui.<br>
						Per entrare in modalità admin <a href="admin">clicca qui</a>
						</span>
				</td>
			</tr>
		</table>
		<table id="logreg">
			<tr align="center">
				<td align="right">
						<br><br><br>
						Nome:
						<br><br>
						Cognome:
						<br><br>
						Email:
						<br><br>
						NomeUtente:
						<br><br>
						Password:
						<br><br>
				</td>
				<td>
					<form action="Reg" method="post">
					<br><br><br><br>
					<input placeholder="Nome" type="text" name="Nome" value="" required>
					<br><br>
					<input placeholder="Cognome" type="text" name="Cognome"	value="" required>
					<br><br>
					<input placeholder="esempio@dominio.com" name="email" type="email" required>
					<br><br>
					<input placeholder="Username" type="text" name="NomeUtente" value="" required>
					<br><br>
					<input placeholder="Password" type="password" name="password" required>
					<br><br>
					<input class="button" type="submit" value="Invia">
					</form>
				</td>

				<td align="right">
						NomeUtente:
						<br><br>
						Password:
						<br><br>
				</td>
				<td>
					<form action="Login" method="post">
					<br>
					<input type="text" name="NomeUtente" placeholder="Username" value="" required>
					<br><br>
					<input type="password" name="Password" placeholder="Password" required>
					<br><br>
					<input class="button" type="submit" value="Invia">
					</form>
				</td>
			</tr>
		</table>
	
	</section>

	
	<%@include file="Footer.jsp" %>


</body>
</html>