package model;

import java.util.ArrayList;
import java.util.List;

public class Morador extends Pessoa {
    
    private List<Credencial> credencial = new ArrayList<>();
    private List<Veiculo> veiculo = new ArrayList<>();
    private String enderecoMorador;


    public Morador(int id, String nome, String cpf, String tel, String enderecoMorador) {
        setID(id);
        setNome(nome);
        setCPF(cpf);
        setTel(tel);
        this.enderecoMorador = enderecoMorador;
    }

    public void exibirDadosMorador(){
        System.out.println("---Dados do Morador---");
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCPF());
        System.out.println("Telefone: " + getTel());
        System.out.println("Endereço: " + getEnderecoMorador());
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

    public String getEnderecoMorador() {
        return enderecoMorador;
    }
    public void setEnderecoMorador(String enderecoMorador) {
        this.enderecoMorador = enderecoMorador;
    }



}
