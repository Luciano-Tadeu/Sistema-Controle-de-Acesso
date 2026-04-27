package model;

import java.util.ArrayList;
import java.util.List;

public class Morador extends Pessoa {
    
    private List<Credencial> credencial = new ArrayList<>();
    private List<Veiculo> veiculo = new ArrayList<>();
    private String enderecoMorador;


    public void exibirDadosMorador(){
        System.out.println("---Dados do Morador---");
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCPF());
        System.out.println("Telefone: " + getTel());
    }

    public void adicionarVeiculo(Veiculo novoVeiculo){
        if(novoVeiculo != null){
            this.veiculo.add(novoVeiculo);
            System.out.println("Veículo adicionado com sucesso ao morador: " + getNome());
        }else{
            System.out.println("ERRO: Tentativa de adicionar um veículo nulo.");
        }
    }

    public String getEnderecoMorador() {
        return enderecoMorador;
    }
    public void setEnderecoMorador(String enderecoMorador) {
        this.enderecoMorador = enderecoMorador;
    }



}
