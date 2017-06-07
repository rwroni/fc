$(document).ready(function () {
    $("#login").click(function () {
        console.log("click");
        var username = $("#username").val();
        var password = $("#password").val();

        $.post("login",
                {
                    username: username,
                    password: password
                },
                function (data, status) {
                    var result = $.trim(data);
                    //belom bener if nya
                    if (result[0] === "{") {
                        window.location.href = "index.html";
                    } else {
                        alert(data);
                    }
                    ;
                });
    });

});
