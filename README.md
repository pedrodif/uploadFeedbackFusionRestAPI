# FeedbackFusion Rest API

Este projeto é uma API REST desenvolvida em Java com Spring Boot. Este README fornece instruções detalhadas para instalação, configuração e execução do projeto.

## Índice

1. [Instalação](#instalação)
   - [Download e Instalação do JDK 17](#download-e-instalação-do-jdk-17)
   - [Instalação do DBeaver](#instalação-do-dbeaver)
   - [Instalação do Maven](#instalação-do-maven)
   - [Instalação do Docker](#instalação-do-docker)
   - [Instalação do Docker Compose](#instalação-do-docker-compose)
2. [Criando o Container](#criando-o-container)
3. [Modelo Relacional](#modelo-relacional)
4. [Banco de Dados](#banco-de-dados)
   - [Criação da Tabela `usuario`](#criação-da-tabela-usuario)
5. [Executando o Projeto](#executando-o-projeto)
6. [Testando a API com Postman](#testando-a-api-com-postman)


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
## Modelo Relacional

O modelo relacional utilizado no projeto **FeedbackFusion** organiza os dados em tabelas interligadas através de chaves primárias e estrangeiras, garantindo a integridade e a consistência das informações. A estrutura foi planejada para permitir uma gestão eficiente de feedbacks, tarefas, conquistas e usuários, além de proporcionar um monitoramento contínuo do desempenho dos colaboradores.

A imagem a seguir ilustra o diagrama do modelo relacional implementado no projeto:

![Modelo Relacional](modeloRelacional.jpg "Modelo Relacional.")

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
    cargo VARCHAR(50) NOT NULL,
    equipe_id BIGINT NULL,
    avatar VARCHAR(100) NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY (id);
ALTER TABLE usuario ADD CONSTRAINT fk_equipe FOREIGN KEY (equipe_id) REFERENCES equipe(id);

```

### Criação da Tabela `equipe`

```sql
CREATE TABLE equipe (
    id BIGSERIAL NOT NULL,
    nome VARCHAR(255) NOT NULL,
    gestor_id BIGINT NOT NULL,
    data_criacao DATE NOT NULL,
    data_edicao DATE NULL
);

ALTER TABLE equipe ADD CONSTRAINT equipe_pk PRIMARY KEY (id);
ALTER TABLE equipe ADD CONSTRAINT fk_gestor FOREIGN KEY (gestor_id) REFERENCES usuario(id);

```


### Criação da Tabela `feedback`

```sql
CREATE TABLE feedback (
    id BIGSERIAL NOT NULL,
    gestor_id BIGINT NOT NULL,
    colaborador_id BIGINT NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    descricao TEXT NOT NULL,
    data_criacao DATE NOT NULL,
    data_edicao DATE NULL
);

ALTER TABLE feedback ADD CONSTRAINT feedback_pk PRIMARY KEY (id);
ALTER TABLE feedback ADD CONSTRAINT fk_gestor FOREIGN KEY (gestor_id) REFERENCES usuario(id);
ALTER TABLE feedback ADD CONSTRAINT fk_colaborador FOREIGN KEY (colaborador_id) REFERENCES usuario(id);

```

### Criação da Tabela `tarefa`

```sql

CREATE TABLE tarefa (
    id BIGSERIAL NOT NULL,
    pontuacao INT DEFAULT 0 NOT NULL,
    pontuacao_obtida INT DEFAULT 0 NOT NULL, -- Novo campo adicionado
    gestor_id BIGINT NOT NULL,
    colaborador_id BIGINT NOT NULL,
    status_conclusao BOOLEAN DEFAULT FALSE NOT NULL,
    documentos VARCHAR(255) NULL,
    comentarios_gestor TEXT NULL,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT NULL,
    data_criacao DATE NOT NULL,
    data_prazo DATE NOT NULL,
    data_edicao DATE NULL,
    data_conclusao DATE NULL
);

ALTER TABLE tarefa ADD CONSTRAINT tarefa_pk PRIMARY KEY (id);
ALTER TABLE tarefa ADD CONSTRAINT fk_gestor FOREIGN KEY (gestor_id) REFERENCES usuario(id);
ALTER TABLE tarefa ADD CONSTRAINT fk_colaborador FOREIGN KEY (colaborador_id) REFERENCES usuario(id);

```
### Criação da Tabela `selo` 

```sql
CREATE TABLE selo (
    id BIGSERIAL NOT NULL,
    nome VARCHAR(150) NOT NULL,
    descricao TEXT NOT NULL,
    tipo VARCHAR(100) NOT NULL
);

ALTER TABLE selo ADD CONSTRAINT selo_pk PRIMARY KEY (id);

```

### Criação da Tabela `conquista` 

```sql

CREATE TABLE conquista (
    id BIGSERIAL NOT NULL,
    gestor_id BIGINT NOT NULL,
    colaborador_id BIGINT NOT NULL,
    data_atribuicao DATE NOT NULL,
    selo_id BIGINT NOT NULL
);

ALTER TABLE conquista ADD CONSTRAINT conquista_pk PRIMARY KEY (id);
ALTER TABLE conquista ADD CONSTRAINT fk_gestor FOREIGN KEY (gestor_id) REFERENCES usuario(id);
ALTER TABLE conquista ADD CONSTRAINT fk_colaborador FOREIGN KEY (colaborador_id) REFERENCES usuario(id);
ALTER TABLE conquista ADD CONSTRAINT fk_selo FOREIGN KEY (selo_id) REFERENCES selo(id); 

```

Inserindo dados na tabela `selo`:

```sql
INSERT INTO selo (nome, descricao, tipo) VALUES
('Monitor Estelar', 'Este selo é concedido àqueles que ajudam outros colaboradores a alcançar grandes resultados em suas tarefas. Parabéns por apoiar sua equipe e se destacar como um verdadeiro mentor. Você é uma estrela!', 'monitor'),
('Gêmeos Sinérgicos', 'Trabalho em equipe! Recebido ao completar uma tarefa com auxilio de um monitor.', 'padrão'),
('Tropa do Cafezinho', 'Para quem mantém a performance lá no alto, pontuando 90% várias vezes. Café e eficiência, do jeitinho brasileiro!', 'padrão'),
('Camelo Valente', 'Concedido àquele que carrega as responsabilidades com determinação e enfrenta longas jornadas sem desistir. Um símbolo de força e dedicação, sempre pronto para ir além!', 'padrão'),
('Mapa do Tesouro', 'Esse selo celebra o colaborador que traça o caminho certo para alcançar as metas. Com estratégia e persistência, ele segue o mapa do tesouro e garante que a equipe alcance seu destino.', 'padrão'),
('Voando Alto', 'Para quem conduz suas tarefas com maestria e precisão. Esse selo é reservado para aqueles que voam alto e alcançam resultados excepcionais!', 'padrão'),
('Dança do Robô', 'Recebido por aqueles que solucionam problemas de maneira rápida e inovadora, como uma verdadeira IA. Esse colaborador é ágil e preciso, sempre encontrando as melhores soluções.', 'padrão'),
('Espírito Selvagem', 'Conquistado por quem avança com coragem e tranquilidade, mesmo em terrenos difíceis. Um selo para os que encontram beleza e aprendizado nos desafios.', 'padrão'),
('Calmaria Tropical', 'Concedido ao colaborador que mantém a calma e resolve problemas com serenidade, mesmo em meio às ondas de desafios. Um verdadeiro exemplo de tranquilidade.', 'padrão'),
('Acampamento Revitalizante', 'Para aquele que desbrava projetos e, após grandes conquistas, aproveita uma merecida pausa. Um acampamento para recarregar as forças e preparar-se para as próximas aventuras!', 'padrão');
('Colaborador Sideral', 'Aquele que navega pelo vasto universo dos desafios, sempre em busca de novas estrelas para conquistar. Sua jornada é repleta de descobertas, e cada conquista é uma galáxia de aprendizados que o prepara para explorar ainda mais além.', 'padrão');

```
### Criação da Tabela `solicitacao_ajuda `

```sql
CREATE TABLE solicitacao_ajuda (
    id BIGSERIAL NOT NULL,
    monitor_id BIGINT NOT NULL,
    colaborador_id BIGINT NOT NULL,
    tarefa_id BIGINT NOT NULL,
    data_solicitacao DATE NOT NULL
);

ALTER TABLE solicitacao_ajuda ADD CONSTRAINT solicitacao_ajuda_pk PRIMARY KEY (id);
ALTER TABLE solicitacao_ajuda ADD CONSTRAINT fk_monitor FOREIGN KEY (monitor_id) REFERENCES usuario(id);
ALTER TABLE solicitacao_ajuda ADD CONSTRAINT fk_colaborador FOREIGN KEY (colaborador_id) REFERENCES usuario(id);
ALTER TABLE solicitacao_ajuda ADD CONSTRAINT fk_tarefa FOREIGN KEY (tarefa_id) REFERENCES tarefa(id);

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
     - Expanda a tabela **feedback** para visualizar suas colunas e confirmar que foi criada conforme o script.

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
    "cargo": "Desenvolvedora",
   "avatar": "https://api.dicebear.com/9.x/bottts/svg?seed=leticia"
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
    "cargo": "Desenvolvedora",
    "avatar": "https://api.dicebear.com/9.x/bottts/svg?seed=leticia"
}
```

**Deletar Usuário:**

- **Método:** DELETE  
- **URL:** `http://localhost:8080/usuarios/{id}`  
  Substitua `{id}` pelo ID do usuário a ser deletado.



