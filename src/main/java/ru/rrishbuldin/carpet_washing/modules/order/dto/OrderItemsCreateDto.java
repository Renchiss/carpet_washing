package ru.rrishbuldin.carpet_washing.modules.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemsCreateDto {
    @NotNull
    private Integer lengthCm;
    @NotNull
    private Integer weightCm;
}
