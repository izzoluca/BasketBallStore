import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Prodotti
 */
@WebServlet("/Prodotti")
public class Prodotti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Prodotti() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>BasketBall Store</title><link type='text/css' rel='stylesheet' href='css/main.css'></head><body>");
		request.getRequestDispatcher("/Header.jsp").include(request, response);
		request.getRequestDispatcher("/nav.jsp").include(request, response);
		
		out.println("<section id='centro'><article class='paragraph'>");

		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("login") == null){
				throw new Exception();
			}

		
			String nomeUtente = request.getParameter("NomeUtente");
			String password = request.getParameter("Password");
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			String url = "jdbc:mysql://localhost:3306/basket?user=root&password=Lebron95";
			con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			if(request.getParameter("param") == null)
				rs = st.executeQuery("SELECT * FROM Prodotti");
			else
				rs = st.executeQuery("SELECT * FROM Prodotti WHERE Id = '"+request.getParameter("param")+"'");
			out.println("<table class='prodotto'>");
			out.println("<tr class='prodotto' align=\"center\"><td class='prodotto'>Immagine</td><td class='prodotto'>Descrizione</td><td class='prodotto'>Taglia</td><td class='prodotto'>Disp</td><td class='prodotto'>PrezzoDollaro</td><td class='prodotto'>Cart</td></tr>");
		
			while(rs.next()){
				int disp = rs.getInt("Disponibilità");
				System.out.println(rs.getString("Immagine"));
				out.println("<tr align=\"center\" class='prodotto'><td class='prodotto'>"+"<img alt=\"prodotto\" src=\""+rs.getString("Immagine")+"\""+" height=\"150\" width=\"150\">"+"</td><td class='prodotto'>"+rs.getString("Descrizione")+"</td><td class='prodotto'>"+rs.getString("Taglia")+"</td><td class='prodotto'>"+disp+"</td><td class='prodotto'>"+rs.getString("Prezzo")+" $"+"</td><td class='prodotto'>"+(disp>0?"<a href='Aggiungi?id="+rs.getString("Id")+"'><button type='button'>Add</button></a></td></tr>":""));
			}
			out.println("</table></article></section>");
					//"<%@include file=\"/Footer.jsp\" %> "
					request.getRequestDispatcher("/Footer.jsp").include(request, response);		
					out.println("</table></body></html>");
			out.close();

		}
		catch(Exception	e){
			//			PrintWriter out = response.getWriter();
			out.println("Effettua il login per visualizzare la pagina");
				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
