const form = document.getElementById('feedbackForm');
const nameInput = document.getElementById('name');
const emailInput = document.getElementById('email');
const commentInput = document.getElementById('comment');
const nameError = document.getElementById('nameError');
const emailError = document.getElementById('emailError');
const commentError = document.getElementById('commentError');
const thankYouMessage = document.getElementById('thankYouMessage');
const backButton = document.getElementById('backButton');

form.addEventListener('submit', function(event) {
    event.preventDefault();

    let valid = true;

    if (nameInput.value.trim() === '') {
        nameError.textContent = 'Name is required';
        valid = false;
    } else {
        nameError.textContent = '';
    }

    if (emailInput.value.trim() === '') {
        emailError.textContent = 'Email is required';
        valid = false;
    } else {
        emailError.textContent = '';
    }

    if (commentInput.value.trim() === '') {
        commentError.textContent = 'Comment is required';
        valid = false;
    } else {
        commentError.textContent = '';
    }

    if (valid) {
        form.style.display = 'none'; // Hide the form
        thankYouMessage.style.display = 'block'; // Show the thank you message
    }
});

backButton.addEventListener('click', function() {
    form.style.display = 'block'; // Show the form again
    thankYouMessage.style.display = 'none'; // Hide the thank you message
    form.reset(); // Reset the form fields
});
