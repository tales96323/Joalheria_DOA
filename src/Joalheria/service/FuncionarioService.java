package Joalheria.service;

import Joalheria.entity.Funcionario;
import Joalheria.exception.EntityAlreadyExistsException;
import Joalheria.exception.EntityNotFoundException;
import Joalheria.repository.FuncionarioRepository;

import java.util.List;


public class FuncionarioService {

    // Dependência do repositório de funcionários para manipulação dos dados
    public FuncionarioRepository funcionarioRepository;

    // Construtor que inicializa o repositório
    public FuncionarioService() {
        this.funcionarioRepository = new FuncionarioRepository();
    }

    // Metodo para adicionar um novo funcionário ao sistema
    public void adicionarFuncionario(Funcionario funcionario) {
        try {
            funcionarioRepository.adicionarFuncionario(funcionario);
            System.out.println("Funcionário adicionado com sucesso: " + funcionario.getNome());
        } catch (EntityAlreadyExistsException e) {
            System.err.println("Erro ao adicionar funcionário: " + e.getMessage());
        }
    }

    // Metodo para buscar um funcionário pelo ID
    public Funcionario buscarFuncionarioPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.buscarFuncionarioPorId(id);
        if (funcionario == null) {
            System.out.println("Funcionário com ID " + id + " não encontrado.");
        }
        return funcionario;
    }

    // Metodo para listar todos os funcionários cadastrados
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.listarFuncionarios();
    }



    // --- APLICAR DEPOIS ---
    // Metodo para atualizar os dados de um funcionário existente
    public void atualizarFuncionario(Funcionario funcionarioAtualizado) {
        try {
            funcionarioRepository.atualizarFuncionario(funcionarioAtualizado);
            System.out.println("Funcionário atualizado com sucesso: " + funcionarioAtualizado.getNome());
        } catch (EntityNotFoundException e) {
            System.err.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }
    // Metodo para remover um funcionário com base no ID
    public void removerFuncionario(Long id) {
        try {
            funcionarioRepository.removerFuncionario(id);
            System.out.println("Funcionário removido com sucesso com ID: " + id);
        } catch (EntityNotFoundException e) {
            System.err.println("Erro ao remover funcionário: " + e.getMessage());
        }
    }
}