package com.agile.demo.core.jwt;

import lombok.*;

import java.util.List;

@Builder
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class JwtPayload {
    private String userName;
    private List<String> scope;
    private Long exp;
    private List<String> authorities;
    private String jti;
    private String clientId;

    /** custom values */
    private String name;
    private String customValue;

}
