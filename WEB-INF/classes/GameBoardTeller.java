import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class GameBoardTeller extends HttpServlet
{
	static int retry=800;
	int count=0;
	int randX=151,randY=71;
	public void checkForFoodStatus(gameboard g)
	{
		int s1x,s1y,s2x,s2y,sum1,sum2;
		s1x=(g.u1.snake.bodyparts.get(0).x)-randX;
		s1y=(g.u1.snake.bodyparts.get(0).y)-randY;
		
		s2x=(g.u2.snake.bodyparts.get(0).x)-randX;
		s2y=(g.u2.snake.bodyparts.get(0).y)-randY;
		
		sum1=s1x+s1y;
		sum2=s2x+s2y;
		
		if(sum1==0||sum2==0)
		{
			randX=72;
			randY=152;
		}
		
		if(sum1==0)
		{
			g.u1.snake.addNewBodyPart();
		}
		if(sum2==0)
		{
			g.u2.snake.addNewBodyPart();
		}
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{	
		res.setContentType("text/event-stream, charset=UTF-8");
		int gid=Integer.parseInt(req.getParameter("gid"));
		String  output="";
		
		for(gameboard g:game.gameboards)
		{
			if(g.gameboard_id==gid)
			{
				checkForFoodStatus(g);
				output=new String();
				output=g.u1.snake.stringizeBodyParts()+"$"+g.u2.snake.stringizeBodyParts();
				count++;
				break;
			}
		}
		if(count%retry==0)
		{
			while(randX>150)
				{
					randX=(int)(Math.random()*100);
				}
				while(randX>70)
				{
					randY=(int)(Math.random()*100);
			}
		}
	
		if(count%retry<=(retry/2)&&(randX<71)&&(randY<152))
		{
			output+="$"+randX+","+randY;
		}
		
		
		PrintWriter out=res.getWriter();
		out.write("event:boardstatus\n"); 
		out.write("retry:"+retry+"\n");
		out.write("data:"+output+" \n\n");
		//out.write("data:here comes the status \n\n");
	}
	
}



