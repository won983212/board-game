// socket
const sock = new SockJS('http://localhost:8080/ws/game');

// modal
const gameFinishModal = new bootstrap.Modal(document.getElementById('gameFinishModal'))

// canvas
const canvas = document.getElementById("gamePanel");
const ctx = canvas.getContext('2d');

// constants
const width = canvas.width;
const height = canvas.height;

// draw constants
const lineOffset = 10;
const lineCount = 19;
const sqWidth = (width - lineOffset * 2) / (lineCount - 1);
const sqHeight = (height - lineOffset * 2) / (lineCount - 1);
const stoneRadius = Math.min(sqWidth, sqHeight) / 2 - 1

// context
let stones = [
    {
        color: 'black',
        x: 1,
        y: 3
    },
    {
        color: 'black',
        x: 2,
        y: 3
    },
    {
        color: 'white',
        x: 5,
        y: 5
    },
    {
        color: 'white',
        x: 6,
        y: 6
    }
];


sock.onopen = function () {
    console.log('Connected!')
    send('joinRoom', {
        roomId: 1,
        auth: $.cookie('auth')
    });
}

sock.onmessage = function (e) {
    console.log('Message: ' + e.data)
    sock.close()
}

sock.onclose = function () {
    console.log('close')
}

function send(type, data) {
    sock.send(JSON.stringify({
        type: type,
        data: JSON.stringify(data)
    }))
}

function renderCanvas() {
    ctx.fillStyle = '#CC8954'
    ctx.fillRect(0, 0, width, height)

    renderLines()
    renderStones()
}

function renderLines() {
    ctx.strokeStyle = '#000000'

    for (let x = 0; x < lineCount; x++) {
        ctx.beginPath()
        ctx.moveTo(lineOffset + x * sqWidth, lineOffset)
        ctx.lineTo(lineOffset + x * sqWidth, height - lineOffset)
        ctx.stroke()
    }

    for (let y = 0; y < lineCount; y++) {
        ctx.beginPath()
        ctx.moveTo(lineOffset, lineOffset + y * sqHeight)
        ctx.lineTo(width - lineOffset, lineOffset + y * sqHeight)
        ctx.stroke()
    }
}

function renderStones() {
    for (const stone of stones) {
        ctx.beginPath()
        ctx.fillStyle = stone.color;
        ctx.arc(lineOffset + stone.x * sqWidth,
            lineOffset + stone.y * sqHeight, stoneRadius,
            0, 2 * Math.PI);
        ctx.fill();
    }
}

renderCanvas()