// 聊天室

//取得userId ajax
let selfId;
$.ajax({
  url: "IdServlet",
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
let host = window.location.host;
let path = window.location.pathname;
let webCtx = path.substring(0, path.indexOf("/", 1));
let endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

let webSocket = new WebSocket(endPointURL);

webSocket.onopen = function (event) {
  console.log("Connect Success!");
};

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
  
  // 從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
  let messages = JSON.parse(jsonObj.message);
  for (let i = 0; i < messages.length; i++) {
    let historyData = JSON.parse(messages[i]);
    let showMsg = historyData.message;
    const isMe = historyData.senderId === selfId;
    appendNewMsg(isMe, showMsg);
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

function sendMessage(event) {
  const message = event.target.value;
  if (!message && message.trim() === "") {
    alert("請輸入訊息");
    return;
  }
  appendNewMsg(true, message);
  let jsonObj = {
    type: "chat",
    senderId: selfId,
    receiverId: friendId,
    message: message,
  };
  webSocket.send(JSON.stringify(jsonObj));
  let message_input = document.querySelector("#msgInput"+ friendId);
  message_input.value = "";
  message_input.focus();
}



function refreshChat() {
  // 移除舊的訊息
  const ul = document.getElementById(`messagesArea${friendId}`)
  if(ul) {
    ul.innerHTML = '';
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

// 聊天室js(暫不用)
//  $(function () {
//     var Message;
//     Message = function (arg) {
//       (this.text = arg.text), (this.message_side = arg.message_side);
//       this.draw = (function (_this) {
//         return function () {
//           var $message;
//           $message = $($(".message_template").clone().html());
//           $message
//             .addClass(_this.message_side)
//             .find(".text")
//             .html(_this.text);
//           $(".messages").append($message);
//           return setTimeout(function () {
//             return $message.addClass("appeared");
//           }, 0);
//         };
//       })(this);
//       return this;
//     };
//     $(function () {
//       var getMessageText, message_side, sendMessage;
//       message_side = "right";
//       getMessageText = function () {
//         var $message_input;
//         $message_input = $(".message_input");
//         return $message_input.val();
//       };
//       sendMessage = function (text) {
//         var $messages, message;
//         if (text.trim() === "") {
//           return;
//         }
//         $(".message_input").val("");
//         $messages = $(".messages");
//         message_side = message_side === "left" ? "right" : "left";
//         message = new Message({
//           text: text,
//           message_side: message_side,
//         });
//         message.draw();
//         return $messages.animate(
//           { scrollTop: $messages.prop("scrollHeight") },
//           300
//         );
//       };
//       $(document).on("click", ".send_message", function () {
//         return sendMessage(getMessageText());
//       });
//       $(document).on("keyup", ".message_input", function (e) {
//         if (e.which === 13) {
//           return sendMessage(getMessageText());
//         }
//       });
//     });
//   }.call(this));

//配對local storage

//[to be asked]
$("#acceptPair").click(function () {
  localStorage.setItem("acceptPage", 10);
  if (localStorage.getItem("acceptPage") == 10) {
    $("#todayPairModal").hide();
  } else {
    $("#todayPairModal").show();
  }
});

//配對者資訊ajax
$.ajax({
  url: "PairInfoServlet",
  type: "GET",
  dataType: "json",
  success: function (data) {
    console.log(data[0].profilePic);
    // let blob = new Blob([data[0].profilePic],{ type: 'application/json' })
    // url = window.URL.createObjectURL(blob);
    // console.log(url);
    var base64String = btoa(
      String.fromCharCode.apply(null, new Uint8Array(data[0].profilePic))
    );
    $("#headshot").attr("src", `data:image/png;base64,${base64String}`);
    $(".name").append(data[0].userName);
    $("#selfIntro").append(data[0].userProfile);
    $("#name1").append(data[0].userName);
  },
});

//更新答案ajax
$("#acceptPairBtn, #refusePairBtn").on("click", function (e) {
  fetch("UpdateAnswerServlet", {
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
  url: "PairListServlet",
  type: "GET",
  dataType: "json",
  success: function (data) {
    if (data.length == 0) {
      let noFriends = `<div>您目前無任何好友，快看看今日配對 ! </div>`;
      $(".list-group").append(noFriends);
    }
    console.log(data);
    for (var i = 0; i < data.length; i++) {
      var base64String = btoa(
        String.fromCharCode.apply(null, new Uint8Array(data[i].profilePic))
      );
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
      <div class="modal-content">
        <div id="modalHeader" class="modal-header">
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
              <input id = "msgInput${data[i].userId}" class="message_input" type="text" placeholder="請輸入文字. . . " onkeydown="if (event.keyCode == 13) sendMessage(event)"/>
            </div>
            <div class="send_message">
              <div class="icon"></div>
              <div class="text" onclick="sendMessage();">送出</div>
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
      $(".list-group").append(html);
      $("#as").append(html2);
      document.querySelectorAll(".avatarList")[i].style.backgroundImage =
        "url('data:image/png;base64," + base64String + "')";
    }
  },
});
