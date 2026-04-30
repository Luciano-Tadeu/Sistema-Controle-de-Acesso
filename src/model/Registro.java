package model;

import java.time.LocalDateTime;

public class Registro {
    private int idReg;
    private LocalDateTime dataHora;
    private boolean acessoLiberado;

    public int getIdReg() {
        return idReg;
    }
    public void setIdReg(int idReg) {
        this.idReg = idReg;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora() {
        this.dataHora = LocalDateTime.now();
    }

    public boolean isAcessoLiberado() {
        return acessoLiberado;
    }
    public void setAcessoLiberado(boolean acessoLiberado) {
        this.acessoLiberado = acessoLiberado;
    }
}
