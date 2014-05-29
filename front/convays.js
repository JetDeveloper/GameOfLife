var gameStance = {
	cells: [{x:1, y:1}]
}

var isRunning = false;
var intervalVariable = null;

function initCanvas() {
	var gameField = document.getElementById("gameField"),
        ctx     = gameField.getContext('2d');
    ctx.fillStyle = "#eee";
    ctx.fillRect(0, 0, gameField.width, gameField.height);  
    gameField.onclick = canvasMouseClick;
    //setInterval(function(){alert("Hello")},3000);
}

function drawField(aliveCells) {
	$.each(aliveCells, function(index, value) {
		drawCell(value, true);
	});
}

function drawCell(cell, isAlive) {
	var gameField = document.getElementById("gameField");
		ctx = gameField.getContext("2d"),
		cellSpace = 1,
		cellSize = 10;
	if(isAlive) {
		ctx.fillStyle = "red";
	} else {
		ctx.fillStyle = "#eee";
	}
	ctx.fillRect(cellSpace + (cell.x * cellSpace) + (cell.x * cellSize),  
			cellSpace + (cellSpace * cell.y) + (cellSize * cell.y), cellSize, cellSize);	
}

function btnNextOnClickHandler() {
	$.post("", JSON.stringify(gameStance), function(data){
		gameStance = JSON.parse(data);
	});
	drawField(gameStance.cells);
}

function btnClearOnClickHandler() {
	clearField(gameStance.cells);
	gameStance.cells = [];
}

function btnRunOnClickHandler() {
	var btn = document.getElementById("buttonRun");
	if (isRunning) {
		btn.value = "Run";
		window.clearInterval(intervalVariable);
		intervalVariable = null;
		isRunning = false;
	} else {
		btn.value = "Stop";
		isRunning = true;
		intervalVariable = window.setInterval(function(){alert("Hello")},3000);
	}
}

function canvasMouseClick(e) {
	var position = mousePosition(e);
	if(searchCell(gameStance, position) == -1) {
		gameStance.cells.push(position);
		drawCell(position, true);
	} else {
		removeCell(gameStance, position);
		drawCell(position, false);
	}
	//alert(JSON.stringify(gameStance));
}

function mousePosition(e) {
    var x, y, domObject, posx = 0, posy = 0, top = 0, left = 0, cellSize = 10, cellSpace = 1;

	if (!e) var e = window.event;
	if (e.pageX || e.pageY) 	{
		posx = e.pageX;
		posy = e.pageY;
	} else if (e.clientX || e.clientY) 	{
		posx = e.clientX + document.body.scrollLeft
			+ document.documentElement.scrollLeft;
		posy = e.clientY + document.body.scrollTop
			+ document.documentElement.scrollTop;
	}
    
    // domObject = event.target || event.srcElement;
    var domObject = e.target;
	while(domObject.nodeType != domObject.ELEMENT_NODE)
	domObject = domObject.parentNode;

    while ( domObject.offsetParent ) {
        left += domObject.offsetLeft;
        top += domObject.offsetTop;
        domObject = domObject.offsetParent;
    }



    domObject.pageTop = top;
    domObject.pageLeft = left;

    x = Math.ceil(((posx - domObject.pageLeft)/(cellSize + cellSpace)) - 1);
    y = Math.ceil(((posy - domObject.pageTop)/(cellSize + cellSpace)) - 1);

    return {"x": x, "y": y};
}

function searchCell(stance, searchedCell) {
	var found = -1;

	for (var i = 0; i < stance.cells.length; i++) {
		if(JSON.stringify(stance.cells[i]) === JSON.stringify(searchedCell)) {
			found = i;
			break;
		}
	}

	return found;
}

function removeCell(stance, cell) {
	var index = searchCell(stance, cell);
	if (index > -1) {
		stance.cells.splice(index, 1);
	}
}

function clearField(aliveCells) {
	$.each(aliveCells, function(index, value) {
		drawCell(value, false);
	});
}