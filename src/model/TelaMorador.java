package model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class TelaMorador extends CSS {

    // ========================================================================
    // MÓDULO MORADORES (Navegação Interna)
    // ========================================================================
    
    public StackPane construirModuloMoradores() {
        StackPane containerMoradores = new StackPane();

        VBox menuGrid = criarGridMoradores(containerMoradores);

        containerMoradores.getChildren().add(menuGrid);

        return containerMoradores;
    }

    public VBox criarGridMoradores(StackPane containerPai) {
        VBox layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);


        Label lblTitulo = new Label("MORADORES");
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

        btnCadastrar.setOnAction(e -> trocarSubTela(containerPai, criarTelaCadastroMorador(containerPai)));
        btnListar.setOnAction(e -> trocarSubTela(containerPai, criarTelaListarMoradores(containerPai)));
        btnEditar.setOnAction(e -> trocarSubTela(containerPai, super.criarTelaListarGenerica(containerPai, "Editar Morador", () -> criarGridMoradores(containerPai))));
        btnExcluir.setOnAction(e -> trocarSubTela(containerPai, super.criarTelaBuscaCPFGenerica(containerPai, "Excluir Morador", () -> criarGridMoradores(containerPai))));

        layout.getChildren().addAll(lblTitulo, grid);
        return layout;
    }

    // ========================================================================
    // SUB-TELAS DO MÓDULO MORADORES
    // ========================================================================

    // TELA 1: CADASTRO
    public VBox criarTelaCadastroMorador(StackPane containerPai) {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setMaxWidth(400); // Limita a largura do formulário

        Label lblTitulo = new Label("Novo Cadastro");
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

        javafx.scene.control.TextField txtEndereco = new javafx.scene.control.TextField();
        txtEndereco.setPromptText("Endereço");
        estilizarInput(txtEndereco);

        Button btnSalvar = customizarBotaoMenu("Salvar Morador"); // Reutilizando seu estilo
        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD")); // Muda a cor para o seu "Unselected"

        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridMoradores(containerPai)));

        layout.getChildren().addAll(lblTitulo, txtNome, txtCpf, txtTelefone, txtEndereco, btnSalvar, btnVoltar);
        return layout;
    }

    public VBox criarTelaListarMoradores(StackPane containerPai) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("Lista de Moradores");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        // Placeholder para a Tabela (TableView será montado aqui no futuro)
        Label lblTabela = new Label("Sua tabela JavaFX entrará aqui...");
        lblTabela.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 16px; -fx-text-fill: #7f8c8d;");

        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridMoradores(containerPai)));

        layout.getChildren().addAll(lblTitulo, lblTabela, btnVoltar);
        return layout;
    }

}
