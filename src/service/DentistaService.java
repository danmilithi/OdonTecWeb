package service;

import dao.DentistaDAO;
import java.util.List;
import model.Dentista;
import repository.CrudRepository;

public class DentistaService {
    private final CrudRepository<Dentista> repository;

    public DentistaService() {
        this(new DentistaDAO());
    }

    public DentistaService(CrudRepository<Dentista> repository) {
        this.repository = repository;
    }

    public void cadastrarDentista(Dentista dentista) {
        validar(dentista);
        repository.salvar(dentista);
    }

    public List<Dentista> listarDentistas() {
        return repository.listarTodos();
    }

    public void atualizarDentista(Dentista dentista) {
        validar(dentista);
        repository.atualizar(dentista);
    }

    public void excluirDentista(int id) {
        repository.deletar(id);
    }

    private void validar(Dentista dentista) {
        ValidacaoService.textoObrigatorio(dentista.getNome(), "Nome");
        ValidacaoService.textoObrigatorio(dentista.getEspecialidade(), "Especialidade");
    }
}
