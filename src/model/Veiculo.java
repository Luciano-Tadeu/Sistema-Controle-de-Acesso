package model;

public class Veiculo {
    private String placa, modelo, cor;

    public Veiculo(String placa, String modelo, String cor) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
    }
    //consertar o erro da interface
    @Override
    public String toString() {
        // Altere para os métodos reais da sua classe
        return this.getModelo() + " | Placa: " + this.getPlaca() + " | Cor: " + this.getCor();
    }
    public void exibirDadosVeiculo(){
        System.out.println("---Dados do Veículo---");
        System.out.println("Placa: " + getPlaca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Cor: " + getCor());
    }

    public String getPlaca(){
        return placa;
    }
    public void setPlaca(String placa){
        this.placa = placa;
    }

    public String getModelo(){
        return modelo;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public String getCor(){
        return cor;
    }
    public void setCor(String cor){
        this.cor = cor;
    }
}
