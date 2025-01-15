# DOA Jewelry

Um sistema de gerenciamento de joalheria baseado em Spring Boot e implementado em Java. Este projeto demonstra os principais conceitos de arquitetura de software, incluindo arquitetura MVC, camadas de serviço, repositórios, DTOs e tratamento de exceções personalizadas. Também inclui testes abrangentes de controladores, serviços e entidades.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

```
src
├── doa_jewelry/
│   ├── controller/
│   │   ├── CustomerController.java         # Controlador para operações com Clientes
│   │   ├── EmployeeController.java         # Controlador para operações com Empregados
│   │   ├── JewelryController.java          # Controlador que gerencia as operações das Joias
│   │   ├── OrderController.java            # Controlador para operações com Pedidos
│   │   └── PaymentController.java          # Controlador para operações com Pagamentos
│   ├── dto/
│   │   ├── CustomerDTO.java                # DTO para Cliente
│   │   ├── EmployeeDTO.java                # DTO para Empregados
│   │   ├── JewelryDTO.java                 # DTO para Joias
│   │   ├── OrderDTO.java                   # DTO para Pedidos
│   │   ├── OrderWithPayments.java          # DTO para Pedidos com Pagamentos
│   │   └── PaymentDTO.java                 # DTO para Pagamentos
│   ├── entity/   
│   │   ├── Customer.java                   # Classe entidade Cliente
│   │   ├── Earring.java                    # Classe entidade Brinco
│   │   ├── Employee.java                   # Classe entidade Empregado
│   │   ├── Jewelry.java                    # Classe entidade Joia
│   │   ├── JewelryCategory.java            # Classe para Categorias de Joias
│   │   ├── Manager.java                    # Classe entidade Gerente
│   │   ├── MaterialType.java               # Classe para Tipos de Material
│   │   ├── Necklace.java                   # Classe entidade Colar
│   │   ├── Order.java                      # Classe entidade Pedido
│   │   ├── OrderItem.java                  # Classe para Itens do Pedido
│   │   ├── OrderStatus.java                # Classe para Status do Pedido
│   │   ├── Payment.java                    # Classe entidade Pagamento
│   │   ├── PaymentMethod.java              # Classe para Métodos de Pagamento
│   │   ├── Ring.java                       # Classe entidade Anel
│   │   └── Salesperson.java                # Classe entidade Vendedor
│   ├── exception/
│   │   ├── EntityAlreadyExistsException.java # Exceção para entidades duplicadas
│   │   ├── EntityNotFoundException.java    # Exceção para entidades não encontradas
│   │   ├── InsufficientUnitsException.java # Exceção para estoque insuficiente
│   │   └── RepositoryException.java        # Exceção para erros no repositório
│   ├── repository/
│   │   ├── CustomerRepository.java         # Repositório para entidades Cliente
│   │   ├── EmployeeRepository.java         # Repositório para entidades Empregado
│   │   ├── JewelryRepository.java          # Repositório para entidades Joia
│   │   ├── OrderRepository.java            # Repositório para entidades Pedido
│   │   └── PaymentRepository.java          # Repositório para entidades Pagamento
│   ├── service/
│   │   ├── CustomerService.java            # Camada de serviço para operações de Cliente
│   │   ├── EmployeeService.java            # Camada de serviço para operações de Empregado
│   │   ├── JewelryService.java             # Camada de serviço para operações de Joias
│   │   ├── OrderService.java               # Camada de serviço para operações de Pedidos
│   │   └── PaymentService.java             # Camada de serviço para operações de Pagamentos
│   ├── startup/
│   │   └── StartupInitializer.java         # Inicializa a aplicação com dados de exemplo
│   └── DoaJewelryApplication.java          # Ponto de entrada principal da aplicação
└── test/ (serão implementados futuramente)
    ├── java/doa_jewelry/
    │   ├── controller/
    │   │   ├── CustomerControllerTest.java # Testes unitários para CustomerController
    │   │   ├── EmployeeControllerTest.java # Testes unitários para EmployeeController
    │   │   ├── JewelryControllerTest.java  # Testes unitários para JewelryController
    │   │   ├── OrderControllerTest.java    # Testes unitários para OrderController
    │   │   └── PaymentControllerTest.java  # Testes unitários para PaymentController
    │   ├── entity/
    │   │   ├── CustomerTest.java           # Testes unitários para a entidade Cliente
    │   │   ├── EarringTest.java            # Testes unitários para a entidade Brinco
    │   │   ├── JewelryTest.java            # Testes unitários para a entidade Joia
    │   │   ├── OrderTest.java              # Testes unitários para a entidade Pedido
    │   │   └── PaymentTest.java            # Testes unitários para a entidade Pagamento
    │   └── service/
    │       ├── CustomerServiceTest.java    # Testes unitários para CustomerService
    │       ├── EmployeeServiceTest.java    # Testes unitários para EmployeeService
    │       ├── JewelryServiceTest.java     # Testes unitários para JewelryService
    │       ├── OrderServiceTest.java       # Testes unitários para OrderService
    │       └── PaymentServiceTest.java     # Testes unitários para PaymentService
```

## Principais Funcionalidades

- **Arquitetura MVC do Spring Boot**: O projeto segue o padrão Model-View-Controller, onde:
    - O `controller` gerencia as requisições e a comunicação entre a view e o model.
    - A camada `service` contém a lógica de negócio.
    - A camada `repository` gerencia o armazenamento e a recuperação de dados.
- **Classes de Entidade e DTOs**: Uso de Data Transfer Objects (`DTOs`) para comunicação e classes `entity` para modelagem de dados.
- **Exceções Personalizadas**: Inclui tratamento de exceções personalizadas para cenários de erro comuns, como duplicidade de entidades ou estoque insuficiente.
- **Testes Unitários**: Testes abrangentes para controladores, serviços e entidades usando JUnit 5.

## Início Rápido

### Pré-requisitos

- Java Development Kit (JDK) 8 ou superior
- IntelliJ IDEA ou outra IDE compatível com Java
- [JUnit 5](https://junit.org/junit5/) para testes

### Configuração

1. **Clone o Repositório**
   ```bash
   git clone https://github.com/tales96323/doa_jewelry.git
   ```

2. **Importe o Projeto**
    - Abra o IntelliJ IDEA (ou sua IDE preferida).
    - Importe o projeto como um projeto existente.

3. **Executando a Aplicação**
    - Use o ponto de entrada principal `DoaJewelryApplication.java` para iniciar a aplicação Spring Boot.

4. **Testes**
    - Os testes serão implementados no futuro.

## Uso

O sistema de gerenciamento da joalheria suporta operações básicas como adicionar, atualizar e recuperar joias e clientes, além de gerenciar pedidos. Este projeto foi projetado para fornecer uma base para desenvolvimento adicional e exercícios.

### Exemplos de Operações

- **Adicionar uma Nova Joia**: Use o `JewelryController` para adicionar e gerenciar joias no estoque.
- **Atualizar o Status de um Pedido**: Atualize o status de um pedido usando o `OrderService`.
- **Calcular Vendas Totais por Material**: Agregue informações de vendas por material das joias.

## Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

## Contribuições

1. Faça um fork do repositório.
2. Crie um novo branch (`git checkout -b feature-branch`).
3. Commit suas alterações (`git commit -m 'Adiciona nova funcionalidade'`).
4. Envie para o branch (`git push origin feature-branch`).
5. Abra um pull request.

## Contato

Para dúvidas, entre em contato pelo e-mail [tales96323@gmail.com](mailto:tales96323@gmail

