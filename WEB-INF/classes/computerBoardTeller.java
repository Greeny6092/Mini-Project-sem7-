import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.Date;
import org.json.*;

public class computerBoardTeller extends HttpServlet
{
	static long retry=100;
	int randX=-1,randY=-1,prev_randX=-1,prev_randY=-1,foodX=-1,foodY=-1;
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
		
		foodX=randX;
		foodY=randY;
		output=foodX+","+foodY+"$";
		PrintWriter out=res.getWriter();
		out.write("event:boardstatus\n"); 
		out.write("retry:"+retry+"\n");
		out.println("data:"+output+"\n\n");
		//out.write("retry:"+retry+"\n");
		//out.write("data:"+output+" \n\n");
		//out.write("data:here comes the status \n\n");
	}
	
}



