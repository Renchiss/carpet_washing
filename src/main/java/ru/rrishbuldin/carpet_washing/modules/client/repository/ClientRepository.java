package ru.rrishbuldin.carpet_washing.modules.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rrishbuldin.carpet_washing.modules.client.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByPhone(String phone);
}
