document.getElementById('load-button').addEventListener('click', function() {
    fetch('/api/caretakerAnimalStats')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('data-table').getElementsByTagName('tbody')[0];
            tableBody.innerHTML = '';
            data.forEach(stats => {
                const row = tableBody.insertRow();
                row.insertCell().innerText = stats.CaretakerID;
                row.insertCell().innerText = stats.TotalAnimals;
                row.insertCell().innerText = new Date(stats.MaxBirthdate).toLocaleDateString();
                row.insertCell().innerText = stats.TotalSickAnimals;
            });
        });
});