package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Visitante extends Pessoa{
    private Morador moradorVisitado;
    private LocalDateTime dataVisita;
    
    public Visitante(int id, String nome, String cpf, String tel, Morador moradorVisitado) {
        setID(id);
        setNome(nome);
        setCPF(cpf);
        setTel(tel);
        this.moradorVisitado = moradorVisitado;
    }

    public void exibirDadosVisitante(){
        System.out.println("---Dados do Visitante---");
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCPF());
        System.out.println("Telefone: " + getTel());
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
