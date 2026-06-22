package model;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro {

    private Controlador controlador;

    public TelaCadastro(Controlador controlador) {
        this.controlador = controlador;
    }

    public void cadastroMorador(){
        // =========================
        // TELA CADASTRO MORADOR
        // =========================
        JTextField campoNome = new JTextField();
        JTextField campoCPF = new JTextField();
        JTextField campoTelefone = new JTextField();
        JTextField campoEndereco = new JTextField();

        Morador novoMorador = null; 

        Object[] campos = {
            "Nome:", campoNome,
            "CPF (somente números):", campoCPF,
            "Telefone (somente números):", campoTelefone,
            "Endereço:", campoEndereco,
        };

        boolean cadastroValido = false;

        while (!cadastroValido) {
            // Substituindo o JOptionPane pelo nosso novo Diálogo Escuro
            int opM = exibirDialogoEscuro("Cadastro Morador", campos);

            if (opM != JOptionPane.OK_OPTION) {
                break; 
            }

            String nome = campoNome.getText().trim();
            String CPF = campoCPF.getText().trim();
            String telefone = campoTelefone.getText().trim();
            String endereco = campoEndereco.getText().trim();

            if (nome.isEmpty() || CPF.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                exibirMensagemEscura("Todos os campos são obrigatórios. Por favor, preencha tudo!", "Erro de Validação", true);
                continue; 
            }

            try {
                Long.parseLong(CPF);
                Long.parseLong(telefone);
                
                int confirmar = exibirConfirmacaoEscura(
                    "Nome: " + nome + 
                    "\nCPF: " + CPF + 
                    "\nTelefone: " + telefone + 
                    "\nEndereço: " + endereco,
                    "CONFIRMAR DADOS?"
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    novoMorador = new Morador(nome, CPF, telefone, endereco);
                    novoMorador.setCredencial(new Credencial());
                    this.controlador.adicionarMorador(novoMorador);
                    cadastroValido = true; 
                    exibirMensagemEscura("Morador cadastrado com sucesso!", "Sucesso", false);
                }

            } catch (NumberFormatException e) {
                exibirMensagemEscura("CPF e Telefone devem conter APENAS números!", "Erro de Formatação", true);
            }
        }

        if (cadastroValido) {
            boolean qtdValida = false;
            int qtdVeiculos = 0;

            while (!qtdValida) {
                // Substituindo o showInputDialog nativo
                String veiculoStr = exibirInputEscuro("Quantos veículos o morador possui? (Máximo 2)", "Cadastro de Veículos");
                
                if (veiculoStr == null) {
                    break; 
                }

                try {
                    qtdVeiculos = Integer.parseInt(veiculoStr); 
                    
                    if (qtdVeiculos < 0 || qtdVeiculos > 2) {
                        exibirMensagemEscura("Quantidade inválida. Insira um valor entre 0 e 2.", "Atenção", true);
                    } else {
                        qtdValida = true;
                    }
                } catch (NumberFormatException e) {
                    exibirMensagemEscura("Por favor, digite um número inteiro válido.", "Erro", true);
                }
            }

            for (int i = 0; i < qtdVeiculos; i++) {
                JTextField campoPlaca = new JTextField();
                JTextField campoModelo = new JTextField();
                JTextField campoCor = new JTextField();

                Object[] camposVeiculo = {
                    "Placa:", campoPlaca,
                    "Modelo:", campoModelo,
                    "Cor:", campoCor,
                };

                boolean veiculoValido = false;
                
                while (!veiculoValido) {
                    int op = exibirDialogoEscuro("Cadastro do Veículo " + (i + 1), camposVeiculo);

                    if (op != JOptionPane.OK_OPTION) {
                        break; 
                    }

                    String placa = campoPlaca.getText().trim();
                    String modelo = campoModelo.getText().trim();
                    String cor = campoCor.getText().trim();

                    if (placa.isEmpty() || modelo.isEmpty() || cor.isEmpty()) {
                        exibirMensagemEscura("Todos os dados do veículo são obrigatórios!", "Erro", true);
                        continue;
                    }

                    int confirmar = exibirConfirmacaoEscura(
                        "Placa: " + placa + 
                        "\nModelo: " + modelo + 
                        "\nCor: " + cor,
                        "CONFIRMAR VEÍCULO " + (i + 1) + "?"
                    );

                    if (confirmar == JOptionPane.YES_OPTION) {
                        Veiculo novoVeiculo = new Veiculo(placa, modelo, cor);
                        novoMorador.adicionarVeiculo(novoVeiculo);
                        veiculoValido = true;
                        exibirMensagemEscura("Veículo cadastrado!", "Sucesso", false);
                    }
                }
            }

            GerenciadorBancoDeDados banco = new GerenciadorBancoDeDados();
            banco.salvarMoradorComVeiculos(novoMorador);

        }
    }

    public void cadastroVisitante(){
        JTextField campoNome = new JTextField();
        JTextField campoCPF = new JTextField();
        JTextField campoTelefone = new JTextField();
        JTextField campoMorador = new JTextField();

        Morador moradorVisitado = null; 

        Object[] campos = {
            "Nome:", campoNome,
            "CPF (somente números):", campoCPF,
            "Telefone (somente números):", campoTelefone,
            "Morador (Endereço):", campoMorador,
        };

        boolean cadastroValido = false;

        while (!cadastroValido) {
            int opM = exibirDialogoEscuro("Cadastro Visita", campos);

            if (opM != JOptionPane.OK_OPTION) {
                break; 
            }

            String nome = campoNome.getText().trim();
            String CPF = campoCPF.getText().trim();
            String telefone = campoTelefone.getText().trim();
            String morador = campoMorador.getText().trim();

            if (nome.isEmpty() || CPF.isEmpty() || telefone.isEmpty() || morador.isEmpty()) {
                exibirMensagemEscura("Todos os campos são obrigatórios. Por favor, preencha tudo!", "Erro de Validação", true);
                continue;
            }

            try {
                Long.parseLong(CPF);
                Long.parseLong(telefone);
                
                int confirmar = exibirConfirmacaoEscura(
                    "Nome: " + nome + 
                    "\nCPF: " + CPF + 
                    "\nTelefone: " + telefone + 
                    "\nMorador (Endereço): " + morador,
                    "CONFIRMAR DADOS?"
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    for(Morador m : controlador.getMoradores()){
                        if(m.getEnderecoMorador().equalsIgnoreCase(morador)){
                            moradorVisitado = m;
                            break;
                        }
                    }
                    if(moradorVisitado != null){
                        Visitante novoVisitante = new Visitante(nome, CPF, telefone, moradorVisitado);
                        controlador.adicionarVisitante(novoVisitante);
                        cadastroValido = true;
                        GerenciadorBancoDeDados banco = new GerenciadorBancoDeDados();
                        banco.salvarVisita(novoVisitante);
                        exibirMensagemEscura("Visitante cadastrado com sucesso!", "Sucesso", false);
                    }
                    else{
                        exibirMensagemEscura("Endereço do morador não existe ou não foi encontrado!", "Erro Endereço Não Existe", true);
                    }
                }
            } catch (NumberFormatException e) {
                exibirMensagemEscura("CPF e Telefone devem conter APENAS números!", "Erro de Formatação", true);
            }
        }
    }

    public void cadastroFuncionario(){
        JTextField campoNome = new JTextField();
        JTextField campoCPF = new JTextField();
        JTextField campoTelefone = new JTextField();
        JTextField campoFuncao = new JTextField();

        Object[] campos = {
            "Nome:", campoNome,
            "CPF (somente números):", campoCPF,
            "Telefone (somente números):", campoTelefone,
            "Função:", campoFuncao,
        };

        boolean cadastroValido = false;

        while (!cadastroValido) {
            int opM = exibirDialogoEscuro("Cadastro Funcionário", campos);

            if (opM != JOptionPane.OK_OPTION) {
                break; 
            }

            String nome = campoNome.getText().trim();
            String CPF = campoCPF.getText().trim();
            String telefone = campoTelefone.getText().trim();
            String funcao = campoFuncao.getText().trim();

            if (nome.isEmpty() || CPF.isEmpty() || telefone.isEmpty() || funcao.isEmpty()) {
                exibirMensagemEscura("Todos os campos são obrigatórios!", "Erro de Validação", true);
                continue;
            }

            try {
                Long.parseLong(CPF);
                Long.parseLong(telefone);
                
                int confirmar = exibirConfirmacaoEscura(
                    "Nome: " + nome + 
                    "\nCPF: " + CPF + 
                    "\nTelefone: " + telefone + 
                    "\nFunção: " + funcao,
                    "CONFIRMAR DADOS?"
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    Funcionario novFuncionario = new Funcionario(nome, CPF, telefone, funcao);
                    controlador.adicionarFuncionario(novFuncionario);
                    cadastroValido = true;
                    exibirMensagemEscura("Funcionário cadastrado com sucesso!", "Sucesso", false);
                }
            } catch (NumberFormatException e) {
                exibirMensagemEscura("CPF e Telefone devem conter APENAS números!", "Erro de Formatação", true);
            }
        }
    }

    public void cadastroPrestador(){
        JTextField campoNome = new JTextField();
        JTextField campoCPF = new JTextField();
        JTextField campoTelefone = new JTextField();
        JTextField campoCNH = new JTextField();
        JTextField campoTipoServico = new JTextField();
        JTextField campoMorador = new JTextField();

        Morador moradorVisitado = null; 

        Object[] campos = {
            "Nome:", campoNome,
            "CPF (somente números):", campoCPF,
            "Telefone (somente números):", campoTelefone,
            "CNH (Somente números):", campoCNH,
            "Tipo de Serviço:", campoTipoServico,
            "Morador (Endereço):", campoMorador,
        };

        boolean cadastroValido = false;

        while (!cadastroValido) {
            int opM = exibirDialogoEscuro("Cadastro Prestador de Serviço", campos);

            if (opM != JOptionPane.OK_OPTION) {
                break; 
            }

            String nome = campoNome.getText().trim();
            String CPF = campoCPF.getText().trim();
            String telefone = campoTelefone.getText().trim();
            String CNH = campoCNH.getText().trim();
            String tipoServico = campoTipoServico.getText().trim();
            String morador = campoMorador.getText().trim();

            if (nome.isEmpty() || CPF.isEmpty() || telefone.isEmpty() || morador.isEmpty() || CNH.isEmpty() || tipoServico.isEmpty()) {
                exibirMensagemEscura("Todos os campos são obrigatórios!", "Erro de Validação", true);
                continue;
            }

            try {
                Long.parseLong(CPF);
                Long.parseLong(telefone);
                Long.parseLong(CNH);
                
                int confirmar = exibirConfirmacaoEscura(
                    "Nome: " + nome + 
                    "\nCPF: " + CPF + 
                    "\nTelefone: " + telefone + 
                    "\nCNH: " + CNH + 
                    "\nTipo de Serviço: " + tipoServico + 
                    "\nMorador (Endereço): " + morador,
                    "CONFIRMAR DADOS?"
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    for(Morador m : controlador.getMoradores()){
                        if(m.getEnderecoMorador().equalsIgnoreCase(morador)){
                            moradorVisitado = m;
                            break;
                        }
                    }
                    if(moradorVisitado != null){
                        PrestadorServico novoPrestadorServico = new PrestadorServico(nome, CPF, telefone, CNH, tipoServico, moradorVisitado);
                        controlador.adicionarPrestador(novoPrestadorServico);
                        cadastroValido = true;
                        exibirMensagemEscura("Prestador de serviço cadastrado!", "Sucesso", false);
                    }
                    else{
                        exibirMensagemEscura("Endereço do morador não existe ou não foi encontrado!", "Erro Endereço", true);
                    }
                }
            } catch (NumberFormatException e) {
                exibirMensagemEscura("CPF, Telefone e CNH devem conter APENAS números!", "Erro de Formatação", true);
            }
        }
    }


    // =========================================================================
    // FÁBRICAS DE INTERFACE (Os métodos que estilizam as janelas)
    // =========================================================================

    /**
     * Monta o formulário de cadastro dinamicamente lendo o array de Objetos
     */
    private int exibirDialogoEscuro(String titulo, Object[] campos) {
        JDialog dialog = new JDialog((java.awt.Frame) null, titulo, true);
        dialog.setSize(550, 60 * (campos.length / 2) + 120); 
        dialog.setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout(15, 15));
        painelPrincipal.setBackground(new Color(33, 37, 41));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel para organizar os campos em grade (Grid)
        JPanel painelForm = new JPanel(new GridLayout(campos.length / 2, 2, 10, 15));
        painelForm.setBackground(new Color(33, 37, 41));

        for (Object obj : campos) {
            if (obj instanceof String) {
                JLabel label = new JLabel((String) obj);
                label.setForeground(new Color(248, 249, 250));
                label.setFont(new Font("Segoe UI", Font.BOLD, 14));
                painelForm.add(label);
            } else if (obj instanceof JTextField) {
                JTextField campo = (JTextField) obj;
                campo.setBackground(new Color(45, 49, 53));
                campo.setForeground(Color.WHITE);
                campo.setCaretColor(Color.WHITE);
                campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                campo.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(73, 80, 87)),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5) 
                ));
                painelForm.add(campo);
            }
        }

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.setBackground(new Color(33, 37, 41));

        JButton btnConfirmar = customizarBotao("Confirmar", new Color(46, 204, 113));
        JButton btnCancelar = customizarBotao("Cancelar", new Color(231, 76, 60));

        final int[] resultado = {JOptionPane.CANCEL_OPTION}; 

        btnConfirmar.addActionListener(e -> {
            resultado[0] = JOptionPane.OK_OPTION;
            dialog.dispose();
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnConfirmar);

        painelPrincipal.add(painelForm, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        dialog.add(painelPrincipal);
        dialog.setVisible(true);

        return resultado[0];
    }

    /**
     * Tela customizada para pedir a quantidade de veículos
     */
    private String exibirInputEscuro(String mensagem, String titulo) {
        JTextField campo = new JTextField();
        Object[] campos = {mensagem, campo};
        int op = exibirDialogoEscuro(titulo, campos);
        if (op == JOptionPane.OK_OPTION) {
            return campo.getText();
        }
        return null;
    }

    /**
     * Janela escura que exibe o resumo dos dados para confirmação do usuário
     */
    private int exibirConfirmacaoEscura(String mensagem, String titulo) {
        JDialog dialog = new JDialog((java.awt.Frame) null, titulo, true);
        dialog.setSize(400, 280);
        dialog.setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(15, 15));
        painel.setBackground(new Color(33, 37, 41));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea text = new JTextArea(mensagem);
        text.setBackground(new Color(33, 37, 41));
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        text.setEditable(false);
        painel.add(text, BorderLayout.CENTER);

        JPanel pBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pBotoes.setBackground(new Color(33, 37, 41));
        JButton btnSim = customizarBotao("Sim, Confirmar", new Color(46, 204, 113));
        JButton btnNao = customizarBotao("Corrigir Dados", new Color(231, 76, 60));
        
        final int[] result = {JOptionPane.NO_OPTION};
        btnSim.addActionListener(e -> { result[0] = JOptionPane.YES_OPTION; dialog.dispose(); });
        btnNao.addActionListener(e -> dialog.dispose());
        
        pBotoes.add(btnNao);
        pBotoes.add(btnSim);
        painel.add(pBotoes, BorderLayout.SOUTH);

        dialog.add(painel);
        dialog.setVisible(true);
        return result[0];
    }

    /**
     * Substitui o message dialog padrão por uma janela estilizada para erros e sucessos
     */
    private void exibirMensagemEscura(String mensagem, String titulo, boolean isErro) {
        JDialog dialog = new JDialog((java.awt.Frame) null, titulo, true);
        dialog.setSize(400, 220); // Aumentamos um pouco a altura padrão da janela
        dialog.setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(15, 15));
        painel.setBackground(new Color(33, 37, 41));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Substituímos o JLabel com HTML por um JTextArea inteligente
        JTextArea areaMsg = new JTextArea(mensagem);
        areaMsg.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        areaMsg.setForeground(Color.WHITE);
        areaMsg.setBackground(new Color(33, 37, 41)); // Mesma cor do fundo para "sumir" a caixa
        areaMsg.setEditable(false);
        areaMsg.setFocusable(false); // Tira o foco para o cursor de texto não ficar piscando
        areaMsg.setLineWrap(true);       // Ativa a quebra de linha automática
        areaMsg.setWrapStyleWord(true);  // Quebra a linha nas palavras (e não no meio da letra)
        
        painel.add(areaMsg, BorderLayout.CENTER);

        JPanel pBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pBotoes.setBackground(new Color(33, 37, 41));
        
        Color corBtn = isErro ? new Color(231, 76, 60) : new Color(52, 152, 219);
        JButton btnOk = customizarBotao("OK", corBtn);
        btnOk.setPreferredSize(new Dimension(100, 35));
        btnOk.addActionListener(e -> dialog.dispose());
        
        pBotoes.add(btnOk);
        painel.add(pBotoes, BorderLayout.SOUTH);

        dialog.add(painel);
        dialog.setVisible(true);
    }

    /**
     * Cópia do botão customizado para garantir o Hover localmente
     */
    private JButton customizarBotao(String texto, Color corFundo) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(corFundo);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);     
        botao.setBorder(BorderFactory.createLineBorder(corFundo.darker(), 1));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        botao.setOpaque(true); 
        botao.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(corFundo.darker(), 1), // Borda externa (a linha visível)
            BorderFactory.createEmptyBorder(8, 15, 8, 15)         // Borda interna vazia (Cima, Esquerda, Baixo, Direita)
        ));

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(corFundo.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(corFundo);
            }
        });

        return botao;
    }
}