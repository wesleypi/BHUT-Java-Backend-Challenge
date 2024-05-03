## Desafio BHUT Java Backend

### A modelagem do desafio segue o esquema:

![concept](https://github.com/wesleypi/BHUT-Java-Backend-Challenge/assets/50997796/c0a08a34-c2e3-4cff-825b-37bf2bec970a)

## Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- [Git](https://git-scm.com)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- Uma IDE Java, como o [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Docker](https://www.docker.com/)


```bash
# Clone este repositório
$ git clone https://github.com/wesleypi/BHUT-Java-Backend-Challenge.git

# Acesse a pasta do projeto no terminal/cmd
$ cd BHUT-Java-Backend-Challenge

# Execute o Docker Compose para iniciar os containers
$ docker compose up -d
```

#### Após os containers estarem rodando, Abra o projeto na sua IDE Java

#### substitua as variáveis de ambiente pelas suas credenciais no arquivo de configuração application.yml:

```yaml
external-api:
  user: ${USER_CREDENTIAL}
  pass: ${PASS_CREDENTIAL}
```

- Utilize sua plataforma de API ([Postman](https://www.postman.com/)) para acessar os endpoints:


```bash
# get & post
localhost:8080/api/car

# get
localhost:8080/api/logs
```

### exemplo para POST api/car

```json
{
"nome" : "carro_nome",
"marca" : "carro_marca",
"preco" : 49999.99,
"anoFabricacao" : 2016,
"webhookURL":"sua_url_para_webhook"
}
```


## Outras Tecnologias 
- [Kafka](https://kafka.apache.org/)
- [MongoDB](https://www.mongodb.com/)

