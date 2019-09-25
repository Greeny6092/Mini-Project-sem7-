import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.Date;
import org.json.*;

public class GameBoardTeller extends HttpServlet
{
	static long retry=100;
	int randX=-1,randY=-1,prev_randX=-1,prev_randY=-1;
	int s1move=39,s2move=37;
	int row_count=0,col_count=0;
	int fs,count=0;
	int id;
	JSONObject o;
	Date d1,d2;
	gameboard gb;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{	
		long diff=0;
		res.setContentType("text/event-stream, charset=UTF-8");
		int gid=Integer.parseInt(req.getParameter("gid"));
		String  output="";
		id=Integer.parseInt(req.getParameter("id"));
		for(gameboard g:game.gameboards)
		{
			if(g.gameboard_id==gid)
			{
				gb=g;
			}
		}
		if(gb.calibrate_flag==1)
		{
			if(id==gb.u1.id)
			{
				d1=new Date();
			}
			if(id==gb.u2.id)
			{
				d2=new Date();
				diff=d2.getTime()-d1.getTime();
				//if(diff>-60&&diff<60)
				//if(diff>-10&&diff<10)
				if(diff==0)
				{
					gb.calibrate_flag=0;
					gb.pauseflag=0;
				}
				else
				{
					gb.pauseflag=1;
					if(diff>0)
						retry-=diff;
					else
						retry+=diff;
				}
			}
		}
		/*for(gameboard g:game.gameboards)
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
		}*/
		//output=s1move+"$"+s2move;
		if(gb.pauseflag==0)
		{
			fs=gb.foodstatus;
			output=new String();	
			row_count=gb.row_count;
			col_count=gb.col_count;
			if(gb.foodstatus==0&&gb.food_count<1)
			{
				randX=-1;
				randY=-1;
				while(randX>=row_count||randX<0||randX==prev_randX)
					{
						randX=(int)(Math.random()*100);
					}
				prev_randX=randX;
					while(randY>=col_count||randY<0||randY==prev_randY)
					{
						randY=(int)(Math.random()*100);
					}
				prev_randY=randY;
				gb.food_count++;
				gb.foodstatus=1;
			}			
		}	
		//output=gb.u2.snake.move+"$"+gb.u1.snake.move+"$";
		output=gb.u1.snake.object+"$"+gb.u2.snake.object+"$";
		output+=randX+","+randY+"$"+gb.u1.snake.addflag+","+gb.u2.snake.addflag+"$"+gb.u1.snake.removeflag+","+gb.u1.snake.position+"$"+gb.u2.snake.removeflag+","+gb.u2.snake.position+"$"+gb.pauseflag+"$"+gb.u1.snake.position_flag+"$"+gb.u2.snake.position_flag;
		count++;
		/*if(count%2==0)
		{
			gb.u1.resetSnakeFlags();
			gb.u2.resetSnakeFlags();
		}*/
		PrintWriter out=res.getWriter();
		out.write("event:boardstatus\n"); 
		out.write("retry:"+retry+"\n");
		out.write("data:"+output+" \n\n");
		if(gb.calibrate_flag==1)
		{
			if(diff>0)
				retry+=diff;
			else
				retry-=diff;
		}
		//out.write("data:here comes the status \n\n");
	}
	
}



