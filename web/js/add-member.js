$(document).ready(function () {
    
    $("#add-member-clear").click(function () {
        $("#add-member-name").val("");
        $("#add-member-id-no").val("");
        $("#add-member-bdate").val("");
        $("#add-member-gender").val("");
        $("#add-member-address").val("");
        $("#add-member-phone").val("");
        $("#add-member-package-list").val("");
    });
    
    $("#add-member-submit").click(function () {
        var name            = $("#add-member-name").val();
        var idno            = $("#add-member-id-no").val();
        var bdate           = $("#add-member-bdate").val();
        var gender          = $("#add-member-gender").val();
        var address         = $("#add-member-address").val();
        var phone           = $("#add-member-phone").val();
        var package_list    = $("#add-member-package-list").val();
        $.post("SubmitAddMember", { name: name, idno: idno, bdate : bdate, gender : gender, address : address, phone : phone, package : package_list },
        function (data, status) {
            var result = $.trim(data);
            alert(result);
        });
    });
});