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
class No implements Comparable<No>, Serializable {
    Object dado;
    No proximo;

    public No(Object dado) {
        this.dado = dado;
        this.proximo = null;
    }

    @Override
    public int compareTo(No outroNo) {
        // Implementação para comparar com base nos valores dos nós
        // Neste exemplo, assumindo que os dados são comparáveis
        return ((Comparable<Object>) this.dado).compareTo(outroNo.dado);
    }
}
