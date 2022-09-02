package hu.unideb.inf.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import Extensions.SceneExtentions;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.Vakcina;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Tamás Ádám
 */
public class FXMLInfoController extends SceneExtentions implements Initializable {

    /**
     * Initializes the controller class.
     */
    JPADAO dao = new JPADAO();
    List<Vakcina> vakcinak = dao.getAllVakcina();
    
    @FXML
    private Button backButton;
    
    @FXML
    private ChoiceBox<String> valaszto;

    @FXML
    private TextArea szoveg;
    
    @FXML
    private Button mutasd;

    @FXML
    void backToOltasok(ActionEvent event) throws IOException{
        ChangeScene(event, "FXMLOltasok");
    }
    
    @FXML
    void displayText(ActionEvent event) {
        String oltas = valaszto.getValue();
        String text = "";
        for(int i = 0; i < vakcinak.size(); i++)
            {
                if (oltas == vakcinak.get(i).getNev())
                {
                    text = vakcinak.get(i).getLeiras();
                }
            }
        szoveg.setText(text);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> nevek = new ArrayList<>();
        
        
        for(int i = 0; i < vakcinak.size(); i++)
        {
            nevek.add(vakcinak.get(i).getNev());
        }
        
        ObservableList names = FXCollections.observableArrayList(nevek);
        
        valaszto.setValue((String) names.get(0));
        valaszto.setItems(names);
        
        
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
