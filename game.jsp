<html>
<head>
<title>game</title>
<style>
	td
	{
		width:3vw;
		height:1vh;

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
<body onload="createboard()">
<div style="position:fixed;float:right;display:none;" name="control_board">
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
	</div>
	<div id="liveuserbox">
	</div>
	<div>
		<center>
			<table border="0" name="gameboard" style="border:6px solid black">
				<tr name="row"><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
			</table>
		</center>
	</div>
	<div id="requestbox">
	<table name="requesttable">
		<tr name="requestrow">
		</tr>
	</table>
	</div>
	<input type="text" onclick="setMove(this.value)" name="input" hidden>
	<script>
		
		var id;
		var gamestartflag=0;
		var gameboard_id;
		function createboard()
		{
			id=sessionStorage.getItem('id');
			//alert("id is "+id);
			var gameboard=document.getElementsByName("gameboard")[0];
			var row=document.getElementsByName("row")[0];
			//alert(row.childNodes.length);
			for(i=0;i<80;i++)
			{
				let newrow=row.cloneNode(true);
				gameboard.appendChild(newrow);
			}
			get_live_users();
		}
		function get_live_users()
		{
			var liveusers=document.getElementById("liveuserbox");
			var node;
			var source = new EventSource('./LiveUserLister'); 
			 source.onmessage=function(event)
            {
				//alert(event.data);
                //liveusers.innerHTML="<span style='float:right'><button onclick='this.parentNode.display=none'>CLOSE</button></span>";
				liveusers.innerHTML="";
				let ids=event.data.split("$")[0].split(",");
				let names=event.data.split("$")[1].split(",");
				for(i=1;i<ids.length;i++)
				{
					//alert("loop "+ids[i]+" s");
					//ids[i]=ids[i].replace(/(\r\n|\n|\r)/gm,"");
					//ids[i]=ids[i].trim();
					let temp=ids[i];
					////alert(ids[i].replace(/(\r\n|\n|\r)/gm,"")+" "+ids[i].trim());
					////alert(id+" "+ids[i].replace(/(\r\n|\n|\r)/gm," ").trim());
					////alert(id+temp.replace(/(\r\n|\n|\r)/gm," ").trim()+" "+ids[i].includes(id.toString()));
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
			
			source.addEventListener("gamestartflag",function(event)
            {
				console.log(event.data);
					gamestartflag=event.data.split(",")[0];
					if(gamestartflag==1)
					{
						document.getElementById("requestbox").style.display="none";
						gameboard_id=event.data.split(",")[1];
						document.getElementsByName("control_board")[0].style.display="inline-block";
						listentogameboard();
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
					console.log("accepted!!!");
				  }
				};
				xhttp.open("GET", "./getid?u1="+id+"&u2="+u2+"&t=2&accept="+accept, false);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhttp.send();
		}
		
		function listentogameboard()
		{
			var source = new EventSource('./GameBoardTeller?gid='+gameboard_id);
			var blue=null,red=null;
			source.addEventListener("boardstatus",function(event)
            {
				var rows=document.getElementsByName("row");
				
				//console.log("got game board status !!! "+event.data+" length "+event.data.length);
				let data=event.data.split("$");
				/*for(i=0;i<data.length-1;i++)
				{
					if(data[i]==0)
					{
						console.log("i is "+i+" i/12 is "+parseInt(1/12)+" i%12 is "+(i%12));
						rows[parseInt(i/12)].childNodes[i%12].setAttribute("style","background-color:red");
						//console.log(rows[0].childNodes[0].nodeType);
					}
					else if(data[i]==1)
					{
						
						rows[parseInt(i/12)].childNodes[i%12].setAttribute("style","background-color:blue");
					}
					else if(data[i]==2)
					{
						rows[parseInt(i/12)].childNodes[i%12].setAttribute("style","background-color:white");
						
					}
				}*/
				//clearBoard();
				if(data.length>2)
				{
					let xy=data[2].split(",");
					rows[parseInt(xy[0])].childNodes[parseInt(xy[1])].setAttribute("style","background-color:yellow");
				}
				
				if(blue!=null)
				{
					console.log("clearing");
					for(i=0;i<blue.length-1;i+=2)
					{
						rows[parseInt(blue[i])].childNodes[parseInt(blue[i+1])].setAttribute("style","background-color:white");
					}
				}
				blue=data[0].split(",");
				for(i=0;i<blue.length-1;i+=2)
				{
					rows[parseInt(blue[i])].childNodes[parseInt(blue[i+1])].setAttribute("style","background-color:blue");
				}
				

				if(red!=null)
				{
					console.log("clearing");
					for(i=0;i<red.length-1;i+=2)
					{
						rows[parseInt(red[i])].childNodes[parseInt(red[i+1])].setAttribute("style","background-color:white");
					}
				}
				red=data[1].split(",");
				for(i=0;i<red.length-1;i+=2)
				{
					rows[parseInt(red[i])].childNodes[parseInt(red[i+1])].setAttribute("style","background-color:red");
				}

				document.getElementsByName("input")[0].value="";
				document.getElementsByName("input")[0].focus();
            })			
		}
		
		function clearBoard()
		{
			let table=document.getElementsByName("gameboard")[0];
			//let innertable=document.getElementsByName("gameboard")[0].childNodes[0].cloneNode(true);
			table.innerHTML="<tr name='row'><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
			//createboard();
			//table.appendChild(innertable);
			//var gameboard=document.getElementsByName("gameboard")[0];
			//var row=document.getElementsByName("row")[0];
			for(i=0;i<80;i++)
			{
				//let newrow=row.cloneNode(false);
				//gameboard.appendChild(newrow);
				table.innerHTML+="<tr name='row'><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
			}
		}
		
		function setMove(m)
		{
			//alert(parseInt(m));
			//alert("Entered");
			let move=parseInt(m);
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() 
				{
				  if (this.readyState == 4 && this.status == 200) 
				  {
					//id = this.responseText;
					//console.log("move placed to moveQueue");
				  }
				};
				xhttp.open("GET", "./getid?uid="+id+"&move="+move+"&gid="+gameboard_id+"&t=3", false);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhttp.send();
		}
		
		document.onkeydown=function(e)
			{
				//alert(e.keyCode);
				let move=e.keyCode
				if((move==37||move==38||move==39||move==40)&&(gamestartflag==1))
				{
					var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() 
					{
					  if (this.readyState == 4 && this.status == 200) 
					  {
						//id = this.responseText;
						//console.log("move placed to moveQueue");
					  }
					};
					xhttp.open("GET", "./getid?uid="+id+"&move="+e.keyCode+"&gid="+gameboard_id+"&t=3", false);
					xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					xhttp.send();
				}
			}
	</script>
</body>
</html>
