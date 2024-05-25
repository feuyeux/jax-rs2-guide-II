<!-- markdown-disable MD033 MD045 -->
<!-- markdownlint-disable MD033 -->

# 《Java Restful Web Service实战·第二版》示例源代码

[![codecov](https://codecov.io/gh/feuyeux/jax-rs2-guide-II/branch/master/graph/badge.svg)](https://codecov.io/gh/feuyeux/jax-rs2-guide-II)

<img width="333" src="cover.png" alt="book cover png"/>

## Build

```bash
git clone https://github.com/feuyeux/jax-rs2-guide-II.git
cd jax-rs2-guide-II
mvn clean install -DskipTests
```

## 源代码目录

1. [1.5.my-first-service](1.5.my-first-service)
2. [1.5.my-first-webapp](1.5.my-first-webapp)
3. [1.6.1.myrest-servlet2-webxml](1.6.1.myrest-servlet2-webxml)
4. [1.6.2.myrest-servlet3-webxml](1.6.2.myrest-servlet3-webxml)
5. [1.6.3.myrest-subservlet](1.6.3.myrest-subservlet)
6. [1.6.4.myrest-servlet3-application](1.6.4.myrest-servlet3-application)
7. [1.6.5.myrest-servlet2-rc](1.6.5.myrest-servlet2-rc)
8. [1.6.6.myrest-servlet2-application](1.6.6.myrest-servlet2-application)
9. [1.6.7.myrest-servlet3-application](1.6.7.myrest-servlet3-application)
10. [2.simple-service-3](2.simple-service-3)
11. [2.2.simple-service-jackson](2.2.simple-service-jackson)
12. [2.3.6-1.simple-service-moxy](2.3.6-1.simple-service-moxy)
13. [2.3.6-2.simple-service-jsonp](2.3.6-2.simple-service-jsonp)
14. [2.3.6-3.simple-service-jackson](2.3.6-3.simple-service-jackson)
15. [2.3.6-4.simple-service-jettison](2.3.6-4.simple-service-jettison)
16. [2.3.simple-service-jettison](2.3.simple-service-jettison)
17. [2.simple-service-3](2.simple-service-3)
18. [3.jaxrs2-handle](3.jaxrs2-handle)
19. [4.2 JAX-RS2的异步机制](4.2.asynchronized)
20. [4.4 基于HTML5的异步通信](4.4.sse)
21. [5 REST 客户端](5.jaxrs2-client)
22. [6.rest-test](6.rest-test)
23. [7.2 Spring-boot基础](7.2.demo)
24. [7.3.1 Spring Cloud Zookeeper示例](7.3.1.boot.zookeeper)
25. [7.3.2 Spring Cloud Consul示例](7.3.2.boot.consul)
26. [7.3.3 Spring Cloud Etcd示例](7.3.2.boot.etcd)
27. [8.2 REST服务的容器化](8.2.containerization)
28. [8.3 容器化微服务](8.3.boot.zk.kafka)
29. [9.simple-service](9.simple-service)
30. [10.3.security-rest](10.3.security-rest)
31. [10.5.oauth2-rest](10.5.oauth2-rest)

## Jersey官方文档

[**Jersey User Guide**](https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest/index.html)

## Jersey问答

[**Jersey StackOverflow**](http://stackoverflow.com/questions/tagged/jersey)

## Jersey Maven仓库

**[GlassFish Jersey Core](https://mvnrepository.com/artifact/org.glassfish.jersey.core)**

## Jersey发布历史

<https://projects.eclipse.org/projects/ee4j.jersey>

| 版本号                                                       | 发布日期   | 发版说明                                                     |
| :----------------------------------------------------------- | :--------- | :----------------------------------------------------------- |
| **Jakarta EE 11**<br />[Jakarta RESTful Web Services 4.0](https://jakarta.ee/specifications/restful-ws/4.0/) |            |                                                              |
| 4.0.0                                                        | 2024-04-10 | [Jersey 4.0.0](https://projects.eclipse.org/projects/ee4j.jersey/releases/4.0.0) |
| **Jakarta EE10**<br />[Jakarta RESTful Web Services 3.1](https://jakarta.ee/specifications/restful-ws/3.1/) |            |                                                              |
| 3.1.6                                                        | 2024-04-05 | [Jersey 3.1.6](https://projects.eclipse.org/projects/ee4j.jersey/releases/3.1.6) |
| 3.1.5                                                        | 2023-12-12 | [Jersey 3.1.5](https://projects.eclipse.org/projects/ee4j.jersey/releases/3.1.5) |
| 3.1.3                                                        | 2023-07-19 | [Jersey 3.1.3](https://projects.eclipse.org/projects/ee4j.jersey/releases/3.1.3) |
| 3.1.2                                                        | 2023-05-17 | [Jersey 3.1.2](https://projects.eclipse.org/projects/ee4j.jersey/releases/3.1.2) |
| 3.1.1                                                        | 2023-01-31 | [Jersey 3.1.1](https://projects.eclipse.org/projects/ee4j.jersey/releases/3.1.1) |
| 3.1.0                                                        | 2022-06-22 | [Jersey 3.1.0](https://projects.eclipse.org/projects/ee4j.jersey/releases/3.1.0) |
| **Jakarta EE9**<br />[Jakarta RESTful Web Services 3.0](https://jakarta.ee/specifications/restful-ws/3.0/) |            |                                                              |
| 3.0.11                                                       | 2023-07-12 | [Jersey 3.0.11](https://projects.eclipse.org/projects/ee4j.jersey/releases/3.0.11) |
| 3.0.10                                                       | 2023-03-31 | [Jersey 3.0.10](https://projects.eclipse.org/projects/ee4j.jersey/releases/3.0.10) |
| 3.0.9                                                        | 2022-12-23 | [Jersey 3.0.9](https://projects.eclipse.org/projects/ee4j.jersey/releases/3.0.9) |
| 3.0.8                                                        | 2022-09-02 | [Jersey 3.0.8](https://projects.eclipse.org/projects/ee4j.jersey/releases/3.0.8) |
| 3.0.5                                                        | 2022-06-15 | [Jersey 3.0.5](https://projects.eclipse.org/projects/ee4j.jersey/releases/3.0.5) |
| 3.0.4                                                        | 2022-02-09 | [Jersey 3.0.4](https://projects.eclipse.org/projects/ee4j.jersey/releases/3.0.4) |
| 3.0.2                                                        | 23/Aug/21  | [Jersey 3.0.2](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/3.0.2.html) |
| 3.0.1                                                        | 27Jan/21   | [Jersey 3.0.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/3.0.1.html) |
| 3.0.0                                                        | 01/Dec/20  | [Jersey 3.0.0](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/3.0.0.html) |
| **Jakarta EE8**<br />[Jakarta RESTful Web Services 2.1](https://jakarta.ee/specifications/restful-ws/2.1/) |            |                                                              |
| 2.42                                                         | 2024-03-19 | [Jersey 2.42](https://projects.eclipse.org/projects/ee4j.jersey/releases/2.42) |
| 2.41                                                         | 2023-10-20 | [Jersey 2.41](https://projects.eclipse.org/projects/ee4j.jersey/releases/2.41) |
| 2.40                                                         | 2023-06-23 | [Jersey 2.40](https://projects.eclipse.org/projects/ee4j.jersey/releases/2.40) |
| 2.39                                                         | 2023-02-17 | [Jersey 2.39](https://projects.eclipse.org/projects/ee4j.jersey/releases/2.39) |
| 2.38                                                         | 2022-12-22 | [Jersey 2.38](https://projects.eclipse.org/projects/ee4j.jersey/releases/2.38) |
| 2.37                                                         | 2022-09-06 | [Jersey 2.37](https://projects.eclipse.org/projects/ee4j.jersey/releases/2.37) |
| 2.36                                                         | 2022-06-13 | [Jersey 2.36](https://projects.eclipse.org/projects/ee4j.jersey/releases/2.36) |
| 2.35                                                         | 2021-09-03 | [Jersey 2.35](https://projects.eclipse.org/projects/ee4j.jersey/releases/2.35) |
| 2.34                                                         | 16/Aug/21  | [Jersey 2.34](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.34.html) |
| 2.33                                                         | 18/Dec/20  | [Jersey 2.33](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.33.html) |
| 2.32                                                         | 25/Sep/20  | [Jersey 2.32](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.32.html) |
| 2.31                                                         | 22/May/20  | [Jersey 2.31](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.31.html) |
| 2.30.1                                                       | 21/Feb/20  | [Jersey 2.30.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.30.1.html) |
| 2.30                                                         | 10/Jan/20  | [Jersey 2.30](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.30.html) |
| 2.29.1                                                       | 10/Sep/19  | [Jersey 2.29.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.29.1.html) |
| 2.29                                                         | 12/Aug/19  | [Jersey 2.29](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.29.html) |
| 2.28                                                         | 25/Jan/19  | [Jersey 2.27](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.28.html) |
| 2.27                                                         | 13/Apr/18  | [Jersey 2.27](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.27.html) |
| 2.26                                                         | 06/Sep/17  | [Jersey 2.26](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.26.html) |
| 2.25                                                         | 08/Dec/16  | [Jersey 2.25](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.25.html) |
| 2.24.1                                                       | 21/Nov/16  | [Jersey 2.24.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.24.1.html) |
| 2.24                                                         | 27/Oct/16  | [Jersey 2.24](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.24.html) |
| 2.23.2                                                       | 08/Aug/16  | [Jersey 2.23.2](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.23.2.html) |
| 2.23.1                                                       | 07/Jun/16  | [Jersey 2.23.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.23.1.html) |
| 2.23                                                         | 18/May/16  | [Jersey 2.23](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.23.html) |
| 2.22.2                                                       | 16/Feb/16  | [Jersey 2.22.2](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.22.2.html) |
| 2.22.1                                                       | 07/Oct/15  | [Jersey 2.22.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.22.1.html) |
| 2.22                                                         | 23/Sep/15  | [Jersey 2.22](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.22.html) |
| 2.21.1                                                       | 16/Sep/15  | [Jersey 2.21.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.21.1.html) |
| 2.21                                                         | 15/Aug/15  | [Jersey 2.21](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.21.html) |
| 2.20                                                         | 06/Aug/15  | [Jersey 2.20](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.20.html) |
| 2.19                                                         | 29/Jun/15  | [Jersey 2.19](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.19.html) |
| 2.18                                                         | 05/Jun/15  | [Jersey 2.18](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.18.html) |
| 2.17                                                         | 11/Mar/15  | [Jersey 2.17](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.17.html) |
| 2.16                                                         | 11/Feb/15  | [Jersey 2.16](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.16.html) |
| 2.15                                                         | 03/Dec/14  | [Jersey 2.15](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.15.html) |
| 2.14                                                         | 01/Oct/14  | [Jersey 2.14](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.14.html) |
| 2.13                                                         | 30/Sep/14  | [Jersey 2.13](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.13.html) |
| 2.12                                                         | 26/Aug/14  | [Jersey 2.12](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.12.html) |
| 2.11                                                         | 23/Jul/14  | [Jersey 2.11](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.11.html) |
| 2.10.1                                                       | 01/Jul/14  | [Jersey 2.10.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.10.1.html) |
| 2.10                                                         | 24/Jun/14  | [Jersey 2.10](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.10.html) |
| 2.9.1                                                        | 30/May/14  | [Jersey 2.9.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.9.1.html) |
| 2.9                                                          | 23/May/14  | [Jersey 2.9](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.9.html) |
| 2.8                                                          | 29/Apr/14  | [Jersey 2.8](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.8.html) |
| 2.7                                                          | 13/Mar/14  | [Jersey 2.7](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.7.html) |
| 2.6                                                          | 19/Feb/14  | [Jersey 2.6](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.6.html) |
| 2.5.1                                                        | 02/Jan/14  | [Jersey 2.5.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.5.1.html) |
| 2.5                                                          | 18/Dec/13  | [Jersey 2.5](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.5.html) |
| 2.4.1                                                        | 08/Nov/13  | [Jersey 2.4.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.4.1.html) |
| 2.4                                                          | 25/Oct/13  | [Jersey 2.4](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.4.html) |
| 2.3.1                                                        | 27/Sep/13  | [Jersey 2.3.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.3.1.html) |
| 2.3                                                          | 20/Sep/13  | [Jersey 2.3](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.3.html) |
| 2.2                                                          | 14/Aug/13  | [Jersey 2.2](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.2.html) |
| 2.1                                                          | 15/Jul/13  | [Jersey 2.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.1.html) |
| 2.0.1                                                        | 03/Jan/14  | [Jersey 2.1](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.0.1.html) |
| 2.0                                                          | 28/May/13  | [Jersey 2.0](https://eclipse-ee4j.github.io/jersey.github.io/release-notes/2.0.html) |

- **[JAX-RS (JSR311)](https://jcp.org/en/jsr/detail?id=311)**
- **[JAX-RS 2.0 (JSR339)](https://jcp.org/en/jsr/detail?id=339)**
- **[JAX-RS 2.1 (JSR370)](https://jcp.org/en/jsr/detail?id=370)**

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=feuyeux/jax-rs2-guide-II&type=Date)](https://star-history.com/#feuyeux/jax-rs2-guide-II&Date)
