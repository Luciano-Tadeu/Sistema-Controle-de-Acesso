package main;
import model.Credencial;
import model.Morador;
import model.Pessoa;
import model.Veiculo;

public class App {
    public static void main(String[] args) throws Exception {
        Morador morador1 = new Morador();
        morador1.setNome("Marcos");
        morador1.setCPF("055");
        morador1.setID(2);
        morador1.setTel("65");
        morador1.setEnderecoMorador("Coophamil");
        morador1.exibirDadosMorador();
    }
}
