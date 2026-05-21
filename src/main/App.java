package main;
import java.rmi.registry.Registry;
import java.util.ResourceBundle.Control;

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

        //Criando um Objeto Morador
        Credencial credencial1 = new Credencial();
        Veiculo veiculo1 = new Veiculo("MVZ5886", "Toyota Etios", "Prata");
        Morador morador1 = new Morador(1, "Marcos", "05518955197", "65981077777", "Rua 29, 19");
        morador1.setCredencial(credencial1);
        morador1.adicionarVeiculo(veiculo1);


        //Criando um Objeto Funcionário
        Credencial credencialFunc = new Credencial();
        Funcionario funcionario1 = new Funcionario(2, "José", "66667892108", "65987748722", "Jardineiro");
        funcionario1.setCredencial(credencialFunc);

        //Criando um Objeto PrestadorServico
        PrestadorServico prestador1 = new PrestadorServico(3, "Antonio", "04478937209", "65987345567", "12345-2", "Ar-Condicionado", morador1);

        //Criando um Objeto Visitante
        Visitante visitante1 = new Visitante(4, "Eduardo", "67890133224", "65987654433", morador1);

        //Criando um Objeto Registro
        Registro registro1 = new Registro(1);

        Registro registro2 = new Registro(2);
        //Criando um Objeto Controlador
        Controlador controlador = new Controlador();
        controlador.adicionarMorador(morador1);
        controlador.adicionarFuncionario(funcionario1);
        controlador.adicionarVisitante(visitante1);
        controlador.liberarPrestador(prestador1, registro1);
        controlador.liberarPrestador(prestador1, registro2);
        controlador.adicionarRegistro(registro1);
        controlador.adicionarRegistro(registro2);
        


        for(Morador m : controlador.getMoradores()){
            m.exibirDados();
            for(Veiculo v : m.getVeiculo()){
                v.exibirDadosVeiculo();
            }
        }
        for(Visitante v : controlador.getVisitantes()){
            v.exibirDados();
        }
        for(Funcionario f : controlador.getFuncionarios()){
            f.exibirDados();
        }
        for(PrestadorServico p : controlador.getPrestadores()){
            p.exibirDados();
        }
        for(Registro r : controlador.getRegistros()){
            r.exibirDadosRegistro();
        }
}}
