package br.ada.customer.crud.integration.memoryrepository;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.Product;

import java.util.Objects;

public class OrderEntityMerge {


    private MemoryDatabase database;

    // Construtor que recebe uma instância de MemoryDatabase.
    public OrderEntityMerge(MemoryDatabase database) {
        this.database = database;
    }

    // Método para mesclar (merge) uma ordem (Order) com um cliente registrado no banco de dados.
    public void merge(Order order) {
        // Encontra o cliente com base no ID do cliente na ordem e atribui o cliente encontrado à ordem.
        Customer customerRegistered = findCustomer(order.getCustomer().getId());
        order.setCustomer(customerRegistered);
    }

    // Método privado para encontrar um cliente pelo ID.
    private Customer findCustomer(Long id) {
        // Encontra um cliente com base no ID fornecido.
        Customer found = database.find(
                Customer.class,
                it -> Objects.equals(id, it.getId())
        ).stream().findFirst().orElse(null);
        return found;
    }

    // Método para mesclar (merge) um item de ordem (OrderItem) com um produto registrado no banco de dados.
    public void merge(OrderItem item) {
        // Encontra o produto com base no ID do produto no item e atribui o produto encontrado ao item.
        Product productRegistered = findProduct(item.getProduct().getId());
        item.setProduct(productRegistered);
    }

    // Método privado para encontrar um produto pelo ID.
    private Product findProduct(Long id) {
        // Encontra um produto com base no ID fornecido.
        Product found = database.find(
                Product.class,
                it -> Objects.equals(id, it.getId())
        ).stream().findFirst().orElse(null);
        return found;
    }
}
