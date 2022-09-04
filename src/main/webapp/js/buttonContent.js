// 聊天室WebSocket
//取得userId ajax
let selfId;
$.ajax({
  url: "/monfood_maven/IdServlet",
  async: false,
  type: "GET",
  dataType: "json",
  success: function (data) {
    selfId = data.userId;
    console.log(selfId);
  },
});

console.log(selfId);

let friendId;
let MyPoint = `/ChatWebsocket/${selfId}`;
// let host = window.location.host;
// let path = window.location.pathname;
// let webCtx = path.substring(0, path.indexOf("/", 1));
// 本地端
let endPointURL = "ws://" + window.location.host + "/monfood_maven" + MyPoint;

// 上雲
// let endPointURL = "wss://35.201.129.109:8443/monfood_maven" + MyPoint;
// let endPointURL = "wss://" + window.location.host + "/monfood_maven" + MyPoint;

let webSocket = new WebSocket(endPointURL);

webSocket.onopen = function (event) {
  console.log("Connect Success!");
};

function sendMessage() {
  const message = document.querySelector("#msgInput" + friendId).value;
  if (!message && message.trim() === "") {
    alert("請輸入訊息");
    return;
  }
// 顯示自己傳送的訊息
    appendNewMsg(true,  message);


  let jsonObj = {
    type: "chat",
    senderId: selfId,
    receiverId: friendId,
    message: message,
  };
  webSocket.send(JSON.stringify(jsonObj));
  console.log(jsonObj);
  let message_input = document.querySelector("#msgInput" + friendId);
  message_input.value = "";
  message_input.focus();
}

function appendNewMsg(isMe, msg) {
  let messagesArea = document.querySelector("#messagesArea" + friendId);
  let li = document.createElement("li");
  li.className += isMe ? "me" : "friend";
  li.innerHTML = msg;
  messagesArea.appendChild(li);
  messagesArea.scrollTop = messagesArea.scrollHeight;
}

webSocket.onmessage = function (event) {
  let jsonObj = JSON.parse(event.data);
  console.log(jsonObj);
  console.log(jsonObj.type);

  // 從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
  if ("history" === jsonObj.type) {
    let messages = JSON.parse(jsonObj.message);
    for (let i = 0; i < messages.length; i++) {
      let historyData = JSON.parse(messages[i]);
      let showMsg = historyData.message;
      const isMe = historyData.senderId === selfId;
      appendNewMsg(isMe, showMsg);
    }
  } else if ("chat" === jsonObj.type) {
    console.log("aaa");
    let message = jsonObj.message;
    let isMe = jsonObj.senderId === selfId;
    let messagesArea = document.querySelector("#messagesArea" + friendId);
    let li = document.createElement("li");
    li.className += isMe ? "me2" : "friend";
    li.innerHTML = message;
    messagesArea.appendChild(li);
    messagesArea.scrollTop = messagesArea.scrollHeight;
    // 上線自己訊息會重複， 把重複移除
      $(".me2").hide();

  }
};

webSocket.onclose = function (event) {
  console.log("Disconnected!");
};

//取得好友ID
$(document).on("click", ".findChatId", function () {
  friendId = $(this).data("value");
  refreshChat();
});



function refreshChat() {
  // 移除舊的訊息
  const ul = document.getElementById(`messagesArea${friendId}`);
  if (ul) {
    ul.innerHTML = "";
  }
  // type是history時表示要拿redis的資料出來
  let jsonObj = {
    type: "history",
    senderId: selfId,
    receiverId: friendId,
    message: "",
  };
  webSocket.send(JSON.stringify(jsonObj));

}



//配對者資訊ajax

  console.log("pairF");
  $.ajax({
    url: "/monfood_maven/PairInfoServlet",
    type: "GET",
    dataType: "json",
    success: function (data) {
      if (data.length == 0){
        console.log("a");
        $("#acceptPairBtn").hide();
        $("#refusePairBtn").hide();
        let pairHtml = `<div>很抱歉，您今日無任何配對</div>`;
        $("#intro").append(pairHtml);
        $("#intro").css("background-color", "white");
  
      }
      // var base64String = btoa(
      //   String.fromCharCode.apply(null, new Uint8Array(data[0].profilePic))
      // );

      var base64String = btoa(new Uint8Array(data[0].profilePic).reduce(
        function (data, byte) {
            return data + String.fromCharCode(byte);
        },
        ''
    ));
      
      $("#headshot").attr("src", `data:image/png;base64,${base64String}`);
      $(".name").append(data[0].userName);
      $("#selfIntro").append(data[0].userProfile);
      $("#name1").append(data[0].userName);
    },
  });



