package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.DocFlavor.STRING;

public class GerenciadorBancoDeDados {

    private Connection conectar() throws SQLException {
        java.util.Properties props = new java.util.Properties();
        
        try (java.io.FileInputStream entrada = new java.io.FileInputStream("db.properties")) {
            props.load(entrada);
        } catch (java.io.IOException e) {
            System.out.println("Erro: Arquivo db.properties não encontrado na raiz do projeto!");
            throw new SQLException("Falha ao carregar credenciais do banco.");
        }

        // Puxa as informações do arquivo
        String host = props.getProperty("DB_HOST");
        String porta = props.getProperty("DB_PORT");
        String bancoDeDados = props.getProperty("DB_NAME");
        String user = props.getProperty("DB_USER");
        String password = props.getProperty("DB_PASS");

        // Monta a URL exigindo o SSL para a nuvem
        String url = "jdbc:mysql://" + host + ":" + porta + "/" + bancoDeDados + "?sslMode=REQUIRED";

        return DriverManager.getConnection(url, user, password);
    }

    // ==========================================
    // CARREGAR TUDO QUANDO O SISTEMA INICIA
    // ==========================================
    public void carregarDadosIniciais(Controlador controlador) {
        
        // 1. Definição de todas as Queries SQL no início para organização
        String sqlMoradores = "SELECT * FROM moradores";
        String sqlVeiculos = "SELECT * FROM veiculos WHERE morador_id = ?";
        String sqlVisitantes = "SELECT v.*, m.cpf AS cpf_morador FROM visitantes v " +
                               "JOIN moradores m ON v.morador_id = m.id";
        String sqlFuncionarios = "SELECT * FROM funcionarios";
        String sqlPrestadores = "SELECT p.*, m.cpf AS cpf_morador FROM prestadores p " +
                                "JOIN moradores m ON p.morador_id = m.id";

        // 2. A MÁGICA ACONTECE AQUI: Uma única conexão aberta para todo o método
        try (Connection conn = conectar()) {

            // ==========================================
            // PARTE A: CARREGAR MORADORES E SEUS VEÍCULOS
            // ==========================================
            try (PreparedStatement stmtM = conn.prepareStatement(sqlMoradores);
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

                    // Busca os veículos associados a este morador usando a MESMA conexão 'conn'
                    try (PreparedStatement stmtVei = conn.prepareStatement(sqlVeiculos)) {
                        stmtVei.setInt(1, idBanco);
                        try (ResultSet rsVeiculos = stmtVei.executeQuery()) {
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
                    controlador.adicionarMorador(m);
                }
                System.out.println("Moradores e Veículos carregados com sucesso!");
            }

            // ==========================================
            // PARTE B: CARREGAR VISITANTES (Com JOIN)
            // ==========================================
            try (PreparedStatement stmtVis = conn.prepareStatement(sqlVisitantes);
                 ResultSet rsVisitantes = stmtVis.executeQuery()) {

                while (rsVisitantes.next()) {
                    String cpfMoradorAlvo = rsVisitantes.getString("cpf_morador");
                    Morador moradorVisitado = null;

                    for (Morador m : controlador.getMoradores()) {
                        if (m.getCPF().equals(cpfMoradorAlvo)) {
                            moradorVisitado = m;
                            break;
                        }
                    }

                    Visitante v = new Visitante(
                        rsVisitantes.getString("nome"),
                        rsVisitantes.getString("cpf"),
                        rsVisitantes.getString("telefone"),
                        moradorVisitado
                    );
                    controlador.adicionarVisitante(v);
                }
                System.out.println("Visitantes carregados com sucesso!");
            }

            // ==========================================
            // PARTE C: CARREGAR FUNCIONÁRIOS
            // ==========================================
            try (PreparedStatement stmtFun = conn.prepareStatement(sqlFuncionarios);
                 ResultSet rsFuncionarios = stmtFun.executeQuery()) {
                
                while (rsFuncionarios.next()) {
                    // Nota: se a sua coluna no banco se chamar 'cargo', mude aqui de "funcao" para "cargo"
                    Funcionario f = new Funcionario(
                        rsFuncionarios.getString("nome"),
                        rsFuncionarios.getString("cpf"),
                        rsFuncionarios.getString("telefone"),
                        rsFuncionarios.getString("funcao") 
                    );
                    controlador.adicionarFuncionario(f);
                }
                System.out.println("Funcionários carregados com sucesso!");
            }

            // ==========================================
            // PARTE D: CARREGAR PRESTADORES (Com JOIN)
            // ==========================================
            try (PreparedStatement stmtPre = conn.prepareStatement(sqlPrestadores);
                 ResultSet rsPrestadores = stmtPre.executeQuery()) {
                
                while (rsPrestadores.next()) {
                    String cpfMoradorAlvo = rsPrestadores.getString("cpf_morador");
                    Morador moradorDestino = null;

                    for (Morador m : controlador.getMoradores()) {
                        if (m.getCPF().equals(cpfMoradorAlvo)) {
                            moradorDestino = m;
                            break;
                        }
                    }

                    PrestadorServico p = new PrestadorServico(
                        rsPrestadores.getString("nome"),
                        rsPrestadores.getString("cpf"),
                        rsPrestadores.getString("telefone"),
                        rsPrestadores.getString("cnh"),
                        rsPrestadores.getString("tiposervico"),
                        moradorDestino
                    );
                    controlador.adicionarPrestador(p);
                }
                System.out.println("Prestadores carregados com sucesso!");
            }

        } catch (SQLException e) {
            // Qualquer erro de banco em qualquer uma das partes vai cair centralizado aqui
            System.out.println("Erro crítico ao carregar dados iniciais da nuvem: " + e.getMessage());
        }
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

    public void salvarFuncionario(Funcionario funcionario){
        String sqlFuncionario = "INSERT INTO funcionarios (nome, cpf, telefone, funcao) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sqlFuncionario)) {
                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getCPF());
                stmt.setString(3, funcionario.getTel());
                stmt.setString(4, funcionario.getFuncao());
                stmt.executeUpdate();
                System.out.println("Funcionário salvo no MySQL com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar visita no banco: " + e.getMessage());
        }
    }

    public void salvarPrestador(PrestadorServico prestador){
        String sqlPrestador = "INSERT INTO prestadores (nome, cpf, telefone, cnh, tiposervico, morador_id) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlBuscaMorador = "SELECT id FROM moradores WHERE cpf = ?";
        int morador_id = -1;

        try(Connection conn = conectar();
            PreparedStatement stmt = conn.prepareStatement(sqlBuscaMorador)) {
                stmt.setString(1, prestador.getMorador().getCPF());
                try(ResultSet rs = stmt.executeQuery()){
                    if(rs.next()){
                        morador_id = rs.getInt("id");
                    }
                }
        } catch (Exception e) {
            System.out.println("Erro ao carregar prestadores: " + e.getMessage());
        }

        try (Connection conn = conectar();
            PreparedStatement stmt = conn.prepareStatement(sqlPrestador)){
                stmt.setString(1, prestador.getNome());
                stmt.setString(2, prestador.getCPF());
                stmt.setString(3, prestador.getTel());
                stmt.setString(4, prestador.getCnh());
                stmt.setString(5, prestador.getTipoServico());
                stmt.setInt(6, morador_id);
                stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao carregar prestadores: " + e.getMessage());
        }

    }
}
