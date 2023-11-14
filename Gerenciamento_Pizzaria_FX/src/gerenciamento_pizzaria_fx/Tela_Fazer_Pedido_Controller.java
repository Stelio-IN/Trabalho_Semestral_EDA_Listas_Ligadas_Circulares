/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gerenciamento_pizzaria_fx;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ListaLigadaCircular;
import model.Pizza;
import model.Salgadinho;
import model.Venda;

/**
 * FXML Controller class
 *
 * @author steli
 */
public class Tela_Fazer_Pedido_Controller implements Initializable {

    @FXML
    private ToggleGroup BordaPizza;

    @FXML
    private TableColumn<Pizza, String> ColunaBorda;

    @FXML
    private TableColumn<Salgadinho, String> ColunaMassa;

    @FXML
    private TableColumn<Pizza, String> ColunaMolho;

    @FXML
    private TableColumn<Salgadinho, String> ColunaNome;

    @FXML
    private TableColumn<Pizza, Double> ColunaPrecoPizza;

    @FXML
    private TableColumn<Salgadinho, Double> ColunaPrecoSalgado;

    @FXML
    private TableColumn<Pizza, String> ColunaRecheioPizza;

    @FXML
    private TableColumn<String, String> ColunaRecheioSalgado;

    @FXML
    private TableColumn<Pizza, String> ColunaSabor;

    @FXML
    private TableColumn<Salgadinho, String> ColunaTipo;

    @FXML
    private ComboBox<String> camboPizzaMolho;

    @FXML
    private ComboBox<String> camboPizzaRecheio;

    @FXML
    private ComboBox<String> comboSalgadoMassa;

    @FXML
    private ComboBox<String> comboSalgadoRecheio;

    @FXML
    private TableView<Pizza> tabelaPizza;

    @FXML
    private TableView<Salgadinho> tabelaSalgado;

    @FXML
    private ToggleGroup tipoSalgado;

    @FXML
    private TextField txtIdPizza;

    @FXML
    private TextField txtIdSalgado;

    @FXML
    private TextField txtMassa;

    @FXML
    private TextField txtMolho;

    @FXML
    private TextField txtNomeCelular;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtNomeMorada;

    @FXML
    private TextField txtPrecoPizza;

    @FXML
    private TextField txtPrecoSalgado;

    @FXML
    private TextField txtPrecoTotal;

    @FXML
    private TextField txtQuantidadePizza;

    @FXML
    private TextField txtQuantidadeSalgado;

    @FXML
    private TextField txtRecheio;

    @FXML
    private TextField txtRecheioPizza;

    @FXML
    private TextField txtSabor;

    private ListaLigadaCircular listaSalgado = new ListaLigadaCircular();

    private ObservableList<Salgadinho> observableListeSalgado;
    private ObservableList<Pizza> observableListePizza;

    private Pizza pizza;
    private Salgadinho salgado;

    @FXML
    private TextField txtNomeSalgado;

    void btnListarSalgados() {
        ListaLigadaCircular listaligada = new ListaLigadaCircular();
        listaligada.exibirSalgado();
        List<Salgadinho> lista = listaligada.getSalgados();
        ColunaMassa.setCellValueFactory(new PropertyValueFactory<>("massa"));
        ColunaPrecoSalgado.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        ColunaRecheioSalgado.setCellValueFactory(new PropertyValueFactory<>("recheio"));
        ColunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        ColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        observableListeSalgado = FXCollections.observableArrayList(lista);
        tabelaSalgado.setItems(observableListeSalgado);
    }

    void btnListarPizzas() {
        ListaLigadaCircular listaligada = new ListaLigadaCircular();
        listaligada.exibirPizza();

        List<Pizza> lista = listaligada.getPizzas();

        ColunaSabor.setCellValueFactory(new PropertyValueFactory<>("sabor"));
        ColunaPrecoPizza.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        ColunaMolho.setCellValueFactory(new PropertyValueFactory<>("molho"));
        ColunaBorda.setCellValueFactory(new PropertyValueFactory<>("borda"));
        ColunaRecheioPizza.setCellValueFactory(new PropertyValueFactory<>("recheio"));

        observableListePizza = FXCollections.observableArrayList(lista);
        tabelaPizza.setItems(observableListePizza);
    }

    void obterLinhaSelecionada(Pizza pizza) {
        Pizza pizzaSelecionada = tabelaPizza.getSelectionModel().getSelectedItem();

        if (pizza != null) {
            preencherCamposPizzaSelecionada(pizzaSelecionada);
        } else {
            System.out.println("Nenhuma linha selecionada.");
        }
    }

