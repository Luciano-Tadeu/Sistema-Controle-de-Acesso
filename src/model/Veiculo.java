package model;

public class Veiculo {
    private String placa, modelo, cor;

    public Veiculo(String placa, String modelo, String cor) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
    }

    public String exibirDadosVeiculo(){
        return "\nPlaca: " + this.placa + "\nModelo: " + this.modelo + "\nCor: " + this.cor;
    }

    @Override
    public String toString() {
        return "Placa: " + this.getPlaca() + " | Modelo: " + this.getModelo() + " | Cor: " + this.getCor();
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
