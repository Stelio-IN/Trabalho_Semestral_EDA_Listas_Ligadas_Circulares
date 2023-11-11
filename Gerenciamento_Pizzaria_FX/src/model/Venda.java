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
    private Pizza produto;
    private Salgadinho salgadinho;
    private int quantidadePizza;
    private int quantidadeSalgado;
    private double valorTotal;

}
