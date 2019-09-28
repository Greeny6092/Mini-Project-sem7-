import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.Date;
import org.json.*;

public class computerBoardTeller extends HttpServlet
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
		PrintWriter out=res.getWriter();
		out.write("event:boardstatus\n"); 
		//out.write("retry:"+retry+"\n");
		//out.write("data:"+output+" \n\n");
		//out.write("data:here comes the status \n\n");
	}
	
}



