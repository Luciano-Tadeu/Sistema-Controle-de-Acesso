package model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class TelaFuncionarios extends CSS{
    // ========================================================================
    // MÓDULO FUNCIONÁRIOS
    // ========================================================================
    public StackPane construirModuloFuncionarios() {
        StackPane containerFunc = new StackPane();
        containerFunc.getChildren().add(criarGridFuncionarios(containerFunc));
        return containerFunc;
    }

    private VBox criarGridFuncionarios(StackPane containerPai) {
        VBox layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("FUNCIONÁRIOS");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 32px; -fx-text-fill: #4A7C59; -fx-font-weight: bold; -fx-border-color: transparent transparent #4A7C59 transparent; -fx-border-width: 0 0 2 0;");

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(25);
        grid.setVgap(25);

        String iconeCadastro = "/images/iconCadastro.png";
        String iconeListar = "/images/iconListar.png";
        String iconeEditar = "/images/iconEditar.png";
        String iconeExcluir = "/images/iconExcluir.png";

        Button btnCadastrar = criarBotaoGridLtop("Cadastro", iconeCadastro);
        Button btnListar = criarBotaoGridRtop("Listar", iconeListar);
        Button btnEditar = criarBotaoGridLdown("Editar", iconeEditar);
        Button btnExcluir = criarBotaoGridRdown("Excluir", iconeExcluir);

        grid.add(btnCadastrar, 0, 0);
        grid.add(btnListar, 1, 0);
        grid.add(btnEditar, 0, 1);
        grid.add(btnExcluir, 1, 1);

        btnCadastrar.setOnAction(e -> trocarSubTela(containerPai, criarTelaCadastroFuncionario(containerPai)));
        btnListar.setOnAction(e -> trocarSubTela(containerPai, criarTelaListarGenerica(containerPai, "Lista de Funcionários", () -> criarGridFuncionarios(containerPai))));
        btnEditar.setOnAction(e -> trocarSubTela(containerPai, criarTelaBuscaCPFGenerica(containerPai, "Editar Funcionário", () -> criarGridFuncionarios(containerPai))));
        btnExcluir.setOnAction(e -> trocarSubTela(containerPai, criarTelaBuscaCPFGenerica(containerPai, "Excluir Funcionário", () -> criarGridFuncionarios(containerPai))));

        layout.getChildren().addAll(lblTitulo, grid);
        return layout;
    }

    private VBox criarTelaCadastroFuncionario(StackPane containerPai) {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setMaxWidth(400);

        Label lblTitulo = new Label("Novo Funcionário");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        javafx.scene.control.TextField txtNome = new javafx.scene.control.TextField();
        txtNome.setPromptText("Nome Completo");
        estilizarInput(txtNome);

        javafx.scene.control.TextField txtCpf = new javafx.scene.control.TextField();
        txtCpf.setPromptText("CPF");
        estilizarInput(txtCpf);

        javafx.scene.control.TextField txtTelefone = new javafx.scene.control.TextField();
        txtTelefone.setPromptText("Telefone");
        estilizarInput(txtTelefone);

        javafx.scene.control.TextField txtFuncao = new javafx.scene.control.TextField();
        txtFuncao.setPromptText("Função");
        estilizarInput(txtFuncao);
        
        Button btnSalvar = customizarBotaoMenu("Salvar"); 
        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD")); 

        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridFuncionarios(containerPai)));

        layout.getChildren().addAll(lblTitulo, txtNome, txtCpf, txtTelefone, txtFuncao, btnSalvar, btnVoltar);
        return layout;
    }

}
