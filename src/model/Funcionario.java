package model;

public class Funcionario extends Pessoa {
    private String funcao;
    private Credencial credencial;

    public Funcionario(String nome, String cpf, String tel, String funcao) {
        super(nome, cpf, tel);
        this.funcao = funcao;
    }

    @Override
    public void exibirDados(){
        System.out.println("---Dados do Funcionário---");
        super.exibirDados();
        System.out.println("Função: " + getFuncao());
    }

    @Override
    public String toString() {
        return "Nome: " + super.getNome() + " | CPF: " + super.getCPF() + " | Telefone: " + super.getTel() + " | Função: " + this.getFuncao();
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
