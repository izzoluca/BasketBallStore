<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Effetua il pagamento</title>
<link type="text/css" rel="stylesheet" href="css/main.css">
</head>
<body>

<%@include file="/Header.jsp" %>

<h1>Inserisci i dati della tua carta di Credito:</h1>

<form action="acquistoEff.jsp" align="center">
<table><tr align="justify"><td align="center">Numero Carta (1111-2222-3333-4444):</td><td><input type='text' name='NumeroCarta'></td></tr><tr align="justify"><td align="center">Scadenza Carta (28/06/1995):</td><td><input type='text' name='Scadenza'></td></tr><tr align="justify"><td align="center">CVV2 (123):</td><td><input type='text' name='CVV2'></td></tr>
</table><input type='submit' name='Paga' value='Paga'>
</form>

 
</body>
</html>