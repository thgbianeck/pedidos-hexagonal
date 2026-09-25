# pedidos-hexagonal

Projeto didático de **Arquitetura Hexagonal** com Java 21, Spring Boot, MySQL e Kafka.

## Pré-requisitos

- Java 21
- Maven 3.9+
- Docker e Docker Compose

## Como executar localmente

```bash
docker compose up -d
./mvnw spring-boot:run
```

## Como executar os testes

```bash
./mvnw test
```

## Estrutura

- `domain`: regras de negócio, sem dependências externas.
- `application`: portas e casos de uso.
- `infraestrutura`: adaptadores REST, JPA/MySQL e Kafka.

## Endpoints

- `POST /pedidos` — cria um novo pedido.