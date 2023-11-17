/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.ListaLigadaCircular;

/**
 *
 * @author steli
 */
public class Ficheiro {

    public static void gravarEmArquivo(ListaLigadaCircular lista, String nomeArquivo) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            No temp = lista.getCabeca();
            do {
                outputStream.writeObject(temp);
                temp = temp.proximo;
            } while (temp != lista.getCabeca());

            System.out.println("Lista gravada em arquivo: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao gravar em arquivo: " + e.getMessage());
        }
    }

    public static void carregarDoArquivo(ListaLigadaCircular lista, String nomeArquivo) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            lista.setCabeca((No) inputStream.readObject());
            No temp = lista.getCabeca();
            while (temp.proximo != null && temp.proximo != lista.getCabeca()) {
                temp = (No) inputStream.readObject();
                temp.proximo = (No) inputStream.readObject();
            }
            System.out.println("Lista carregada do arquivo: " + nomeArquivo);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar do arquivo: " + e.getMessage());
        }
    }
}
