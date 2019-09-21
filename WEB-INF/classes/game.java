import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class game extends HttpServlet
{
	static public ArrayList<user> users=new ArrayList<user>();
	static public ArrayList<gameboard> gameboards=new ArrayList<gameboard>();
	static user getUserById(int id)
	{
		for(user u:users)
		{
			if(u.id==id)
			{
				return u;
			}
		}
		return null;
	}
	
	static void giveRequest(int id)
	{
		user u=getUserById(id);
		if(u!=null)
		{
			u.requests.add(new request(u));
		}
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{	

	}
}



