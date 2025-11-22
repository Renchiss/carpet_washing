package ru.rrishbuldin.carpet_washing.modules.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rrishbuldin.carpet_washing.modules.order.entity.Orders;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Optional<Orders> findById(Long orderId);
}
