package ru.rrishbuldin.carpet_washing.modules.validation.person_name;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {PersonNameValidator.class})
public @interface PersonName {
}
