<<<<<<< HEAD
package model;

import java.util.UUID;

public class Credencial {

    private String codigoID;
    private boolean ativa;
 
    public Credencial(){
        this.codigoID = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.ativa = true;
    }

    public String getCodigoID() {
        return codigoID;
    }

    public boolean isAtiva() {
        return ativa;
    }
    public void ativar() {
        this.ativa = true;
    }
    public void desativar() {
        this.ativa = false;
    }

}
=======
package model;

import java.util.UUID;

public class Credencial {

    private String codigoID;
    private boolean ativa;
 
    public Credencial(){
        this.codigoID = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.ativa = true;
    }

    public String getCodigoID() {
        return codigoID;
    }

    public boolean isAtiva() {
        return ativa;
    }
    public void ativar() {
        this.ativa = true;
    }
    public void desativar() {
        this.ativa = false;
    }

}
>>>>>>> a149c82ec1b23948276b1a194003a2c35bdaee2c
