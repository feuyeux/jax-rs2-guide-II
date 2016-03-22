# 集成Spring、JPA和jQuery的Jersey2示例 #

## 1 数据库准备 ##
>jax-rs2-guide\sample\2\5simple-service-webapp-spring-jpa-jquery\manual\database.sql

## 2 WADL ##
>http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/application.wadl

## 3 RESTful接口 ##
#### 1 查询全部(没有分页功能) ####
>http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books GET

#### 2 主键查询(query) ####
>http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/book?{id} GET
#### 3 主键查询(path) ####
>http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/{bookId} GET
#### 4 更新 ####
>http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/{bookId} PUT
#### 5 删除 ####
>http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/{bookId} DELETE
#### 6 新增 ####
>http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books POST

## 4 架构简图 ##
>
jQuery <--ajax(JSON/XML) GET/POST/PUT/DELETE-->
>
Spring/JAX-RS(Entity/JAXB class :: annotation) <--ORM-->
>
JPA <--Native sql + JDBC-->
>
Mysql DB

## 5 cURL 访问示例 ##

### Accept:application/json ###

	curl -H "Accept:application/json" http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/book?id=1

	{"bookId":1,"bookName":"Java Restful Web Service实战","publisher":"cmpbook"}

### Get more than one ###

	curl http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/{1,2}
	C:\Users\Administrator>curl http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/{1,2}

	[1/2]: http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/1 --> <stdout>
	--_curl_--http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/1
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><book bookId="1" bookName="Java Restful Web Service实战" publisher="cmpbook"/>

	[2/2]: http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/2 --> <stdout>
	--_curl_--http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/2
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><book bookId="2" bookName="JSF2和RichFaces4使用指南" publisher="phei"/>

### post XML ###

	curl -v -X POST -H "Content-Type:application/xml" -H "Accept: application/xml" http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books --data-binary "<book bookName='JAX-RS2'/>"

	       > POST /simple-service-webapp-spring-jpa-jquery/webapi/books HTTP/1.1
	       > User-Agent: curl/7.26.0
	       > Host: localhost:8080
	       > Content-Type:application/xml
	       > Accept: application/xml
	       > Content-Length: 26
	       >
	       * upload completely sent off: 26 out of 26 bytes
	       < HTTP/1.1 200 OK
	       < Server: Apache-Coyote/1.1
	       < Content-Type: application/xml
	       < Content-Length: 93
	       < Date: Sun, 01 Sep 2013 05:30:59 GMT
	       <
	       <?xml version="1.0" encoding="UTF-8" standalone="yes"?><book bookId="14" bookName="JAX-RS2"/>
### PUT XML ###

	curl -v -X PUT -H "Content-Type:application/xml" -H "Accept: application/xml" http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/2 --data-binary "<book bookName='JAX-RS2' publisher='CMP'/>"

	       > PUT /simple-service-webapp-spring-jpa-jquery/webapi/books/2 HTTP/1.1
	       > User-Agent: curl/7.26.0
	       > Host: localhost:8080
	       > Content-Type:application/xml
	       > Accept: application/xml
	       > Content-Length: 42
	       >
	       * upload completely sent off: 42 out of 42 bytes
	       < HTTP/1.1 200 OK
	       < Server: Apache-Coyote/1.1
	       < Content-Type: application/xml
	       < Content-Length: 108
	       < Date: Sun, 01 Sep 2013 05:36:13 GMT
	       <
	       <?xml version="1.0" encoding="UTF-8" standalone="yes"?><book bookId="2" bookName="JAX-RS2" publisher="CMP"/>


### PUT json ###

	curl -v -X PUT -H "Content-Type:application/json" http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books/3 -d "{\"bookName\":\"JAX-RS2\",\"publisher\":\"CMP\"}"

	       > PUT /simple-service-webapp-spring-jpa-jquery/webapi/books/3 HTTP/1.1
	       > User-Agent: curl/7.26.0
	       > Host: localhost:8080
	       > Accept: */*
	       > Content-Type:application/json
	       > Content-Length: 40
	       >
	       * upload completely sent off: 40 out of 40 bytes
	       < HTTP/1.1 200 OK
	       < Server: Apache-Coyote/1.1
	       < Content-Type: application/xml
	       < Content-Length: 108
	       < Date: Mon, 16 Sep 2013 13:46:27 GMT
	       <
	       <?xml version="1.0" encoding="UTF-8" standalone="yes"?><book bookId="3" bookName="JAX-RS2" publisher="CMP"/>

### Summary ###

	curl -H "Accept: application/json" http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books
	{"bookList":{"book":[{"bookId":1,"bookName":"Java Restful Web Service实战","publisher":"cmpbook"},{"bookId":2,"bookName":"JSF2和RichFaces4使用指南","publisher":"phei"}]}}


	curl -H "Accept: application/xml" http://localhost:8080/simple-service-webapp-spring-jpa-jquery/webapi/books
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?><books><bookList><book bookId="1" bookName="Java Restful Web Service实战" publisher="cmpbook"/><book bookId="2" bookName="JSF2和RichFaces4使用指南" publisher="phei"/></bookList></books>
