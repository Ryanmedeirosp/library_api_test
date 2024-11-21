# Library API Tests

Projeto de finalização da matéria Testes de Back-End. O objetivo da aplicação é ser um sistema de gerenciamento de biblioteca constando com funções de cadastro de usuários e livros, buscas, atualização de emprésttimos e outros.

## Requisitos da aplicação

|ID    | Descrição do Requisito  | Prioridade |
|------|-----------------------------------------|----|
|RF-001| Um usuário deve possuir um ID, nome, e-mail, data de cadastro e um número de cartão único | ALTA | 
|RF-002| Um usuário deve ter um status de ativo ou inativo | ALTA |
|RF-003| A consulta deve retornar detalhes do usuário, como nome, e-mail e número do cartão | ALTA |
|RF-004| A busca deve retornar usuários com empréstimos atrasados que ultrapassam um número de dias específico (informado na consulta) | ALTA | 
|RF-005| Empréstimo deve ser vinculado ao usuário e ao livro | ALTA |
|RF-006| A funcionalidade deve verificar se o e-mail já está registrado | ALTA |
|RF-007| Sistema deve verificar se livro já está emprestado antes de permitir um novo empréstimo | ALTA | 
|RF-008| Número do cartão deve ser gerado automaticamente e ser único para cada usuário | ALTA |
|RF-009| Um livro deve conter ID, título, autor, ISBN e ano de publicação | ALTA |
|RF-010| Deve ser possível consultar livros pelo autor e/ou ano de publicação | ALTA |
|RF-011| Consultas devem retornar livros ordenados por título | ALTA | 
|RF-012| Empréstimo deve registrar a data de início e a data de devolução | ALTA |
|RF-013| A consulta deve retornar todos os empréstimos ativos de um usuário, incluindo informações como data de início, data de devolução e status | ALTA |
|RF-014| O sistema deve calcular automaticamente se o empréstimo está atrasado, comparando a data de devolução com a data atual | ALTA |
|RF-015| A busca deve retornar usuários com empréstimos atrasados que ultrapassam um número de dias específico (informado na consulta) | ALTA | 

## Instalação

Clone o arquivo em algum repositório com:

```
ADICIONAR O LINK AQUI
```

Busque o arquivo <em>LibraryApplication.java</em> e rode na sua máquina.

Lembrando que esta é uma aplicação local, logo, você deve utlizar <em>http://localhost:8080</em> na sua URL para utilizá-la.

## Endpoints

### User

Esses Endpoints referem-se aos Usuários.



Rota: GET | /users

Descrição: Seleciona todos os usuaários registrados.

```
{
    "id": 0,
    "name": "string",
    "email": "string",
    "registerDate": "2024-11-21",
    "singleCard": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "status": true,
    "loans": [
      {
        "id": 0,
        "startDate": "2024-11-21",
        "devolutionDate": "2024-11-21",
        "status": "EM_ANDAMENTO",
        "books": [
          {
            "id": 0,
            "title": "string",
            "author": "string",
            "isbn": "string",
            "status": true,
            "yearOfPublication": 0
          }
        ]
      }
    ]
  }
```



Rota: POST | /users

Descrição: Cadastra um novo usuário.

```
{
  "name": "string",
  "email": "string"
}
```

Rota: GET | /users/{id}

Descrição: Busca um usuário por seu ID.

```
{
  "id": 0,
  "name": "string",
  "email": "string",
  "registerDate": "2024-11-21",
  "singleCard": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "status": true,
  "loans": [
    {
      "id": 0,
      "startDate": "2024-11-21",
      "devolutionDate": "2024-11-21",
      "status": "EM_ANDAMENTO",
      "books": [
        {
          "id": 0,
          "title": "string",
          "author": "string",
          "isbn": "string",
          "status": true,
          "yearOfPublication": 0
        }
      ]
    }
  ]
}
```



Rota: DELETE | /users/{id}

Descrição: Deleta um usuário.

#



### Book

Esses Endpoints referem-se aos Livros.



Rota: GET | /books

Descrição: Busca todos os Livros da Aplicação

```
  {
    "id": 0,
    "title": "string",
    "author": "string",
    "isbn": "string",
    "status": true,
    "yearOfPublication": 0
  }
```



Rota: GET | /books

Descrição: Busca todos os Livros da Aplicação

```
  {
    "id": 0,
    "title": "string",
    "author": "string",
    "isbn": "string",
    "status": true,
    "yearOfPublication": 0
  }
```



Rota: POST | /books

Descrição: Adiciona um livro.

```
{
  "title": "string",
  "author": "string",
  "isbn": "string",
  "yearOfPublication": 0
}
```



Rota: GET | /books/{id}

Descrição: Busca um livro por seu ID.

```
{
  "id": 0,
  "title": "string",
  "author": "string",
  "isbn": "string",
  "status": true,
  "yearOfPublication": 0
}
```



Rota: GET | /books/year

Descrição: Busca um livro por seu ano de publicação.

```
{
  "id": 0,
  "title": "string",
  "author": "string",
  "isbn": "string",
  "status": true,
  "yearOfPublication": 0
}
```



Rota: GET | /books/title

Descrição: Busca um livro por seu título.

```
{
  "id": 0,
  "title": "string",
  "author": "string",
  "isbn": "string",
  "status": true,
  "yearOfPublication": 0
}
```



Rota: GET | /books/author

Descrição: Busca um livro por seu autor.

```
{
  "id": 0,
  "title": "string",
  "author": "string",
  "isbn": "string",
  "status": true,
  "yearOfPublication": 0
}
```



Rota: DELETE | /books/{id}

Descrição: Deleta um livro.

#



### Loan

Esses Endpoints referem-se aos Empréstimos.



Rota: GET | /loans

Descrição: Busca todos os empréstimos feitos.

```
  {
    "id": 0,
    "startDate": "2024-11-21",
    "devolutionDate": "2024-11-21",
    "status": "EM_ANDAMENTO",
    "books": [
      {
        "id": 0,
        "title": "string",
        "author": "string",
        "isbn": "string",
        "status": true,
        "yearOfPublication": 0
      }
    ]
  }
```



Rota: GET | /loans/{id}

Descrição: Busca empréstimos pelo seu ID.

```
  {
    "id": 0,
    "startDate": "2024-11-21",
    "devolutionDate": "2024-11-21",
    "status": "EM_ANDAMENTO",
    "books": [
      {
        "id": 0,
        "title": "string",
        "author": "string",
        "isbn": "string",
        "status": true,
        "yearOfPublication": 0
      }
    ]
  }
```



Rota: GET | /loans/status

Descrição: Busca empréstimos pelo seu status.

```
  {
    "id": 0,
    "startDate": "2024-11-21",
    "devolutionDate": "2024-11-21",
    "status": "PENDENTE",
    "books": [
      {
        "id": 0,
        "title": "string",
        "author": "string",
        "isbn": "string",
        "status": true,
        "yearOfPublication": 0
      }
    ]
  }
```



Rota: PUT | /loans

Descrição: Usado para atualizar os status de um empréstimo.

```
{
  "email": "string",
  "bookCodes": [
    0
  ]
}
```



Rota: POST | /loans

Descrição: Usado para criar um empréstimo.

```
{
  "email": "string",
  "bookCodes": [
    0
  ]
}
```



Rota: DELETE | /loans/{id}

Descrição: Deleta um empréstimo.



#
*Desenvolvido por [Paulo Gabriel](https://github.com/WSgabri3l), [Arthur Davis](https://github.com/Arthurdnl), [Ryan Medeiros](https://github.com/Ryanmedeirosp), [José Bulhões](https://github.com/juneonju)*
