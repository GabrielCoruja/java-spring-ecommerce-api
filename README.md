# SpringMarket: API de E-Commerce com Spring Boot

## Descrição

O SpringMarket é uma API de comércio eletrônico construída com Spring Boot Web, Spring JPA para persistência de dados e Spring Security com tokens JWT para Autenticação e Autorização. 
Desenvolvido para oferecer funcionalidades básicas de login e compra de produtos.

## Funcionalidades (Em construção 🚧)

### Usuários

- Cadastro de novos usuários.
- Login de usuários.

## Tecnologias e Ferramentas Utilizadas

### Tecnologias

- [Java Versão 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
  - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
  - [Spring Security](https://spring.io/projects/spring-security) (Utilizado com JWT)
- [Docker](https://docs.docker.com/engine/install/)
  - [Docker Compose](https://docs.docker.com/compose/)
- [PostgresSQL](https://www.postgresql.org/) (Baixado via Docker)
- [H2 database](https://www.h2database.com/html/main.html)

### Ferramentas

- [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/)
- [Insomnia](https://insomnia.rest/)

### Qualidade de código

O [Checkstyle](https://checkstyle.sourceforge.io/) é uma ferramenta de análise estática de código que 
ajuda a garantir que o código-fonte Java siga um conjunto predefinido de regras de codificação. No projeto, é utilizado o 
arquivo de configuração [intellij-java-google-style.xml](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml) 
do Google, que define um conjunto de diretrizes de codificação baseadas nas práticas recomendadas do Google para Java.

Para executar a análise, utilize o comando:

   ```sh
   mvn checkstyle:check
   ```

## Como Executar o projeto

### Pré-requisitos:

- [SDKMAN! (Software Development Kit Manager)](https://sdkman.io/)
  - Ótima alternativa para baixar o Java e ter o controle de versões.
  - Versão utilizada foi a 17.x.x Oracle.
- [Java Versão 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Docker](https://docs.docker.com/engine/install/)

### Instalação e Execução (Em construção 🚧)

1. Clone o repositório
   ```sh
   git clone git@github.com:GabrielCoruja/java-spring-ecommerce-api.git
    ```

2. Instalação e compilação dos recursos necessários
   ```sh
   mvn install -DskipTests
    ```

3. Iniciar o banco de dados e a aplicação

<details>
  <summary>Start da aplicação com Docker</summary>

- Para subir a aplicação e o banco de dados, execute o comando:

    ```sh
   docker-compose up -d --build
    ```

Obs: Utilizando o docker os dados serão persistidos utilizando o PostgreSQL.

</details>

4. O projeto terá como base o endpoint http://localhost:8080

### Testes (Em construção 🚧)

Para executar os testes, Utilize o comando:

```sh

```

### Documentação (Em construção 🚧)