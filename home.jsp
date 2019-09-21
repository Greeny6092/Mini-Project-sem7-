<%@page import="java.util.*"%>
<html>
<head>
	<title>Snake Game</title>
	<style>
		button,input[type="button"]
		{
			background-color:green;
			border:transparent;
			width:28vw;
			font-size:32pt;
			font-weight:bold;
			border-radius:3vw;
			transition: background-color 1s,border 1s;
		}
		button:hover,input[type="button"]:hover
		{
			background-color:rgba(200,0,0,0.5);
			border :3px dashed green;
			
		}
		b
		{
			font-size:60pt;
			transition:color 1s;
		}
		b:hover
		{
			color:rgba(180,180,180,1);
		}
		body
		{
			background-image:url("home_bk.jpg");
			background-position:center;
		}
	</style>
</head>
<body onload="checkforsession()">
	<div style="padding-top:15vw"><center><button onclick="getid()" id="getid"><b>GET ID</b></button></center></div><br><div><center><a href="game.jsp"><input type="button" id="start" style="width:40vw;height:13vh;" value="START GAME" disabled><b></b></button></a></center></div>
	<script>
		var id,name;
		function getid() 
			{
				name=prompt("Enter your name:");
			  var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() 
				{
				  if (this.readyState == 4 && this.status == 200) {
					id = this.responseText;
					//alert("Your id is"+id);
					document.getElementById("getid").disabled=true;
					sessionStorage.setItem('id',id);
					sessionStorage.setItem('name',name);
					document.getElementById("start").disabled=false;
					}
				  
				};
				xhttp.open("GET", "./getid?name="+name+"&t=0", false);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhttp.send();
			}
			
		function checkforsession()
		{
			id=sessionStorage.getItem('id');
			//alert("check for id ,id is "+id);
			if(id!=null)
			{
				document.getElementById("getid").disabled=true;
				document.getElementById("start").disabled=false;
			}
		}
	</script>
</body>
</html>
