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
		int l1=0,d1=0,id1=0,d2=0,l2=0,oppdir=0,headx1=0,heady1=0,dixs=0,diys=0,dix1f=0,diy1f=0;
		int total=0,tol=0,tot=0,tor=0,tod=0,tol1=0,tod1=0,toheadx1=0,toheady1=0,todixs=0,todiys=0,todix1f=0,todiy1f=0;
		int todatal1givenl=0,todatal1givent=0,todatal1givenr=0,todatal1givend=0;
		int todatad1givenl=0,todatad1givent=0,todatad1givenr=0,todatad1givend=0;
		int todataheadx1givenl=0,todataheadx1givent=0,todataheadx1givenr=0,todataheadx1givend=0;
		int todataheady1givenl=0,todataheady1givent=0,todataheady1givenr=0,todataheady1givend=0;
		int todatadixsgivenl=0,todatadixsgivent=0,todatadixsgivenr=0,todatadixsgivend=0;
		int todatadiysgivenl=0,todatadiysgivent=0,todatadiysgivenr=0,todatadiysgivend=0;
		int todatadix1fgivenl=0,todatadix1fgivent=0,todatadix1fgivenr=0,todatadix1fgivend=0;
		int todatadiy1fgivenl=0,todatadiy1fgivent=0,todatadiy1fgivenr=0,todatadiy1fgivend=0;
		
		double pol=0,pot=0,por=0,pod=0;
		double podatal1givenl=0,podatal1givent=0,podatal1givenr=0,podatal1givend=0;
		double podatad1givenl=0,podatad1givent=0,podatad1givenr=0,podatad1givend=0;
		double podataheadx1givenl=0,podataheadx1givent=0,podataheadx1givenr=0,podataheadx1givend=0;
		double podataheady1givenl=0,podataheady1givent=0,podataheady1givenr=0,podataheady1givend=0;
		double podatadixsgivenl=0,podatadixsgivent=0,podatadixsgivenr=0,podatadixsgivend=0;
		double podatadiysgivenl=0,podatadiysgivent=0,podatadiysgivenr=0,podatadiysgivend=0;
		double podatadix1fgivenl=0,podatadix1fgivent=0,podatadix1fgivenr=0,podatadix1fgivend=0;
		double podatadiy1fgivenl=0,podatadiy1fgivent=0,podatadiy1fgivenr=0,podatadiy1fgivend=0;
		double podatad1=0,podatal1=0,podataheadx1=0,podataheady1=0,podatadixs=0,podatadiys=0,podatadix1f=0,podatadiy1f=0;
		
		id1=Integer.parseInt(req.getParameter("id1"));
		d1=Integer.parseInt(req.getParameter("d1"));
		l1=Integer.parseInt(req.getParameter("l1"));
		d2=Integer.parseInt(req.getParameter("d2"));
		l2=Integer.parseInt(req.getParameter("l2"));
		headx1=Integer.parseInt(req.getParameter("headx1"));
		heady1=Integer.parseInt(req.getParameter("heady1"));
		oppdir=Integer.parseInt(req.getParameter("oppdir"));
		dixs=Integer.parseInt(req.getParameter("dixs"));
		diys=Integer.parseInt(req.getParameter("diys"));
		dix1f=Integer.parseInt(req.getParameter("dix1f"));
		diy1f=Integer.parseInt(req.getParameter("diy1f"));
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snakegame?useSSL=false","root","");  
			Statement stmt=con.createStatement(); 
			ResultSet rs;
			rs=stmt.executeQuery("select count(*) as total from moves;");
			rs.next();
			total=rs.getInt(1);
			out.println("total "+total);
			
			rs=stmt.executeQuery("select count(*) as total from moves where nmove1=37;");
			rs.next();
			tol=rs.getInt(1);
			out.println("tol "+tol);
			
			rs=stmt.executeQuery("select count(*) as total from moves where nmove1=38;");
			rs.next();
			tot=rs.getInt(1);
			out.println("tot "+tot);
			
			rs=stmt.executeQuery("select count(*) as total from moves where nmove1=39;");
			rs.next();
			tor=rs.getInt(1);
			out.println("tor "+tor);
			
			rs=stmt.executeQuery("select count(*) as total from moves where nmove1=40;");
			rs.next();
			tod=rs.getInt(1);	
			out.println("tod "+tod);
			
			rs=stmt.executeQuery("select count(*) as total from moves where l1="+l1+";");
			rs.next();
			tol1=rs.getInt(1);
			out.println("tol1 "+tol1);
			
			rs=stmt.executeQuery("select count(*) as total from moves where d1="+d1+";");
			rs.next();
			tod1=rs.getInt(1);
			out.println("tod1 "+tod1);
			
			rs=stmt.executeQuery("select count(*) as total from moves where headx1="+headx1+";");
			rs.next();
			toheadx1=rs.getInt(1);
			out.println("toheadx1 "+toheadx1);
			
			rs=stmt.executeQuery("select count(*) as total from moves where heady1="+heady1+";");
			rs.next();
			toheady1=rs.getInt(1);
			out.println("toheady1 "+toheady1+"\n");
			
			rs=stmt.executeQuery("select count(*) as total from moves where dixs="+dixs+";");
			rs.next();
			todixs=rs.getInt(1);
			out.println("todixs "+todixs+"\n");

			rs=stmt.executeQuery("select count(*) as total from moves where diys="+diys+";");
			rs.next();
			todiys=rs.getInt(1);
			out.println("todiys "+todiys+"\n");
			
			rs=stmt.executeQuery("select count(*) as total from moves where dix1f="+dix1f+";");
			rs.next();
			todix1f=rs.getInt(1);
			out.println("todix1f "+todix1f+"\n");		

			rs=stmt.executeQuery("select count(*) as total from moves where diy1f="+diy1f+";");
			rs.next();
			todiy1f=rs.getInt(1);
			out.println("todiy1f "+todiy1f+"\n");				
			
			/*tol/=total;
			tor/=total;
			tou/=total;
			tod/=total;*/
			
			rs=stmt.executeQuery("select count(*) as total from moves where l1="+l1+" and nmove1=37;");
			rs.next();
			todatal1givenl=rs.getInt(1);	
			out.println("todatal1givenl "+todatal1givenl);			
			rs=stmt.executeQuery("select count(*) as total from moves where l1="+l1+" and nmove1=38;");
			rs.next();
			todatal1givent=rs.getInt(1);
			out.println("todatal1givent "+todatal1givent);	
			rs=stmt.executeQuery("select count(*) as total from moves where l1="+l1+" and nmove1=39;");
			rs.next();
			todatal1givenr=rs.getInt(1);
			out.println("todatal1givenr "+todatal1givenr);	
			rs=stmt.executeQuery("select count(*) as total from moves where l1="+l1+" and nmove1=40;");
			rs.next();
			todatal1givend=rs.getInt(1);
			out.println("todatal1givend "+todatal1givend);				
			
			rs=stmt.executeQuery("select count(*) as total from moves where d1="+d1+" and nmove1=37;");
			rs.next();
			todatad1givenl=rs.getInt(1);	
			out.println("todatad1givenl "+todatad1givenl);				
			rs=stmt.executeQuery("select count(*) as total from moves where d1="+d1+" and nmove1=38;");
			rs.next();
			todatad1givent=rs.getInt(1);
			out.println("todatad1givent "+todatad1givent);				
			rs=stmt.executeQuery("select count(*) as total from moves where d1="+d1+" and nmove1=39;");
			rs.next();
			todatad1givenr=rs.getInt(1);
			out.println("todatad1givenr "+todatad1givenr);				
			rs=stmt.executeQuery("select count(*) as total from moves where d1="+d1+" and nmove1=40;");
			rs.next();
			todatad1givend=rs.getInt(1);		
			out.println("todatad1givend "+todatad1givend+" \n");				
			
			rs=stmt.executeQuery("select count(*) as total from moves where headx1="+headx1+" and nmove1=37;");
			rs.next();
			todataheadx1givenl=rs.getInt(1);		
			out.println("todataheadx1givenl "+todataheadx1givenl);				
			rs=stmt.executeQuery("select count(*) as total from moves where headx1="+headx1+" and nmove1=38;");
			rs.next();
			todataheadx1givent=rs.getInt(1);
			out.println("todataheadx1givent "+todataheadx1givent);				
			rs=stmt.executeQuery("select count(*) as total from moves where headx1="+headx1+" and nmove1=39;");
			rs.next();
			todataheadx1givenr=rs.getInt(1);
			out.println("todataheadx1givenr "+todataheadx1givenr);				
			rs=stmt.executeQuery("select count(*) as total from moves where headx1="+headx1+" and nmove1=40;");
			rs.next();
			todataheadx1givend=rs.getInt(1);
			out.println("todataheadx1givend "+todataheadx1givend);							
			
			rs=stmt.executeQuery("select count(*) as total from moves where heady1="+heady1+" and nmove1=37;");
			rs.next();
			todataheady1givenl=rs.getInt(1);		
			out.println("todataheady1givenl "+todataheady1givenl);				
			rs=stmt.executeQuery("select count(*) as total from moves where heady1="+heady1+" and nmove1=38;");
			rs.next();
			todataheady1givent=rs.getInt(1);
			out.println("todataheady1givent "+todataheady1givent);				
			rs=stmt.executeQuery("select count(*) as total from moves where heady1="+heady1+" and nmove1=39;");
			rs.next();
			todataheady1givenr=rs.getInt(1);
			out.println("todataheady1givenr "+todataheady1givenr);				
			rs=stmt.executeQuery("select count(*) as total from moves where heady1="+heady1+" and nmove1=40;");
			rs.next();
			todataheady1givend=rs.getInt(1);
			out.println("todataheady1givend "+todataheady1givend+" \n");	

			rs=stmt.executeQuery("select count(*) as total from moves where dixs="+dixs+" and nmove1=37;");
			rs.next();
			todatadixsgivenl=rs.getInt(1);	
			out.println("todatadixsgivenl "+todatadixsgivenl);			
			rs=stmt.executeQuery("select count(*) as total from moves where dixs="+dixs+" and nmove1=38;");
			rs.next();
			todatadixsgivent=rs.getInt(1);
			out.println("todatadixsgivent "+todatadixsgivent);	
			rs=stmt.executeQuery("select count(*) as total from moves where dixs="+dixs+" and nmove1=39;");
			rs.next();
			todatadixsgivenr=rs.getInt(1);
			out.println("todatadixsgivenr "+todatadixsgivenr);	
			rs=stmt.executeQuery("select count(*) as total from moves where dixs="+dixs+" and nmove1=40;");
			rs.next();
			todatadixsgivend=rs.getInt(1);
			out.println("todatadixsgivend "+todatadixsgivend);

			rs=stmt.executeQuery("select count(*) as total from moves where diys="+diys+" and nmove1=37;");
			rs.next();
			todatadiysgivenl=rs.getInt(1);	
			out.println("todatadiysgivenl "+todatadiysgivenl);			
			rs=stmt.executeQuery("select count(*) as total from moves where diys="+diys+" and nmove1=38;");
			rs.next();
			todatadiysgivent=rs.getInt(1);
			out.println("todatadiysgivent "+todatadiysgivent);	
			rs=stmt.executeQuery("select count(*) as total from moves where diys="+diys+" and nmove1=39;");
			rs.next();
			todatadiysgivenr=rs.getInt(1);
			out.println("todatadiysgivenr "+todatadiysgivenr);	
			rs=stmt.executeQuery("select count(*) as total from moves where diys="+diys+" and nmove1=40;");
			rs.next();
			todatadiysgivend=rs.getInt(1);
			out.println("todatadiysgivend "+todatadiysgivend);	

			rs=stmt.executeQuery("select count(*) as total from moves where dix1f="+dix1f+" and nmove1=37;");
			rs.next();
			todatadix1fgivenl=rs.getInt(1);	
			out.println("todatadix1fgivenl "+todatadix1fgivenl);			
			rs=stmt.executeQuery("select count(*) as total from moves where dix1f="+dix1f+" and nmove1=38;");
			rs.next();
			todatadix1fgivent=rs.getInt(1);
			out.println("todatadix1fgivent "+todatadix1fgivent);	
			rs=stmt.executeQuery("select count(*) as total from moves where dix1f="+dix1f+" and nmove1=39;");
			rs.next();
			todatadix1fgivenr=rs.getInt(1);
			out.println("todatadix1fgivenr "+todatadix1fgivenr);	
			rs=stmt.executeQuery("select count(*) as total from moves where dix1f="+dix1f+" and nmove1=40;");
			rs.next();
			todatadix1fgivend=rs.getInt(1);
			out.println("todatadix1fgivend "+todatadix1fgivend);	

			rs=stmt.executeQuery("select count(*) as total from moves where diy1f="+diy1f+" and nmove1=37;");
			rs.next();
			todatadiy1fgivenl=rs.getInt(1);	
			out.println("todatadiy1fgivenl "+todatadiy1fgivenl);			
			rs=stmt.executeQuery("select count(*) as total from moves where diy1f="+diy1f+" and nmove1=38;");
			rs.next();
			todatadiy1fgivent=rs.getInt(1);
			out.println("todatadiy1fgivent "+todatadiy1fgivent);	
			rs=stmt.executeQuery("select count(*) as total from moves where diy1f="+diy1f+" and nmove1=39;");
			rs.next();
			todatadiy1fgivenr=rs.getInt(1);
			out.println("todatadiy1fgivenr "+todatadiy1fgivenr);	
			rs=stmt.executeQuery("select count(*) as total from moves where diy1f="+diy1f+" and nmove1=40;");
			rs.next();
			todatadiy1fgivend=rs.getInt(1);
			out.println("todatadiy1fgivend "+todatadiy1fgivend);			
		}
		catch(Exception e)
		{
			out.println("Error :"+e);
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
			podatadixs=(double)todixs/(double)total;
			podatadiys=(double)todiys/(double)total;
			podatadix1f=(double)todix1f/(double)total;
			podatadiy1f=(double)todiy1f/(double)total;
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
			podatadixs=0;
			podatadiys=0;
			podatadix1f=0;
			podatadiy1f=0;
		}
		

		
		podatal1givenl=(double)todatal1givenl/(double)tol;
		podatal1givent=(double)todatal1givent/(double)tot;
		podatal1givenr=(double)todatal1givenr/(double)tor;
		podatal1givend=(double)todatal1givend/(double)tod;
		out.println("podatal1givenl "+podatal1givenl+"\npodatal1givent "+podatal1givent+"\npodatal1givenr "+podatal1givenr+"\npodatal1givend "+podatal1givend+"\n\n");
	
		podatad1givenl=(double)todatal1givenl/(double)tol;
		podatad1givent=(double)todatal1givent/(double)tot;
		podatad1givenr=(double)todatal1givenr/(double)tor;
		podatad1givend=(double)todatal1givend/(double)tod;
		out.println("podatad1givenl "+podatad1givenl+"\npodatad1givent "+podatad1givent+"\npodatad1givenr "+podatad1givenr+"\npodatad1givend "+podatad1givend+"\n\n");
		
		podataheadx1givenl=(double)todataheadx1givenl/(double)tol;
		podataheadx1givent=(double)todataheadx1givent/(double)tot;
		podataheadx1givenr=(double)todataheadx1givenr/(double)tor;
		podataheadx1givend=(double)todataheadx1givend/(double)tod;
		out.println("podataheadx1givenl "+podataheadx1givenl+"\npodataheadx1givent "+podataheadx1givent+"\npodataheadx1givenr "+podataheadx1givenr+"\npodataheadx1givend "+podataheadx1givend+"\n\n");
		
		podataheady1givenl=(double)todataheady1givenl/(double)tol;
		podataheady1givent=(double)todataheady1givent/(double)tot;
		podataheady1givenr=(double)todataheady1givenr/(double)tor;
		podataheady1givend=(double)todataheady1givend/(double)tod;
		out.println("podataheady1givenl "+podataheady1givenl+"\npodataheady1givent "+podataheady1givent+"\npodataheady1givenr "+podataheady1givenr+"\npodataheady1givend "+podataheady1givend+"\n\n");
		
		podatadixsgivenl=(double)todatadixsgivenl/(double)tol;
		podatadixsgivent=(double)todatadixsgivent/(double)tot;
		podatadixsgivenr=(double)todatadixsgivenr/(double)tor;
		podatadixsgivend=(double)todatadixsgivend/(double)tod;
		out.println("podatadixsgivenl "+podatadixsgivenl+"\npodatadixsgivent "+podatadixsgivent+"\npodatadixsgivenr "+podatadixsgivenr+"\npodatadixsgivend "+podatadixsgivend+"\n\n");		
		
		podatadiysgivenl=(double)todatadiysgivenl/(double)tol;
		podatadiysgivent=(double)todatadiysgivent/(double)tot;
		podatadiysgivenr=(double)todatadiysgivenr/(double)tor;
		podatadiysgivend=(double)todatadiysgivend/(double)tod;
		out.println("podatadiysgivenl "+podatadiysgivenl+"\npodatadiysgivent "+podatadiysgivent+"\npodatadiysgivenr "+podatadiysgivenr+"\npodatadiysgivend "+podatadiysgivend+"\n\n");				

		podatadix1fgivenl=(double)todatadix1fgivenl/(double)tol;
		podatadix1fgivent=(double)todatadix1fgivent/(double)tot;
		podatadix1fgivenr=(double)todatadix1fgivenr/(double)tor;
		podatadix1fgivend=(double)todatadix1fgivend/(double)tod;
		out.println("podatadix1fgivenl "+podatadix1fgivenl+"\npodatadix1fgivent "+podatadix1fgivent+"\npodatadix1fgivenr "+podatadix1fgivenr+"\npodatadix1fgivend "+podatadix1fgivend+"\n\n");				

		podatadiy1fgivenl=(double)todatadiy1fgivenl/(double)tol;
		podatadiy1fgivent=(double)todatadiy1fgivent/(double)tot;
		podatadiy1fgivenr=(double)todatadiy1fgivenr/(double)tor;
		podatadiy1fgivend=(double)todatadiy1fgivend/(double)tod;
		out.println("podatadiy1fgivenl "+podatadiy1fgivenl+"\npodatadiy1fgivent "+podatadiy1fgivent+"\npodatadiy1fgivenr "+podatadiy1fgivenr+"\npodatadiy1fgivend "+podatadiy1fgivend+"\n\n");						
		
		polgivendata=podatal1givenl*podatad1givenl*podataheadx1givenl*podataheady1givenl*podatadixsgivenl*podatadiysgivenl*podatadix1fgivenl*podatadiy1fgivenl*pol;
		out.println("polgivendata "+polgivendata);

		potgivendata=podatal1givent*podatad1givent*podataheadx1givent*podataheady1givent*podatadixsgivent*podatadiysgivent*podatadix1fgivent*podatadiy1fgivent*pot;
		out.println("potgivendata "+potgivendata);
	
		porgivendata=podatal1givenr*podatad1givenr*podataheadx1givenr*podataheady1givenr*podatadixsgivenr*podatadiysgivenr*podatadix1fgivenr*podatadiy1fgivenr*por;
		out.println("porgivendata "+porgivendata);
		
		podgivendata=podatal1givend*podatad1givend*podataheadx1givend*podataheady1givend*podatadixsgivend*podatadiysgivend*podatadix1fgivend*podatadiy1fgivend*pod;
		out.println("podgivendata "+podgivendata);		
		
		double total_final=polgivendata+potgivendata+porgivendata+podgivendata;
		out.println("total_final "+total_final);
		double finall=polgivendata/total_final;
		out.println("finall "+finall);
		double finalt=potgivendata/total_final;
		out.println("finalt "+finalt);
		double finalr=porgivendata/total_final;
		out.println("finalr "+finalr);
		double finald=podgivendata/total_final;
		out.println("finald "+finald);
		
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



