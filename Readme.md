# Spring + API Open-Meteo

Este projeto representa um estudo sobre Java e Spring Boot. Neste foi desenvolvida uma conexão com a API Open-Meteo, juntamente com um formulário o qual o usuário poderá digitar a latitude e a longitude de uma localidade, então o sistema retornará uma tabela com o horário e a temperatura em graus. 

## Tecnologias Utilizadas

### Backend

* **[Spring Boot](https://www.django-rest-framework.org/)** : Framework poderoso para a construção da estrutura e conexões web.
* **[Spring Initializr](https://start.spring.io/)** : Ferramenta para a criação de projetos rápidos com Spring Boot.
* **Java** : Linguagem de programação utilizada para a construção do backend.

### Frontend

* **HTML** : Tecnologia utilizada para a criação do conteúdo da página web/hipermedia.
* **CSS** : Tecnologia utilizada para a estilização do conteúdo.

## Instalação

### Requisitos

* Java 23+
* Maeven 3.9.9+
* Spring Boot 3.3.4+

### Passo a passo da instalação

#### 1. Clone o repositório

    git clone https://github.com/daniwells/Desafio-Open-Meteo.git

#### 2. Configurar Ambiente

1. Navegue até a raíz do projeto, no diretório "Final"

2. Instale as dependências:
        
       mvn clean install
       mvn dependency:purge-local-repository

3. Inicie o servidor de desenvolvimento:

       mvn spring-boot:run

Agora você pode acessar o projeto no seu navegador em `http://localhost:8082`.

## Licença

Este projeto está licenciado sob a licença MIT.

## Autoria

Este projeto foi desenvolvido por:

* Daniel Lima;
