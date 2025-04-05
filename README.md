# desafio-cresol JAVA

## Regras de fucionamento

O projeto possui duas entidades, Instituicoes e Eventos, quando cadastrada uma instituição então 
é possível cadastrar um evento referenciando a qual instituição o mesmo pertence, o mesmo possui a configuração de um cronjob para 
realizar o encerramento de eventos de maneira automatizada, sendo executado a cada 30 segundos, a cada execução é verificado se 
existe algum evento para encerrar e caso exista o mesmo é encerrado.

### Cadastro de Instituições

Para o cadastro de instituições é necessário passar os dados `nome` e `tipo` 
sendo `nome` um texto comum(string) e `tipo` um enum com três opções (`CONFEDERACAO, SINGULAR, COOPERATIVA E CENTRAL`)

### Cadatro de Eventos
Para o cadastro de eventos é necessário passar os dados `nome`, `dataInicial`, `dataFinal` e `idInstituicao`
sendo `nome` um texto comum(string), `dataInicial` e `dataFinal` LocalDateTime e `idInstituicao` um inteiro.


## Para executar o projeto localmente

### Caso tenha a CLI do quarkus instalada na maquina utilizar
```shell script
quarkus dev
```

### Caso contrário utilizar
```shell script
./mvnw quarkus:dev
```

## Tecnologias usadas no projeto

- Java 21.
- Quarkus
- PostgreSql