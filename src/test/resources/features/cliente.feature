# language: pt
Funcionalidade: Gerenciamento de Clientes

  @smoke
  Cenário: Criar um novo cliente
    Quando criar um novo cliente
    Então o cliente é registrado com sucesso

  Cenário: Buscar um cliente existente por CPF
    Dado que um cliente já foi cadastrado
    Quando requisitar a busca do cliente pelo CPF
    Então o cliente é exibido com sucesso

  Cenário: Buscar um cliente existente por ID
    Dado que um cliente já foi cadastrado
    Quando requisitar a busca do cliente pelo ID
    Então o cliente é exibido com sucesso

  Cenário: Buscar um cliente existente por UUID
    Dado que um cliente já foi cadastrado
    Quando requisitar a busca do cliente pelo UUID
    Então o cliente é exibido com sucesso

  Cenário: Buscar um cliente inexistente por ID
    Quando requisitar a busca de um cliente inexistente pelo ID
    Então uma mensagem de cliente não encontrado é exibida