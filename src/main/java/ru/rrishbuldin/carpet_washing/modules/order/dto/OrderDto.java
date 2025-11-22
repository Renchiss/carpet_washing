package ru.rrishbuldin.carpet_washing.modules.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class OrderDto {
    @Schema(name = "id", description = "Идентификатор")
    private Long id;

    @Schema(name = "items_count", description = "Количество ковров")
    private Integer itemsCount;

    @Schema(name = "clientName", description = "Имя клиента")
    private String clientName;

    @Schema(name = "phone", description = "Номер телефона")
    private String phone;

    @Schema(name = "address", description = "Адрес")
    private String address;

    @Schema(name = "orderNumber", description = "Номер заказа")
    private String orderNumber;

    @Schema(name = "pickupRequired", description = "Нужно ли забирать ковер?")
    private Boolean pickupRequired;
}
