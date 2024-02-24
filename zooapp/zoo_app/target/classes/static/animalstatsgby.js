$(document).ready(function() {
    $('#button3').click(function() {
    fetch('/api/animals/stats')
    .then(response => response.json())
    .then(data => {
        let html = '<table><tr><th>HabitatID</th><th>AnimalCount</th><th>AverageSicknessRate</th></tr>';
        for (let stat of data) {
            html += `<tr><td>${stat.HabitatID}</td><td>${stat.AnimalCount}</td><td>${stat.AverageSicknessRate.toFixed(4)}</td></tr>`;
        }
        html += '</table>';
        $('#stats-container').html(html);
    })
    .catch(error => console.error('Error:', error));
});
});