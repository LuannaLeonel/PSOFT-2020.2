# Coronavirus Brasil - Grupo 10

# Link de acesso para a API
https://coronavirus-brasil-grupo-10.herokuapp.com/swagger-ui.html

## Login
É necessário realizar o processo de login para ter acesso à maioria das rotas. Cada rota tem um tipo de autorização específica que pode ser verificada no Controller de cada entidade pelo anotação @PreAuthorize("hasRole('ROLE_EXEMPLO')"). Após realizar o login, será gerado um token que deve ser colocado em todas as rotas no parâmetro Authorization. 

## Tipos de Login já cadastrados

### Login para Hospital
```
{
  "password": "123",
  "username": "hospital"
}
```

### Login para Funcionario
```
{
  "password": "123",
  "username": "funcionario"
}
```

### Login para Administrador
```
{
  "password": "123",
  "username": "admin"
}
```

### Login para Laboratório
```
{
  "password": "123",
  "username": "laboratorio"
}
```

## Membros
- Anderson Victor da Silva Siqueira Melo
- Fernando Gabriel Marques da Silva
- Luanna Leonel de Farias Silva
- Pedro Lucas Siqueira de Lima
- Victor Hugo Sousa Rocha de Aquino
