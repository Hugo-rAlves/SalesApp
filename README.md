# Sistema de gestão de Clientes e Vendas - Sales APP

## Tecnologias Utilizadas

### Backend
- **Java 11+**
- **Spring Boot 2.7.3**
- **Spring Security** (autenticação JWT)
- **PostgreSQL 14** (armazenamento de dados)
- **Flyway** (migrations de banco de dados)
- **Maven** (gerenciamento de dependências)

### Frontend
- **React**
- **HTML**
- **CSS**
- **JavaScript**
- **Axios** (requisições HTTP)
- **LeafletJS** (mapa para exibir localização dos clientes)

## Funcionalidades
- Autenticação JWT
- Cadastro e gerenciamento de clientes
- Cadastro e gerenciamento de vendas
- Tela de relatórios com gráficos e exportação de dados em CSV
- Exibição de localização dos clientes no mapa

## Pré-requisitos

- **Java 11+**
- **Maven**
- **Node.js** (incluindo npm ou yarn)
- **PostgreSQL**

## Passo a Passo para Execução
- Clone o repositório ou baixe o arquivo .zip;
- No diretório do Backend `src/main/resources/application.properties` adicione o seu usuário e senha da sua base de dados Postgres;
- Ainda no diretório `src/main/resources/application.properties` adicione uma token.secret para encode do JWT;
- Execute as Migrations do Flyway com o maven: `mvn flyway:migrate`;
- Execute o serviço do backend `mvn spring-boot:run`;
- No diretório do Frontend, abra a linha de comando e execute `npm install` para instalar as dependências;
- Execute o Frontend, ainda na linha comando, pelo comando `npm start`;
- Acesse, do seu navegador, a url `http://localhost:3000` para acessar a aplicação.

## Endpoints e Requisições
- Os Endpoints da aplicação podem ser testados via Postman, Insomnia ou qualquer outra ferramenta de requisição, estando exposto na porta 8080 `http://localhost:8080`;
- Embora o sistema possua roles para a autenticação, os Endpoints foram expostos para facilitar seus testes localmente.
