package model;

public class Funcionario extends Pessoa {
    private String funcao;
    private Credencial credencial;

    public Funcionario(String funcao, Credencial credencial) {
        this.funcao = funcao;
        this.credencial = credencial;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        if(credencial != null){
            this.credencial = credencial;
            System.out.println("Credencial adicionada com sucesso ao funcionario: " + getNome());
        }else{
            System.out.println("ERRO: Tentativa de adicionar uma credencial nula.");
        }
    }

}
