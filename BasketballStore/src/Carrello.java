import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Carrello
 */
@WebServlet("/Carrello")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public Carrello() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		java.sql.Connection con = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		PrintWriter out = response.getWriter();
		double prezzo = 0;
		out.println("<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>BasketballStore</title><link type='text/css' rel='stylesheet' href='css/main.css'></head><body>");
		request.getRequestDispatcher("/Header.jsp").include(request, response);
		request.getRequestDispatcher("/nav.jsp").include(request, response);
		out.println("<section id='centro'><article class='paragraph' >");
		
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("login") == null){
				throw new Exception();
			}
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			String url = "jdbc:mysql://localhost:3306/basket?user=root&password=Lebron95";
			con = DriverManager.getConnection(url);
			java.sql.Statement st = con.createStatement();
			java.sql.Statement st1 = con.createStatement();

			String utenteLoggato = (String) session.getAttribute("login");
			rs = st.executeQuery("SELECT UserCliente,IdProdottiUno FROM Sceglie WHERE UserCliente='"+utenteLoggato+"'");
			
			
			out.println("<table class='prodotto'>");
			out.println("<tr class='prodotto' align=\"center\"><td class='prodotto'>Immagine</td><td class='prodotto'>Descrizione</td><td class='prodotto'>PrezzoDollaro</td><td class='prodotto'>Cart</td></tr>");
			while(rs.next()){
				String idprodotto = rs.getString("IdProdottiUno");
				rs1 = null;
				rs1 = st1.executeQuery("SELECT * FROM Prodotti WHERE Id='"+idprodotto+"'");
				rs1.next();
				prezzo += rs1.getDouble("prezzo");
				out.println("<tr align=\"center\"class='prodotto'><td class='prodotto'>"+"<img alt=\"prodotto\" src=\""+rs1.getString("Immagine")+"\""+"height=\"100\" width=\"100\">"+"<td class='prodotto'>"+rs1.getString("Descrizione")+"<td class='prodotto'>"+rs1.getString("Taglia")+"<td class='prodotto'>"+rs1.getString("Prezzo")+" $"+"</td><td class='prodotto'>"+"<a href='Rimuovi?id="+rs1.getString("Id")+"'<a href='Rimuovi?id="+idprodotto+"'><button type='button'>X</button></a></td></tr>");
			}
			out.println("</table>");
			out.println("<h3 align=\"right\" style=\"background-color: black;\"> Prezzo Totale: "+prezzo+" $ </h3>");
			out.println();	
			out.println("<h3 align=\"center\" style=\"background-color: black;\"><a href='acquista.jsp'><button class=\"button\" type='button'>Procedi all'ordine</button></h3>");
			out.println("</article></section>");
			request.getRequestDispatcher("/Footer.jsp").include(request, response);	
			out.print(" </body></html>");
			out.close();		

		}
		catch(Exception	e){
			//			PrintWriter out = response.getWriter();
			out.println("Effettua il login per visualizzare la pagina");
		}


	}

}
