/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import model.ListaLigadaCircular;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Venda;

/**
 * FXML Controller class
 *
 * @author $umeid_ibr
 */
public class Tela_Menu_FuncController implements Initializable {

    @FXML
    private TableView<Venda> TabelaVenda;

    @FXML
    private TableColumn<Venda, String> colunaCelular;

    @FXML
    private TableColumn<Venda, String> colunaCliente;

    @FXML
    private TableColumn<Venda, Integer> colunaId;

    @FXML
    private TableColumn<Venda, String> colunaSituacao;

    @FXML
    private TableColumn<Venda, Double> colunaValor;

    @FXML
    private TextField txtClienteCelular;

    @FXML
    private TextField txtClienteMorada;

    @FXML
    private TextField txtClienteNome;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPagamentosCancelados;

    @FXML
    private TextField txtPagamentosEfectuados;

    @FXML
    private TextField txtPagamentosPendentes;

    @FXML
    private TextField txtPizza;

    @FXML
    private TextField txtPizzaQuant;

    @FXML
    private TextField txtSalgado;

    @FXML
    private TextField txtSalgadoQuant;

    @FXML
    private TextField txtSituacao;

    @FXML
    private TextField p1;

    @FXML
    private TextField p2;

    @FXML
    private TextField s1;

    @FXML
    private TextField s2;
    @FXML
    private TextField txtTotalPagar;
    private ListaLigadaCircular listaVenda = new ListaLigadaCircular();
    private Venda produto;
    private ObservableList<Venda> observableListeVenda;

    private void preencherCamposPizzaSelecionada(Venda venda) {
        //  produto = venda;
        txtId.setText(String.valueOf(venda.getId()));

        txtClienteNome.setText(venda.getCliente());
        txtClienteCelular.setText(venda.getCelular());
        txtClienteMorada.setText(venda.getMorada());
        txtPizza.setText(venda.getNome_Pizza());
        txtSalgado.setText(venda.getNome_Salgadinho());
        txtPizzaQuant.setText(String.valueOf(venda.getQuantidadePizza()));
        txtSalgadoQuant.setText(String.valueOf(venda.getQuantidadeSalgado()));
        txtTotalPagar.setText(String.valueOf(venda.getValorTotal()));
        txtSituacao.setText(venda.getSituacao());
    }

    void obterLinhaSelecionada(Venda venda) {
        produto = venda;
        Venda vendaSelecionada = TabelaVenda.getSelectionModel().getSelectedItem();

        if (venda != null) {
            preencherCamposPizzaSelecionada(vendaSelecionada);
        } else {
            System.out.println("Nenhuma linha selecionada.");
        }
    }

    void ListarVendas() {
        ListaLigadaCircular listaligada = new ListaLigadaCircular();
        listaligada.exibirVenda();

        List<Venda> lista = listaligada.getVendas();

        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        colunaSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        colunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>(" valorTotal"));

        observableListeVenda = FXCollections.observableArrayList(lista);
        TabelaVenda.setItems(observableListeVenda);

        int pendentes = 0;
        int efectuados = 0;
        int cancelados = 0;
        for (Venda a : lista) {
            if (a.getSituacao().equals("Pendente")) {
                pendentes++;
            }
            if (a.getSituacao().equals("Pago")) {
                efectuados++;
            }
            if (a.getSituacao().equals("Cancelado")) {
                cancelados++;
            }
        }
        txtPagamentosCancelados.setText(String.valueOf(cancelados));
        txtPagamentosEfectuados.setText(String.valueOf(efectuados));
        txtPagamentosPendentes.setText(String.valueOf(pendentes));

    }

    @FXML
    void atualizarPagina(ActionEvent event) {
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

    @FXML
    void cancelarPagamento(ActionEvent event) {
        produto.setSituacao("Cancelado");
        listaVenda.atualizarPeloIdVenda(produto.getId(), produto);
        atualizarPagina(event);
    }

    @FXML
    void confirmarPagamento(ActionEvent event) {
        produto.setSituacao("Pago");
        listaVenda.atualizarPeloIdVenda(produto.getId(), produto);
        atualizarPagina(event);
    }

    @FXML
    void Tela_Gestao_Pizza(ActionEvent event) {
        carregarTela("/view/FXMLDocument");
    }

    @FXML
    void Tela_Gestao_Salgadinho(ActionEvent event) {
        carregarTela("/view/Tela_Salgadinho");
    }

    @FXML
    void Tela_Realizar_Venda(ActionEvent event) {
        atualizarPagina(event);
    }

    @FXML
    private AnchorPane PainelTelaLogin;
    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    void logOut(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("FECHAR");
        alerta.setHeaderText("Quer realment Fechar");
        alerta.setContentText("Deseja salvar antes de Fechar");
        if (alerta.showAndWait().get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Tela_Login.fxml"));
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

    @FXML
    private BorderPane borderPane;

    private void carregarTela(String tela) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(tela + ".fxml"));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Tela_Menu_FuncController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        borderPane.setCenter(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaVenda.exibirVenda();
        TabelaVenda.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> obterLinhaSelecionada(newValue)
        );

        ListarVendas();
        p1.setVisible(false);
        p2.setVisible(false);
        s2.setVisible(false);
        s1.setVisible(false);

    }

    @FXML
    void ocultarPizzaDetalhes(MouseEvent event) {
        p1.setVisible(false);
        p2.setVisible(false);
    }

    @FXML
    void ocultarSalgadoDetalhes(MouseEvent event) {
        s2.setVisible(false);
        s1.setVisible(false);
    }

    @FXML
    void pizzaDetalhes(MouseEvent event) {
        p1.setText(produto.getPizza().getRecheio());
        p2.setText(produto.getPizza().getMolho());
        p2.setVisible(true);
        p1.setVisible(true);

    }

    @FXML
    void salgadoDetalhes(MouseEvent event) {
        s1.setText(produto.getSalgadinho().getRecheio());
        s2.setText(produto.getSalgadinho().getMassa());
        s2.setVisible(true);
        s1.setVisible(true);

    }

}
