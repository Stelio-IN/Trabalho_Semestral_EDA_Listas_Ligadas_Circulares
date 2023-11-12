/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gerenciamento_pizzaria_fx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ListaLigadaCircular;
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
        listaPizza.atualizarPeloIdPizza(Integer.parseInt(txtId.getText()), pizzaAtualizada);
        listarOrdem(event);

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
      //  pizza = listaPizza.obterPizzaPorId(pizza.getId());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tabela.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> obterLinhaSelecionada(newValue)
        );
        txtId.setDisable(true);
        listaPizza.exibirPizza();

    }

}
