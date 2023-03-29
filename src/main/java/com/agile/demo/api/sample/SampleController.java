package com.agile.demo.api.sample;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/api/samples")
public class SampleController {
    @GetMapping
    public ResponseEntity<HashMap<String, String>> gets(){
        HashMap<String, String> result = new HashMap<>();
        result.put("id", "sample");
        return ResponseEntity.ok().body(result);
    }
}
