import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class get_question extends HttpServlet
{

	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{	
	//res.getWriter().write("got request");
	 res.setContentType("text/html");
	 int n;
	do
	{
	n=(int)(Math.random()*100);
	}while(n>27);
	 int i=0;
	String question;	
      String f="/WEB-INF/classes/questions.txt";
InputStream is = getServletContext().getResourceAsStream(f);
//res.getWriter().write("opened a file");
InputStreamReader isr=new InputStreamReader(is); 
      BufferedReader b=new BufferedReader(isr);
	 do
	 {
		question=b.readLine();
		i++;
	 }while(i<n);
//res.getWriter().write("read the file");
	 res.getWriter().write(question);
	}
	



	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{	
	//res.getWriter().write("got request");
	 res.setContentType("text/html");
	 int n;
	do
	{
	n=(int)(Math.random()*100);
	}while(n>27);
	 int i=0;
	String question;	
      String f="/WEB-INF/classes/questions.txt";
InputStream is = getServletContext().getResourceAsStream(f);
//res.getWriter().write("opened a file");
InputStreamReader isr=new InputStreamReader(is); 
      BufferedReader b=new BufferedReader(isr);
	 do
	 {
		question=b.readLine();
		i++;
	 }while(i<n);
//res.getWriter().write("read the file");
	 res.getWriter().write(question);

}}




