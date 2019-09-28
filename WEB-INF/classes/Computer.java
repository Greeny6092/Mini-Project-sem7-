import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.Date;
import org.json.*;

public class Computer extends HttpServlet
{
	Snake snake;
	int id,status,cur_gameboard_id;
	user u;
	gameboard gb;
	static int computer_count=0;
	
	public Computer(user u)
	{
		this.u=u;
		Computer.computer_count++;
		this.id=Computer.computer_count;
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{	
	}
	
}



