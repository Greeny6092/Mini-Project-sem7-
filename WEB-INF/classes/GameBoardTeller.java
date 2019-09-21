import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class GameBoardTeller extends HttpServlet
{
	static int retry=110;
	int count=0;
	int randX=151,randY=71;
	int s1move=39,s2move=37;
	int row_count=0,col_count=0;
	int fs;
	gameboard gb;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{	
		res.setContentType("text/event-stream, charset=UTF-8");
		int gid=Integer.parseInt(req.getParameter("gid"));
		String  output="";
		
		for(gameboard g:game.gameboards)
		{
			if(g.gameboard_id==gid)
			{
				gb=g;
				randX=-1;
				randY=-1;
				//checkForFoodStatus(g);
				fs=g.foodstatus;
				output=new String();
				output=g.u2.snake.move+"$"+g.u1.snake.move+"$";
				row_count=g.row_count;
				col_count=g.col_count;
				if(g.foodstatus==0)
				{
					while(randX>row_count||randX<0)
						{
							randX=(int)(Math.random()*100);
						}
						while(randY>col_count||randY<0)
						{
							randY=(int)(Math.random()*100);
						}
					g.foodstatus=1;
				}
				break;
			}
		}
		//output=s1move+"$"+s2move;

		output+=randX+","+randY+"$"+gb.u1.snake.addflag+","+gb.u2.snake.addflag+"$"+gb.u1.snake.removeflag+","+gb.u1.snake.position+"$"+gb.u2.snake.removeflag+","+gb.u2.snake.position;
		gb.u1.resetSnakeFlags();
		gb.u2.resetSnakeFlags();
		PrintWriter out=res.getWriter();
		out.write("event:boardstatus\n"); 
		out.write("retry:"+retry+"\n");
		out.write("data:"+output+" \n\n");
		//out.write("data:here comes the status \n\n");
	}
	
}



