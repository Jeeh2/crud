package br.ada.customer.crud.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

// A classe Customer implementa a interface Serializable, o que permite que objetos desta classe sejam serializados.
public class Customer implements Serializable, Comparable<Customer> {

    // Variável para armazenar o ID do cliente.
    private Long id;

    // Variável para armazenar o nome do cliente.
    private String name;

    // Variável para armazenar o documento do cliente (por exemplo, CPF).
    private String document;

    // Lista de endereços de email associados ao cliente.
    private List<String> email;

    // Lista de números de telefone associados ao cliente.
    private List<String> telephone;

    // Variável para armazenar a data de nascimento do cliente.
    private LocalDate birthDate;

    // Método para obter o ID do cliente.
    public Long getId() {
        return id;
    }

    // Método para definir o ID do cliente.
    public void setId(Long id) {
        this.id = id;
    }

    // Método para obter o nome do cliente.
    public String getName() {
        return name;
    }

    // Método para definir o nome do cliente.
    public void setName(String name) {
        this.name = name;
    }

    // Método para obter o documento do cliente.
    public String getDocument() {
        return document;
    }

    // Método para definir o documento do cliente.
    public void setDocument(String document) {
        this.document = document;
    }

    // Método para obter a lista de endereços de email do cliente.
    public List<String> getEmail() {
        return email;
    }

    // Método para definir a lista de endereços de email do cliente.
    public void setEmail(List<String> email) {
        this.email = email;
    }

    // Método para obter a lista de números de telefone do cliente.
    public List<String> getTelephone() {
        return telephone;
    }

    // Método para definir a lista de números de telefone do cliente.
    public void setTelephone(List<String> telephone) {
        this.telephone = telephone;
    }

    // Método para obter a data de nascimento do cliente.
    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Método para definir a data de nascimento do cliente.
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // Implementação do método compareTo da interface Comparable<Customer> para permitir a comparação de clientes com base em seus IDs.
    @Override
    public int compareTo(Customer other) {
        return this.id.compareTo(other.id);
    }
}