//更新答案ajax
// 到其他人頁面改為動態

  $(document).on("click","#acceptPairBtn, #refusePairBtn", function (e) {
    console.log("aa");
    fetch("/monfood_maven/UpdateAnswerServlet", {
      method: "POST",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify({
        useraAnswer: e.target.value,
        userbAnswer: e.target.value,
      }),
    });
  });
  
  $("#acceptPairBtn, #refusePairBtn").on("click", function (e) {
    console.log("aa");
    fetch("/monfood_maven/UpdateAnswerServlet", {
      method: "POST",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify({
        useraAnswer: e.target.value,
        userbAnswer: e.target.value,
      }),
    });
  });




//訊息列表+聊天室ajax

  $.ajax({
    url: "/monfood_maven/PairListServlet",
    type: "GET",
    dataType: "json",
    success: function (data) {
      if (data.length == 0) {
        let noFriends = `<div>您目前無任何好友，快看看今日配對 ! </div>`;
        $(".list-groups").append(noFriends);
      }
      console.log(data);
      for (var i = 0; i < data.length; i++) {
        // var base64String = btoa(
        //   String.fromCharCode.apply(null, new Uint8Array(data[i].profilePic))
        // );
        var base64String = btoa(new Uint8Array(data[i].profilePic).reduce(
          function (data, byte) {
              return data + String.fromCharCode(byte);
          },
          ''
      ));
        var html = "";
        html += `              <a
        href="#"
        class="list-group-item list-group-item-action findChatId"
        aria-current="true"
        data-bs-toggle="modal"
        data-bs-target="#chatRoomModal${data[i].userId}"
        data-value = "${data[i].userId}"
  
      >
        <div class="d-flex w-100">
          <div class="avatarList"></div>
          <div id="chatList">
            <h5 class="mb-1">${data[i].userName}</h5>
            <p id="chatContent" class="mb-1">開始聊天吧!</p>
          </div>
          
        </div>
      </a>
      `;
  
        var html2 = "";
        html2 += `<div
      class="modal fade"
      id="chatRoomModal${data[i].userId}"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div id="modalContentPair" class="modal-content">
          <div id="modalHeaderPair" class="modal-header">
            <h5>
              <span class="logo">Mon</span><span class="logo2">Food</span>
            </h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <div class="top_menu">
              <div class="buttons">
                <button
                  id="back"
                  data-bs-toggle="modal"
                  data-bs-target="#chatListModal"
                >
                  <
                </button>
              </div>
              <div class="title">${data[i].userName}</div>
            </div>
            <ul id= "messagesArea${data[i].userId}" class="messages message-area"></ul>
  
            <div id="sendMsg" class="bottom_wrapper clearfix">
              <div class="message_input_wrapper">
                <input id = "msgInput${data[i].userId}" class="message_input" type="text" placeholder="請輸入文字. . . " onkeydown="if (event.keyCode == 13) sendMessage()"/>
              </div>
              <div class="send_message" onclick="sendMessage();">
                <div class="icon"></div>
                <div class="text">送出</div>
              </div>
            </div>
          </div>
  
          <div class="message_template">
            <li class="message">
              <div class="avatar"></div>
              <div class="text_wrapper">
                <div class="text"></div>
              </div>
            </li>
          </div>
        </div>
      </div>
    </div>`;
        $(".list-groups").append(html);
        $("#as").append(html2);
        document.querySelectorAll(".avatarList")[i].style.backgroundImage =
          "url('data:image/png;base64," + base64String + "')";
      }
    },
  });



//配對local storage (按完接受配對or拒絕配對後當天不能再按按鈕)

let twentyFourHoursSec = 24 * 60 * 60;
$("#acceptPairBtn, #refusePairBtn").on("click", function (){
  localStorage.setItem(
    "whetherShow",
    JSON.stringify({ date: new Date().getDate() })
  );
  init()

})
//到其他人頁面改為動態
$(document).on("click","#acceptPairBtn, #refusePairBtn", function (){
  localStorage.setItem(
    "whetherShow",
    JSON.stringify({ date: new Date().getDate() })
  );
  init();

})

const showBtn = (value) => {
  let acceptPairBtn = document.querySelector("#acceptPairBtn");
  let refusePairBtn = document.querySelector("#refusePairBtn");
  acceptPairBtn.disabled = value;
  refusePairBtn.disabled = value;
};

const init = () => {
  let show = JSON.parse(localStorage.getItem("whetherShow"));
  let { date } = !!show ? show : { date: null };

  if (!date | (parseInt(new Date().getDate()) > parseInt(date)  )) return showBtn(false);

  showBtn(true);
};

window.addEventListener("load", init);


