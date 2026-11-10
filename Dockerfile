# Giai đoạn 1: Build code
FROM maven:3.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# Build code
RUN mvn clean package -DskipTests

# Giai đoạn 2: Chạy Tomcat
FROM tomcat:10.1-jdk17-temurin
RUN rm -rf /usr/local/tomcat/webapps/*
# Dùng /*.war để tự động tìm file war sinh ra
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]