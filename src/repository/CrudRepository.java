package repository;

import java.util.List;

public interface CrudRepository<T> {
    void salvar(T entidade);

    List<T> listarTodos();

    T buscarPorId(int id);

    void atualizar(T entidade);

    void deletar(int id);
}
