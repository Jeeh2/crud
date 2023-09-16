package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.*;
import br.ada.customer.crud.usecases.ICreateOrderUseCase;
import br.ada.customer.crud.usecases.repository.CustomerRepository;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CreateOrderUseCaseImpl implements ICreateOrderUseCase {

    // Variável para armazenar o repositório de ordens.
    private OrderRepository repository;

    // Variável para armazenar o repositório de clientes.
    private CustomerRepository customerRepository;

    // Construtor que recebe o repositório de ordens e o repositório de clientes.
    public CreateOrderUseCaseImpl(OrderRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    // Método para criar uma nova ordem com base no cliente fornecido.
    @Override
    public Order create(Customer customer) {
        // Valida o cliente fornecido.
        validCustomer(customer);

        // Cria uma nova instância de ordem.
        Order order = new Order();

        // Define o cliente na ordem.
        order.setCustomer(customer);

        // Inicializa a lista de itens da ordem como vazia.
        order.setItems(new ArrayList<>()); //aqui é criado o carrinho

        // Define o status da ordem como "OPEN" (aberta).
        order.setStatus(OrderStatus.OPEN);

        // Define o endereço de envio padrão da ordem.
        order.setShippingAddress("Minha casa sempre");

        // Define a data e hora da ordem como o momento atual.
        order.setOrderedAt(LocalDateTime.now());

        // Salva a ordem no repositório de ordens.
        repository.save(order);

        // Retorna a ordem criada.
        return order;
    }

    // Método privado para validar o cliente.
    private void validCustomer(Customer customer) {
        // Verifica se o cliente existe no repositório de clientes com base no documento.
        Customer found = customerRepository.findByDocument(customer.getDocument());
        if (found == null) {
            throw new IllegalStateException("Cliente não encontrado");
        }
    }

    @Override
    public OrderItem changeAmount(Order order, Product product, Integer amount) {
        // Verifica se o status do pedido é OPEN.
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new IllegalStateException("Pedido não está no estado OPEN.");
        }

        // Encontra o OrderItem correspondente na lista de itens do pedido.
        OrderItem orderItem = findOrderItem(order, product);

        // Atualiza a quantidade do OrderItem.
        orderItem.setAmount(amount);


        return orderItem;
    }

    @Override
    public void removeItem(Order order, Product product) {
        // Verifica se o status do pedido é OPEN.
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new IllegalStateException("Pedido não está no estado OPEN.");
        }

        // Encontra o OrderItem correspondente na lista de itens do pedido.
        OrderItem orderItem = findOrderItem(order, product);

        // Remove o OrderItem da lista de itens do pedido.
        order.getItems().remove(orderItem);


    }

    private OrderItem findOrderItem(Order order, Product product) {
        // Encontra o OrderItem correspondente na lista de itens do pedido com base no produto.
        return order.getItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Item de pedido não encontrado."));
    }
}

