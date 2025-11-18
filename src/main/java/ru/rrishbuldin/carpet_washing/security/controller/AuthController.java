package ru.rrishbuldin.carpet_washing.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rrishbuldin.carpet_washing.dto.auth.LoginRequestDto;
import ru.rrishbuldin.carpet_washing.dto.auth.LoginResponseDto;
import ru.rrishbuldin.carpet_washing.dto.auth.RegisterRequestDto;
import ru.rrishbuldin.carpet_washing.dto.user.UserDto;
import ru.rrishbuldin.carpet_washing.security.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Аутентификация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorization success",
                    content = @Content(schema = @Schema(implementation = LoginResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Authorization error")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        final String token = authenticationService.doLogin(loginRequestDto);

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        UserDto userDto = authenticationService.doRegister(registerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}

