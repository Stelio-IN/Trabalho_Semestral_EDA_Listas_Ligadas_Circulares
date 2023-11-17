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
    //    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
  //      Parent root = FXMLLoader.load(getClass().getResource("Tela_Salgadinho.fxml"));
     // Parent root = FXMLLoader.load(getClass().getResource("Tela_Fazer_Pedido.fxml"));
      Parent root = FXMLLoader.load(getClass().getResource("/view/Tela_Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          launch(args);
    }

}
