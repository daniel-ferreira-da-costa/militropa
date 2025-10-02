# ☕ Militropa - Sistema de Gestão de Armamentos

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Quarkus](https://img.shields.io/badge/Quarkus-3.7.1-blue?style=for-the-badge&logo=quarkus)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apache-maven)
![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=for-the-badge&logo=docker)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

## 📋 Visão Geral

**Militropa** é uma API REST robusta desenvolvida com Quarkus para gerenciamento completo de armamentos, clientes, pedidos e controle de acesso. O sistema oferece autenticação JWT, validação de dados, upload de imagens e integração com banco de dados PostgreSQL, ideal para lojas especializadas em armamentos que necessitam de controle rigoroso de estoque, vendas e documentação legal.

## ✅ Features

- ✅ **Autenticação e Autorização JWT** - Sistema seguro de login com tokens JWT
- ✅ **Gestão de Armas** - CRUD completo com informações detalhadas (calibre, marca, modelo, RNA, etc.)
- ✅ **Gestão de Clientes** - Cadastro com CPF, registro de posse/porte, endereços e telefones
- ✅ **Gestão de Pedidos** - Controle de vendas e histórico de transações
- ✅ **Gestão de Funcionários** - Controle de acesso e permissões
- ✅ **Upload de Imagens** - Suporte para fotos de produtos
- ✅ **Validação de Dados** - Hibernate Validator para garantir integridade
- ✅ **API RESTful** - Endpoints bem estruturados seguindo padrões REST
- ✅ **Documentação OpenAPI** - Swagger UI integrado para testes
- ✅ **CORS Configurado** - Pronto para integração com frontends
- ✅ **Testes Automatizados** - JUnit 5 + Mockito + RestAssured
- ✅ **Docker Ready** - Múltiplos Dockerfiles para diferentes ambientes

## 🚀 Como Rodar

### Pré-requisitos

- **Java 21** ou superior
- **Maven 3.8+**
- **PostgreSQL 12+**
- **Docker** (opcional, para containerização)

### 1️⃣ Configurar o Banco de Dados

Crie um banco de dados PostgreSQL:

```sql
CREATE DATABASE topicos1db;
CREATE USER topicos1 WITH PASSWORD '123456';
GRANT ALL PRIVILEGES ON DATABASE topicos1db TO topicos1;
```

### 2️⃣ Configurar Variáveis de Ambiente (Opcional)

Edite o arquivo `src/main/resources/application.properties` se necessário:

```properties
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/topicos1db
quarkus.datasource.username=topicos1
quarkus.datasource.password=123456
```

### 3️⃣ Executar em Modo de Desenvolvimento

```bash
# Clone o repositório
git clone https://github.com/daniel-ferreira-da-costa/militropa.git
cd militropa

# Execute em modo dev (hot reload ativado)
./mvnw quarkus:dev
```

A aplicação estará disponível em:
- **API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/q/swagger-ui
- **Health Check**: http://localhost:8080/q/health

### 4️⃣ Build para Produção

```bash
# Gerar JAR executável
./mvnw package

# Executar o JAR
java -jar target/quarkus-app/quarkus-run.jar
```

### 5️⃣ Build Nativo (GraalVM)

```bash
# Requer GraalVM instalado
./mvnw package -Pnative

# Executar binário nativo
./target/militropa-1.0.0-SNAPSHOT-runner
```

### 6️⃣ Docker

```bash
# Build da imagem JVM
docker build -f src/main/docker/Dockerfile.jvm -t militropa:jvm .

# Executar container
docker run -i --rm -p 8080:8080 militropa:jvm
```

## 📁 Estrutura do Projeto

```
militropa/
├── src/
│   ├── main/
│   │   ├── docker/              # Dockerfiles (JVM, Native, Micro)
│   │   ├── java/unitins/tp1/
│   │   │   ├── application/     # Configurações e tratamento de erros
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   │   ├── arma/
│   │   │   │   ├── cliente/
│   │   │   │   ├── endereco/
│   │   │   │   ├── funcionario/
│   │   │   │   ├── pedido/
│   │   │   │   └── usuario/
│   │   │   ├── form/            # Formulários (upload de imagens)
│   │   │   ├── model/           # Entidades JPA
│   │   │   │   └── converterjpa/
│   │   │   ├── repository/      # Repositórios Panache
│   │   │   ├── resource/        # Controllers REST
│   │   │   ├── service/         # Lógica de negócio
│   │   │   │   ├── arma/
│   │   │   │   ├── cliente/
│   │   │   │   ├── endereco/
│   │   │   │   ├── file/
│   │   │   │   ├── funcionario/
│   │   │   │   ├── hash/
│   │   │   │   ├── jwt/
│   │   │   │   ├── pedido/
│   │   │   │   └── usuario/
│   │   │   └── validation/      # Validadores customizados
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── import.sql       # Dados iniciais
│   │       └── token/           # Chaves JWT (public/private)
│   └── test/                    # Testes unitários e integração
├── pom.xml                      # Dependências Maven
├── mvnw / mvnw.cmd             # Maven Wrapper
└── README.md
```

## 🤝 Contribuição

Contribuições são bem-vindas! Para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

Por favor, certifique-se de:
- Seguir os padrões de código do projeto
- Adicionar testes para novas funcionalidades
- Atualizar a documentação quando necessário

## 📄 Licença

Este projeto está sob a licença **MIT** *(sugestão, pode alterar depois)*. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

**Daniel Ferreira da Costa**

[![GitHub](https://img.shields.io/badge/GitHub-daniel--ferreira--da--costa-black?style=for-the-badge&logo=github)](https://github.com/daniel-ferreira-da-costa)

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!
