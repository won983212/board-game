var sock = new SockJS('http://localhost:8080/ws/game');

sock.onopen = function () {
    console.log('Connected!')
    sock.send('hello')
}

sock.onmessage = function (e) {
    console.log('Message: ' + e.data)
    sock.close()
}

sock.onclose = function () {
    console.log('close')
}