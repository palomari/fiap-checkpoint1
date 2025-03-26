package br.com.fiap.checkpoint1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O prato é obrigatório")
    private String prato;

    private LocalDate dataPedido = LocalDate.now();

    @PositiveOrZero(message = "O valor do pedido não pode ser negativo")
    private double valor;

    public Pedido() {}

    public Pedido(String prato, double valor) {
        this.prato = prato;
        this.valor = valor;
        this.dataPedido = LocalDate.now();
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getPrato() {
        return prato;
    }

    public void setPrato(String prato) {
        this.prato = prato;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
