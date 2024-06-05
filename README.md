# Sistema de Gerenciamento de Protocolos e Demandas Bancárias

Este sistema é uma API que foi desenvolvida para facilitar o gerenciamento de protocolos e demandas solicitadas por um requerente.

A API faz os registros de demandas do tipo: Informação, Reclamação, Cancelamento, Elogi, Consulta, Solicitação e Denúncia. Além disso, é possível também gerenciar os funcionários.


## PRINCIPAIS FUNCIONALIDADES

Na nossa API, algumas das principais funcionalidades são:

- Um usuário fazer a abertura de um protocolo ou demanda, tendo a opção de se identificar ou não.
- Um requerente pode acompanhar e fazer consultas em seu protocolo para visualizar a resposta que a instituição deu referente ao seu protocolo.
- Um funcionário, Analista ou Operador, consegue fazer a tratativa de protocolos, sendo responsável por responder um protocolo da melhor forma.
- A demanda ou protocolo é distribuída de forma automática para um funcionário Analista com menor demanda ainda não tratadas, a depender do departamento que será resolvido o protocolo.
- Apenas um funcionário Gerente é capaz de fazer alterações sensiveis de funcionários, como mudar cargo, status ou departamento de um funcionário.
- Um status de um protocol é definido e tem seu controle feito pelo próprio sistema.

Para mais funcionalidades teste no swagger: `http://3.238.189.164:8080/swagger-ui/index.html#/`


## INFORMAÇÕES COMPLEMENTARES

Além das funcionalidades principais, para a construção da nossa API, organizamos os projetos em camadas.

### Controller Layer
É responsável por lidar com as solicitações HTTP, mostrando as funcionalidades para serem consumidas.

### Service Layer
Contém toda a lógica de negócios da aplicação. Se a lógica de negócios requer buscar/descartar dados, ela utiliza um repositório. Os serviços são responsáveis por orquestrar operações mais complexas que podem envolver múltiplas camadas ou entidades do sistema.

### Repository Layer
Responsável pelo armazenamento e recuperação de dados. Os repositórios interagem diretamente com o banco de dados ou para realizar operações CRUD (Create, Read, Update, Delete).

### DTO Layer
Desempenham um papel crucial nesta arquitetura, servindo como objetos que transportam dados entre processos para reduzir o número de chamadas de métodos.


## ROTAS

Durante a construção da API, as rotas para a porta utilizada foi a `http://localhost:8080`.

E após o deploy a rota passou a ser: `http://ec2-3-238-189-164.compute-1.amazonaws.com:8080/api/protocol`
- Para o deploy da aplicação estamos usando uma instância da AWS, então se o link estiver insdisponível significa que você terá que se conectar a nossa intância.
- Aqui está o tutorial do passo-a-passo para fazer este processo `https://www.notion.so/Tutorial-de-Conex-o-API-c4d4fa049ad741109e6e4bef56c7b29b?pvs=4`

Estrutura das rotas que usamos segue um padrão de `/api/` classe referente ao serviço / serviço que buscamos.

- `GET /api/employee/listEmployees`: Neste exemplo, buscamos a função de listar todos os funcionários cadastrados no sistema.
- `GET /api/protocol/number/{protocolNumber}`: Neste caso, estamos buscando um protocolo por seu número de registro, número este que iremos enviar através da URL no campo `/ {protocolNumber}`.


## CONFIGURAÇÃO DO AMBIENTE

Antes de executar o projeto, será necessário seguir alguns passos que listamos abaixo:

- Verifique a sua versão do JDK (Java Development Kit), para não haver qualquer conflito.
  Versão usada no projeto: JDK 21
- Configure as variáveis de ambiente `JAVA_HOME`.
- Configure as variáveis de ambiente `M2_HOME`.
- Configure e instale uma IDE (IntelliJ IDEA, Eclipse, STS).

### INSTALAÇÃO 

No seu terminal rode os seguintes comandos:

```
git clone https://github.com/ranielluna/Bradesco_Protocolo_Backup.git
```
```
cd Bradesco_Protocolo_Backup
```

Com o projeto aberto, basta apenas rodar ou pelo terminal com o comando: 
`./mvnw spring-boot:run` ou pelo botão Run da própria IDE.
