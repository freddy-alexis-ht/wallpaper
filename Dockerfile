FROM maven as build
WORKDIR /app
COPY . .
RUN mvn -f /app/pom.xml clean package -DskipTests

FROM openjdk:13-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/wallpaper.jar
EXPOSE 7070
CMD [ "java","-jar","wallpaper.jar" ]