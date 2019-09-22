<html>
<head>
<title>game</title>
<style>
	td
	{
		width:2vw;
		height:2vh;

	}
	button
	{
		background-color:rgba(100,50,50,0.8);
	}
	button.control
	{
		width:32vw;
		height:30vh;
		font-weight:bold;
		font-size:16pt;
		border-radius:2vw;
		background-color:rgba(255,0,10,0.1);
		border:transparent;
	}
	
</style>
</head>
<body onload="createBoard()">
<!--<div style="position:fixed;float:right;display:none;" name="control_board">
	<table style="background-color:rgba(255,255,255,0.3);border-radius:2vw;">
	<tr>
	<td></td>
	<td><button name="up" class="control" onclick="setMove(38)">^</button></td>
	<td></td>
	</tr>
	<tr><td><button name="up" class="control" onclick="setMove(37)"> < </button></td>
		<td></td>
		<td><button name="up" class="control" onclick="setMove(39)"> > </button></td>
	</tr>
	<tr>
	<td></td>
	<td><button name="up" class="control" onclick="setMove(40)">V</button></td>
	<td></td>	
	</tr>
	</table>
	</div>-->
	<div id="liveuserbox">
	</div>
	<div>
		<center>
			<table name="table" border="0" style="background-color:black;" cellspacing="0"><tr name="row"><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr></table>
		</center>
	</div>
	<div id="requestbox">
	<table name="requesttable">
		<tr name="requestrow">
		</tr>
	</table>
	</div>
	<script>
		
		var id;
		var gamestartflag=0;
		var gameboard_id;
		var foodcolor="green";
		function get_live_users()
		{
			var liveusers=document.getElementById("liveuserbox");
			var node;
			var source = new EventSource('./LiveUserLister'); 
			 source.onmessage=function(event)
            {
				liveusers.innerHTML="";
				let ids=event.data.split("$")[0].split(",");
				let names=event.data.split("$")[1].split(",");
				for(i=1;i<ids.length;i++)
				{
					let temp=ids[i];
					
					if(!(ids[i].includes(id)))
					{
						//alert("list");
						node=document.createElement("button");
						node.setAttribute("onclick","challenge("+ids[i]+")");
						node.setAttribute("style","border-radius:2vw;width:18vw;height:5vh;");
						node.innerHTML=names[i];
						liveusers.appendChild(node);
						if(gamestartflag==1)
						{
							liveusers.style.display="none";
						}
					}
				}
				
            };
			get_request_details();
		}
		
		function get_request_details()
		{
			var requestbox=document.getElementsByName("requestbox")[0];
			var requesttable=document.getElementsByName("requesttable")[0];
			var requestrow=document.getElementsByName("requestrow")[0];
			var source = new EventSource('./RequestTeller?id='+id); 
			source.addEventListener("live_users",function(event)
			{
					//alert("got request");
					console.log("you got request!!! "+event.data+" end");
					requesttable.innerHTML="<tr name='requestrow'></tr>";
					let requested=event.data.split("$")[1].split(",");
					let names=event.data.split("$")[0].split(",");
					for(i=0;i<requested.length-1;i++)
					{
						let newrow=requestrow.cloneNode(true);
						newrow.innerHTML="<td>"+names[i]+"</td><td><button style='background-color:green;' onclick='accept("+requested[i]+",1)'>Accept</button></td><td><button style='background-color:red;' onclick='accept("+requested[i]+",0)'>Decline</button></td>";
						requesttable.appendChild(newrow);
					}
					//confirm("You got request from "+);
			})
			
			source.addEventListener("gamestartflag",startflag=function(event)
            {
				console.log(event.data);
				var temp=gamestartflag;
					gamestartflag=event.data.split(",")[0];
					if(gamestartflag==1)
					{
						document.getElementById("requestbox").style.display="none";
						gameboard_id=event.data.split(",")[1];
						//document.getElementsByName("control_board")[0].style.display="inline-block";
						document.getElementsByName("table")[0].style.display="inline-block";
						init_board();
						if(gamestartflag!=temp)
						{
							//start_snake_action();
							console.log("called start_snake_action");
						}
						listentogameboard();
						
						source.removeEventListener("gamestartflag",startflag);
					}
					//console.log("game board id is "+gameboard_id+"gameflag is "+gamestartflag);
					
            })
			
		}
		
		function challenge(id2)
		{
			  var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() 
				{
				  if (this.readyState == 4 && this.status == 200) 
				  {
					//id = this.responseText;
					mysnake=snake1;
					mysnake.id=id;
					alert("challenge request sent!!!");
				  }
				};
				xhttp.open("GET", "./getid?id1="+id+"&id2="+id2+"&t=1", false);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhttp.send();
		}
		
		function accept(u2,accept)
		{
				//alert("Entered");
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() 
				{
				  if (this.readyState == 4 && this.status == 200) 
				  {
					//id = this.responseText;
					mysnake=snake2;
					mysnake.id=id;
					console.log("accepted!!!");
				  }
				};
				snake1.uid=u2;
				snake2.uid=id;
				xhttp.open("GET", "./getid?u1="+u2+"&u2="+id+"&t=2&accept="+accept+"&row_count="+row_count+"&col_count="+col_count+"&snake1="+JSON.stringify(snake1)+"&snake2="+JSON.stringify(snake2), false);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhttp.send();
		}
		var pauseflag=1,startcount=0;
		function listentogameboard()
		{
			var source = new EventSource('./GameBoardTeller?gid='+gameboard_id+"&id="+id);
			var blue=null,red=null;
			source.addEventListener("boardstatus",function(event)
            {
				var rows=document.getElementsByName("row");
				
				console.log("got game board status !!! "+event.data+" length "+event.data.length+" retry "+event);
				let data=event.data.split("$");
				//snake1.direction=parseInt(data[0]);
				//snake2.direction=parseInt(data[1]);
				console.log(data[2].length);
				let x=parseInt(data[2].split(",")[0]);
				let y=parseInt(data[2].split(",")[1]);
				if(parseInt(data[7])==1)
				{
					let dummy=JSON.parse(data[0]);
					if(dummy.uid!=mysnake.id)
					{
						clearsnake(snake1);
						snake1=JSON.parse(data[0]);
					}
				}
				if(parseInt(data[8])==1)
				{
					let dummy=JSON.parse(data[1]);
					if(dummy.uid!=mysnake.id)
					{
						clearsnake(snake2);
						snake2=JSON.parse(data[1]);
					}
				}
				if(pauseflag!=0&&parseInt(data[6])==0)
				{
					//alert(pauseflag+" "+parseInt(data[6]));
					start_snake_action();
					pauseflag=0;
				}
				else if(parseInt(data[6])==1&&startcount==1)
				{
					console.log("going to call pause game");
					pausegame();
					pauseflag=parseInt(data[6]);
				}
				if(pauseflag==0)
				{
					if(x!=-1&&y!=-1)
					{
						let row=document.getElementsByName("row");
						row[x].childNodes[y].style.backgroundColor=foodcolor;
					}
					let addflags=data[3].split(",");
					if(addflags[0]==1)
					{
						addBodyPart(1);
					}
					if(addflags[1]==1)
					{
						addBodyPart(2);
					}
				}
            })			
		}
		




	var row_count;
	var col_count=document.getElementsByName("row")[0].childNodes.length;
	var max_snake_len;
	var snake1;
	var snake2;
	var mysnake;
	var interval;	
		function init_snake()
		{
			snake1= {
						uid:"",
						body:[{x:0,y:0}],
						//body:new array(),
						color:"red",
						direction:39
					};
			snake2= {
						uid:"",
						//body:bodyparts[max_snake_len],
						body:[{x:0,y:0}],
						color:"blue",
						direction:37
					};		
			let i;
			let x=parseInt(row_count/2),y=2;
			snake1.body.pop();
			for(i=0;i<3;i++,y--)
			{
				let bodypart=new Object();
				bodypart.x=x;
				bodypart.y=y;
				snake1.body.push(bodypart);
			}
			x=parseInt((row_count/2)+1);
			y=col_count-3;
			snake2.body.pop();
			for(i=0;i<3;i++,y++)
			{
				let bodypart=new Object();
				bodypart.x=x;
				bodypart.y=y;
				snake2.body.push(bodypart);
			}
			for(i=0;i<3;i++)
			console.log("snake 1 body "+snake1.body[i].x+" "+snake1.body[i].y+"\nsnake 2 body "+snake2.body[i].x+" "+snake2.body[i].y);
			init_board();
		}
		
		function init_board()
		{
			let i;
			let rows;
			rows=document.getElementsByName("row");
			console.log("called init board");
			for(i=0;i<3;i++)
			{
				console.log("enter loop");
				rows[snake1.body[i].x].childNodes[snake1.body[i].y].style.backgroundColor=snake1.color;
				rows[snake2.body[i].x].childNodes[snake2.body[i].y].style.backgroundColor=snake2.color;
			}
		}
		
		function createBoard()
		{
			
			console.log("called createBoard!!");
			id=sessionStorage.getItem('id');
			//alert("id is "+id);
			var gameboard=document.getElementsByName("gameboard")[0];
			var row=document.getElementsByName("row")[0];
			var table=document.getElementsByName("table")[0];
			var row=document.getElementsByName("row")[0];
			
			var i;
			for(i=0;i<40;i++)
			{
				let newrow=row.cloneNode(true);
				table.appendChild(newrow);
			}
			table.style.display="none";
			row_count=document.getElementsByName("row").length;
			max_snake_len=row_count*col_count;
			console.log("row_count = "+row_count+"\ncol_count = "+col_count);
			get_live_users();
			init_snake();
			//start_snake_action();
		}
		
		function nextmove()
		{
			let i;
			console.log("nextmove called");
			let rows=document.getElementsByName("row");
			rows[snake1.body[snake1.body.length-1].x].childNodes[snake1.body[snake1.body.length-1].y].style.backgroundColor="transparent";
			rows[snake2.body[snake2.body.length-1].x].childNodes[snake2.body[snake2.body.length-1].y].style.backgroundColor="transparent";
			for(i=snake1.body.length-1;i>0;i--)
			{
				snake1.body[i].x=snake1.body[i-1].x;
				snake1.body[i].y=snake1.body[i-1].y;
			}
			
			for(i=snake2.body.length-1;i>0;i--)
			{
				snake2.body[i].x=snake2.body[i-1].x;
				snake2.body[i].y=snake2.body[i-1].y;
			}
			
			switch(snake1.direction)
			{
				case 37:
						if(snake1.body[0].y<=0)
						{
							snake1.body[0].y=col_count-1;
						}
						else
						{
							snake1.body[0].y--;
						}
						break;
				case 38:
						if(snake1.body[0].x<=0)
						{
							snake1.body[0].x=row_count-1;
						}
						else
						{
							snake1.body[0].x--;
						}
						break;
				case 39:
						snake1.body[0].y=(snake1.body[0].y+1)%col_count;
						break;
				case 40:
						snake1.body[0].x=(snake1.body[0].x+1)%row_count;
						break;						
			}
			if(rows[snake1.body[0].x].childNodes[snake1.body[0].y].style.backgroundColor==foodcolor)
			{
				reportmysnakestatus(1,0,gameboard_id,1);
			}
			switch(snake2.direction)
			{
				case 37:
						if(snake2.body[0].y<=0)
						{
							snake2.body[0].y=col_count-1;
						}
						else
						{
							snake2.body[0].y--;
						}
						break;
				case 38:
						if(snake2.body[0].x<=0)
						{
							snake2.body[0].x=row_count-1;
						}
						else
						{
							snake2.body[0].x--;
						}
						break;
				case 39:
						snake2.body[0].y=(snake2.body[0].y+1)%col_count;
						break;
				case 40:
						snake2.body[0].x=(snake2.body[0].x+1)%row_count;
						break;						
			}
			
			if(rows[snake2.body[0].x].childNodes[snake2.body[0].y].style.backgroundColor==foodcolor)
			{
				reportmysnakestatus(1,0,gameboard_id,2);
			}
			redraw_snakes();
		}
		
		function redraw_snakes()
		{
			let i;
			console.log("called redraw snake");
			let rows=document.getElementsByName("row");
			for(i=0;i<snake1.body.length;i++)
			{
				rows[snake1.body[i].x].childNodes[snake1.body[i].y].style.backgroundColor=snake1.color;
				
			}
			
			for(i=0;i<snake2.body.length;i++)
			{
				rows[snake2.body[i].x].childNodes[snake2.body[i].y].style.backgroundColor=snake2.color;
			}
		}
		
		function start_snake_action()
		{
			//alert("gameboard id "+gameboard_id);
			//alert("my snake "+mysnake.color);
			interval=setInterval(nextmove,120);
		}
		
		document.onkeydown=function(e)
			{
				//alert(e.keyCode);
				console.log("got input");
				let move=e.keyCode
				let diff=move-mysnake.direction;
				if((move==37||move==38||move==39||move==40)&&(gamestartflag==1)&&(diff!=2&&diff!=2))
				{
					mysnake.direction=move;
					var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() 
					{
					  if (this.readyState == 4 && this.status == 200) 
					  {
						
					  }
					};
					mysnake.uid=id;
					xhttp.open("GET", "./getid?uid="+id+"&move="+e.keyCode+"&gid="+gameboard_id+"&t=3"+"&object="+JSON.stringify(mysnake), true);
					xhttp.setRequestHeader("Content-type", "application/json");
					xhttp.send();
				}
			}
			
		function food_acquired()
		{
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() 
			{
			  if (this.readyState == 4 && this.status == 200) 
			  {
				//mysnake.direction=move;
			  }
			};
			xhttp.open("GET", "./getid?uid="+id+"&gid="+gameboard_id+"&t=4", true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send();
		}
		
		function reportmysnakestatus(operation,position,gid,uno)
		{
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() 
			{
			  if (this.readyState == 4 && this.status == 200) 
			  {
				//mysnake.direction=move;
			  }
			};
			xhttp.open("GET", "./getid?uid="+id+"&gid="+gameboard_id+"&operation="+operation+"&position="+position+"&gid="+gid+"&uno="+uno+"&t=5", true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send();
		}
		
		function addBodyPart(n)
		{
			if(n==1)
			{
				//alert("got food snake1");
				let prev=snake1.body[snake1.body.length-1];
				let bodypart=new Object();
				switch(snake1.direction)
				{
					case 37:
						bodypart.x=prev.x;
						bodypart.y=(prev.y+1)%col_count;
						//alert("snake1 37");
						break;
					case 38:
						bodypart.y=prev.y;
						bodypart.x=(prev.x+1)%row_count;
						//alert("snake1 38");
						break;
					case 39:
					bodypart.x=prev.x;
						if(prev.y<=0)
						{
							bodypart.y=col_count-1;
						}
						else
						{
							bodypart.y=prev.y-1;
						}
						//alert("snake1 39");
						break;
					case 40:
						bodypart.y=prev.y;
						if(prev.x<=0)
						{
							bodypart.x=row_count-1;
						}
						else
						{
							bodypart.x=prev.x-1;
						}
						//alert("snake1 40");
						break;	
				}
				snake1.body.push(bodypart);
				food_acquired();
			}
			else if(n==2)
			{
				//alert("got food snake2");
				let prev=snake2.body[snake2.body.length-1];
				let bodypart=new Object();
				switch(snake2.direction)
				{
					case 37:
						bodypart.x=prev.x;
						bodypart.y=(prev.y+1)%col_count;
						//alert("snake2 37");
						break;
					case 38:
						bodypart.y=prev.y;
						bodypart.x=(prev.x+1)%row_count;
						//alert("snake2 38");
						break;
					case 39:
					bodypart.x=prev.x;
						if(prev.y<=0)
						{
							bodypart.y=col_count-1;
						}
						else
						{
							bodypart.y=prev.y-1;
						}
						//alert("snake2 39");
						break;
					case 40:
						bodypart.y=prev.y;
						if(prev.x<=0)
						{
							bodypart.x=row_count-1;
						}
						else
						{
							bodypart.x=prev.x-1;
						}
						//alert("snake2 40");
						break;	
				}
				snake2.body.push(bodypart);
				food_acquired();
				
			}
		}
		
		function pausegame()
		{
			console.log("cleared interval!!!");
			//clearInterval(interval);
		}
		
		function clearsnake(snake)
		{
			let row=document.getElementsByName("row");
			for(i=0;i<snake.body.length;i++)
			{
				row[snake.body[i].x].childNodes[snake.body[i].y].style.backgroundColor="transparent";
			}
		}
	</script>
</body>
</html>