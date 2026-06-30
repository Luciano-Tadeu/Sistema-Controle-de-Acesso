package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class TelaRegistros extends CSS {

    private Controlador controlador;
    private GerenciadorBancoDeDados banco;

    public TelaRegistros(Controlador c, GerenciadorBancoDeDados b){
        this.controlador = c;
        this.banco = b;
    }

    // ========================================================================
    // TELA DE LISTAGEM DE REGISTROS (Modular e com Paginação)
    // ========================================================================
    // ========================================================================
    // TELA DE LISTAGEM DE REGISTROS (Retornando StackPane)
    // ========================================================================
    public StackPane criarTelaListarRegistros() {
        // 1. Cria a caixa mestre que será retornada no final
        StackPane root = new StackPane();

        // 2. Mantém o VBox para organizar os itens de cima para baixo
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30)); 

        Label lblTitulo = new Label("Lista de Registros");
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        // --- CABEÇALHO DA TABELA ---
        HBox cabecalho = new HBox(20);
        cabecalho.setAlignment(Pos.CENTER_LEFT);
        cabecalho.setStyle("-fx-border-color: transparent transparent #4A7C59 transparent; -fx-border-width: 0 0 2 0; -fx-padding: 0 0 10 0;");
        
        Label colId = new Label("ID");
        colId.setPrefWidth(60);
        colId.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333; -fx-alignment: center;");
        
        Label colUsuario = new Label("Usuário");
        colUsuario.setPrefWidth(150);
        colUsuario.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");

        Label colAcao = new Label("Mensagem / Ação");
        colAcao.setPrefWidth(300);
        colAcao.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");
        
        Label colData = new Label("Data/Hora");
        colData.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #333333;");

        cabecalho.getChildren().addAll(colId, colUsuario, colAcao, colData);

        // --- CONTEÚDO (Onde os itens vão aparecer) ---
        VBox listaConteudo = new VBox(0); 
        
        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(listaConteudo);
        scrollPane.setFitToWidth(true); 
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS); 

        // --- CONTROLES DE PAGINAÇÃO ---
        HBox boxPaginacao = new HBox(15);
        boxPaginacao.setAlignment(Pos.CENTER);
        
        Button btnAnterior = new Button("←");
        Button btnProximo = new Button("→");
        
        String estiloBotaoPaginacao = "-fx-background-radius: 50em; -fx-min-width: 35px; -fx-min-height: 35px; -fx-font-weight: bold; -fx-cursor: hand;";
        
        // --- LÓGICA DE PAGINAÇÃO (O Motor da Tela) ---
        int itensPorPagina = 10;
        final int[] paginaAtual = {0}; 
        
        // 1. Puxa os registros e cria uma cópia da lista para podermos manipulá-la com segurança
        java.util.List<model.Registro> todosRegistros = new java.util.ArrayList<>(controlador.getRegistros()); 

        // 2. Inverte a lista (O último elemento vira o primeiro, e assim por diante)
        java.util.Collections.reverse(todosRegistros); 

        Runnable atualizarLista = () -> {
            listaConteudo.getChildren().clear(); 
            
            int totalItens = todosRegistros.size();
            int totalPaginas = (int) Math.ceil((double) totalItens / itensPorPagina);
            
            int inicio = paginaAtual[0] * itensPorPagina;
            int fim = Math.min(inicio + itensPorPagina, totalItens); 
            
            for (int i = inicio; i < fim; i++) {
                model.Registro r = todosRegistros.get(i);
                listaConteudo.getChildren().add(criarLinhaTabelaRegistro(r.getId(), r)); 
            }

            boolean podeVoltar = paginaAtual[0] > 0;
            boolean podeAvancar = paginaAtual[0] < totalPaginas - 1;

            btnAnterior.setDisable(!podeVoltar);
            btnAnterior.setStyle(estiloBotaoPaginacao + (podeVoltar ? "-fx-background-color: #4A7C59; -fx-text-fill: white;" : "-fx-background-color: #CDCDCD; -fx-text-fill: #666666;"));
            
            btnProximo.setDisable(!podeAvancar);
            btnProximo.setStyle(estiloBotaoPaginacao + (podeAvancar ? "-fx-background-color: #4A7C59; -fx-text-fill: white;" : "-fx-background-color: #CDCDCD; -fx-text-fill: #666666;"));
        };

        btnAnterior.setOnAction(e -> {
            paginaAtual[0]--;
            atualizarLista.run();
        });

        btnProximo.setOnAction(e -> {
            paginaAtual[0]++;
            atualizarLista.run();
        });

        boxPaginacao.getChildren().addAll(btnAnterior, btnProximo);

        if (!todosRegistros.isEmpty()) {
            atualizarLista.run();
        } else {
            Label lblVazio = new Label("Nenhum registro encontrado.");
            lblVazio.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px; -fx-text-fill: #666666;");
            listaConteudo.getChildren().add(lblVazio);
            boxPaginacao.setVisible(false);
        }

        // 3. Monta o VBox normalmente
        layout.getChildren().addAll(lblTitulo, cabecalho, scrollPane, boxPaginacao);

        // 4. Joga o VBox montado para dentro do StackPane
        root.getChildren().add(layout);

        // 5. Retorna o StackPane
        return root;
    }
    // ========================================================================
    // LINHA DA TABELA DE REGISTROS
    // ========================================================================
    private HBox criarLinhaTabelaRegistro(int idBanco, model.Registro r) {
        HBox linha = new HBox(20);
        linha.setAlignment(Pos.CENTER_LEFT);
        linha.setStyle("-fx-border-color: transparent transparent #CDCDCD transparent; -fx-border-width: 0 0 1 0; -fx-padding: 15 0 15 0;");

        // 1. COLUNA ID
        Label lblId = new Label(String.format("#%03d", idBanco));
        lblId.setPrefWidth(60);
        lblId.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px; -fx-text-fill: #333333; -fx-alignment: center; -fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");

        // 2. COLUNA USUÁRIO 
        Label lblUsuario = new Label(r.getUsuario()); 
        lblUsuario.setPrefWidth(150);
        lblUsuario.setStyle("-fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #4A7C59; -fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");
        
        // 3. COLUNA MENSAGEM / AÇÃO
        Label lblAcao = new Label(r.getMensagem()); 
        lblAcao.setPrefWidth(300);
        lblAcao.setWrapText(true); 
        lblAcao.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #666666; -fx-border-color: transparent #CDCDCD transparent transparent; -fx-border-width: 0 2 0 0;");

        // 4. COLUNA DATA/HORA
        Label lblData = new Label(r.getDataHoraFormatada()); 
        lblData.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 13px; -fx-text-fill: #333333;");

        linha.getChildren().addAll(lblId, lblUsuario, lblAcao, lblData);
        return linha;
    }
}