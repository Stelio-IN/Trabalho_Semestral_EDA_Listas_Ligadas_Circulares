/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gerenciamento_pizzaria_fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author $umeid_ibr
 */
public class Tela_LoginController implements Initializable {

    @FXML
    private TextField txtEmail;

   @FXML
    private PasswordField txtPassword;

    public void Tela_de_Entrada(ActionEvent event, String caminho) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Entrar(ActionEvent event) throws IOException {
        if (txtEmail.getText().equalsIgnoreCase("funcionario@gmail.com")) {
            Tela_de_Entrada(event,"/gerenciamento_pizzaria_fx/Tela_Menu_Func.fxml");
        }
        if (txtEmail.getText().equalsIgnoreCase("cliente@gmail.com")) {
            Tela_de_Entrada(event,"/gerenciamento_pizzaria_fx/Tela_Fazer_Pedido.fxml");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
