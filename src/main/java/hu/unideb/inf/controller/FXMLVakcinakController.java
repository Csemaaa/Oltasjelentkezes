
package hu.unideb.inf.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Extensions.SceneExtentions;
import static hu.unideb.inf.controller.FXMLOltasokController.oltasAzonosito;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.Vakcina;
import hu.unideb.inf.model.VakcinaErtekeles;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.controlsfx.control.Rating;
//import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Tamás �?dám
 */
public class FXMLVakcinakController extends SceneExtentions implements Initializable {

    /**
     * Initializes the controller class.
     */
    JPADAO dao = new JPADAO();
    ObservableList list = FXCollections.observableArrayList();
    
    @FXML
    private ListView<String> oltasLista;
    
    @FXML
    private Rating ertekeles; //dependenciesben hozzáadtam a controlsfx 11.1.0.jar-t
    
    private float ertek;
    
    @FXML
    private Text szoveg;
    
    @FXML
    private Label label_szovvisszajelzes;
    
    @FXML
    private ListView<String> szoveges_ertekelesek;

    
    @FXML
    void displaySelected(MouseEvent event) {
        
        //String ertekeles = oltasLista.getSelectionModel().getSelectedItem();
        oltasLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                System.out.println(newValue);
            }
        });
        
    

    }


    @FXML
    void goBackToIndex(ActionEvent event) throws IOException {
        ChangeScene(event, "FXMLindexScene");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        szoveges_ertekelesek.setCellFactory(param -> new ListCell<String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    setGraphic(null);
                    setText(null); 
                    // other stuff to do...

                }else{

                    // set the width's
                    setMinWidth(param.getWidth());
                    setMaxWidth(param.getWidth());
                    setPrefWidth(param.getWidth());

                    // allow wrapping
                    setWrapText(true);

                    setText(item.toString());


                }
            }
        });

        List<Vakcina> vakcinak = dao.getAllVakcina();
        int i = 0;
        for (Vakcina v: vakcinak)
        {
            list.add(v.getNev());
            if (i < 10)
            {
                if (i == 0)
                {
                    i++;
                    continue;
                }
                v.setErtekeles((float) 2.4);
            }
            else
            {
                v.setErtekeles((float)4.2);
            }
            i++;
            
        }
        
       
        oltasLista.getItems().addAll(list);
        
        
        ertekeles.setMouseTransparent(true); // ne lehessen modosítani, disable nemjo, mert elszürkiti
        ertekeles.ratingProperty().set(floatKerekit(0));
        
        oltasLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                //System.out.println(newValue);
                List<VakcinaErtekeles> ertekelesek = null;
            for (Vakcina v: vakcinak)
            {
                if(v.getNev() == newValue)
                {
                    
                    ertek = v.getErtekeles();
                    ertekelesek = dao.GetVakcinaErtekelesekByVakcinaID(v.getID());
                    for (int i = 0; i < ertekelesek.size(); i++) {
                        System.out.println(ertekelesek.get(i).getErtekeles());
                    }
                    break;
                }
            }
            if (ertek < 1)
            {
                szoveg.setVisible(true);
                szoveg.setText("Még nem érkezett értékelés ehhez az oltáshoz!");
                ertekeles.ratingProperty().set(0);
                ertekeles.setVisible(false);
            }
            else
            {
                ertekeles.setVisible(true);
                szoveg.setVisible(false);
                ertekeles.ratingProperty().set(floatKerekit(ertek));
            }
            
            szoveges_ertekelesek.getItems().clear();
            if (ertekelesek != null || ertekelesek.size() == 0)
            {
                for (int j = 0; j < ertekelesek.size(); j++) {
                    
                    System.out.println(ertekelesek.get(j).getErtekeles());
                    szoveges_ertekelesek.getItems().add(ertekelesek.get(j).getErtekeles());
                }
                szoveges_ertekelesek.setVisible(true);
                label_szovvisszajelzes.setText("Szöveges visszajelzések:");
                
            }
            else{
                szoveges_ertekelesek.setVisible(false);
                label_szovvisszajelzes.setText("Szöveges visszajelzések: még nem érkeztek szöveges visszajelzések");
            }
            
            }
        });

    }

    private double floatKerekit(float szam) {
        return Math.round(szam);
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
