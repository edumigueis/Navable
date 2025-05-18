# Navable - Navegação Inclusiva

Este repositório contém o código da aplicação **Acessibilidade Urbana**, desenvolvida pelo grupo **Honolulu** da disciplina MC536 – Banco de Dados: Teoria e Prática, do Instituto de Computação da UNICAMP. O objetivo da aplicação é oferecer suporte à locomoção de pessoas com deficiência e/ou necessidades especiais, permitindo a consulta e avaliação de estabelecimentos acessíveis na cidade além da criação de avisos de acessibilidade pela cidade.

## Sumário

- [Descrição da Aplicação](#descrição-da-aplicação)
- [Estrutura do Repositório](#estrutura-do-repositório)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Como Executar a Aplicação](#como-executar-a-aplicação)
- [Arquitetura e Funcionalidades](#arquitetura-e-funcionalidades)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

## Descrição da Aplicação

A **Navable** é uma aplicação que tem como principal objetivo facilitar a locomoção e a busca por locais acessíveis para pessoas com deficiência ou necessidades especiais. A aplicação permite que os usuários:

- Se cadastrem no sistema e escolham suas necessidades de acessibilidade.
- Avaliem estabelecimentos e ocorrências de acessibilidade em diferentes locais.
- Consultem um mapa interativo de estabelecimentos acessíveis, com filtros baseados nas necessidades individuais do usuário.
- Registrem e acompanhem ocorrências relacionadas à acessibilidade em espaços públicos e privados.

O sistema é composto por um frontend que interage com a API backend para acessar e manipular os dados no banco de dados. A aplicação permite a personalização da experiência, com base nas necessidades de acessibilidade do usuário, garantindo uma experiência inclusiva.

## Estrutura do Repositório

Este repositório está organizado da seguinte forma:

- **docker/**: Contém arquivos de configuração para rodar a aplicação em containers Docker.
- **navable/**: Diretório que contém o código do frontend, que é responsável pela interface de usuário (UI) e interação com a API.
- **navable-api/**: Diretório que contém o código do backend (API), que gerencia as requisições e interage com o banco de dados.

## Arquitetura do Sistema

- diagrama

### Arquitetura Controller-Service-Repository

- O backend utiliza uma variação da arquitetura MVC, isolando a lógica de domínio das interações externas.
- Os Controllers atuam como a camada de entrada, enquanto os Repositories funcionam como a camada de saída.
- Entre elas temos os services, que encapsulam a lógica de negócios central, independente de infraestrutura externa.

### Repository Pattern

- Implementado explicitamente no backend, abstraindo o acesso aos dados e permitindo a troca de tecnologias de persistência sem afetar a lógica de negócios.
- Repositories expõem interfaces de alto nível para manipular as entidades, ocultando detalhes da implementação do banco de dados.

### Dependency Injection

- Utilizado extensivamente no Spring Boot para gerenciar dependências entre componentes.
- Facilita testes unitários e melhora a modularidade, permitindo substituir implementações conforme necessário.

### Service Layer Pattern

- Orquestra operações complexas que envolvem múltiplos repositórios e regras de negócios.
- Centraliza a lógica de domínio, aplicando validações e regras de negócio antes de persistir dados.

### Façade Pattern

- Os Controllers da API funcionam como fachadas, simplificando o acesso aos serviços do backend.
- Expõem uma interface REST coerente que oculta a complexidade interna do sistema.

### Provider Pattern (no Flutter)

- Implementado para gerenciamento de estado e injeção de dependências no frontend.
- Permite compartilhar dados entre widgets e gerenciar o ciclo de vida dos componentes.

## Principais Componentes

### Frontend (Flutter)

- **UI Components**: Componentes de interface reutilizáveis como botões, cards e elementos de formulário adaptados para acessibilidade.
- **Pages**: Telas da aplicação que organizam os componentes de UI e conectam-se aos controllers, implementando layouts específicos para cada funcionalidade.
- **Controllers**: Responsáveis pela lógica de apresentação, gerenciamento do estado da UI e tratamento de eventos do usuário.
- **Services**: Lidam com a lógica de negócios do cliente e comunicação com a API, processando e transformando dados.
- **Data Transfer Objects (DTO)**: Estruturas de dados para comunicação entre camadas e serialização/deserialização de JSON.
- **HTTP Client**: Responsável pela comunicação com o backend através de requisições HTTP, gerenciando erros e retentativas.

### Backend (Spring Boot)

- **REST API Controllers**: Expõem endpoints da API, validam entradas e direcionam requisições para os serviços apropriados.
- **Service Layer**: Contém a lógica de negócios, orquestrando operações e garantindo a integridade dos dados entre diferentes entidades.
- **Repository Layer**: Provê abstração para acesso ao banco de dados e implementa operações CRUD específicas para cada domínio.
- **Entity Models**: Representam as entidades do domínio e são mapeadas para tabelas do banco de dados através de JPA/Hibernate.
- **JPA/Hibernate**: Framework ORM para mapeamento objeto-relacional e abstração do acesso ao banco de dados.

### Core Components de Domínio

#### Usuário

- Gerencia o ciclo de vida dos usuários (cadastro, autenticação, recuperação e exclusão)
- Mantém perfis com necessidades específicas de acessibilidade
- Controla a gamificação (selos por contribuições)
- Armazena preferências e configurações personalizadas
- Gerencia relações com avaliações e ocorrências criadas pelo usuário

#### Ocorrência

- Registra problemas de acessibilidade com geolocalização
- Permite categorização por tipos de barreiras
- Implementa sistema de votos para priorização
- Fornece consultas baseadas em proximidade geográfica
- Mantém status de resolução e histórico de atualizações

#### Estabelecimento

- Armazena informações de locais com suas coordenadas geográficas
- Categoriza estabelecimentos por tipo e serviços oferecidos
- Calcula e mantém métricas de acessibilidade baseadas em avaliações
- Permite buscas por proximidade e filtros por recursos de acessibilidade
- Vincula-se às ocorrências registradas no local

#### Avaliação

- Registra feedback dos usuários sobre estabelecimentos
- Utiliza sistema de pontuação para diferentes aspectos de acessibilidade
- Permite comentários detalhados e upload de imagens
- Fornece mecanismos de moderação e denúncia
- Calcula métricas consolidadas de acessibilidade
## Tecnologias Utilizadas

- **Frontend**: 
  - Flutter.
  - Comunicação com a API via HTTP (REST).
  
- **Backend**: 
  - Java (utilizando Spring Boot e JPA).
  - RESTful API

- **Docker**:
  - Contêineres para a subida do banco de dados Postgres.

## Como Executar a Aplicação

### Pré-requisitos

1. **Docker**: Certifique-se de que o Docker esteja instalado na sua máquina. [Instruções de instalação do Docker](https://www.docker.com/get-started).
2. **Java**: Tenha Java 21 para manipular diretamente o back-end [Refira-se ao README de /navable-api para mais informações](https://github.com/edumigueis/Navable/blob/main/navable-api/README.md).
3. **Maven**: [Refira-se ao README de /navable-api para mais informações](https://github.com/edumigueis/Navable/blob/main/navable-api/README.md).
4. **Flutter SDK**: Flutter é necessário para a execução do projeto [Refira-se ao README de /navable para mais informações](https://github.com/edumigueis/Navable/blob/main/navable/README.md).

### Passo a Passo

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/edumigueis/Navable.git
   cd Navable
   ```
2. **Inicie o container docker com o banco de dados**:
     ```bash
     cd ./docker
     docker compose up
     ```
3. **Siga os passos no readme de cada pasta para configurar e iniciar o back-end e o front-end**

## Arquitetura e Funcionalidades

### Frontend (navable/)

O frontend da aplicação é responsável por:

- Exibir os dados de estabelecimentos e ocorrências acessíveis em um mapa interativo.
- Permitir que os usuários filtrem e busquem informações com base em suas necessidades de acessibilidade.
- Fornecer uma interface intuitiva para o cadastro de usuários, registro de avaliações e inserção de ocorrências.
- Ainda carece de algumas implementações que excederam o tamanho do escopo.

### Backend (navable-api/)

O backend é responsável por:

- Processar as requisições da aplicação frontend.
- Validar e persistir dados no banco de dados.
- Garantir que as regras de negócio sejam seguidas, como validação de avaliações e controle de integridade dos dados.

As operações no banco de dados são feitas através de SQL, com o arquivo `init.sql` fornecendo dados iniciais.

## Contribuindo

Se você gostaria de contribuir com melhorias ou correções, siga os passos abaixo:

1. Faça um fork deste repositório.
2. Crie uma nova branch para sua feature ou correção:

```bash
git checkout -b minha-feature
```

3. Realize as modificações necessárias.
4. Envie as mudanças para o repositório:

```bash
git push origin minha-feature
```
5. Abra um pull request para revisão.

## Licença

Este projeto não é licenciado, peça autorização para utilizá-lo por completo.
