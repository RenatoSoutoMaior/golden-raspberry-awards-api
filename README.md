## Golden Raspberry Awards - API

<p align="center">
  <img src="http://img.shields.io/static/v1?label=JAVA&message=BACKEND&color=orange&style=for-the-badge"/>
  <img src="http://img.shields.io/static/v1?label=SPRING&message=FRAMEWORK&color=blue&style=for-the-badge"/>
  <img src="http://img.shields.io/static/v1?label=JUNIT&message=TESTES&color=purple&style=for-the-badge"/>
  <img src="http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=green&style=for-the-badge"/>
</p>

<hr>

## Tópicos 

- [Descrição do projeto](#descrição-do-projeto)
- [Tecnologias utilizadas](#tecnologias-utilizadas)
- [Requisito do sistema](#requisito-do-sistema)
- [Executando a aplicação](#executando-a-aplicação)
- [API](#api)

<hr>

## Descrição do projeto

API RESTful desenvolvida para possibilitar a leitura da lista de indicados e vencedores da categoria **Pior Filme do 
Golden Raspberry Awards**, obtendo o **produtor** com maior intervalo entre dois prêmios consecutivos e o que obteve dois
prêmios mais rápido.

## Tecnologias utilizadas

- Java 8
- Spring Boot e Data
- H2 Database
- JUnit
- Lombok

## Requisito do sistema

- JDK 1.8
- Garantir que tenha um arquivo CSV com a lista de filmes no mesmo padrão do arquivo de teste que fica no seguinte caminho: ``golden-raspberry-awards-api/src/main/resources/files/movielist.csv``

## Executando a aplicação

- O primeiro passo é clonar ou baixar o ZIP do projeto no GitHub.
- Na pasta raiz do projeto, execute o comando ``mvn spring-boot:run`` para executar a sua aplicação. 
Você também pode utilizar uma IDE de sua preferência para rodar a aplicação. ``(Exemplo: IntelliJ / Eclipse)``
- Após levantar a aplicação com sucesso, a aplicação estará disponível em http://localhost:8080.

## API

Você pode utilizar a seguinte API para obter os dados desejados:

- /producer/interval-between-awards **(GET)**
  - Exemplo de response:
  
  ```
  {
      "min": [
          {
              "producer": "Joel Silver",
              "interval": 1,
              "previousWin": 1990,
              "followingWin": 1991
          }
      ],
      "max": [
          {
              "producer": "Matthew Vaughn",
              "interval": 13,
              "previousWin": 2002,
              "followingWin": 2015
          }
      ]
  }
  ```

## Testes

- Para rodar os testes em sua IDE, localize e abra a classe de teste localizada no seguinte caminho: ``golden-raspberry-awards-api/src/test/java/com/rsm/goldenraspberryawardsapi/ProducerControllerTest.java``.
- Depois execute os testes clicando em **Run > Run**.

<hr>