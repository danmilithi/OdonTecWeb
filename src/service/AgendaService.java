package service;

import dao.AgendaDAO;
import java.util.List;
import model.Agenda;
import repository.CrudRepository;

public class AgendaService {
    private final CrudRepository<Agenda> repository;

    public AgendaService() {
        this(new AgendaDAO());
    }

    public AgendaService(CrudRepository<Agenda> repository) {
        this.repository = repository;
    }

    public void cadastrarAgendamento(Agenda agenda) {
        validar(agenda);
        repository.salvar(agenda);
    }

    public List<Agenda> listarAgendamentos() {
        return repository.listarTodos();
    }

    public void atualizarAgendamento(Agenda agenda) {
        validar(agenda);
        repository.atualizar(agenda);
    }

    public void excluirAgendamento(int id) {
        repository.deletar(id);
    }

    private void validar(Agenda agenda) {
        ValidacaoService.textoObrigatorio(agenda.getPaciente(), "Paciente");
        ValidacaoService.textoObrigatorio(agenda.getDentista(), "Dentista");
        ValidacaoService.textoObrigatorio(agenda.getData(), "Data");
        ValidacaoService.textoObrigatorio(agenda.getHora(), "Hora");
        ValidacaoService.textoObrigatorio(agenda.getTratamento(), "Tratamento");
    }
}
