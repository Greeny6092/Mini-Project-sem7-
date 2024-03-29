// Create a 4x4 grid
// Represent the grid as a 2-dimensional array
var grid;
var visitedlistx=new Array();
var visitedlisty=new Array();
function init2dgrid()
{
	//var gridSize = 4;
	grid = [];
	//alert("max dft "+row_count+" max dfl "+col_count);
	for (var i=0; i<row_count; i++) {
	  grid[i] = [];
	  for (var j=0; j<col_count; j++) {
		  if(document.getElementsByName("row")[i].childNodes[j].style.backgroundColor==""||document.getElementsByName("row")[i].childNodes[j].style.backgroundColor=="transparent")
			grid[i][j] = "Empty";
		  else if(document.getElementsByName("row")[i].childNodes[j].style.backgroundColor==snake2.color)
			grid[i][j]= "Visited";
		console.log(grid[i][j]+"\t");
	  }
	}
	//alert("dft 23 dfl 44 "+grid[23][44].style.backgroundColor);
	// Think of the first index as "distance from the top row"
	// Think of the second index as "distance from the left-most column"

	// This is how we would represent the grid with obstacles above
	grid[0][0] = "Start";
	//alert("foodX = "+foodX+" foodY = "+foodY);
	//if(foodX!=-1&&foodY!=-1)
		//grid[foodX][foodY] = "Goal";
}

function setFoodLocation(foodX,foodY,snake1,snake2)
{
	//alert("foodX = "+foodX+" foodY = "+foodY);
	let i;
	if(foodX!=-1&&foodY!=-1)
		grid[foodX][foodY] = "Goal";
	for(i=0;i<snake1.body.length;i++)
	{
		grid[snake1.body[i].x][snake1.body[i].y]="Visited";
	}

	for(i=0;i<snake2.body.length;i++)
	{
		grid[snake2.body[i].x][snake2.body[i].y]="Visited";
	}
	console.log("set food location "+foodX+","+foodY);
}

function resetCell(x,y)
{
	grid[x][y]= "Empty";
}

function resetVisited()
{
	let i;
	for(i=0;i<visitedlistx.length;i++)
	{
		let x=visitedlistx.shift();
		let y=visitedlisty.shift();
		grid[x][y]="Empty";
	}
}
//grid[1][1] = "Obstacle";
//grid[1][2] = "Obstacle";
//grid[1][3] = "Obstacle";
//grid[2][1] = "Obstacle";



// Start location will be in the following format:
// [distanceFromTop, distanceFromLeft]
var findShortestPath = function(startCoordinates) {
	//alert("start coordinates "+startCoordinates);
  var distanceFromTop = startCoordinates[0];
  var distanceFromLeft = startCoordinates[1];
  console.log("my head location "+distanceFromTop+","+distanceFromLeft);
  // Each "location" will store its coordinates
  // and the shortest path required to arrive there
  var location = {
    distanceFromTop: distanceFromTop,
    distanceFromLeft: distanceFromLeft,
    path: [],
    status: 'Start'
  };

  // Initialize the queue with the start location already inside
  var queue = [location];

  // Loop through the grid searching for the goal
  while (queue.length > 0) {
    // Take the first location off the queue
    var currentLocation = queue.shift();

    // Explore North
    var newLocation = exploreInDirection(currentLocation, 38);
    if (newLocation.status == 'Goal') {
      return newLocation.path;
    } else if (newLocation.status == 'Valid') {
      queue.push(newLocation);
    }

    // Explore East
    var newLocation = exploreInDirection(currentLocation, 39);
    if (newLocation.status == 'Goal') {
      return newLocation.path;
    } else if (newLocation.status == 'Valid') {
      queue.push(newLocation);
    }

    // Explore South
    var newLocation = exploreInDirection(currentLocation, 40);
    if (newLocation.status === 'Goal') {
      return newLocation.path;
    } else if (newLocation.status == 'Valid') {
      queue.push(newLocation);
    }

    // Explore West
    var newLocation = exploreInDirection(currentLocation, 37);
    if (newLocation.status == "Goal") {
      return newLocation.path;
    } else if (newLocation.status == 'Valid') {
      queue.push(newLocation);
    }
  }

  // No valid path found
  return false;

};

// This function will check a location's status
// (a location is "valid" if it is on the grid, is not an "obstacle",
// and has not yet been visited by our algorithm)
// Returns "Valid", "Invalid", "Blocked", or "Goal"
var locationStatus = function(location) {
  var gridSize = grid.length;
  var dft = location.distanceFromTop;
  var dfl = location.distanceFromLeft;
  console.log("checking "+dft+","+dfl);
  //console.log("dft "+dft+" dlt "+dfl+" type "+grid[dft][dfl].nodeType);
  //console.log(grid[dft][dfl].style.backgroundColor);
  if (location.distanceFromLeft < 0 ||
      location.distanceFromLeft >= col_count ||
      location.distanceFromTop < 0 ||
      location.distanceFromTop >= row_count) {

    // location is not on the grid--return false
    return 'Invalid';
  } else if (grid[dft][dfl] == 'Goal') {
    return 'Goal';
  } else if (grid[dft][dfl] != 'Empty') {
    // location is either an obstacle or has been visited
    return 'Blocked';
  } else {
    return 'Valid';
  }
};


// Explores the grid from the given location in the given
// direction
var exploreInDirection = function(currentLocation, direction) {
  var newPath = currentLocation.path.slice();
  newPath.push(direction);

  var dft = currentLocation.distanceFromTop;
  var dfl = currentLocation.distanceFromLeft;

  if (direction == 38) {
    dft -= 1;
  } else if (direction == 39) {
    dfl += 1;
  } else if (direction == 40) {
    dft += 1;
  } else if (direction == 37) {
    dfl -= 1;
  }

  var newLocation = {
    distanceFromTop: dft,
    distanceFromLeft: dfl,
    path: newPath,
    status: 'Unknown'
  };
  newLocation.status = locationStatus(newLocation);
  console.log(newLocation.status);
  // If this new location is valid, mark it as 'Visited'
  if (newLocation.status == 'Valid') {
    grid[newLocation.distanceFromTop][newLocation.distanceFromLeft] = 'Visited';
	visitedlistx.push(newLocation.distanceFromTop);
	visitedlisty.push(newLocation.distanceFromLeft);
  }
  else if(newLocation.status == "Blocked")
  {
	  //grid[newLocation.distanceFromTop][newLocation.distanceFromLeft] = 'Empty';
  }

  return newLocation;
};


// OK. We have the functions we need--let's run them to get our shortest path!

// Create a 4x4 grid
// Represent the grid as a 2-dimensional array
/*var gridSize = 4;
var grid = [];
for (var i=0; i<gridSize; i++) {
  grid[i] = [];
  for (var j=0; j<gridSize; j++) {
    grid[i][j] = 'Empty';
  }
}*/

// Think of the first index as "distance from the top row"
// Think of the second index as "distance from the left-most column"

// This is how we would represent the grid with obstacles above
/*grid[0][0] = "Start";
grid[2][2] = "Goal";

grid[1][1] = "Obstacle";
grid[1][2] = "Obstacle";
grid[1][3] = "Obstacle";
grid[2][1] = "Obstacle";*/

//console.log(findShortestPath([snake2.body[0].x,snake2.body[0].y], grid));
