import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import org.json.*;

public class Snake extends HttpServlet
{
	String color=new String();
	int move;
	String object;
	int position_flag=0;
	int addflag=0,removeflag=0,position=0;
	Queue<Integer> moveQueue=new LinkedList<Integer>();
	ArrayList<BodyParts> bodyparts=new ArrayList<BodyParts>();
	public Snake(String color,int initialdir)
	{
		//this.uid=uid;
		this.color=color;
		move=initialdir;
	}
	
	public void  setObject(String o)
	{
		object=new String(o);
	}
	
	public void addNewBodyPart()
	{
		int cur_move=(int)(moveQueue.peek());
		BodyParts last=bodyparts.get(bodyparts.size()-1);
		BodyParts beforelast=bodyparts.get(bodyparts.size()-2);
		int newX,newY;
		if(last.x==beforelast.x)
		{
			newX=last.x;
			if(last.y==38)
			{
				newY=(last.y+1)%71;
			}
			else
			{
				newY=(last.y-1);
				if(newY<0)
				{
					newY=70;
				}
			}
			BodyParts newPart=new BodyParts(newX,newY);
			bodyparts.add(newPart);
		}
		else if(last.y==beforelast.y)
		{
			newY=last.y;
			if(last.x==37)
			{
				newX=(last.x+1)%185;
			}
			else
			{
				newX=(last.x-1);
				if(newX<0)
				{
					newX=184;
				}
			}
			BodyParts newPart=new BodyParts(newX,newY);
			bodyparts.add(newPart);
		}
	}
	
	public void setPosition(int playerno)
	{
		BodyParts b;
			if(playerno==1)
			{
				for(int i=2;i>=0;i--)
				{
					b=new BodyParts(5,i);
					bodyparts.add(b);
				}
			}

		if(playerno==2)
		{
			for(int i=9;i<12;i++)
			{
				b=new BodyParts(6,i);
				bodyparts.add(b);
			}
		}
	}
	
	public void nextPosition()
	{
		/*while(moveQueue.size()>1)
		{
			BodyParts b;
			for(int i=1;i<moveQueue.size();i++)
			{
				b=bodyparts.get(i-1);
				bodyparts.add(i,b);
			}
			b=bodyparts.get(0);
			switch(moveQueue.remove())
			{
				case 1:
					b.x=(b.x-1);
					if(b.x<0)
					{
						b.x=9;
					}
					break;
				case 2:
					b.y=(b.y+1)%12;
					break;
				case 3:
					b.x=(b.x+1)%10;
					break;
				case 4:
				b.y=(b.y-1);
				if(b.y<0)
				{
					b.y=12;
				}
					break;	
			}
		}*/
		
		BodyParts b;
			for(int i=bodyparts.size()-1;i>0;i--)
			{
				b=new BodyParts(bodyparts.get(i-1));
				//bodyparts[i].x=bodyparts[i-1].x;
				//bodyparts[i].y=bodyparts[i-1].y;
				bodyparts.set(i,b);
			}
		b=new BodyParts(bodyparts.get(0));
			switch(moveQueue.peek())
			{
				case 38:
					b.x=(b.x-1);
					if(b.x<0)
					{
						b.x=81;
					}
					break;
				case 39:
					b.y=(b.y+1)%185;
					break;
				case 40:
					b.x=(b.x+1)%82;
					break;
				case 37:
				b.y=(b.y-1);
				if(b.y<0)
				{
					b.y=185;
				}
					break;	
			}
			bodyparts.set(0,b);
		if(moveQueue.size()>1)
		{
			moveQueue.remove();
		}
	}
	
	public String stringizeBodyParts()
	{
		nextPosition();
		String output=new String();
		for(BodyParts b:bodyparts)
		{
			output+=b.x+","+b.y+",";
		}
		
		return output;
	}
	
	public int getLast()
	{
		return move;
	}
	
	class BodyParts
	{
		int x,y;
		
		public BodyParts(BodyParts t)
		{
			this.x=t.x;
			this.y=t.y;
		}
		
		public BodyParts(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
	}
}



