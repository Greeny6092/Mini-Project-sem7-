import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class request extends HttpServlet
{
	user u;
	public request(user u)
	{
		this.u=u;
	}
}



