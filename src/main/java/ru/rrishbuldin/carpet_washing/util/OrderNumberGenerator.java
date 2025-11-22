package ru.rrishbuldin.carpet_washing.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rrishbuldin.carpet_washing.repository.OrderNumberSequenceRepository;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class OrderNumberGenerator {

    private final OrderNumberSequenceRepository repository;

    public String generate() {
        long seq = repository.getNextVal();

        String year = String.valueOf(LocalDate.now().getYear());
        String padded = String.format("%06d", seq);

        return "CW-" + year + "-" + padded;
    }
}