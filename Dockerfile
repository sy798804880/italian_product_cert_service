FROM openjdk:17
ADD ./cert-admin/target/cert-admin.jar app.jar
EXPOSE 8009
ENTRYPOINT ["java","-jar","app.jar"]
