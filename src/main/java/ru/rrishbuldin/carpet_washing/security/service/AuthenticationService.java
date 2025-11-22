package ru.rrishbuldin.carpet_washing.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rrishbuldin.carpet_washing.security.dto.auth.LoginRequestDto;
import ru.rrishbuldin.carpet_washing.security.dto.auth.RegisterRequestDto;
import ru.rrishbuldin.carpet_washing.modules.user.dto.UserDto;
import ru.rrishbuldin.carpet_washing.exception.RegistrationException;
import ru.rrishbuldin.carpet_washing.exception.UserInteractionException;
import ru.rrishbuldin.carpet_washing.security.jwt.JwtUtil;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService customUserDetailService;
    private final JwtUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 CustomUserDetailService customUserDetailService,
                                 JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailService = customUserDetailService;
        this.jwtUtil = jwtUtil;
    }

    public String doLogin(LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getLogin(),
                            loginRequestDto.getPassword()
                    )
            );

            final UserDetails userDetails = customUserDetailService
                    .loadUserByUsername(loginRequestDto.getLogin());

            return jwtUtil.generateToken(userDetails);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("User with login or password not found");
        }
    }

    @Transactional
    public UserDto doRegister(RegisterRequestDto registerRequestDto) {
        try {
            UserDto user = customUserDetailService.registerUser(registerRequestDto);

            return Optional.ofNullable(user)
                    .orElseThrow(() -> new UserInteractionException("User not found"));

        } catch (Exception e) {
            throw new RegistrationException("Registration failed");
        }

    }
}