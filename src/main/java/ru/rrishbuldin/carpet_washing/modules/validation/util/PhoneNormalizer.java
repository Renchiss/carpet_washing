package ru.rrishbuldin.carpet_washing.modules.validation.util;

public class PhoneNormalizer {
    public static String normalize(String phone) {
        if (phone == null) return null;

        // убираем все кроме цифр
        String digits = phone.replaceAll("\\D", "");

        // приводим к формату +7XXXXXXXXXX
        if (digits.startsWith("8")) {
            digits = "7" + digits.substring(1);
        }
        if (!digits.startsWith("7")) {
            digits = "7" + digits;
        }

        if (digits.length() != 11) {
            throw new IllegalArgumentException("Некорректный телефон после нормализации: " + phone);
        }

        return "+7" + digits.substring(1);
    }
}
