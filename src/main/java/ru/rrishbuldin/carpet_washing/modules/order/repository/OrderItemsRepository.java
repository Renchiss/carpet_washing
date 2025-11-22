package ru.rrishbuldin.carpet_washing.modules.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rrishbuldin.carpet_washing.modules.order.entity.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
