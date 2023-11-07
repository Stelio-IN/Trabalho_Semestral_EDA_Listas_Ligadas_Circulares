/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dell
 */
public class Pizzas {
    private String Mexicana;
    private String vegetariana;
    private String cogumelos;
    private String peperone;
    private Double valor_unitario;
    private String data_pagamento;
    private int quantidadePizzas;

    public String getMexicana() {
        return Mexicana;
    }

    public void setMexicana(String Mexicana) {
        this.Mexicana = Mexicana;
    }

    public String getVegetariana() {
        return vegetariana;
    }

    public void setVegetariana(String vegetariana) {
        this.vegetariana = vegetariana;
    }

    public String getCogumelos() {
        return cogumelos;
    }

    public void setCogumelos(String cogumelos) {
        this.cogumelos = cogumelos;
    }

    public String getPeperone() {
        return peperone;
    }

    public void setPeperone(String peperone) {
        this.peperone = peperone;
    }

    public Double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(Double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public String getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public int getQuantidadePizzas() {
        return quantidadePizzas;
    }

    public void setQuantidadePizzas(int quantidadePizzas) {
        this.quantidadePizzas = quantidadePizzas;
    }

    public Pizzas(String Mexicana, String vegetariana, String cogumelos, String peperone, Double valor_unitario, String data_pagamento, int quantidadePizzas) {
        this.Mexicana = Mexicana;
        this.vegetariana = vegetariana;
        this.cogumelos = cogumelos;
        this.peperone = peperone;
        this.valor_unitario = valor_unitario;
        this.data_pagamento = data_pagamento;
        this.quantidadePizzas = quantidadePizzas;
    }
    
    
    
}
