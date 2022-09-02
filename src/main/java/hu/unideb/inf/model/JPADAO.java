/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import static hu.unideb.inf.controller.FXMLAdatokController.valasztottOrvosID;
import static hu.unideb.inf.controller.FXMLOltasokController.oltasAzonosito;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author karal
 */
public class JPADAO implements DAO{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void save(Object o) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(o);
            entityManager.getTransaction().commit();
        }
        catch(Exception ex)
        {
            System.err.println("Hiba mentés közben!" + ex.getMessage());
        }
    }

    @Override
    public void update(Object o) {
        try
        {
            entityManager.getTransaction().begin();
            entityManager.merge(o);
            entityManager.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.err.println("Hiba update közben! Excetion: " + e.getMessage());
        }
    }

    @Override
    public void delete(Object o) {
        try
        {
            entityManager.getTransaction().begin();
            entityManager.remove(o);
            entityManager.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.err.println("Hiba törlés közben! Excetion: " + e.getMessage());
        }
    }
    
    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }  

    @Override
    public List<Orvos> getAllOrvos() {
        try
        {
            TypedQuery<Orvos> query = entityManager.createQuery("SELECT a FROM Orvos a", Orvos.class);
            List<Orvos> orvosok = query.getResultList();
            System.out.println("\nOrvosok\n");
            System.out.println(orvosok.size());
            return orvosok;
        }
        catch(Exception e)
        {
            System.err.println("Hiba orvosok lekérdezése közben! Excetion: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<OltasEsemeny> getAllOltasEsemeny() {
        try
        {
            TypedQuery<OltasEsemeny> query = entityManager.createQuery("SELECT a FROM OltasEsemeny a", OltasEsemeny.class);            
            List<OltasEsemeny> oltasEsemenyek = query.getResultList();
            System.out.println("\nOltasEsemenyek\n");
            System.out.println(oltasEsemenyek.size());
            return oltasEsemenyek;
        }
        catch(Exception e)
        {
            System.err.println("Hiba oltásesemények lekérdezése közben! Excetion: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Vakcina> getAllVakcina() {
        try{
            TypedQuery<Vakcina> query = entityManager.createQuery("SELECT a FROM Vakcina a", Vakcina.class);
            List<Vakcina> vakcinak = query.getResultList();
            System.out.println("\nvakcinák\n");
            System.out.println(vakcinak.size());
            return vakcinak;
        }
        catch(Exception e)
        {
            System.err.println("Hiba vakcinák lekérdezése közben! Excetion: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<OrvosBeosztas> GetOrvosBeosztas(Orvos o) {
        try{        
            TypedQuery<OrvosBeosztas> query = entityManager.createQuery("SELECT b FROM OrvosBeosztas b WHERE b.orvos.ID = " + o.getID(), OrvosBeosztas.class);
            //query.setParameter("id", o.getID());
            System.out.println(o.getID());

            List<OrvosBeosztas> beosztas = query.getResultList();
            return beosztas;
        }
        catch(Exception e)
        {
            System.err.println("Hiba orvosbeosztál lekérdezése közben! Excetion: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Vakcina GetVakcinaById(int id) {
        try{
            TypedQuery<Vakcina> query = entityManager.createQuery("SELECT a FROM Vakcina a WHERE ID = " + id, Vakcina.class);            
            return query.getResultList().get(0);        
        }
        catch(Exception e)
        {
            System.err.println("Hiba vakcina lekérdezése ID alapján  közben! Excetion: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Orvos GetOrvosById(int id) {
        try
        {
            TypedQuery<Orvos> o_query = entityManager.createQuery("SELECT a FROM Orvos a WHERE ID = " + id, Orvos.class);        
            return o_query.getSingleResult();
        }
        catch(Exception e)
        {
            System.err.println("Hiba orvos lekérdezése ID alapján  közben! Excetion: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<OltasEsemeny> GetUserOltasEsemenyei(int userid) {
        try
        {
            TypedQuery<OltasEsemeny> query = entityManager.createQuery("SELECT a FROM OltasEsemeny a WHERE user_id = " + userid, OltasEsemeny.class);
        
            return query.getResultList();
        }
        catch(Exception e)
        {
            System.err.println("Hiba felhasználó oltáseseményei lekérdezése közben! Excetion: " + e.getMessage());
            return null;
        }
    }

  
    @Override
    public FelhasznaloSzemely GetUserById(int userid) {
        try{
            TypedQuery<FelhasznaloSzemely> o_query = entityManager.createQuery("SELECT a FROM FelhasznaloSzemely a WHERE ID = " + userid, FelhasznaloSzemely.class);
            return o_query.getSingleResult();
        }
        catch(Exception e)
        {
            System.err.println("Hiba felhasználó lekérdezése (ID alapján) közben! Excetion: " + e.getMessage());
            return null;
        }
    }
    

    @Override
    public List<FelhasznaloSzemely> GetFelhasznaloSzemelyek() {
        TypedQuery<FelhasznaloSzemely> o_query = entityManager.createQuery("SELECT a FROM FelhasznaloSzemely a ", FelhasznaloSzemely.class);
        List<FelhasznaloSzemely> felhasznalok = o_query.getResultList();
        return felhasznalok;
    }
    
     @Override
    public List<OrvosBeosztas> GetOrvosIdopontok(int id) {
    try{        
        TypedQuery<OrvosBeosztas> query = entityManager.createQuery("SELECT b FROM OrvosBeosztas b WHERE ID = " + id, OrvosBeosztas.class);
        //query.setParameter("id", o.getID());


        List<OrvosBeosztas> idopont = query.getResultList();
        return idopont;
    }
    
    catch(Exception e)
    {
        System.err.println("Hiba orvosbeosztál lekérdezése közben! Excetion: " + e.getMessage());
        return null;
    }
    }

    @Override
    public List<VakcinaErtekeles> GetVakcinaErtekelesekByVakcinaID(int vakcinaID) {
        TypedQuery<VakcinaErtekeles> query = entityManager.createQuery("SELECT b FROM VakcinaErtekeles b WHERE vakcina_id = " + vakcinaID, VakcinaErtekeles.class);
        List<VakcinaErtekeles> result = query.getResultList();
        
        return result;
    }
}
