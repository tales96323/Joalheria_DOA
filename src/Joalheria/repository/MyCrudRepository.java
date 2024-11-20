package Joalheria.repository;

import java.util.List;
import java.util.Optional;

public interface MyCrudRepository<T> {
    void salvar(T t);
    List<T> listarTodos();
    Optional<T> buscarPorId(String id);
    void atualizar(T t);
    void remover(String id);
}