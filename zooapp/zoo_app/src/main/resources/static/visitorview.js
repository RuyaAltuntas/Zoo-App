$('#loadDataButton').click(function() {
    $.ajax({
        url: "/api/visitorHabitatCapacityView",
        type: "GET",
        dataType: "json",
        success: function(data) {
            let html = '';
            for (let row of data) {
                html += `<tr><td>${row.VisitorID}</td><td>${row.VisitDate}</td><td>${row.Visitor_FirstName}</td><td>${row.Visitor_LastName}</td><td>${row.HabitatID}</td><td>${row.Capacity}</td><td>${row.Habitat_Type}</td></tr>`;
            }
            // Insert the HTML into the page
            $('#visitorTable tbody').html(html);
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
});