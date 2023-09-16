package br.ada.customer.crud.model;

import java.math.BigDecimal;

/**
 * Representação de um item do pedido
 */
public class OrderItem {

    // Variável para armazenar o ID do item de pedido.
    private Long id;

    // Variável para armazenar o produto associado ao item de pedido.
    private Product product;

    // Valor de venda do item.
    private BigDecimal saleValue;

    // Quantidade de unidades deste item no pedido.
    private Integer amount;

    // Método para obter o ID do item de pedido.
    public Long getId() {
        return id;
    }

    // Método para definir o ID do item de pedido.
    public void setId(Long id) {
        this.id = id;
    }

    // Método para obter o produto associado ao item de pedido.
    public Product getProduct() {
        return product;
    }

    // Método para definir o produto associado ao item de pedido.
    public void setProduct(Product product) {
        this.product = product;
    }

    // Método para obter o valor de venda do item.
    public BigDecimal getSaleValue() {
        return saleValue;
    }

    // Método para definir o valor de venda do item.
    public void setSaleValue(BigDecimal saleValue) {
        this.saleValue = saleValue;
    }

    // Método para obter a quantidade de unidades deste item no pedido.
    public Integer getAmount() {
        return amount;
    }

    // Método para definir a quantidade de unidades deste item no pedido.
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}