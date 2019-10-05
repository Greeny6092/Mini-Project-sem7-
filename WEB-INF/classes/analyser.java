import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.Date;
import org.json.*;

public class analyser extends HttpServlet
{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{	
		PrintWriter out=res.getWriter();
		
		double polgivendata=0,potgivendata=0,porgivendata=0,podgivendata=0;
		int l1=0,d1=0,id1=0,d2=0,l2=0,oppdir=0,headx1=0,heady1=0;
		int total=0,tol=0,tot=0,tor=0,tod=0,tol1=0,tod1=0,toheadx1=0,toheady1=0;
		int todatal1givenl=0,todatal1givent=0,todatal1givenr=0,todatal1givend=0;
		int todatad1givenl=0,todatad1givent=0,todatad1givenr=0,todatad1givend=0;
		int todataheadx1givenl=0,todataheadx1givent=0,todataheadx1givenr=0,todataheadx1givend=0;
		int todataheady1givenl=0,todataheady1givent=0,todataheady1givenr=0,todataheady1givend=0;
		
		double pol=0,pot=0,por=0,pod=0;
		double podatal1givenl=0,podatal1givent=0,podatal1givenr=0,podatal1givend=0;
		double podatad1givenl=0,podatad1givent=0,podatad1givenr=0,podatad1givend=0;
		double podataheadx1givenl=0,podataheadx1givent=0,podataheadx1givenr=0,podataheadx1givend=0;
		double podataheady1givenl=0,podataheady1givent=0,podataheady1givenr=0,podataheady1givend=0;
		double podatad1=0,podatal1=0,podataheadx1=0,podataheady1;
		
		id1=Integer.parseInt(req.getParameter("id1"));
		d1=Integer.parseInt(req.getParameter("d1"));
		l1=Integer.parseInt(req.getParameter("l1"));
		d2=Integer.parseInt(req.getParameter("d2"));
		l2=Integer.parseInt(req.getParameter("l2"));
		headx1=Integer.parseInt(req.getParameter("headx1"));
		heady1=Integer.parseInt(req.getParameter("heady1"));
		oppdir=Integer.parseInt(req.getParameter("oppdir"));
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snakegame?useSSL=false","root","");  
			Statement stmt=con.createStatement(); 
			ResultSet rs;
			rs=stmt.executeQuery("select count(*) as total from moves;");
			rs.next();
			total=rs.getInt(1);
			//out.println("total "+total);
			
			rs=stmt.executeQuery("select count(*) as total from moves where nmove1=37;");
			rs.next();
			tol=rs.getInt(1);
			//out.println("tol "+tol);
			
			rs=stmt.executeQuery("select count(*) as total from moves where nmove1=38;");
			rs.next();
			tot=rs.getInt(1);
			//out.println("tot "+tot);
			
			rs=stmt.executeQuery("select count(*) as total from moves where nmove1=39;");
			rs.next();
			tor=rs.getInt(1);
			//out.println("tor "+tor);
			
			rs=stmt.executeQuery("select count(*) as total from moves where nmove1=40;");
			rs.next();
			tod=rs.getInt(1);	
			//out.println("tod "+tod);
			
			rs=stmt.executeQuery("select count(*) as total from moves where l1="+l1+";");
			rs.next();
			tol1=rs.getInt(1);
			//out.println("tol1 "+tol1);
			
			rs=stmt.executeQuery("select count(*) as total from moves where d1="+d1+";");
			rs.next();
			tod1=rs.getInt(1);
			//out.println("tod1 "+tod1);
			
			rs=stmt.executeQuery("select count(*) as total from moves where headx1="+headx1+";");
			rs.next();
			toheadx1=rs.getInt(1);
			//out.println("toheadx1 "+toheadx1);
			
			rs=stmt.executeQuery("select count(*) as total from moves where heady1="+heady1+";");
			rs.next();
			toheady1=rs.getInt(1);
			//out.println("toheady1 "+toheady1+"\n");
			
			/*tol/=total;
			tor/=total;
			tou/=total;
			tod/=total;*/
			
			rs=stmt.executeQuery("select count(*) as total from moves where l1="+l1+" and nmove1=37;");
			rs.next();
			todatal1givenl=rs.getInt(1);	
			//out.println("todatal1givenl "+todatal1givenl);			
			rs=stmt.executeQuery("select count(*) as total from moves where l1="+l1+" and nmove1=38;");
			rs.next();
			todatal1givent=rs.getInt(1);
			//out.println("todatal1givent "+todatal1givent);	
			rs=stmt.executeQuery("select count(*) as total from moves where l1="+l1+" and nmove1=39;");
			rs.next();
			todatal1givenr=rs.getInt(1);
			//out.println("todatal1givenr "+todatal1givenr);	
			rs=stmt.executeQuery("select count(*) as total from moves where l1="+l1+" and nmove1=40;");
			rs.next();
			todatal1givend=rs.getInt(1);
			//out.println("todatal1givend "+todatal1givend);				
			
			rs=stmt.executeQuery("select count(*) as total from moves where d1="+d1+" and nmove1=37;");
			rs.next();
			todatad1givenl=rs.getInt(1);	
			//out.println("todatad1givenl "+todatad1givenl);				
			rs=stmt.executeQuery("select count(*) as total from moves where d1="+d1+" and nmove1=38;");
			rs.next();
			todatad1givent=rs.getInt(1);
			//out.println("todatad1givent "+todatad1givent);				
			rs=stmt.executeQuery("select count(*) as total from moves where d1="+d1+" and nmove1=39;");
			rs.next();
			todatad1givenr=rs.getInt(1);
			//out.println("todatad1givenr "+todatad1givenr);				
			rs=stmt.executeQuery("select count(*) as total from moves where d1="+d1+" and nmove1=40;");
			rs.next();
			todatad1givend=rs.getInt(1);		
			//out.println("todatad1givend "+todatad1givend+" \n");				
			
			rs=stmt.executeQuery("select count(*) as total from moves where headx1="+headx1+" and nmove1=37;");
			rs.next();
			todataheadx1givenl=rs.getInt(1);		
			//out.println("todataheadx1givenl "+todataheadx1givenl);				
			rs=stmt.executeQuery("select count(*) as total from moves where headx1="+headx1+" and nmove1=38;");
			rs.next();
			todataheadx1givent=rs.getInt(1);
			//out.println("todataheadx1givent "+todataheadx1givent);				
			rs=stmt.executeQuery("select count(*) as total from moves where headx1="+headx1+" and nmove1=39;");
			rs.next();
			todataheadx1givenr=rs.getInt(1);
			//out.println("todataheadx1givenr "+todataheadx1givenr);				
			rs=stmt.executeQuery("select count(*) as total from moves where headx1="+headx1+" and nmove1=40;");
			rs.next();
			todataheadx1givend=rs.getInt(1);
			//out.println("todataheadx1givend "+todataheadx1givend);							
			
			rs=stmt.executeQuery("select count(*) as total from moves where heady1="+heady1+" and nmove1=37;");
			rs.next();
			todataheady1givenl=rs.getInt(1);		
			//out.println("todataheady1givenl "+todataheady1givenl);				
			rs=stmt.executeQuery("select count(*) as total from moves where heady1="+heady1+" and nmove1=38;");
			rs.next();
			todataheady1givent=rs.getInt(1);
			//out.println("todataheady1givent "+todataheady1givent);				
			rs=stmt.executeQuery("select count(*) as total from moves where heady1="+heady1+" and nmove1=39;");
			rs.next();
			todataheady1givenr=rs.getInt(1);
			//out.println("todataheady1givenr "+todataheady1givenr);				
			rs=stmt.executeQuery("select count(*) as total from moves where heady1="+heady1+" and nmove1=40;");
			rs.next();
			todataheady1givend=rs.getInt(1);
			//out.println("todataheady1givend "+todataheady1givend+" \n");				
		}
		catch(Exception e)
		{
			//out.println("Error :"+e);
		}
		
