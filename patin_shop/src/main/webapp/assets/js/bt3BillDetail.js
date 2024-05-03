$('document').ready(function () {
    var id = $('#billID').val()
    // var id = document.querySelector("#billID").value
    $('#data').DataTable({
        ajax: {
            url: `getDataBillDetail?id=${id}`,
            type: "GET",
            dataType: "json",
            dataSrc: "",
            // success: function (response) {
            //     console.log(response)
            // },
            // error: function (xhr, status, error) {
            //     console.log(error)
            // }
        },
        columns: [
            {
                data: "product.productDetail.product.name",
            },
            {data: "color.name"},
            {data: "size.name"},
            {data: "quantity"},
            {
                data: "price",
                render: function (data) {
                    return parseFloat(data).toLocaleString('vi-VN', {
                        style: 'currency',
                        currency: 'VND'
                    })
                }
            }
        ]
    })

    $('#statusBill').change(function () {
        var status = $(this).val()
        if (status == "Trạng thái hoàn thành"){
            $(this).prop('disabled', true)
        }
        $.ajax({
            url: 'changeStatusBillBT3',
            type: 'POST',
            data: {
                id: $('#billID').val(),
                status: status
            },
            success: function (response) {
                alert(response)
            },
            error: function (xhr, status, error) {
                alert(error)
            }
        })
    })
})