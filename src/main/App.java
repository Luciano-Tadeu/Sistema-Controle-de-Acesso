package main;
import java.rmi.registry.Registry;
import java.util.ResourceBundle.Control;
import javax.swing.*;

import model.Controlador;
import model.Credencial;
import model.Funcionario;
import model.Morador;
import model.Pessoa;
import model.PrestadorServico;
import model.Registro;
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
                          "1 - Adicionar Morador e Veículo\n" +
                          "2 - Listar Moradores e Veículos\n\n" +
                          "3 - Adicionar Visitante\n" +
                          "4 - Listar Visitantes\n\n" +
                          "5 - Adicionar Funcionário\n" +
                          "6 - Listar Funcionários\n\n" +
                          "7 - Adicionar Prestador de Serviço\n" +
                          "8 - Listar Prestadores de Serviço\n\n" +
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
                    JTextField campoNome = new JTextField();
                    JTextField campoCPF = new JTextField();
                    JTextField campoTelefone = new JTextField();
                    JTextField campoEndereco = new JTextField();
                    int idTemp = 0;

                    Object[] campos = {
                        "Nome:", campoNome,
                        "CPF:", campoCPF,
                        "Telefone:", campoTelefone,
                        "Endereço:", campoEndereco,
                    };

                    int opM = JOptionPane.showConfirmDialog(
                        null,
                        campos,
                        "Cadastro Morador",
                        JOptionPane.OK_CANCEL_OPTION
                    );

                    if(opM == JOptionPane.OK_OPTION){
                        String nome = campoNome.getText();
                        String CPF = campoCPF.getText();
                        String telefone = campoTelefone.getText();
                        String endereco = campoEndereco.getText();

                        int confirmar = JOptionPane.showConfirmDialog(
                        null,
                        "Nome: " + nome +
                        "\nCPF: " + CPF +
                        "\nTelefone: " + telefone +
                        "\nEndereço: " + endereco,
                        "CONFIRMAR?",
                        JOptionPane.YES_NO_OPTION
                        );

                        if(confirmar == JOptionPane.YES_OPTION){
                            Morador novoMorador = new Morador(nome, CPF, telefone, endereco);
                            novoMorador.setCredencial(new Credencial());
                            idTemp = novoMorador.getID();
                            controlador.adicionarMorador(novoMorador);
                        }
                    }

                    int veiculo = JOptionPane.showConfirmDialog(
                        null,
                        "Possui Veículo?",
                        "Cadastro Morador",
                        JOptionPane.YES_NO_OPTION
                    );

                    if(veiculo == JOptionPane.YES_OPTION){
                        JTextField campoPlaca = new JTextField();
                        JTextField campoModelo = new JTextField();
                        JTextField campoCor = new JTextField();

                        Object[] camposVeiculo = {
                            "Placa:", campoPlaca,
                            "Modelo:", campoModelo,
                            "Cor:", campoCor,
                        };

                        int op = JOptionPane.showConfirmDialog(
                            null, 
                            camposVeiculo, 
                            "Cadastro Morador", 
                            JOptionPane.OK_CANCEL_OPTION
                        );

                        if(op == JOptionPane.OK_OPTION){
                            String placa = campoPlaca.getText();
                            String modelo = campoModelo.getText();
                            String cor = campoCor.getText();

                            int confirmar = JOptionPane.showConfirmDialog(
                            null,
                            "Placa: " + placa +
                            "\nModelo: " + modelo +
                            "\nCor: " + cor,
                            "CONFIRMAR?",
                            JOptionPane.YES_NO_OPTION
                            );

                            if(confirmar == JOptionPane.YES_OPTION){
                                for(Morador m:controlador.getMoradores()){
                                    if(m.getID() == idTemp){
                                        Veiculo novoVeiculo = new Veiculo(placa, modelo, cor);
                                        m.adicionarVeiculo(novoVeiculo);
                                    }
                                }
                                
                            }
                        }
                    }
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
