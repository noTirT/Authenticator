./mvnw clean package -DskipTests
cp target/authenticator-0.0.1-SNAPSHOT.jar src/main/docker

cd src/main/docker
docker-compose down
docker rmi authenticator:latest
docker-compose up