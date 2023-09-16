package br.ada.customer.crud.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class Product implements Serializable, Comparable<Product> {

    // Variável para armazenar o ID do produto.
    private Long id;

    // Variável para armazenar a descrição do produto.
    private String description;

    // Variável para armazenar o código de barras do produto.
    private String barcode;

    // Variável para armazenar o preço do produto.
    private BigDecimal price;

    // Método para obter o ID do produto.
    public Long getId() {
        return id;
    }

    // Método para definir o ID do produto.
    public void setId(Long id) {
        this.id = id;
    }

    // Método para obter a descrição do produto.
    public String getDescription() {
        return description;
    }

    // Método para definir a descrição do produto.
    public void setDescription(String description) {
        this.description = description;
    }

    // Método para obter o código de barras do produto.
    public String getBarcode() {
        return barcode;
    }

    // Método para definir o código de barras do produto.
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    // Método para obter o preço do produto.
    public BigDecimal getPrice() {
        return price;
    }

    // Método para definir o preço do produto.
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int compareTo(Product o) {
        return this.id.compareTo(o.id);
    }
}