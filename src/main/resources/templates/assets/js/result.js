document.addEventListener('DOMContentLoaded', function() {
    // Fetch latest score from backend
    fetch('/latest-score')
        .then(response => response.json())
        .then(data => {
            const latestScore = data.score;

            // Scale the score between 1 and 99
            const scaledScore = Math.floor(((latestScore + 44) / 121) * 98) + 1;

            // Make request to server to get appropriate page based on scaled score
            fetch(`/result?score=${scaledScore}`)
                .then(response => response.text())
                .then(html => {
                    document.body.innerHTML = html;

                    // Display the scaled score
                    document.getElementById('latest-score').textContent = scaledScore;
                })
                .catch(error => console.error('Error fetching page:', error));
        })
        .catch(error => console.error('Error fetching latest score:', error));
});
