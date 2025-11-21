# Spring Security(SpringBoot)
- Spring Boot version : 3.5.8
- Name: `security`
- Language: Java
- Type: Gradle - Groovy
- Group: `com.test`
- Artifact: `security`
- Package name: `com.test.security`
- Java: 17
- Packaging: jar
- Dependencies:
  - Spring Web
  - Lombok
  - Oracle Driver
  - Thymeleaf
  - Spring Boot DevTools
  - Spring Data JPA
  - Spring Security

## 파일 생성하기
- com.test.security.controller
  - `MainController.java`
  - `AuthController.java`: 로그인 기능 구현하기
  - `MemberController.java` : 회원 가입 기능 구현하기
- com.test.security.dto
  - `MemberDTO.java`
- com.test.security.entity
  - `Member.java`
- com.test.security.repository
  - `MemberRepository.java`(I)
- com.test.security.service
  - `MemberService.java`

- template
  - `template.html`
  - `index.html`: 시작 페이지
  - `member.html`: 회원 페이지
  - `admin.html`: 관리자 페이지
  - `login.html`: 로그인 페이지
  - `denied.html`: 403 Forbidden 처리 페이지
  - `join.html`: 회원 가입 페이지
- template/inc
    - `header.html` : 공통 메뉴

## 스프링부트 시큐리티 설정하기
- Spring Boot에서는 XML 대신 Config.java로 제어하는 편
- com.test.security.config
  - `SecurityConfig.java`

### Spring Boot Security의 특징
- 기본으로 `user` 계정이 제공된다.
- 비밀번호는 서버 시작 시 콘솔에 찍힘
- 기본으로 모든 페이지가 인증 사용자만 접근할 수 있다.


DB 기반 로그인
- com.test.security.dto > "CustomUserDetails.java"
- com.test.security.service > "CustomUserDetailsService.java"

- 기존 사용중인 In-memory > 주석