package main;
import java.util.ResourceBundle.Control;

import model.Controlador;
import model.Credencial;
import model.Morador;
import model.Pessoa;
import model.Veiculo;

public class App {
    public static void main(String[] args) throws Exception {
        Credencial credencial1 = new Credencial();
        Veiculo veiculo1 = new Veiculo("MVZ5886", "Toyota Etios", "Prata");

        Morador morador1 = new Morador(1, "Marcos", "05518955197", "65981077777", "Rua 29, 19");
        morador1.setCredencial(credencial1);
        morador1.adicionarVeiculo(veiculo1);

        Controlador controlador = new Controlador();
        controlador.adicionarMorador(morador1);
        System.out.println(controlador.getMoradores());
        morador1.exibirDadosMorador();
}}
