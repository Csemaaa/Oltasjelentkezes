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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Extensions.SceneExtentions;
import static hu.unideb.inf.controller.FXMLOltasokController.oltasAzonosito;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.OltasEsemeny;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import hu.unideb.inf.model.Orvos;
import hu.unideb.inf.model.OrvosBeosztas;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tooltip;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author 
 */
public class FXMLAdatokController extends SceneExtentions implements Initializable {

    /**
     * Initializes the controller class.
     */
    JPADAO dao = new JPADAO();
    
    //ObservableList<String> idopontokLista = FXCollections.observableArrayList("2021.01.01", "2020. 03. 11", "2022. 02. 11");
   
    

    
    public static String valasztottOrvos;
    public static int valasztottOrvosID;
    public static String valasztottIdopont;
    public static List<LocalDateTime> idopontok = new ArrayList<>();
    public static int index;
    public static int kiegeszit; // adatok2-ben hogy melyik foglalt-ba töltse az indexet
    
    List<Orvos> orvosok = dao.getAllOrvos();
    public static int fontos;
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    
    @FXML
    private Button nextbtn;
    
    @FXML
    private RadioButton dr1;
    
    
    @FXML
    private ToggleGroup doctors;

    @FXML
    private RadioButton dr2;

    @FXML
    private RadioButton dr3;

    @FXML
    private RadioButton dr4;

    @FXML
    private RadioButton dr5;
    
    @FXML
    private RadioButton dr6;
    
    
    @FXML
    private Text tst2;
    
    @FXML
    private ChoiceBox<String> idopontValaszto;
    
    @FXML
    void backToTheVaccine(ActionEvent event) throws IOException {
        ChangeScene(event, "FXMLOltasok");
    }
    
    @FXML
    void nextPage(ActionEvent event) throws IOException {
        valasztottIdopont = idopontValaszto.getValue();

        System.out.println("Datum " + valasztottIdopont);
        

        //System.out.println("index " + index);
        ChangeScene(event, "FXMLAdatok2");
    }
    
