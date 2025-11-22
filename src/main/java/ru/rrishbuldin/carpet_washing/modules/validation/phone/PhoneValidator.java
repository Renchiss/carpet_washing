package ru.rrishbuldin.carpet_washing.modules.validation.phone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final String PATTERN =
            "^(\\+7|7|8)?[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}[\\s\\-]?\\d{2}[\\s\\-]?\\d{2}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        if (value == null) return false;
        return value.matches(PATTERN);
    }
}