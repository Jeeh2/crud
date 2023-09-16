package br.ada.customer.crud;

import br.ada.customer.crud.factory.CustomerFactory;
import br.ada.customer.crud.factory.OrderFactory;
import br.ada.customer.crud.factory.ProductFactory;
import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.Product;
import br.ada.customer.crud.usecases.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

public class ExampleOrderMain {

    public static void main(String[] args) {
        IProductUseCase productUseCase = ProductFactory.createUseCase();
        ICustomerUseCase customerUseCase = CustomerFactory.createUseCase();
        ICreateOrderUseCase orderUseCase = OrderFactory.createUseCase();
        IOrderItemUseCase orderItemUseCase = OrderFactory.orderItemUseCase();
        IOrderPlaceUseCase orderPlaceUseCase = OrderFactory.placeOrderUseCase();
        IOrderPayUseCase orderPayUseCase = OrderFactory.createPayOrderUseCase();
        IOrderShippingUseCase orderShipping = OrderFactory.shippingUseCase();

        Customer customer = new Customer();
        customer.setName("Jéssica");
        customer.setEmail(Collections.singletonList("jessicalima@gmail.com"));
        customer.setTelephone(Collections.singletonList("91967162"));
        customer.setBirthDate(LocalDate.ofEpochDay(5 / 3 /1995));
        customerUseCase.create(customer);


        Product productOne = new Product();
        productOne.setDescription("023");
        productUseCase.create(productOne);

        Product productTwo = new Product();
        productTwo.setDescription("1546");
        productUseCase.create(productTwo);

        Product productThree = new Product();
        productThree.setDescription("516");
        productUseCase.create(productThree);

        Order order = orderUseCase.create(customer);
        orderItemUseCase.addItem(order, productOne, BigDecimal.TEN, 1);
        orderItemUseCase.addItem(order, productTwo, BigDecimal.TEN, 2);
        orderItemUseCase.changeAmount(order, productTwo, 5);
        orderItemUseCase.removeItem(order, productOne);
        orderPlaceUseCase.placeOrder(order);
        orderPayUseCase.pay(order);
        orderShipping.shipping(order);
    }

}
