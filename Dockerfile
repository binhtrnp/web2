
FROM tomcat:10.1-jdk21-temurin

# Xóa các ứng dụng mặc định của Tomcat để nhẹ hơn (tùy chọn)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy file .war từ thư mục target (sau khi build) vào Tomcat
# Đổi tên thành ROOT.war để ứng dụng chạy ở đường dẫn gốc (ví dụ: https://ten-app.onrender.com/)
COPY target/ch04_exl_survey.war /usr/local/tomcat/webapps/ROOT.war

# Mở port 8080 (Render sẽ tự động map port này ra ngoài)
EXPOSE 8080

# Lệnh khởi chạy Tomcat
CMD ["catalina.sh", "run"]