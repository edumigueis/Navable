# Aplicação Spring Boot 3 (Java 21)

Esta é uma aplicação Spring Boot 3 construída com Java 21. A aplicação utiliza o Maven para gerenciamento de
dependências e automação de builds.

## Pré-requisitos

Antes de rodar a aplicação, assegure-se de que você tem os seguintes itens instalados:

- **Java 21** (JDK 21): [Baixar Java 21](https://jdk.java.net/21/)
- **Maven**: [Instalar Maven](https://maven.apache.org/install.html)
- **Git**: [Instalar Git](https://git-scm.com/)

## Construindo a Aplicação

1. **Compilar e Construir o Projeto** usando o Maven:

   Se você não tem o Maven instalado globalmente, pode usar o seguinte comando para construir o projeto:

```bash
mvn clean install
```

Isso irá:

- Limpar o projeto (excluindo o diretório `target`).
- Instalar as dependências necessárias.
- Empacotar a aplicação em um arquivo JAR (localizado no diretório `target`).

2. **Ignorar Testes** (Opcional): Se você deseja ignorar a execução dos testes durante o processo de build, use:

```bash
mvn clean install -DskipTests
```

## Rodando a Aplicação

Para rodar a aplicação após construí-la:

1. **Rodar o arquivo JAR**:

   Após construir a aplicação, navegue até o diretório `target` e execute o seguinte comando:

```bash
java -jar target/nome-da-aplicacao.jar
```

Substitua `nome-da-aplicacao.jar` pelo nome real do arquivo JAR gerado. Você verá o Spring Boot iniciando e a aplicação
rodando na porta padrão (geralmente `8080`).

2. **Rodar a Aplicação com o Maven** (Alternativa):

   Como alternativa, você pode rodar a aplicação diretamente usando o Maven sem precisar empacotá-la primeiro:

```bash
mvn spring-boot
```

Isso irá compilar o código e iniciar a aplicação diretamente.

## Acessando a Aplicação

- Por padrão, a aplicação estará disponível em `http://localhost:8080`, a menos que você tenha configurado uma porta
  diferente no arquivo `application.properties` ou `application.yml`.

- Para verificar se a aplicação está rodando, abra o seu navegador e acesse:

```bash
http://localhost:8080
```

## Parando a Aplicação

Para parar a aplicação, você pode pressionar `Ctrl + C` no terminal onde a aplicação está rodando. Se estiver rodando em
segundo plano ou em um servidor, você precisará matar o processo utilizando o ID do processo (PID).

## Modo de Desenvolvimento

Se você estiver desenvolvendo a aplicação e quiser que as alterações sejam recarregadas automaticamente sem reiniciar a
aplicação, você pode habilitar o Spring DevTools:

1. Adicione o Spring Boot DevTools no seu `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
</dependency>
```

Reinicie a aplicação usando:

```bash
mvn spring-boot:run
```

## Solução de Problemas

- **Conflito de Porta**: Se você encontrar um erro sobre a porta já estar em uso, altere a porta no
  arquivo `application.properties` ou `application.yml`:

```bash
server.port=8081
```

- **Problemas com Dependências**: Se o Maven falhar ao baixar as dependências, tente limpar o repositório Maven local:

```bash
mvn dependency
mvn clean install
```

## Notas

Como facilitador, uma IDE como o intellij pode ajudar a tornar esse processo mais rápido. Inclusive a adição da
amazon-coretto:21 por meio dele tende a ser mais simples. Na IDE, basta ter java configurado no projeto
e rodar o maven para fazer o download das dependencias adequadas. Não se esqueça de ligar o processamento de anotações
para o lombok.
