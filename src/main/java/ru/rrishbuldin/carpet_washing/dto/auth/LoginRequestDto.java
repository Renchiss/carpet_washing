package ru.rrishbuldin.carpet_washing.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDto implements Serializable {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}