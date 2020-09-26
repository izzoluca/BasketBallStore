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
@WebServlet("/InserisciProdotto")
public class InserisciProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InserisciProdotto() {
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
		out.println("<html>" +"<title>Inserisci Prodotto</title><head><link type=\"text/css\" rel=\"stylesheet\" href=\"css/main.css\"></head>"+"<body>");


		try{
			String id = request.getParameter("id");
			String descrizione = request.getParameter("descrizione");
			String immagine = request.getParameter("immagine");
			String taglia = request.getParameter("taglia");
			String disponibilita = request.getParameter("disponibilita");
			String prezzo = request.getParameter("prezzo");
			
			int idInt , dispInt;
			double prezzoInt;
			idInt = Integer.parseInt(id);
			prezzoInt = Double.parseDouble(prezzo);
			dispInt = Integer.parseInt(disponibilita);
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			String url = "jdbc:mysql://localhost:3306/basket?user=root&password=Lebron95";
			con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			String insert = "INSERT INTO Prodotti VALUES ("+idInt+",'"+immagine+"','"+descrizione+"', "+prezzoInt+", '"+taglia+"',"+dispInt+")";
			st.executeUpdate(insert);
			out.println("<h2>Inserimento riuscito</h2>");
			out.println("<a href='admin?procedi=Procedi'>< <<-Torna all'area Admin </a>");
		}
		catch(Exception e){
			e.printStackTrace();
			out.print("Inserimento Fallito");
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
