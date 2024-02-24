// Handle the 'Delete' button
$('#delete-button').click(function() {
    // Get the HabitatID from the input field
    let HabitatID = $('#delete-HabitatID').val();

    // Send a DELETE request with the HabitatID in the URL
    $.ajax({
        url: '/api/habitats/delete/' + HabitatID,
        type: 'DELETE',
        success: function(response) {
            console.log('Success:', response);
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
});
// Handle the 'Insert' button
$('#insert-button').click(function() {
    // Get the values from the form fields
    let HabitatID = $('#HabitatID').val();
    let Capacity = $('#Capacity').val();
    let Habitat_Type = $('#Habitat_Type').val();
    // Create an object with the data
    let data = { HabitatID: HabitatID, Capacity: Capacity, Habitat_Type: Habitat_Type };

    // Send a POST request with the data
    console.log(HabitatID);
    $.ajax({
        url: '/api/habitats/insert',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(response) {
            console.log('Success:', response);
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
});


// Handle the 'View' button
$('#view-button').click(function() {
    $.ajax({
        url: "/api/habitats",
        type: "GET",
        dataType: "json",
        success: function(data) {
            console.log(data);
            let html = '<table><tr><th>Habitat ID</th><th>Capacity</th><th>Habitat Type</th>';
            // Check if 'humidity_level' exists in the first habitat object
            if (data.length > 0 && 'humidity_level' in data[0]) {
                html += '<th>Humidity Level</th>'; // Add 'Humidity Level' header
            }
            html += '</tr>';
            for (let habitat of data) {
                html += `<tr><td>${habitat.HabitatID}</td><td>${habitat.Capacity}</td><td>${habitat.Habitat_Type}</td>`;
                if ('humidity_level' in habitat) {
                    html += `<td>${habitat.humidity_level}</td>`; // Add 'humidity_level' data
                }
                html += '</tr>';
            }
            html += '</table>';

            // Insert the HTML into the page
            $('#habitat-container').html(html);
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
});
$(document).ready(function() {
    $('#add-humidity-button').click(function() {
        $.ajax({
            url: '/api/habitats/addHumidityLevelColumn',
            type: 'GET',
            success: function(response) {
                console.log('Humidity level column added successfully');
            },
            error: function(error) {
                console.error('Error:', error);
            }
        });
    });
});
$(document).ready(function() {
    // ...

    $('#update-humidity-button').click(function() {
        $.ajax({
            url: '/api/habitats/updateHumidityLevel',
            type: 'PATCH',
            success: function(response) {
                console.log('Humidity level updated successfully');
            },
            error: function(error) {
                console.error('Error:', error);
            }
        });
    });
});
$(document).ready(function() {
    $('#drop-humidity-button').click(function() {
        $.ajax({
            url: '/api/habitats/dropHumidityLevelColumn',
            type: 'DELETE',
            success: function(response) {
                console.log('Humidity level column deleted successfully');
            },
            error: function(error) {
                console.error('Error:', error);
            }
        });
    });
});