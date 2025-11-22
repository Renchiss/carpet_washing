package ru.rrishbuldin.carpet_washing.modules.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rrishbuldin.carpet_washing.modules.client.dto.ClientInlineDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequestDto {
    @Schema(name = "itemsCount", description = "Количество ковров")
    private Integer itemsCount;

    @Schema(name = "pickupRequired", description = "Нужно ли забирать ковер?")
    private Boolean pickupRequired;

    @Schema(name = "clientInlineDto", description = "Данные клиента")
    @Valid
    private ClientInlineDto clientInlineDto;
}
