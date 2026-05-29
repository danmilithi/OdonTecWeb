const formProduto = document.getElementById('formProduto');

const resultadoProduto = document.getElementById('resultadoProduto');

formProduto.addEventListener('submit', function(event){

    event.preventDefault();

    const produto = document.getElementById('produto').value;

    const quantidade = document.getElementById('quantidade').value;

    const preco = document.getElementById('preco').value;

    if(produto === '' || quantidade === '' || preco === ''){

        resultadoProduto.innerHTML = 'Preencha todos os campos!';

        resultadoProduto.style.color = 'red';

    }else{

        resultadoProduto.innerHTML = 'Produto cadastrado com sucesso!';

        resultadoProduto.style.color = 'green';

        formProduto.reset();
    }

});



