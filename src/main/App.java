package main;

import javax.swing.*;
import java.awt.*;
import model.Controlador;
import model.Funcionario;
import model.Morador;
import model.PrestadorServico;
import model.Registro;
import model.TelaCadastro;
import model.Veiculo;
import model.Visitante;

public class App {

    // Instanciação do Controlador como atributo estático para os botões acessarem
    private static Controlador controlador = new Controlador();

    public static void main(String[] args) {

        // 1. Criação da Janela Principal (Moldura)
        JFrame janela = new JFrame("Portaria Condomínio - Módulo de Acesso");
        janela.setSize(650, 500);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null); // Centraliza no meio da tela

        // 2. Painel Principal com bordas e cor de fundo escura (Sleek Dark Theme)
        JPanel painelPrincipal = new JPanel(new BorderLayout(15, 15));
        painelPrincipal.setBackground(new Color(33, 37, 41)); // Cinza escuro/Grafite
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 3. Título do Sistema
        JLabel lblTitulo = new JLabel("SISTEMA DE CONTROLE DE ACESSO", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(248, 249, 250)); // Branco Off-white
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // 4. Painel de Botões organizados em Grade (5 linhas, 2 colunas, espaços de 12px)
        JPanel painelBotoes = new JPanel(new GridLayout(5, 2, 12, 12));
        painelBotoes.setBackground(new Color(33, 37, 41));

        // Definição da Paleta de Cores dos Botões
        Color azulCadastro = new Color(52, 152, 219);     // Azul elegante para Cadastros
        Color cinzaListagem = new Color(73, 80, 87);      // Cinza neutro para Relatórios/Listas
        Color verdeRegistros = new Color(46, 204, 113);   // Verde para o Log de Registros
        Color vermelhoSair = new Color(231, 76, 60);      // Vermelho para o botão de Sair

        // 5. Instanciação dos Botões Customizados
        JButton btnCadMorador = customizarBotao("Cadastrar Novo Morador", azulCadastro);
        JButton btnListMorador = customizarBotao("Listar Moradores", cinzaListagem);
        
        JButton btnCadVisita = customizarBotao("Cadastrar Visita", azulCadastro);
        JButton btnListVisita = customizarBotao("Listar Visitas", cinzaListagem);
        
        JButton btnCadFuncionario = customizarBotao("Cadastrar Funcionário", azulCadastro);
        JButton btnListFuncionario = customizarBotao("Listar Funcionários", cinzaListagem);
        
        JButton btnCadPrestador = customizarBotao("Cadastrar Prestador", azulCadastro);
        JButton btnListPrestador = customizarBotao("Listar Serviços", cinzaListagem);
        
        JButton btnVerRegistros = customizarBotao("Ver Registros de Acesso", verdeRegistros);
        JButton btnSair = customizarBotao("Sair do Sistema", vermelhoSair);

        // =========================================================================
        // 6. AÇÕES DOS BOTÕES (Listeners contendo exatamente a sua lógica original)
        // =========================================================================

        btnCadMorador.addActionListener(e -> {
            TelaCadastro tela = new TelaCadastro(controlador);
            tela.cadastroMorador();
        });

        btnListMorador.addActionListener(e -> {
            String relatorioMoradores = "=== MORADORES NO SISTEMA ===\n\n";
            for (Morador m : controlador.getMoradores()) {
                relatorioMoradores += "• Morador: " + m.toString() + "\n"; 
                if (!m.getVeiculo().isEmpty()) {
                    relatorioMoradores += "  ↳ Veículos:\n";
                    for (Veiculo v : m.getVeiculo()) {
                        relatorioMoradores += "    - " + v.toString() + "\n";
                    }
                }
                relatorioMoradores += "--------------------------------------------------------\n";
            }
            exibirJanelaRolavel(relatorioMoradores, "Relatório de Moradores");
        });

        btnCadVisita.addActionListener(e -> {
            TelaCadastro tela = new TelaCadastro(controlador);
            tela.cadastroVisitante();
        });

        btnListVisita.addActionListener(e -> {
            String relatorioVisitantes = "=== VISITANTES NO SISTEMA ===\n\n";
            for (Visitante v : controlador.getVisitantes()) {
                relatorioVisitantes += "• " + v.toString() + "\n--------------------------------------------------------\n";
            }
            exibirJanelaRolavel(relatorioVisitantes, "Relatório de Visitantes");
        });

        btnCadFuncionario.addActionListener(e -> {
            TelaCadastro tela = new TelaCadastro(controlador);
            tela.cadastroFuncionario();
        });

        btnListFuncionario.addActionListener(e -> {
            String relatorioFuncionarios = "=== FUNCIONÁRIOS NO SISTEMA ===\n\n";
            for (Funcionario f : controlador.getFuncionarios()) {
                relatorioFuncionarios += "• " + f.toString() + "\n--------------------------------------------------------\n";
            }
            exibirJanelaRolavel(relatorioFuncionarios, "Relatório de Funcionários");
        });

