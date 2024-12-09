package com.feedbackFusion.controller;

import com.feedbackFusion.dto.AuthDTO;
import com.feedbackFusion.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@RequestBody AuthDTO authDTO) {
        AuthDTO authenticatedUser = authService.autenticar(authDTO);
        return ResponseEntity.ok(authenticatedUser);
    }
}
