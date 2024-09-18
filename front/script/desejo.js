document.addEventListener('DOMContentLoaded', function() {
    const token = localStorage.getItem('authToken'); // Obtém o token de autenticação
    const id = localStorage.getItem('IdUser'); // Obtém o ID do usuário

    async function fetchFilmes() {
        try {
            const response = await fetch('http://localhost:8080/listadesejo/sher', {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json' // Define o tipo de conteúdo
                },
                body: JSON.stringify({ idUser: id }) // Envia o ID no corpo da requisição
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const filmes = await response.json();
            renderFilmes(filmes);
        } catch (error) {
            console.error('Erro ao buscar filmes:', error);
        }
    }

    function renderFilmes(filmes) {
        const filmesContainer = document.getElementById('filmes_container');
        
        filmesContainer.innerHTML = ''; // Limpa o container de filmes

        filmes.forEach(filme => {
            const filmeElement = document.createElement('div');
            filmeElement.className = 'filmes'; // Adiciona a classe 'filmes' para estilização
            filmeElement.innerHTML = `
                <a href="details.html?id=${filme.id}">
                <img class="img_poster" src="https://image.tmdb.org/t/p/w500${filme.
                    poster_path}" alt="${filme.title}">
                <p><strong>Nome Do filme:</strong> ${filme.title}</p>
                <p><strong>Lançamento:</strong> ${filme.release_date}</p>
                <p><strong>Imdb note:</strong> ${filme.vote_average}</p>
                <div class="butt">
                    <button class="button_remover" data-id="${filme.id}">Remover</button>
                </div>
            `;
            filmesContainer.appendChild(filmeElement);
        });
        console.log(filmesContainer)
        document.querySelectorAll('.button_remover').forEach(button => {
            button.addEventListener('click', function() {
                const filmeId = this.getAttribute('data-id');
                removeFilme(filmeId);
            });
        });
    }

    async function removeFilme(filmeId) {
        try {
            const response = await fetch(`http://localhost:8080/listadesejo/remove`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ idUser: id, idFilme: filmeId })
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            // Recarrega a lista de filmes após a remoção
            fetchFilmes();
        } catch (error) {
            console.error('Erro ao remover filme:', error);
        }
    }

    fetchFilmes(); // Chama a função para buscar e renderizar os filmes
});
