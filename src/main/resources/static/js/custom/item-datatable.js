// Tables-DataTables.js
// ====================================================================
// This file should not be included in your project.
// This is just a sample how to initialize plugins or components.
//
// - ThemeOn.net -

/*<![CDATA[*/
var table;

$(window).on('load', function () {
    // DATA TABLES
    // =================================================================
    // Require Data Tables
    // -----------------------------------------------------------------
    // http://www.datatables.net/
    // =================================================================

    $.fn.DataTable.ext.pager.numbers_length = 5;


    table = $('#datatable').DataTable({
        "lengthMenu": [10, 50, 150, 500],
        "processing": true,
        responsive: true,
        "ajax": {
            "url": CONTEXT_PATH + "/itemController/itemlist",
            "type": "GET"
        },
        "columns": [
            {"data": "shortCode"},
            {"data": "name"},
            {"data": "description"},
            {"data": "price"}

        ]
    });


    $('#datatable tbody').on('click', 'tr', function () {
        var data = table.row(this).data();
        var id = data['shortCode'];
        $("#shortCode").val(id);
        document.getElementById('frmMain').action = CONTEXT_PATH + "/itemController/viewEdit";
        document.getElementById('frmMain').submit();

    });

    var rowSelection = table;
    $('#datatable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            rowSelection.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }

    });


});
//

function reloadPage() {
    var userName = $("#userSearchName").val();
    var companyName = $("#companyName").val();


    table.ajax.url(encodeURI(CONTEXT_PATH + "/user/list" + "?userSearchName=" + userName + "&companyName=" + companyName)).load();
    return false;
}
/*]]>*/
