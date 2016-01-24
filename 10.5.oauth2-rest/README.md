oauth2-rest
==================

## start server and client
```
 mvn clean install -DskipTests && cd myclient && mvn tomcat:run
```


## access list from client

```
http://localhost:8080/myclient/
```

## rest resources on server

```
http://localhost:8080/myserver/rest/hello
http://localhost:8080/myserver/rest/tarots
```