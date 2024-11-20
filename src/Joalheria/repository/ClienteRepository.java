package Joalheria.repository;

import Joalheria.entity.Cliente;
import Joalheria.exception.EntityAlreadyExistsException;
import Joalheria.exception.EntityNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteRepository {

    // Caminho do arquivo onde os clientes são armazenados
    private static final String ARQUIVO_CLIENTES = "clientes.csv";

    // Lista que mantém os clientes carregados em memória
    private List<Cliente> clientes;

    // Construtor que carrega os clientes do arquivo para a memória ao iniciar o repositório
    public ClienteRepository() {
        this.clientes = new ArrayList<>();
        carregarClientesDoArquivo();
    }

    // Metodo para carregar clientes do arquivo CSV
    private void carregarClientesDoArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CLIENTES))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 6) {
                    Cliente cliente = new Cliente(
                            (long) Integer.parseInt(dados[0]),
                            dados[1],
                            dados[2],
                            dados[3],
                            dados[4],
                            dados[5]
                    );
                    clientes.add(cliente);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de clientes não encontrado. Um novo será criado quando clientes forem adicionados.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo para salvar todos os clientes em memória no arquivo
    private void salvarClientesNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_CLIENTES))) {
            for (Cliente cliente : clientes) {
                bw.write(cliente.getId() + "," + cliente.getNome() + "," + cliente.getNif() + ","
                        + cliente.getEmail() + "," + cliente.getTelefone() + "," + cliente.getEndereco() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo para adicionar um cliente ao repositório
    public void adicionarCliente(Cliente cliente) throws EntityAlreadyExistsException {
        if (buscarClientePorId(cliente.getId()) != null) {
            throw new EntityAlreadyExistsException("Cliente com ID " + cliente.getId() + " já existe.");
        }
        clientes.add(cliente);
        salvarClientesNoArquivo();
    }

    // Metodo para buscar um cliente por seu ID
    public Cliente buscarClientePorId(Long id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    // Metodo para listar todos os clientes
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }


    // Metodo para atualizar um cliente existente
    public void atualizarCliente(Cliente clienteAtualizado) throws EntityNotFoundException {
        Cliente clienteExistente = buscarClientePorId(clienteAtualizado.getId());
        if (clienteExistente == null) {
            throw new EntityNotFoundException("Cliente com ID " + clienteAtualizado.getId() + " não encontrado.");
        }
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setNif(clienteAtualizado.getNif());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());
        salvarClientesNoArquivo();
    }

    // Metodo para remover um cliente por seu ID
    public void removerCliente(Long id) throws EntityNotFoundException {
        Cliente cliente = buscarClientePorId(id);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente com ID " + id + " não encontrado.");
        }
        clientes.remove(cliente);
        salvarClientesNoArquivo();
    }

    // Metodo para buscar um cliente por seu NIF
    public Cliente buscarClientePorNif(String nif) {
        for (Cliente cliente : clientes) {
            if (cliente.getNif().equals(nif)) {
                return cliente;
            }
        }
        return null;
    }

}