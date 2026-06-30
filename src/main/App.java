package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Controlador;
import model.GerenciadorBancoDeDados;
import model.TelaFuncionarios;
import model.TelaMorador;
import model.TelaRegistros;
import model.TelaServicos;
import model.TelaVisitas;

public class App extends Application {

    private static Controlador controlador = new Controlador();
    private static GerenciadorBancoDeDados banco = new GerenciadorBancoDeDados();
    private static TelaMorador telaMorador = new TelaMorador(controlador, banco);
    private static TelaFuncionarios telaFuncionario = new TelaFuncionarios(controlador, banco);
    private static TelaVisitas telaVisita = new TelaVisitas(controlador, banco);
    private static TelaServicos telaServico = new TelaServicos(controlador, banco);
    private static TelaRegistros telaRegistros = new TelaRegistros(controlador, banco);

    private StackPane cartaoBrancoConteudo;
    
    private Button botaoMenuAtivo = null; 

    private final String ESTILO_NORMAL = "-fx-background-color: #8FC0A950; -fx-text-fill: white; -fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-background-radius: 0 25 25 0;";
    private final String ESTILO_HOVER = "-fx-background-color: #8FC0A975; -fx-text-fill: white; -fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-background-radius: 0 25 25 0;";
    private final String ESTILO_SELECTED = "-fx-background-color: #68B0AB; -fx-text-fill: white; -fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-background-radius: 0 25 25 0;";

    @Override
    public void start(Stage palcoPrincipal) {
        
        banco.carregarDadosIniciais(controlador);

        // ==========================================
        // ESTRUTURA PRINCIPAL
        // ==========================================
        HBox raiz = new HBox(); 

        // ==========================================
        // MENU LATERAL (Cor: Destaque #4A7C59)
        // ==========================================
        VBox menuLateral = new VBox(15); 
        menuLateral.setPadding(new Insets(30, 0, 0, 0)); 
        menuLateral.setPrefWidth(260); 
        menuLateral.setStyle("-fx-background-color: #4A7C59;"); // Aplicando Destaque

        Button btnRegistros = customizarBotaoMenu("Registros");
        Button btnMoradores = customizarBotaoMenu("Moradores");
        Button btnVisitas = customizarBotaoMenu("Visitas");
        Button btnFuncionarios = customizarBotaoMenu("Funcionários");
        Button btnServicos = customizarBotaoMenu("Serviços");
        Button btnSair = customizarBotaoSair("Sair");


        javafx.scene.layout.Region espacador = new javafx.scene.layout.Region();
        
        VBox.setVgrow(espacador, Priority.ALWAYS);

        VBox.setMargin(btnSair, new Insets(0, 0, 30, 0));

        menuLateral.getChildren().addAll(btnRegistros, btnMoradores, btnVisitas, btnFuncionarios, btnServicos);
        menuLateral.getChildren().add(espacador); 
        menuLateral.getChildren().add(btnSair);

        // ==========================================
        // ÁREA DE CONTEÚDO (Cor: Primary #FAF3DD)
        // ==========================================
        StackPane areaDireita = new StackPane();
        areaDireita.setStyle("-fx-background-color: #FAF3DD;"); // Aplicando Primary
        HBox.setHgrow(areaDireita, Priority.ALWAYS); 

        cartaoBrancoConteudo = new StackPane();
        cartaoBrancoConteudo.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 30px;");
        StackPane.setMargin(cartaoBrancoConteudo, new Insets(30)); 

        areaDireita.getChildren().add(cartaoBrancoConteudo);


        btnRegistros.setOnAction(e -> trocarTela(telaRegistros.criarTelaListarRegistros(), btnRegistros));
        btnMoradores.setOnAction(e -> trocarTela(telaMorador.construirModuloMoradores(), btnMoradores));
        btnVisitas.setOnAction(e -> trocarTela(telaVisita.construirModuloVisitas(), btnVisitas));
        btnFuncionarios.setOnAction(e -> trocarTela(telaFuncionario.construirModuloFuncionarios(), btnFuncionarios));
        btnServicos.setOnAction(e -> trocarTela(telaServico.construirModuloServicos(), btnServicos));
        btnSair.setOnAction(e -> {javafx.application.Platform.exit();});

        trocarTela(telaRegistros.criarTelaListarRegistros(), btnRegistros);

        // ==========================================
        // MONTAGEM FINAL
        // ==========================================
        raiz.getChildren().addAll(menuLateral, areaDireita);

        Scene cena = new Scene(raiz, 1280, 720); 
        palcoPrincipal.setTitle("Portaria Condomínio - Módulo de Acesso");
        palcoPrincipal.setScene(cena);
        palcoPrincipal.show();
    }

    private Button customizarBotaoMenu(String texto) {
        Button botao = new Button(texto);
        botao.setPrefWidth(240); 
        botao.setPrefHeight(50);
        botao.setAlignment(Pos.CENTER); 
        botao.setCursor(Cursor.HAND);
        
        botao.setStyle(ESTILO_NORMAL);

        botao.setOnMouseEntered(e -> {
            if (botao != botaoMenuAtivo) {
                botao.setStyle(ESTILO_HOVER);
            }
        });

        botao.setOnMouseExited(e -> {
            if (botao != botaoMenuAtivo) {
                botao.setStyle(ESTILO_NORMAL);
            }
        });

        return botao;
    }

    private Button customizarBotaoSair(String texto) {
        Button botao = new Button(texto);
        botao.setPrefWidth(240); 
        botao.setPrefHeight(50);
        botao.setAlignment(Pos.CENTER); 
        botao.setCursor(Cursor.HAND);
        
        String ESTILO_NORMAL_EXIT = "-fx-background-color: #fdaaaa; -fx-text-fill: white; -fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-background-radius: 0 25 25 0;";
        String ESTILO_HOVER_EXIT = "-fx-background-color: #ee6969; -fx-text-fill: white; -fx-font-family: 'Poppins'; -fx-font-weight: bold; -fx-font-size: 18px; -fx-background-radius: 0 25 25 0;";
        botao.setStyle(ESTILO_NORMAL_EXIT);

        botao.setOnMouseEntered(e -> {
            if (botao != botaoMenuAtivo) {
                botao.setStyle(ESTILO_HOVER_EXIT);
            }
        });

        botao.setOnMouseExited(e -> {
            if (botao != botaoMenuAtivo) {
                botao.setStyle(ESTILO_NORMAL_EXIT);
            }
        });

        return botao;
    }

    private void trocarTela(StackPane novaTela, Button botaoClicado) {
        cartaoBrancoConteudo.getChildren().clear(); 
        cartaoBrancoConteudo.getChildren().add(novaTela); 

        if (botaoMenuAtivo != null) {
            botaoMenuAtivo.setStyle(ESTILO_NORMAL);
        }

        botaoMenuAtivo = botaoClicado;
        botaoMenuAtivo.setStyle(ESTILO_SELECTED);
    }

    public static void main(String[] args) {
        launch(args); 
    }

}