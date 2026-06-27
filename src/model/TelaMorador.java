package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class TelaMorador extends CSS {

    private Controlador controlador;
    private GerenciadorBancoDeDados banco;

    public TelaMorador(Controlador c, GerenciadorBancoDeDados b){
        this.controlador = c;
        this.banco = b;
    }

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
        btnExcluir.setOnAction(e -> trocarSubTela(containerPai, criarTelaExcluirMorador(containerPai)));

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
        aplicarFiltroNumerico(txtCpf, 11);

        javafx.scene.control.TextField txtTelefone = new javafx.scene.control.TextField();
        txtTelefone.setPromptText("Telefone");
        estilizarInput(txtTelefone);
        aplicarFiltroNumerico(txtTelefone, 11);

        javafx.scene.control.TextField txtEndereco = new javafx.scene.control.TextField();
        txtEndereco.setPromptText("Endereço");
        estilizarInput(txtEndereco);

        Button btnSalvar = customizarBotaoMenu("Salvar"); // Reutilizando seu estilo
        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD")); // Muda a cor para o seu "Unselected"

        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridMoradores(containerPai)));
        btnSalvar.setOnAction(e -> {
                    // =========================
                    // LÓGICA CADASTRO MORADOR
                    // =========================
                    Morador novoMorador = null; 
                    boolean cadastroValido = false;

                    String nome = txtNome.getText().trim();
                    String CPF = txtCpf.getText().trim();
                    String telefone = txtTelefone.getText().trim();
                    String endereco = txtEndereco.getText().trim();

                    if (nome.isEmpty() || CPF.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                        if(nome.isEmpty()) estilizarInputErro(txtNome);
                        else estilizarInput(txtNome);
                        if(CPF.isEmpty()) estilizarInputErro(txtCpf);
                        else estilizarInput(txtCpf);
                        if(telefone.isEmpty()) estilizarInputErro(txtTelefone);
                        else estilizarInput(txtTelefone);
                        if(endereco.isEmpty()) estilizarInputErro(txtEndereco);
                        else estilizarInput(txtEndereco);
                        return; 
                    }

                    String mensagem = "Nome: " + nome + 
                                    "\nCPF: " + CPF + 
                                    "\nTelefone: " + telefone + 
                                    "\nEndereço: " + endereco;

                    if (exibirConfirmacao("Confirmar Cadastro?", mensagem)) {
                            novoMorador = new Morador(nome, CPF, telefone, endereco);
                            novoMorador.setCredencial(new Credencial());
                            this.controlador.adicionarMorador(novoMorador);
                            cadastroValido = true; 
                        }
                    else return;

                    if(cadastroValido) {
                        trocarSubTela(containerPai, criarTelaQuantidadeVeiculos(containerPai, novoMorador));
                    };
        });

        layout.getChildren().addAll(lblTitulo, txtNome, txtCpf, txtTelefone, txtEndereco, btnSalvar, btnVoltar);
        return layout;
    }

    private VBox criarTelaQuantidadeVeiculos(StackPane containerPai, model.Morador morador) {
        VBox layout = new VBox(25);
        layout.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("Veículos de " + morador.getNome());
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        Label lblInstrucao = new Label("Quantos veículos este morador possui?");
        lblInstrucao.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 16px;");

        HBox boxBotoes = new HBox(15);
        boxBotoes.setAlignment(Pos.CENTER);

        Button btnZero = customizarBotaoMenu("0");
        Button btnUm = customizarBotaoMenu("1");
        Button btnDois = customizarBotaoMenu("2");

        btnZero.setOnAction(e -> finalizarCadastroCompleto(containerPai, morador));
        
        btnUm.setOnAction(e -> trocarSubTela(containerPai, criarTelaCadastroVeiculo(containerPai, morador, 1, 1)));
        btnDois.setOnAction(e -> trocarSubTela(containerPai, criarTelaCadastroVeiculo(containerPai, morador, 2, 1)));

        boxBotoes.getChildren().addAll(btnZero, btnUm, btnDois);
        layout.getChildren().addAll(lblTitulo, lblInstrucao, boxBotoes);

        return layout;
    }

    private VBox criarTelaCadastroVeiculo(StackPane containerPai, model.Morador morador, int totalVeiculos, int veiculoAtual) {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setMaxWidth(400);

        Label lblTitulo = new Label("Cadastro de Veículo " + veiculoAtual + " / " + totalVeiculos);
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        javafx.scene.control.TextField txtPlaca = new javafx.scene.control.TextField();
        txtPlaca.setPromptText("Placa");
        estilizarInput(txtPlaca);

        javafx.scene.control.TextField txtModelo = new javafx.scene.control.TextField();
        txtModelo.setPromptText("Modelo");
        estilizarInput(txtModelo);

        javafx.scene.control.TextField txtCor = new javafx.scene.control.TextField();
        txtCor.setPromptText("Cor");
        estilizarInput(txtCor);

        Button btnSalvar = customizarBotaoMenu(veiculoAtual < totalVeiculos ? "Salvar" : "Finalizar");

        btnSalvar.setOnAction(e -> {
            String placa = txtPlaca.getText().trim();
            String modelo = txtModelo.getText().trim();
            String cor = txtCor.getText().trim();

            if (placa.isEmpty() || modelo.isEmpty() || cor.isEmpty()) {
                if(placa.isEmpty()) estilizarInputErro(txtPlaca);
                else estilizarInput(txtPlaca);
                if(modelo.isEmpty()) estilizarInputErro(txtModelo);
                else estilizarInput(txtModelo);
                if(cor.isEmpty()) estilizarInputErro(txtCor);
                else estilizarInput(txtCor);
                return;
            }

            String mensagem = "Placa: " + placa + 
                            "\nModelo: " + modelo + 
                            "\nCor: " + cor;

            if (exibirConfirmacao("Confirmar Cadastro?", mensagem)) {
                    model.Veiculo novoVeiculo = new model.Veiculo(placa, modelo, cor);
                    morador.adicionarVeiculo(novoVeiculo);
                }
            else return;

            if (veiculoAtual < totalVeiculos) {
                trocarSubTela(containerPai, criarTelaCadastroVeiculo(containerPai, morador, totalVeiculos, veiculoAtual + 1));
            } else {
                finalizarCadastroCompleto(containerPai, morador);
            }
        });

        layout.getChildren().addAll(lblTitulo, txtPlaca, txtModelo, txtCor, btnSalvar);
        return layout;
    }

    private void finalizarCadastroCompleto(StackPane containerPai, model.Morador moradorFinalizado) {
        banco.salvarMoradorComVeiculos(moradorFinalizado);
        String mensagemFinal = moradorFinalizado.toString();
        for(Veiculo v : moradorFinalizado.getVeiculo()){
            mensagemFinal += "\n";
            mensagemFinal += v.toString();
        }
        exibirFinalizacao("Cadastro Finalizado", mensagemFinal);
        trocarSubTela(containerPai, criarGridMoradores(containerPai));
    }

    private VBox criarTelaListarMoradores(StackPane containerPai) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        // O padding empurra o conteúdo para não colar nas bordas do cartão branco
        layout.setPadding(new Insets(30)); 

        Label lblTitulo = new Label("Lista de Moradores");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        // --- CABEÇALHO DA TABELA ---
        HBox cabecalho = new HBox(20);
        cabecalho.setAlignment(Pos.CENTER_LEFT);
        cabecalho.setStyle("-fx-border-color: transparent transparent #4A7C59 transparent; -fx-border-width: 0 0 2 0; -fx-padding: 0 0 10 0;");
        
        Label colId = new Label("ID");
        colId.setPrefWidth(80);
        colId.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333; -fx-alignment: center;");
        
        Label colDados = new Label("Dados do Morador");
        colDados.setPrefWidth(400);
        colDados.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");
        
        Label colVeiculos = new Label("Veículos Registrados");
        colVeiculos.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");

        cabecalho.getChildren().addAll(colId, colDados, colVeiculos);

        // --- CONTEÚDO (A LISTA DE VERDADE) ---
        VBox listaConteudo = new VBox(0); // Espaçamento 0 porque as linhas já terão bordas
        
        // Laço de repetição: Puxa do backend e cria uma linha visual para cada morador
        int contadorSimuladoId = 1; // Usado caso a sua classe Morador não tenha o getID() mapeado do banco ainda
        for (model.Morador m : controlador.getMoradores()) {
            // Se você tiver o getId() no backend, troque o contadorSimuladoId por m.getId()
            HBox linhaMorador = criarLinhaTabelaMorador(contadorSimuladoId, m);
            listaConteudo.getChildren().add(linhaMorador);
            contadorSimuladoId++;
        }

        // --- SCROLLPANE (Barra de Rolagem) ---
        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(listaConteudo);
        scrollPane.setFitToWidth(true); // Faz a lista esticar até a borda
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS); // Manda o scroll crescer e empurrar o botão voltar pra baixo

        // --- BOTÃO VOLTAR ---
        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridMoradores(containerPai)));

        layout.getChildren().addAll(lblTitulo, cabecalho, scrollPane, btnVoltar);
        return layout;
    }

    private HBox criarLinhaTabelaMorador(int idBanco, model.Morador m) {
        HBox linha = new HBox(20);
        linha.setAlignment(Pos.CENTER_LEFT);
        // Linha inferior cinza (Unselected palette) para separar os registros
        linha.setStyle("-fx-border-color: transparent transparent #CDCDCD transparent; -fx-border-width: 0 0 1 0; -fx-padding: 15 0 15 0;");

        // 1. COLUNA ID (Com a barrinha vertical da sua imagem)
        Label lblId = new Label(String.format("#%03d", idBanco)); // Formata para #001, #002...
        lblId.setPrefWidth(80);
        lblId.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px; -fx-text-fill: #333333; -fx-alignment: center; -fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");

        // 2. COLUNA DADOS PRINCIPAIS
        VBox boxDados = new VBox(5);
        boxDados.setPrefWidth(400);
        boxDados.setStyle("-fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");
        
        Label lblNome = new Label(m.getNome());
        lblNome.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #4A7C59;");
        
        Label lblInfoExtra = new Label("CPF: " + m.getCPF() + " | Tel: " + m.getTel());
        lblInfoExtra.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #666666;");
        
        Label lblEndereco = new Label("Endereço: " + m.getEnderecoMorador());
        lblEndereco.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #666666;");

        boxDados.getChildren().addAll(lblNome, lblInfoExtra, lblEndereco);

        // 3. COLUNA VEÍCULOS (Empilhados)
        VBox boxVeiculos = new VBox(5);
        boxVeiculos.setAlignment(Pos.CENTER_LEFT);

        java.util.List<model.Veiculo> veiculos = m.getVeiculo();
        
        if (veiculos == null || veiculos.isEmpty()) {
            Label lblSemVeiculo = new Label("Nenhum veículo");
            lblSemVeiculo.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #CDCDCD; -fx-font-style: italic;");
            boxVeiculos.getChildren().add(lblSemVeiculo);
        } else {
            // Cria um textinho para cada veículo que o morador tem
            for (model.Veiculo v : veiculos) {
                Label lblV = new Label("• " + v.getModelo() + " (" + v.getPlaca() + ") - " + v.getCor());
                lblV.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #333333;");
                boxVeiculos.getChildren().add(lblV);
            }
        }

        linha.getChildren().addAll(lblId, boxDados, boxVeiculos);
        return linha;
    }

    public VBox criarTelaExcluirMorador(StackPane containerPai) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setMaxWidth(300);

        Label lblTitulo = new Label("Excluir Morador");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        javafx.scene.control.TextField txtBusca = new javafx.scene.control.TextField();
        txtBusca.setPromptText("CPF");
        estilizarInput(txtBusca);
        aplicarFiltroNumerico(txtBusca, 11);

        Button btnBuscar = customizarBotaoMenu("Buscar");
        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, criarGridMoradores(containerPai)));
        btnBuscar.setOnAction(e -> {
            Morador moradorBuscado = null;

            for(Morador m : controlador.getMoradores()){
                if(m.getCPF().trim().equals(txtBusca.getText())){
                    moradorBuscado = m;
                    break;
                }
            }

            if(moradorBuscado == null){
                exibirAlerta("ERRO", "Morador não encontrado");
            }
            else{
                if(exibirConfirmacao("Excluir?", moradorBuscado.toString())){
                    final Morador alvo = moradorBuscado;
                    controlador.getVisitantes().removeIf(v -> v.getMoradorVisitado() == alvo);
                    controlador.getPrestadores().removeIf(p -> p.getMorador() == alvo);
                    banco.removerMorador(moradorBuscado);
                    controlador.getMoradores().remove(moradorBuscado);
                    exibirFinalizacao("Sucesso", "Morador excluído!");
                    trocarSubTela(containerPai, criarGridMoradores(containerPai));
                    }
                else return;
                }
        });

        layout.getChildren().addAll(lblTitulo, txtBusca, btnBuscar, btnVoltar);
        return layout;
    }
}
