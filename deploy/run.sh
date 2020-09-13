cd ../
mvn clean package
nohup java -jar target/longsalix-web.jar > deploy/server.log 2>&1 &