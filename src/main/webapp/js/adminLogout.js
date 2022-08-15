window.addEventListener('load', function(){
    $(".logout").on("click", function () {
        var logout = "/AdminLogoutServlet";
        $.ajax({
            url: logout,
            type: "POST",
            data: JSON.stringify({
              action: "logout",
            }),
            dataType: "json",
            success: function (data) {
                console.log(data);
                if ("登出成功" === data) {
                    window.location.href = "adminLogin.html";
                }
            },
            complete: function () {
                window.location.href = "adminLogin.html";
            },
        });
    });
})