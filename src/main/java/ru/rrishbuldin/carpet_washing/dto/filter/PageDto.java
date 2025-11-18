package ru.rrishbuldin.carpet_washing.dto.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDto {
    @Schema(name = "max", description = "Максимальное количество элементов на странице")
    @Min(1)
    @Builder.Default
    private Integer max = 10;

    @Schema(name = "offset", description = "Смещение для пагинации")
    @Min(0)
    @Builder.Default
    private Integer offset = 0;
}

