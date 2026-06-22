package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GerenciadorBancoDeDados {

    private static final String URL = "jdbc:mysql://localhost:3306/condominio";
    private static final String USER = "root";
    private static final String PASS = "condominio123"; // Coloque sua senha real

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }


    
    // ==========================================
    // CARREGAR TUDO QUANDO O SISTEMA INICIA
    // ==========================================
    public void carregarDadosIniciais(Controlador controlador) {
    
    // ==========================================
    // 1. CARREGAR MORADORES E SEUS VEÍCULOS
    // ==========================================
    String sqlMoradores = "SELECT * FROM moradores";
    String sqlVeiculos = "SELECT * FROM veiculos WHERE morador_id = ?";

    try (Connection conn = conectar(); 
         PreparedStatement stmtM = conn.prepareStatement(sqlMoradores);
         ResultSet rsMoradores = stmtM.executeQuery()) {

        while (rsMoradores.next()) {
            int idBanco = rsMoradores.getInt("id"); 
            
            Morador m = new Morador(
                rsMoradores.getString("nome"), 
                rsMoradores.getString("cpf"), 
                rsMoradores.getString("telefone"), 
                rsMoradores.getString("endereco")
            );
            m.setCredencial(new Credencial());

            // Busca os veículos associados a este morador específico
            try (PreparedStatement stmtVei = conn.prepareStatement(sqlVeiculos)) {
                stmtVei.setInt(1, idBanco); // CORRIGIDO: Usando a variável correta e índice 1
                try (ResultSet rsVeiculos = stmtVei.executeQuery()) { // CORRIGIDO: Executando o stmt dos veículos
                    while (rsVeiculos.next()) {
                        Veiculo v = new Veiculo(
                            rsVeiculos.getString("placa"), 
                            rsVeiculos.getString("modelo"), 
                            rsVeiculos.getString("cor")
                        );
                        m.adicionarVeiculo(v);
                    }
                }
            }
            // Adiciona o morador completo (com carros) na memória
            controlador.adicionarMorador(m);
        }
        System.out.println("Moradores e Veículos carregados com sucesso!");

    } catch (SQLException e) {
        System.out.println("Erro ao carregar moradores/veículos: " + e.getMessage());
    }

    // ==========================================
    // 2. CARREGAR VISITANTES (Com JOIN inteligente)
    // ==========================================
    // Trazemos todos os visitantes e injetamos o CPF do respectivo morador na mesma tabela temporária
    String sqlVisitantes = "SELECT v.*, m.cpf AS cpf_morador FROM visitantes v " +
                           "JOIN moradores m ON v.morador_id = m.id";

    try (Connection conn = conectar();
         PreparedStatement stmtVis = conn.prepareStatement(sqlVisitantes);
         ResultSet rsVisitantes = stmtVis.executeQuery()) {

        while (rsVisitantes.next()) {
            String cpfMoradorAlvo = rsVisitantes.getString("cpf_morador");
            Morador moradorVisitado = null;

            // Busca o objeto morador correspondente que já está na memória do controlador
            for (Morador m : controlador.getMoradores()) {
                if (m.getCPF().equals(cpfMoradorAlvo)) {
                    moradorVisitado = m;
                    break;
                }
            }

            // Instancia o visitante vinculando o morador encontrado
            Visitante v = new Visitante(
                rsVisitantes.getString("nome"),
                rsVisitantes.getString("cpf"),
                rsVisitantes.getString("telefone"),
                moradorVisitado
            );

            controlador.adicionarVisitante(v);
        }
        System.out.println("Visitantes carregados com sucesso!");

    } catch (SQLException e) {
        System.out.println("Erro ao carregar visitantes: " + e.getMessage());
    }

    // ==========================================
    // 3. CARREGAR FUNCIONÁRIOS E PRESTADORES
    // ==========================================
    // Siga a mesma lógica sequencial acima para ler as tabelas de funcionários e prestadores futuramente!
}


    // ==========================================
    // SALVAR MORADOR E SEUS VEÍCULOS JUNTOS
    // ==========================================
    public void salvarMoradorComVeiculos(Morador morador) {
        String sqlMorador = "INSERT INTO moradores (nome, cpf, telefone, endereco) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar(); 
             // O Statement.RETURN_GENERATED_KEYS avisa o banco para devolver o ID criado!
             PreparedStatement stmt = conn.prepareStatement(sqlMorador, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, morador.getNome());
            stmt.setString(2, morador.getCPF());
            stmt.setString(3, morador.getTel());
            stmt.setString(4, morador.getEnderecoMorador());
            stmt.executeUpdate();

            // 1. Pega o ID que o MySQL acabou de criar para esse morador
            ResultSet rsId = stmt.getGeneratedKeys();
            int idGerado = -1;
            if (rsId.next()) {
                idGerado = rsId.getInt(1);
            }

            // 2. Se pegou o ID com sucesso, salva a lista de veículos dele!
            if (idGerado != -1 && !morador.getVeiculo().isEmpty()) {
                String sqlVeiculo = "INSERT INTO veiculos (placa, modelo, cor, morador_id) VALUES (?, ?, ?, ?)";
                
                try (PreparedStatement stmtVeiculo = conn.prepareStatement(sqlVeiculo)) {
                    for (Veiculo v : morador.getVeiculo()) {
                        stmtVeiculo.setString(1, v.getPlaca());
                        stmtVeiculo.setString(2, v.getModelo());
                        stmtVeiculo.setString(3, v.getCor());
                        stmtVeiculo.setInt(4, idGerado); // Vincula o carro ao ID do morador!
                        stmtVeiculo.executeUpdate();
                    }
                }
            }
            System.out.println("Morador e Veículos salvos no MySQL com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar no banco: " + e.getMessage());
        }
    }

    // ==========================================
    // SALVAR VISITANTE (Com busca de Foreign Key)
    // ==========================================
    public void salvarVisita(Visitante visita) {
        
        // 1. QUERY DE BUSCA: Vamos achar o ID do morador dono da visita usando o CPF dele
        String sqlBuscaMorador = "SELECT id FROM moradores WHERE cpf = ?";
        int idMoradorBanco = -1; // Começa negativo para sabermos se falhou

        try (Connection conn = conectar(); 
             PreparedStatement stmtBusca = conn.prepareStatement(sqlBuscaMorador)) {
            
            // Pega o CPF do morador que está dentro do objeto visitante
            // (Ajuste o nome do 'getMorador' se na sua classe estiver diferente, ex: getMoradorVisitado())
            stmtBusca.setString(1, visita.getMoradorVisitado().getCPF());
            
            try (ResultSet rs = stmtBusca.executeQuery()) {
                if (rs.next()) {
                    idMoradorBanco = rs.getInt("id"); // Achamos o ID real do banco!
                }
            }

            // Se o ID continuar -1, significa que o morador não existe no banco. Interrompemos aqui.
            if (idMoradorBanco == -1) {
                System.out.println("Erro: Morador não encontrado no banco de dados. A visita não foi salva.");
                return; 
            }

            // 2. QUERY DE INSERÇÃO: Agora que temos o ID, salvamos o visitante
            String sqlVisitante = "INSERT INTO visitantes (nome, cpf, telefone, morador_id) VALUES (?, ?, ?, ?)";
            
            try (PreparedStatement stmtVisita = conn.prepareStatement(sqlVisitante)) {
                
                stmtVisita.setString(1, visita.getNome());
                stmtVisita.setString(2, visita.getCPF());
                stmtVisita.setString(3, visita.getTel()); // Ajuste se o seu for getTelefone()
                stmtVisita.setInt(4, idMoradorBanco);     // A Mágica da Foreign Key acontece aqui!
                
                stmtVisita.executeUpdate();
                System.out.println("Visitante salvo no MySQL com sucesso, vinculado ao morador ID: " + idMoradorBanco);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar visita no banco: " + e.getMessage());
        }
    }
}