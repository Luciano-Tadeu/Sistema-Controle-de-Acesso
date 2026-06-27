package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class TelaVisitas extends CSS{

    private Controlador controlador;
    private GerenciadorBancoDeDados banco;

    public TelaVisitas(Controlador c, GerenciadorBancoDeDados b){
        this.controlador = c;
        this.banco = b;
    }

    // ========================================================================
    // MÓDULO VISITAS
    // ========================================================================
    public StackPane construirModuloVisitas() {
        StackPane containerVisitas = new StackPane();
        containerVisitas.getChildren().add(criarGridVisitas(containerVisitas));
        return containerVisitas;
    }

    private VBox criarGridVisitas(StackPane containerPai) {
        VBox layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("VISITAS");
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

        btnCadastrar.setOnAction(e -> trocarSubTela(containerPai, criarTelaCadastroVisita(containerPai)));
        
        btnListar.setOnAction(e -> trocarSubTela(containerPai, criarTelaListarVisitas(containerPai)));
        btnEditar.setOnAction(e -> trocarSubTela(containerPai, criarTelaBuscaCPFGenerica(containerPai, "Editar Visitante", () -> criarGridVisitas(containerPai))));
        btnExcluir.setOnAction(e -> trocarSubTela(containerPai, criarTelaExcluirVisita(containerPai)));

        layout.getChildren().addAll(lblTitulo, grid);
        return layout;
    }

    private VBox criarTelaCadastroVisita(StackPane containerPai) {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setMaxWidth(400);

        Label lblTitulo = new Label("Registrar Visita");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        javafx.scene.control.TextField txtNome = new javafx.scene.control.TextField();
        txtNome.setPromptText("Nome");
        estilizarInput(txtNome);

        javafx.scene.control.TextField txtCpf = new javafx.scene.control.TextField();
        txtCpf.setPromptText("CPF");
        estilizarInput(txtCpf);
        aplicarFiltroNumerico(txtCpf, 11);

        javafx.scene.control.TextField txtTelefone = new javafx.scene.control.TextField();
        txtTelefone.setPromptText("Telefone");
        estilizarInput(txtTelefone);
        aplicarFiltroNumerico(txtTelefone, 11);

        javafx.scene.control.TextField txtMoradorAlvo = new javafx.scene.control.TextField();
        txtMoradorAlvo.setPromptText("Endereço Morador Visitado");
        estilizarInput(txtMoradorAlvo);

        Button btnSalvar = customizarBotaoMenu("Salvar"); 
        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD")); 

        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridVisitas(containerPai)));
        btnSalvar.setOnAction(e -> {
                    // =========================
                    // LÓGICA CADASTRO VISITA
                    // =========================
                    Morador moradorVisitado = null; 
                    boolean cadastroValido = false;
                    Visitante novoVisitante;

                    String nome = txtNome.getText().trim();
                    String CPF = txtCpf.getText().trim();
                    String telefone = txtTelefone.getText().trim();
                    String endereco = txtMoradorAlvo.getText().trim();

                    if (nome.isEmpty() || CPF.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                        if(nome.isEmpty()) estilizarInputErro(txtNome);
                        else estilizarInput(txtNome);
                        if(CPF.isEmpty()) estilizarInputErro(txtCpf);
                        else estilizarInput(txtCpf);
                        if(telefone.isEmpty()) estilizarInputErro(txtTelefone);
                        else estilizarInput(txtTelefone);
                        if(endereco.isEmpty()) estilizarInputErro(txtMoradorAlvo);
                        else estilizarInput(txtMoradorAlvo);
                        return; 
                    }

                    String mensagem = "Nome: " + nome + 
                                    "\nCPF: " + CPF + 
                                    "\nTelefone: " + telefone + 
                                    "\nMorador: " + endereco;

                    if (exibirConfirmacao("Confirmar Cadastro?", mensagem)) {
                        for(Morador m : controlador.getMoradores()){
                            if(m.getEnderecoMorador().equalsIgnoreCase(endereco)){
                                moradorVisitado = m;
                                break;
                            }
                        }

                        if(moradorVisitado != null){
                            novoVisitante = new Visitante(nome, CPF, telefone, moradorVisitado);
                            this.controlador.adicionarVisitante(novoVisitante);
                            banco.salvarVisita(novoVisitante);
                            cadastroValido = true; 
                        }
                        else{
                            exibirAlerta("ERRO", "Morador não encontrado.");
                            return;
                        }
                        }
                    else return;

                    if(cadastroValido) {
                        String mensagemFinal = novoVisitante.toString();
                        exibirFinalizacao("Cadastro Finalizado", mensagemFinal);
                        trocarSubTela(containerPai, criarGridVisitas(containerPai));
                    };
        });

        layout.getChildren().addAll(lblTitulo, txtNome, txtCpf, txtTelefone, txtMoradorAlvo, btnSalvar, btnVoltar);
        return layout;
    }

    private VBox criarTelaListarVisitas(StackPane containerPai){
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30)); 

        Label lblTitulo = new Label("Lista de Visitas");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        HBox cabecalho = new HBox(20);
        cabecalho.setAlignment(Pos.CENTER_LEFT);
        cabecalho.setStyle("-fx-border-color: transparent transparent #4A7C59 transparent; -fx-border-width: 0 0 2 0; -fx-padding: 0 0 10 0;");
        
        Label colId = new Label("ID");
        colId.setPrefWidth(80);
        colId.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333; -fx-alignment: center;");
        
        Label colDados = new Label("Dados do Visitante");
        colDados.setPrefWidth(400);
        colDados.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");
        
        Label colData = new Label("Morador / Data Visita");
        colData.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");

        cabecalho.getChildren().addAll(colId, colDados, colData);

        VBox listaConteudo = new VBox(0);

        int contadorSimuladoId = 1;
        for (Visitante v : controlador.getVisitantes()) {
            HBox linhaVisitante = criarLinhaTabelaVisitante(contadorSimuladoId, v);
            listaConteudo.getChildren().add(linhaVisitante);
            contadorSimuladoId++;
        }

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(listaConteudo);
        scrollPane.setFitToWidth(true); // Faz a lista esticar até a borda
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS); // Manda o scroll crescer e empurrar o botão voltar pra baixo

        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridVisitas(containerPai)));

        layout.getChildren().addAll(lblTitulo, cabecalho, scrollPane, btnVoltar);
        return layout;
    }

    private HBox criarLinhaTabelaVisitante(int idBanco, Visitante v) {
        HBox linha = new HBox(20);
        linha.setAlignment(Pos.CENTER_LEFT);
        linha.setStyle("-fx-border-color: transparent transparent #CDCDCD transparent; -fx-border-width: 0 0 1 0; -fx-padding: 15 0 15 0;");

        Label lblId = new Label(String.format("#%03d", idBanco)); // Formata para #001, #002...
        lblId.setPrefWidth(80);
        lblId.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px; -fx-text-fill: #333333; -fx-alignment: center; -fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");

        VBox boxDados = new VBox(5);
        boxDados.setPrefWidth(400);
        boxDados.setStyle("-fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");
        
        Label lblNome = new Label(v.getNome());
        lblNome.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #4A7C59;");
        
        Label lblInfoExtra = new Label("CPF: " + v.getCPF() + " | Tel: " + v.getTel());
        lblInfoExtra.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #666666;");

        boxDados.getChildren().addAll(lblNome, lblInfoExtra);

        VBox boxMoradorV = new VBox(5);
        boxMoradorV.setAlignment(Pos.CENTER_LEFT);

        Morador morador = v.getMoradorVisitado();
        
        Label lblMoradorV = new Label("Morador: " + morador.getNome() + " | Endereço: " + morador.getEnderecoMorador() + "\nData: " + v.getDataVisitaFormatada());
        lblMoradorV.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #333333;");
        boxMoradorV.getChildren().add(lblMoradorV);

        linha.getChildren().addAll(lblId, boxDados, boxMoradorV);
        return linha;
    }

    public VBox criarTelaExcluirVisita(StackPane containerPai) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setMaxWidth(300);

        Label lblTitulo = new Label("Excluir Visitante");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        javafx.scene.control.TextField txtBusca = new javafx.scene.control.TextField();
        txtBusca.setPromptText("CPF");
        estilizarInput(txtBusca);
        aplicarFiltroNumerico(txtBusca, 11);

        Button btnBuscar = customizarBotaoMenu("Buscar");
        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridVisitas(containerPai)));
        btnBuscar.setOnAction(e -> {
            Visitante visitanteBuscado = null;

            for(Visitante v : controlador.getVisitantes()){
                if(v.getCPF().trim().equals(txtBusca.getText())){
                    visitanteBuscado = v;
                    break;
                }
            }

            if(visitanteBuscado == null){
                exibirAlerta("ERRO", "Visitante não encontrado");
            }
            else{
                if(exibirConfirmacao("Excluir?", visitanteBuscado.toString())){
                    banco.removerVisita(visitanteBuscado);
                    controlador.getVisitantes().remove(visitanteBuscado);
                    exibirFinalizacao("Sucesso", "Visitante excluído!");
                    trocarSubTela(containerPai, criarGridVisitas(containerPai));
                    }
                else return;
                }
        });

        layout.getChildren().addAll(lblTitulo, txtBusca, btnBuscar, btnVoltar);
        return layout;
    }
}
