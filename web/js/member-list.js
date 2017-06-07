$(document).ready(function () {
    $.post("RetrieveMemberList", {},
    function (data, status) {
        var result = JSON.parse(data);
        for(var i = 0; i < result.length; i++) {
            var row = "<tr><td>"+result[i][0]+"</td><td>"+(result[i][1] == "1" ? "Male" : "Female")+"</td><td>"+result[i][2]+"</td><td>"+result[i][3]+"</td><td>"+result[i][4]+"</td></tr>";
            
            $("#membership-list").append(row);
        }
        $(".datatable").dataTable();
    });
});

function refreshList() {
    location.reload();
}
