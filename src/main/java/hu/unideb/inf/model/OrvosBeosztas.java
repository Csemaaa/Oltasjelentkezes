/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import Extensions.Nevesitett_konst;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author admin
 * a tábla egy rekordja egy időintervallumot tartalmaz
 */
@Entity
@Table(name="OrvosBeosztasok")
public class OrvosBeosztas {
    @Id
    @GeneratedValue
    private int ID;
    private LocalDateTime  kezdesIdo;
    private LocalDateTime  vegzesIdo;
    
    @ManyToOne
    @JoinColumn(name = "orvos_id", referencedColumnName = "ID")
    public Orvos orvos;
    
    public int getID() {
        return ID;
    }


    public LocalDateTime getKezdesIdo() {
        return kezdesIdo;
    }

    public void setKezdesIdo(LocalDateTime kezdesIdo) {
        this.kezdesIdo = kezdesIdo;
    }

    public LocalDateTime getVegzesIdo() {
        return vegzesIdo;
    }

    public void setVegzesIdo(LocalDateTime vegzesIdo) {        
        this.vegzesIdo = vegzesIdo;
    }

    
}
