$(function(){

    
    const orderId = $('#orderId');
    
    $('#submit').on('click', function(){
        console.log(orderId.val());
        
        $.ajax({
            url: 'OrderServlet',
            type: 'POST',
            data: JSON.stringify({orderId : orderId.val()}),
            dataType: 'json',
            success: function(data){
                console.log(data);
                console.log(data.orderId);
                let table_html = `
                <tr>
                    <td>${data.orderId}</td>
                    <td>${data.userId}</td>
                    <td>${data.resId}</td>
                    <td>${data.delId}</td>
                    <td>${data.orderStatus}</td>
                    <td>${data.note}</td>
                    <td>${data.userLocation}</td>
                    <td>${data.orderCreate}</td>
                    <td>${data.orderDone}</td>
                    <td>${data.productKcalTotal}</td>
                    <td>${data.total}</td>
                    <td>${data.delCost}</td>
                    <td>${data.useCash}</td>
                    <td>${data.creditId}</td>
                    <td>${data.bonus}</td>
                    <td>${data.rating}</td>
                    <td>${data.resRate}</td>
                    <td>${data.delRate}</td>
                    <td>${data.resComment}</td>
                    <td>${data.delComment}</td>
                    <td>${data.promoteId}</td>
                </tr>
                `

                $('#content').html(table_html);
            }






        })
    })
    
})
