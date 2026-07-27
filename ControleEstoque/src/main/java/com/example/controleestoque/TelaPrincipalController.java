package com.example.controleestoque;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaPrincipalController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNome;

    @FXML
    private ComboBox<String> cmbCategoria;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private TableView<Produto> tbProdutos;

    @FXML
    private TableColumn<Produto, Integer> colCodigo;

    @FXML
    private TableColumn<Produto, String> colNome;

    @FXML
    private TableColumn<Produto, String> colCategoria;

    @FXML
    private TableColumn<Produto, Double> colPreco;

    @FXML
    private TableColumn<Produto, Integer> colQuantidade;

    @FXML
    private TableColumn<Produto, Double> colValorEstoque;

    private ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        tbProdutos.setItems(listaProdutos);

        cmbCategoria.getItems().addAll("Informática", "Periféricos", "Eletrônicos", "Escritório", "Acessórios", "Outros");

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        colValorEstoque.setCellValueFactory(new PropertyValueFactory<>("valorEstoque"));
    }

    public void btnAdicionar(ActionEvent actionEvent) {
    }

    public void btnAtualizar(ActionEvent actionEvent) {
    }

    public void btnExcluir(ActionEvent actionEvent) {
    }

    public void btnPesquisar(ActionEvent actionEvent) {
    }

    public void btnLimpar(ActionEvent actionEvent) {
    }

    public void btnSair(ActionEvent actionEvent) {
        javafx.application.Platform.exit();
    }
}