    private void preencherCamposPizzaSelecionada(Pizza pizz) {
        pizza = pizz;
        txtIdPizza.setText(String.valueOf(pizz.getId()));
        txtSabor.setText(pizz.getSabor());
        txtPrecoPizza.setText(String.valueOf(pizz.getPrecoUnitario()));
        txtMolho.setText(pizz.getMolho());
        txtRecheioPizza.setText(pizz.getRecheio());
        // Desmarque todos os toggles no ToggleGroup
        BordaPizza.getToggles().forEach(toggle -> {
            if (toggle instanceof RadioButton radioButton) {
                radioButton.setSelected(false);
            }
        });

        // Selecione o Toggle com base no valor da borda da pizza
        BordaPizza.getToggles().forEach(toggle -> {
            if (toggle instanceof RadioButton radioButton) {
                if (radioButton.getText().equals(pizz.getBorda())) {
                    radioButton.setSelected(true);
                }
            }
        });

    }

    void obterLinhaSelecionada(Salgadinho salgado) {
        Salgadinho salgadoSelecionado = tabelaSalgado.getSelectionModel().getSelectedItem();

        if (salgado != null) {
            preencherCamposSalgadoSelecionado(salgadoSelecionado);
        } else {
            System.out.println("Nenhuma linha selecionada.");
        }
    }

    private void preencherCamposSalgadoSelecionado(Salgadinho sal) {
        salgado = sal;
        txtIdSalgado.setText(String.valueOf(sal.getId()));
        //txtTipo.setText(sal.getTipo());
        txtPrecoSalgado.setText(String.valueOf(sal.getPrecoUnitario()));
        txtRecheio.setText(sal.getRecheio());
        txtMassa.setText(sal.getMassa());
        txtNomeSalgado.setText(sal.getNome());
        // Desmarque todos os toggles no ToggleGroup
        tipoSalgado.getToggles().forEach(toggle -> {
            if (toggle instanceof RadioButton radioButton) {
                radioButton.setSelected(false);
            }
        });

        // Selecione o Toggle com base no valor da borda da pizza
        tipoSalgado.getToggles().forEach(toggle -> {
            if (toggle instanceof RadioButton radioButton) {
                if (radioButton.getText().equals(sal.getTipo())) {
                    radioButton.setSelected(true);
                }
            }
        });

    }

    private void atualizarSoma(TextField textField1, TextField textField2, TextField resultField) {
        String text1 = textField1.getText();
        String text2 = textField2.getText();

        if (!text1.isEmpty() && !text2.isEmpty()) {
            try {
                double value1 = Double.parseDouble(text1);
                double value2 = Double.parseDouble(text2);

                double sum = value1 + value2;

                resultField.setText(Double.toString(sum));
            } catch (NumberFormatException ex) {
                //     showAlert("Erro de formato", "Digite números válidos.");
            }
        } else if (!text1.isEmpty() && text2.isEmpty()) {
            double value1 = Double.parseDouble(text1);
            resultField.setText(Double.toString(value1));
        } else if (text1.isEmpty() && !text2.isEmpty()) {
            double value1 = Double.parseDouble(text2);
            resultField.setText(Double.toString(value1));
        } else {
            resultField.clear();
        }

    }

    private final List<String> recheioPizza = new ArrayList<>();
    private final List<String> recheioSalgado = new ArrayList<>();
    private final List<String> MolhoPizza = new ArrayList<>();
    private final List<String> MassaSalgado = new ArrayList<>();

    void carregarRecheioPizza() {
        recheioPizza.add("Calabresa");
        recheioPizza.add("Mussarela");
        recheioPizza.add("Portuguesa");
        recheioPizza.add("Margherita");
        ObservableList<String> obserRecheioPizza = FXCollections.observableArrayList(recheioPizza);
        camboPizzaRecheio.setItems(obserRecheioPizza);
    }

    void carregarMolhoPizza() {
        MolhoPizza.add("Tomate");
        MolhoPizza.add("Bechamel");
        MolhoPizza.add("Pesto");
        MolhoPizza.add("Barbecue");
        ObservableList<String> obserMolhoPizza = FXCollections.observableArrayList(MolhoPizza);
        camboPizzaMolho.setItems(obserMolhoPizza);
    }

