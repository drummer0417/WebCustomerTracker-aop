package nl.androidappfactory.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setup connection vars
		String user = "springstudent";
		String password = "springstudent";

		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";

		// get connection to db
		try {
			PrintWriter out = response.getWriter();

			System.out.println("connecting to database: " + jdbcUrl);
			out.println("Connecting to database: " + jdbcUrl);

			Class.forName(driver);

			Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
			conn.close();
			out.println("\n");
			out.println("Connection successful");

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		} finally {

		}

	}

}
