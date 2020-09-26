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
 * Servlet implementation class Rimuovi
 */
@WebServlet("/Rimuovi")
public class Rimuovi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rimuovi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String idprodotto = request.getParameter("id");
		Connection con = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			String url = "jdbc:mysql://localhost:3306/basket?user=root&password=Lebron95";
			con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			HttpSession session = request.getSession();
			String utenteLoggato = (String) session.getAttribute("login");
			rs = st.executeQuery("SELECT Count(*) FROM SCEGLIE WHERE IdProdottiUno = "+idprodotto+" AND UserCliente = '"+utenteLoggato+"'");
			rs.next();
			int n = rs.getInt(1);
			st.executeUpdate("DELETE FROM SCEGLIE WHERE IdProdottiUno = "+idprodotto+" AND UserCliente = '"+utenteLoggato+"'");
			rs = st.executeQuery("SELECT Disponibilità FROM Prodotti WHERE Id='"+idprodotto+"'");
			rs.next();
			int disp = rs.getInt("Disponibilità");
			String update = "UPDATE Prodotti SET Disponibilità="+(disp+n)+" WHERE Id="+idprodotto;
			st.executeUpdate(update);
			
			out.println("<html><body onload='document.forms[0].submit();'><form action='Carrello' method='get'><input type='hidden'></form></body></html>");
		}
		catch(Exception e){
			out.println("Fallito");
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
