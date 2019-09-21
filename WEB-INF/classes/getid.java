import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class getid extends HttpServlet
{
	static int user_count=1;
	String name;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{	
		res.setContentType("text/html");
		int t;
		PrintWriter out=res.getWriter();
		t=Integer.parseInt(req.getParameter("t"));
		if(t==0)
		{
			//creating new user ID and returning....
			String name=req.getParameter("name");
			name=req.getParameter("name");
			user u=new user(user_count,name);
			game.users.add(u);
			out.println(user_count);
			user_count++;
		}
		else if(t==1)
		{
			//adding request to recipient request queue....
			int id1=Integer.parseInt(req.getParameter("id1"));
			int id2=Integer.parseInt(req.getParameter("id2"));
			user u=game.getUserById(id1);
			u.addRequest(id2);
		}
		else if(t==2)
		{
			//adding two users to game and starting it...
			int accept=Integer.parseInt(req.getParameter("accept"));
			int u1,u2;
			u1=Integer.parseInt(req.getParameter("u1"));
			u2=Integer.parseInt(req.getParameter("u2"));
			if(accept==1)
			{

				gameboard g=new gameboard(game.getUserById(u1),game.getUserById(u2));
				game.gameboards.add(g);
				//gameboard.gameboard_count++;
				g.u1.status=1;
				g.u1.cur_gameboard_id=g.gameboard_id;
				g.u2.status=1;
				g.u2.cur_gameboard_id=g.gameboard_id;
				g.init();
			}
			else if(accept==0)
			{
				game.getUserById(u1).removeRequest(game.getUserById(u2));
			}
		}
		else if(t==3)
		{
			int gid=Integer.parseInt(req.getParameter("gid"));
			int move=Integer.parseInt(req.getParameter("move"));
			int uid=Integer.parseInt(req.getParameter("uid"));
			for(user u:game.users)
			{
				if(u.id==uid)
				{
					int diff=(u.snake.getLast()-move);
					if(diff!=2&&diff!=-2)
					{
						u.snake.moveQueue.add(move);
					}
				}
			}
		}
	}
}



