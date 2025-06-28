package com.example.e_commerce_rest_api.service.impl;

import com.example.e_commerce_rest_api.entity.*;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.enums.OrderStatus;
import com.example.e_commerce_rest_api.enums.PaymentStatus;
import com.example.e_commerce_rest_api.model.request.InsertOrderRequest;
import com.example.e_commerce_rest_api.model.request.UpdateOrderStatusRequest;
import com.example.e_commerce_rest_api.model.response.OrderDto;
import com.example.e_commerce_rest_api.repository.CartItemRepository;
import com.example.e_commerce_rest_api.repository.OrderRepository;
import com.example.e_commerce_rest_api.repository.PaymentRepository;
import com.example.e_commerce_rest_api.service.IOrderService;
import com.example.e_commerce_rest_api.utils.exception.exceptions.BusinessLogicException;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final ModelMapper modelMapper;
    private final MessageSourceHelper messageSourceHelper;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final PaymentRepository paymentRepository;
    private final AddressService addressService;

    @Override
    public List<OrderDto> findAll() {
        User user = userService.getUser();
        List<Order> orders = orderRepository.findAllByUserIdOrderByCreatedDateDesc(user.getId());
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    @Override
    public List<OrderDto> findAllAdmin() {
        List<Order> orders = orderRepository.findAllByOrderByCreatedDateDesc();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    @Override
    public List<OrderDto> findAllAdminByOrderStatus(OrderStatus orderStatus) {
        List<Order> orders = orderRepository.findAllByStatusOrderByCreatedDateDesc(orderStatus);
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    @Override
    public OrderDto findById(Long id) {
        return modelMapper.map(getOrderById(id), OrderDto.class);
    }

    @Override
    @Transactional
    public OrderDto save(InsertOrderRequest request) {
        User user = userService.getUser();

        // Total amount hazırlanıyor.
        List<CartItem> cartItems = cartItemRepository.findAllByUserIdAndOrderNull(user.getId());
        if (cartItems.isEmpty()) throw new BusinessLogicException(messageSourceHelper.getMessage(MessageKey.CART_EMPTY));
        BigDecimal totalAmount = cartItems.stream()
                .map(cartItem -> cartItem.getProduct().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Payment hazırlanıyor.
        Payment payment = Payment.builder()
                .paymentMethod(request.getPaymentMethod())
                .status(PaymentStatus.WAITING)
                .build();
        Payment savedPayment = paymentRepository.save(payment);

        // Address bilgisi alınıyor
        Address address = addressService.getAddressById(request.getAddressId());

        // Order hazırlanıyor ve kaydediliyor.
        Order order = Order.builder()
                .totalAmount(totalAmount)
                .status(OrderStatus.PENDING)
                .payment(savedPayment)
                .address(address)
                .orderNote(request.getOrderNote())
                .user(user)
                .build();
        Order savedOrder = orderRepository.save(order);

        // CartItem bilgileri Order ile ilişkilendiriliyor.
        cartItems.forEach(cartItem -> cartItem.setOrder(savedOrder));
        cartItemRepository.saveAll(cartItems);

        return modelMapper.map(savedOrder, OrderDto.class);
    }

    @Override
    public OrderDto updateOrderStatus(UpdateOrderStatusRequest request) {
        Order order = getOrderById(request.getId());
        order.setStatus(request.getOrderStatus());
        orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }

    private Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.ORDER_NOT_FOUND)));
    }
}