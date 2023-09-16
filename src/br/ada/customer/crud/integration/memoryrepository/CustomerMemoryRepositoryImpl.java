package br.ada.customer.crud.integration.memoryrepository;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.usecases.repository.CustomerRepository;
import br.ada.customer.crud.usecases.repository.RepositoryException;

import java.awt.im.InputSubset;
import java.util.List;
import java.util.Objects;

public class CustomerMemoryRepositoryImpl implements CustomerRepository {


    private final MemoryDatabase database;

    // Construtor que recebe uma instância de MemoryDatabase.
    public CustomerMemoryRepositoryImpl(MemoryDatabase database) {
        this.database = database;
    }

    // Método para salvar um cliente no repositório.
    @Override
    public void save(Customer customer) throws RepositoryException {
        // Atribui um novo ID ao cliente e, em seguida, salva ou atualiza o cliente no banco de dados.
        customer.setId(database.nextId());
        database.saveOrUpdate(customer);
    }

    // Método para listar todos os clientes no repositório.
    @Override
    public List<Customer> listAll() throws RepositoryException {
        // Retorna uma lista de todos os clientes armazenados no banco de dados.
        return database.listAll(Customer.class);
    }

    // Método para encontrar um cliente por ID.
    @Override
    public Customer findById(Long id) throws RepositoryException {
        // Encontra um cliente com base no ID fornecido.
        Customer found = database.find(
                Customer.class,
                it -> Objects.equals(id, it.getId())
        ).stream().findFirst().orElse(null);
        return found;
    }

    // Método para atualizar as informações de um cliente.
    @Override
    public void update(Customer customer) throws RepositoryException {
        // Encontra o cliente pelo ID e atualiza suas informações no banco de dados.
        Customer inserted = findById(customer.getId());
        inserted.setName(customer.getName());
        inserted.setDocument(customer.getDocument());
        inserted.setEmail(customer.getEmail());
        inserted.setTelephone(customer.getTelephone());
        inserted.setDocument(customer.getDocument());
        database.saveOrUpdate(inserted);
    }

    // Método para excluir um cliente do repositório.
    @Override
    public Customer delete(Customer customer) throws RepositoryException {
        // Remove o cliente do banco de dados e retorna o cliente removido.
        return database.delete(customer);
    }

    // Método para encontrar um cliente pelo número de documento.
    @Override
    public Customer findByDocument(String document) {
        // Encontra um cliente com base no número de documento fornecido.
        Customer found = database.find(
                Customer.class,
                it -> Objects.equals(document, it.getDocument())
        ).stream().findFirst().orElse(null);
        return found;
    }

    // Método para encontrar clientes pelo nome.
    @Override
    public List<Customer> findByName(String name) {
        // Retorna uma lista de clientes com o nome fornecido.
        return database.find(
                Customer.class,
                it -> Objects.equals(name, it.getName())
        );
    }
}
