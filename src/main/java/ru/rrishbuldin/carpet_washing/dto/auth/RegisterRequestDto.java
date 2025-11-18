package ru.rrishbuldin.carpet_washing.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRequestDto implements Serializable {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{2,18}$")
    private String login;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?])[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{8,32}$")
    private String password;

    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ]{2,30}$", message = "invalid firstName format")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ\\-]{2,30}$", message = "invalid lastName format")
    private String lastName;
}