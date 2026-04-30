package model;


import java.time.LocalTime;


public class PrestadorServico extends Pessoa{
    private String cnh;
    private String tipoServico;
    private String enderecoMoradorP;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;

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

    public String getEnderecoMoradorP() {
        return enderecoMoradorP;
    }
    public void setEnderecoMoradorP(String enderecoMoradorP) {
        this.enderecoMoradorP = enderecoMoradorP;
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
