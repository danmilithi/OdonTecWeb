const formPaciente = document.getElementById('formPaciente');

const resultado = document.getElementById('resultado');

formPaciente.addEventListener('submit', function(event){

    event.preventDefault();

    const nome = document.getElementById('nome').value;

    const cpf = document.getElementById('cpf').value;

    const telefone = document.getElementById('telefone').value;

    const email = document.getElementById('email').value;

    if(nome === '' || cpf === '' || telefone === '' || email === ''){

        resultado.innerHTML = 'Preencha todos os campos!';

        resultado.style.color = 'red';

    }else{

        resultado.innerHTML = 'Paciente cadastrado com sucesso!';

        resultado.style.color = 'green';

        formPaciente.reset();
    }

});



