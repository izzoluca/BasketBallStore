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
 * Servlet implementation class Inserisciprodotto
 */
@WebServlet("/ModificaProdotto")
public class ModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaProdotto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		Connection con = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();
		out.println("<html>" +"<title>Modifica Prodotto</title><head><link type=\"text/css\" rel=\"stylesheet\" href=\"css/main.css\"></head>"+"<body>");
		out.println("<h1> Seleziona il prodotto da modificare: </h1>");

		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
				
			String url = "jdbc:mysql://localhost:3306/basket?user=root&password=Lebron95";
				
			con = DriverManager.getConnection(url);
			
			Statement st = con.createStatement();
			
	
			rs = st.executeQuery("SELECT * FROM Prodotti WHERE Id="+id);
		
			rs.next();
				
				String descrizione = rs.getString("Descrizione");
				String immagine = rs.getString("Immagine");
				String taglia = rs.getString("Taglia");
				Double prezzo = rs.getDouble("Prezzo");
				int disp = rs.getInt("Disponibilità");
				
				
			
			String delete = "DELETE FROM Prodotti WHERE Id="+id;
			
			st.executeUpdate(delete);
			out.print("Cancellazione effettuata");
			
			out.println("<form action='InserisciProdotto' method='get'><table><tr><td>Id:</td><td><input type='text' value='"+id+"' name='id'></td></tr><tr><td>Descrizione:</td><td><input type='text' name='descrizione' value='"+descrizione+"'></td></tr><tr><td>Immagine:</td><td><input type='text' name='immagine' value='"+immagine+"'></td></tr><tr><td>Taglia:</td><td><input type='text' name='taglia' value='"+taglia+"'></td></tr><tr><td>Prezzo</td><td><input type='number' name='prezzo' value='"+prezzo+"'></tr></td><tr><td>Disponibilità:</td><td><input type='number' name='disponibilita' value='"+disp+"'></td></tr></table><input type='submit' name='insert' value='Submit'></form>");

		
		}
		catch(Exception e){
			out.println("Niente (al get)");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();
		out.println("<html>" +"<title>Modifica Prodotto</title><head><link type=\"text/css\" rel=\"stylesheet\" href=\"css/main.css\"></head>"+"<body>");
		out.println("<h1> Seleziona il prodotto da modificare: </h1>");

		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
				
			String url = "jdbc:mysql://localhost:3306/basket?user=root&password=Lebron95";
			
			con = DriverManager.getConnection(url);
		
			Statement st = con.createStatement();
				
			
			rs = st.executeQuery("SELECT * FROM Prodotti");
			while(rs.next()){
				String descrizione = rs.getString("Descrizione");
				String immagine = rs.getString("Immagine");
				String taglia = rs.getString("Taglia");
				Double prezzo = rs.getDouble("Prezzo");
				int disp = rs.getInt("Disponibilità");
				out.println("<table><tr class='prodotto'><td class='prodotto'>"+"<img alt=\"prodotto\" src=\""+rs.getString("Immagine")+"\""+"height=\"100\" width=\"100\">"+"</td><td class='prodotto'>"+rs.getString("Descrizione")+"</td><td class='prodotto'>"+rs.getString("Taglia")+"</td><td class='prodotto'>"+(disp)+"</td><td class='prodotto'>"+rs.getString("Prezzo")+" $"+"</td><td class='prodotto'>"+"<a href='ModificaProdotto?id="+rs.getInt("Id")+"'><button>Modify</button></a></td></tr></table>");
			}
		}
		catch(Exception e){
			out.println("Niente (al post)");
		}
	}

}
