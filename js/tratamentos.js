const formTratamento = document.getElementById('formTratamento');

const resultadoTratamento = document.getElementById('resultadoTratamento');

formTratamento.addEventListener('submit', function(event){

    event.preventDefault();

    const tratamento = document.getElementById('tratamento').value;

    const descricao = document.getElementById('descricao').value;

    const valor = document.getElementById('valor').value;

    if(tratamento === '' || descricao === '' || valor === ''){

        resultadoTratamento.innerHTML = 'Preencha todos os campos!';

        resultadoTratamento.style.color = 'red';

    }else{

        resultadoTratamento.innerHTML = 'Tratamento cadastrado com sucesso!';

        resultadoTratamento.style.color = 'green';

        formTratamento.reset();
    }

});



