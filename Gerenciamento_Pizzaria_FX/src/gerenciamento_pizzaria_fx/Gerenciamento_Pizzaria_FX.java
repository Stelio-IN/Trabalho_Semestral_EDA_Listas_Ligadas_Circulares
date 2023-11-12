/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package gerenciamento_pizzaria_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ListaLigadaCircular;
import model.Pizza;
import model.Salgadinho;
import model.Venda;

/**
 *
 * @author steli
 */
public class Gerenciamento_Pizzaria_FX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
  //      Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
  //      Parent root = FXMLLoader.load(getClass().getResource("Tela_Salgadinho.fxml"));
     // Parent root = FXMLLoader.load(getClass().getResource("Tela_Fazer_Pedido.fxml"));
      Parent root = FXMLLoader.load(getClass().getResource("Tela_Venda.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          launch(args);
//        // System.out.println("Sin");
//        ListaLigadaCircular pizza = new ListaLigadaCircular();
////           (String sabor, String borda, String molho, double precoUnitario)
////        pizza.adicionar(new Pizza(4,"Margarita", "Fina", "Tomate", 900));
////        pizza.adicionar(new Pizza(5,"Margarita", "Fina", "Tomate", 250));
////        pizza.adicionar(new Pizza(0,"Margarita", "Fina", "Tomate", 350));
////        pizza.adicionar(new Pizza(1,"Calabresa", "Normal", "Tomate", 100));
////        pizza.adicionar(new Pizza(2,"Calabresa", "Normal", "Tomate", 800));
////        System.out.println("Sem ordem");
////        pizza.gravarEmArquivo("Pizza.txt");
//
////        System.out.println("Ordem de Preço");
////        System.out.println("");
////        System.out.println("");
////      //  pizza.exibirEmOrdemCrescentePizza();
////        System.out.println( pizza.sizePizza());
//        //(int id,String tipo, String massa, String recheio, double precoUnitario)
//        ListaLigadaCircular salgado = new ListaLigadaCircular();
////        salgado.adicionar(new Salgadinho(0,"Chamussa","Leve","Lulas",50));
////        salgado.adicionar(new Salgadinho(2,"Badjia","Leve","Porco",25));
////        salgado.adicionar(new Salgadinho(1,"Ressol","Grossa","Frango",70));
////        salgado.gravarEmArquivo("Salgado.txt");
//
//        pizza.exibirPizza();
////        pizza.exibirEmOrdemCrescentePizza();
////        salgado.exibirEmOrdemCrescenteSalgado();
//        Pizza Pteste = pizza.obterPizzaPorId(2);
//
//        ListaLigadaCircular venda = new ListaLigadaCircular();
//        //(int id, Pizza produto, Salgadinho salgadinho, int quantidadePizza, int quantidadeSalgado, double valorTotal, String situacao) {
////        venda.adicionar(new Venda(0, Pteste, null, 4, 0, 500, "Pendente"));
////        venda.gravarEmArquivo("Venda.txt");
//        venda.exibirVenda();
//        venda.atualizarPeloIdVenda(0, new Venda(0, Pteste, null, 4, 0, 500, "Pendente"));
//        venda.exibirVenda();
    }

}
