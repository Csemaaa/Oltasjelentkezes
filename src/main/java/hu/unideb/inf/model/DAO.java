/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author karal
 */
public interface DAO extends AutoCloseable{
    public void save(Object o);
    public void update (Object o);
    public void delete (Object o);
    public List<Orvos> getAllOrvos();
    public List<OltasEsemeny> getAllOltasEsemeny();
    public List<Vakcina> getAllVakcina();

    public List<OrvosBeosztas> GetOrvosBeosztas(Orvos o);
    
    /*public List<Vakcina> getVakcinaById(int azonosito);
    public List<Orvos> getOrvosById(int azonosito);    */
    
    public Vakcina GetVakcinaById(int id);
    public Orvos GetOrvosById(int id);
    public List<OltasEsemeny> GetUserOltasEsemenyei(int userid);    
    public FelhasznaloSzemely GetUserById(int userid);
    public List<FelhasznaloSzemely> GetFelhasznaloSzemelyek();
    public List<OrvosBeosztas> GetOrvosIdopontok(int id);  
    public List<VakcinaErtekeles> GetVakcinaErtekelesekByVakcinaID(int vakcinaID);
}
