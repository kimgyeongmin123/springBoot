##### -------------------------- 
### Thymeleaf 설정 
##### --------------------------
#### 1) Dependency Setting(build.gradle)
##### 	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
#### 2) Thymeleaf Setting(application.properties)
##### # Thymeleaf 설정
##### spring.thymeleaf.prefix: classpath:/templates/
##### spring.thymeleaf.suffix: .html
##### spring.thymeleaf.mode: HTML5
##### spaing.thymeleaf.cache:false
##### # 중요!(JSP와 구별위한코드)
##### spring.thymeleaf.view-names:thymeleaf/*
#### 3) File 생성
##### resources/templates/thymeleaf/index.html 생성 후 Controller로 확인해보기
