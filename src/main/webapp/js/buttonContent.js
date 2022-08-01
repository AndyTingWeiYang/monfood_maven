// 聊天室
     (function () {
        var Message;
        Message = function (arg) {
          (this.text = arg.text), (this.message_side = arg.message_side);
          this.draw = (function (_this) {
            return function () {
              var $message;
              $message = $($(".message_template").clone().html());
              $message
                .addClass(_this.message_side)
                .find(".text")
                .html(_this.text);
              $(".messages").append($message);
              return setTimeout(function () {
                return $message.addClass("appeared");
              }, 0);
            };
          })(this);
          return this;
        };
        $(function () {
          var getMessageText, message_side, sendMessage;
          message_side = "right";
          getMessageText = function () {
            var $message_input;
            $message_input = $(".message_input");
            return $message_input.val();
          };
          sendMessage = function (text) {
            var $messages, message;
            if (text.trim() === "") {
              return;
            }
            $(".message_input").val("");
            $messages = $(".messages");
            message_side = message_side === "left" ? "right" : "left";
            message = new Message({
              text: text,
              message_side: message_side,
            });
            message.draw();
            return $messages.animate(
              { scrollTop: $messages.prop("scrollHeight") },
              300
            );
          };
          $(".send_message").click(function (e) {
            return sendMessage(getMessageText());
          });
          $(".message_input").keyup(function (e) {
            if (e.which === 13) {
              return sendMessage(getMessageText());
            }
          });
        });
      }.call(this));

      
      
//配對local storage

      //to be asked
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
          var base64String = btoa(String.fromCharCode.apply(null, new Uint8Array(data[0].profilePic)));
          $("#headshot").attr('src', `data:image/png;base64,${base64String}`);
          $(".name").append(data[0].userName);
          $("#selfIntro").append(data[0].userProfile);
          $("#name1").append(data[0].userName);
        },
      });

//更新答案ajax
console.log(parseInt(refusePair));
$("#acceptPairBtn, #refusePairBtn").on("click",function(e){
  // $.ajax({
  //   url: "UpdateAnswerServlet",
  //   type: "POST",
  //   data: JSON.stringify({useraAnswer : parseInt(refusePair) || parseInt(acceptPair),
  //     userbAnswer : parseInt(refusePair) || parseInt(acceptPair) }),
  //   dataType: "json",
  //   success: function (data) {
  //     console.log(data); },
  // });
  fetch("UpdateAnswerServlet",{
    method:"POST",
    headers:{
      "content-Type":"application/json"
    },
    body:JSON.stringify({
      useraAnswer : e.target.value,
      userbAnswer : e.target.value
    })
  });
})
