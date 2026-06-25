package model;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public abstract class CSS {

    // ========================================================================
    // MÉTODOS DE ESTILIZAÇÃO E UTILIDADE PARA AS SUB-TELAS
    // ========================================================================
    /**
     * Botões com a cor Alternative (#8FC0A9) e fonte Poppins
     */
    public Button customizarBotaoMenu(String texto) {
        Button botao = new Button(texto);
        botao.setPrefWidth(240); 
        botao.setPrefHeight(50);
        botao.setAlignment(Pos.CENTER); 
        
        // Estilo Normal: Fundo Alternative (#8FC0A9) e fonte Poppins
        String estiloNormal = "-fx-background-color: #4A7C59; " +
                              "-fx-text-fill: white; " +
                              "-fx-font-family: 'Poppins'; " + 
                              "-fx-font-weight: bold; " +
                              "-fx-font-size: 18px; " +
                              "-fx-background-radius: 25;";
                              
        // Estilo Hover: Fundo Tertiary (#68B0AB) para dar destaque ao passar o mouse
        String estiloHover = "-fx-background-color: #8FC0A9; " +
                     "-fx-text-fill: white; " +
                     "-fx-font-family: 'Poppins'; " + 
                     "-fx-font-weight: bold; " +
                     "-fx-font-size: 18px; " +
                     "-fx-background-radius: 25; " +
                     "-fx-border-color: #4A7C59; " + // Define a cor da borda (ex: Dark Slate Gray)
                     "-fx-border-width: 2px; " +     // Define a espessura da borda
                     "-fx-border-radius: 25;";       // Arredonda a borda para acompanhar o fundo

        botao.setStyle(estiloNormal);
        botao.setCursor(Cursor.HAND);

        botao.setOnMouseEntered(e -> botao.setStyle(estiloHover));
        botao.setOnMouseExited(e -> botao.setStyle(estiloNormal));

        return botao;
    }

    public Button criarBotaoGridLtop(String texto, String caminhoImagemPng) {
        Button btn = new Button(texto);
        btn.setPrefSize(215, 215); 
        
        // Configura para o ícone ficar EM CIMA do texto
        btn.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        btn.setGraphicTextGap(15); // Espaço entre o ícone e o texto
        
        try {
            // Busca o arquivo PNG na pasta do projeto
            javafx.scene.image.Image imagem = new javafx.scene.image.Image(getClass().getResourceAsStream(caminhoImagemPng));
            javafx.scene.image.ImageView icone = new javafx.scene.image.ImageView(imagem);
            
            // Define o tamanho exato que o PNG vai ter na tela (ex: 48x48 pixels)
            icone.setFitWidth(48);
            icone.setFitHeight(48);
            icone.setPreserveRatio(true); // Evita que a imagem fique esticada/deformada
            
            btn.setGraphic(icone); 
            
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem: " + caminhoImagemPng);
        }
        
        String estiloNormal = "-fx-background-color: #4A7C59; " + 
                              "-fx-text-fill: white; " +
                              "-fx-font-family: 'Poppins'; " +
                              "-fx-font-weight: bold; " +
                              "-fx-font-size: 20px; " +
                              "-fx-background-radius: 20 0 0 0;"; 
        
        String estiloHover = "-fx-background-color: #68B0AB; " + 
                             "-fx-text-fill: white; " +
                             "-fx-font-family: 'Poppins'; " +
                             "-fx-font-weight: bold; " +
                             "-fx-font-size: 20px; " +
                             "-fx-background-radius: 20 0 0 0;";

        btn.setStyle(estiloNormal);
        btn.setCursor(Cursor.HAND);
        btn.setOnMouseEntered(e -> btn.setStyle(estiloHover));
        btn.setOnMouseExited(e -> btn.setStyle(estiloNormal));

        return btn;
    }

    public Button criarBotaoGridRtop(String texto, String caminhoImagemPng) {
        Button btn = new Button(texto);
        btn.setPrefSize(215, 215); 
        
        // Configura para o ícone ficar EM CIMA do texto
        btn.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        btn.setGraphicTextGap(15); // Espaço entre o ícone e o texto
        
        try {
            // Busca o arquivo PNG na pasta do projeto
            javafx.scene.image.Image imagem = new javafx.scene.image.Image(getClass().getResourceAsStream(caminhoImagemPng));
            javafx.scene.image.ImageView icone = new javafx.scene.image.ImageView(imagem);
            
            // Define o tamanho exato que o PNG vai ter na tela (ex: 48x48 pixels)
            icone.setFitWidth(48);
            icone.setFitHeight(48);
            icone.setPreserveRatio(true); // Evita que a imagem fique esticada/deformada
            
            btn.setGraphic(icone); 
            
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem: " + caminhoImagemPng);
        }
        
        String estiloNormal = "-fx-background-color: #4A7C59; " + 
                              "-fx-text-fill: white; " +
                              "-fx-font-family: 'Poppins'; " +
                              "-fx-font-weight: bold; " +
                              "-fx-font-size: 20px; " +
                              "-fx-background-radius: 0 20 0 0;"; 
        
        String estiloHover = "-fx-background-color: #68B0AB; " + 
                             "-fx-text-fill: white; " +
                             "-fx-font-family: 'Poppins'; " +
                             "-fx-font-weight: bold; " +
                             "-fx-font-size: 20px; " +
                             "-fx-background-radius: 0 20 0 0;";

        btn.setStyle(estiloNormal);
        btn.setCursor(Cursor.HAND);
        btn.setOnMouseEntered(e -> btn.setStyle(estiloHover));
        btn.setOnMouseExited(e -> btn.setStyle(estiloNormal));

        return btn;
    }

    public Button criarBotaoGridRdown(String texto, String caminhoImagemPng) {
        Button btn = new Button(texto);
        btn.setPrefSize(215, 215); 
        
        // Configura para o ícone ficar EM CIMA do texto
        btn.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        btn.setGraphicTextGap(15); // Espaço entre o ícone e o texto
        
        try {
            // Busca o arquivo PNG na pasta do projeto
            javafx.scene.image.Image imagem = new javafx.scene.image.Image(getClass().getResourceAsStream(caminhoImagemPng));
            javafx.scene.image.ImageView icone = new javafx.scene.image.ImageView(imagem);
            
            // Define o tamanho exato que o PNG vai ter na tela (ex: 48x48 pixels)
            icone.setFitWidth(48);
            icone.setFitHeight(48);
            icone.setPreserveRatio(true); // Evita que a imagem fique esticada/deformada
            
            btn.setGraphic(icone); 
            
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem: " + caminhoImagemPng);
        }
        
        String estiloNormal = "-fx-background-color: #4A7C59; " + 
                              "-fx-text-fill: white; " +
                              "-fx-font-family: 'Poppins'; " +
                              "-fx-font-weight: bold; " +
                              "-fx-font-size: 20px; " +
                              "-fx-background-radius: 0 0 20 0;"; 
        
        String estiloHover = "-fx-background-color: #68B0AB; " + 
                             "-fx-text-fill: white; " +
                             "-fx-font-family: 'Poppins'; " +
                             "-fx-font-weight: bold; " +
                             "-fx-font-size: 20px; " +
                             "-fx-background-radius: 0 0 20 0;";

        btn.setStyle(estiloNormal);
        btn.setCursor(Cursor.HAND);
        btn.setOnMouseEntered(e -> btn.setStyle(estiloHover));
        btn.setOnMouseExited(e -> btn.setStyle(estiloNormal));

        return btn;
    }

    public Button criarBotaoGridLdown(String texto, String caminhoImagemPng) {
        Button btn = new Button(texto);
        btn.setPrefSize(215, 215); 
        
        // Configura para o ícone ficar EM CIMA do texto
        btn.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        btn.setGraphicTextGap(15); // Espaço entre o ícone e o texto
        
        try {
            // Busca o arquivo PNG na pasta do projeto
            javafx.scene.image.Image imagem = new javafx.scene.image.Image(getClass().getResourceAsStream(caminhoImagemPng));
            javafx.scene.image.ImageView icone = new javafx.scene.image.ImageView(imagem);
            
            // Define o tamanho exato que o PNG vai ter na tela (ex: 48x48 pixels)
            icone.setFitWidth(48);
            icone.setFitHeight(48);
            icone.setPreserveRatio(true); // Evita que a imagem fique esticada/deformada
            
            btn.setGraphic(icone); 
            
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem: " + caminhoImagemPng);
        }

        
        String estiloNormal = "-fx-background-color: #4A7C59; " + 
                              "-fx-text-fill: white; " +
                              "-fx-font-family: 'Poppins'; " +
                              "-fx-font-weight: bold; " +
                              "-fx-font-size: 20px; " +
                              "-fx-background-radius: 0 0 0 20;"; 
        
        String estiloHover = "-fx-background-color: #68B0AB; " + 
                             "-fx-text-fill: white; " +
                             "-fx-font-family: 'Poppins'; " +
                             "-fx-font-weight: bold; " +
                             "-fx-font-size: 20px; " +
                             "-fx-background-radius: 0 0 0 20;";

        btn.setStyle(estiloNormal);
        btn.setCursor(Cursor.HAND);
        btn.setOnMouseEntered(e -> btn.setStyle(estiloHover));
        btn.setOnMouseExited(e -> btn.setStyle(estiloNormal));

        return btn;
    }

    public Button criarBotaoGridMid(String texto, String caminhoImagemPng) {
        Button btn = new Button(texto);
        btn.setPrefSize(215, 215); 
        
        // Configura para o ícone ficar EM CIMA do texto
        btn.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        btn.setGraphicTextGap(15); // Espaço entre o ícone e o texto
        
        try {
            // Busca o arquivo PNG na pasta do projeto
            javafx.scene.image.Image imagem = new javafx.scene.image.Image(getClass().getResourceAsStream(caminhoImagemPng));
            javafx.scene.image.ImageView icone = new javafx.scene.image.ImageView(imagem);
            
            // Define o tamanho exato que o PNG vai ter na tela (ex: 48x48 pixels)
            icone.setFitWidth(48);
            icone.setFitHeight(48);
            icone.setPreserveRatio(true); // Evita que a imagem fique esticada/deformada
            
            btn.setGraphic(icone); 
            
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem: " + caminhoImagemPng);
        }

        
        String estiloNormal = "-fx-background-color: #4A7C59; " + 
                              "-fx-text-fill: white; " +
                              "-fx-font-family: 'Poppins'; " +
                              "-fx-font-weight: bold; " +
                              "-fx-font-size: 20px; " +
                              "-fx-background-radius: 0 0 0 0;"; 
        
        String estiloHover = "-fx-background-color: #68B0AB; " + 
                             "-fx-text-fill: white; " +
                             "-fx-font-family: 'Poppins'; " +
                             "-fx-font-weight: bold; " +
                             "-fx-font-size: 20px; " +
                             "-fx-background-radius: 0 0 0 0;";

        btn.setStyle(estiloNormal);
        btn.setCursor(Cursor.HAND);
        btn.setOnMouseEntered(e -> btn.setStyle(estiloHover));
        btn.setOnMouseExited(e -> btn.setStyle(estiloNormal));

        return btn;
    }

    public void estilizarInput(javafx.scene.control.TextField input) {
        input.setPrefHeight(45);
        input.setStyle("-fx-background-color: #F0F0F0; -fx-background-radius: 10px; -fx-padding: 10px; -fx-font-family: 'Inter'; -fx-font-size: 14px;");
    }

    public void trocarSubTela(StackPane containerPai, javafx.scene.Node novaTela) {
        containerPai.getChildren().clear();
        containerPai.getChildren().add(novaTela);
    }

    // ========================================================================
    // TELAS GENÉRICAS (Para evitar código repetido)
    // ========================================================================
    public VBox criarTelaListarGenerica(StackPane containerPai, String tituloTela, java.util.function.Supplier<VBox> metodoVoltar) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label(tituloTela);
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        Label lblTabela = new Label("Tabela de dados entrará aqui...");
        lblTabela.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 16px; -fx-text-fill: #7f8c8d;");

        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, metodoVoltar.get()));

        layout.getChildren().addAll(lblTitulo, lblTabela, btnVoltar);
        return layout;
    }

    public VBox criarTelaBuscaCPFGenerica(StackPane containerPai, String tituloAcao, java.util.function.Supplier<VBox> metodoVoltar) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setMaxWidth(300);

        Label lblTitulo = new Label(tituloAcao);
        lblTitulo.setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 24px; -fx-text-fill: #4A7C59; -fx-font-weight: bold;");

        javafx.scene.control.TextField txtBusca = new javafx.scene.control.TextField();
        txtBusca.setPromptText("Digite o CPF...");
        estilizarInput(txtBusca);

        Button btnBuscar = customizarBotaoMenu("Buscar");
        Button btnVoltar = customizarBotaoMenu("Voltar");
        btnVoltar.setStyle(btnVoltar.getStyle().replace("#8FC0A9", "#CDCDCD"));
        btnVoltar.setOnAction(e -> trocarSubTela(containerPai, metodoVoltar.get()));

        layout.getChildren().addAll(lblTitulo, txtBusca, btnBuscar, btnVoltar);
        return layout;
    }

}
