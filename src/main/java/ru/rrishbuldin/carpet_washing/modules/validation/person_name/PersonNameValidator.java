package ru.rrishbuldin.carpet_washing.modules.validation.person_name;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PersonNameValidator implements ConstraintValidator<PersonName, String> {

    private static final String PATTERN =
            "^[A-Za-zА-Яа-яЁё\\s\\-]{2,50}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        if (value == null) return false;
        return value.trim().matches(PATTERN);
    }
}