    void carregarMassaSalgado() {
        MassaSalgado.add("Folhada");
        MassaSalgado.add("Integral");
        MassaSalgado.add("Tradicional");
        MassaSalgado.add("Sem Glúten");
        ObservableList<String> obserMassaSalgado = FXCollections.observableArrayList(MassaSalgado);
        comboSalgadoMassa.setItems(obserMassaSalgado);
    }

    void carregarRecheioSalgado() {
        recheioSalgado.add("Frango");
        recheioSalgado.add("Carne");
        recheioSalgado.add("Queijo");
        recheioSalgado.add("Presunto");
        ObservableList<String> obserRecheioSalgado = FXCollections.observableArrayList(recheioSalgado);
        comboSalgadoRecheio.setItems(obserRecheioSalgado);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnListarSalgados();
        btnListarPizzas();

        tabelaPizza.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> obterLinhaSelecionada(newValue)
        );
        txtIdPizza.setDisable(true);
        tabelaSalgado.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> obterLinhaSelecionada(newValue)
        );
        txtIdSalgado.setDisable(true);

        txtPrecoSalgado.textProperty().addListener((observable, oldValue, newValue) -> atualizarSoma(txtPrecoPizza, txtPrecoSalgado, txtPrecoTotal));
        txtPrecoPizza.textProperty().addListener((observable, oldValue, newValue) -> atualizarSoma(txtPrecoPizza, txtPrecoSalgado, txtPrecoTotal));

        tabelaSalgado.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo != null) {
                // Chame um método para selecionar automaticamente o Toggle com base no tipo do salgado
                //   selecionarTogglePorTipo(novo.getTipo());
            }
        });
        carregarRecheioSalgado();
        carregarMassaSalgado();
        carregarMolhoPizza();
        carregarRecheioPizza();
        camboPizzaMolho.setOnAction(this::preencherMolhoPizza);
        camboPizzaRecheio.setOnAction(this::preencherRecheioPizza);
    }

    private void preencherRecheioPizza(ActionEvent event) {
        String textoSelecionado = camboPizzaRecheio.getSelectionModel().getSelectedItem();
        if (textoSelecionado != null) {
            txtRecheioPizza.setText(textoSelecionado);
        }
    }

    private void preencherMolhoPizza(ActionEvent event) {
        String textoSelecionado = camboPizzaMolho.getSelectionModel().getSelectedItem();
        if (textoSelecionado != null) {
            txtMolho.setText(textoSelecionado);
        }
    }

    private void resetarFXML(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Tela_Fazer_Pedido.fxml"));
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
    void Fazer_Pedido(ActionEvent event) {
        ListaLigadaCircular venda = new ListaLigadaCircular();
        Venda pedido = new Venda();

        RadioButton pegarBorda = (RadioButton) BordaPizza.getSelectedToggle();
        String borda = pegarBorda.getText();
        RadioButton pegarSalgado = (RadioButton) tipoSalgado.getSelectedToggle();
        String tipo = pegarSalgado.getText();
        salgado.setTipo(tipo);
        pizza.setBorda(borda);
        int idVenda = venda.sizeVenda() + 1;
        pedido.setId(idVenda);
        pedido.setCelular(txtNomeCelular.getText());
        pedido.setCliente(txtNomeCliente.getText());
        pedido.setPizza(pizza);
        pedido.setSalgadinho(salgado);
        pedido.setNome_Pizza(pizza.getSabor());
        pedido.setNome_Salgadinho(salgado.getNome());
        pedido.setQuantidadePizza(Integer.parseInt(txtQuantidadePizza.getText()));
        pedido.setQuantidadeSalgado(Integer.parseInt(txtQuantidadeSalgado.getText()));
        pedido.setMorada(txtNomeMorada.getText());

        Double total = Double.parseDouble(txtPrecoPizza.getText()) * Double.parseDouble(txtQuantidadePizza.getText())
                + Double.parseDouble(txtPrecoSalgado.getText()) * Double.parseDouble(txtQuantidadeSalgado.getText());

        //  pedido.setValorTotal(Double.parseDouble(txtPrecoTotal.getText()));
        pedido.setValorTotal(total);
        pedido.setSituacao("Pendente");

        venda.adicionar(pedido);
        venda.gravarEmArquivo("Venda.txt");

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Pedido Efetuado");
        alert.setHeaderText(null);
        alert.setContentText("Pedido Efectuado com sucesso! Total: " + total);
        alert.showAndWait();

        venda.exibirVenda();
        resetarFXML(event);
    }

}
