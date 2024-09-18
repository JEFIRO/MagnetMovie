document.getElementById('cadastre-se').addEventListener('click', async () => {
    const name = document.getElementById('name');
    const userName = document.getElementById('userName');
    const email = document.getElementById('email');
    const date = document.getElementById('date');
    const password = document.getElementById('password');
    const messageContainer = document.getElementById('message-container');

    let hasError = false;

    // Verifica se os campos estão vazios
    if (!name.value.trim()) {
        name.classList.add('error');
        hasError = true;
    } else {
        name.classList.remove('error');
    }

    if (!userName.value.trim()) {
        userName.classList.add('error');
        hasError = true;
    } else {
        userName.classList.remove('error');
    }

    if (!email.value.trim()) {
        email.classList.add('error');
        hasError = true;
    } else {
        email.classList.remove('error');
    }

    if (!date.value.trim()) {
        date.classList.add('error');
        hasError = true;
    } else {
        date.classList.remove('error');
    }

    if (!password.value.trim()) {
        password.classList.add('error');
        hasError = true;
    } else {
        password.classList.remove('error');
    }

    if (hasError) {
        messageContainer.textContent = 'Por favor, preencha todos os campos.';
        messageContainer.className = 'error';
        return; // Interrompe a execução se houver erro
    }

    try {
        const response = await fetch('http://localhost:8080/cadastro', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: name.value,
                userName: userName.value,
                email: email.value,
                date: date.value,
                password: password.value
            })
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const result = await response.json();
        messageContainer.textContent = result.message || 'Cadastro realizado com sucesso!';
        messageContainer.className = 'success';

    } catch (error) {
        console.error('Erro:', error);
        messageContainer.textContent = 'Ocorreu um erro ao processar sua solicitação. Tente novamente mais tarde.';
        messageContainer.className = 'error';
    }
});
