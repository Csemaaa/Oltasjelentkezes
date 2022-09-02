/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name="OltasEsemenyek")
public class OltasEsemeny {
    @Id
    @GeneratedValue
    private int ID;
    private LocalDateTime idopont;
    private boolean megkapta;
    private boolean vizsgalva;

    public boolean isVizsgalva() {
        return vizsgalva;
    }

    public void setVizsgalva(boolean vizsgalva) {
        this.vizsgalva = vizsgalva;
    }

    @ManyToOne
    @JoinColumn(name = "orvos_id", referencedColumnName = "ID")
    public Orvos orvos;
    
    @ManyToOne
    @JoinColumn(name = "vakcina_id", referencedColumnName = "ID")
    public Vakcina vakcina;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
    public FelhasznaloSzemely user;
    
    
    public int getID() {
        return ID;
    }   


    public void setID(int ID) {
        this.ID = ID;
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
