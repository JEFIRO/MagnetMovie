// Obtém o ID do filme a partir dos parâmetros da URL
const urlParams = new URLSearchParams(window.location.search);
const movieId = urlParams.get('id');
const token = localStorage.getItem('authToken');

// Função para buscar os detalhes do filme com base no ID
const fetchMovieDetails = async (id) => {
    try {
        const response = await fetch(`http://localhost:8080/home/details?id=${id}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error('Erro na requisição: ' + response.statusText);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Erro ao buscar detalhes do filme:', error);
    }
};

// Função para exibir os detalhes do filme na página
const displayMovieDetails = async () => {
    const movie = await fetchMovieDetails(movieId);
    if (movie) {
        // Atualiza o conteúdo da página com os detalhes do filme
        document.getElementById('title').textContent = movie.original_title ;
        document.getElementById('movie-title').textContent = movie.title ;
        document.getElementById('movie-poster').src = movie.poster_path ? `https://image.tmdb.org/t/p/w500${movie.poster_path}` : 'path/to/default/image.jpg';
        document.getElementById('movie-overview').textContent = movie.overview;
        document.getElementById('movie-release-date').textContent = movie.release_date;
        document.getElementById('movie-runtime-c').textContent = movie.runtime;
        document.getElementById('movie-runtime').textContent = movie.runtime;

        // Atualiza o conteúdo dos atores, diretores e gêneros
        updateActors(movie.ator, movie.direto);
        updateGenero(movie.genero);
        updateGeneroo(movie.genero);

        // Atualiza a seção de filmes relacionados
        updateRelatedMovies(movie.relacionados);

        const url = movie.link || '#'; // Redefinindo para um URL padrão ou '#'
        const button = document.getElementById('redirect-button');

        // Configura o botão para redirecionar para a URL do filme
        if (button) {
            button.addEventListener('click', () => {
                window.location.href = url; // Redireciona para a URL
            });
        }

        // Adiciona o filme à lista de desejos ao clicar no botão
        const desejoButton = document.getElementById('button_Laste');
        if (desejoButton) {
            desejoButton.addEventListener('click', async () => {
                try {
                    const id = localStorage.getItem('IdUser');
                    const filmeName = movie.id;

                    console.log('Tentando adicionar à lista de desejos:', { idUser: id, nameMovie: filmeName });

                    const response = await fetch('http://localhost:8080/listadesejo', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${token}`
                        },
                        body: JSON.stringify({
                            idUser: id,
                            idMovie: filmeName
                        })
                    });

                
                } catch (error) {
                    console.error('Erro ao adicionar filme à lista de desejos:', error);
                }
            });
        }
    }
};

// Função para atualizar a lista de atores e diretores
const updateActors = (actors, director) => {
    const atoresContainer = document.querySelector('.atores');
    if (atoresContainer) {
        atoresContainer.innerHTML = ''; // Limpa o conteúdo existente

        actors.slice(0, 6).forEach((actor, index) => {
            // Cria um novo elemento para cada ator
            const actorElement = document.createElement('a');
            actorElement.href = ''; // Você pode adicionar um link ou ID do ator, se necessário
            actorElement.id = `movie-ator-${index + 1}`;
            actorElement.innerHTML = `<strong>${actor.name}</strong>`;

            // Adiciona o elemento ao container
            atoresContainer.appendChild(actorElement);
        });
    }

    const diretorContainer = document.querySelector('.diretor');
    if (diretorContainer) {
        diretorContainer.innerHTML = ''; // Limpa o conteúdo existente

        if (director) {
            // Cria um novo elemento para o diretor
            const directorElement = document.createElement('a');
            directorElement.href = ''; // Você pode adicionar um link ou ID do diretor, se necessário
            directorElement.id = 'movie-diretor-1';
            directorElement.innerHTML = `<strong>${director.name}</strong>`;

            // Adiciona o elemento ao container
            diretorContainer.appendChild(directorElement);
        }
    }
};

// Função para atualizar a lista de gêneros
const updateGenero = (generos) => {
    const generoElement = document.getElementById('movie-genero');
    if (generoElement) {
        // Converte o array de gêneros em uma string separada por vírgulas
        const generoNames = generos.map(g => g.name).join(', ');

        // Atualiza o conteúdo do elemento com os nomes dos gêneros
        generoElement.textContent = generoNames;
    }
};

const updateGeneroo = (generos) => {
    // Seleciona todos os links de gênero
    const genreLinks = [
        document.querySelector('.movie-genre-1'),
        document.querySelector('.movie-genre-2'),
        document.querySelector('.movie-genre-3'),
        document.querySelector('.movie-genre-4')
    ];

    // Atualiza os links com os nomes dos gêneros
    genreLinks.forEach((link, index) => {
        if (generos[index]) {
            link.textContent = generos[index].name;
            link.href = `#${generos[index].name.toLowerCase()}`; // Ajuste o href conforme necessário
        } else {
            link.textContent = ''; // Limpa o texto se não houver gênero para esse link
            link.href = ''; // Limpa o href se não houver gênero
        }
    });
};

// Nova função para atualizar os filmes relacionados
const updateRelatedMovies = (relatedMovies) => {
    var cont = 0;
    const relacionadosContainer = document.querySelector('.recomendados_filmes');
    if (relacionadosContainer) {
        relacionadosContainer.innerHTML = ''; // Limpa o conteúdo existente

        relatedMovies.slice(0, 100).forEach((movie, index) => {
            // Cria um novo elemento para cada filme relacionado
            const movieElement = document.createElement('div');
            movieElement.classList.add('filme_te');
            console.log(index)
            if(cont == 6){return}
            if(movie.posterPath == null){
                return
            }
            movieElement.innerHTML = `
                        <a href="details.html?id=${movie.id}">
                <img src="${movie.posterPath ? `https://image.tmdb.org/t/p/w500${movie.posterPath}` : 'path/to/default/image.jpg'}" alt="${movie.title}" />
                <div class="texto_filme"><p>${movie.title}</p></div>
            `;

            // Adiciona o elemento ao container
            relacionadosContainer.appendChild(movieElement);
            cont++;
        });
    }
};

// Chama a função para exibir os detalhes do filme
displayMovieDetails();
