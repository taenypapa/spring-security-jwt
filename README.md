# spring-security-jwt

## 1. 공통 사항

    * 버전: Spring Boot 2.5.14.RELEASE
    * JPA + spring-boot-configuration-processor 사용
    * 자바 11(Openjdk 11)


## 2. Controller

    * @RestController: ResponseEntity를 활용하여 json 기반 Api 호출을 위해 사용
    * @RequestMapping: URL 매핑을 위해 사용
    * @AllArgsConstructor: @Autowired 없이 자동 주입을 위해 사용


## 3. BaseEntity

    * @PrePersist: DB로 저장 되기 전 호출
    * @PreUpdate: DB update 되기 전 호출


## 4. DataInitializer

    * 서버 재기동 직후 호출
    * 데이터 초기화 등에 사용


## 참고. 개발 패턴
### 1) 기본 구조: REST API + HATEOAS + JPA + JWT
<pre>
  (1) REST API: 테이블 entity 명과 자원 명 통일
    - 참고: https://meetup.toast.com/posts/92
    
  (2) HATEOAS: API 내 전체 참고 url 제공
    - 참고: https://pjh3749.tistory.com/260
  
  (3) JPA: orm 집합체, 기존 XML 기반 SQL 대체, 간결한 구조
    - 예시 코드: https://github.com/spring-projects/spring-data-examples/tree/master/jpa/example/src/main/java/example/springdata/jpa
    - 참고: https://gmlwjd9405.github.io/2019/08/04/what-is-jpa.html
      
  (4) JWT(Json Web Token): json 기반 인증 관리 기술, 쿠키로 인증 관리, 프론트엔드/백엔드 분리시 주로 사용
    - 참고: https://jwt.io/introduction
    - token, https://jwt.io/에서 확인
</pre>

### 2) entity의 기본 ID는 @ID를 사용하고 변수 타입과 변수 명은 Long seq로 통일

    (1) entity의 기본 ID는 @ID를 사용하고 변수 타입과 변수 명은 Long seq로 통일
        - PageObject 기본 정렬: seq,desc
    (2) 데이터 저장/변경/삭제 이력은 savedAt, modifiedAt, deletedAt으로 통일

### 3) REST API URI 규칙
#### - Method 별 역할

    @GET: 단건/다건 조회, 단건 조회시 id 또는 seq를 사용
    @POST: 데이터 최초 저장
    @PUT: 데이터 전체 업데이트
    @PATCH: 데이터 일부 업데이트(비밀번호, 로그인 실패 횟수 등)
    @DELETE: 데이터 삭제

##
#### - 시작은 버전을 나타내는 문구로 시작한다.(상황에 따라 /v1, /v2 여러 버전 동시에 운영 될 수도 있음)

    ex) 버전으로 시작: /v1/**

##
#### - 기본적으로 명사형을 사용하고, 명사형은 집합명사 등 특이한 경우를 제외하고는 복수형으로 한다.

**예시**

    명사형: /v1/manage(X) >> /v1/managers(O)
    복수형: /v1/account(X) >> /v1/accounts(O)
    집합명사: /v1/people(X) >> /v1/persons(O)

    예외: 데이터를 관리 하기 위해서는 동사형 허용
    /v1/accounts/duplicate

##
#### - url을 통해 업무를 파악할 수 있도록 약자 사용은 지양한다.
    /v1/mgr(X) >> /v1/managers

##
#### - 언더바(_) 대신 하이픈(-)을 사용한다.
    /v1/registration_requests >> /v1/registration-requests

##
#### - 소문자만 사용한다.(카멜 표기법 대신 케밥 표기법 사용)
    /v1/registrationRequests >> /v1/registration-requests

#
### 4) REST API 객체 및 return 관리

Entity, Dto, Response 객체의 목적에 따른 분리
  
   * Entity: 테이블 생성 및 데이터 저장 등 데이터 삭제를 위한 용도
   * Dto: FE --> BE
   * Response: BE --> FE
   * 주의 사항: 민간한 정보인 password 등은 Response를 활용해 api로 노출할 때, 제거해야 한다.

#
### 5) Spring doc (SWAGGER 3.0)
Swagger를 활용하여 api 문서를 관리한다.

##
#### Controller - 기본

@Tag 어노테이션을 사용한다.
name: 컨트롤러 명, description: 설명

```
@Tag(name = "AccountController", description = "계정관리")
public class AccountController{
    ...
}
```

##
#### Controller - Method
<pre>
@Operation, @Parameters + @Parameter, @ApiResponses + @ApiResponse 어노테이션을 사용한다.
name: 컨트롤러 명, description: 설명
</pre>
  
```java
@Operation(summary = "계정 목록 조회", description = "return AccountResource")
@Parameters({
    @Parameter(name = "page", description = "페이지번호", required = false, example = "0"),
    @Parameter(name = "size", description = "페이지사이즈", required = false, example = "10"),})
	
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "성공"),
    @ApiResponse(responseCode = "403", description = "접근권한부족"),
    @ApiResponse(responseCode = "406", description = "관리권한부족"),})
```

##
#### Entity, Dto, Response
@Schema 어노테이션을 사용한다.

```java
@Schema(description = "사용자 계정 도메인")
public class AccountDto {

    @Schema(required = true, example = "tuser@test.com",description = "이메일")
    private String email;

    @Schema(required = true, example = "help-you",description = "이름")
    private String name;

    @Schema(required = true, example = "1234",description = "비밀번호")
    private String password; 

    @Schema(required = true, example = "USER",description = "권한")
    private String role;

    @Schema(required = true, example = "Y",description = "사용여부")
    private String isEnabled;
}
```

##
#### Swagger 보안
@SecurityRequirement(name = "bearerAuth") 선언한 Controller만 Oauth2 인증을 사용 가능하다.
현재는 implements BaseController 한 Controller는 일괄 적용된다.

주의사항: OpenApiConfig에 선언한 보안 설정(bearerAuth)이랑 값이 같아야 한다.

```java
  @OpenAPIDefinition(
          servers = {
                  @Server(url = "http://localhost:8080", description = "Local"),
          },
          info = @Info(title = "API 명세서",
                  description = "API 명세서",
                  version = "v1",
                  contact = @Contact(name = "taeny"),
                  license = @License(name = "Apache 2.0",
                  url = "http://www.apache.org/licenses/LICENSE-2.0.html")
          ))
  @SecurityScheme(
          name = "bearerAuth",
          type = SecuritySchemeType.HTTP,
          bearerFormat = "JWT",
          scheme = "bearer"
  )
  @Configuration
  public class OpenApiConfig {

      /**
       * customGameOpenApi.
       * @return GroupedOpenApi
       */
      @Bean
      public GroupedOpenApi myOpenApi() {
          String[] paths = {"/v1/**"};
          return GroupedOpenApi.builder().group("Data API").pathsToMatch(paths)
                  .build();
      }

  }
```