<nav>

	<table>
		<tr>
			<td>
				
				<a href="Home.jsp"><button class="button"  type="button"  >Home</button></a>
				<a href="Prodotti"><button class="button"  type="button">Prodotti</button></a>
				<a href="ChiSiamo.jsp"><button class="button"  type="button">Chi Siamo</button></a>
				<a href="DoveSiamo.jsp"><button class="button"  type="button">Dove Siamo</button></a>
				
				
				<% if(request.getSession().getAttribute("login") == null) {%>
					<a class="utente" href="LogReg.jsp"><button class="button" type="button">Login o Registrazione</button></a>
				<% }else { %>
					<a href="Logout"><button class="button" type="button">Logout</button></a>
				<% } %>
				</td>
				<td>
				<a class="carrello" href="Carrello"><img alt="carrello" src="img/cart.png"
						height="22px" width="22px" hspace="10"></a>
				</td>
				
			
			</tr>
			</table> 
	</nav>