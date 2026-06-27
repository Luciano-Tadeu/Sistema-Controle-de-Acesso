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

    public Button customizarBotaoTabela(String texto) {
        Button botao = new Button(texto);
        botao.setPrefWidth(80); 
        botao.setPrefHeight(25);
        botao.setAlignment(Pos.CENTER); 
        
        // Estilo Normal: Fundo Alternative (#8FC0A9) e fonte Poppins
        String estiloNormal = "-fx-background-color: #4A7C59; " +
                              "-fx-text-fill: white; " +
                              "-fx-font-family: 'Poppins'; " + 
                              "-fx-font-weight: bold; " +
                              "-fx-font-size: 10px; " +
                              "-fx-background-radius: 25;";
                              
        // Estilo Hover: Fundo Tertiary (#68B0AB) para dar destaque ao passar o mouse
        String estiloHover = "-fx-background-color: #8FC0A9; " +
                     "-fx-text-fill: white; " +
                     "-fx-font-family: 'Poppins'; " + 
                     "-fx-font-weight: bold; " +
                     "-fx-font-size: 10px; " +
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

    // ========================================================================
    // MÉTODOS DE FORMATAÇÃO E VALIDAÇÃO
    // ========================================================================
    public void aplicarFiltroNumerico(javafx.scene.control.TextField campo, int limiteCaracteres) {
        java.util.function.UnaryOperator<javafx.scene.control.TextFormatter.Change> filtro = mudanca -> {
            // Pega o texto que ficaria na tela se a digitação fosse aceita
            String novoTexto = mudanca.getControlNewText();
            
            // Verifica se contém APENAS números (\\d*) E se o tamanho está dentro do limite
            if (novoTexto.matches("\\d*") && novoTexto.length() <= limiteCaracteres) {
                return mudanca; // Permite a digitação
            }
            
            return null; // Bloqueia a digitação (o caractere nem aparece na tela)
        };
        
        campo.setTextFormatter(new javafx.scene.control.TextFormatter<>(filtro));
    }

    public void estilizarInputErro(javafx.scene.control.TextField input) {
        input.setPrefHeight(45);
        input.setStyle("-fx-background-color: #fdaaaa50; -fx-background-radius: 10px; -fx-padding: 10px; -fx-font-family: 'Inter'; -fx-font-size: 14px;");
    }

    public boolean exibirConfirmacao(String titulo, String mensagem) {
        javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        // Estilizando o fundo geral e a fonte
        alerta.getDialogPane().setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 14px; -fx-background-color: #FAF3DD;");

        // ==========================================
        // "PESCANDO" E ESTILIZANDO OS BOTÕES 
        // ==========================================
        javafx.scene.control.Button btnOk = (javafx.scene.control.Button) alerta.getDialogPane().lookupButton(javafx.scene.control.ButtonType.OK);
        javafx.scene.control.Button btnCancelar = (javafx.scene.control.Button) alerta.getDialogPane().lookupButton(javafx.scene.control.ButtonType.CANCEL);

        // Deixando os textos em português caso o sistema do PC esteja em inglês
        btnOk.setText("Confirmar");
        btnCancelar.setText("Cancelar");

        // Estilo do Botão Confirmar (Verde Destaque)
        btnOk.setStyle("-fx-background-color: #4A7C59; " +
                       "-fx-text-fill: white; " +
                       "-fx-font-family: 'Poppins'; " +
                       "-fx-font-weight: bold; " +
                       "-fx-background-radius: 8px; " +
                       "-fx-padding: 8px 20px;");
        btnOk.setCursor(javafx.scene.Cursor.HAND);

        // Estilo do Botão Cancelar (Cinza Unselected)
        btnCancelar.setStyle("-fx-background-color: #CDCDCD; " +
                             "-fx-text-fill: #333333; " +
                             "-fx-font-family: 'Poppins'; " +
                             "-fx-font-weight: bold; " +
                             "-fx-background-radius: 8px; " +
                             "-fx-padding: 8px 20px;");
        btnCancelar.setCursor(javafx.scene.Cursor.HAND);

        // Pausa a tela e espera o clique do usuário
        java.util.Optional<javafx.scene.control.ButtonType> resultado = alerta.showAndWait();

        // Retorna true APENAS se o botão clicado foi o OK
        return resultado.isPresent() && resultado.get() == javafx.scene.control.ButtonType.OK;
    }

    public void exibirFinalizacao(String titulo, String mensagem) {
        javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        // Estilizando o fundo geral e a fonte
        alerta.getDialogPane().setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 14px; -fx-background-color: #FAF3DD;");

        // ==========================================
        // "PESCANDO" E ESTILIZANDO OS BOTÕES 
        // ==========================================
        javafx.scene.control.Button btnOk = (javafx.scene.control.Button) alerta.getDialogPane().lookupButton(javafx.scene.control.ButtonType.OK);

        // Deixando os textos em português caso o sistema do PC esteja em inglês
        btnOk.setText("Ok");

        // Estilo do Botão Confirmar (Verde Destaque)
        btnOk.setStyle("-fx-background-color: #4A7C59; " +
                       "-fx-text-fill: white; " +
                       "-fx-font-family: 'Poppins'; " +
                       "-fx-font-weight: bold; " +
                       "-fx-background-radius: 8px; " +
                       "-fx-padding: 8px 20px;");
        btnOk.setCursor(javafx.scene.Cursor.HAND);

        // Pausa a tela e espera o clique do usuário
        java.util.Optional<javafx.scene.control.ButtonType> resultado = alerta.showAndWait();
    }

    public void exibirAlerta(String titulo, String erro) {
        javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(erro);

        // Estilizando o fundo geral e a fonte
        alerta.getDialogPane().setStyle("-fx-font-family: 'Poppins'; -fx-font-size: 14px; -fx-background-color: #FAF3DD;");

        // ==========================================
        // "PESCANDO" E ESTILIZANDO OS BOTÕES 
        // ==========================================
        javafx.scene.control.Button btnOk = (javafx.scene.control.Button) alerta.getDialogPane().lookupButton(javafx.scene.control.ButtonType.OK);

        // Deixando os textos em português caso o sistema do PC esteja em inglês
        btnOk.setText("Ok");

        // Estilo do Botão Confirmar (Verde Destaque)
        btnOk.setStyle("-fx-background-color: #4A7C59; " +
                       "-fx-text-fill: white; " +
                       "-fx-font-family: 'Poppins'; " +
                       "-fx-font-weight: bold; " +
                       "-fx-background-radius: 8px; " +
                       "-fx-padding: 8px 20px;");
        btnOk.setCursor(javafx.scene.Cursor.HAND);

        // Pausa a tela e espera o clique do usuário
        java.util.Optional<javafx.scene.control.ButtonType> resultado = alerta.showAndWait();
    }

}
