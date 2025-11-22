package ru.rrishbuldin.carpet_washing.modules.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemsBatchDto {
    @NotEmpty(message = "Список айтемов не может быть пустым")
    @Valid
    @Schema(name = "orderItems", description = "Список ковров для множественной вставки")
    List<OrderItemsCreateDto> orderItems;
}
