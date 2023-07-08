# Welcome to RealCoders

### real-coder-linkedin-spring-3

This is a LinkedIn clone made with the help of my mentor for me to better understand how Spring Security works behind the scenes. in this project, I have implemented spring security, built myself JWT token and JWT authentication filter. I also use JDBC template to connect to PostgreSQL db for a better understanding of SQL statements. after registration and user and password authentication, the user gets JWT token, with that token user can access secured endpoint /companies/dummy and create a company. this endpoint is secured via jwtfilterconfig class and gives access only to authorized users with tokens. I use threads to identify which user is currently sending a company registration request to API. extract username method is used in the filter to get the username from token, find the user from db and create a separate thread for that user with ThreadLocal class to make API requests.

### JWT Token Implementation

The token is divided into three parts: header, payload and signature. the header contains info about the algorithm and token type, the payload contains user id, username and expiration date of the token. In signature part, I encrypt header + payload and my 256-bit secret key with the SHA-256 algorithm. then I encode all this with a Base64 encoder and that's how I create my token.I use string and byte arrays to build a token and JsonNode class to extract claims from it: username and expiration date (this is not the best way to build and extract it but it was convenient for me at the moment). decoder method is a list of strings and saves header payload and signature separately.


Requirements:


* JDK 17
* Maven 3.*

Run:
* Simple run as spring boot app from IDE.
* In case need build run "mvn clean install"

