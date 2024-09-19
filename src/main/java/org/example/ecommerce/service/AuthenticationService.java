package org.example.ecommerce.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.dto.request.AuthenticationRequest;
import org.example.ecommerce.dto.response.AuthenticationResponse;
import org.example.ecommerce.entity.User;
import org.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Value("${jwt.signerKey}")
    @NonFinal
    private String signerKey;

    public boolean introspect(String token) throws JOSEException, ParseException {
        //verify token
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        var verified = signedJWT.verify(verifier);
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        return verified && expirationTime.after(new Date());

    }
    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) throws JOSEException {
        var user = userRepository.findByUsername(authRequest.getUsername()).orElseThrow(
                () -> new RuntimeException("User not found")
        );


        boolean authenticated =  passwordEncoder.matches(authRequest.getPassword(), user.getPassword());
        if (!authenticated) throw new RuntimeException("Invalid password");
        // if authenticate success then generate token
        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .authenticated(authenticated)
                .token(token)
                .build();
    }

    private String generateToken(User user) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername()) //username login
                .issuer("phucnvh.com")// who creates the token and signs it
                .issueTime(new Date()) // time to issue
                .expirationTime(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // time to
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        //JWSObject requires a header(algorithm to encrypt)  and a payload
        JWSObject jwsObject = new JWSObject(header, payload);
        try{
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        }
        catch (JOSEException e){
            log.error("Error signing token", e);
            throw e;
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(stringJoiner::add);
        }
        return stringJoiner.toString();
    }
}
