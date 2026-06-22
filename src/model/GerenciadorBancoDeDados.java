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
    // CARREGAR TUDO QUANDO O SISTEMA INICIA
    // ==========================================
    public void carregarDadosIniciais(Controlador controlador) {
        String sqlMoradores = "SELECT * FROM moradores";

        try (Connection conn = conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sqlMoradores);
             ResultSet rsMoradores = stmt.executeQuery()) {

            while (rsMoradores.next()) {
                int idBanco = rsMoradores.getInt("id"); // Guarda o ID para buscar os carros depois
                
                Morador m = new Morador(
                    rsMoradores.getString("nome"), 
                    rsMoradores.getString("cpf"), 
                    rsMoradores.getString("telefone"), 
                    rsMoradores.getString("endereco")
                );
                m.setCredencial(new Credencial());

                // Agora, busca no banco se esse morador tem carros guardados
                String sqlVeiculos = "SELECT * FROM veiculos WHERE morador_id = ?";
                try (PreparedStatement stmtV = conn.prepareStatement(sqlVeiculos)) {
                    stmtV.setInt(1, idBanco);
                    try (ResultSet rsVeiculos = stmtV.executeQuery()) {
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
                // Morador totalmente ressuscitado com os carros, vai para o cérebro!
                controlador.adicionarMorador(m);
            }
            System.out.println("Moradores e Veículos carregados com sucesso da base de dados!");

        } catch (SQLException e) {
            System.out.println("Erro ao carregar dados do banco: " + e.getMessage());
        }
    }
}