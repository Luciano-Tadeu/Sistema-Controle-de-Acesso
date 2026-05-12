package model;


import java.time.LocalTime;


public class PrestadorServico extends Pessoa{
    private String cnh;
    private String tipoServico;
    private String enderecoMoradorP;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;

    public PrestadorServico(int id, String nome, String cpf, String tel, String cnh, String tipoServico, String enderecoMoradorP) {
        setID(id);
        setNome(nome);
        setCPF(cpf);
        setTel(tel);
        this.cnh = cnh;
        this.tipoServico = tipoServico;
        this.enderecoMoradorP = enderecoMoradorP;
    }

        public void exibirDadosPrestador(){
        System.out.println("---Dados do Prestador de Serviço---");
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCPF());
        System.out.println("Telefone: " + getTel());
        System.out.println("Endereço Morador: " + getEnderecoMoradorP());
        System.out.println("Serviço: " + getTipoServico());
        System.out.println("Hora Entrada: " + getHoraEntrada());
        System.out.println("Hora Saida: " + getHoraSaida());
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