		if(total!=0)
		{
			pol=(double)tol/(double)total;
			pot=(double)tot/(double)total;
			por=(double)tor/(double)total;
			pod=(double)tod/(double)total;
			podatal1=(double)tol1/(double)total;
			podatad1=(double)tod1/(double)total;
			podataheadx1=(double)toheadx1/(double)total;
			podataheady1=(double)toheady1/(double)total;
		}
		else
		{
			pol=0;
			pot=0;
			por=0;
			pod=0;
			podatal1=0;
			podatad1=0;
			podataheadx1=0;
			podataheady1=0;
		}
		

		
		podatal1givenl=(double)todatal1givenl/(double)tol;
		podatal1givent=(double)todatal1givent/(double)tot;
		podatal1givenr=(double)todatal1givenr/(double)tor;
		podatal1givend=(double)todatal1givend/(double)tod;
		//out.println("podatal1givenl "+podatal1givenl+"\npodatal1givent "+podatal1givent+"\npodatal1givenr "+podatal1givenr+"\npodatal1givend "+podatal1givend+"\n\n");
	
		podatad1givenl=(double)todatal1givenl/(double)tol;
		podatad1givent=(double)todatal1givent/(double)tot;
		podatad1givenr=(double)todatal1givenr/(double)tor;
		podatad1givend=(double)todatal1givend/(double)tod;
		//out.println("podatad1givenl "+podatad1givenl+"\npodatad1givent "+podatad1givent+"\npodatad1givenr "+podatad1givenr+"\npodatad1givend "+podatad1givend+"\n\n");
		
