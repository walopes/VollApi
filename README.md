Based on course "Formação Java e Spring Boot", from [Alura](`https://www.alura.com.br/formacao-spring-boot-3`)

Some steps to be added later:
- Docker Image
- Testing feature

Package: `$ mvn clean package`
Run: `$ java -jar target/api-1.2.3-SNAPSHOT.jar`
Run in production: `$ java -Dspring.profiles.active=prod -DDATASOURCE_URL=jdbc:mysql://localhost/vollmed_api -DDATASOURCE_USERNAME=root -DDATASOURCE_PASSWORD=root -jar target/api-1.2.3-SNAPSHOT.jar`

Generating image with Native Image: `$ ./mvnw -Pnative native:compile`
