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

 public class Pizza implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String sabor;
    private String borda;
    private String molho;
    private double precoUnitario;
    private String recheio;

    public Pizza() {
    }
    

    public Pizza(int id,String recheio,String sabor, String borda, String molho, double precoUnitario) {
        this.id = id;
        this.recheio = recheio;
        this.sabor = sabor;
        this.borda = borda;
        this.molho = molho;
        this.precoUnitario = precoUnitario;
    }

    public double calcularPreco(int quantidade) {
        return precoUnitario * quantidade;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "Id='" + id + '\'' +
                ",Recheio='" + recheio + '\'' +
                ",sabor='" + sabor + '\'' +
                ", borda='" + borda + '\'' +
                ", molho='" + molho + '\'' +
                ", precoUnitario=" + precoUnitario +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getBorda() {
        return borda;
    }

    public void setBorda(String borda) {
        this.borda = borda;
    }

    public String getMolho() {
        return molho;
    }

    public void setMolho(String molho) {
        this.molho = molho;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getRecheio() {
        return recheio;
    }

    public void setRecheio(String recheio) {
        this.recheio = recheio;
    }

    
}


