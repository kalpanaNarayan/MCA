const jokeText = document.getElementById('joke-text');
const fetchButton = document.getElementById('fetch-button');

async function fetchRandomJoke() {
    try {
        const response = await fetch('https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit');
        const data = await response.json();

        if (data.error) {
            jokeText.textContent = 'Failed to fetch joke. Please try again.';
        } else {
            jokeText.textContent = data.joke || (data.setup + ' ' + data.delivery);
        }
    } catch (error) {
        jokeText.textContent = 'An error occurred. Please try again later.';
    }
}

fetchButton.addEventListener('click', fetchRandomJoke);
fetchRandomJoke(); // Fetch a joke on page load
