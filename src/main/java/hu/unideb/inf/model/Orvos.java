/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name="Orvosok")
public class Orvos {
    @Id
    @GeneratedValue
    private int ID;
    private String nev;
    private float ertekeles;
    private int ertekeles_dbszam;

    public int getErtekeles_dbszam() {
        return ertekeles_dbszam;
    }

    public void setErtekeles_dbszam(int ertekeles_dbszam) {
        this.ertekeles_dbszam = ertekeles_dbszam;
    }

    public int getID() {
        return ID;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public float getErtekeles() {
        return ertekeles;
    }

    public void setErtekeles(float ertekeles) {
        this.ertekeles = ertekeles;
    }

    @OneToMany(mappedBy = "orvos")
    public Set<OltasEsemeny> beoltas = new HashSet<>();
    
    @OneToMany(mappedBy = "orvos")
    public Set<OrvosBeosztas> beosztas = new HashSet<>();
}
