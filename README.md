# shorturl
InfraCloud

1. Developed REST API on Spring Boot Application 
2. Created POST service with following Endpoint and body payload  
    1. Endpoint - /short (e.g. - http://localhost:8080/short) 
    2. Body - Content-type - application/JSON 
    { 
       "url" : "https://github.com/sandeep2244/" 
    } 

3. Conversion logic of URL is based on convert memory sequence number base10 to base62 format to generate uniquid  
4. Service will store data successfully on Text file in the following format  
    Row1 -  [Id] | [UniqueId] | [shortenUrl] | [LongUrl] 
5. Local text file location set to - "C:\File\shortURL.txt"  
   if need to update location , can update constant  "FILENAME" on repository/URLRepository.java  file
6. Created Docker image with help of Docker file  on project root folder
    1. Build - docker build -t shorturl:latest . 
    2. Run - docker run -d -p 8080:8080 -t shorturl:latest . 
    3. docker ps // For checking, image is up?  
7. For Rerun project or import on IDE , run following commands  respectively 
    1. mvn clean  
    2. mvn install


