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