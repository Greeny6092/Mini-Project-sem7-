import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class RequestTeller extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{	
		res.setContentType("text/event-stream, charset=UTF-8");
		int id=Integer.parseInt(req.getParameter("id"));
		user u=game.getUserById(id);
		String requestedusers=new String();
		String ids=new String();
		for(request r:u.requests)
		{
			ids+=r.u.id+",";
		}
		for(request r:u.requests)
		{
			requestedusers+=r.u.name+",";
		}
		PrintWriter out=res.getWriter();
		if(u.status!=1)
		{
			out.write("event:live_users\n"); 
			out.write("data:"+requestedusers+"$"+ids+"  \n\n");
		}
		else if(u.status==1)
		{
			out.write("event:gamestartflag\n"); 
			out.write("data: "+u.status+","+u.cur_gameboard_id+"\n\n");
			out.write("retry: 110\n");
		}
	}
	
}



