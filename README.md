# MinhaVeterinaria 🐶🐱
O projeto minha veterinária se trata de uma api para gerenciar uma veterinária,  
aplicando os conceitos de serviços separados em apis e autenticando usuário através de JWT.

## Stack utilizada:
- Java (JDK 17)
- SpringBoot
- JWT na autenticação das requisições
- MongoDB
- React

## Como rodar o projeto (sem docker):
- Clone este repositório para diretório que deseja: `git clone https://github.com/gustavoc0imbra/MinhaVeterinaria.git`
- Após terminar o download, abra cada pasta api em sua IDE de preferência
- Após abrir cada api na IDE execute-as conforme sua IDE

## APIs e suas respectivas portas:
- Abaixo todas as portas que cada api é exposta:
  
|      API     | Porta |
| ------------ | ----- |
| Auth Service     | 8080 |
| People Service   | 8081 |
| Animal Service   | 8082 |
| Schedule Service | 8083 |

## Como acessar documentação de todas as APIs:
- Basta acessar o endereço `http://localhost:` + {portaApi} + `/api/v0/docs`
- Toda documentação é feita pelo Swagger

## Endpoints Auth API
![image](https://github.com/user-attachments/assets/a48b7115-3a6a-4a20-98a1-3a2c80ad0f36)

## Endpoints People API
![image](https://github.com/user-attachments/assets/8987a434-8bd7-4ed2-aaa6-b5900ff40945)

## Endpoints Animal API
![image](https://github.com/user-attachments/assets/7f68ddb0-c13f-457d-b8aa-12dc11dbf68e)

## Endpoints Schedule API
![image](https://github.com/user-attachments/assets/f8b1dd07-2622-4a6f-bdd0-ac6d79b28bc5)

> [!WARNING]
> Ao iniciar a api de autenticação (Auth Service) será criado automaticamente um usuário padrão para poder usar todos os outros endpoints  
> **Login:** `admin@email.com`  
> **Senha:** `admin123`

## Como gerar token JWT
- Para gerar o token é necessário enviar para API de autenticação o seguinte corpo de requisição:
```
{
    "email": "string",
    "password": "string"
}
```
- caso seja efetuado o login, será retornado no corpo da mensagem o seguinte corpo contendo o token jwt:
```
{
    "token": "string",
    "message": "Autenticado"
}
```

## Exemplos de uso (Postman):
**Todas as APIs ficam no endereço localhost e com suas respectivas portas**
- Auth Service - Verificar se token está válido:  
Se token estiver válido é retornado true, caso contrário false  
![image](https://github.com/user-attachments/assets/b2ac7368-8a84-4788-b699-00d0c1ae4627)

- Auth Service - Login  
Se estiver login certo é retornar o token e a mensagem "Autenticado", caso contrário status 401 é retornado:  
![image](https://github.com/user-attachments/assets/1bec9bc2-1c67-4f67-b3b5-9701589d55ca)

**Todas as apis abaixo é necessário enviar o token JWT no header Authorization da requisição**
- People Service - Listar todas as pessoas cadastradas  
`curl --location 'http://localhost:8081/api/v0/people' --header 'Accept: application/json' --header 'Authorization: Bearer ******`  
![image](https://github.com/user-attachments/assets/61cc7328-ad4c-45ab-892f-805651d50105)

- People Service - Salvar pessoa  
`curl --location 'http://localhost:8081/api/v0/people' --header 'Accept: application/json' --header 'Content-Type: application/json' --header 'Authorization: Bearer ****' --data '{"name":"Gustavo teste","cpf":"","phoneNumber":"12345678"}'`  
![image](https://github.com/user-attachments/assets/fc60680c-5ad5-43cd-8ea1-e3b6ee3ad8f0)

- People Service - Atualizar pessoa  
`curl --location --request PUT 'http://localhost:8081/api/v0/people' --header 'Accept: application/json' --header 'Content-Type: application/json' --header 'Authorization: Bearer ••••••' --data '{"id":"684a2c63c607952e9debf061","name":"Gustavo novo att agora","cpf":"123123","phoneNumber":"123123","createdAt":"2025-05-25T00:03:40.012+00:00"}'`  
![image](https://github.com/user-attachments/assets/7b003ddb-899c-4863-b312-9cd517341590)

- People Service - Buscar pelo id  
`curl --location 'http://localhost:8081/api/v0/people/68324737c8baac40edc55cc3' --header 'Accept: application/json' --header 'Authorization: Bearer ••••••'`  
![image](https://github.com/user-attachments/assets/b5b58828-9dca-48a4-bab3-d5254edec0ef)

- People Service - Deletar pessoa  
`curl --location --request DELETE 'http://localhost:8081/api/v0/people/6838dd41abd72143c8aadadd' --header 'Accept: application/json' --header 'Authorization: Bearer ••••••'`  
![image](https://github.com/user-attachments/assets/7c0a87ab-ef06-4ef3-9b52-c0115310108f)

- Animal Service - Listar todos animais cadastrados  
`curl --location 'http://localhost:8082/api/v0/animals' --header 'Accept: application/json' --header 'Authorization: Bearer ••••••'`  
![image](https://github.com/user-attachments/assets/627a5b79-7dba-4a4b-bd23-8ec2887ec374)

- Animal Service - Salvar animal (não é obrigatório passar id de uma pessoa, se não tiver passar "" no campo personId):  
`curl --location 'http://localhost:8082/api/v0/animals' --header 'Accept: application/json' --header 'Content-Type: application/json' --header 'Authorization: Bearer ••••••' --data '{"name":"Mel","personId":"684a2c5ac607952e9debf060","specie":"Dog","breed":"Dachshund","age":16}'`  
![image](https://github.com/user-attachments/assets/7719b627-c0d3-43fc-b82d-43d7a7d0324a)

- Animal Service - Atualizar animal (não é obrigatório passar id de uma pessoa, se não tiver passar "" no campo personId)):  
`curl --location --request PUT 'http://localhost:8082/api/v0/animals' --header 'Accept: application/json' --header 'Content-Type: application/json' --header 'Authorization: Bearer ••••••' --data '{"personId":"","id":"684a2f0ba685fb5748aba913","name":"Mel att","specie":"Dog","breed":"Dachshund","age":16}'`  
![image](https://github.com/user-attachments/assets/3fad9d1b-91de-483e-a092-5889acf4a356)

- Animal Service - Buscar pelo id:
`curl --location 'http://localhost:8082/api/v0/animals/684a2f0ba685fb5748aba913' --header 'Accept: application/json' --header 'Authorization: ••••••'`  
![image](https://github.com/user-attachments/assets/7b82ed00-8972-48ad-8293-53a5e64ea560)

- Animal Service - Deletar animal  
`curl --location --request DELETE 'http://localhost:8082/api/v0/animals/684a2f0ba685fb5748aba913' --header 'Accept: application/json' --header 'Authorization: Bearer ••••••'`  
![image](https://github.com/user-attachments/assets/3d4d76d8-f341-40d3-908d-fc6971277fe6)

- Schedule Service - Listar todos agendamentos cadastrados  
`curl --location 'http://localhost:8083/api/v0/schedule' --header 'Accept: application/json' --header 'Authorization: Bearer ••••••'`  
![image](https://github.com/user-attachments/assets/b578bf47-d55a-4a33-8ea1-b0706a2a5413)

- Schedule Service - Salvar um agendamento (é obrigatório passar o campo animalId):  
`curl --location 'http://localhost:8083/api/v0/schedule' --header 'Accept: application/json' --header 'Content-Type: application/json' --header 'Authorization: Bearer ••••••' --data '{"type":"Banho","animalId":"6835a660040df34cfb0eef83","date":"2025-05-30","price":"90"}'`  
![image](https://github.com/user-attachments/assets/a731bf89-9318-4d13-a94c-e44a21bb4f21)

- Schedule Service - Atualizar agendamento (é obrigatório passar o campo animalId)):  
`curl --location --request PUT 'http://localhost:8083/api/v0/schedule' --header 'Accept: application/json' --header 'Content-Type: application/json' --header 'Authorization: Bearer ••••••' --data '{"id":"684a320131574a67f499bfb6","animalId":"6835a660040df34cfb0eef83","type":"Vacina att preço","date":"2025-05-31T00:00:00.000+00:00","status":"P","price":70.431}'`  
![image](https://github.com/user-attachments/assets/de1e5055-5fde-4bd0-8e81-142c287a0338)

- Schedule Service - Buscar pelo id:
`curl --location 'http://localhost:8083/api/v0/schedule/684a320131574a67f499bfb6' --header 'Accept: application/json' --header 'Authorization: ••••••'`
![image](https://github.com/user-attachments/assets/55e965bc-f9b5-4aef-85f7-cc455659a8ef)

- Schedule Service - Deletar agendamento  
`curl --location --request DELETE 'http://localhost:8083/api/v0/schedule/684a320131574a67f499bfb6' --header 'Accept: application/json' --header 'Authorization: ••••••'`  
![image](https://github.com/user-attachments/assets/57e948e7-69d4-493e-ad88-d85250a80157)
