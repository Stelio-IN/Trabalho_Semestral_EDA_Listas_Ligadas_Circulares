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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author steli
 */
public class ListaLigadaCircular {

    private No cabeca;

    public ListaLigadaCircular() {
        this.cabeca = null;
    }

    public void adicionar(Object objeto) {
        No novoNo = new No(objeto);

        if (cabeca == null) {
            cabeca = novoNo;
            cabeca.proximo = cabeca; // Fazendo o último nó apontar de volta para o primeiro
        } else {
            No temp = cabeca;
            while (temp.proximo != cabeca) {
                temp = temp.proximo;
            }
            temp.proximo = novoNo;
            novoNo.proximo = cabeca;
        }
    }

    public void gravarEmArquivo(String nomeArquivo) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            No temp = cabeca;
            do {
                outputStream.writeObject(temp);
                temp = temp.proximo;
            } while (temp != cabeca);

            System.out.println("Lista gravada em arquivo: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao gravar em arquivo: " + e.getMessage());
        }
    }

    public void carregarDoArquivo(String nomeArquivo) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            cabeca = (No) inputStream.readObject();
            No temp = cabeca;

            while (temp.proximo != null && temp.proximo != cabeca) {
                temp = (No) inputStream.readObject();
                temp.proximo = (No) inputStream.readObject();
            }

            System.out.println("Lista carregada do arquivo: " + nomeArquivo);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar do arquivo: " + e.getMessage());
        }
    }

    /*
        Pizza
     */
    public void exibirPizza() {
        carregarDoArquivo("Pizza.txt"); // Carregar os dados do arquivo antes de exibir

        if (cabeca == null) {
            System.out.println("A lista está vazia.");
            return;
        }

        No temp = cabeca;
        do {
            System.out.println(temp.dado.toString());
            temp = temp.proximo;
        } while (temp != cabeca);
    }

    public void exibirEmOrdemCrescentePizza() {
        carregarDoArquivo("Pizza.txt"); // Carregar os dados do arquivo antes de exibir

        if (cabeca == null) {
            System.out.println("A lista está vazia.");
            return;
        }

        List<Pizza> listaOrdenada = new ArrayList<>();

        No temp = cabeca;
        do {
            listaOrdenada.add((Pizza) temp.dado);
            temp = temp.proximo;
        } while (temp != cabeca);

        // Ordenar a lista pelo preço
        Collections.sort(listaOrdenada, Comparator.comparing(Pizza::getPrecoUnitario));

        // Imprimir a lista ordenada
        for (Pizza pizza : listaOrdenada) {
            System.out.println(pizza.toString());
        }
    }

    public int sizePizza() {
        carregarDoArquivo("Pizza.txt");
        if (cabeca == null) {
            return 0;
        }

        int count = 0;
        No temp = cabeca;
        do {
            count++;
            temp = temp.proximo;
        } while (temp != cabeca);

        return count;
    }

    public void removerPeloIdPizza(int id) {
        if (cabeca == null) {
            System.out.println("A lista está vazia. Não é possível remover.");
            return;
        }

        No atual = cabeca;
        No anterior = null;

        // Procurar o nó a ser removido
        do {
            if (((Pizza) atual.dado).getId() == id) {
                break;
            }
            anterior = atual;
            atual = atual.proximo;
        } while (atual != cabeca);

        // Se o nó a ser removido é a cabeça
        if (atual == cabeca) {
            // Se há apenas um nó na lista
            if (atual.proximo == cabeca) {
                cabeca = null;
            } else {
                // Caso contrário, ajustar a cabeça e a cauda
                cabeca = cabeca.proximo;
                anterior.proximo = cabeca;
            }
        } else {
            // Remover o nó do meio da lista
            anterior.proximo = atual.proximo;
        }

        System.out.println("Elemento removido pelo ID: " + id);
        gravarEmArquivo("Pizza.txt"); // Atualizar o arquivo após remover
    }

    public void atualizarPeloIdPizza(int id, Object novoObjeto) {
        if (cabeca == null) {
            System.out.println("A lista está vazia. Não é possível atualizar.");
            return;
        }

        No temp = cabeca;

        // Procurar o nó a ser atualizado
        do {
            if (((Pizza) temp.dado).getId() == id) {
                temp.dado = novoObjeto;
                System.out.println("Elemento atualizado pelo ID: " + id);
                gravarEmArquivo("Pizza.txt"); // Atualizar o arquivo após atualizar
                return;
            }
            temp = temp.proximo;
        } while (temp != cabeca);

        System.out.println("Elemento não encontrado pelo ID: " + id);
    }
    

    /*
    Salgado
     */
    public void exibirEmOrdemCrescenteSalgado() {
        carregarDoArquivo("Salgado.txt"); // Carregar os dados do arquivo antes de exibir

        if (cabeca == null) {
            System.out.println("A lista está vazia.");
            return;
        }

        List<Salgadinho> listaOrdenada = new ArrayList<>();

        No temp = cabeca;
        do {
            listaOrdenada.add((Salgadinho) temp.dado);
            temp = temp.proximo;
        } while (temp != cabeca);

        // Ordenar a lista pelo preço
        Collections.sort(listaOrdenada, Comparator.comparing(Salgadinho::getPrecoUnitario));

        // Imprimir a lista ordenada
        for (Salgadinho salgado : listaOrdenada) {
            System.out.println(salgado.toString());
        }
    }

    public void atualizarPeloIdSalgado(int id, Object novoObjeto) {
        if (cabeca == null) {
            System.out.println("A lista está vazia. Não é possível atualizar.");
            return;
        }

        No temp = cabeca;

    
        do {
            if (((Salgadinho) temp.dado).getId() == id) {
                temp.dado = novoObjeto;
                System.out.println("Elemento atualizado pelo ID: " + id);
                gravarEmArquivo("Salgado.txt"); // Atualizar o arquivo após atualizar
                return;
            }
            temp = temp.proximo;
        } while (temp != cabeca);

        System.out.println("Elemento não encontrado pelo ID: " + id);
    }

    public void removerPeloIdSalgado(int id) {
        if (cabeca == null) {
            System.out.println("A lista está vazia. Não é possível remover.");
            return;
        }

        No atual = cabeca;
        No anterior = null;

        // Procurar o nó a ser removido
        do {
            if (((Salgadinho) atual.dado).getId() == id) {
                break;
            }
            anterior = atual;
            atual = atual.proximo;
        } while (atual != cabeca);

        // Se o nó a ser removido é a cabeça
        if (atual == cabeca) {
            // Se há apenas um nó na lista
            if (atual.proximo == cabeca) {
                cabeca = null;
            } else {
                // Caso contrário, ajustar a cabeça e a cauda
                cabeca = cabeca.proximo;
                anterior.proximo = cabeca;
            }
        } else {
            // Remover o nó do meio da lista
            anterior.proximo = atual.proximo;
        }

        System.out.println("Elemento removido pelo ID: " + id);
        gravarEmArquivo("Salgado.txt"); // Atualizar o arquivo após remover
    }

    public void exibirSalgado() {
        carregarDoArquivo("Salgado.txt"); // Carregar os dados do arquivo antes de exibir

        if (cabeca == null) {
            System.out.println("A lista está vazia.");
            return;
        }

        No temp = cabeca;
        do {
            System.out.println(temp.dado.toString());
            temp = temp.proximo;
        } while (temp != cabeca);
    }

    public int sizeSalgado() {
        carregarDoArquivo("Salgado.txt");
        if (cabeca == null) {
            return 0;
        }

        int count = 0;
        No temp = cabeca;
        do {
            count++;
            temp = temp.proximo;
        } while (temp != cabeca);

        return count;
    }

}