package main;
import javax.swing.*;

import model.Controlador;
import model.Credencial;
import model.Funcionario;
import model.Morador;
import model.PrestadorServico;
import model.Registro;
import model.TelaCadastro;
import model.Veiculo;
import model.Visitante;



public class App {

    public static void main(String[] args) throws Exception {


        //Instanciação do Controlador
        Controlador controlador = new Controlador();


        // ==========================================
        // INTERFACE GRÁFICA (MENU DO MÓDULO)
        // ==========================================
        boolean executando = true;

        while (executando) {
            String menu = "=== MÓDULO DE ACESSO - PORTARIA ===\n\n" +
                          "1 - Cadastrar Novo Morador\n" +
                          "2 - Listar Moradores\n\n" +
                          "3 - Cadastrar Visita\n" +
                          "4 - Listar Visitas\n\n" +
                          "5 - Cadastrar Funcionário\n" +
                          "6 - Listar Funcionários\n\n" +
                          "7 - Cadastrar Prestador de Serviço\n" +
                          "8 - Listar Serviços\n\n" +
                          "9 - Ver Registros de Acesso\n" +
                          "0 - Sair do Sistema\n\n" +
                          "Escolha uma opção:";

            String opcao = JOptionPane.showInputDialog(null, menu, "Portaria Condomínio", JOptionPane.QUESTION_MESSAGE);

            if (opcao == null || opcao.equals("0")) {
                executando = false;
                JOptionPane.showMessageDialog(null, "Saindo do Módulo de Acesso...");
                break;
            }

            switch (opcao) {
                case "1":
                    // =========================
                    // TELA CADASTRO MORADOR
                    // =========================
                    TelaCadastro telaCadastroMorador = new TelaCadastro(controlador);
                    telaCadastroMorador.cadastroMorador();
                    break;
                case "2":
                    // LISTAR MORADORES NA JANELA
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
                    break;

                case "3":
                    // =========================
                    // TELA CADASTRO VISITANTE
                    // =========================
                    TelaCadastro telaCadastroVisitante = new TelaCadastro(controlador);
                    telaCadastroVisitante.cadastroVisitante();
                    break;
                case "4":
                    // LISTAR VISITANTES NA JANELA
                    String relatorioVisitantes = "=== VISITANTES NO SISTEMA ===\n\n";
                    for (Visitante v : controlador.getVisitantes()) {
                        relatorioVisitantes += "• " + v.toString() + "\n--------------------------------------------------------\n";
                    }
                    exibirJanelaRolavel(relatorioVisitantes, "Relatório de Visitantes");
                    break;

                case "5":
                    // =========================
                    // TELA CADASTRO FUNCIONÁRIO
                    // =========================
                    TelaCadastro telaCadastroFuncionario = new TelaCadastro(controlador);
                    telaCadastroFuncionario.cadastroFuncionario();
                    break;

                case "6":
                    // LISTAR FUNCIONÁRIOS NA JANELA
                    String relatorioFuncionarios = "=== FUNCIONÁRIOS NO SISTEMA ===\n\n";
                    for (Funcionario f : controlador.getFuncionarios()) {
                        relatorioFuncionarios += "• " + f.toString() + "\n--------------------------------------------------------\n";
                    }
                    exibirJanelaRolavel(relatorioFuncionarios, "Relatório de Funcionários");
                    break;

                case "7":
                    // =========================
                    // TELA CADASTRO PRESTADOR
                    // =========================
                    TelaCadastro telaCadastroPrestador = new TelaCadastro(controlador);
                    telaCadastroPrestador.cadastroPrestador();
                    break;
                case "8":
                    // LISTAR PRESTADORES NA JANELA
                    String relatorioPrestadores = "=== PRESTADORES NO SISTEMA ===\n\n";
                    for (PrestadorServico p : controlador.getPrestadores()) {
                        relatorioPrestadores += "• " + p.toString() + "\n--------------------------------------------------------\n";
                    }
                    exibirJanelaRolavel(relatorioPrestadores, "Relatório de Prestadores");
                    break;

                case "9":
                    // LISTAR REGISTROS NA JANELA
                    String relatorioRegistros = "=== HISTÓRICO DE ACESSOS ===\n\n";
                    for (Registro r : controlador.getRegistros()) {
                        relatorioRegistros += "• Registro ID: " + r.toString() + "\n--------------------------------------------------------\n";
                    }
                    exibirJanelaRolavel(relatorioRegistros, "Log de Registros");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    /**
     * Método auxiliar para criar uma janela com barra de rolagem (Scroll)
     */
    private static void exibirJanelaRolavel(String texto, String titulo) {
        JTextArea areaTexto = new JTextArea(15, 45);
        areaTexto.setText(texto);
        areaTexto.setEditable(false); 
        JScrollPane scroll = new JScrollPane(areaTexto);
        
        JOptionPane.showMessageDialog(null, scroll, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
