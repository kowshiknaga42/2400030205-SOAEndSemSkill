
package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.*;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {

    private final JwtUtil jwtUtil;

    public TouristController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // Generate token for testing
    @GetMapping("/token")
    public String getToken() {
        return jwtUtil.generateToken("Aditya");
    }

    // Fetch all tourists
    @GetMapping
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<?> getAllTourists(
        @RequestHeader("Authorization") String auth) {

        String token = auth.replace("Bearer ", "");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401)
                .body("Invalid Token");
        }

        return ResponseEntity.ok(
            Arrays.asList("Tourist 1",
                          "Tourist 2",
                          "Tourist 3"));
    }
}
