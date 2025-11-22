package ru.rrishbuldin.carpet_washing.modules.user.mapper;

import org.mapstruct.*;
import ru.rrishbuldin.carpet_washing.modules.user.dto.UserDto;
import ru.rrishbuldin.carpet_washing.modules.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Named("toDto")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "middleName", source = "middleName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    UserDto toDto(User user);

    @Named("toEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "middleName", source = "middleName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    User toEntity(UserDto userDto);

}
