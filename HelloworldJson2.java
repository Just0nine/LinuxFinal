import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloworldJson extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
//		Gson gson =new Gson();
//		Student stu =new Student("dddd");
//		String json =gson.toJson(stu);

	        Connection conn = null;
	        Statement stmt = null;
	        try {
	            Class.forName(JDBC_DRIVER);
        	    conn = DriverManager.getConnection(DB_URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql = "select id, name from t_student";
	            ResultSet rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	                int id = rs.getInt("id");
			if(id ==4){
		                String name = rs.getString("name");
				out.println("id: " +id +" name: " +name);
				out.flush();
				out.close();
			}
	            }
	            rs.close();
	            stmt.close();
	            conn.close();
	        } catch (SQLException se) {
	            se.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (stmt != null)
	                    stmt.close();
	                if (conn != null)
	                    conn.close();
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
	        }

}

	static final String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
	static final String DB_URL ="jdbc:mysql://175.24.186.97/linux_final";
	static final String USER ="root";
	static final String PASS ="Mysqld1234_";
	
}


//class Student{
//	
//	String sName;
//	
//	Student(String stu){
//		this.sName =stu;
//}
//
//}
