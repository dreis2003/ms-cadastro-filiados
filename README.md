# ms-cadastro-filiados

Microsservico backend para cadastro de filiados da IKO Nakamura Brasil.

## Stack

- Java 21
- Spring Boot 4
- Maven
- PostgreSQL
- Flyway
- Spring Web MVC
- Spring Data JPA
- Spring Security
- Bean Validation
- Actuator
- JUnit, Mockito e Testcontainers

## Arquitetura

O projeto segue Arquitetura Hexagonal e Clean Architecture.

- `dominio`: regras de negocio e entidades puras, sem Spring, JPA ou Bean Validation.
- `aplicacao`: casos de uso e portas de entrada/saida.
- `infraestrutura`: REST, JPA, banco de dados, configuracoes e seguranca.

Estrutura principal:

```text
br.com.ikonbrasil.cadastrofiliados
├── compartilhado
│   ├── dominio
│   ├── aplicacao
│   └── infraestrutura
├── filial
│   ├── dominio
│   ├── aplicacao
│   └── infraestrutura
├── filiado
│   ├── dominio
│   ├── aplicacao
│   └── infraestrutura
└── seguranca
    ├── dominio
    ├── aplicacao
    └── infraestrutura
```

## Banco De Dados

Profile `dev`:

- Banco: `db_ikon_brasil_dev`
- Host: `192.168.40.80`
- Usuario: `ikon_dev`

Profile `prod`:

- Banco: `db_ikon_brasil`
- Configurado por variaveis de ambiente.

## Migrations

As migrations Flyway ficam em:

```text
src/main/resources/db/migration
```

Migrations iniciais:

- `V1__criar_tabela_perfis_acesso.sql`
- `V2__criar_tabela_filiais.sql`
- `V3__criar_tabela_usuarios.sql`
- `V4__criar_tabela_filiados.sql`

O schema do banco e gerenciado pelo Flyway. O Hibernate esta configurado com `ddl-auto=none` para nao criar nem alterar tabelas automaticamente.

## Como Rodar

Na raiz do projeto:

```bash
mvn spring-boot:run
```

Para escolher o profile:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

Se executar pela IDE, confirme que o profile ativo e `dev`. A configuracao comum ja define `spring.profiles.active=dev`, mas algumas configuracoes de execucao da IDE podem sobrescrever esse valor.

Health check:

```bash
curl http://localhost:8080/actuator/health
```

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

## Feature: Cadastro De Filial/Dojo

Autenticacao:

- Gerar token no `ms-auth` pelo endpoint `POST http://localhost:8081/api/v1/auth/login`.
- Enviar o token no header `Authorization: Bearer <accessToken>`.
- O `ms-cadastro-filiados` valida o JWT e usa os claims `perfil`, `permissoes` e `filialId`.

Endpoints:

```http
POST  /api/v1/filiais
GET   /api/v1/filiais
GET   /api/v1/filiais/{id}
PATCH /api/v1/filiais/{id}/ativar
PATCH /api/v1/filiais/{id}/inativar
```

Regras de acesso:

- Apenas `MATRIZ_ADMIN` pode criar, listar, ativar e inativar filiais.
- `FILIAL_RESPONSAVEL` e `FILIAL_PROFESSOR` podem consultar somente a propria filial.
- A filial autorizada vem do claim `filialId` do JWT. O header `X-Filial-Id` nao e mais usado para autorizacao.

Collection Postman:

```text
docs/postman/ms-cadastro-filiados-filiais.postman_collection.json
```

## Feature: Cadastro De Filiado

Endpoints:

```http
POST  /api/v1/filiados
GET   /api/v1/filiados
GET   /api/v1/filiados?filialId={filialId}
GET   /api/v1/filiados/{id}
PUT   /api/v1/filiados/{id}
PATCH /api/v1/filiados/{id}/foto-perfil
PATCH /api/v1/filiados/{id}/ativar
PATCH /api/v1/filiados/{id}/inativar
```

Regras implementadas:

- Filiado deve possuir nome completo, data de nascimento e filial.
- CPF e numero internacional sao opcionais.
- CPF, quando informado, e normalizado para somente digitos.
- CPF nao pode ser duplicado.
- Numero internacional nao pode ser duplicado quando informado.
- Filiado so pode ser cadastrado em filial ativa.
- Ao transferir filiado para outra filial, a nova filial deve estar ativa.
- `MATRIZ_ADMIN` acessa todas as filiais.
- `FILIAL_RESPONSAVEL` e `FILIAL_PROFESSOR` criam/listam/consultam apenas filiados da propria filial, definida pelo claim `filialId` do JWT.
- `FILIADO_CRIAR` permite cadastrar.
- `FILIADO_VISUALIZAR` permite listar e consultar.
- `FILIADO_EDITAR` permite atualizar dados, foto de perfil e ativar.
- `FILIADO_INATIVAR` permite inativar.

Collection Postman:

```text
docs/postman/ms-cadastro-filiados-filiados.postman_collection.json
```

## Como Testar

```bash
mvn test
```

Os testes de integracao deverao usar Testcontainers com PostgreSQL.

## Observacoes

- Frontend nao faz parte deste projeto neste momento.
- As classes de dominio nao usam `@Entity`, `@Table`, `@Service`, `@Component` ou Bean Validation.
- As entidades JPA ficam somente em `infraestrutura/banco/entidade`.
- As validacoes de entrada REST devem ficar futuramente nos DTOs de entrada.
