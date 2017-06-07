$(document).ready(function () { 
    $.get("index", function (data, status) {
        var result = $.trim(data);
        if (result === "true") {
            return;
        } else {
            window.location.href = "pages-login.html";
        }
        ;
    });

    $("#logout").click(function () {
        $.post("logout", function (data, status) {
            alert("Data: " + data.length + data + "\nStatus: " + status);
            var result = $.trim(data);
            if (result === "true") {
                alert(result);
                window.location.href = "pages-login.html";
            } else {
                alert("try again logout");
                return;
            }
            ;
        });
    });
    
    $.post("Dashboard",
    {
        code: 1
    },function (data, status) {
        var response = JSON.parse(data);
        $("#employee-total").html(response[0][0]);
    });
    
    $.post("Dashboard",
    {
        code: 2
    },function (data, status) {
        var response = JSON.parse(data);
        $("#member-total").html(response[0][0]);
    });
    
    $.post("Dashboard",
    {
        code: 3
    },function (data, status) {
        var response = JSON.parse(data);
        $("#new-member-total").html(response[0][0]);
    });
    
    $.post("Dashboard",
    {
        code: 4
    },function (data, status) {
        var response = JSON.parse(data);
        $("#exp-member").html(response[0][0]);
    });
    
    $.post("Dashboard",
    {
        code: 5
    },function (data, status) {
        var response = JSON.parse(data);
        var total = 0;
        for(var i = 0; i < response.length; i++) {
            var label = "danger";
            var text  = response[i][1]+" days left";
            if(parseInt(response[i][1]) == 0) {
                label = "danger";
            } else if(parseInt(response[i][1]) < 3) {
                label = "danger";
            } else if(parseInt(response[i][1]) < 6){
                label = "warning";
            } else{
                label = "success";
            }
            
            if(parseInt(response[i][1]) == 0) {
                text  = "Today";
            } else if(parseInt(response[i][1]) == 1) {
                text  = "Tomorrow";
            }
            
            var append = "<tr>"+
                "<td><strong>"+response[i][0]+"</strong></td>"+
                "<td><span class='label label-"+label+"'>"+text+"</span></td>"+
                "<td>"+response[i][2]+"</td>" +
            "</tr>";
            $("#member-in-exparacy").append(append);
        }
    });
});

function getURLParameter(url, name) {
    return (RegExp(name + '=' + '(.+?)(&|$)').exec(url) || [, null])[1];
}
