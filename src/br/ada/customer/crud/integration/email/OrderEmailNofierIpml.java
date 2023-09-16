package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.INotifierPaidOrderUseCase;
import br.ada.customer.crud.usecases.INotifierUserCase;
import br.ada.customer.crud.usecases.IPlaceNotifierUseCase;

public class OrderEmailNofierIpml implements INotifierPaidOrderUseCase {

    private SendEmail sendEmail;

    public OrderEmailNofierIpml(SendEmail sendEmail){
        this.sendEmail = sendEmail;
    }
    @Override
    public void notify(Order order) {
        sendEmail.send("comunicado@ecommerce.com.br", order.getCustomer().getEmail(), "Pedido pago");
        System.out.printf("Tudo certo! Pedido pago");
    }
}
