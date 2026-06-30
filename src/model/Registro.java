package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Registro {
    private int idReg;
    private String usuario;
    private String mensagem;
    private LocalDateTime dataHora;

    public Registro(String user, String men) {
        this.usuario = user;
        this.mensagem = men;
        setDataHora(LocalDateTime.now());
    }

    public String getDataHoraFormatada(){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        String dataBonita = this.getDataHora().format(formatador);
        return dataBonita;
    }

    public int getId() {
        return idReg;
    }

    public void setId(int id){
        this.idReg = id;
    }

    public String getMensagem(){
        return this.mensagem;
    }

    public String getUsuario(){
        return this.usuario;
    }

    public void setIdReg(int idReg) {
        this.idReg = idReg;
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }
    public void setDataHora(LocalDateTime d) {
        this.dataHora = d;
    }

}
