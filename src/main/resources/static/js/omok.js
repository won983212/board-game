var sock = new SockJS('http://localhost:8080/ws/game');

sock.onopen = function () {
    console.log('Connected!')
    send('joinRoom', {
        roomId: 1,
        auth: getCookie('auth')
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

function getCookie(cookie_name) {
    let x, y;
    const val = document.cookie.split(';');
    for (let i = 0; i < val.length; i++) {
        x = val[i].substr(0, val[i].indexOf('='));
        y = val[i].substr(val[i].indexOf('=') + 1);
        x = x.replace(/^\s+|\s+$/g, '');
        if (x === cookie_name) {
            return decodeURI(y);
        }
    }
}