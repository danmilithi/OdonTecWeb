const formAgenda = document.getElementById('formAgenda');

const resultadoAgenda = document.getElementById('resultadoAgenda');

formAgenda.addEventListener('submit', function(event){

    event.preventDefault();

    const pacienteAgenda = document.getElementById('pacienteAgenda').value;

    const dentistaAgenda = document.getElementById('dentistaAgenda').value;

    const dataAgenda = document.getElementById('dataAgenda').value;

    if(pacienteAgenda === '' || dentistaAgenda === '' || dataAgenda === ''){

        resultadoAgenda.innerHTML = 'Preencha todos os campos!';

        resultadoAgenda.style.color = 'red';

    }else{

        resultadoAgenda.innerHTML = 'Consulta agendada com sucesso!';

        resultadoAgenda.style.color = 'green';

        formAgenda.reset();
    }

});




