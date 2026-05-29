const formFornecedor = document.getElementById('formFornecedor');

const resultadoFornecedor = document.getElementById('resultadoFornecedor');

formFornecedor.addEventListener('submit', function(event){

    event.preventDefault();

    const empresa = document.getElementById('empresa').value;

    const cnpj = document.getElementById('cnpj').value;

    const telefoneFornecedor = document.getElementById('telefoneFornecedor').value;

    if(empresa === '' || cnpj === '' || telefoneFornecedor === ''){

        resultadoFornecedor.innerHTML = 'Preencha todos os campos!';

        resultadoFornecedor.style.color = 'red';

    }else{

        resultadoFornecedor.innerHTML = 'Fornecedor cadastrado com sucesso!';

        resultadoFornecedor.style.color = 'green';

        formFornecedor.reset();
    }

});





