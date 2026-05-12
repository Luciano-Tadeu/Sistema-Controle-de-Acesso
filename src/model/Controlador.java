package model;

import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private List<Morador> moradores;
    private List<Funcionario> funcionarios;
    private List<PrestadorServico> prestadores;
    private List<Visitante> visitantes;


    public Controlador() {
        this.moradores = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.prestadores = new ArrayList<>();
        this.visitantes = new ArrayList<>();
    }

    public void adicionarMorador(Morador morador) {
        this.moradores.add(morador);
    }
    
    public void adicionarFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    public void adicionarPrestador(PrestadorServico prestador) {
        this.prestadores.add(prestador);
    }

    public void adicionarVisitante(Visitante visitante) {
        this.visitantes.add(visitante);
    }

    public List<Morador> getMoradores() {
        return moradores;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public List<PrestadorServico> getPrestadores() {
        return prestadores;
    }

    public List<Visitante> getVisitantes() {
        return visitantes;
    }

    public boolean verificarAcesso(Credencial credencial) {
        if (credencial == null) return false;
        return credencial.isAtiva();
    }
}