# crawler-mvc

# application uses MySQL so please create database 
# running following script before starting application:
# CREATE DATABASE IF NOT EXISTS fastovezz_db DEFAULT CHARACTER SET = 'utf8' DEFAULT COLLATE 'utf8_general_ci'

# maven is used to manage project dependencies
# but it was unable to download c3p0-0.9.2.1.jar
# so I downloaded it manually and placed under
# my local maven repository

# to start application run following commands under project root directory:
# 1. mvn clean package
# 2. java -jar target/crawler-mvc-0.1.0.jar 
# 3. open your browser and enter url: http://localhost:8090/products
