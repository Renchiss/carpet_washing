package ru.rrishbuldin.carpet_washing.modules.order.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.rrishbuldin.carpet_washing.modules.order.dto.OrderDto;
import ru.rrishbuldin.carpet_washing.modules.order.entity.Orders;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    @Named("toDto")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "itemsCount", source = "itemsCount")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "pickupRequired", source = "pickupRequired")
    OrderDto toDto(Orders orders);

    @Named("toEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "itemsCount", source = "itemsCount")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "pickupRequired", source = "pickupRequired")
    Orders toEntity(OrderDto orderDto);
}
