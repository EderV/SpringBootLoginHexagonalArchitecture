package com.eder.rider.SpringBootLoginHexagonalArchitecture.application;

import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.Credentials;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.in.AuthServicePort;
import com.eder.rider.SpringBootLoginHexagonalArchitecture.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceDefault implements AuthServicePort {

    private final AuthenticationProvider authenticationProvider;

    @Override
    public Authentication login(Credentials credentials) throws AuthenticationException {
        var username = credentials.getUsername();
        var password = credentials.getPassword();

        var userDetails = User.builder().username(username).password(password).build();
        var authentication = new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        return authenticationProvider.authenticate(authentication);
    }

}
