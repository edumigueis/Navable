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

## Principais componentes

**Usuário:** O componente Usuário é responsável pela gestão dos dados dos usuários da aplicação. Ele permite o cadastro, autenticação (login), recuperação e exclusão de usuários. Além disso, gerencia funcionalidades relacionadas à gamificação e personalização, como o vínculo de selos, categorias de acessibilidade e votos em ocorrências.

**Ocorrência:** O componente Ocorrência trata do registro e consulta de problemas relacionados à acessibilidade em locais da universidade. Os usuários podem criar novas ocorrências informando tipo, localização e descrição. Também é possível buscar ocorrências próximas a uma determinada coordenada geográfica, visualizar detalhes de cada uma e consultar o número de votos recebidos.

**Estabelecimento:** O componente Estabelecimento representa os locais físicos da universidade (como prédios, departamentos ou áreas externas). Ele armazena informações básicas sobre esses locais e serve de base para que usuários possam avaliá-los quanto à acessibilidade. Também pode ser usado para agregar dados estatísticos e facilitar a localização de pontos com boa ou má acessibilidade.

**Avaliação:** O componente Avaliação permite que os usuários registrem opiniões e atribuam notas sobre a acessibilidade de um estabelecimento. Cada avaliação inclui uma descrição e um valor numérico, sendo associada a um usuário e a um local específico. Esse componente é essencial para coletar feedback da comunidade e apoiar decisões de melhoria na infraestrutura.

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
