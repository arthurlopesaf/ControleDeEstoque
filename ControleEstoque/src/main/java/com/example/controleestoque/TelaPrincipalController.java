package com.example.controleestoque;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaPrincipalController {

    private Produto produtoSelecionado;

    private ObservableList<Produto> produtosFiltrados = FXCollections.observableArrayList();

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

        tbProdutos.setOnMouseClicked(event -> {

            produtoSelecionado = tbProdutos.getSelectionModel().getSelectedItem();

            if (produtoSelecionado != null) {

                txtCodigo.setText(String.valueOf(produtoSelecionado.getCodigo()));
                txtNome.setText(produtoSelecionado.getNome());
                cmbCategoria.setValue(produtoSelecionado.getCategoria());
                txtPreco.setText(String.valueOf(produtoSelecionado.getPreco()));
                txtQuantidade.setText(String.valueOf(produtoSelecionado.getQuantidade()));
            }

        });
    }

    public void btnAdicionar(ActionEvent actionEvent) {

        if (!validarCampos(false)) {
            return;
        }

        Produto produto = new Produto(
                Integer.parseInt(txtCodigo.getText()),
                txtNome.getText(),
                cmbCategoria.getValue(),
                Double.parseDouble(txtPreco.getText()),
                Integer.parseInt(txtQuantidade.getText())
        );

        listaProdutos.add(produto);

        limparCampos();
    }

    public void btnAtualizar(ActionEvent actionEvent) {

        if (produtoSelecionado == null) {
            mostrarAlerta("Selecione um produto.");
            return;
        }

        if (!validarCampos(true)) {
            return;
        }

        produtoSelecionado.setCodigo(Integer.parseInt(txtCodigo.getText()));
        produtoSelecionado.setNome(txtNome.getText());
        produtoSelecionado.setCategoria(cmbCategoria.getValue());
        produtoSelecionado.setPreco(Double.parseDouble(txtPreco.getText()));
        produtoSelecionado.setQuantidade(Integer.parseInt(txtQuantidade.getText()));

        tbProdutos.refresh();

        limparCampos();
    }

    public void btnExcluir(ActionEvent actionEvent) {

        if (produtoSelecionado == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Selecione um produto para excluir");
            alert.showAndWait();

            return;
        }

        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setHeaderText("Deseja realmente excluir este produto?");

        if (confirmacao.showAndWait().get() == ButtonType.OK) {

            listaProdutos.remove(produtoSelecionado);

            produtoSelecionado = null;

            tbProdutos.refresh();

            limparCampos();
        }
    }

    public void btnPesquisar(ActionEvent actionEvent) {

        String pesquisa = txtPesquisar.getText().toLowerCase();

        produtosFiltrados.clear();

        for (Produto produto : listaProdutos) {

            if (String.valueOf(produto.getCodigo()).contains(pesquisa)
                    || produto.getNome().toLowerCase().contains(pesquisa)
                    || produto.getCategoria().toLowerCase().contains(pesquisa)) {

                produtosFiltrados.add(produto);
            }
        }

        if (produtosFiltrados.isEmpty()) {
            mostrarAlerta("Nenhum produto encontrado.");
            tbProdutos.setItems(listaProdutos);
        } else {
            tbProdutos.setItems(produtosFiltrados);
        }
    }

    public void btnLimpar(ActionEvent actionEvent) {
        limparCampos();
    }

    public void btnSair(ActionEvent actionEvent) {
        javafx.application.Platform.exit();
    }

    private void mostrarAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void limparCampos() {
        txtCodigo.clear();
        txtNome.clear();
        txtPreco.clear();
        txtQuantidade.clear();

        cmbCategoria.setValue(null);
    }

    private boolean validarCampos(boolean atualizando) {

        if (txtCodigo.getText().isBlank()) {
            mostrarAlerta("O código é obrigatório.");
            return false;
        }

        int codigo;

        try {
            codigo = Integer.parseInt(txtCodigo.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("O código deve conter apenas números.");
            return false;
        }

        if (!atualizando) {
            for (Produto p : listaProdutos) {
                if (p.getCodigo() == codigo) {
                    mostrarAlerta("Já existe um produto com este código.");
                    return false;
                }
            }
        }

        if (txtNome.getText().isBlank()) {
            mostrarAlerta("O nome é obrigatório.");
            return false;
        }

        if (cmbCategoria.getValue() == null) {
            mostrarAlerta("Selecione uma categoria.");
            return false;
        }

        double preco;

        if (txtPreco.getText().isBlank()) {
            mostrarAlerta("O preço é obrigatório.");
            return false;
        }

        try {
            preco = Double.parseDouble(txtPreco.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Preço inválido.");
            return false;
        }

        if (preco <= 0) {
            mostrarAlerta("O preço deve ser maior que zero.");
            return false;
        }

        int quantidade;

        if (txtQuantidade.getText().isBlank()) {
            mostrarAlerta("A quantidade é obrigatória.");
            return false;
        }

        try {
            quantidade = Integer.parseInt(txtQuantidade.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Quantidade inválida.");
            return false;
        }

        if (quantidade < 0) {
            mostrarAlerta("A quantidade não pode ser negativa.");
            return false;
        }

        return true;
    }
}