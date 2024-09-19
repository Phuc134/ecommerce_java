package org.example.ecommerce.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.dto.request.AuthenticationRequest;
import org.example.ecommerce.dto.response.AuthenticationResponse;
import org.example.ecommerce.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/login")
    public AuthenticationResponse createUser(@RequestBody AuthenticationRequest authRequest) throws JOSEException {
        return authenticationService.authenticate(authRequest);
    }
    @PostMapping("/introspect")
    public boolean introspect(@RequestBody Map<String, String> body) throws ParseException, JOSEException {
        String token = body.get("token");
        return authenticationService.introspect(token);
    }
}
