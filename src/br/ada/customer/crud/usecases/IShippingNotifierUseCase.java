package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;

//Notifica um pedido que foi entregue
public interface IShippingNotifierUseCase {

    void notify(Order order);

}
