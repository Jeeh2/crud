package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.IPlaceNotifierUseCase;

public class OrderPlaceNotifierImpl implements IPlaceNotifierUseCase {

    private SendEmail sendEmail;

    public OrderPlaceNotifierImpl(SendEmail sendEmail){
        this.sendEmail = sendEmail;
    }

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


}
