package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.integration.email.OrderPlaceNotifierImpl;
import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.IOrderPlaceUseCase;
import br.ada.customer.crud.usecases.IPlaceNotifierUseCase;
import br.ada.customer.crud.usecases.IShippingNotifierUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;
public class OrderPlaceUseCaseImpl implements IOrderPlaceUseCase {

    private OrderRepository orderRepository;
    private IShippingNotifierUseCase notifierUseCase;

    private IPlaceNotifierUseCase iPlaceNotifierUseCase;
    private IPlaceNotifierUseCase notifierUseCaseEmail;
    private IPlaceNotifierUseCase notifierUseCaseSms;

    private OrderItem orderItem;

    public OrderPlaceUseCaseImpl(OrderRepository orderRepository,
                                 IPlaceNotifierUseCase iPlaceNotifierUseCase,
                                 IPlaceNotifierUseCase notifierUseCaseEmail,
                                 IPlaceNotifierUseCase notifierUseCaseSms) {
        this.orderRepository = orderRepository;
        this.iPlaceNotifierUseCase = iPlaceNotifierUseCase;
        this.notifierUseCaseEmail = notifierUseCaseEmail;
        this.notifierUseCaseSms = notifierUseCaseSms;
    }

    @Override
    public void placeOrder(Order order) {
        BigDecimal sum = BigDecimal.ZERO;
        // Verificando se o pedido está no status correto.
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new IllegalStateException("Pedido não está no status OPEN.");
        }

        // Verificando se o pedido tem pelo menos um item.
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalStateException("O pedido deve ter pelo menos um item.");
        }


        //valor maior que zero
        for(OrderItem item: order.getItems()){
            sum = sum.add(item.getSaleValue());

        }

        if(sum.compareTo(BigDecimal.ZERO) <= 0){
           throw new RuntimeException("O pedido não pode ser zero");
        }


        order.setStatus(OrderStatus.PENDING_PAYMENT);
        notifierUseCaseEmail.pendingPayment(order);
        notifierUseCaseSms.pendingPayment(order);
    }



    // Valor total do pedido.
    private BigDecimal calculateOrderTotal(Order order) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (OrderItem item : order.getItems()) {
            BigDecimal itemTotal = item.getSaleValue().multiply(BigDecimal.valueOf(item.getAmount()));
            valorTotal = valorTotal.add(itemTotal);
        }
        return valorTotal;
    }
}

