# Giai đoạn 1: Dùng Maven để build code
FROM maven:3.8-eclipse-temurin-17 AS build
WORKDIR /app
# Copy toàn bộ source code vào
COPY . .
# Chạy lệnh build (bỏ qua test cho nhanh)
RUN mvn clean package -DskipTests

# Giai đoạn 2: Đưa file .war sang Tomcat 10
FROM tomcat:10.1-jdk21-temurin
# Xóa các app mặc định
RUN rm -rf /usr/local/tomcat/webapps/*
# Copy file .war từ Giai đoạn 1 sang Tomcat
# LƯU Ý: Tên file .war lấy theo artifactId và version trong pom.xml của bạn
COPY --from=build /app/target/web2-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]