document.addEventListener('DOMContentLoaded', function() {
    let currentQuestionIndex = 0;
    const questionsContainer = document.getElementById('questionsContainer');
    const submitBtn = document.getElementById('submitBtn');
    const nextBtn = document.getElementById('nextBtn');
    const prevBtn = document.getElementById('prevBtn');
    const questions = [];
    const responses = []; // Store user responses

    // Fetch questions from the backend
    fetch('/survey', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        console.log("Received questions:", data);
        questions.push(...data.questions);

        // Display the first question
        displayQuestion(currentQuestionIndex);
    })
    .catch(error => console.error('Error fetching questions:', error));

    // Function to display the current question
    function displayQuestion(index) {
        const question = questions[index];
        const questionDiv = document.createElement('div');
        questionDiv.classList.add('question');
        questionDiv.dataset.sectionId = question.s_id;

        const optionsDiv = document.createElement('div');
        optionsDiv.classList.add('options');

        const questionText = document.createElement('p');
        questionText.textContent = question.q_id + '. ' + question.q_text;
        questionDiv.appendChild(questionText);

        question.options.forEach(option => {
            const input = document.createElement('input');
            input.type = 'radio';
            input.name = 'questionOption';
            input.value = option.o_weight;
            input.id = option.o_id;

            const label = document.createElement('label');
            label.textContent = option.o_text;
            label.htmlFor = input.id;

            optionsDiv.appendChild(input);
            optionsDiv.appendChild(label);
            optionsDiv.appendChild(document.createElement('br'));
        });

        questionDiv.appendChild(optionsDiv);
        questionsContainer.innerHTML = ''; // Clear previous question
        questionsContainer.appendChild(questionDiv);

        // Show/hide next and previous buttons based on the current question index
        if (currentQuestionIndex === 0) {
            prevBtn.style.display = 'none';
        } else {
            prevBtn.style.display = 'inline-block';
        }

        if (currentQuestionIndex === questions.length - 1) {
            nextBtn.style.display = 'none';
            submitBtn.style.display = 'inline-block'; // Show submit button when on the last question
        } else {
            nextBtn.style.display = 'inline-block';
            submitBtn.style.display = 'none';
        }
    }

    // Event listener for next button
    nextBtn.addEventListener('click', function () {
        saveResponse();
        currentQuestionIndex++;
        displayQuestion(currentQuestionIndex);
    });

    // Event listener for previous button
    prevBtn.addEventListener('click', function () {
        saveResponse();
        currentQuestionIndex--;
        displayQuestion(currentQuestionIndex);
    });

    // Function to calculate total score based on user's responses
    function calculateTotalScore(responses) {
        let totalScore = 0;
        responses.forEach(response => {
        totalScore += response.sel_score || 0; // Add sel_score to total score
        });
        return totalScore;
    }


    // Event listener for submit button
    submitBtn.addEventListener('click', function () {
        saveResponse();
        console.log(responses);

        // Calculate total score
        const totalScore = calculateTotalScore(responses);
        const scaledScore = Math.floor(((totalScore + 44) / 121) * 98) + 1;

        // Send responses to backend
        fetch('/submit', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(responses)
        })
        .then(response => {
            if (response.ok) {
                console.log('Responses submitted successfully');
                // Redirect to result page with total score as query parameter
                window.location.href = `/result?score=${scaledScore}`;
            } else {
                console.error('Error submitting responses:', response.statusText);
                // Show an error message to the user
            }
        })
        .catch(error => console.error('Error submitting responses:', error));
    });


    // Function to save user response
    function saveResponse() {
        const question = questions[currentQuestionIndex];
        const selectedOption = document.querySelector('input[name="questionOption"]:checked');
        if (selectedOption) {
            const responseIndex = responses.findIndex(response => response.q_id === parseInt(question.q_id));
            if (responseIndex !== -1) {
                // If response exists for this question, update it
                responses[responseIndex] = {
                    s_id: parseInt(question.s_id),
                    q_id: parseInt(question.q_id),
                    o_id: parseInt(selectedOption.id),
                    user_id: 1,
                    sel_score: parseInt(selectedOption.value)
                };
            } else {
                // If response doesn't exist for this question, add it
                responses.push({
                    s_id: parseInt(question.s_id),
                    q_id: parseInt(question.q_id),
                    o_id: parseInt(selectedOption.id),
                    user_id: 1,
                    sel_score: parseInt(selectedOption.value)
                });
            }
        }
    }
});
