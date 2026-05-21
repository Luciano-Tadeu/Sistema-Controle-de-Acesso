package model;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PrestadorServico extends Pessoa{
    private String cnh;
    private String tipoServico;
    private Morador morador;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;

    public PrestadorServico(int id, String nome, String cpf, String tel, String cnh, String tipoServico, Morador morador) {
        super(id, nome, cpf, tel);
        this.cnh = cnh;
        this.tipoServico = tipoServico;
        this.morador = morador;
    }

    @Override
    public void exibirDados(){
        System.out.println("---Dados do Prestador de Serviço---");
        super.exibirDados();
        System.out.println("Endereço Morador: " + morador.getEnderecoMorador());
        System.out.println("Serviço: " + getTipoServico());
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataEntradaBonita = this.getHoraEntrada().format(formatador);
        System.out.println("Hora Entrada: " + dataEntradaBonita);
        String dataSaidaBonita = this.getHoraSaida().format(formatador);
        System.out.println("Hora Saida: " + dataSaidaBonita);
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

    public void setHoraEntrada(){
        this.horaEntrada = LocalTime.now();
    }
    public void setHoraSaida(){
        this.horaSaida = LocalTime.now();
    }
    public LocalTime getHoraEntrada(){
        return this.horaEntrada;
    }
    public LocalTime getHoraSaida(){
        return this.horaSaida;  
    }
}
