# Departments Registration
 
Micro serviço responsável por gerenciar o cadastro de departments contas de pagamentos.

# Funcionamento

Esse micro serviço é consumido por serviços que realizam o gerenciamento e cadastros de novos departamentos, onde pode ser acessado via protocolo HTTP, podendo ser 
consumido por diversos serviços independentes.

- Java 11
- Spring Boot 2.3.1

### Databases
- MySql 5.7
    
### Dependências
Não possui dependências externas

## Como rodar localmente

Rode o um container docker contendo o seguinte database departament_db, após isso execute a aplicação  utilizando o comando mvn spring-boot:run, ou  
a IDE de sua preferência.

#Endpoints: 
  
GET http://localhost:8080/api/v1/departments -> listar todos
PUT http://localhost:8080/api/v1/departments -> atualizar registro deve ser passado os campos que deseja atualizar e o ID do deparment no body 
DELETE http://localhost:8080/api/v1/departments/department?id={ID} -> deletar por ID
GET http://localhost:8080/api/v1/departments/find-department?id={ID} -> listar por ID
POST http://localhost:8080/api/v1/departments/ -> criar novo department deve ser enviado os dados dos novo department no Body.

