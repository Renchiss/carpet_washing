package ru.rrishbuldin.carpet_washing.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rrishbuldin.carpet_washing.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @EntityGraph(value = "User.withRolesAndRoleDetails", type = EntityGraph.EntityGraphType.FETCH)
    Optional<User> findWithRolesByLogin(String login);
    boolean existsByLogin(String username);
    @EntityGraph(value = "User.withRolesAndRoleDetails", type = EntityGraph.EntityGraphType.FETCH)
    Optional<User> findById(Long id);
}
