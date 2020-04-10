package com.training.enrollment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.* ;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ViewTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter() ;
		
		
		  try
	        {
	             Class.forName("oracle.jdbc.driver.OracleDriver");
	             Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","pass");
	             Statement st=con.createStatement();
	             System.out.println("connection established successfully...!!");     

	             ResultSet rs=st.executeQuery("Select * from training");

	            out.print("<table border=1>");
	 			out.print("<tr>");
	 			out.print("<td>Training Id</td>"); 
	 			out.print("<td>Training Name</td>"); 
	 			out.print("<td>Avalaible Seats</td>"); 
	 			out.print("<td>Enroll in training</td>"); 
	 			out.print("</tr>");  
	 			
	 			while(rs.next()) {
	 				out.print("<tr>") ;
	 					out.print("<td>"+rs.getInt("trainingId")+"</td>");
	 					out.print("<td>"+rs.getString("trainingName")+"</td>");
	 					out.print("<td>"+rs.getInt("AvailableSeats")+"</td>");
	 					out.print("<td><a href='Enrollment?trainid="+rs.getInt("trainingId")
																+"&availseats="+rs.getInt("AvailableSeats")
																+"&trainName="+rs.getString("trainingName")
																+"'>Enroll</a>"+
	 							                                                "</td>");
	 					String tid=Integer.toString(rs.getInt("trainingId"));
	 					request.setAttribute("trainid",tid);
	 					RequestDispatcher rd1 = request.getRequestDispatcher("/Enrollment");
	 					rd1.forward(request,response);
	 					String availseats=Integer.toString(rs.getInt("AvailableSeats"));
	 					request.setAttribute("availseats",availseats);
	 					RequestDispatcher rd2 = request.getRequestDispatcher("/Enrollment");
	 					rd1.forward(request,response);
	 					out.print("<td> <a href='Enrollment'>Enroll</a></td>");}
	 		 con.close();
	            st.close();
	        	rs.close();
	        }
		  catch (Exception e){
	            e.printStackTrace();
	        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
