var gameStance = {
    cells: []
}

var hostName = "http://10.17.163.89:8080/GameOfLife/control";

var isRunning = false;
var intervalVariable = null;
var gameId = 0;

function initCanvas() {
    var gameField = document.getElementById("gameField"),
            ctx = gameField.getContext('2d');
    ctx.fillStyle = "#eee";
    ctx.fillRect(0, 0, gameField.width, gameField.height);
    gameField.onclick = canvasMouseClickHandler;
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
    if (isAlive) {
        ctx.fillStyle = "red";
    } else {
        ctx.fillStyle = "#eee";
    }
    ctx.fillRect(cellSpace + (cell.x * cellSpace) + (cell.x * cellSize),
            cellSpace + (cellSpace * cell.y) + (cellSize * cell.y), cellSize, cellSize);
}

// HANDLERS

function btnNextOnClickHandler() {
    /*$.get(hostName + "/nextstep/" + gameId, JSON.stringify(gameStance), function(data) {
     gameStance = JSON.parse(data);
     */
    ctx.fillStyle = "#eee";
    ctx.fillRect(0, 0, gameField.width, gameField.height);
    $.ajax({
        url: hostName + "/nextstep/" + gameId,
        type: 'GET',
        contentType: "application/json",
        data: JSON.stringify(gameStance),
        dataType: "json",
        success: function(result) {
            gameStance = result;
        }
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
        intervalVariable = window.setInterval(function() {
            ctx.fillStyle = "#eee";
            ctx.fillRect(0, 0, gameField.width, gameField.height);
            $.ajax({
                url: hostName + "/nextstep/" + gameId,
                type: 'GET',
                contentType: "application/json",
                data: JSON.stringify(gameStance),
                dataType: "json",
                success: function(result) {
                    gameStance = result;
                }
            });

            drawField(gameStance.cells);
        }, 500);
    }
}

function btnExportOnClickHandler() {
    $.ajax({
        url: hostName + "/loadmodel/" + gameId,
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify(gameStance),
        dataType: "json",
        success: function(result) {
            alert(result);
        }
    });
}

function btnNewOnClickHandler() {
    /* $.post(hostName + "/newgame", function(data) {
     gameId = data;
     alert(data);
     });*/
    $.ajax({
        url: hostName + "/newgame",
        type: 'POST',
        crossDomain: true,
        xhrFields: {
            withCredentials: true
        },
        success: function(result) {
            gameId = result;
            alert(result);
        }
    });
    drawField(gameStance.cells);
}

function canvasMouseClickHandler(e) {
    var position = mousePosition(e);
    if (searchCell(gameStance, position) == -1) {
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

    if (!e)
        var e = window.event;
    if (e.pageX || e.pageY) {
        posx = e.pageX;
        posy = e.pageY;
    } else if (e.clientX || e.clientY) {
        posx = e.clientX + document.body.scrollLeft
                + document.documentElement.scrollLeft;
        posy = e.clientY + document.body.scrollTop
                + document.documentElement.scrollTop;
    }

    var domObject = e.target;
    while (domObject.nodeType != domObject.ELEMENT_NODE)
        domObject = domObject.parentNode;

    while (domObject.offsetParent) {
        left += domObject.offsetLeft;
        top += domObject.offsetTop;
        domObject = domObject.offsetParent;
    }

    domObject.pageTop = top;
    domObject.pageLeft = left;

    x = Math.ceil(((posx - domObject.pageLeft) / (cellSize + cellSpace)) - 1);
    y = Math.ceil(((posy - domObject.pageTop) / (cellSize + cellSpace)) - 1);

    return {"x": x, "y": y};
}

function searchCell(stance, searchedCell) {
    var found = -1;

    for (var i = 0; i < stance.cells.length; i++) {
        if (JSON.stringify(stance.cells[i]) === JSON.stringify(searchedCell)) {
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