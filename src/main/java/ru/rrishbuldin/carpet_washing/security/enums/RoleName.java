package ru.rrishbuldin.carpet_washing.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/// Enum ролей с набором прав
@AllArgsConstructor
@Getter
public enum RoleName implements GrantedAuthority {
    ROLE_ADMIN(Set.of(
            Permission.VIEW_ALL_ORDERS,
            Permission.MANAGE_USERS,
            Permission.ASSIGN_TASKS,
            Permission.CREATE_ORDER,
            Permission.EDIT_ORDER
    )),
    ROLE_USER(Set.of(
            Permission.CREATE_ORDER
    )),
    ROLE_COURIER(Set.of(
            Permission.VIEW_ACTUAL_ORDERS,
            Permission.EDIT_ORDER
    )),
    ROLE_WASHER(Set.of(
            Permission.EDIT_ORDER
    ));

    private final Set<Permission> permissions;

    @Override
    public String getAuthority() {
        return name();
    }
}
