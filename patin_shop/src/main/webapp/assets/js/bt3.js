$('document').ready(function () {
    var table = $('#data').DataTable({
        ajax: {
            url: "showBillBT3",
            type: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {
                data: null,
                render: function (data) {
                    return `<a href="showBillDetailBT3?id=${data.id}">${data.name}</a>`
                }
            },
            {data: "date"},
            {
                data: "status",
                className: "text-center",
                // render: function () {
                //     return `<select>
                //                 <option value="1">Trạng thái 1</option>
                //                 <option value="2">Trạng thái 2</option>
                //                 <option value="3">Trạng thái 3</option>
                //                 <option value="4">Trạng thái 4</option>
                //             </select>`
                // }

            },
            {data: "payment"},
            {data: "note"},
            {data: "user.phone"},
        ]
    })

})