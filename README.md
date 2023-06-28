# DesafioTinnova

Prova técnica para Tinnova.

projeto desenvolvido utilizando Maven, Java 17, SpringBoot 3, banco de dados H2, Docker, e OpenAPI 3.0 (swagger) testes unitários com Mockito e de integração através do Postman (no futuro, serão integrados como Newman em uma pipeline Jenkins).

Para rodar o projeto, basta rodar através de uma IDE dando run pelo DesafioTinnovaApplication ou baixando a imagem Docker do seguinte repositório do Docker Hub:

https://hub.docker.com/repository/docker/lucasbjacob/desafio_tinnova

baixando a imagem, deve-se rodar através do comando

docker pull lucasbjacob/desafio_tinnova

o projeto pode ser testado via Swagger pelo endpoint

http://localhost:8080/swagger-questao5.html

O projeto se baseia em 5 questões respondidas dentro dos packages.

A questão 5 conta com uma consulta na Cars API (https://carsapi1.docs.apiary.io/#reference/0/engines-collection/list-all-manufacturers) para busca do nome de marcas de carro.

#ENDPOINTS:
Veiculo:
POST localhost:8080/veiculos - criar Veiculo
GET localhost:8080/veiculos - buscar todos os veiculos (possui paginação)
GET localhost:8080/veiculos/{id} - buscar veiculo por id
PUT localhost:8080/veiculos/{id} - atualizar veiculo
PATCH localhost:8080/veiculos/{id} - atualizar fields do veiculo
DELETE localhost:8080/veiculos/{id} - deletar veiculo
