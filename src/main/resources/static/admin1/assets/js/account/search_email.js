$(document).ready(function() {
    $('#searchInput').on('input', function() {
       // var query = $(this).val();
        if (query.length >= 1) { // Chỉ gửi yêu cầu khi có ít nhất 3 ký tự
            $.ajax({
                type: 'GET',
                url: "/admin/account",
                data: { query: query },
                success: function(data) {
                    // Hiển thị kết quả tìm kiếm
                    console.log(data);
                    
                    var table = $('#resultsTable');
                    table.empty();
                    // $.each(data, function(index, item) {
                    //     var row = $('<tr><td>' + item.field1 + '</td><td>' + item.field2 + '</td></tr>');
                    //     table.append(row);
                    // });

                }
            });
        } else {
            $('#searchResults').html(''); // Xóa kết quả tìm kiếm nếu có ít hơn 3 ký tự
        }
    });
});