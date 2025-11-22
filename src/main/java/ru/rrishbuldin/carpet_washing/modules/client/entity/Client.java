package ru.rrishbuldin.carpet_washing.modules.client.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.rrishbuldin.carpet_washing.entity.Auditable;
import ru.rrishbuldin.carpet_washing.modules.order.entity.Orders;
import ru.rrishbuldin.carpet_washing.modules.user.entity.User;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 20)
    @NotNull
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "address", length = Integer.MAX_VALUE)
    private String address;

    @Column(name = "other_contacts", length = Integer.MAX_VALUE)
    private String otherContacts;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Orders> orders = new LinkedHashSet<>();
}
