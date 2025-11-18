package ru.rrishbuldin.carpet_washing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity @Table(name = "order_items")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id")
    private Orders order;

    @Size(max = 50)
    @Column(name = "carpet_type", length = 50)
    private String carpetType;

    @Column(name = "length_cm")
    private Integer lengthCm;

    @Column(name = "width_cm")
    private Integer widthCm;

    @Column(name = "area_m2", precision = 6, scale = 2)
    private BigDecimal areaM2;

    @Size(max = 30)
    @Column(name = "condition_in", length = 30)
    private String conditionIn;

    @Size(max = 30)
    @Column(name = "condition_out", length = 30)
    private String conditionOut;

    @ColumnDefault("250")
    @Column(name = "price_per_m2", precision = 8, scale = 2)
    @Builder.Default
    private BigDecimal pricePerM2 = BigDecimal.valueOf(250);

    @Column(name = "item_price", precision = 10, scale = 2)
    private BigDecimal itemPrice;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;
}
