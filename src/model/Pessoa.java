package model;

public abstract class Pessoa {
    private int iD;
    private String nome, cpf, telefone;
    private static int contadorIdGeral = 1;


    public Pessoa(String nome, String cpf, String telefone){
        boolean isTrue = setID(contadorIdGeral);
        contadorIdGeral++;
        if(!isTrue) return;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public void exibirDados(){
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCPF());
        System.out.println("Telefone: " + getTel());
    }

    public int getID(){
        return iD;
    }

    public boolean setID(int iD){
        if(iD > 0){
            this.iD = iD;
            return true;
        }else{
            System.out.println("ERRO: O ID não pode ser negativo.");
            return false;
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
