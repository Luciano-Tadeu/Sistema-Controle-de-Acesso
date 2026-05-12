package model;

import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private List<Morador> moradores;
    private List<Funcionario> funcionarios;
    private List<PrestadorServico> prestadores;
    private List<Visitante> visitantes;
    private List<Registro> registros;


    public Controlador() {
        this.moradores = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.prestadores = new ArrayList<>();
        this.visitantes = new ArrayList<>();
        this.registros = new ArrayList<>();
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

    public void adicionarRegistro(Registro registro){
        this.registros.add(registro);
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

    public List<Registro> getRegistros() {
        return registros;
    }

    public boolean liberarPrestador(PrestadorServico p, Registro r){
        if(p.getHoraEntrada() == null){
            p.setHoraEntrada();
            r.setDataHora();
            r.setAcessoLiberado(true);
            return true;
        }else if(p.getHoraSaida() == null){
            p.setHoraSaida();
            r.setDataHora();
            r.setAcessoLiberado(true);
            return true;
        }
            r.setDataHora();
            r.setAcessoLiberado(false);
        return false;
    }

    public boolean verificarAcesso(Credencial credencial) {
        if (credencial == null) return false;
        return credencial.isAtiva();
    }
}