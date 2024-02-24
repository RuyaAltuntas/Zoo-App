document.getElementById('load-button').addEventListener('click', function() {
    fetch('/api/specialCapacity')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('data-table').getElementsByTagName('tbody')[0];
            tableBody.innerHTML = '';
            data.forEach(visitor => {
                const row = tableBody.insertRow();
                row.insertCell().innerText = visitor.visitorFirstName;
                row.insertCell().innerText = visitor.visitorLastName;
                row.insertCell().innerText = new Date(visitor.visitDate).toLocaleDateString();
            });
        });
});