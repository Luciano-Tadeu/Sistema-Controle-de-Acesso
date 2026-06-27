package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class TelaFuncionarios extends CSS{

    private Controlador controlador;
    private GerenciadorBancoDeDados banco;

    public TelaFuncionarios(Controlador c, GerenciadorBancoDeDados b){
        this.controlador = c;
        this.banco = b;
    }

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
        btnListar.setOnAction(e -> trocarSubTela(containerPai, criarTelaListarFuncionarios(containerPai)));
        btnEditar.setOnAction(e -> trocarSubTela(containerPai, criarTelaBuscaCPFGenerica(containerPai, "Editar Funcionário", () -> criarGridFuncionarios(containerPai))));
        btnExcluir.setOnAction(e -> trocarSubTela(containerPai, criarTelaExcluirFuncionario(containerPai)));

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
        aplicarFiltroNumerico(txtCpf, 11);

        javafx.scene.control.TextField txtTelefone = new javafx.scene.control.TextField();
        txtTelefone.setPromptText("Telefone");
        estilizarInput(txtTelefone);
        aplicarFiltroNumerico(txtTelefone, 11);

        javafx.scene.control.TextField txtFuncao = new javafx.scene.control.TextField();
        txtFuncao.setPromptText("Função");
        estilizarInput(txtFuncao);
        
        Button btnSalvar = customizarBotaoMenu("Salvar"); 
        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD")); 

        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridFuncionarios(containerPai)));
        btnSalvar.setOnAction(e -> {
                    // =========================
                    // LÓGICA CADASTRO FUNCIONÁRIO
                    // ========================= 
                    boolean cadastroValido = false;
                    Funcionario novoFuncionario;

                    String nome = txtNome.getText().trim();
                    String CPF = txtCpf.getText().trim();
                    String telefone = txtTelefone.getText().trim();
                    String funcao = txtFuncao.getText().trim();

                    if (nome.isEmpty() || CPF.isEmpty() || telefone.isEmpty() || funcao.isEmpty()) {
                        if(nome.isEmpty()) estilizarInputErro(txtNome);
                        else estilizarInput(txtNome);
                        if(CPF.isEmpty()) estilizarInputErro(txtCpf);
                        else estilizarInput(txtCpf);
                        if(telefone.isEmpty()) estilizarInputErro(txtTelefone);
                        else estilizarInput(txtTelefone);
                        if(funcao.isEmpty()) estilizarInputErro(txtFuncao);
                        else estilizarInput(txtFuncao);
                        return; 
                    }

                    String mensagem = "Nome: " + nome + 
                                    "\nCPF: " + CPF + 
                                    "\nTelefone: " + telefone + 
                                    "\nFunção: " + funcao;

                    if (exibirConfirmacao("Confirmar Cadastro?", mensagem)) {
                            novoFuncionario = new Funcionario(nome, CPF, telefone, funcao);
                            this.controlador.adicionarFuncionario(novoFuncionario);
                            banco.salvarFuncionario(novoFuncionario);
                            cadastroValido = true; 
                        }
                    else return;

                    if(cadastroValido) {
                        String mensagemFinal = novoFuncionario.toString();
                        exibirFinalizacao("Cadastro Finalizado", mensagemFinal);
                        trocarSubTela(containerPai, criarGridFuncionarios(containerPai));
                    };
        });

        layout.getChildren().addAll(lblTitulo, txtNome, txtCpf, txtTelefone, txtFuncao, btnSalvar, btnVoltar);
        return layout;
    }

        private VBox criarTelaListarFuncionarios(StackPane containerPai){
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30)); 

        Label lblTitulo = new Label("Lista de Funcionários");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        HBox cabecalho = new HBox(20);
        cabecalho.setAlignment(Pos.CENTER_LEFT);
        cabecalho.setStyle("-fx-border-color: transparent transparent #4A7C59 transparent; -fx-border-width: 0 0 2 0; -fx-padding: 0 0 10 0;");
        
        Label colId = new Label("ID");
        colId.setPrefWidth(80);
        colId.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333; -fx-alignment: center;");
        
        Label colDados = new Label("Dados do Funcionário");
        colDados.setPrefWidth(400);
        colDados.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");
        
        Label colFuncao = new Label("Função");
        colFuncao.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");

        cabecalho.getChildren().addAll(colId, colDados, colFuncao);

        VBox listaConteudo = new VBox(0);

        int contadorSimuladoId = 1;
        for (Funcionario f : controlador.getFuncionarios()) {
            HBox linhaFuncionario = criarLinhaTabelaFuncionarios(contadorSimuladoId, f);
            listaConteudo.getChildren().add(linhaFuncionario);
            contadorSimuladoId++;
        }

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(listaConteudo);
        scrollPane.setFitToWidth(true); // Faz a lista esticar até a borda
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS); // Manda o scroll crescer e empurrar o botão voltar pra baixo

        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridFuncionarios(containerPai)));

        layout.getChildren().addAll(lblTitulo, cabecalho, scrollPane, btnVoltar);
        return layout;
    }

    private HBox criarLinhaTabelaFuncionarios(int idBanco, Funcionario f) {
        HBox linha = new HBox(20);
        linha.setAlignment(Pos.CENTER_LEFT);
        linha.setStyle("-fx-border-color: transparent transparent #CDCDCD transparent; -fx-border-width: 0 0 1 0; -fx-padding: 15 0 15 0;");

        Label lblId = new Label(String.format("#%03d", idBanco)); // Formata para #001, #002...
        lblId.setPrefWidth(80);
        lblId.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px; -fx-text-fill: #333333; -fx-alignment: center; -fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");

        VBox boxDados = new VBox(5);
        boxDados.setPrefWidth(400);
        boxDados.setStyle("-fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");
        
        Label lblNome = new Label(f.getNome());
        lblNome.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #4A7C59;");
        
        Label lblInfoExtra = new Label("CPF: " + f.getCPF() + " | Tel: " + f.getTel());
        lblInfoExtra.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #666666;");

        boxDados.getChildren().addAll(lblNome, lblInfoExtra);

        VBox boxFuncionario = new VBox(5);
        boxFuncionario.setAlignment(Pos.CENTER_LEFT);
        
        Label lblFuncao = new Label(f.getFuncao());
        lblFuncao.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 22px; -fx-text-fill: #333333;");
        boxFuncionario.getChildren().add(lblFuncao);

        linha.getChildren().addAll(lblId, boxDados, boxFuncionario);
        return linha;
    }

    public VBox criarTelaExcluirFuncionario(StackPane containerPai) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setMaxWidth(300);

        Label lblTitulo = new Label("Excluir Funcionário");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        javafx.scene.control.TextField txtBusca = new javafx.scene.control.TextField();
        txtBusca.setPromptText("CPF");
        estilizarInput(txtBusca);
        aplicarFiltroNumerico(txtBusca, 11);

        Button btnBuscar = customizarBotaoMenu("Buscar");
        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridFuncionarios(containerPai)));
        btnBuscar.setOnAction(e -> {
            Funcionario funcionarioBuscado = null;

            for(Funcionario f : controlador.getFuncionarios()){
                if(f.getCPF().trim().equals(txtBusca.getText())){
                    funcionarioBuscado = f;
                    break;
                }
            }

            if(funcionarioBuscado == null){
                exibirAlerta("ERRO", "Funcionário não encontrado");
            }
            else{
                if(exibirConfirmacao("Excluir?", funcionarioBuscado.toString())){
                    banco.removerFuncionario(funcionarioBuscado);
                    controlador.getFuncionarios().remove(funcionarioBuscado);
                    exibirFinalizacao("Sucesso", "Funcionário excluído!");
                    trocarSubTela(containerPai, criarGridFuncionarios(containerPai));
                    }
                else return;
                }
        });

        layout.getChildren().addAll(lblTitulo, txtBusca, btnBuscar, btnVoltar);
        return layout;
    }

}
