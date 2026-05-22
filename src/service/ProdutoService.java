package service;

import dao.ProdutoDAO;
import java.util.List;
import model.Produto;
import repository.CrudRepository;

public class ProdutoService {
    private final CrudRepository<Produto> repository;

    public ProdutoService() {
        this(new ProdutoDAO());
    }

    public ProdutoService(CrudRepository<Produto> repository) {
        this.repository = repository;
    }

    public void cadastrarProduto(Produto produto) {
        validar(produto);
        repository.salvar(produto);
    }

    public List<Produto> listarProdutos() {
        return repository.listarTodos();
    }

    public void atualizarProduto(Produto produto) {
        validar(produto);
        repository.atualizar(produto);
    }

    public void excluirProduto(int id) {
        repository.deletar(id);
    }

    private void validar(Produto produto) {
        ValidacaoService.textoObrigatorio(produto.getNome(), "Nome");
        ValidacaoService.textoObrigatorio(produto.getFornecedor(), "Fornecedor");
        ValidacaoService.numeroPositivo(produto.getPreco(), "Preco");
        ValidacaoService.numeroNaoNegativo(produto.getQuantidade(), "Quantidade");
    }
}
