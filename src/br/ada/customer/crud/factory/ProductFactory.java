package br.ada.customer.crud.factory;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.integration.memoryrepository.ProductMemoryRepositoryImpl;
import br.ada.customer.crud.usecases.IProductUseCase;
import br.ada.customer.crud.usecases.impl.ProductUseCaseImpl;
import br.ada.customer.crud.usecases.repository.ProductRepository;

public class ProductFactory {

    // Método para criar uma instância de IProductUseCase.
    public static IProductUseCase createUseCase() {
        // Cria uma instância de ProductUseCaseImpl, passando uma instância de repositório criada localmente.
        return new ProductUseCaseImpl(
                createRepository()
        );
    }

    // Método para criar uma instância de ProductRepository.
    public static ProductRepository createRepository() {
        // Cria uma instância de ProductMemoryRepositoryImpl, passando uma instância de MemoryDatabase.
        return new ProductMemoryRepositoryImpl(
                MemoryDatabase.getInstance()
        );
    }
}
