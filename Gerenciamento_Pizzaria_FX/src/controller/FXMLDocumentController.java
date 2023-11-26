/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controller;

import model.ListaLigadaCircular;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Pizza;

/**
 *
 * @author steli
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableColumn<Pizza, String> ColunaBorda;

    @FXML
    private TableColumn<Pizza, Integer> ColunaId;

    @FXML
    private TableColumn<Pizza, String> ColunaMolho;

    @FXML
    private TableColumn<Pizza, Double> ColunaPreco;

    @FXML
    private TableColumn<Pizza, String> ColunaSabor;

    @FXML
    private TableView<Pizza> tabela;

    private ObservableList<Pizza> observableListe;
    @FXML
    private TextField txtBorda;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMolho;

    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtRecheio;
    @FXML
    private TableColumn<Pizza, String> ColunaRecheio;

    @FXML
    private TextField txtSabor;
    private ListaLigadaCircular listaPizza = new ListaLigadaCircular();
    // private Pizza pizza = new Pizza();

    @FXML
    void btnListar(ActionEvent event) {
        ListaLigadaCircular listaligada = new ListaLigadaCircular();
        listaligada.exibirPizza();

        List<Pizza> lista = listaligada.getPizzas();

        ColunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColunaSabor.setCellValueFactory(new PropertyValueFactory<>("sabor"));
        ColunaRecheio.setCellValueFactory(new PropertyValueFactory<>("recheio"));
        ColunaPreco.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        ColunaMolho.setCellValueFactory(new PropertyValueFactory<>("molho"));
        ColunaBorda.setCellValueFactory(new PropertyValueFactory<>("borda"));

        observableListe = FXCollections.observableArrayList(lista);
        tabela.setItems(observableListe);
    }

    @FXML
    void listarOrdem(ActionEvent event) {
        ListaLigadaCircular listaligada = new ListaLigadaCircular();
        listaligada.exibirPizza();

        List<Pizza> lista = listaligada.obterListaEmOrdemCrescentePizza();

        ColunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColunaSabor.setCellValueFactory(new PropertyValueFactory<>("sabor"));
        ColunaRecheio.setCellValueFactory(new PropertyValueFactory<>("recheio"));
        ColunaPreco.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        ColunaMolho.setCellValueFactory(new PropertyValueFactory<>("molho"));
        ColunaBorda.setCellValueFactory(new PropertyValueFactory<>("borda"));

        observableListe = FXCollections.observableArrayList(lista);
        tabela.setItems(observableListe);
    }

    @FXML
    void editar(ActionEvent event) {
        Pizza pizzaAtualizada = new Pizza();
        pizzaAtualizada.setId(Integer.parseInt(txtId.getText()));
        pizzaAtualizada.setSabor(txtSabor.getText());
        pizzaAtualizada.setPrecoUnitario(Double.parseDouble(txtPreco.getText()));
        pizzaAtualizada.setMolho(txtMolho.getText());
        pizzaAtualizada.setBorda(txtBorda.getText());
        pizzaAtualizada.setRecheio(txtRecheio.getText());
        listaPizza.atualizarPeloIdPizza(Integer.parseInt(txtId.getText()), pizzaAtualizada);
        listarOrdem(event);
        atualizarPagina(event);

    }

    void obterLinhaSelecionada(Pizza pizza) {
        Pizza pizzaSelecionada = tabela.getSelectionModel().getSelectedItem();

        if (pizza != null) {
            preencherCamposPizzaSelecionada(pizzaSelecionada);
        } else {
            System.out.println("Nenhuma linha selecionada.");
        }
    }

    private void preencherCamposPizzaSelecionada(Pizza pizza) {
        txtId.setText(String.valueOf(pizza.getId()));
        txtSabor.setText(pizza.getSabor());
        txtPreco.setText(String.valueOf(pizza.getPrecoUnitario()));
        txtMolho.setText(pizza.getMolho());
        txtBorda.setText(pizza.getBorda());
        txtRecheio.setText(pizza.getRecheio());

    }

    @FXML
    void gravar(ActionEvent event) {
        Pizza pizzaAtualizada = new Pizza();
        
      //  listaPizza.
    //    txtSabor.getText();
        
        pizzaAtualizada.setId(Integer.parseInt(txtId.getText()));
        pizzaAtualizada.setSabor(txtSabor.getText());
        pizzaAtualizada.setPrecoUnitario(Double.parseDouble(txtPreco.getText()));
        pizzaAtualizada.setMolho(txtMolho.getText());
        pizzaAtualizada.setBorda(txtBorda.getText());
        pizzaAtualizada.setRecheio(txtRecheio.getText());
        listaPizza.adicionar(pizzaAtualizada);
        listaPizza.gravarEmArquivo("Pizza.txt");
        atualizarPagina(event);
        listarOrdem(event);
    }

    @FXML
    void remover(ActionEvent event) {
        listaPizza.removerPeloIdPizza(Integer.parseInt(txtId.getText()));
        atualizarPagina(event);
    }

    @FXML
    void atualizarPagina(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLDocument.fxml"));
            Parent root = loader.load();

            // Seu código para configurar o controlador, se necessário
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Lide com a exceção conforme necessário
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tabela.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> obterLinhaSelecionada(newValue)
        );
        txtId.setDisable(true);
        listaPizza.exibirPizza();
        int a = 0;
        a = listaPizza.sizePizza();
        txtId.setText(String.valueOf(a));

    }
    
        @FXML
    void voltar(ActionEvent event) {
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Tela_Menu_Func.fxml"));
            Parent root = loader.load();

            // Seu código para configurar o controlador, se necessário
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Lide com a exceção conforme necessário
        }
    }

}
