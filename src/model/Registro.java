<<<<<<< HEAD
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Registro {
    private int idReg;
    private LocalDateTime dataHora;
    private boolean acessoLiberado;

    public Registro(int idReg) {
        this.idReg = idReg;
    }

    public void exibirDadosRegistro(){
        System.out.printf("---Registro %d ---\n", getIdReg());
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataBonita = this.getDataHora().format(formatador);
        System.out.println("Data: " + dataBonita);
        System.out.printf("Acesso: %s\n", isAcessoLiberado()? "Liberado" : "Negado");
    }

    public int getIdReg() {
        return idReg;
    }
    public void setIdReg(int idReg) {
        this.idReg = idReg;
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
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
=======
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Registro {
    private int idReg;
    private LocalDateTime dataHora;
    private boolean acessoLiberado;

    public Registro(int idReg) {
        this.idReg = idReg;
    }

    public void exibirDadosRegistro(){
        System.out.printf("---Registro %d ---\n", getIdReg());
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataBonita = this.getDataHora().format(formatador);
        System.out.println("Data: " + dataBonita);
        System.out.printf("Acesso: %s\n", isAcessoLiberado()? "Liberado" : "Negado");
    }

    public int getIdReg() {
        return idReg;
    }
    public void setIdReg(int idReg) {
        this.idReg = idReg;
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
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
>>>>>>> a149c82ec1b23948276b1a194003a2c35bdaee2c
