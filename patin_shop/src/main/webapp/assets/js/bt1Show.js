$('document').ready(function () {
    var table = $('#data').DataTable({
        ajax: {
            url: 'getUserBT1',
            type: 'GET',
            dataType: 'json',
            dataSrc: ''
        },

        columns: [
            {data: 'email'},
            // {data: 'password'},
            {data: 'fullName'},
            {data: 'phone'},
            {data: 'address'},
            {
                data: 'sex',
                className: 'text-center',
                render: function (data){
                    return `<i class="fa-solid ${data == 0 ? "fa-person text-primary" : "fa-person-dress text-danger"}"></i>`
                }

            },
            {data: 'dob'},
            {data: 'verify'},
            {data: 'role'},
            {
                data: 'Edit',
                className: 'text-center edit',
                render: function () {
                    return '<i class="fa-solid fa-pen maction "></i>'
                }
            },
            {
                data: null,
                className: 'text-center delete',
                render: function (data) {
                    return '<i class="fa-solid fa-trash maction"></i>'
                }
            }
        ]
    })


    $('#data tbody').on('click', 'td.delete', function () {
        var rowIndex = table.cell($(this)).index().row;
        var rowData = table.row(rowIndex).data();
        var idUser = rowData.id;

        $.ajax({
            url: 'deleteUserBT1',
            type: 'POST',
            data: { id: idUser },
            success: function(response) {
                table.row(rowIndex).remove().draw();
                console.log(response)
            },
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
    })

})