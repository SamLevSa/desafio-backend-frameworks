# 📦 Projeto Multiframework — Spring Boot & Node.js/Express

> Projeto acadêmico desenvolvido com arquitetura em camadas utilizando dois frameworks back-end distintos: **Java/Spring Boot** e **Node.js/Express**.

---

## 📁 Estrutura de Diretórios

```
my-project/
├── backend-spring/                  # Aplicação Java com Spring Boot
│   └── src/main/java/com/example/
│       ├── controller/              # Camada de Apresentação
│       ├── service/                 # Camada de Negócio
│       │   └── impl/
│       ├── repository/              # Camada de Acesso a Dados
│       ├── model/                   # Entidades JPA
│       ├── dto/                     # Data Transfer Objects
│       └── exception/               # Tratamento global de erros
│
├── backend-node/                    # Aplicação Node.js com Express
│   └── src/
│       ├── controllers/             # Camada de Apresentação
│       ├── services/                # Camada de Negócio
│       ├── repositories/            # Camada de Acesso a Dados
│       ├── models/                  # Entidades / Schemas
│       ├── routes/                  # Definição de Rotas
│       └── middlewares/             # Middlewares e tratamento de erros
│
└── docker-compose.yml               # Orquestração dos serviços
```

---

## 🏛️ Explicação da Arquitetura em Camadas

A arquitetura em camadas (*Layered Architecture*) é um padrão de projeto que organiza o sistema em responsabilidades bem definidas e separadas. Cada camada se comunica apenas com a camada imediatamente abaixo, garantindo baixo acoplamento e alta coesão.

### Fluxo de uma requisição

```
Cliente (HTTP Request)
        │
        ▼
┌──────────────────┐
│   Controller     │  ← Recebe a requisição, valida entrada, retorna resposta HTTP
└────────┬─────────┘
         │ chama
         ▼
┌──────────────────┐
│    Service       │  ← Contém as regras de negócio da aplicação
└────────┬─────────┘
         │ chama
         ▼
┌──────────────────┐
│   Repository     │  ← Abstrai o acesso ao banco de dados
└────────┬─────────┘
         │ persiste / consulta
         ▼
┌──────────────────┐
│    Database      │  ← Armazena os dados
└──────────────────┘
```

### Responsabilidades de cada camada

| Camada | Responsabilidade | Spring Boot | Node/Express |
|---|---|---|---|
| **Controller** | Receber e responder requisições HTTP | `@RestController` | `controller.js` + `routes.js` |
| **Service** | Aplicar regras de negócio | `@Service` + interface | `service.js` |
| **Repository** | Acessar e persistir dados | `JpaRepository` | `repository.js` |
| **Model** | Representar entidades do domínio | `@Entity` | Sequelize/Mongoose Model |
| **DTO** | Transferir dados entre camadas sem expor a entidade | `record` / classe DTO | objeto JS simples |

### Princípios aplicados

- **Separação de responsabilidades (SoC):** cada camada tem uma função única e bem definida.
- **Inversão de dependência:** as camadas superiores dependem de abstrações (interfaces no Spring; módulos exportados no Node).
- **Encapsulamento:** as regras de negócio ficam isoladas na camada de serviço, sem vazar para o controller ou para o repositório.
- **Reutilização:** um mesmo `Service` pode ser consumido por múltiplos `Controllers` ou rotas sem duplicação de lógica.

---

## 🔍 Comparação entre Frameworks

### 1. Configuração Inicial

| Critério | Node.js / Express | Java / Spring Boot |
|---|---|---|
| **Tempo de setup** | Rápido — `npm init` + instalar pacotes | Moderado — geração via Spring Initializr |
| **Estrutura inicial** | Mínima, o desenvolvedor organiza como quiser | Gerada automaticamente com convenções claras |
| **Arquivos de configuração** | `package.json`, `.env` | `pom.xml` / `build.gradle`, `application.properties` |
| **Curva de aprendizado** | Baixa — JavaScript amplamente conhecido | Maior — requer entendimento de Java, IoC e anotações |

O Express adota uma filosofia *minimalista*: entrega apenas o essencial e deixa as decisões arquiteturais para o desenvolvedor. O Spring Boot, por outro lado, oferece um ecossistema completo com autoconfiguração via *convention over configuration*, reduzindo decisões repetitivas, mas exigindo mais conhecimento prévio.

---

### 2. Verbosidade do Código

