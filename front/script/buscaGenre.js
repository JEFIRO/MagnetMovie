let currentPage = 1;
const token = localStorage.getItem('authToken');


const displayMovies = (movies) => {
    const movieList = document.getElementById('movie-list');
    movieList.innerHTML = '';
    movies.forEach(movie => {
        const movieElement = document.createElement('div');
        movieElement.className = 'movie';
        movieElement.innerHTML = `
            <a href="details.html?id=${movie.id}">
                <img src="https://image.tmdb.org/t/p/w500${movie.posterPath}" alt="${movie.title}">
                <div class="movie-info">
                    <h3>${movie.title}</h3>
                    <h2>${movie.voteAverage}</h2>
                </div>
            </a>
        `;
        movieList.appendChild(movieElement);
    });
};

const loadMovies = async () => {
    const movies = await fetchMovies(currentPage);
    if (movies) {
        displayMovies(movies);
        document.getElementById('prev-page').disabled = currentPage === 1;
    }
};

document.getElementById('prev-page').addEventListener('click', () => {
    if (currentPage > 1) {
        currentPage--;
        searchMovieGenre();
    }
});

document.getElementById('next-page').addEventListener('click', () => {
    currentPage++;
    searchMovieGenre()
});
document.getElementById('pesquisa-button').addEventListener('click', () => {
    searchMovie();
});
document.getElementById('genre').addEventListener('change', () => {
    searchMovieGenre()
});

async function searchMovieGenre() {
    let genreId = document.getElementById('genre').value;
    if (genreId) {
        try {
            const response = await fetch(`http://localhost:8080/home/genre/${currentPage}?nome=${encodeURIComponent(genreId)}`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            // Tenta converter a resposta para JSON
            const data = await response.json();

            if (Array.isArray(data) && data.length > 0) {
                displayMovies(data);
            } else {
                alert('Nenhum filme encontrado para o gênero selecionado.');
            }
        } catch (error) {
            console.error('Erro ao buscar filmes por gênero:', error);
            alert('Ocorreu um erro ao buscar filmes. Tente novamente mais tarde.');
        }
    } else {
        alert('Por favor, selecione um gênero para buscar.');
    }
}

async function searchMovie() {
    const query = document.querySelector('input[type="text"]').value;
    if (query) {
        try {
            const response = await fetch(`http://localhost:8080/home/procura?nome=${encodeURIComponent(query)}`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();

            if (data && Array.isArray(data) && data.length > 0) {
                displayMovies(data);
            } else {
                alert('Nenhum filme encontrado com o título fornecido.');
            }
        } catch (error) {
            console.error('Erro ao buscar filmes:', error);
            alert('Ocorreu um erro ao buscar filmes. Tente novamente mais tarde.');
        }
    } else {
        alert('Por favor, digite um nome de filme para buscar.');
    }
}
function Forbiden403() {
    window.location.href = 'http://127.0.0.1:5503/login.html';
}
searchMovieGenre();
