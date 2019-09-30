import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import org.json.*;


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
			if(id2!=-1)
			{
				user u=game.getUserById(id1);
				u.addRequest(id2);
			}
			else if(id2==-1)
			{
				out.println("-1");
				/*gameboard g=new gameboard(game.getUserById(u1),game.getUserById(u2),row_count,col_count);
				game.gameboards.add(g);				
				User u=game.getUserById(id1);
				u.status=1;
				Computer c=new Computer(u);*/
			}
		}
		else if(t==2)
		{
			//adding two users to game and starting it...
			int accept=Integer.parseInt(req.getParameter("accept"));
			int u1,u2;
			u1=Integer.parseInt(req.getParameter("u1"));
			u2=Integer.parseInt(req.getParameter("u2"));
			String s1,s2;
			if(accept==1)
			{
				int row_count,col_count;
				row_count=Integer.parseInt(req.getParameter("row_count"));
				col_count=Integer.parseInt(req.getParameter("col_count"));
				s1=new String(req.getParameter("snake1"));
				s2=new String(req.getParameter("snake2"));
				if(u2!=-1)
				{
					gameboard g=new gameboard(game.getUserById(u1),game.getUserById(u2),row_count,col_count);
					game.gameboards.add(g);
					//gameboard.gameboard_count++;
					g.u1.status=1;
					g.u1.cur_gameboard_id=g.gameboard_id;
					g.u1.snake.setObject(s1);
					g.u2.status=1;
					g.u2.cur_gameboard_id=g.gameboard_id;
					g.u2.snake.setObject(s2);
					game.getUserById(u1).removeRequest(game.getUserById(u2));					
				}
				else if(u2==-1)
				{
					gameboard g=new gameboard(game.getUserById(u1),new Computer(game.getUserById(u1)),row_count,col_count);					
					game.gameboards.add(g);
					//gameboard.gameboard_count++;
					g.u1.status=1;
					g.u1.cur_gameboard_id=g.gameboard_id;
					g.u1.snake.setObject(s1);
					g.c.status=1;
					g.c.cur_gameboard_id=g.gameboard_id;
					g.c.snake.setObject(s2);
					game.getUserById(u1).removeRequest(game.getUserById(u2));					
				}
				/*game.gameboards.add(g);
				//gameboard.gameboard_count++;
				g.u1.status=1;
				g.u1.cur_gameboard_id=g.gameboard_id;
				g.u1.snake.setObject(s1);
				g.u2.status=1;
				g.u2.cur_gameboard_id=g.gameboard_id;
				g.u2.snake.setObject(s2);
				game.getUserById(u1).removeRequest(game.getUserById(u2));*/
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
			int withcomputer=Integer.parseInt(req.getParameter("withcomputer"));
			try
			{
				String object=new String(req.getParameter("object"));
				for(user u:game.users)
				{
					if(u.id==uid)
					{
						int diff=(u.snake.getLast()-move);
						if(diff!=2&&diff!=-2)
						{
							if(withcomputer==0)
							{
								u.snake.move=move;
								u.snake.setObject(object);
								u.snake.position_flag=1;
							}
							else if(withcomputer==1)
							{
								u.snake.move=move;
								u.snake.setObject(object);
								String compobj=new String(req.getParameter("compobject"));
								for(gameboard g:game.gameboards)
								{
									if(g.gameboard_id==gid)
									{
										g.c.snake.setObject(compobj);
									}
								}
							}
						}
					}
				}
				
			}
			catch(Exception e)
			{
				
			}
		}
		else if(t==4)
		{
			int gid=Integer.parseInt(req.getParameter("gid"));
			for(gameboard g:game.gameboards)
			{
				if(gid==g.gameboard_id)
				{
					g.foodstatus=0;
					g.food_count--;
				}
			}
		}
		else if(t==5)
		{
			int uid=Integer.parseInt(req.getParameter("uid"));
			int operation=Integer.parseInt(req.getParameter("operation"));
			int position=Integer.parseInt(req.getParameter("position"));
			int gid=Integer.parseInt(req.getParameter("gid"));
			int uno=Integer.parseInt(req.getParameter("uno"));
			for(gameboard g :game.gameboards)
			{
				if(g.gameboard_id==gid)
				{
					if(operation==1)
					{
						if(uno==1)
						{
							g.u1.snake.addflag=1;
						}
						else if(uno==2)
						{
							g.u2.snake.addflag=1;
						}
					}
					else if(operation==2)
					{
						if(uno==1)
						{
							g.u1.snake.removeflag=1;
							g.u1.snake.position=position;
						}
						else if(uno==2)
						{
							g.u2.snake.removeflag=1;
							g.u2.snake.position=position;							
						}
					}
				}
			}
		}
		else if(t==6)
		{
			try
			{
				out.println("enterd\n");
				String s1=new String(req.getParameter("s1"));
				out.println("got s1");
				String s2=new String(req.getParameter("s2"));
				out.println("got s2");
				out.println("got parameters\ns1 "+s1+"\ns2 "+s2);
				JSONObject o1=new JSONObject(s1);
				out.println("converted s1");
				JSONObject o2=new JSONObject(s2);
				out.println("converted to JSON object!!");
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snakegame?useSSL=false","root","");  
				Statement stmt=con.createStatement(); 
				int id1=o1.getInt("uid");
				out.println("extracted uid");
				int d1=o1.getInt("direction");
				out.println("extracted direction");
				int l1=o1.getJSONArray("body").length();
				out.println("extracted array");
				int id2=o2.getInt("uid");
				int d2=o2.getInt("direction");
				int l2=o2.getJSONArray("body").length();
				//stmt.execute("create database if not exists snakegame;");
				//stmt.execute("use snakegame;");
				stmt.execute("create table if not exists moves (id1 int(3),d1 int(3),l1 int(3),id2 int(3),d2 int(3),l2 int(3));");
				stmt.execute("insert into moves values("+id1+","+d1+","+l1+","+id2+","+d2+","+l2+");");
				out.println("inserted");
				con.close();
			}
			catch(Exception e)
			{
				out.println("Error "+e);
			}
		}
		else if(t==7)
		{
			int uid=Integer.parseInt(req.getParameter("uid"));
			int gid=Integer.parseInt(req.getParameter("gid"));
			for(gameboard g:game.gameboards)
			{
				if(g.gameboard_id==gid)
				{
					if(g.u1.id==uid)
					{
						g.u1.resetSnakeFlags();
					}
					else
					{
						g.u2.resetSnakeFlags();
					}
				}
			}
		}
		else if(t==8)
		{
			String s1,s2;
			int gid=Integer.parseInt(req.getParameter("gid"));
			s1=new String(req.getParameter("s1"));
			s2=new String(req.getParameter("s2"));
			for(gameboard g:game.gameboards)
			{
				if(g.gameboard_id==gid)
				{
					g.pauseflag=2;
					g.u1.snake.setObject(s1);
					g.u2.snake.setObject(s1);
					g.u1.status=0;
					g.u2.status=0;
				}
			}				
		}
	}
}



