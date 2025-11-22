package ru.rrishbuldin.carpet_washing.modules.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rrishbuldin.carpet_washing.modules.user.entity.Role;
import ru.rrishbuldin.carpet_washing.security.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name =:name")
    Role findByName(RoleName name);
}
