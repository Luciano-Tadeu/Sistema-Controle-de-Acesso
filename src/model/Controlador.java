package model;

import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private List<Morador> moradores = new ArrayList<>();
    private List<Funcionario> funcionarios = new ArrayList<>();
    private List<PrestadorServico> prestadores = new ArrayList<>();
    private List<Visitante> visitantes = new ArrayList<>();

    public List<Morador> getMoradores() {
        return moradores;
    }
    public void setMoradores(Morador morador) {
        this.moradores.add(morador);
    }
    
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    public void setFuncionarios(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    public List<PrestadorServico> getPrestadores() {
        return prestadores;
    }
    public void setPrestadores(PrestadorServico prestador) {
        this.prestadores.add(prestador);
    }

    public List<Visitante> getVisitantes() {
        return visitantes;
    }
    public void setVisitantes(Visitante visitante) {
        this.visitantes.add(visitante);
    }

    public boolean verificarAcesso(Credencial credencial){
        return credencial.isAtiva();
    }

}
