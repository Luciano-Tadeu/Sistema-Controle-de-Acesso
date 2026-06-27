package model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrestadorServico extends Pessoa{
    private String cnh;
    private String tipoServico;
    private Morador morador;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    public PrestadorServico(String nome, String cpf, String tel, String cnh, String tipoServico, Morador morador) {
        super(nome, cpf, tel);
        this.cnh = cnh;
        this.tipoServico = tipoServico;
        this.morador = morador;
    }

    @Override
    public String toString() {
        return "Nome: " + super.getNome() + " | CPF: " + super.getCPF() + " | Telefone: " + super.getTel() + " | CNH: " + this.getCnh() + " | Tipo de Serviço: " + this.getTipoServico() + " | Morador (Endereço): " + this.getMorador().getEnderecoMorador();
    }

    public String getCnh() {
        return cnh;
    }
    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getTipoServico() {
        return tipoServico;
    }
    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Morador getMorador() {
        return this.morador;
    }
    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public void setHoraEntrada(LocalDateTime d){
        this.horaEntrada = d;
    }
    public void setHoraSaida(LocalDateTime d){
        this.horaSaida = d;
    }
    public LocalDateTime getHoraEntrada(){
        return this.horaEntrada;
    }
    public LocalDateTime getHoraSaida(){
        return this.horaSaida;  
    }

    public String getHoraFormatada(){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss / ");
        String data =  this.getHoraEntrada() == null? "- / " : this.getHoraEntrada().format(formatador);
        formatador = DateTimeFormatter.ofPattern("HH:mm:ss");
        data += this.getHoraSaida() == null? "-" : this.getHoraSaida().format(formatador);

        return data;
    }
}
