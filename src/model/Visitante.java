package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Visitante extends Pessoa{
    private Morador moradorVisitado;
    private LocalDateTime dataVisita;
    
    public Visitante(String nome, String cpf, String tel, Morador moradorVisitado) {
        super(nome, cpf, tel);
        this.moradorVisitado = moradorVisitado;
        setDataVisita();
    }

    @Override
    public void exibirDados(){
        System.out.println("---Dados do Visitante---");
        super.exibirDados();
        System.out.println("Morador Visitado: " + moradorVisitado.getNome());
    }

    public Morador getMoradorVisitado() {
        return moradorVisitado;
    }
    public void setMoradorVisitado(Morador moradorVisitado) {
        this.moradorVisitado = moradorVisitado;
    }

    public String getDataVisita() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dataVisita.format(formatador);
    }
    public void setDataVisita() {
        this.dataVisita = LocalDateTime.now();
    }

}
