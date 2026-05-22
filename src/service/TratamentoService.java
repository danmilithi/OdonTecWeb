package service;

import dao.TratamentoDAO;
import java.util.List;
import model.Tratamento;
import repository.CrudRepository;

public class TratamentoService {
    private final CrudRepository<Tratamento> repository;

    public TratamentoService() {
        this(new TratamentoDAO());
    }

    public TratamentoService(CrudRepository<Tratamento> repository) {
        this.repository = repository;
    }

    public void cadastrarTratamento(Tratamento tratamento) {
        validar(tratamento);
        repository.salvar(tratamento);
    }

    public List<Tratamento> listarTratamentos() {
        return repository.listarTodos();
    }

    public void atualizarTratamento(Tratamento tratamento) {
        validar(tratamento);
        repository.atualizar(tratamento);
    }

    public void excluirTratamento(int id) {
        repository.deletar(id);
    }

    private void validar(Tratamento tratamento) {
        ValidacaoService.textoObrigatorio(tratamento.getTratamento(), "Tratamento");
        ValidacaoService.numeroPositivo(tratamento.getPreco(), "Preco");
        ValidacaoService.numeroPositivo(tratamento.getDuracao(), "Duracao");
    }
}
