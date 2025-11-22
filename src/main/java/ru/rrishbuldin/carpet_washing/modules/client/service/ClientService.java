package ru.rrishbuldin.carpet_washing.modules.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rrishbuldin.carpet_washing.modules.client.dto.ClientCreateRequestDto;
import ru.rrishbuldin.carpet_washing.modules.client.dto.ClientInlineDto;
import ru.rrishbuldin.carpet_washing.modules.client.entity.Client;
import ru.rrishbuldin.carpet_washing.modules.client.repository.ClientRepository;
import ru.rrishbuldin.carpet_washing.modules.validation.util.PhoneNormalizer;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional
    public Client createOrGetClient(ClientInlineDto dto) {

        // Чистим данные
        dto.setPhone(PhoneNormalizer.normalize(dto.getPhone().trim()));
        dto.setName(dto.getName().trim());
        dto.setAddress(dto.getAddress().trim());

        Client existing = clientRepository.findByPhone(dto.getPhone());

        if (existing != null) {
//            // Опционально — обновление, если данные изменились
//            boolean changed = false;
//
//            if (dto.getName() != null && !dto.getName().equals(existing.getName())) {
//                existing.setName(dto.getName());
//                changed = true;
//            }
//            if (dto.getAddress() != null && !dto.getAddress().equals(existing.getAddress())) {
//                existing.setAddress(dto.getAddress());
//                changed = true;
//            }

            //return changed ? clientRepository.save(existing) : existing;

            return existing;
        }

        Client client = Client.builder()
                .phone(dto.getPhone())
                .name(dto.getName())
                .address(dto.getAddress())
                .build();

        return clientRepository.save(client);
    }

    @Transactional
    public Client createClient(ClientCreateRequestDto requestDto) {

        requestDto.setPhone(PhoneNormalizer.normalize(requestDto.getPhone().trim()));

        Client client = Client.builder()
                .name(requestDto.getName())
                .phone(requestDto.getPhone())
                .address(requestDto.getAddress())
                .otherContacts(requestDto.getOtherContacts())
                .build();
        return clientRepository.save(client);
    }
}
