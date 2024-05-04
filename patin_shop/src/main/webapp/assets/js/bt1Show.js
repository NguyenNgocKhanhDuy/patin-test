$('document').ready(function () {
    var table = $('#data').DataTable({
        ajax: {
            url: 'getUserBT1',
            type: 'GET',
            dataType: 'json',
            dataSrc: ''
        },


        // "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
        //     var index = iDisplayIndex +1;
        //     $('td:eq(0)',nRow).html(index);
        //     return nRow;
        // },

        columns: [
            // {data: 'ID'},

            {data: 'fullName'},
            // {data: 'password'},
            {data: 'email'},
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
                render: function () {
                    return '<i class="fa-solid fa-trash maction"></i>'
                }
            }
        ]
    })


    $('#data tbody').on('click', 'td.delete', function () {
        var rowIndex = table.cell($(this)).index().row;
        var rowData = table.row(rowIndex).data();
        var idUser = rowData.id;

        $('.modal').css({"display": "flex"})
        $('.modal .btn-info').on('click', function () {
            $('.modal').css({"display": "none"})
        })
        $('.modal .btn-danger').on('click', function () {
            $('.modal').css({"display": "none"})
            $.ajax({
                url: 'deleteUserBT1',
                type: 'POST',
                data: { id: idUser },
                success: function(response) {
                    table.row(rowIndex).remove().draw();
                    $('.popup').addClass("success").removeClass("none")
                    $('.popup').html(`<i class=\"fa-solid fa-check icon\"></i> <p>${response}</p> <i class="fa-solid fa-xmark del"></i>`)
                    time = setTimeout(hideError, 3000)
                    $('.popup .del').on('click', function () {
                        hideError()
                        clearInterval(time)
                    })
                },
                error: function(xhr, status, error) {
                    $('.popup').addClass("error").removeClass("none")
                    $('.popup').html(`<i class="fa-solid fa-ban fa-flip-horizontal icon"></i> <p>${error}</p> <i class="fa-solid fa-xmark del"></i>`)
                }
            });
        })
    })

    function hideError() {
        $('.popup').addClass("fadeOut")
        setTimeout(hide, 500);
    }

    function hide() {
        $('.popup').removeClass("error alert success fadeOut")
        $('.popup').addClass("none")
    }
})