        btnCadPrestador.addActionListener(e -> {
            TelaCadastro tela = new TelaCadastro(controlador);
            tela.cadastroPrestador();
        });

        btnListPrestador.addActionListener(e -> {
            String relatorioPrestadores = "=== PRESTADORES NO SISTEMA ===\n\n";
            for (PrestadorServico p : controlador.getPrestadores()) {
                relatorioPrestadores += "• " + p.toString() + "\n--------------------------------------------------------\n";
            }
            exibirJanelaRolavel(relatorioPrestadores, "Relatório de Prestadores");
        });

        btnVerRegistros.addActionListener(e -> {
            String relatorioRegistros = "=== HISTÓRICO DE ACESSOS ===\n\n";
            for (Registro r : controlador.getRegistros()) {
                relatorioRegistros += "• Registro ID: " + r.toString() + "\n--------------------------------------------------------\n";
            }
            exibirJanelaRolavel(relatorioRegistros, "Log de Registros");
        });

        btnSair.addActionListener(e -> {
            System.exit(0); // Fecha a aplicação com segurança
        });

        // 7. Adicionando os botões na grade seguindo a ordem visual lado a lado
        painelBotoes.add(btnCadMorador);
        painelBotoes.add(btnListMorador);
        painelBotoes.add(btnCadVisita);
        painelBotoes.add(btnListVisita);
        painelBotoes.add(btnCadFuncionario);
        painelBotoes.add(btnListFuncionario);
        painelBotoes.add(btnCadPrestador);
        painelBotoes.add(btnListPrestador);
        painelBotoes.add(btnVerRegistros);
        painelBotoes.add(btnSair);

        // 8. Montagem Final e Exibição
        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);
        janela.add(painelPrincipal);
        janela.setVisible(true);
    }

    /**
     * Método auxiliar para padronizar e estilizar os botões
     */
    private static JButton customizarBotao(String texto, Color corFundo) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(corFundo);
        botao.setForeground(Color.WHITE); // Texto sempre branco
        botao.setFocusPainted(false);     
        botao.setBorder(BorderFactory.createLineBorder(corFundo.darker(), 1));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        botao.setOpaque(true); 

        // ==========================================
        // EFEITO DE HOVER (Mouse Entra / Mouse Sai)
        // ==========================================
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Quando o mouse entra, deixa a cor original mais clara
                botao.setBackground(corFundo.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Quando o mouse sai, volta para a cor original
                botao.setBackground(corFundo);
            }
        });

        return botao;
    }

    /**
     * Método auxiliar para criar uma janela de visualização totalmente customizada com tema escuro e scroll
     */
    private static void exibirJanelaRolavel(String texto, String titulo) {
        // 1. Cria um JDialog modal (bloqueia a janela principal até que este seja fechado)
        JDialog dialog = new JDialog((Frame) null, titulo, true);
        dialog.setSize(550, 450);
        dialog.setLocationRelativeTo(null); // Centraliza na tela

        // 2. Painel Principal do Pop-up (Mesmo grafite do Menu Principal)
        JPanel painelContador = new JPanel(new BorderLayout(12, 12));
        painelContador.setBackground(new Color(33, 37, 41));
        painelContador.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 3. Configuração da Área de Texto (Fundo escuro e fonte clara)
        JTextArea areaTexto = new JTextArea();
        areaTexto.setText(texto);
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 13)); // Mantém o alinhamento dos relatórios
        areaTexto.setBackground(new Color(45, 49, 53));            // Um cinza ligeiramente mais claro para contraste
        areaTexto.setForeground(new Color(248, 249, 250));          // Texto Off-White
        areaTexto.setCaretColor(Color.WHITE);                       // Cor do cursor de seleção
        areaTexto.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12)); // Margem interna do texto

        // 4. Configuração da Barra de Rolagem (Scroll)
        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(73, 80, 87), 1)); // Borda sutil
        scroll.getViewport().setBackground(new Color(45, 49, 53));

        // 5. Botão de Fechar (Reutilizando seu método e aplicando a cor cinza de listagem)
        JButton btnFechar = customizarBotao("Fechar Relatório", new Color(73, 80, 87));
        btnFechar.setPreferredSize(new Dimension(160, 35)); // Define um tamanho elegante para o botão
        
        // Ação para fechar apenas o pop-up atual (sem fechar o sistema inteiro)
        btnFechar.addActionListener(e -> dialog.dispose());

        // Painel inferior para centralizar o botão de fechar
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelInferior.setBackground(new Color(33, 37, 41));
        painelInferior.add(btnFechar);

        // 6. Montagem final do Diálogo
        painelContador.add(scroll, BorderLayout.CENTER);
        painelContador.add(painelInferior, BorderLayout.SOUTH);
        
        dialog.add(painelContador);
        dialog.setVisible(true); // Exibe a janela na tela
    }
}