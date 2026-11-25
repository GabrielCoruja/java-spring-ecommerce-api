# 🛒 SpringMarket: API de E-commerce com Spring Boot

[![CI](https://github.com/GabrielCoruja/java-spring-ecommerce-api/actions/workflows/ci.yml/badge.svg)](https://github.com/GabrielCoruja/java-spring-ecommerce-api/actions/workflows/ci.yml)
![Java](https://img.shields.io/badge/Java_21-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_4.2-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security_+_JWT-6DB33F?style=flat-square&logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=postgresql&logoColor=white)
![License](https://img.shields.io/badge/licen%C3%A7a-MIT-blue?style=flat-square)

API REST de e-commerce com cadastro e login de usuários, catálogo de produtos e registro de vendas. A autenticação usa **Spring Security com tokens JWT**, e o acesso às rotas é controlado por **perfil de usuário** (`ADMIN`, `MANAGER` e `USER`).

## Funcionalidades

- **Usuários:** cadastro, login com geração de token JWT e consulta (só `ADMIN`)
- **Produtos:** CRUD completo (`ADMIN` e `MANAGER`)
- **Vendas:** registro de vendas com vários produtos e quantidades (`ADMIN` e `MANAGER`)
- **Documentação interativa** com Swagger / OpenAPI
- **Dados de exemplo** carregados ao iniciar a aplicação

## Tecnologias

| Camada | Tecnologias |
| :-- | :-- |
| Linguagem | Java 21 |
| Framework | Spring Boot 4.2 (Web MVC, Data JPA, Security, Actuator) |
| Autenticação | Spring Security + JWT ([java-jwt](https://github.com/auth0/java-jwt)) |
| Banco de dados | PostgreSQL (Docker) e H2 em memória (desenvolvimento e testes) |
| Documentação | springdoc-openapi (Swagger UI) |
| Qualidade | Checkstyle (Google Java Style), JUnit, Mockito e MockMvc |
| Infra | Docker, Docker Compose e GitHub Actions |

## Arquitetura

```
src/main/java/com/gabriel/ecommerce/
├── controller/   # endpoints REST, DTOs e tratamento de exceções
├── service/      # regras de negócio e geração/validação de tokens
├── repository/   # interfaces Spring Data JPA
├── entity/       # entidades JPA (User, Product, Sale, ProductSale)
├── security/     # configuração do Spring Security e filtro JWT
├── docs/         # configuração do OpenAPI
└── seed/         # carga inicial de dados
```

## Como executar

**Pré-requisitos:** Java 21 ou Docker.

```sh
git clone https://github.com/GabrielCoruja/java-spring-ecommerce-api.git
cd java-spring-ecommerce-api
```

<details>
  <summary><b>Com Docker</b> (PostgreSQL)</summary>

```sh
docker compose up -d --build
```

</details>

<details>
  <summary><b>Sem Docker</b> (H2 em memória)</summary>

```sh
./mvnw spring-boot:run
```

O Maven Wrapper (`./mvnw`) baixa o Maven automaticamente, então não é preciso instalá-lo.

</details>

A API sobe em `http://localhost:8080`, e a documentação fica em **http://localhost:8080/swagger-ui/index.html**.

### Usuário de exemplo

Ao iniciar, a aplicação cria um usuário `ADMIN` para testes:

| username | password |
| :-- | :-- |
| `coruja` | `password` |

## Endpoints

| Método | Rota | Acesso | Descrição |
| :-- | :-- | :-- | :-- |
| `POST` | `/users` | público | Cadastra um usuário |
| `POST` | `/auth/login` | público | Faz login e retorna o token JWT |
| `GET` | `/users` | `ADMIN` | Lista os usuários |
| `GET` | `/users/{userId}` | `ADMIN` | Busca um usuário |
| `GET` | `/products` | `ADMIN`, `MANAGER` | Lista os produtos |
| `GET` | `/products/{productId}` | `ADMIN`, `MANAGER` | Busca um produto |
| `POST` | `/products` | `ADMIN`, `MANAGER` | Cria um produto |
| `PUT` | `/products/{productId}` | `ADMIN`, `MANAGER` | Atualiza um produto |
| `DELETE` | `/products/{productId}` | `ADMIN`, `MANAGER` | Remove um produto |
| `GET` | `/sales` | `ADMIN`, `MANAGER` | Lista as vendas |
| `GET` | `/sales/{saleId}` | `ADMIN`, `MANAGER` | Busca uma venda |
| `POST` | `/sales` | `ADMIN`, `MANAGER` | Registra uma venda |

Exemplo de fluxo:

```sh
# 1. Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"coruja","password":"password"}'

# 2. Use o token retornado nas rotas protegidas
curl http://localhost:8080/products -H "Authorization: Bearer <token>"
```

## Testes e qualidade

```sh
./mvnw verify
```

O comando roda o **Checkstyle** (Google Java Style), compila e executa os testes de integração (MockMvc) e de unidade. O mesmo comando roda no GitHub Actions a cada push e pull request.

Para rodar um teste específico:

```sh
./mvnw -Dtest=UserTest#createSuccessTest test
```

## Licença

Distribuído sob a licença MIT. Veja [LICENSE](LICENSE).
