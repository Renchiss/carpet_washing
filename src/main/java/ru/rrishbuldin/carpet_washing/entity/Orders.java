package ru.rrishbuldin.carpet_washing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.rrishbuldin.carpet_washing.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity @Table(name = "orders")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Orders extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 150)
    @Column(name = "user_name", length = 150)
    private String userName;

    @Size(max = 20)
    @NotNull
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "address", length = Integer.MAX_VALUE)
    private String address;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;

    @Size(max = 20)
    @NotNull
    @Column(name = "order_number", nullable = false, length = 20)
    private String orderNumber;

    @ColumnDefault("now()")
    @Column(name = "received_at")
    @Builder.Default
    private Instant receivedAt = Instant.now();

    @Column(name = "promised_at")
    private Instant promisedAt;

    @Column(name = "completed_at")
    private Instant completedAt;

    @Size(max = 20)
    @ColumnDefault("'new'")
    @Column(name = "status", length = 20)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    // TODO сделать энум со статусами
    private OrderStatus status = OrderStatus.NEW;

    @ColumnDefault("1")
    @Column(name = "items_count")
    @Builder.Default
    private Integer itemsCount = 1;

    @Column(name = "total_area_m2", precision = 8, scale = 2)
    private BigDecimal totalAreaM2;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @ColumnDefault("0")
    @Column(name = "discount_percent", precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal discountPercent = BigDecimal.ZERO;

    @ColumnDefault("0")
    @Column(name = "discount_amount", precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @NotNull
    @Column(name = "final_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal finalPrice;

    @Size(max = 20)
    @Column(name = "payment_method", length = 20)
    private String paymentMethod;

    @Size(max = 20)
    @ColumnDefault("'pending'")
    @Column(name = "payment_status", length = 20)
    @Builder.Default
    // TODO сделать энум со статусами
    private String paymentStatus = "pending";

    @ColumnDefault("false")
    @Column(name = "pickup_required")
    @Builder.Default
    private Boolean pickupRequired = false;

    @Column(name = "pickup_scheduled_at")
    private Instant pickupScheduledAt;

    @ColumnDefault("false")
    @Column(name = "delivery_required")
    @Builder.Default
    private Boolean deliveryRequired = false;

    @Column(name = "delivery_scheduled_at")
    private Instant deliveryScheduledAt;

    @Size(max = 30)
    @Column(name = "source", length = 30)
    private String source;

    @Column(name = "tags")
    private List<String> tags;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToMany(mappedBy = "order")
    private Set<OrderItems> orderItems = new LinkedHashSet<>();
}
