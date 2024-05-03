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
            {data: "price"}
        ]
    })
})