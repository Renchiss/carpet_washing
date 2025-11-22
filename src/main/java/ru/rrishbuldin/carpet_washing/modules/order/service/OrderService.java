package ru.rrishbuldin.carpet_washing.modules.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rrishbuldin.carpet_washing.modules.client.entity.Client;
import ru.rrishbuldin.carpet_washing.modules.client.service.ClientService;
import ru.rrishbuldin.carpet_washing.modules.order.dto.OrderCreateRequestDto;
import ru.rrishbuldin.carpet_washing.modules.order.dto.OrderDto;
import ru.rrishbuldin.carpet_washing.modules.order.dto.OrderItemsBatchDto;
import ru.rrishbuldin.carpet_washing.modules.order.dto.OrderItemsCreateDto;
import ru.rrishbuldin.carpet_washing.modules.order.entity.OrderItems;
import ru.rrishbuldin.carpet_washing.modules.order.entity.Orders;
import ru.rrishbuldin.carpet_washing.modules.order.mapper.OrdersMapper;
import ru.rrishbuldin.carpet_washing.modules.order.repository.OrderItemsRepository;
import ru.rrishbuldin.carpet_washing.modules.order.repository.OrdersRepository;
import ru.rrishbuldin.carpet_washing.util.CarpetUtils;
import ru.rrishbuldin.carpet_washing.util.OrderNumberGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;
    private final ClientService clientService;
    private final OrderNumberGenerator orderNumberGenerator;
    private final OrderItemsRepository orderItemsRepository;

    @Transactional
    public OrderDto createOrder(OrderCreateRequestDto createRequestDto) {

        Client client = clientService.createOrGetClient(createRequestDto.getClientInlineDto());

        String orderNumber = orderNumberGenerator.generate();

        Orders order = Orders.builder()
                .itemsCount(createRequestDto.getItemsCount())
                .client(client)
                .address(client.getAddress())
                .phone(client.getPhone())
                .orderNumber(orderNumber)
                .pickupRequired(createRequestDto.getPickupRequired())
                .build();

        return ordersMapper.toDto(ordersRepository.save(order));
    }

    private OrderItems addItemToOrder(Orders order, OrderItemsCreateDto orderItemsCreateDto) {
        //Orders order = ordersRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        Integer lengthCm = orderItemsCreateDto.getLengthCm();
        Integer weightCm = orderItemsCreateDto.getWeightCm();
        BigDecimal areaM2 = CarpetUtils.calculateAreaInSquareMeters(lengthCm, weightCm);

        OrderItems orderItems = OrderItems.builder()
                .order(order)
                .lengthCm(lengthCm)
                .widthCm(weightCm)
                .areaM2(areaM2)
                .build();

        BigDecimal itemPrice = CarpetUtils.calculateCarpetPrice(orderItems.getPricePerM2(), orderItems.getAreaM2());
        orderItems.setItemPrice(itemPrice);

        return orderItemsRepository.save(orderItems);
    }

    @Transactional
    public List<OrderItems> addItemsToOrder(Long orderId, OrderItemsBatchDto orderItemsBatchDto) {
        Orders order = ordersRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
//        List<OrderItems> orderItems = new ArrayList<>();
//        orderItemsBatchDto.getOrderItems().forEach(orderItemsCreateDto -> {
//            orderItems.add(addItemToOrder(order, orderItemsCreateDto));
//        });
//        return orderItems;

        return orderItemsBatchDto.getOrderItems().stream()
                .map(item -> addItemToOrder(order, item))
                .collect(Collectors.toList());
    }

}
