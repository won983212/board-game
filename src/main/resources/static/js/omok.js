// socket
const sock = new SockJS('http://localhost:8080/ws/game');

// canvas
const canvas = document.getElementById("gamePanel");
const ctx = canvas.getContext('2d');

// constants
const width = canvas.width;
const height = canvas.height;

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

function initializeCanvas() {
    ctx.fillStyle = '#CC8954'
    ctx.fillRect(0, 0, width, height)
}

function send(type, data) {
    sock.send(JSON.stringify({
        type: type,
        data: JSON.stringify(data)
    }))
}

initializeCanvas()