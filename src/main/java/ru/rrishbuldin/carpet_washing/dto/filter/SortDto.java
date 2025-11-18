package ru.rrishbuldin.carpet_washing.dto.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.rrishbuldin.carpet_washing.enums.SortOrder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SortDto {

    @Schema(name = "sortOrder", description = "Направление сортировки")
    private SortOrder sortOrder;

    @Schema(name = "column", description = "Колонка, по которой сортируем")
    private String column;
}
