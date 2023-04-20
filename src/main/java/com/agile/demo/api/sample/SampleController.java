package com.agile.demo.api.sample;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/me")
@AllArgsConstructor
public class SampleController {

    private SampleRepository sampleRepository;
    @GetMapping
    public ResponseEntity<?> gets(HttpHeaders httpHeaders){
        /** valid token*/
        //return ResponseEntity.ok().build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
