var sock = new SockJS('http://localhost:8080/ws/game');

sock.onopen = function () {
    console.log('Connected!')
    send('omok-joinRoom', {
        roomId: 1,
        auth: 'hello auth'
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