package model;

public class Pessoa {
    private int iD;
    private String nome, cpf, telefone;

    public int getID(){
        return iD;
    }
    public void setID(int iD){
        if(iD > 0){
            this.iD = iD;
        }else{
            System.out.println("ERRO: O ID não pode ser negativo.");
        }
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCPF(){
        return cpf;
    }
    public void setCPF(String cpf){
        this.cpf = cpf;
    }

    public String getTel(){
        return telefone;
    }
    public void setTel(String telefone){
        this.telefone = telefone;
    }
}
