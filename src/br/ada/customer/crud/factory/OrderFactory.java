package br.ada.customer.crud.factory;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.integration.email.OrderEmailNofierIpml;
import br.ada.customer.crud.integration.email.OrderPlaceNotifierImpl;
import br.ada.customer.crud.integration.email.SendEmail;
import br.ada.customer.crud.integration.memoryrepository.OrderEntityMerge;
import br.ada.customer.crud.integration.memoryrepository.OrderMemoryRepositoryImpl;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.*;
import br.ada.customer.crud.usecases.impl.*;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderFactory {

    // Método para criar uma instância de ICreateOrderUseCase.
    public static ICreateOrderUseCase createUseCase() {
        // Cria uma instância de CreateOrderUseCaseImpl, passando duas instâncias de repositório:
        // uma criada localmente e outra da CustomerFactory.
        return new CreateOrderUseCaseImpl(
                createRepository(),
                CustomerFactory.createRepository()
        );
    }

    // Método para criar uma instância de IOrderItemUseCase.
    public static IOrderItemUseCase orderItemUseCase() {
        return new OrderItemUseCaseImpl(
                createRepository(),
                ProductFactory.createRepository()
        );
    }

    // Método para criar uma instância de IOrderPlaceUseCase.
    public static IOrderPlaceUseCase placeOrderUseCase() {
        IPlaceNotifierUseCase iPlaceNotifierUseCase = new IPlaceNotifierUseCase() {
            @Override
            public void shipping(Object object) {

            }

            @Override
            public void updatedPayOrder(Object object) {

            }

            @Override
            public void pendingPayment(Object object) {

            }

            @Override
            public void notifyPayPending(Object object) {

            }

            @Override
            public void shipping(Order order) {

            }

            @Override
            public void updatedPayOrder(Order order) {

            }

            @Override
            public void pendingPayment(Order order) {

            }

            @Override
            public void notifyPayPending(Order order) {

            }
        };
        IPlaceNotifierUseCase notifierUseCaseEmail = new IPlaceNotifierUseCase() {
            @Override
            public void shipping(Object object) {

            }

            @Override
            public void updatedPayOrder(Object object) {

            }

            @Override
            public void pendingPayment(Object object) {

            }

            @Override
            public void notifyPayPending(Object object) {

            }

            @Override
            public void shipping(Order order) {

            }

            @Override
            public void updatedPayOrder(Order order) {

            }

            @Override
            public void pendingPayment(Order order) {

            }

            @Override
            public void notifyPayPending(Order order) {

            }
        };
        IPlaceNotifierUseCase notifierUseCaseSms = new IPlaceNotifierUseCase() {
            @Override
            public void shipping(Object object) {

            }

            @Override
            public void updatedPayOrder(Object object) {

            }

            @Override
            public void pendingPayment(Object object) {

            }

            @Override
            public void notifyPayPending(Object object) {

            }

            @Override
            public void shipping(Order order) {

            }

            @Override
            public void updatedPayOrder(Order order) {

            }

            @Override
            public void pendingPayment(Order order) {

            }

            @Override
            public void notifyPayPending(Order order) {

            }
        };
        return new OrderPlaceUseCaseImpl(
                createRepository(),
                iPlaceNotifierUseCase,
                notifierUseCaseEmail,
                notifierUseCaseSms


        );
    }



    // Método para criar uma instância de IOrderPayUseCase.
    public static IOrderPayUseCase createPayOrderUseCase() {
        return new OrderPayUseCaseImpl(
                createRepository(),
                new OrderEmailNofierIpml(new SendEmail())
        );
    }

    // Método para criar uma instância de IOrderShippingUseCase.
    public static IOrderShippingUseCase shippingUseCase() {
        return new OrderShippingUseCaseImpl(
                createRepository(),
                null
        );
    }

    // Método para criar uma instância de OrderRepository.
    public static OrderRepository createRepository() {
        // Cria uma instância de OrderMemoryRepositoryImpl, passando uma instância de MemoryDatabase e OrderEntityMerge.
        return new OrderMemoryRepositoryImpl(
                MemoryDatabase.getInstance(),
                new OrderEntityMerge(MemoryDatabase.getInstance())
        );
    }
}