		podataheadx1givenl=(double)todataheadx1givenl/(double)tol;
		podataheadx1givent=(double)todataheadx1givent/(double)tot;
		podataheadx1givenr=(double)todataheadx1givenr/(double)tor;
		podataheadx1givend=(double)todataheadx1givend/(double)tod;
		//out.println("podataheadx1givenl "+podataheadx1givenl+"\npodataheadx1givent "+podataheadx1givent+"\npodataheadx1givenr "+podataheadx1givenr+"\npodataheadx1givend "+podataheadx1givend+"\n\n");
		
		podataheady1givenl=(double)todataheady1givenl/(double)tol;
		podataheady1givent=(double)todataheady1givent/(double)tot;
		podataheady1givenr=(double)todataheady1givenr/(double)tor;
		podataheady1givend=(double)todataheady1givend/(double)tod;
		//out.println("podataheady1givenl "+podataheady1givenl+"\npodataheady1givent "+podataheady1givent+"\npodataheady1givenr "+podataheady1givenr+"\npodataheady1givend "+podataheady1givend+"\n\n");
		
		polgivendata=podatal1givenl*podatad1givenl*podataheadx1givenl*podataheady1givenl*pol;
		//out.println("polgivendata "+polgivendata);
		potgivendata=podatal1givent*podatad1givent*podataheadx1givent*podataheady1givent*pot;
		//out.println("potgivendata "+potgivendata);
		porgivendata=podatal1givenr*podatad1givenr*podataheadx1givenr*podataheady1givenr*por;
		//out.println("porgivendata "+porgivendata);
		podgivendata=podatal1givend*podatad1givend*podataheadx1givend*podataheady1givend*pod;
		//out.println("podgivendata "+podgivendata);
		
		double total_final=polgivendata+potgivendata+porgivendata+podgivendata;
		//out.println("total_final "+total_final);
		double finall=polgivendata/total_final;
		//out.println("finall "+finall);
		double finalt=potgivendata/total_final;
		//out.println("finalt "+finalt);
		double finalr=porgivendata/total_final;
		//out.println("finalr "+finalr);
		double finald=podgivendata/total_final;
		//out.println("finald "+finald);
		
		if(finall>finalt)
		{
			if(finall>finalr)
			{
				if(finall>finald)
				{
					out.println("37");
				}
				else
				{
					out.println("40");
				}
			}
			else if(finalr>finald)
			{
				out.println("39");
			}
			else
			{
				out.println("40");
			}
		}
		else if(finalt>finalr)
		{
			if(finalt>finald)
			{
				out.println("38");
			}
			else
			{
				out.println("40");
			}
		}
		else if(finalr>finald)
		{
			out.println("39");
		}
		else 
		{
			out.println("40");
		}
	}
	
}



