package com.training.enrollment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Enrollment_process
 */
public class Enrollment_process extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Enrollment_process() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		PrintWriter out = response.getWriter();
		
		int get_trainingid =Integer.parseInt(request.getParameter("trainid"));
		String get_trainingname= request.getParameter("trainName");
		int get_availableseats=Integer.parseInt(request.getParameter("availseats"));
		
		
		if(get_availableseats==0){
			out.print("<h1>Sorry ! No Seats Are Available.</h1></br>");
			out.print("<hr><a href='index.html'></a>");
		}
		else{
			try {
			    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","pass");
				Statement st=con.createStatement();
				String str="update training set AvailableSeats="+(get_availableseats-1)+" where trainingId="+get_trainingid;
				ResultSet rs=st.executeQuery(str);
				con.close();
				st.close();
				rs.close();
				
		    	} 
			catch (SQLException e) {
				e.printStackTrace();
			    }
			out.print("<h1>Hi you have successfully enrolled for "+get_trainingname+" Training.</h1>");
			
		}
	}

}
