package ru.rrishbuldin.carpet_washing.modules.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.rrishbuldin.carpet_washing.entity.Auditable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@NamedEntityGraph(
        name = "User.withRolesAndRoleDetails",
        attributeNodes = @NamedAttributeNode(
                value = "role"
        )
)
@Entity @Table(name = "\"user\"")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class User extends Auditable implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login", nullable = false)
    @NotNull
    private String login;

    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @Column(name = "total_orders")
    private Long totalOrders;

    @Column(name = "total_spent")
    private BigDecimal totalSpent;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "middle_name", length = 100)
    private String middleName;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @JoinColumn(name = "role")
    @Enumerated(EnumType.STRING)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Role role;

    @Override
    @NonNull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> set = new HashSet<>();
        set.add(new SimpleGrantedAuthority(role.getName().getAuthority()));
        role.getName().getPermissions().forEach(permission -> set.add(new SimpleGrantedAuthority(permission.name())));
        return set;
    }

    @Override
    @NonNull
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
