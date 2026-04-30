package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Visitante {
    private Morador moradorVisitado;
    private LocalDateTime dataVisita;
    
    
    public Morador getMoradorVisitado() {
        return moradorVisitado;
    }
    public void setMoradorVisitado(Morador moradorVisitado) {
        this.moradorVisitado = moradorVisitado;
    }

    public String getDataVisita() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dataVisita.format(formatador);
    }
    public void setDataVisita() {
        this.dataVisita = LocalDateTime.now();
    }

}
