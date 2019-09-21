import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class LiveUserLister extends HttpServlet
{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{	
		res.setContentType("text/event-stream, charset=UTF-8");
		String ids=new String();
		String names=new String();
		
		for(user u:game.users)
		{
			ids+=","+u.id;
			names+=","+u.name;
		}
		PrintWriter out = res.getWriter();
		out.write("event: live_users \n\n"); 
        out.write("data: "+ids+"$"+names+" \n\n");
		
	}
}



