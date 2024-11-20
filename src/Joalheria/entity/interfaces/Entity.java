package Joalheria.entity.interfaces;

/**
 * Interface base para todas as entidades do sistema.
 * Esta interface define métodos comuns que todas as entidades devem implementar,
 * como o identificador único (ID) e a comparação entre entidades.
 */
public interface Entity<ID> {

    /**
     * Obtém o identificador único da entidade.
     * Cada entidade deve ter um identificador único (ID) para ser identificada de forma única.
     *
     * @return o identificador único da entidade
     */
    ID getId();

    /**
     * Define o identificador único da entidade.
     * Este metodo é usado para atribuir um ID único à entidade, normalmente
     * durante a criação ou atualização da entidade.
     *
     * @param id o identificador único a ser atribuído
     */
    void setId(ID id);
}