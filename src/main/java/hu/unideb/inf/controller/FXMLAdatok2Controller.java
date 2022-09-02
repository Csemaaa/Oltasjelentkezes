package hu.unideb.inf.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static Extensions.Nevesitett_konst.FOGLALT1;
import static Extensions.Nevesitett_konst.FOGLALT2;
import static Extensions.Nevesitett_konst.FOGLALT3;
import static Extensions.Nevesitett_konst.FOGLALT4;
import static Extensions.Nevesitett_konst.FOGLALT5;
import static Extensions.Nevesitett_konst.FOGLALT6;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Extensions.SceneExtentions;
import static hu.unideb.inf.controller.FXMLAdatokController.fontos;
import static hu.unideb.inf.controller.FXMLAdatokController.formatter;
import static hu.unideb.inf.controller.FXMLAdatokController.idopontok;
import static hu.unideb.inf.controller.FXMLAdatokController.index;
import static hu.unideb.inf.controller.FXMLAdatokController.kiegeszit;
import static hu.unideb.inf.controller.FXMLAdatokController.valasztottIdopont;
import static hu.unideb.inf.controller.FXMLAdatokController.valasztottOrvos;
import static hu.unideb.inf.controller.FXMLAdatokController.valasztottOrvosID;
import static hu.unideb.inf.controller.FXMLOltasokController.oltasAzonosito;
import static hu.unideb.inf.controller.FXMLOltasokController.oltasNev;
import static hu.unideb.inf.controller.indexController.belepett;
import hu.unideb.inf.model.FelhasznaloSzemely;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.OltasEsemeny;
import hu.unideb.inf.model.Orvos;
import hu.unideb.inf.model.OrvosBeosztas;

import hu.unideb.inf.model.Vakcina;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author Tamás �?dám
 */
public class FXMLAdatok2Controller extends SceneExtentions implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Text oltasTipus;

    @FXML
    private Text valasztOrvos;
    
    @FXML
    private Text idopontText;
   
    @FXML
    private CheckBox ellenorzes;
    
    @FXML
    private Text hiba;
    
    @FXML
    private Text nevMezo;

    @FXML
    private Text tajMezo;

    @FXML
    private Text szulinapMezo;

    @FXML
    private Text nemMezo;
    
    

    
    @FXML
    void backToTheDoctors(ActionEvent event) throws IOException{
        ChangeScene(event, "FXMLAdatok");
    }
    
    void kuldesKiegeszito()
    {
        if (kiegeszit == 1)
        {
            FOGLALT1.add(index);
        }
        else if (kiegeszit == 2)
        {
            FOGLALT2.add(index);
        }
        else if (kiegeszit == 3)
        {
            FOGLALT3.add(index);
        }
        else if (kiegeszit == 4)
        {
            FOGLALT4.add(index);
        }
        else if (kiegeszit == 5)
        {
            FOGLALT5.add(index);
        }
        else if (kiegeszit == 6)
        {
            FOGLALT6.add(index);
        }
    }
    
    
    @FXML
    void kuldes(ActionEvent event) throws IOException {
        JPADAO dao = new JPADAO();
        if (!ellenorzes.isSelected())
        {
            hiba.setVisible(true);
        }
        else
        {
            OltasEsemeny oltas = new OltasEsemeny();
            oltas.setIdopont(idopontok.get(index));
            //FOGLALT1.add(index);
            kuldesKiegeszito();

            //oltFOGLALT1.add(index);as.setMegkapta(true);
            
            //final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
            //final EntityManager entityManager = entityManagerFactory.createEntityManager();
            //TypedQuery<Vakcina> query = entityManager.createQuery("SELECT a FROM Vakcina a WHERE ID = " + oltasAzonosito, Vakcina.class);
            //List<Vakcina> vakcinak = query.getResultList();
            //List<Vakcina> vakcinak = dao.getVakcinaById(oltasAzonosito);
//          System.out.println(vakcinak.get(0).getNev());
            //TypedQuery<Orvos> o_query = entityManager.createQuery("SELECT a FROM Orvos a WHERE ID = " + valasztottOrvosID, Orvos.class);
            //List<Orvos> orvosok = o_query.getResultList();
           // List<Orvos> orvosok = dao.GetOrvosById(valasztottOrvosID);

            //Vakcina vakcina = vakcinak.get(0);
            //vakcina.beoltas.add(oltas);
            List<Orvos> orvosok = dao.getAllOrvos();
            List<OrvosBeosztas> beosztasok = dao.GetOrvosBeosztas(orvosok.get(fontos));
            List<Integer> ids = new ArrayList<>();
            for(int j = 0; j < beosztasok.size(); j++)
            {
                ids.add(beosztasok.get(j).getID());
            }


            for(int i = 0; i < beosztasok.size(); i++)
            {
                String formattedDateTime = beosztasok.get(i).getKezdesIdo().format(formatter).replace("T", " ");

                if(formattedDateTime.equals(valasztottIdopont))
                {
                    int id = ids.get(i);
                    System.out.println("VALASZTOTT");
                    System.out.println(formattedDateTime);
                    List<OrvosBeosztas> ido = dao.GetOrvosIdopontok(id);
                    OrvosBeosztas azonosito = ido.get(0);
                    dao.delete(azonosito);
                }
                //System.out.println(formattedDateTime);
                //System.out.println(valasztottIdopont);

            }
        
            System.out.println("oltásAzonosító: " + oltasAzonosito);
            oltas.vakcina = dao.GetVakcinaById(oltasAzonosito);
            oltas.orvos = dao.GetOrvosById(valasztottOrvosID);
            oltas.user = dao.GetUserById(belepett.getID());
            //System.out.println(vakcina.beoltas);

            dao.save(oltas);
            //dao.update(vakcina);
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sikeres jelentkezés");
            alert.setHeaderText(null);
            alert.setContentText("Ön sikeresen jelentkezett az oltásra!");

            alert.showAndWait();
 
            
            ChangeScene(event, "FXMLindexScene");
        }

    }
    
    @FXML
    void visszaallit(ActionEvent event) {
        hiba.setVisible(false);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        oltasTipus.setText(oltasAzonosito + " / " + oltasNev);
        valasztOrvos.setText(valasztottOrvos);
        idopontText.setText(valasztottIdopont);
        JPADAO dao = new JPADAO();
        FelhasznaloSzemely aktualisUser = dao.GetUserById(belepett.getID());
        nevMezo.setText(aktualisUser.getNev());
        tajMezo.setText("" + aktualisUser.getTAJ());
        szulinapMezo.setText(aktualisUser.getSzuletesiDatum().toString());
        nemMezo.setText(aktualisUser.getNem().toString());
        
    }    
    
     SceneExtentions sc = new SceneExtentions();
    
    @FXML
    void indexmenuClicked(ActionEvent event) throws IOException {        
        sc.ChangeScene(event, "FXMLindexScene");
    }

    @FXML
    void jelentkezesmenuclicked(ActionEvent event) throws IOException {
        sc.ChangeScene(event, "FXMLOltasok");
    }

    @FXML
    void kilelpesmenuclicked(ActionEvent event) throws IOException {
        sc.ChangeScene(event, "FXMLLoginScene");        
    }

    @FXML
    void orvosokmenuclicked(ActionEvent event) throws IOException {
        sc.ChangeScene(event, "OrvosOsszesitoLap");
    }

    @FXML
    void vakcinainfomenuclicked(ActionEvent event) throws IOException {
        sc.ChangeScene(event, "FXMLVakcinak");
    }


    
}
