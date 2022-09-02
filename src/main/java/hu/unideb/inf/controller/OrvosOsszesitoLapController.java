/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import Extensions.SceneExtentions;
import hu.unideb.inf.model.DAO;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.Orvos;
import hu.unideb.inf.model.OrvosBeosztas;
import hu.unideb.inf.model.OrvosDisplayViewModel;
import hu.unideb.inf.model.Vakcina;
import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.controlsfx.control.Rating;


public class OrvosOsszesitoLapController implements Initializable {

    DAO dao = new JPADAO();
    
    /*TableView<Orvos> table;*/
    @FXML
    private TableView<OrvosDisplayViewModel> orvosTable;

    @FXML
    private TableColumn<OrvosDisplayViewModel, String> nameColumn;

    @FXML
    private TableColumn<OrvosDisplayViewModel, Rating> rateColumn;
    
    @FXML
    private TableColumn<OrvosDisplayViewModel, String> idopontColumn;
    
    
                   
    
    @FXML
    void GoToIndexButtonPushed(ActionEvent event) throws IOException {
        
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLindexScene");

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Orvos> dokik =dao.getAllOrvos(); 
        List<OrvosDisplayViewModel> orvos_dvm = new ArrayList<OrvosDisplayViewModel>();
        
        for (Orvos item : dokik) {
            OrvosDisplayViewModel currentObj = new OrvosDisplayViewModel();
            currentObj.name = item.getNev();
            currentObj.rating = makeRating((double)item.getErtekeles());
            List<OrvosBeosztas> tmp = dao.GetOrvosBeosztas(item);
            if (tmp.size() > 0)
            {                
                OrvosBeosztas beo = tmp.get(0);                
                String kezdes = beo.getKezdesIdo().format(SceneExtentions.getFormatter());
                String vege = beo.getVegzesIdo().format(SceneExtentions.getFormatter());
                currentObj.idopont = kezdes + " - " + vege;
            }
            else
            {
                currentObj.idopont = "Nem található időpont!";
            }
            orvos_dvm.add(currentObj);
        }
       
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        
        idopontColumn.setCellValueFactory(new PropertyValueFactory<>("idopont") );
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        
        ObservableList<OrvosDisplayViewModel> orvosok = FXCollections.observableArrayList(orvos_dvm);
        orvosTable.setItems(orvosok);
    }
    
    private Rating makeRating(double rate) {
        Rating rating = new Rating();
        rating.setOrientation(Orientation.HORIZONTAL);
        rating.setPartialRating(true);                
        rating.setRating(rate);
        rating.setMouseTransparent(true);
        return rating;
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
