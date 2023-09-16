package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;

public interface IPlaceNotifierUseCase <T> {


    void shipping(T object);
    void updatedPayOrder(T object);
    void pendingPayment(T object);

    void notifyPayPending(T object);

    void shipping(Order order);

    void updatedPayOrder(Order order);

    void pendingPayment(Order order);

    void notifyPayPending(Order order);
}

