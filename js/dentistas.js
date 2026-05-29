const formDentista = document.getElementById('formDentista');

const resultadoDentista = document.getElementById('resultadoDentista');

formDentista.addEventListener('submit', function(event){

    event.preventDefault();

    const nomeDentista = document.getElementById('nomeDentista').value;

    const cro = document.getElementById('cro').value;

    const especialidade = document.getElementById('especialidade').value;

    if(nomeDentista === '' || cro === '' || especialidade === ''){

        resultadoDentista.innerHTML = 'Preencha todos os campos!';

        resultadoDentista.style.color = 'red';

    }else{

        resultadoDentista.innerHTML = 'Dentista cadastrado com sucesso!';

        resultadoDentista.style.color = 'green';

        formDentista.reset();
    }

});





