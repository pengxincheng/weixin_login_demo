
function QrcodeLogin() {

   $("#normalLogin").hide();
   $("#qrCodeLogin").show();
}



$("#login-button").click(function () {
    $.post("/user/login",$("#loginForm").serialize(),function (data) {
        console.log(data);
        if("SUCCESS" === data.code){
            QrcodeLogin();
            webSocketConnect(data.data);
        }
    })
})


function webSocketConnect (sceneId) {
    var websocket;
    if ('WebSocket' in window) {
        console.log('支持WebSocket！');
        var protocol = location.protocol == "http:" ? "ws://" : "wss://";
        websocket = new WebSocket(protocol + location.host + "/websocket");
    } else {
        console.log('不支持WebSocket！');
    }
    websocket.onopen = function () {
        websocket.send(sceneId);
    };
    websocket.onmessage = function (data) {
        //成功
        if (data.data == "true") {
            this.close();//关闭连接
            alert("验证通过")
        } else {
            this.close();
            webSocketConnect();
            alert("请使用已绑定运营后台的微信扫码");
        }
    };
}

$("#test").click(function () {
    $.post("/wx/getQrCode",null,function (data) {
        console.log(data);
    });
})
