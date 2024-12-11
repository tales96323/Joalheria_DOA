# Banco de Dados de Joalheria

Este repositório contém os artefatos principais relacionados ao banco de dados de uma joalheria. Ele foi desenvolvido como parte de um projeto para modelar e implementar a estrutura de dados necessária para gerenciar informações de clientes, funcionários, fornecedores, joias, pedidos e pagamentos.

## Conteúdo do Repositório

O repositório inclui os seguintes arquivos:

1. **`DOA_db.sql`**: Contém todo o código SQL necessário para criar e popular as tabelas do banco de dados. Este arquivo inclui:
   - Criação de tabelas com chaves primárias, estrangeiras e restrições.
   - Inserção de dados de exemplo para testes e validação do modelo.
   - Relacionamentos entre tabelas, incluindo referências a IDs de outras tabelas e uso de arrays para associações.

2. **`Diagrama DOA Store.pdf`**: Um diagrama Entidade-Relacionamento (ER) gerado a partir do modelo de dados implementado no arquivo SQL. Este diagrama ajuda a visualizar as tabelas e seus relacionamentos, facilitando o entendimento do banco de dados.

## Como Usar Este Repositório

### Executando o Script SQL
1. Certifique-se de ter o PostgreSQL instalado em sua máquina.
2. Abra o terminal ou uma ferramenta de administração do PostgreSQL (como o pgAdmin ou DBeaver).
3. Crie um novo banco de dados:
   ```sql
   CREATE DATABASE DOA_db;
   ```

### Visualizando o Diagrama ER
- Abra o arquivo `Diagrama DOA Store.pdf` em um visualizador de PDF para entender a estrutura do banco de dados.
- Use o diagrama como referência para navegar pelas tabelas e seus relacionamentos.
- Existe uma visulização de um diagrama em outro software web chamado dbdiagram.io. O link para vislização do diagrama é: https://dbdiagram.io/d/Diagrama-DOA-Store-674b72ebe9daa85aca3ba38c
