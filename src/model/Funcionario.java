package model;

public class Funcionario extends Pessoa {
    private String funcao;
    private Credencial credencial;

    public Funcionario(int id, String nome, String cpf, String tel, String funcao, Credencial credencial) {
        setID(id);
        setNome(nome);
        setCPF(cpf);
        setTel(tel);
        this.funcao = funcao;
        this.credencial = credencial;
    }

        public void exibirDadosFuncionario(){
        System.out.println("---Dados do Funcionário---");
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCPF());
        System.out.println("Telefone: " + getTel());
        System.out.println("Função: " + getFuncao());
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
