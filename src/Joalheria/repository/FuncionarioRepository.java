package Joalheria.repository;

import Joalheria.entity.Funcionario;
import Joalheria.exception.EntityAlreadyExistsException;
import Joalheria.exception.EntityNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioRepository {

    // Caminho do arquivo onde os funcionários são armazenados
    private static final String ARQUIVO_FUNCIONARIOS = "funcionarios.csv";

    // Lista que mantém os funcionários carregados em memória
    private List<Funcionario> funcionarios;

    // Construtor que carrega os funcionários do arquivo para a memória ao iniciar o repositório
    public FuncionarioRepository() {
        this.funcionarios = new ArrayList<>();
        try {
            verificarECriarArquivo();
            carregarFuncionariosDoArquivo();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao inicializar o repositório de funcionários: " + e.getMessage());
        }
    }

    // Verifica se o arquivo existe, e cria o arquivo vazio se não existir
    private void verificarECriarArquivo() throws IOException {
        File arquivo = new File(ARQUIVO_FUNCIONARIOS);
        if (!arquivo.exists()) {
            // Se o arquivo não existir, cria um novo arquivo
            arquivo.createNewFile();
            System.out.println("Arquivo 'funcionarios.csv' criado.");
        }
    }

    // Metodo para carregar funcionários do arquivo CSV
    private void carregarFuncionariosDoArquivo() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_FUNCIONARIOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(","); // Ajuste conforme o delimitador do CSV (ex: vírgula)
                if (campos.length < 7) {
                    System.err.println("Linha com dados insuficientes: " + linha);
                    continue; // Pula para a próxima linha se houver dados insuficientes
                }

                try {
                    // Parse dos campos básicos para todos os funcionários
                    Long id = Long.parseLong(campos[0]);
                    String nome = campos[1];
                    String nif = campos[2];
                    String dataContrato = campos[3];
                    double salario = Double.parseDouble(campos[4]);
                    double metaVendas = Double.parseDouble(campos[5]);
                    String cargo = campos[6];

                    // Aqui você deve instanciar e adicionar o funcionário de acordo com o tipo
                    Funcionario funcionario;
                    if (cargo.equalsIgnoreCase("Gerente")) {
                        funcionario = new Funcionario.Gerente(id, nome, nif, dataContrato, salario, metaVendas, campos[7]);
                    } else if (cargo.equalsIgnoreCase("Vendedor")) {
                        funcionario = new Funcionario.Vendedor(id, nome, nif, dataContrato, salario, metaVendas, Double.parseDouble(campos[7]));
                    } else {
                        funcionario = new Funcionario(id, nome, nif, dataContrato, salario, metaVendas);
                    }

                    funcionarios.add(funcionario);
                } catch (NumberFormatException e) {
                    System.err.println("Erro de formatação ao ler os dados da linha: " + linha + " - " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Erro inesperado ao carregar funcionário: " + e.getMessage());
                }
            }
        }
    }

    // Metodo para salvar todos os funcionários em memória no arquivo
    private void salvarFuncionariosNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_FUNCIONARIOS))) {
            for (Funcionario funcionario : funcionarios) {
                bw.write(funcionarioParaString(funcionario) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo auxiliar para converter um funcionário em formato de string para salvar no arquivo
    private String funcionarioParaString(Funcionario funcionario) {
        if (funcionario instanceof Funcionario.Gerente) {
            Funcionario.Gerente gerente = (Funcionario.Gerente) funcionario;
            return gerente.getId() + "," + gerente.getNome() + "," + gerente.getNif() + "," +
                    gerente.getDataContrato() + "," + gerente.getSalario() + "," +
                    gerente.getMetaVendas() + "," + "Gerente" + "," + gerente.getDepartamento();
        } else if (funcionario instanceof Funcionario.Vendedor) {
            Funcionario.Vendedor vendedor = (Funcionario.Vendedor) funcionario;
            return vendedor.getId() + "," + vendedor.getNome() + "," + vendedor.getNif() + "," +
                    vendedor.getDataContrato() + "," + vendedor.getSalario() + "," +
                    vendedor.getMetaVendas() + "," + "Vendedor" + "," + vendedor.getComissao();
        } else {
            // Caso padrão para funcionário
            return funcionario.getId() + "," + funcionario.getNome() + "," + funcionario.getNif() + "," +
                    funcionario.getDataContrato() + "," + funcionario.getSalario() + "," +
                    funcionario.getMetaVendas() + "," + "Funcionario";
        }
    }

    // Metodo para adicionar um funcionário ao repositório
    public void adicionarFuncionario(Funcionario funcionario) throws EntityAlreadyExistsException {
        if (buscarFuncionarioPorId(funcionario.getId()) != null) {
            throw new EntityAlreadyExistsException("Funcionário com ID " + funcionario.getId() + " já existe.");
        }
        funcionarios.add(funcionario);
        salvarFuncionariosNoArquivo(); // Persiste as alterações no arquivo
    }

    // Metodo para buscar um funcionário por seu ID
    public Funcionario buscarFuncionarioPorId(Long id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId().equals(id)) {
                return funcionario;
            }
        }
        return null; // Retorna null se o funcionário não for encontrado
    }

    // Metodo para listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios); // Retorna uma cópia da lista de funcionários
    }

    // Metodo para atualizar um funcionário existente
    public void atualizarFuncionario(Funcionario funcionarioAtualizado) throws EntityNotFoundException {
        Funcionario funcionarioExistente = buscarFuncionarioPorId(funcionarioAtualizado.getId());
        if (funcionarioExistente == null) {
            throw new EntityNotFoundException("Funcionário com ID " + funcionarioAtualizado.getId() + " não encontrado.");
        }

        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setNif(funcionarioAtualizado.getNif());
        funcionarioExistente.setDataContrato(funcionarioAtualizado.getDataContrato());
        funcionarioExistente.setSalario(funcionarioAtualizado.getSalario());
        funcionarioExistente.setMetaVendas(funcionarioAtualizado.getMetaVendas());

        // Esse trecho faz a distinção das subclasses Gerente e vendedor para que um vendedor não seja capaz de atualizar os dados.
        if (funcionarioExistente instanceof Funcionario.Gerente && funcionarioAtualizado instanceof Funcionario.Gerente) {
            ((Funcionario.Gerente) funcionarioExistente).setDepartamento(((Funcionario.Gerente) funcionarioAtualizado).getDepartamento());
        } else if (funcionarioExistente instanceof Funcionario.Vendedor && funcionarioAtualizado instanceof Funcionario.Vendedor) {
            ((Funcionario.Vendedor) funcionarioExistente).setComissao(((Funcionario.Vendedor) funcionarioAtualizado).getComissao());
        }
        salvarFuncionariosNoArquivo(); // Persiste as alterações no arquivo
    }

    // Metodo para remover um funcionário por seu ID
    public void removerFuncionario(Long id) throws EntityNotFoundException {
        Funcionario funcionario = buscarFuncionarioPorId(id);
        if (funcionario == null) {
            throw new EntityNotFoundException("Funcionário com ID " + id + " não encontrado.");
        }
        funcionarios.remove(funcionario);
        salvarFuncionariosNoArquivo();
    }
}
