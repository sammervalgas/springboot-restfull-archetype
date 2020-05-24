# Springboot Restfull Template

## Sumário
Projeto Spring Boot Web Rest 
que possue todos os padrões de desenvolvimento
para a construção de uma API Restfull.

* Java 1.8+ 
* Maven 3.5+
* Spring Boot 2.1.13.RELEASE+
* [Spring Boot Cache](https://spring.io/guides/gs/caching/) 
* [Spring Boot Data JPA](https://docs.spring.io/spring-data/jpa/docs/2.1.17.RELEASE/reference/html/#_declaring_interfaces)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-endpoints)
* [JWT](src/main/java/br/com/samgit/configurations/security)
* [Lombok](https://projectlombok.org/features/Data)
* [Swagger 2](https://springfox.github.io/springfox/docs/current/#quick-start-guides)
* Database Mysql 8+
* Rest API Model
* Model Mapper
* SonarQube Code analysis
* RestAssured API Testing

## Requisitos

O projeto segue alguns requisitos de instalação para que 
funcione corretamente no ambitente local.

Siga as instruções de instalação abaixo:

* [Maven](https://maven.apache.org/install.html)
* [Lombok](https://projectlombok.org/setup/intellij)
* [Docker](https://docs.docker.com/get-docker/)
    * [Docker Compose](https://docs.docker.com/compose/install/)
    * [Mysql](docker/docker-compose.yml)
    * [SonarQube](docker/docker-compose.yml)
* [Postman](https://www.postman.com/downloads/)

## Modo de uso
Abaixo os comando de modo de uso para 
rodar o projeto em localhost.

```bash
git clone ...
cd springboot-restfull

# Sonarqube, Mysql e Adminer
cd docker && docker-compose up -d && cd ../

# Gerar pacote 
mvn clean package -DskipTests

# Rodar aplicação
java -jar target/springboot-restfull.jar

## Executar sonar
mvn sonar: sonar \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login=the-generated-token
```

## Endereços
Realizamos os testes através do Postman com os dados abaixo:


###### Autenticação: 
>http://localhost:8080/auth 
> 
> body:
> ```json
> {
>  "email" : "user@mail.com",
>  "pass" : "1234"
> }
> ```

###### Actuator
> http://localhost:8080/actuator/health

###### Swagger
> http://localhost:8080/swagger-ui.html

## Archetype do projeto

_Maven archetype é uma ferramenta de modelagem de projeto._

Archetype cria uma abstração do projeto 
e assim garante que o padrão construído seja facilmente
recriado contendo toda a estrutura definida.
 
**Exemplo:**

* Criar
    ```bash
     mvn archetype:create-from-project
     cd target/generated-sources/archetype
     mvn clean install
     ```

* Gerar
    ```bash
    mvn archetype:generate \
    -DarchetypeCatalog=local \
    -DgroupId=br.com.mypackage \
    -DartifactId=MyProjectName \
    -DinteractiveMode=false \
    -DarchetypeGroupId=br.com.svalgas \
    -DarchetypeArtifactId=springboot-restfull-archetype
    
    ```

## Documentacao API
A documentação do swagger pode ser acessada através do endereco **/swagger-ui.html**.

http://localhost:8080/swagger-ui.html

> Mais informacoes [aqui](HELP.md).
## Licença
* _Autor_: Sammer Valgas

