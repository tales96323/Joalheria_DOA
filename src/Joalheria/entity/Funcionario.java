package Joalheria.entity;

public class Funcionario {
    protected Long id;
    protected String nome;
    protected String nif;
    protected String dataContrato;
    protected double salario;
    protected double metaVendas;

    // Construtor
    public Funcionario(Long id, String nome, String nif, String dataContrato, double salario, double metaVendas) {
        this.id = id;
        this.nome = nome;
        this.nif = nif;
        this.dataContrato = dataContrato;
        this.salario = salario;
        this.metaVendas = metaVendas;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNif() {
        return nif;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }
    public String getDataContrato() {
        return dataContrato;
    }
    public void setDataContrato(String dataContrato) {
        this.dataContrato = dataContrato;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public double getMetaVendas() {
        return metaVendas;
    }
    public void setMetaVendas(double metaVendas) {
        this.metaVendas = metaVendas;
    }

    // Metodo para ser sobrescrito nas subclasses, retornando detalhes adicionais
    public String detalhesExtras() {
        return null;
    }

    // Representação em String dos detalhes do funcionário
    @Override
    public String toString() {
        return id + "," + nome + "," + nif + "," + dataContrato + "," + salario + "," + metaVendas + "," + detalhesExtras();
    }

    // Subclasse Gerente que herda de Funcionario
    public static class Gerente extends Funcionario {
        public String departamento;

        // Construtor para Gerente, incluindo o atributo adicional 'departamento'
        public Gerente(Long id, String nome, String nif, String dataContrato, double salario, double metaVendas, String departamento) {
            super(id, nome, nif, dataContrato, salario, metaVendas);
            this.departamento = departamento;
        }


        // Getter e Setter para o atributo 'departamento'
        public String getDepartamento() {
            return departamento;
        }

        public void setDepartamento(String departamento) {
            this.departamento = departamento;
        }

        // Sobrescrita do metodo para retornar detalhes adicionais
        @Override
        public String detalhesExtras() {
            return departamento;
        }
    }

    // Subclasse Vendedor que herda de Funcionario
    public static class Vendedor extends Funcionario {
        public double comissao; // Comissão sobre vendas

        // Construtor para Vendedor, incluindo o atributo adicional 'comissao'
        public Vendedor(Long id, String nome, String nif, String dataContrato, double salario, double metaVendas, double comissao) {
            super(id, nome, nif, dataContrato, salario, metaVendas);
            this.comissao = comissao;
        }

        // Getter e Setter para o atributo 'comissao'
        public double getComissao() {
            return comissao;
        }

        public void setComissao(double comissao) {
            this.comissao = comissao;
        }

        // Sobrescrita do metodo para retornar detalhes adicionais
        @Override
        public String detalhesExtras() {
            return String.valueOf(comissao);
        }
    }
}