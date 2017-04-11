//回车事件
function send(e) {
    var code;
    if (!e) {
        var e = window.event;
    }
    if (e.keyCode) {
        code = e.keyCode;
    }
    else if (e.which) {
        code = e.which;
    }
    if (code == 13) {
        sendData();
    }
}

$("input").focus(function () {
    $("input").css("background-color", "#FFFFCC");
});

//验证用户名
function checkUser() {
    var name = $("input[name='name']").val();
    if (name == "") {
        $("#check").text("用户名不能为空!").css("color", "red");
        $("#name").focus();
        return false;
    }
    else {
        $("#check").text("");
        return true;
    }
}

//用户密码非空验证
function checkUserPsw() {
    var userpassword = $("input[name='password']").val();
    if (userpassword == "") {
        $("#check").text("密码不能为空").css("color", "red");
        $("#password").focus();
        return false;
    }
    else {
        $("#check").text("");
        return true;
    }
}

//登录表单提交
function sendData() {
    /*<![CDATA[*/
    if (checkUser() && checkUserPsw())
    /*]]>*/
    {
        var config =
        {
            name: $("input[name='name']").val(),
            password: $("input[name='password']").val()
        };

        $.ajax
        ({
            type: "POST",
            url: "/login",
            data: config,
            error: function () {
                $("#check").text("用户登录失败").css("color", "red");
            },
            success: function (data) {
                if (data == "0") {
                    $("#check").text("用户名或者密码不对!").css("color", "red");
                    return false;
                } else if (data == "1") {
                    window.location.href = "/";
                    return true;
                }
            }
        });
        return true;
    }
}