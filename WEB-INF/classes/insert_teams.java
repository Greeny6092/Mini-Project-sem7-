import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class insert_teams extends HttpServlet
{

	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{	int i;
		String teams,insert;
 		teams=req.getParameter("teams");
		String team[]=teams.split("+");;          
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection 			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hangman?useSSL=false","root","root");
			Statement stmt=conn.createStatement();		
			for(i=0;i<team.length;i++)
			{	
		insert="insert into teams(team_name) values('"+team[i]+"')";
				stmt.execute(insert);		
			}
	}
		catch(Exception e)
		{

		}
	}
}



