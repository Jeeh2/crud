package br.ada.customer.crud.factory;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.integration.email.CustomerEmailNotifierImpl;
import br.ada.customer.crud.integration.email.SendEmail;
import br.ada.customer.crud.integration.memoryrepository.CustomerMemoryRepositoryImpl;
import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.usecases.ICustomerUseCase;
import br.ada.customer.crud.usecases.INotifierUserCase;
import br.ada.customer.crud.usecases.impl.CustomerUseCaseImpl;
import br.ada.customer.crud.usecases.repository.CustomerRepository;

//Decide qual implentação
public class CustomerFactory {

    // Método para criar uma instância de ICustomerUseCase.
    public static ICustomerUseCase createUseCase() {
        // Cria uma instância de CustomerUseCaseImpl, passando uma instância de CustomerRepository e INotifierUserCase.
        return new CustomerUseCaseImpl(
                createRepository(),
                createNotifier()
        );
    }

    // Método para criar uma instância de CustomerRepository.
    public static CustomerRepository createRepository() {
        // Cria uma instância de CustomerMemoryRepositoryImpl, passando uma instância de MemoryDatabase.
        return new CustomerMemoryRepositoryImpl(MemoryDatabase.getInstance());
    }

    // Método para criar uma instância de INotifierUserCase<Customer>.
    public static INotifierUserCase<Customer> createNotifier() {
        // Cria uma instância de CustomerEmailNotifierImpl, passando uma instância de SendEmail.
        return new CustomerEmailNotifierImpl(new SendEmail());
    }
}

