package Joalheria.repository;

import Joalheria.entity.Joia;
import Joalheria.entity.Colar;
import Joalheria.entity.Brinco;
import Joalheria.entity.Anel;
import Joalheria.exception.EntityAlreadyExistsException;
import Joalheria.exception.EntityNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class JoiaRepository {

    // Caminho do arquivo onde as joias são armazenadas
    private static final String ARQUIVO_JOIAS = "joias.csv";

    // Lista que mantém as joias carregadas em memória
    private final List<Joia> joias;

    // Construtor que carrega as joias do arquivo para a memória ao iniciar o repositório
    public JoiaRepository() {
        this.joias = new ArrayList<>();
        carregarJoiasDoArquivo();
    }

    // Metodo para carregar as joias do arquivo CSV
    private void carregarJoiasDoArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_JOIAS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 8) {
                    Long id = Long.parseLong(dados[0]);
                    String nome = dados[1];
                    String tipo = dados[2];
                    String material = dados[3];
                    double peso = Double.parseDouble(dados[4]);
                    double preco = Double.parseDouble(dados[5]);
                    int quantidadeEstoque = Integer.parseInt(dados[6]);
                    String classificacao = dados[7];

                    switch (tipo.toLowerCase()) {
                        case "colar":
                            double comprimento = Double.parseDouble(dados[8]);
                            joias.add(new Colar(id, nome, material, peso, preco, quantidadeEstoque, classificacao, comprimento));
                            break;
                        case "brinco":
                            String tipoDeFecho = dados[8];
                            joias.add(new Brinco(id, nome, material, peso, preco, quantidadeEstoque, classificacao, tipoDeFecho));
                            break;
                        case "anel":
                            int tamanho = Integer.parseInt(dados[8]);
                            joias.add(new Anel(id, nome, material, peso, preco, quantidadeEstoque, classificacao, tamanho));
                            break;
                        default:
                            System.out.println("Tipo de joia desconhecido: " + tipo);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de joias não encontrado. Um novo será criado quando joias forem adicionadas.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo para salvar todas as joias em memória no arquivo
    private void salvarJoiasNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_JOIAS))) {
            for (Joia joia : joias) {
                bw.write(joiaParaString(joia) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo auxiliar para converter uma joia em formato de string para salvar no arquivo
    private String joiaParaString(Joia joia) {
        StringBuilder sb = new StringBuilder();
        sb.append(joia.getId()).append(",")
                .append(joia.getNome()).append(",")
                .append(joia.getTipo()).append(",")
                .append(joia.getMaterial()).append(",")
                .append(joia.getPeso()).append(",")
                .append(joia.getPreco()).append(",")
                .append(joia.getQuantidadeEstoque()).append(",")
                .append(joia.getClassificacao());

        // Adiciona os atributos extras com base no tipo
        if (joia instanceof Colar) {
            sb.append(",").append(((Colar) joia).getComprimento());
        } else if (joia instanceof Brinco) {
            sb.append(",").append(((Brinco) joia).getTipoDeFecho());
        } else if (joia instanceof Anel) {
            sb.append(",").append(((Anel) joia).getTamanho());
        }
        return sb.toString();
    }

    // Metodo para adicionar uma nova joia ao repositório
    public void adicionarJoia(Joia joia) throws EntityAlreadyExistsException {
        if (buscarJoiaPorId(joia.getId()) != null) {
            throw new EntityAlreadyExistsException("Joia com ID " + joia.getId() + " já existe.");
        }
        joias.add(joia);
        salvarJoiasNoArquivo(); // Persiste as alterações no arquivo
    }

    // Metodo para buscar uma joia por seu ID
    public Joia buscarJoiaPorId(Long id) {
        for (Joia joia : joias) {
            if (joia.getId().equals(id)) {
                return joia;
            }
        }
        return null; // Retorna null se a joia não for encontrada
    }

    // Metodo para listar todas as joias
    public List<Joia> listarJoias() {
        return new ArrayList<>(joias); // Retorna uma cópia da lista de joias
    }

    // Metodo para atualizar uma joia existente
    public void atualizarJoia(Joia joiaAtualizada) throws EntityNotFoundException {
        Joia joiaExistente = buscarJoiaPorId(joiaAtualizada.getId());
        if (joiaExistente == null) {
            throw new EntityNotFoundException("Joia com ID " + joiaAtualizada.getId() + " não encontrada.");
        }
        // Atualiza os campos da joia existente com os dados fornecidos
        joiaExistente.setNome(joiaAtualizada.getNome());
        joiaExistente.setMaterial(joiaAtualizada.getMaterial());
        joiaExistente.setPeso(joiaAtualizada.getPeso());
        joiaExistente.setPreco(joiaAtualizada.getPreco());
        joiaExistente.setQuantidadeEstoque(joiaAtualizada.getQuantidadeEstoque());
        joiaExistente.setClassificacao(joiaAtualizada.getClassificacao());

        // Atualiza atributos específicos se necessário
        if (joiaExistente instanceof Colar && joiaAtualizada instanceof Colar) {
            ((Colar) joiaExistente).setComprimento(((Colar) joiaAtualizada).getComprimento());
        } else if (joiaExistente instanceof Brinco && joiaAtualizada instanceof Brinco) {
            ((Brinco) joiaExistente).setTipoDeFecho(((Brinco) joiaAtualizada).getTipoDeFecho());
        } else if (joiaExistente instanceof Anel && joiaAtualizada instanceof Anel) {
            ((Anel) joiaExistente).setTamanho(((Anel) joiaAtualizada).getTamanho());
        }
        salvarJoiasNoArquivo(); // Persiste as alterações no arquivo
    }

    // Metodo para remover uma joia por seu ID
    public void removerJoia(Long id) throws EntityNotFoundException {
        Joia joia = buscarJoiaPorId(id);
        if (joia == null) {
            throw new EntityNotFoundException("Joia com ID " + id + " não encontrada.");
        }
        joias.remove(joia);
        salvarJoiasNoArquivo(); // Persiste as alterações no arquivo
    }
}