import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reg
 */
@WebServlet("/Reg")
public class Reg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		PrintWriter out = response.getWriter();

		try{
			String nome = request.getParameter("Nome");
			String cognome = request.getParameter("Cognome");
			String email = request.getParameter("email");
			String nomeUtente = request.getParameter("NomeUtente");
			String password = request.getParameter("password");
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			String url = "jdbc:mysql://localhost:3306/basket?user=root&password=Lebron95";
			con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			String insert = "INSERT INTO Cliente (nomeUtenteCliente, PassCliente, Nome, Cognome, Email) VALUES ('"+nomeUtente+"', '"+password+"', '"+nome+"', '"+cognome+"', '"+email+"')";
			st.executeUpdate(insert);
			
			response.setContentType("text/html");
			out.println("<html>" +"<title>BasketBall Store</title><head><link type=\"text/css\" rel=\"stylesheet\" href=\"css/main.css\"></head>"+"<body>");
			out.println("<h1 align=\"center\">Registrazione riuscita</h1>");
			out.println("<h2 align=\"center\">Benvenuto nel nostro Store, effettua il Login per acquistare i nostri prodotti!</h2>");
			out.println("<form align=\"center\" action=\"LogReg.jsp\" method=\"get\"><input type=\"submit\" name=\"procedi\" value=\"Procedi\" ></form>");
			//out.println("<html><body onload='document.forms[0].submit();'><form action='LogReg.jsp' method='get'><input type='hidden'></form></body></html>");
		}
		catch(Exception e){
			out.print("Registrazione Fallita");
		}
	}

}
