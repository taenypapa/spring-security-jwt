package com.agile.demo;

import com.agile.demo.core.jwt.JwtPayload;
import com.agile.demo.core.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/me")
@AllArgsConstructor
public class TokenController {

    @GetMapping
    public ResponseEntity<?> getMe(HttpServletRequest request ) {
        // 계정 조회하기
        JwtPayload jwtPayload = JwtUtils.initJwtPayload(JwtUtils.getJwtToken(request));

        Map<String, String> result = new HashMap<>();
        result.put("name", Objects.requireNonNull(jwtPayload).getName());
        result.put("userId", Objects.requireNonNull(jwtPayload).getUserName());
        result.put("customValue", Objects.requireNonNull(jwtPayload).getCustomValue());

        return ResponseEntity.ok().body(result);
    }
}
