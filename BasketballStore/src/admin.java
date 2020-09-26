import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<html>" +"<title>Admin Page</title><head><link type=\"text/css\" rel=\"stylesheet\" href=\"css/main.css\"></head>"+"<body>");
		if(request.getParameter("action")==null && request.getParameter("insert")==null){
			if(request.getParameter("procedi") == null){
				out.println("<h1 align=\"center\">Iserisci le credenziali Amministratore</h1>");
				out.println("<table><tr><td style=\"font-size: x-large;\" align=\"right\">NomeUtente:<br><br>Password:<br><br></td><td><form action=\"admin\"method=\"post\"><br><input type=\"text\" name=\"id\"><br><br><input type=\"password\" name=\"password\"><br><br><input class=\"button\" type=\"submit\" value=\"Invia\"></form></td></tr></table>");
			}
			else{
				out.println("<h1 align=\"center\">Seleziona l'azione da eseguire<h1>");
				out.println("<form align=\"center\" action='admin' method='get'><input class='button' type='submit' name='action' value='Inserisci Prodotto'></form><form action='ModificaProdotto' method='post'><input class='button' type='submit' name='action' value='Modifica Prodotto'></form><form action='Cancellazione' method='post'><input class='button' type='submit' name='action' value='Rimuovi Prodotto'></form><form action='Home.jsp' method='post'><input class='button' type='submit' name='action' value='Disconnetti Admin'></form>");
			}
		}
		else{
			if(request.getParameter("action").equals("Inserisci Prodotto")){
				out.println("<form action='InserisciProdotto' method='get'><table><tr><td>Id:</td><td><input type='text' name='id'></td></tr><tr><td>Descrizione:</td><td><input type='text' name='descrizione'></td></tr><tr><td>Taglia:</td><td><input type='text' name='taglia'></td></tr><tr><td>Prezzo</td><td><input type='number' name='prezzo'></td></tr><tr><td>Disponibilità:</td><td><input type='number' name='disponibilita'></td></tr><tr><td>Immagine URL:</td><td><input type='text' name='immagine'></td></tr></table><input type='submit' name='insert' value='Submit'></form>");
			}

			else if(request.getParameter("action").equals("Modifica Prodotto")){
				out.println("Stai modificando un prodotto");
			}
			else if(request.getParameter("action").equals("Rimuovi Prodotto")){
				out.println("Stai rimuovendo un prodotto");
			}
			else if(request.getParameter("action").equals("TornaHome")){
				out.println("Hai chiuso la sessione Admin!");
			}

		}
		out.println("</body>" + "</html>");
	}




	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();
		out.println("<html>" +"<body>");
		try{
			String nomeUtente = request.getParameter("id");
			String password = request.getParameter("password");
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			String url = "jdbc:mysql://localhost:3306/basket?user=root&password=Lebron95";
			con = DriverManager.getConnection(url);
			Statement st = con.createStatement();

			rs = st.executeQuery("SELECT NomeUtente,Pass FROM Admin");
			while(rs.next()){
				String id = rs.getString("NomeUtente");
				String psw = rs.getString("Pass");
				if((id.toLowerCase()).equals(nomeUtente.toLowerCase()) && psw.equals(password)){
					HttpSession session = request.getSession();
					session.setAttribute("admin", id);
					response.setContentType("text/html");
					out.println("<html>" +"<title>Admin Page</title><head><link type=\"text/css\" rel=\"stylesheet\" href=\"css/main.css\"></head>"+"<body>");
					out.println("<h1 align=\"center\">Login riuscito</h1>");
					out.println("<h2 align=\"center\">Benvenuto Spizzico,ora tutte le funzioni Admin sono disponibili</h2>");
					out.println("<form align=\"center\" action=\"admin\" method=\"get\"><input type=\"submit\" name=\"procedi\" value=\"Procedi\" ></form>");
				}else{
					out.println("<html><body onload=\"document.forms[0].submit();\"><form action=\"admin\" method=\"get\"><input type=\"hidden\"></form></body></html>");
					
					
				}
			}
			out.close();

		}
		catch(Exception	e){
			//			PrintWriter out = response.getWriter();
			out.println("<html>" +"<title>Admin Page</title><head><link type=\"text/css\" rel=\"stylesheet\" href=\"css/main.css\"></head>"+"<body>");
			out.println("<h1> Login non riuscito </h1>");
		}
		out.println("</body>" + "</html>");




	}


}

