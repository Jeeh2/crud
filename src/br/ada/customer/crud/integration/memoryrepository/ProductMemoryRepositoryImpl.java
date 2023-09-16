package br.ada.customer.crud.integration.memoryrepository;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.model.Product;
import br.ada.customer.crud.usecases.repository.ProductRepository;
import br.ada.customer.crud.usecases.repository.RepositoryException;

import java.util.List;
import java.util.Objects;

public class ProductMemoryRepositoryImpl implements ProductRepository {

    // Uma referência à instância de MemoryDatabase utilizada por este repositório.
    private final MemoryDatabase database;

    // Construtor que recebe uma instância de MemoryDatabase.
    public ProductMemoryRepositoryImpl(MemoryDatabase database) {
        this.database = database;
    }

    // Método para salvar um produto (Product) no repositório.
    @Override
    public void save(Product product) throws RepositoryException {
        // Atribui um novo ID ao produto e, em seguida, salva ou atualiza o produto no banco de dados.
        product.setId(database.nextId());
        database.saveOrUpdate(product);
    }

    // Método para listar todos os produtos no repositório.
    @Override
    public List<Product> listAll() throws RepositoryException {
        // Retorna uma lista de todos os produtos armazenados no banco de dados.
        return database.listAll(Product.class);
    }

    // Método para encontrar um produto pelo ID.
    @Override
    public Product findById(Long id) throws RepositoryException {
        // Encontra um produto com base no ID fornecido.
        return database.find(
                Product.class,
                it -> Objects.equals(id, it.getId())
        ).stream().findFirst().orElse(null);
    }

    // Método para atualizar um produto no repositório.
    @Override
    public void update(Product product) throws RepositoryException {
        // Encontra o produto pelo ID e atualiza suas informações no banco de dados.
        Product inserted = findById(product.getId());
        inserted.setDescription(product.getDescription());
        inserted.setBarcode(product.getBarcode());
        inserted.setPrice(product.getPrice());
        database.saveOrUpdate(inserted);
    }

    // Método para excluir um produto do repositório.
    @Override
    public Product delete(Product product) throws RepositoryException {
        // Remove o produto do banco de dados e o retorna.
        return database.delete(product);
    }

    // Método para encontrar produtos com base na descrição.
    @Override
    public List<Product> findByDescription(String description) {
        // Retorna uma lista de produtos com a descrição fornecida.
        return database.find(
                Product.class,
                it -> Objects.equals(description, it.getDescription())
        );
    }

    // Método para encontrar um produto pelo código de barras (barcode).
    @Override
    public Product findByBarcode(String barcode) {
        // Encontra um produto com base no código de barras fornecido.
        return database.find(
                Product.class,
                it -> Objects.equals(barcode, it.getBarcode())
        ).stream().findFirst().orElse(null);
    }
}

