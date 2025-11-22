package ru.rrishbuldin.carpet_washing.modules.validation.address;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<Address, String> {

    private static final String PATTERN =
            "^[A-Za-zА-Яа-яЁё0-9\\s.,\\-\\/\"№#]{5,200}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        if (value == null) return false;
        return value.trim().matches(PATTERN);
    }
}
