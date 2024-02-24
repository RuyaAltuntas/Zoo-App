$('#loadDataButton').click(function() {
    $.ajax({
        url: '/api/tigerandlion',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            const tableBody = $('#animalTable tbody');
            // Clear the table body to ensure old data is removed
            tableBody.empty();
            data.forEach(row => {
                const newRow = $('<tr></tr>');
                Object.values(row).forEach(cell => {
                    newRow.append($('<td></td>').text(cell));
                });
                tableBody.append(newRow);
            });
        }
    });
});