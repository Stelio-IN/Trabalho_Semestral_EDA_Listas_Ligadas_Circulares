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
public class Salgadinho implements Serializable {

   private static final long serialVersionUID = 1L;
   private int id;
    private String tipo;
    private String massa;
    private String recheio;
    private double precoUnitario;

    public Salgadinho(int id,String tipo, String massa, String recheio, double precoUnitario) {
        
        this.id = id;
        this.tipo = tipo;
        this.massa = massa;
        this.recheio = recheio;
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return "Salgadinho{" + "id=" + id + ", tipo=" + tipo + ", massa=" + massa + ", recheio=" + recheio + ", precoUnitario=" + precoUnitario + '}';
    }
    

    public double calcularPreco(int quantidade) {
        return precoUnitario * quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMassa() {
        return massa;
    }

    public void setMassa(String massa) {
        this.massa = massa;
    }

    public String getRecheio() {
        return recheio;
    }

    public void setRecheio(String recheio) {
        this.recheio = recheio;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
