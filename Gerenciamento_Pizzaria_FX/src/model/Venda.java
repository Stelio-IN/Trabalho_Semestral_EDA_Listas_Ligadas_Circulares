/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author steli
 */
public class Venda implements Serializable{

    private static final long serialVersionUID = 1L;
    private int id;
    private Pizza pizza;
    private String nome_Pizza;
    private String nome_Salgadinho;
    private Salgadinho salgadinho;
    private int quantidadePizza;
    private int quantidadeSalgado;
    private double valorTotal;
    private String situacao;
    private String cliente;
    private String celular;
    private String morada;

    public Venda(int id, String nome_Pizza, String nome_Salgadinho, int quantidadePizza, int quantidadeSalgado, double valorTotal, String situacao, String cliente, String celular, String morada) {
        this.id = id;
        this.nome_Pizza = nome_Pizza;
        this.nome_Salgadinho = nome_Salgadinho;
        this.quantidadePizza = quantidadePizza;
        this.quantidadeSalgado = quantidadeSalgado;
        this.valorTotal = valorTotal;
        this.situacao = situacao;
        this.cliente = cliente;
        this.celular = celular;
        this.morada = morada;
    }

    public Venda() {
    }

    
    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Salgadinho getSalgadinho() {
        return salgadinho;
    }

    public void setSalgadinho(Salgadinho salgadinho) {
        this.salgadinho = salgadinho;
    }

    public int getQuantidadePizza() {
        return quantidadePizza;
    }

    public void setQuantidadePizza(int quantidadePizza) {
        this.quantidadePizza = quantidadePizza;
    }

    public int getQuantidadeSalgado() {
        return quantidadeSalgado;
    }

    public void setQuantidadeSalgado(int quantidadeSalgado) {
        this.quantidadeSalgado = quantidadeSalgado;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getNome_Pizza() {
        return nome_Pizza;
    }

    public void setNome_Pizza(String nome_Pizza) {
        this.nome_Pizza = nome_Pizza;
    }

    public String getNome_Salgadinho() {
        return nome_Salgadinho;
    }

    public void setNome_Salgadinho(String nome_Salgadinho) {
        this.nome_Salgadinho = nome_Salgadinho;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", pizza=" + pizza + ", nome_Pizza=" + nome_Pizza + ", nome_Salgadinho=" + nome_Salgadinho + ", salgadinho=" + salgadinho + ", quantidadePizza=" + quantidadePizza + ", quantidadeSalgado=" + quantidadeSalgado + ", valorTotal=" + valorTotal + ", situacao=" + situacao + ", cliente=" + cliente + ", celular=" + celular + ", morada=" + morada + '}';
    }

    
    
}
