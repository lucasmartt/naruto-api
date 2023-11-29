# API de Personagens do Naruto

Esta API é desenvolvida em Spring e consome outra API para listar todos os personagens do universo Naruto.

## Swagger
A documentação da API está disponível via Swagger. Acesse [aqui](http://localhost:8080/swagger-ui/index.html#/) para explorar e entender os endpoints disponíveis.

## Trello
Acompanhe o progresso do projeto no [nosso quadro no Trello](https://trello.com/b/qrbkvQZA/naruto-api). Fique por dentro das funcionalidades planejadas, em desenvolvimento e concluídas.

## Dependências e Execução
A API utiliza Maven para gerenciamento de dependências. Certifique-se de ter o Maven instalado.

### Configuração do Banco de Dados
Para que a funcionalidade de cadastro de novos personagens funcione corretamente, é necessário criar uma variável de sistema com a seguinte estrutura: `DB_URL`=`jdbc:mysql://username:password@ip:port/database_name`.
Substitua `username`, `password`, `ip`, `port` e `database_name` pelas credenciais e informações do seu banco de dados MySQL.

### Executando a API
1. Clone o repositório.
2. Configure a variável de sistema `DB_URL`.
3. Execute a aplicação utilizando o Maven:

Isso iniciará a API na porta padrão.