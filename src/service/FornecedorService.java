package service;

import dao.FornecedorDAO;
import java.util.List;
import model.Fornecedor;
import repository.CrudRepository;

public class FornecedorService {
    private final CrudRepository<Fornecedor> repository;

    public FornecedorService() {
        this(new FornecedorDAO());
    }

    public FornecedorService(CrudRepository<Fornecedor> repository) {
        this.repository = repository;
    }

    public void cadastrarFornecedor(Fornecedor fornecedor) {
        validar(fornecedor);
        repository.salvar(fornecedor);
    }

    public List<Fornecedor> listarFornecedores() {
        return repository.listarTodos();
    }

    public void atualizarFornecedor(Fornecedor fornecedor) {
        validar(fornecedor);
        repository.atualizar(fornecedor);
    }

    public void excluirFornecedor(int id) {
        repository.deletar(id);
    }

    private void validar(Fornecedor fornecedor) {
        ValidacaoService.textoObrigatorio(fornecedor.getNome(), "Nome");
        ValidacaoService.textoObrigatorio(fornecedor.getProduto(), "Produto");
    }
}
