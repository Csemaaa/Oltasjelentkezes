/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public class KorTortenet {

    private int ID;
    private LocalDateTime idopont;
    private boolean megkapta;
    private String Vakcina;
    private String Orvos;

    public String getVakcina() {
        return Vakcina;
    }

    public void setVakcina(String Vakcina) {
        this.Vakcina = Vakcina;
    }

    public String getOrvos() {
        return Orvos;
    }

    public void setOrvos(String Orvos) {
        this.Orvos = Orvos;
    }


    
    public int getID() {
        return ID;
    }   
    
    public LocalDateTime getIdopont() {
        return idopont;
    }

    public void setIdopont(LocalDateTime idopont) {
        this.idopont = idopont;
    }

    public boolean isMegkapta() {
        return megkapta;
    }

    public void setMegkapta(boolean megkapta) {
        this.megkapta = megkapta;
    }

    
}
