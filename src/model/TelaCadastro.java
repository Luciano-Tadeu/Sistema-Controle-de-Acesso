package model;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

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

        // Guarda a referência do morador criado para facilitar a adição de veículos depois
        Morador novoMorador = null; 

        Object[] campos = {
            "Nome:", campoNome,
            "CPF (somente números):", campoCPF,
            "Telefone (somente números):", campoTelefone,
            "Endereço:", campoEndereco,
        };

        boolean cadastroValido = false;

        // Loop para garantir que o usuário preencha os dados corretamente
        while (!cadastroValido) {
            int opM = JOptionPane.showConfirmDialog(null, campos, "Cadastro Morador", JOptionPane.OK_CANCEL_OPTION);

            // Se o usuário clicar em Cancelar ou fechar a janela, interrompe o loop
            if (opM != JOptionPane.OK_OPTION) {
                break; 
            }

            // O .trim() remove espaços em branco acidentais no início e fim
            String nome = campoNome.getText().trim();
            String CPF = campoCPF.getText().trim();
            String telefone = campoTelefone.getText().trim();
            String endereco = campoEndereco.getText().trim();

            // 1. Validação com IF: Impedir campos vazios
            if (nome.isEmpty() || CPF.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios. Por favor, preencha tudo!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                continue; // Reinicia o loop para tentar novamente
            }

            // 2. Validação com TRY-CATCH: Garantir que CPF e Telefone sejam apenas números
            try {
                Long.parseLong(CPF);
                Long.parseLong(telefone);
                
                // Se o código chegou até aqui sem cair no catch, os dados são válidos
                int confirmar = JOptionPane.showConfirmDialog(null,
                    "Nome: " + nome + 
                    "\nCPF: " + CPF + 
                    "\nTelefone: " + telefone + 
                    "\nEndereço: " + endereco,
                    "CONFIRMAR DADOS?",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    novoMorador = new Morador(nome, CPF, telefone, endereco);
                    novoMorador.setCredencial(new Credencial());
                    this.controlador.adicionarMorador(novoMorador);
                    cadastroValido = true; // Validação passou, sai do loop
                }

            } catch (NumberFormatException e) {
                // Cai aqui se o parseLong falhar (ou seja, tem letras ou caracteres especiais)
                JOptionPane.showMessageDialog(null, "CPF e Telefone devem conter APENAS números (sem traços ou espaços)!", "Erro de Formatação", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Só prossegue para perguntar de veículos se o morador foi efetivamente cadastrado
        if (cadastroValido) {
            boolean qtdValida = false;
            int qtdVeiculos = 0;

            // Loop para validar a quantidade de veículos
            while (!qtdValida) {
                String veiculoStr = JOptionPane.showInputDialog(null, "Quantos veículos o morador possui? (Máximo 2)", "Cadastro de Veículos", JOptionPane.QUESTION_MESSAGE);
                
                if (veiculoStr == null) {
                    break; // Usuário cancelou
                }

                try {
                    qtdVeiculos = Integer.parseInt(veiculoStr); // Try-catch para garantir que digitou um número
                    
                    // Validação com IF para travar a quantidade entre 0 e 2
                    if (qtdVeiculos < 0 || qtdVeiculos > 2) {
                        JOptionPane.showMessageDialog(null, "Quantidade inválida. Insira um valor entre 0 e 2.", "Atenção", JOptionPane.WARNING_MESSAGE);
                    } else {
                        qtdValida = true;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, digite um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Loop para criar as telas de acordo com a quantidade exata informada
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
                    int op = JOptionPane.showConfirmDialog(null, camposVeiculo, "Cadastro do Veículo " + (i + 1), JOptionPane.OK_CANCEL_OPTION);

                    if (op != JOptionPane.OK_OPTION) {
                        break; // Permite cancelar o cadastro deste veículo específico
                    }

                    String placa = campoPlaca.getText().trim();
                    String modelo = campoModelo.getText().trim();
                    String cor = campoCor.getText().trim();

                    if (placa.isEmpty() || modelo.isEmpty() || cor.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos os dados do veículo são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }

                    int confirmar = JOptionPane.showConfirmDialog(null,
                        "Placa: " + placa + 
                        "\nModelo: " + modelo + 
                        "\nCor: " + cor,
                        "CONFIRMAR VEÍCULO " + (i + 1) + "?",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirmar == JOptionPane.YES_OPTION) {
                        Veiculo novoVeiculo = new Veiculo(placa, modelo, cor);
                        // Utilizando a referência direta do novoMorador criado lá em cima,
                        // eliminando a necessidade do for() que buscava pelo ID temporário.
                        novoMorador.adicionarVeiculo(novoVeiculo);
                        veiculoValido = true;
                    }
                }
            }
        }
    }

    public void cadastroVisitante(){
        // =========================
        // TELA CADASTRO VISITANTE
        // =========================
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

        // Loop para garantir que o usuário preencha os dados corretamente
        while (!cadastroValido) {
            int opM = JOptionPane.showConfirmDialog(null, campos, "Cadastro Morador", JOptionPane.OK_CANCEL_OPTION);

            if (opM != JOptionPane.OK_OPTION) {
                break; 
            }

            String nome = campoNome.getText().trim();
            String CPF = campoCPF.getText().trim();
            String telefone = campoTelefone.getText().trim();
            String morador = campoMorador.getText().trim();

            // 1. Validação com IF: Impedir campos vazios
            if (nome.isEmpty() || CPF.isEmpty() || telefone.isEmpty() || morador.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios. Por favor, preencha tudo!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            // 2. Validação com TRY-CATCH: Garantir que CPF e Telefone sejam apenas números
            try {
                Long.parseLong(CPF);
                Long.parseLong(telefone);
                
                int confirmar = JOptionPane.showConfirmDialog(null,
                    "Nome: " + nome + 
                    "\nCPF: " + CPF + 
                    "\nTelefone: " + telefone + 
                    "\nMorador (Endereço): " + morador,
                    "CONFIRMAR DADOS?",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    for(Morador m : controlador.getMoradores()){
                        if(m.getEnderecoMorador().equalsIgnoreCase(morador)){
                            moradorVisitado = m;
                            break;
                        }
                    }
                    // 3. Verificar se existe o morador no sistema
                    if(moradorVisitado != null){
                        Visitante novoVisitante = new Visitante(nome, CPF, telefone, moradorVisitado);
                        controlador.adicionarVisitante(novoVisitante);
                        cadastroValido = true;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Endereço do morador não existe ou não foi encontrado!", "Erro Endereço Não Existe", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CPF e Telefone devem conter APENAS números (sem traços ou espaços)!", "Erro de Formatação", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void cadastroFuncionario(){
        
    }

    public void cadastroPrestador(){
        
    }

}
