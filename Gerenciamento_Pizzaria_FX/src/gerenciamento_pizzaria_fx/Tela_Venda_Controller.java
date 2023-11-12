/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gerenciamento_pizzaria_fx;

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
import model.ListaLigadaCircular;
import model.Venda;

/**
 * FXML Controller class
 *
 * @author steli
 */
public class Tela_Venda_Controller implements Initializable {

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
        
        int pendentes =0; 
        int efectuados =0; 
        int cancelados =0; 
        for(Venda a: lista){
            if(a.getSituacao().equals("Pendente")) pendentes++;
            if(a.getSituacao().equals("Pago")) efectuados++;
            if(a.getSituacao().equals("Cancelado")) cancelados++;           
        }
        txtPagamentosCancelados.setText(String.valueOf(cancelados));
        txtPagamentosEfectuados.setText(String.valueOf(efectuados));
        txtPagamentosPendentes.setText(String.valueOf(pendentes));
        
    }

    @FXML
    void atualizarPagina(ActionEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Tela_Venda.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaVenda.exibirVenda();
        TabelaVenda.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> obterLinhaSelecionada(newValue)
        );
        ListarVendas();
    }
    
    

}
