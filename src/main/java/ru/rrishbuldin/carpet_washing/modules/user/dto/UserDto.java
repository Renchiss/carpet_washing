package ru.rrishbuldin.carpet_washing.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    @Schema(name = "id", description = "Идентификатор")
    private Long id;

    @Schema(name = "login", description = "Логин")
    private String login;

    @Schema(name = "firstName", description = "Имя")
    private String firstName;

    @Schema(name = "lastName", description = "Фамилия")
    private String lastName;

    @Schema(name = "middleName", description = "Отчество")
    private String middleName;

    @Schema(name = "email", description = "Email")
    private String email;

    @Schema(name = "dateOfBirth", description = "Дата рождения")
    private LocalDate dateOfBirth;
}
