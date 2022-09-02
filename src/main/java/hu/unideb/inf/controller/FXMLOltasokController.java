package hu.unideb.inf.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.beans.Expression;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Extensions.SceneExtentions;
import hu.unideb.inf.TablaFeltoltes;
//import static hu.unideb.inf.controller.indexController.userID;
import static hu.unideb.inf.controller.indexController.belepett;
import hu.unideb.inf.model.FelhasznaloSzemely;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.Orvos;
import hu.unideb.inf.model.Vakcina;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Tamás m
 */
public class FXMLOltasokController extends SceneExtentions implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static int oltasAzonosito; 
    public static String oltasNev;
    public static long eletkor;
    private static boolean megfeleloKor;
    
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn9;

    @FXML
    private Button btn13;

    @FXML
    private Button btn6;

    @FXML
    private Button btn10;

    @FXML
    private Button btn14;

    @FXML
    private Button btn7;

    @FXML
    private Button btn11;

    @FXML
    private Button btn15;

    @FXML
    private Button btn8;

    @FXML
    private Button btn12;

    @FXML
    private Button btn16;

    @FXML
    private Button info;

    @FXML
    void handleButtonPushed(ActionEvent event) throws IOException {
        megfeleloKor = true;
        String oltasID;
        String eventString = event.toString();
        //System.out.println(eventString);
        String[] cutAtId = eventString.split("id=");
        cutAtId = cutAtId[1].split(", ");
        eventString = cutAtId[0];
        oltasID = eventString;
        System.out.println("eventString ===>>>" + eventString + "<<<<====");
        //System.out.println(oltasID);
        JPADAO dao = new JPADAO();
        LocalDate gc = dao.GetUserById(belepett.getID()).getSzuletesiDatum();
        LocalDate start = gc;
        LocalDate end = LocalDate.now();
        eletkor = ChronoUnit.YEARS.between(start, end);
        System.out.println("Age : " + eletkor);

        
        
        List<Vakcina> oltasok = dao.getAllVakcina();
        System.out.println("oltasID a btn-nel: " + oltasok.get(4).getID());
        switch (oltasID)
        {
            case "btn1": oltasNev = oltasok.get(0).getNev(); oltasAzonosito=oltasok.get(0).getID(); break;
            case "btn2": oltasNev = oltasok.get(1).getNev(); oltasAzonosito=oltasok.get(1).getID(); break;
            case "btn3": oltasNev = oltasok.get(2).getNev(); oltasAzonosito=oltasok.get(2).getID(); break;
            case "btn4": oltasNev = oltasok.get(3).getNev(); oltasAzonosito=oltasok.get(3).getID(); break;
            case "btn5": oltasNev = oltasok.get(4).getNev(); oltasAzonosito=oltasok.get(4).getID(); break;
            case "btn6": oltasNev = oltasok.get(5).getNev(); oltasAzonosito=oltasok.get(5).getID(); break;
            case "btn7": oltasNev = oltasok.get(6).getNev(); oltasAzonosito=oltasok.get(6).getID(); break;
            case "btn8": oltasNev = oltasok.get(7).getNev(); oltasAzonosito=oltasok.get(7).getID(); break;
            case "btn9": oltasNev = oltasok.get(8).getNev(); oltasAzonosito=oltasok.get(8).getID(); break;
            case "btn10": oltasNev = oltasok.get(9).getNev(); oltasAzonosito=oltasok.get(9).getID(); break;
            case "btn11": oltasNev = oltasok.get(10).getNev(); oltasAzonosito=oltasok.get(10).getID(); break;
            case "btn12": oltasNev = oltasok.get(11).getNev(); oltasAzonosito=oltasok.get(11).getID(); break;
            case "btn13": oltasNev = oltasok.get(12).getNev(); oltasAzonosito=oltasok.get(12).getID(); break;
            case "btn14": if (eletkor >= 18) {oltasNev = oltasok.get(13).getNev(); oltasAzonosito=oltasok.get(13).getID(); break;} kiirAlert(); break;
            case "btn15": if (eletkor >= 18) {oltasNev = oltasok.get(14).getNev(); oltasAzonosito=oltasok.get(14).getID(); break;} kiirAlert(); break;
            case "btn16": if (eletkor >= 18) {oltasNev = oltasok.get(15).getNev(); oltasAzonosito=oltasok.get(15).getID(); break;} kiirAlert(); break;
            
            
        }
        
        System.out.println("btnoltásAzonosító: " + oltasAzonosito);
        System.out.println("btnoltásAzonosító: " + oltasNev);
        if (megfeleloKor)
        {
            ChangeScene(event, "FXMLAdatok");
        }
        
        
    }
    
    
    @FXML
    void handleInfoButton(ActionEvent event) throws IOException {
        ChangeScene(event, "FXMLInfo");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TablaFeltoltes.feltolt();
    }    

    private void kiirAlert() {
            megfeleloKor = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba!");
            alert.setHeaderText(null);
            alert.setContentText("Ön túl fiatal ehhez az oltáshoz!");

            alert.showAndWait();
 
            
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
