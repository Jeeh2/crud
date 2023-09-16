package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.IOrderShippingUseCase;
import br.ada.customer.crud.usecases.IShippingNotifierUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderShippingUseCaseImpl implements IOrderShippingUseCase {

    // Variável para armazenar o repositório de ordens.
    private OrderRepository orderRepository;

    // Variável para armazenar o caso de uso de notificação de envio.
    private IShippingNotifierUseCase notifierUseCase;

    // Construtor que recebe o repositório de ordens e o caso de uso de notificação de envio.
    public OrderShippingUseCaseImpl(
            OrderRepository orderRepository,
            IShippingNotifierUseCase notifierUseCase
    ) {
        this.orderRepository = orderRepository;
        this.notifierUseCase = notifierUseCase;
    }

    // Método para realizar o envio de uma ordem.
    @Override
    public void shipping(Order order) {
        if (order.getStatus() != OrderStatus.PAID) {
            throw new RuntimeException("Pedido ainda não pago.");
        }

        order.setStatus(OrderStatus.FINISH);
        orderRepository.save(order);

        if (notifierUseCase != null) {
            notifierUseCase.notify(order);
        } else {
            // Lida com o caso em que notifierUseCase é nulo (se necessário).
        }
    }
}
