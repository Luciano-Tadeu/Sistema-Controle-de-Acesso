package model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TelaServicos extends CSS{
    // ========================================================================
    // MÓDULO SERVIÇOS
    // ========================================================================
    public StackPane construirModuloServicos() {
        StackPane containerFunc = new StackPane();
        containerFunc.getChildren().add(criarGridServicos(containerFunc));
        return containerFunc;
    }

    private VBox criarGridServicos(StackPane containerPai) {
        VBox layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("SERVIÇOS");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 32px; -fx-text-fill: #4A7C59; -fx-font-weight: bold; -fx-border-color: transparent transparent #4A7C59 transparent; -fx-border-width: 0 0 2 0;");

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(25);
        grid.setVgap(25);

        String iconeCadastro = "/images/iconCadastro.png";
        String iconeListar = "/images/iconListar.png";
        String iconeEditar = "/images/iconEditar.png";
        String iconeExcluir = "/images/iconExcluir.png";
        String iconeEntrar = "/images/iconEntrar.png";
        String iconeSair = "/images/iconSair.png";

        Button btnCadastrar = criarBotaoGridLtop("Cadastro", iconeCadastro);
        Button btnListar = criarBotaoGridRtop("Listar", iconeListar);
        Button btnEditar = criarBotaoGridLdown("Editar", iconeEditar);
        Button btnExcluir = criarBotaoGridRdown("Excluir", iconeExcluir);
        Button btnEntrar = criarBotaoGridMid("Liberar Entrada", iconeEntrar);
        Button btnSair = criarBotaoGridMid("Liberar Saída", iconeSair);

        grid.add(btnCadastrar, 0, 0);
        grid.add(btnListar, 2, 0);
        grid.add(btnEntrar, 1, 0);
        grid.add(btnEditar, 0, 1);
        grid.add(btnSair, 1, 1);
        grid.add(btnExcluir, 2, 1);

        btnCadastrar.setOnAction(e -> trocarSubTela(containerPai, criarTelaCadastroServicos(containerPai)));
        btnListar.setOnAction(e -> trocarSubTela(containerPai, criarTelaListarGenerica(containerPai, "Lista de Serviços", () -> criarGridServicos(containerPai))));
        btnEditar.setOnAction(e -> trocarSubTela(containerPai, criarTelaBuscaCPFGenerica(containerPai, "Editar Serviços", () -> criarGridServicos(containerPai))));
        btnExcluir.setOnAction(e -> trocarSubTela(containerPai, criarTelaBuscaCPFGenerica(containerPai, "Excluir Serviços", () -> criarGridServicos(containerPai))));

        layout.getChildren().addAll(lblTitulo, grid);
        return layout;
    }

    private VBox criarTelaCadastroServicos(StackPane containerPai) {
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

        javafx.scene.control.TextField txtCNH = new javafx.scene.control.TextField();
        txtCNH.setPromptText("CNH");
        estilizarInput(txtCNH);

        javafx.scene.control.TextField txtTipoServico = new javafx.scene.control.TextField();
        txtTipoServico.setPromptText("Tipo de Serviço");
        estilizarInput(txtTipoServico);

        javafx.scene.control.TextField txtEndereco = new javafx.scene.control.TextField();
        txtEndereco.setPromptText("Endereço Morador");
        estilizarInput(txtEndereco);

        Button btnSalvar = customizarBotaoMenu("Salvar"); 
        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD")); 

        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridServicos(containerPai)));

        layout.getChildren().addAll(lblTitulo, txtNome, txtCpf, txtTelefone, txtCNH, txtTipoServico, txtEndereco, btnSalvar, btnVoltar);
        return layout;
    }
}
