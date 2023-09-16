package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.integration.email.OrderEmailNofierIpml;
import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.INotifierPaidOrderUseCase;
import br.ada.customer.crud.usecases.INotifierUserCase;
import br.ada.customer.crud.usecases.IOrderPayUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderPayUseCaseImpl implements IOrderPayUseCase {

    private OrderRepository orderRepository;
    private INotifierPaidOrderUseCase iNotifierPaidOrderUseCase;

    public OrderPayUseCaseImpl(OrderRepository orderRepository, INotifierPaidOrderUseCase iNotifierPaidOrderUseCase) {
        this.orderRepository = orderRepository;
        this.iNotifierPaidOrderUseCase = iNotifierPaidOrderUseCase;
    }

    @Override
    public void pay(Order order) {


        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            throw new IllegalStateException("O pedido ainda está em aberto.");
        }

        // Atualizando o status do pedido para PAID.
        order.setStatus(OrderStatus.PAID);
        synchronized (iNotifierPaidOrderUseCase) {
            iNotifierPaidOrderUseCase.notify();
        }

        synchronized (iNotifierPaidOrderUseCase) {
            iNotifierPaidOrderUseCase.notify();
        }
        // Atualizando o pedido no repositório.
        orderRepository.update(order);
    }
}