| Critério | Node.js / Express | Java / Spring Boot |
|---|---|---|
| **Linhas para um endpoint simples** | ~10–15 linhas | ~20–30 linhas (com anotações) |
| **Tipagem** | Opcional (TypeScript) | Obrigatória (tipagem estática forte) |
| **Boilerplate** | Baixo | Moderado (getters, setters, DTOs, interfaces) |
| **Legibilidade** | Alta para projetos pequenos | Alta para projetos médios/grandes |

O Node.js/Express permite criar APIs funcionais com poucas linhas de código, o que favorece prototipação rápida. Já o Spring Boot, apesar de mais verboso, exige que o desenvolvedor defina contratos claros (interfaces de serviço, DTOs separados por entrada e saída), o que se torna uma vantagem em projetos de maior escala, onde a manutenção e a consistência são prioritárias.

Com o uso do **Lombok** no Spring, parte do boilerplate (construtores, getters, setters) é eliminado via anotações como `@Data`, `@RequiredArgsConstructor` e `@Builder`.

---

### 3. Gestão de Dependências

| Critério | Node.js / Express | Java / Spring Boot |
|---|---|---|
| **Gerenciador** | npm / yarn / pnpm | Maven / Gradle |
| **Arquivo de dependências** | `package.json` | `pom.xml` (Maven) ou `build.gradle` (Gradle) |
| **Repositório central** | npmjs.com | Maven Central / JitPack |
| **Resolução de conflitos** | Automática (node_modules) | Declarativa com exclusões manuais se necessário |
| **Tamanho do projeto** | `node_modules` pode ser volumoso | `.jar` empacotado, leve para distribuição |

O npm resolve dependências de forma simples e rápida, porém o diretório `node_modules` pode crescer significativamente. O Maven/Gradle do ecossistema Spring utiliza um repositório local de cache (`.m2`) e gera um `.jar` executável final, mais adequado para ambientes de produção corporativos.

---

### 4. Análise Geral

```
                   Node.js/Express     Spring Boot
Configuração            ████░░            ███░░░
Verbosidade baixa       █████░            ███░░░
Maturidade ecosistema   ████░░            █████░
Escalabilidade          ████░░            █████░
Produtividade inicial   █████░            ███░░░
Produtividade em escala ███░░░            █████░
```

**Quando preferir Node.js/Express:**
- APIs leves e microserviços com muitas operações de I/O
- Times com domínio em JavaScript/TypeScript
- Projetos que exigem prototipação e entrega rápida
- Aplicações em tempo real (WebSockets, streaming)

**Quando preferir Spring Boot:**
- Sistemas corporativos com regras de negócio complexas
- Necessidade de tipagem forte e contratos bem definidos em tempo de compilação
- Integração nativa com ecossistemas Java (Kafka, JPA, Spring Security)
- Times que valorizam convenção e padronização arquitetural

---

## 🚀 Como Executar

### Spring Boot

```bash
cd backend-spring
./mvnw spring-boot:run
# Disponível em: http://localhost:8080
```

### Node.js / Express

```bash
cd backend-node
npm install
npm run dev
# Disponível em: http://localhost:3000
```

### Com Docker

```bash
docker-compose up --build
```

---

## 🔗 Endpoints disponíveis

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/api/users` | Lista todos os usuários |
| `GET` | `/api/users/:id` | Busca usuário por ID |
| `POST` | `/api/users` | Cria um novo usuário |
| `DELETE` | `/api/users/:id` | Remove um usuário |

---

## 🛠️ Tecnologias Utilizadas

**Backend Spring:**
- Java 17+
- Spring Boot 3.x
- Spring Web, Spring Data JPA
- Lombok
- H2 / PostgreSQL

**Backend Node:**
- Node.js 18+
- Express.js
- Sequelize (ORM)
- Nodemon (dev)

**Infraestrutura:**
- Docker & Docker Compose

---

## 📌 Observações sobre Organização do Git

Recomenda-se seguir as convenções abaixo para commits:

```
feat: adiciona endpoint de criação de usuário
fix: corrige tratamento de erro no UserService
refactor: separa lógica de negócio do controller
docs: atualiza README com comparação de frameworks
chore: adiciona docker-compose para orquestração
```

Cada commit deve representar uma unidade lógica de trabalho, preferencialmente vinculada a uma camada ou funcionalidade específica.

---

## 👤 Samuel Levi, de Matrícula 01812954

Desenvolvido como atividade acadêmica para a disciplina de **Back-End Frameworks**.
