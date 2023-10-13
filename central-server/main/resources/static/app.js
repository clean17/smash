/**
 * 웹소켓 연결 초기화
 * @type {StompJs.Client}
 */
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/gs-guide-websocket'
});

/**
 * 연결되면 렌더링 + 구독
 * @param frame
 */
stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/greetings', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

/**
 * 연결이 되면 버튼 + 테이블 렌더링
 * @param connected
 */
function setConnected(connected) {
    // prop -> 해당 속성을, 두번째 파라미터의 값으로 결정 ( 토글 )
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

/**
 * 최초 연결
 */
function connect() {
    stompClient.activate();
}

/**
 * 연결 끊기
 */
function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

/**
 * publish - 메세지 생산
 */
function sendName() {
    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

/**
 * 채팅 렌더링
 * @param message
 */
function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

// Event Handler
$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});