package br.ada.customer.crud.model;

import java.time.LocalDateTime;
import java.util.List;

// representa uma ordem de compra.
public class Order implements Comparable<Order>{

    // Variável para armazenar o ID da ordem.
    private Long id;

    // Variável para armazenar o cliente associado à ordem.
    private Customer customer;

    // Lista de itens de ordem que compõem a ordem.
    private List<OrderItem> items;

    // Data e hora em que a ordem foi feita.
    private LocalDateTime orderedAt;

    // Status da ordem (por exemplo, "pendente", "enviada", etc.).
    private OrderStatus status;

    // Endereço de envio da ordem.
    private String shippingAddress;

    // Método para obter o ID da ordem.
    public Long getId() {
        return id;
    }

    // Método para definir o ID da ordem.
    public void setId(Long id) {
        this.id = id;
    }

    // Método para obter o cliente associado à ordem.
    public Customer getCustomer() {
        return customer;
    }

    // Método para definir o cliente associado à ordem.
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Método para obter a lista de itens de ordem da ordem.
    public List<OrderItem> getItems() {
        return items;
    }

    // Método para definir a lista de itens de ordem da ordem.
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    // Método para obter a data e hora em que a ordem foi feita.
    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    // Método para definir a data e hora em que a ordem foi feita.
    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    // Método para obter o status da ordem.
    public OrderStatus getStatus() {
        return status;
    }

    // Método para definir o status da ordem.
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    // Método para obter o endereço de envio da ordem.
    public String getShippingAddress() {
        return shippingAddress;
    }

    // Método para definir o endereço de envio da ordem.
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public int compareTo(Order o) {
        return this.id.compareTo(o.id);
    }
}