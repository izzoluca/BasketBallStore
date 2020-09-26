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

/**
 * Servlet implementation class InserisciPacchetto
 */
@WebServlet("/Cancellazione")
public class Cancellazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cancellazione() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		PrintWriter out = response.getWriter();
		out.println("<html>" +"<head><link type=\"text/css\" rel=\"stylesheet\" href=\"css/main.css\"></head>"+"<body>");


		String id = request.getParameter("id");

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			String url = "jdbc:mysql://localhost:3306/basket?user=root&password=Lebron95";
			con = DriverManager.getConnection(url);
			Statement st = con.createStatement();

			String delete = "DELETE FROM Prodotti WHERE Id="+id;
			
			st.executeUpdate(delete);
			out.println("<h2>Cancellazione effettuata correttamente</h2>");
			out.println("<a href='admin?procedi=Procedi'>< <<-Torna all'area Admin </a>");
		}
		catch(Exception e){
			out.print("Niente da fare!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection con = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();
		out.println("<html>" +"<head><link type=\"text/css\" rel=\"stylesheet\" href=\"css/main.css\"></head>"+"<body>");

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			String url = "jdbc:mysql://localhost:3306/basket?user=root&password=Lebron95";
			con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM Prodotti");
			out.println("<table>");
			while(rs.next()){
				boolean disp = rs.getString("Disponibilità").equals("Disponibile");
				out.println("<table><tr class='prodotto'><td class='prodotto'>"+"<img alt=\"prodotto\" src=\""+rs.getString("Immagine")+"\""+" height=\"100\" width=\"100\">"+"</td><td class='prodotto'>"+rs.getString("Descrizione")+"</td><td class='prodotto'>"+rs.getString("Taglia")+"</td><td class='prodotto'>"+(disp)+"</td><td class='prodotto'>"+rs.getString("Prezzo")+" $"+"</td><td class='prodotto'>"+"<a href='Cancellazione?id="+rs.getInt("Id")+"'><button>X</button></a></td></tr>");
			}
			out.println("</table>");
			out.close();
		}
		catch(Exception e){
			out.print("Nient.");
		}



	}

}

