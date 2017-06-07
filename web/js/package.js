$(document).ready(function () {
    
    $("#package-clear").click(function () {
        $("#package-name").val("");
        $("#package-period").val("1");
        $("#package-price").val("");
    });
    
    $("#package-submit").click(function () {
        var name   = $("#package-name").val();
        var period = $("#package-period").val();
        var price  = $("#package-price").val();
        $.post("submit_package", { name: name, period: period, price : price },
        function (data, status) {
            var result = $.trim(data);
            alert(result);
            
            
            refreshList();
        });
    });
    
    $.post("RetrievePackage", {},
    function (data, status) {
        var result = JSON.parse(data);
        for(var i = 0; i < result.length; i++) {
            var row = "<tr><td>"+result[i][0]+"</td><td>"+result[i][1]+"</td><td> Rp. "+result[i][2]+"</td></tr>";
            
            $("#package-list").append(row);
        }
        $(".datatable").dataTable();
    });
});

function refreshList() {
    location.reload();
}
