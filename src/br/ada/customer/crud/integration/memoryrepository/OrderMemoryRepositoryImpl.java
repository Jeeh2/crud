package br.ada.customer.crud.integration.memoryrepository;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.usecases.repository.OrderRepository;
import br.ada.customer.crud.usecases.repository.RepositoryException;

import java.util.List;
import java.util.Objects;

public class OrderMemoryRepositoryImpl implements OrderRepository {


    private final MemoryDatabase database;

    // Uma referência à instância de OrderEntityMerge utilizada para mesclar entidades de
    // ordens e itens de ordem.
    private final OrderEntityMerge entityMerge;

    // Construtor que recebe uma instância de MemoryDatabase e uma instância de OrderEntityMerge.
    public OrderMemoryRepositoryImpl(MemoryDatabase database, OrderEntityMerge entityMerge) {
        this.database = database;
        this.entityMerge = entityMerge;
    }

    // Método para salvar uma ordem (Order) no repositório.
    @Override
    public void save(Order order) throws RepositoryException {
        // Atribui um novo ID à ordem e, em seguida, mescla (merge) a ordem e seus itens com clientes e produtos registrados no banco de dados.
        order.setId(database.nextId());
        entityMerge.merge(order);
        for (OrderItem item : order.getItems()) {
            entityMerge.merge(item);
        }
        // Salva ou atualiza a ordem no banco de dados.
        database.saveOrUpdate(order);
    }

    // Método para listar todas as ordens no repositório.
    @Override
    public List<Order> listAll() throws RepositoryException {
        // Retorna uma lista de todas as ordens armazenadas no banco de dados.
        return database.listAll(Order.class);
    }

    // Método para encontrar uma ordem pelo ID.
    @Override
    public Order findById(Long id) throws RepositoryException {
        // Encontra uma ordem com base no ID fornecido.
        return database.find(
                Order.class,
                it -> Objects.equals(id, it.getId())
        ).stream().findFirst().orElse(null);
    }

    // Método para atualizar uma ordem no repositório.
    @Override
    public void update(Order order) throws RepositoryException {
        // Mescla (merge) a ordem e seus itens com clientes e produtos registrados no banco de dados.
        entityMerge.merge(order);
        for (OrderItem item : order.getItems()) {
            entityMerge.merge(item);
        }
        // Encontra a ordem pelo ID e atualiza suas informações no banco de dados.
        Order inserted = findById(order.getId());
        inserted.setCustomer(order.getCustomer());
        inserted.setStatus(order.getStatus());
        inserted.setShippingAddress(order.getShippingAddress());
        inserted.setItems(order.getItems());
        database.saveOrUpdate(inserted);
    }

    // Método para excluir uma ordem do repositório.
    @Override
    public Order delete(Order object) throws RepositoryException {
        // Remove a ordem do banco de dados e a retorna.
        return database.delete(object);
    }

    // Método para encontrar todas as ordens de um cliente específico.
    @Override
    public List<Order> findByCustomer(Customer customer) {
        // Retorna uma lista de ordens onde o ID do cliente corresponde ao ID do cliente fornecido.
        return database.find(
                Order.class,
                it -> Objects.equals(customer.getId(), it.getCustomer().getId())
        );
    }
}
