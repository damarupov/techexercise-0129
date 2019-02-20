
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServletDB
 */
@WebServlet("/MyServletDB")
public class MyServletDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	String url = "jdbc:mysql://ec2-18-223-152-203.us-east-2.compute.amazonaws.com:3306/myDB";
	String user = "newMysqlRemoteuser";
	String password = "mypassword";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServletDB() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			//String selectSQL = "SELECT * FROM myTable WHERE MYUSER LIKE 'user'";
			String selectSQL = "SELECT * FROM myTable;";
			String theUserName = "user%";
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			//preparedStatement.setString(1, theUserName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("ID");
				String username = rs.getString("MYUSER");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				response.getWriter().append("USER ID: " + id + ", ");
				response.getWriter().append("USER NAME: " + username + ", ");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

//		response.setContentType("text/html");
//		response.getWriter().println("<h1> Dilshod Marupov </h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
