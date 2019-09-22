import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class gameboard extends HttpServlet
{
	user u1;
	user u2;
	int status,cur_u1_move,cur_u2_move;
	int gameboard_id;
	int row_count,col_count;
	static int gameboard_count=0;
	int matrix[][]=new int[10][12];
	int foodstatus=0;
	int pauseflag=1;
	int calibrate_flag=1;
	public void nextMove()
	{
				
	}
	
	
	public String getString()
	{
		nextMove();
		String output=new String();
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<12;j++)
			{
				output+=(Integer.toString(matrix[i][j]));
			}
		}
		return output;
	}
	public gameboard(user u1,user u2,int row_count,int col_count)
	{
		this.u1=u1;
		this.u2=u2;
		this.row_count=row_count;
		this.col_count=col_count;
		this.u1.snake=new Snake("red",39);
		this.u2.snake=new Snake("blue",37);
		status=1;
		gameboard_id=gameboard.gameboard_count;
		//u1move.add(2);
		//cur_u1_move=1;
		//u2move.add(4);
		//cur_u2_move=4;
		gameboard.gameboard_count++;
	}
}



