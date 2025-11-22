package ru.rrishbuldin.carpet_washing.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class OrderNumberSequenceRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public long getNextVal() {
        return ((Number) entityManager
                .createNativeQuery("SELECT nextval('order_number_seq')")
                .getSingleResult())
                .longValue();
    }
}
