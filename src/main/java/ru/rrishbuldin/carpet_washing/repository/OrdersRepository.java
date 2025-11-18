package ru.rrishbuldin.carpet_washing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rrishbuldin.carpet_washing.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
