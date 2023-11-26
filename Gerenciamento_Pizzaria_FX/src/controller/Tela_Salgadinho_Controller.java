/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Salgadinho;

/**
 * FXML Controller class
 *
 * @author steli
 */
public class Tela_Salgadinho_Controller implements Initializable {

    @FXML
    private TableColumn<Salgadinho, Integer> ColunaId;

    @FXML
    private TableColumn<Salgadinho, String> ColunaMassa;

    @FXML
    private TableColumn<Salgadinho, Double> ColunaPreco;

    @FXML
    private TableColumn<Salgadinho, String> ColunaRecheio;

    @FXML
    private TableColumn<Salgadinho, String> ColunaTipo;

    @FXML
    private TableView<Salgadinho> tabela;
    @FXML
    private TextField txtNome;
    @FXML
    private TableColumn<Salgadinho, String> ColunaNome;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMassa;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtRecheio;

    @FXML
    private TextField txtTipo;
    private ListaLigadaCircular listaSalgado = new ListaLigadaCircular();

    private ObservableList<Salgadinho> observableListe;

    @FXML
    void editar(ActionEvent event) {
        Salgadinho salgadoAtualizado = new Salgadinho();
        salgadoAtualizado.setId(Integer.parseInt(txtId.getText()));
        salgadoAtualizado.setNome(txtNome.getText());

        salgadoAtualizado.setTipo(txtTipo.getText());
        salgadoAtualizado.setPrecoUnitario(Double.parseDouble(txtPreco.getText()));
        salgadoAtualizado.setMassa(txtMassa.getText());
        salgadoAtualizado.setRecheio(txtRecheio.getText());
        listaSalgado.atualizarPeloIdSalgado(Integer.parseInt(txtId.getText()), salgadoAtualizado);
        listarOrdem(event);
        atualizarPagina(event);

    }

    @FXML
    void btnListar(ActionEvent event) {
        ListaLigadaCircular listaligada = new ListaLigadaCircular();
        listaligada.exibirSalgado();

        List<Salgadinho> lista = listaligada.getSalgados();

        ColunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        ColunaMassa.setCellValueFactory(new PropertyValueFactory<>("massa"));
        ColunaPreco.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        ColunaRecheio.setCellValueFactory(new PropertyValueFactory<>("recheio"));
        ColunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        observableListe = FXCollections.observableArrayList(lista);
        tabela.setItems(observableListe);
    }

    @FXML
    void listarOrdem(ActionEvent event) {
        ListaLigadaCircular listaligada = new ListaLigadaCircular();
        listaligada.exibirPizza();

        List<Salgadinho> lista = listaSalgado.obterListaEmOrdemCrescenteSalgado();

        ColunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        ColunaMassa.setCellValueFactory(new PropertyValueFactory<>("massa"));
        ColunaPreco.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        ColunaRecheio.setCellValueFactory(new PropertyValueFactory<>("recheio"));
        ColunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        observableListe = FXCollections.observableArrayList(lista);
        tabela.setItems(observableListe);
    }

    void obterLinhaSelecionada(Salgadinho salgado) {
        Salgadinho salgadoSelecionado = tabela.getSelectionModel().getSelectedItem();

        if (salgado != null) {
            preencherCamposPizzaSelecionada(salgadoSelecionado);
        } else {
            System.out.println("Nenhuma linha selecionada.");
        }
    }

    private void preencherCamposPizzaSelecionada(Salgadinho salgado) {
        txtId.setText(String.valueOf(salgado.getId()));
        txtTipo.setText(salgado.getTipo());
        txtPreco.setText(String.valueOf(salgado.getPrecoUnitario()));
        txtRecheio.setText(salgado.getRecheio());
        txtMassa.setText(salgado.getMassa());
        txtNome.setText(salgado.getNome());

    }

    @FXML
    void gravar(ActionEvent event) {
        Salgadinho salgadoAtualizado = new Salgadinho();
        salgadoAtualizado.setId(Integer.parseInt(txtId.getText()));
        salgadoAtualizado.setNome(txtNome.getText());

        salgadoAtualizado.setTipo(txtTipo.getText());
        salgadoAtualizado.setPrecoUnitario(Double.parseDouble(txtPreco.getText()));
        salgadoAtualizado.setMassa(txtMassa.getText());
        salgadoAtualizado.setRecheio(txtRecheio.getText());
        listaSalgado.adicionar(salgadoAtualizado);
        listaSalgado.gravarEmArquivo("Salgado.txt");
        listarOrdem(event);
        atualizarPagina(event);
    }

    @FXML
    void remover(ActionEvent event) {
        listaSalgado.removerPeloIdSalgado(Integer.parseInt(txtId.getText()));
        atualizarPagina(event);
    }

    @FXML
    void atualizarPagina(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Tela_Salgadinho.fxml"));
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
        listaSalgado.exibirSalgado();
        int a = 0;
        a = listaSalgado.sizeSalgado();
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
