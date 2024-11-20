package Joalheria.service;

import Joalheria.entity.Joia;
import Joalheria.exception.EntityAlreadyExistsException;
import Joalheria.exception.EntityNotFoundException;
import Joalheria.repository.JoiaRepository;

import java.util.List;


public class JoiaService {

    // Dependência do repositório de joias para manipulação dos dados
    public JoiaRepository joiaRepository;

    // Construtor que inicializa o repositório
    public JoiaService() {
        this.joiaRepository = new JoiaRepository();
    }

    // Metodo para adicionar uma nova joia ao sistema
    public void adicionarJoia(Joia joia) {
        try {
            joiaRepository.adicionarJoia(joia);
            System.out.println("Joia adicionada com sucesso: " + joia.getNome());
        } catch (EntityAlreadyExistsException e) {
            System.err.println("Erro ao adicionar joia: " + e.getMessage());
        }
    }

    // Metodo para buscar uma joia pelo ID
    public Joia buscarJoiaPorId(Long id) {
        Joia joia = joiaRepository.buscarJoiaPorId(id);
        if (joia == null) {
            System.out.println("Joia com ID " + id + " não encontrada.");
        }
        return joia;
    }

    // Metodo para listar todas as joias cadastradas
    public List<Joia> listarJoias() {
        return joiaRepository.listarJoias();
    }


    // Metodo para atualizar os dados de uma joia existente
    public void atualizarJoia(Joia joiaAtualizada) {
        try {
            joiaRepository.atualizarJoia(joiaAtualizada);
            System.out.println("Joia atualizada com sucesso: " + joiaAtualizada.getNome());
        } catch (EntityNotFoundException e) {
            System.err.println("Erro ao atualizar joia: " + e.getMessage());
        }
    }

    // Metodo para remover uma joia com base no ID
    public void removerJoia(Long id) {
        try {
            joiaRepository.removerJoia(id);
            System.out.println("Joia removida com sucesso com ID: " + id);
        } catch (EntityNotFoundException e) {
            System.err.println("Erro ao remover joia: " + e.getMessage());
        }
    }

}