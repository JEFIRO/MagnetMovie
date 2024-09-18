document.getElementById('login').addEventListener('click', async () => {
    const email = document.getElementById('email');
    const senha = document.getElementById('password');
    const messageContainer = document.getElementById('message-container');

    let hasError = false;

    // Verifica se o campo de email está vazio
    if (!email.value.trim()) {
        email.classList.add('error');
        hasError = true;
    } else {
        email.classList.remove('error');
    }

    // Verifica se o campo de senha está vazio
    if (!senha.value.trim()) {
        senha.classList.add('error');
        hasError = true;
    } else {
        senha.classList.remove('error');
    }

    // Se houver erros, exibe a mensagem e interrompe o processo de login
    if (hasError) {
        messageContainer.textContent = 'Por favor, preencha todos os campos.';
        messageContainer.className = 'error';
        return;
    }

    // Tenta fazer a requisição de login
    try {
        const response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email.value,
                senha: senha.value
            })
        });

        console.log('Resposta do servidor:', response);

        // Verifica se a resposta foi bem-sucedida
        if (response.ok) {
            const result = await response.json();
            
            messageContainer.textContent = 'Sucesso';
            messageContainer.className = 'success';
            localStorage.setItem('authToken', result.token);
            localStorage.setItem('IdUser', result.idInterno);
            console.log('Redirecionando para a nova página...');
            onLoginSuccess();
        } else {
            messageContainer.textContent = 'Email ou senha inválidos. Tente novamente.';
            messageContainer.className = 'error';
        }
    } catch (error) {
        console.error('Erro:', error);
        messageContainer.textContent = 'Ocorreu um erro ao processar sua solicitação. Tente novamente mais tarde.';
        messageContainer.className = 'error';
    }
});

function onLoginSuccess() {
    console.log('Login bem-sucedido. Redirecionando...');
    window.location.href = './index.html';
}
