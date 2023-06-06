# 개인 맞춤형 채식 쇼핑몰 웹 애플리케이션, 채식이들 🥗

## Server

> ### 프로젝트 목적
> 1. Java, Spring, Security, JPA 기술 학습
> 2. 프론트엔드 팀과 효율적인 협업을 위한 고민
> 3. 기획과 요구사항에 맞는 기술 구현

### 개발 환경

---

* IntelliJ IDEA 2022.1.1 ~ 2022.1.3
* Java 11
* Spring Boot 2.7.3
* Gradle 8.0

### 기술 세부 스택

---

Spring Boot
* Spring Data JPA
* Spring Security
* JWT
* H2 Database
* MySQL Driver
* Lombok
* Spring validation
* Spring Rest DOCS

그 외
* QueryDSL 

### API 명세서

* (Springfox) Swagger
* Swagger UI

> **Swagger 선택 이유**
> 프로젝트 초기에는 SpringRestDocs 를 사용함. 코드 및 서비스가 확장될 수록 테스트 오류가 심해짐 <br>
> MockMVC 사용으로 인해 SpringSecurity 설정이 제외됨. 따라서 모든 테스트가 401 에러 출력 <br>
> `@Import`와 `@WithMockUser` 를 사용하여 문제를 해결하며 문서 자동화 진행 <br>
> MockBean에서 NullPointerException 발생 <br>
> <br>
> **Swagger 로 전략 변경**
> 1) 많은 도메인의 api 문서를 처음부터 만들면서, 테스트까지 통과시켜야하니 task 부담이 큼
> 2) Spring rest docs는 tdd 에 더 적합한 느낌
> 3) 현재 우선순위는 API 문서를 빠르게 구현하는 것이기 때문에 Swagger 결정

---

### 배포

---

* 예정

### 데모 페이지

---

비용 절감을 위해 클라우드 서버가 자동 슬립 모드로 설정되어 있습니다. 링크를 클릭하면 서비스가 깨어납니다. 따라서 첫번째 페이지 오픈시 다소 늦을 수 있습니다.

* 링크 예정

