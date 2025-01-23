# Microservice POC

* Hello, I am developing this project as a Proof of Concept (POC) for a Microservice Architecture. 
* It incorporates key components such as a Load Balancer, Discovery Server, Circuit Breaker, and Rate Limiting. 
* Additionally, it includes JUnit testing, JaCoCo code coverage, Checkstyle for code quality, and Projektor for centralized visualization of test results and coverage metrics.

<hr/>

* Currently, I have 4 services:
  * EMPLOYEE-SERVICE : Provide employee details.
  * DISCOVERY-SERVICE : Used Eureka server for registering all micro-services.
  * ADDRESS-SERVICE : I am treating it as primary server available in same region, and can create multiple instances let's say for different zones, each zone will have one one instance and request will come in **Round Robin** form.
  * SECONDARY-ADDRESS-SERVICE : I am considering it as a backup server, it is present in different region, Request will delegate here when primary server is down, and when Primary server is come back then primary server serve the request, to achieve this i used **Circuit Breaker Pattern** through resilience4j.
  * LOGGING-SERVICE: I have also added logging service for central logging also used (kafka, logstash, and elasticsearch)

<hr/>

References: 
  - https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/#spring-cloud-feign-circuitbreaker-fallback
  - https://arnoldgalovics.com/feign-fallback/
  - https://rameshfadatare.medium.com/spring-boot-microservices-openfeign-example-with-e-commerce-574d1ef54443
  - https://javatechonline.com/how-to-implement-feign-client-in-spring-boot-microservices/
  - https://medium.com/@deepugeorge2007travel/feignclient-springboot-microservice-cfb29f6528e7
  - https://www.youtube.com/watch?v=b6R4dElDtRc&t=11s 
  - [Junit Jupiter Test](https://medium.com/@Lakshitha_Fernando/spring-boot-unit-testing-for-repositories-controllers-and-services-using-junit-5-and-mockito-def3ff5891be)
  - [JaCoCo Code Coverage](https://docs.gradle.org/current/userguide/jacoco_plugin.html)
  - [JaCoCo Code Coverage 2](https://github.com/BadrOuaddah/jacoco-tutorial/blob/master/README.md)

<hr/>

TroubleShoot: 
  - 
