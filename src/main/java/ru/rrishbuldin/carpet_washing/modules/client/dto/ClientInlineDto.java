package ru.rrishbuldin.carpet_washing.modules.client.dto;

import lombok.*;
import ru.rrishbuldin.carpet_washing.modules.validation.address.Address;
import ru.rrishbuldin.carpet_washing.modules.validation.person_name.PersonName;
import ru.rrishbuldin.carpet_washing.modules.validation.phone.Phone;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientInlineDto {
    @PersonName
    private String name;

    @Phone
    private String phone;

    @Address
    private String address;
}
