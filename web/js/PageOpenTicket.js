/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {
    var data_atm;
    var data_peg;
    $.post('helper_atm', {code: "0"},
            function (returnedData) {
                var obj = JSON.parse(returnedData);
                data_atm = JSON.parse(returnedData);
                console.log(returnedData);
                jQuery.each(obj, function (i, val) {
                    console.log(obj[i]["id_atm"]);
                    $('<option>').val(obj[i]["id_atm"]).text(obj[i]["id_atm"]).appendTo('#select_atm');
                });
                ;

            }).fail(function () {
        console.log("error");
    });
    $.post('helper_masalah', {code: "0"},
            function (returnedData) {
                var obj = JSON.parse(returnedData);
                console.log(returnedData);
                jQuery.each(obj, function (i, val) {
                    console.log(obj[i]["id_masalah"]);
                    $('<option>').val(obj[i]["nama_masalah"]).text(obj[i]["nama_masalah"]).appendTo('#select_masalah');
                });
                ;

            }).fail(function () {
        console.log("error");
    });
    $.post('helper_pegawai', {code: "0"},
            function (returnedData) {
                var obj = JSON.parse(returnedData);
                console.log(returnedData);
                data_peg = obj;
                ;

            }).fail(function () {
        console.log("error");
    });

    $("#select_atm").click(function () {
        var id_atm = $("#select_atm").val();
        jQuery.each(data_atm, function (i, val) {
            console.log(data_atm[i]["id_atm"]);
            if (data_atm[i]["id_atm"] === id_atm) {
                //nih disini 
                $("#input_atm_name").val(data_atm[i]["nama_atm"]);
                $("#input_atm_loct").val(data_atm[i]["nama_lokasi"]);
                console.log(data_atm[i]["nama_lokasi"] + " " + data_atm[i]["p_terakhir"] + " " + data_atm[i]["nama_atm"] + " " + data_atm[i]["kordinator"]);
            }

        });
    });

    $('#nik').autocomplete({

        minLength: 2,
        source: function (request, response) {
            response($.map(data_peg, function (value, key) {
                return {
                    label: value.nik,
                    value: value.nik
                }
            }));

        }
    });

    $('#nik').on('autocompletechange change', function () {
        var nik = this.value;
        jQuery.each(data_peg, function (i, val) {
            console.log(data_peg[i]["nik"]);
            if (data_peg[i]["nik"].trim() ===nik.trim()) {
                //nih disini 
                $('#custody').val(data_peg[i]["nama"]);
            }

        });
//   $('#custody').val(this.value);
    }).change();
});

