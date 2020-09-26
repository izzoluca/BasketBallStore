import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.dbcp.dbcp2.DriverConnectionFactory;

import com.mysql.jdbc.*;

/**
 * Servlet implementation class Login
 */
@WebServlet(name="Login",urlPatterns={"/Login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection con = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();

		try{
			String nomeUtente = request.getParameter("NomeUtente");
			String password = request.getParameter("Password");
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			String url = "jdbc:mysql://localhost:3306/basket?user=root&password=Lebron95";
			con = DriverManager.getConnection(url);
			Statement st = con.createStatement();

			rs = st.executeQuery("SELECT NomeUtenteCliente,PassCliente FROM Cliente");
			while(rs.next()){
				String id = rs.getString("NomeUtenteCliente");
				String psw = rs.getString("PassCliente");
				if((rs.getString("NomeUtenteCliente").toLowerCase()).equals(nomeUtente.toLowerCase()) && rs.getString("PassCliente").equals(password)){
					HttpSession session = request.getSession();
					session.setAttribute("login", id);
					response.setContentType("text/html");
					out.println("<html><body onload=\"document.forms[0].submit();\"><form action=\"Home.jsp\" method=\"get\"><input type=\"hidden\"></form></body></html>");
					return;
				}
				else{
					
					out.println("<html><body onload=\"document.forms[0].submit();\"><form action=\"LogReg.jsp\" method=\"get\"><input type=\"hidden\"></form></body></html>");
					out.println("<script type=\"text/javascript\">setTimeout(wakeUpUser, 10);function wakeUpUser() {alert(\"Si è verificato un problema!Username o Password errati!\");}</script>");
				}
				
			}
			out.close();

		}
		catch(Exception	e){
//			PrintWriter out = response.getWriter();
			out.println("<html><body onload=\"document.forms[0].submit();\"><form action=\"LogReg.jsp\" method=\"get\"><input type=\"hidden\"></form></body></html>");
		}


	}
}


