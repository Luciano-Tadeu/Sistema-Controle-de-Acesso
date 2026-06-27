package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Visitante extends Pessoa{
    private Morador moradorVisitado;
    private LocalDateTime dataVisita;
    
    public Visitante(String nome, String cpf, String tel, Morador moradorVisitado) {
        super(nome, cpf, tel);
        this.moradorVisitado = moradorVisitado;
        this.dataVisita = LocalDateTime.now();
    }

    @Override
    public void exibirDados(){
        System.out.println("---Dados do Visitante---");
        super.exibirDados();
        System.out.println("Morador Visitado: " + moradorVisitado.getNome());
    }

    @Override
    public String toString() {
        return "Nome: " + super.getNome() + " | CPF: " + super.getCPF() + " | Telefone: " + super.getTel() + "\nMorador Visitado: " + this.getMoradorVisitado().getNome() + " | Morador (Endereço): " + this.getMoradorVisitado().getEnderecoMorador();
    }

    public Morador getMoradorVisitado() {
        return moradorVisitado;
    }
    public void setMoradorVisitado(Morador moradorVisitado) {
        this.moradorVisitado = moradorVisitado;
    }

    public String getDataVisitaFormatada() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        return this.dataVisita.format(formatador);
    }

    public LocalDateTime getDataVisita() {
        return this.dataVisita;
    }

    public void setDataVisita(LocalDateTime data) {
        this.dataVisita = data;
    }

}