    void idopontListazasKiegeszito(int elem)
    {

        if (elem == 0)
        {
            for(int j = 0; j < idopontok.size(); j++)
            {
                if (FOGLALT1.indexOf(j) != -1 )
                {
                    idopontok.remove(j);

                }
            }
        }
        else if (elem == 1)
        {
            for(int j = 0; j < idopontok.size(); j++)
            {
                if (FOGLALT2.indexOf(j) != -1 )
                {
                    idopontok.remove(j);

                }
            }
        }
        else if (elem == 2)
        {
            for(int j = 0; j < idopontok.size(); j++)
            {
                if (FOGLALT3.indexOf(j) != -1 )
                {
                    idopontok.remove(j);

                }
            }            
        }
        else if (elem == 3)
        {
            for(int j = 0; j < idopontok.size(); j++)
            {
                if (FOGLALT4.indexOf(j) != -1 )
                {
                    idopontok.remove(j);

                }
            }            
        }
        else if (elem == 4)
        {
            for(int j = 0; j < idopontok.size(); j++)
            {
                if (FOGLALT5.indexOf(j) != -1 )
                {
                    idopontok.remove(j);

                }
            }            
        }
        else if (elem == 5)
        {
            for(int j = 0; j < idopontok.size(); j++)
            {
                if (FOGLALT6.indexOf(j) != -1 )
                {
                    idopontok.remove(j);

                }
            }            
        }
    }
    
    
    void idopontListazas(int elem)
    {

            idopontok.clear();

            List<OrvosBeosztas> beosztasok = dao.GetOrvosBeosztas(orvosok.get(elem));

            LocalDateTime kezdes = beosztasok.get(0).getKezdesIdo();
            LocalDateTime vegzes = beosztasok.get(0).getVegzesIdo();
            
            
            for(int i = 0; i < beosztasok.size(); i++)
            {
                idopontok.add(beosztasok.get(i).getKezdesIdo());
            }

            /*
            int i = 30;
            int ora = kezdes.getHour();
            while(ora != vegzes.getHour())//kezdes.plusMinutes(i).getMinute() != vegzes.getMinute())
            {
                idopontok.add(kezdes.plusMinutes(i));
                i += 30;
                if (kezdes.plusMinutes(i).getMinute() == 0)
                {
                    ora ++;
                }

            }

            idopontok.add(vegzes);
*/
           // System.out.println("kezdes: " + kezdes);

            //System.out.println("vegzes: " + vegzes);
            //System.out.println("idopontok: " +  idopontok);

            idopontListazasKiegeszito(elem);
            
            
            List<String> idopontok_string = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for(LocalDateTime e: idopontok)
            {
                String formattedDateTime = e.format(formatter);
                idopontok_string.add(formattedDateTime);
            }
            //System.out.println(idopontok_string);
            

            ObservableList lista =  FXCollections.observableArrayList(idopontok_string);
            
            idopontValaszto.setItems(lista);
            idopontValaszto.getSelectionModel().selectFirst();
            idopontValaszto.setTooltip(new Tooltip("Válassz egy időpontot!"));
            idopontValaszto.getSelectionModel().selectedIndexProperty().addListener( new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                    index = t1.intValue();
                    //System.out.println(index);
                }
            });
            
        
    }

                    
    
    @FXML
    void idopontok(ActionEvent event) {
        
      
        idopontValaszto.setVisible(true);
        tst2.setVisible(true);
        nextbtn.setDisable(false);
        if (dr1.isSelected())
        {
            //valasztottOrvos = "Dr. Ügyes Zsolt";
            valasztottOrvos = orvosok.get(0).getNev();
            valasztottOrvosID = orvosok.get(0).getID();
            kiegeszit = 1;
            idopontListazas(0);
            fontos = 0;
            //List<LocalDateTime> eredmeny = lista;
            //System.out.println(eredmeny.get(index));
            

        }
        else if (dr2.isSelected())  
        {
            //valasztottOrvos = "Dr. Szuri Káta";
            valasztottOrvos = orvosok.get(1).getNev();
            valasztottOrvosID = orvosok.get(1).getID();
            kiegeszit = 2;
            idopontListazas(1);
            fontos = 1;
        }
        else if (dr3.isSelected())
        {
            //valasztottOrvos = "Dr. Ollókezű Edward";
            valasztottOrvos = orvosok.get(2).getNev();
            valasztottOrvosID = orvosok.get(2).getID();
            kiegeszit = 3;
            idopontListazas(2);
            fontos = 2;
        }
        else if (dr4.isSelected())
        {
            //valasztottOrvos = "Dr. Padlógáz";
            valasztottOrvos = orvosok.get(3).getNev();
            valasztottOrvosID = orvosok.get(3).getID();
            kiegeszit = 4;
            idopontListazas(3);
            fontos = 3;
        }
        else if (dr5.isSelected())
        {
            //valasztottOrvos = "Dr. Harry Potter";
            valasztottOrvos = orvosok.get(4).getNev();
            valasztottOrvosID = orvosok.get(4).getID();
            kiegeszit = 5;
            idopontListazas(4);
            fontos = 4;
        }
        else if (dr6.isSelected())
        {
            //valasztottOrvos = "Dr. Lassúkezű Dénes";
            valasztottOrvos = orvosok.get(5).getNev();
            valasztottOrvosID = orvosok.get(5).getID();
            kiegeszit = 6;
            idopontListazas(5);
            fontos = 5;
        }

    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

         //idopont(idopontok.g )
         
        //idopontValaszto.setValue(idopontokLista.getIdopont() );
        //idopontValaszto.setItems(idopontokLista);
    }
    
     SceneExtentions sc = new SceneExtentions();
    
    @FXML
    void indexmenuClicked(ActionEvent event) throws IOException {
        System.out.println("hu.unideb.inf.controller.indexController.indexmenuClicked()");
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
