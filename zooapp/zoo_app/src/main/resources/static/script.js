$(document).ready(function() {
    $('#button1').click(function() {
        fetch('/api/animals/male')
        .then(response => response.json())
        .then(data => {
            console.log(data);
            let html = '<table><tr><th>ID</th><th>Caretaker_Supplier_Info</th><th>Animal_name</th><th>>Animal_Birthdate</th><th>Animal_Gender</th><th>Animal_Specie</th></tr>';
            for (let animal of data) {
                html += `<tr><td>${animal.AnimalID}</td><td>${animal.Caretaker_Supplier_Info}</td><td>${animal.Animal_name}</td><td>${animal.Animal_Birthdate}</td><td>${animal.Animal_Gender}</td><td>${animal.Animal_Specie}</td></tr>`;
            } 
            html += '</table>';

            // Insert the HTML into the page
            $('#animal-container').html(html);
        })
        .catch(error => console.error('Error:', error));
    });

    $('#button2').click(function() {
        fetch('/api/animals/female')
        .then(response => response.json())
        .then(data => {
            // Create a string of HTML to display the data
            let html = '<table><tr><th>ID</th><th>Caretaker_Supplier_Info</th><th>.Animal_name</th></tr>';
            for (let animal of data) {
                html += `<tr><td>${animal.AnimalID}</td><td>${animal.Caretaker_Supplier_Info}</td><td>${animal.Animal_name}</td></tr>`;
            }
            html += '</table>';

            // Insert the HTML into the page
            $('#animal-container').html(html);
        })
        .catch(error => console.error('Error:', error));
    });
});

