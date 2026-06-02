package model;

import java.util.ArrayList;
import java.util.List;

public class Morador extends Pessoa {
    
    private List<Credencial> credencial = new ArrayList<>();
    private List<Veiculo> veiculo = new ArrayList<>();
    private String enderecoMorador;


    public Morador(String nome, String cpf, String tel, String enderecoMorador) {
        super(nome, cpf, tel);
        this.enderecoMorador = enderecoMorador;
    }

    @Override
    public void exibirDados(){
        System.out.println("---Dados do Morador---");
        super.exibirDados();
        System.out.println("Endereço: " + getEnderecoMorador());
    }
    // consertar o erro da interface
    @Override
    public String toString() {
        // Altere "getNome()" e "getCPF()" para o nome real dos métodos na sua classe
        return this.getNome() + " - CPF: " + this.getCPF(); 
    }
    public void adicionarVeiculo(Veiculo novoVeiculo){
        if(novoVeiculo != null){
            this.veiculo.add(novoVeiculo);
            System.out.println("Veículo adicionado com sucesso ao morador: " + getNome());
        }else{
            System.out.println("ERRO: Tentativa de adicionar um veículo nulo.");
        }
    }

    public void setCredencial(Credencial credencial) {
        if(credencial != null){
            if(this.credencial.size() >= 2){
                System.out.println("ERRO: Morador já possui duas credenciais.");
            }else{
                this.credencial.add(credencial);
                System.out.println("Credencial adicionada com sucesso ao morador: " + getNome());
            }
        }else{
            System.out.println("ERRO: Tentativa de adicionar uma credencial nula.");
        }
    }

    public List<Veiculo> getVeiculo() {
        return veiculo;
    }

    public String getEnderecoMorador() {
        return enderecoMorador;
    }
    public void setEnderecoMorador(String enderecoMorador) {
        this.enderecoMorador = enderecoMorador;
    }



}
