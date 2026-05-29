const formLogin = document.getElementById('formLogin');

const mensagem = document.getElementById('mensagem');

formLogin.addEventListener('submit', function(event){

    event.preventDefault();

    const usuario = document.getElementById('usuario').value;

    const senha = document.getElementById('senha').value;

    if(usuario === 'admin' && senha === '123'){

        mensagem.innerHTML = 'Login realizado com sucesso!';

        mensagem.style.color = 'green';

        setTimeout(() => {

            window.location.href = 'dashboard.html';

        }, 1000);

    }else{

        mensagem.innerHTML = 'Usuário ou senha inválidos!';

        mensagem.style.color = 'red';
    }

});



