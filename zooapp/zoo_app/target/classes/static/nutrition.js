window.onload = function() {
    fetch('/api/nutrition')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('nutritionTable').getElementsByTagName('tbody')[0];
            data.forEach(row => {
                const newRow = tableBody.insertRow();
                newRow.insertCell().innerText = row.NutritionID;
                newRow.insertCell().innerText = row.Food_Type;
                newRow.insertCell().innerText = row.SupplierID;
                newRow.insertCell().innerText = row.Supplier_FirstName;
                newRow.insertCell().innerText = row.Supplier_LastName;
                newRow.insertCell().innerText = row.TotalRows;
            });
        });
}