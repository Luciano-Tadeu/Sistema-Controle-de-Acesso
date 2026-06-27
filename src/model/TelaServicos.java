package model;

import java.time.LocalDateTime;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TelaServicos extends CSS{

    private Controlador controlador;
    private GerenciadorBancoDeDados banco;

    public TelaServicos(Controlador c, GerenciadorBancoDeDados b){
        this.controlador = c;
        this.banco = b;
    }

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
        btnListar.setOnAction(e -> trocarSubTela(containerPai, criarTelaListarServicos(containerPai)));
        btnEditar.setOnAction(e -> trocarSubTela(containerPai, criarTelaBuscaCPFGenerica(containerPai, "Editar Serviços", () -> criarGridServicos(containerPai))));
        btnExcluir.setOnAction(e -> trocarSubTela(containerPai, criarTelaExcluirServico(containerPai)));
        btnEntrar.setOnAction(e -> trocarSubTela(containerPai, criarTelaListarEntrada(containerPai)));
        btnSair.setOnAction(e -> trocarSubTela(containerPai, criarTelaListarSaida(containerPai)));

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
        aplicarFiltroNumerico(txtCpf, 11);

        javafx.scene.control.TextField txtTelefone = new javafx.scene.control.TextField();
        txtTelefone.setPromptText("Telefone");
        estilizarInput(txtTelefone);
        aplicarFiltroNumerico(txtTelefone, 11);

        javafx.scene.control.TextField txtCNH = new javafx.scene.control.TextField();
        txtCNH.setPromptText("CNH");
        estilizarInput(txtCNH);
        aplicarFiltroNumerico(txtCNH, 11);

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
        btnSalvar.setOnAction(e -> {
                    // =========================
                    // LÓGICA CADASTRO FUNCIONÁRIO
                    // ========================= 
                    boolean cadastroValido = false;
                    PrestadorServico novoPrestador;
                    Morador moradorServico = null;

                    String nome = txtNome.getText().trim();
                    String CPF = txtCpf.getText().trim();
                    String telefone = txtTelefone.getText().trim();
                    String cnh = txtCNH.getText().trim();
                    String tiposervico = txtTipoServico.getText().trim();
                    String endereco = txtEndereco.getText().trim();

                    if (nome.isEmpty() || CPF.isEmpty() || telefone.isEmpty() || cnh.isEmpty() || tiposervico.isEmpty() || endereco.isEmpty()) {
                        if(nome.isEmpty()) estilizarInputErro(txtNome);
                        else estilizarInput(txtNome);
                        if(CPF.isEmpty()) estilizarInputErro(txtCpf);
                        else estilizarInput(txtCpf);
                        if(telefone.isEmpty()) estilizarInputErro(txtTelefone);
                        else estilizarInput(txtTelefone);
                        if(cnh.isEmpty()) estilizarInputErro(txtCNH);
                        else estilizarInput(txtCNH);
                        if(tiposervico.isEmpty()) estilizarInputErro(txtTipoServico);
                        else estilizarInput(txtTipoServico);
                        if(endereco.isEmpty()) estilizarInputErro(txtEndereco);
                        else estilizarInput(txtEndereco);
                        return; 
                    }

                    String mensagem = "Nome: " + nome + 
                                    "\nCPF: " + CPF + 
                                    "\nTelefone: " + telefone + 
                                    "\nCNH: " + cnh +
                                    "\nTipo de Serviço: " + tiposervico +
                                    "\nMorador (endereço): " + endereco;

                    if (exibirConfirmacao("Confirmar Cadastro?", mensagem)) {
                            for(Morador m : controlador.getMoradores()){
                                if(m.getEnderecoMorador().trim().equals(endereco)){
                                    moradorServico = m;
                                    break;
                                }
                            }

                            if(moradorServico != null){
                                novoPrestador = new PrestadorServico(nome, CPF, telefone, cnh, tiposervico, moradorServico);
                                this.controlador.adicionarPrestador(novoPrestador);
                                banco.salvarPrestador(novoPrestador);
                                cadastroValido = true; 
                            }
                            else{
                                exibirAlerta("ERRO", "Morador não encontrado.");
                                return;
                            }
                            
                        }
                    else return;

                    if(cadastroValido) {
                        String mensagemFinal = novoPrestador.toString();
                        exibirFinalizacao("Cadastro Finalizado", mensagemFinal);
                        trocarSubTela(containerPai, criarGridServicos(containerPai));
                    };
        });

        layout.getChildren().addAll(lblTitulo, txtNome, txtCpf, txtTelefone, txtCNH, txtTipoServico, txtEndereco, btnSalvar, btnVoltar);
        return layout;
    }

        private VBox criarTelaListarServicos(StackPane containerPai){
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30)); 

        Label lblTitulo = new Label("Lista de Serviços");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        HBox cabecalho = new HBox(20);
        cabecalho.setAlignment(Pos.CENTER_LEFT);
        cabecalho.setStyle("-fx-border-color: transparent transparent #4A7C59 transparent; -fx-border-width: 0 0 2 0; -fx-padding: 0 0 10 0;");
        
        Label colId = new Label("ID");
        colId.setPrefWidth(80);
        colId.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333; -fx-alignment: center;");
        
        Label colDados = new Label("Dados do Prestador");
        colDados.setPrefWidth(400);
        colDados.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");
        
        Label colData = new Label("Morador / Data Serviço");
        colData.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");

        cabecalho.getChildren().addAll(colId, colDados, colData);

        VBox listaConteudo = new VBox(0);

        int contadorSimuladoId = 1;
        for (PrestadorServico p : controlador.getPrestadores()) {
            HBox linhaPrestador = criarLinhaTabelaServico(contadorSimuladoId, p);
            listaConteudo.getChildren().add(linhaPrestador);
            contadorSimuladoId++;
        }

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(listaConteudo);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridServicos(containerPai)));

        layout.getChildren().addAll(lblTitulo, cabecalho, scrollPane, btnVoltar);
        return layout;
    }

    private HBox criarLinhaTabelaServico(int idBanco, PrestadorServico p) {
        HBox linha = new HBox(20);
        linha.setAlignment(Pos.CENTER_LEFT);
        linha.setStyle("-fx-border-color: transparent transparent #CDCDCD transparent; -fx-border-width: 0 0 1 0; -fx-padding: 15 0 15 0;");

        Label lblId = new Label(String.format("#%03d", idBanco));
        lblId.setPrefWidth(80);
        lblId.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px; -fx-text-fill: #333333; -fx-alignment: center; -fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");

        VBox boxDados = new VBox(5);
        boxDados.setPrefWidth(400);
        boxDados.setStyle("-fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");
        
        Label lblNome = new Label(p.getNome());
        lblNome.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #4A7C59;");
        
        Label lblInfoExtra = new Label("CPF: " + p.getCPF() + " | Tel: " + p.getTel() + " | CNH: " + p.getCnh());
        lblInfoExtra.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #666666;");

        boxDados.getChildren().addAll(lblNome, lblInfoExtra);

        VBox boxMoradorV = new VBox(5);
        boxMoradorV.setAlignment(Pos.CENTER_LEFT);

        Morador morador = p.getMorador();
        
        Label lblMoradorP = new Label("Morador: " + morador.getNome() + " | Endereço: " + morador.getEnderecoMorador() + "\nData: " + p.getHoraFormatada());
        lblMoradorP.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #333333;");
        boxMoradorV.getChildren().add(lblMoradorP);

        linha.getChildren().addAll(lblId, boxDados, boxMoradorV);
        return linha;
    }

    public VBox criarTelaExcluirServico(StackPane containerPai) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setMaxWidth(300);

        Label lblTitulo = new Label("Excluir Serviço");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        javafx.scene.control.TextField txtBusca = new javafx.scene.control.TextField();
        txtBusca.setPromptText("CPF");
        estilizarInput(txtBusca);
        aplicarFiltroNumerico(txtBusca, 11);

        Button btnBuscar = customizarBotaoMenu("Buscar");
        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridServicos(containerPai)));
        btnBuscar.setOnAction(e -> {
            PrestadorServico prestadorBuscado = null;

            for(PrestadorServico p : controlador.getPrestadores()){
                if(p.getCPF().trim().equals(txtBusca.getText())){
                    prestadorBuscado = p;
                    break;
                }
            }

            if(prestadorBuscado == null){
                exibirAlerta("ERRO", "Prestador não encontrado");
            }
            else{
                if(exibirConfirmacao("Excluir?", prestadorBuscado.toString())){
                    banco.removerPrestador(prestadorBuscado);
                    controlador.getPrestadores().remove(prestadorBuscado);
                    exibirFinalizacao("Sucesso", "Prestador excluído!");
                    trocarSubTela(containerPai, criarGridServicos(containerPai));
                    }
                else return;
                }
        });

        layout.getChildren().addAll(lblTitulo, txtBusca, btnBuscar, btnVoltar);
        return layout;
    }

    private VBox criarTelaListarEntrada(StackPane containerPai){
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30)); 

        Label lblTitulo = new Label("Liberar Entrada");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        HBox cabecalho = new HBox(20);
        cabecalho.setAlignment(Pos.CENTER_LEFT);
        cabecalho.setStyle("-fx-border-color: transparent transparent #4A7C59 transparent; -fx-border-width: 0 0 2 0; -fx-padding: 0 0 10 0;");
        
        Label colId = new Label("ID");
        colId.setPrefWidth(80);
        colId.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333; -fx-alignment: center;");
        
        Label colDados = new Label("Dados do Prestador");
        colDados.setPrefWidth(400);
        colDados.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");
        
        Label colData = new Label("Morador / Data Serviço");
        colData.setPrefWidth(290);
        colData.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");

        Label colButton = new Label("Ação");
        colButton.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");

        cabecalho.getChildren().addAll(colId, colDados, colData, colButton);

        VBox listaConteudo = new VBox(0);

        int contadorSimuladoId = 1;
        for (PrestadorServico p : controlador.getPrestadores()) {
            if(p.getHoraEntrada() == null){
                HBox linhaPrestador = criarLinhaTabelaEntrada(contadorSimuladoId, p, containerPai);
                listaConteudo.getChildren().add(linhaPrestador);
                contadorSimuladoId++;
            }
        } 

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(listaConteudo);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridServicos(containerPai)));

        layout.getChildren().addAll(lblTitulo, cabecalho, scrollPane, btnVoltar);
        return layout;
    }

    private HBox criarLinhaTabelaEntrada(int idBanco, PrestadorServico p, StackPane containerPai) {
        HBox linha = new HBox(20);
        linha.setAlignment(Pos.CENTER_LEFT);
        linha.setStyle("-fx-border-color: transparent transparent #CDCDCD transparent; -fx-border-width: 0 0 1 0; -fx-padding: 15 0 15 0;");

        Label lblId = new Label(String.format("#%03d", idBanco));
        lblId.setPrefWidth(80);
        lblId.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px; -fx-text-fill: #333333; -fx-alignment: center; -fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");

        VBox boxDados = new VBox(5);
        boxDados.setPrefWidth(400);
        boxDados.setStyle("-fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");
        
        Label lblNome = new Label(p.getNome());
        lblNome.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #4A7C59;");
        
        Label lblInfoExtra = new Label("CPF: " + p.getCPF() + " | Tel: " + p.getTel() + " | CNH: " + p.getCnh());
        lblInfoExtra.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #666666;");

        boxDados.getChildren().addAll(lblNome, lblInfoExtra);

        VBox boxMoradorV = new VBox(5);
        boxMoradorV.setAlignment(Pos.CENTER_LEFT);

        Morador morador = p.getMorador();
        
        Label lblMoradorP = new Label("Morador: " + morador.getNome() + " | Endereço: " + morador.getEnderecoMorador() + "\nData: " + p.getHoraFormatada());
        lblMoradorP.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #333333;");
        boxMoradorV.getChildren().add(lblMoradorP);

        Button btnLiberar = customizarBotaoTabela("Liberar");
        btnLiberar.setStyle(btnLiberar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnLiberar.setOnAction(e -> {
            p.setHoraEntrada(LocalDateTime.now());
            banco.salvarEntradaServico(p);
            exibirFinalizacao("Sucesso", "Prestador autorizado a entrar!");
            trocarSubTela(containerPai, criarGridServicos(containerPai));
        });

        linha.getChildren().addAll(lblId, boxDados, boxMoradorV, btnLiberar);
        return linha;
    }

    private VBox criarTelaListarSaida(StackPane containerPai){
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30)); 

        Label lblTitulo = new Label("Liberar Entrada");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        HBox cabecalho = new HBox(20);
        cabecalho.setAlignment(Pos.CENTER_LEFT);
        cabecalho.setStyle("-fx-border-color: transparent transparent #4A7C59 transparent; -fx-border-width: 0 0 2 0; -fx-padding: 0 0 10 0;");
        
        Label colId = new Label("ID");
        colId.setPrefWidth(80);
        colId.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333; -fx-alignment: center;");
        
        Label colDados = new Label("Dados do Prestador");
        colDados.setPrefWidth(400);
        colDados.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");
        
        Label colData = new Label("Morador / Data Serviço");
        colData.setPrefWidth(290);
        colData.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");

        Label colButton = new Label("Ação");
        colButton.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");

        cabecalho.getChildren().addAll(colId, colDados, colData, colButton);

        VBox listaConteudo = new VBox(0);

        int contadorSimuladoId = 1;
        for (PrestadorServico p : controlador.getPrestadores()) {
            if(p.getHoraSaida() == null && p.getHoraEntrada() != null){
                HBox linhaPrestador = criarLinhaTabelaSaida(contadorSimuladoId, p, containerPai);
                listaConteudo.getChildren().add(linhaPrestador);
                contadorSimuladoId++;
            }
        } 

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(listaConteudo);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridServicos(containerPai)));

        layout.getChildren().addAll(lblTitulo, cabecalho, scrollPane, btnVoltar);
        return layout;
    }

    private HBox criarLinhaTabelaSaida(int idBanco, PrestadorServico p, StackPane containerPai) {
        HBox linha = new HBox(20);
        linha.setAlignment(Pos.CENTER_LEFT);
        linha.setStyle("-fx-border-color: transparent transparent #CDCDCD transparent; -fx-border-width: 0 0 1 0; -fx-padding: 15 0 15 0;");

        Label lblId = new Label(String.format("#%03d", idBanco));
        lblId.setPrefWidth(80);
        lblId.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px; -fx-text-fill: #333333; -fx-alignment: center; -fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");

        VBox boxDados = new VBox(5);
        boxDados.setPrefWidth(400);
        boxDados.setStyle("-fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");
        
        Label lblNome = new Label(p.getNome());
        lblNome.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #4A7C59;");
        
        Label lblInfoExtra = new Label("CPF: " + p.getCPF() + " | Tel: " + p.getTel() + " | CNH: " + p.getCnh());
        lblInfoExtra.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #666666;");

        boxDados.getChildren().addAll(lblNome, lblInfoExtra);

        VBox boxMoradorV = new VBox(5);
        boxMoradorV.setAlignment(Pos.CENTER_LEFT);

        Morador morador = p.getMorador();
        
        Label lblMoradorP = new Label("Morador: " + morador.getNome() + " | Endereço: " + morador.getEnderecoMorador() + "\nData: " + p.getHoraFormatada());
        lblMoradorP.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #333333;");
        boxMoradorV.getChildren().add(lblMoradorP);

        Button btnLiberar = customizarBotaoTabela("Liberar");
        btnLiberar.setStyle(btnLiberar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnLiberar.setOnAction(e -> {
            p.setHoraSaida(LocalDateTime.now());
            banco.salvarSaidaServico(p);
            exibirFinalizacao("Sucesso", "Prestador autorizado a sair!");
            trocarSubTela(containerPai, criarGridServicos(containerPai));
        });

        linha.getChildren().addAll(lblId, boxDados, boxMoradorV, btnLiberar);
        return linha;
    }

}
