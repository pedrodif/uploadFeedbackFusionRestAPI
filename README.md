# FeedbackFusion Rest API

Este projeto é uma API REST desenvolvida em Java com Spring Boot. Este README fornece instruções detalhadas para instalação, configuração e execução do projeto.

## Índice

1. [Pré-requisitos](#pré-requisitos)
2. [Instalação](#instalação)
    - [Download e Instalação do JDK 17](#download-e-instalação-do-jdk-17)
    - [Instalação do DBeaver](#instalação-do-dbeaver)
    - [Instalação do Maven](#instalação-do-maven)
    - [Instalação do Docker](#instalação-do-docker)
    - [Instalação do Docker Compose](#instalação-do-docker-compose)
3. [Configuração do Projeto](#configuração-do-projeto)
    - [Variáveis de Ambiente](#variáveis-de-ambiente)
4. [Banco de Dados](#banco-de-dados)
    - [Criação da Tabela `usuario`](#criação-da-tabela-usuario)
5. [Executando o Projeto](#executando-o-projeto)
6. [Testando a API com Postman](#testando-a-api-com-postman)

## Pré-requisitos

- Sistema operacional: Windows.

## Instalação

### Download e Instalação do JDK 17

1. Acesse o site oficial do [JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
2. Baixe o instalador apropriado para o seu sistema operacional.
3. Siga as instruções de instalação.

**Criar variável de ambiente JAVA_HOME:**

- Abra o Painel de Controle.
- Vá em Sistema > Configurações avançadas do sistema > Variáveis de Ambiente.
- Clique em "Nova" em Variáveis do sistema e adicione:
  - Nome: `JAVA_HOME`
  - Valor: `C:\Caminho\Para\jdk-17`

### Instalação do DBeaver

1. Acesse o site oficial do [DBeaver](https://dbeaver.io/download/).
2. Baixe o instalador apropriado para o seu sistema operacional.
3. Siga as instruções de instalação.

### Instalação do Maven

1. Acesse o site oficial do [Maven](https://maven.apache.org/download.cgi).
2. Baixe o arquivo binário.
3. Extraia o conteúdo do arquivo para uma pasta de sua escolha.
4. Adicione a pasta `bin` do Maven à sua variável de ambiente `PATH`.

**Criar variável de ambiente M2_HOME:**

- Abra o Painel de Controle.
- Vá em Sistema > Configurações avançadas do sistema > Variáveis de Ambiente.
- Clique em "Nova" em Variáveis do sistema e adicione:
  - Nome: `M2_HOME`
  - Valor: `C:\Caminho\Para\apache-maven-3.x.x`

### Instalação do Docker

1. Acesse o site oficial do [Docker](https://docs.docker.com/get-docker/).
2. Siga as instruções de instalação para o seu sistema operacional.

**Login no Docker pelo Terminal:**

Para fazer login no Docker Hub, execute o comando abaixo. Insira seu nome de usuário e senha do Docker Hub quando solicitado:

```bash
docker login -u <seu-usuario> -p <sua-senha>

```

### Instalação do Docker Compose

Siga as instruções do site oficial para a instalação do Docker Compose: [Instalação do Docker Compose](https://docs.docker.com/compose/install/standalone/).

## Criando o container 

**Importante:** Lembre-se de inicializar o Docker Desktop antes de executar qualquer comando relacionado ao Docker.
**Importante:** Este passo deve ser concluído antes de realizar a configuração no Banco de Dados.

Para criar e iniciar os containers Docker, navegue até o diretório onde está localizado o arquivo `database-docker` e execute o seguinte comando:

```bash
docker-compose up -d
```

## Banco de Dados

**Atenção:** este projeto está sendo criado localmente.

### Configurando a Conexão com o Banco de Dados PostgreSQL no DBeaver

Para configurar corretamente sua conexão no DBeaver, certifique-se de que o nome do banco de dados seja **`feedbackfusion_database`**. Siga os passos abaixo:

1. **Abrir o DBeaver**:
   - Inicie o DBeaver no seu computador.

2. **Criar uma Nova Conexão**:
   - Clique no ícone de **Nova Conexão** (ícone de plug) ou vá em **Arquivo > Nova Conexão**.

3. **Escolher o Banco de Dados**:
   - Escolha **PostgreSQL** (ou o tipo de banco de dados que você está utilizando) na lista e clique em **Avançar**.

4. **Configurar a Conexão**:
   - Preencha os detalhes da conexão com os seguintes valores:
     - **Host**: `0.0.0.0` (caso o banco esteja configurado para aceitar conexões dessa interface).
     - **Porta**: `5432` (ou outra porta configurada para o PostgreSQL).
     - **Database**: **`feedbackfusion_database`** (é fundamental que este seja o nome exato do banco).
     - **Usuário**: postgres.
     - **Senha**: `123`.
   - Clique em **Testar Conexão** para garantir que a conexão está correta.

5. **Salvar a Conexão**:
   - Se o teste de conexão for bem-sucedido, clique em **Concluir** para salvar e finalizar a configuração.

Agora você poderá executar o script SQL para criar a tabela diretamente no DBeaver com a conexão configurada.


### Criação da Tabela `usuario`

Utilize o seguinte script SQL para criar a tabela no DBeaver:

```sql
CREATE TABLE usuario (
    id BIGSERIAL NOT NULL,
    pontuacao_total INT DEFAULT 0 NOT NULL,
    status_monitor BOOLEAN DEFAULT FALSE NOT NULL,
    email VARCHAR(150) NOT NULL,
    senha VARCHAR(80) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    empresa VARCHAR(100) NOT NULL,
    departamento VARCHAR(50) NOT NULL,
    cargo VARCHAR(50) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY (id);

```
- Executar o Script:

1. **Executar o Script SQL**:
   - Clique no ícone **Executar SQL** (ou pressione **Ctrl + Enter**) para executar o script.
   - Verifique a aba de **Resultados** para confirmar que a tabela foi criada com sucesso.

2. **Verificando a Tabela Criada**:
   - **Atualizar o Banco de Dados**:
     - No painel esquerdo, expanda o banco de dados onde você criou a tabela, clique com o botão direito e selecione **Atualizar** para ver a nova tabela listada.
   - **Visualizar Estrutura da Tabela**:
     - Expanda a tabela **usuario** para visualizar suas colunas e confirmar que foi criada conforme o script.

Com esses passos, você terá configurado a conexão e criado a tabela com sucesso no DBeaver.

## Executando o projeto

Para executar o projeto **FeedbackFusionRestApiApplication**, inicie a aplicação a partir do método `main`. Isso irá inicializar o servidor e tornar sua API disponível para interações.


## Testando a API com Postman

1. Abra o Postman.
2. Configure as requisições para interagir com sua API.
3. Use as URLs e métodos apropriados (GET, POST, etc.) de acordo com a sua implementação.

#### Exemplos de Requisições

**Criar Usuário:**

- **Método:** POST  
- **URL:** `http://localhost:8080/usuarios`  
- **Corpo:**
  
```json
{
    "email": "leticia.branca@example.com",
    "senha": "senha123",
    "nome": "Leticia Branca de Barros Motta",
    "empresa": "Exemplo Ltda",
    "departamento": "Desenvolvimento",
    "cargo": "Desenvolvedora"
}

```
**Listar Usuários:**

- **Método:** GET  
- **URL:** `http://localhost:8080/usuarios`  

**Obter Usuário por ID:**

- **Método:** GET  
- **URL:** `http://localhost:8080/usuarios/{id}`  
  Substitua `{id}` pelo ID do usuário desejado.

**Atualizar Usuário:**

- **Método:** PUT  
- **URL:** `http://localhost:8080/usuarios/{id}`  
  Substitua `{id}` pelo ID do usuário a ser atualizado.  
- **Corpo:**
  
```json
{
    "email": "leticia.branca@example.com",
    "senha": "novaSenha123",
    "nome": "Leticia Branca de Barros Motta",
    "empresa": "Exemplo Ltda",
    "departamento": "Desenvolvimento",
    "cargo": "Desenvolvedora"
}
```

**Deletar Usuário:**

- **Método:** DELETE  
- **URL:** `http://localhost:8080/usuarios/{id}`  
  Substitua `{id}` pelo ID do usuário a ser deletado.



