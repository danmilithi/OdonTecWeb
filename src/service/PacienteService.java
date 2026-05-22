package service;

import dao.PacienteDAO;
import java.util.List;
import model.Paciente;
import repository.CrudRepository;

public class PacienteService {
    private final CrudRepository<Paciente> repository;

    public PacienteService() {
        this(new PacienteDAO());
    }

    public PacienteService(CrudRepository<Paciente> repository) {
        this.repository = repository;
    }

    public void cadastrarPaciente(Paciente paciente) {
        validar(paciente);
        repository.salvar(paciente);
    }

    public List<Paciente> listarPacientes() {
        return repository.listarTodos();
    }

    public void atualizarPaciente(Paciente paciente) {
        validar(paciente);
        repository.atualizar(paciente);
    }

    public void excluirPaciente(int id) {
        repository.deletar(id);
    }

    private void validar(Paciente paciente) {
        ValidacaoService.textoObrigatorio(paciente.getNome(), "Nome");
        ValidacaoService.textoObrigatorio(paciente.getTelefone(), "Telefone");
    }
}
