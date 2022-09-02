/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import Extensions.SceneExtentions;
import hu.unideb.inf.model.FelhasznaloSzemely;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.OltasEsemeny;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author andra
 */

public class FXMLLoginSceneController implements Initializable{

public FXMLLoginSceneController(){
    
}
    
    

    public static FelhasznaloSzemely belepett = new FelhasznaloSzemely();
    
    @FXML
    private TextField textemail;

    @FXML
    private PasswordField textjelszo;
     
    @FXML
    public void handleBelepesButtonPressed(ActionEvent event) throws IOException {
        String email = textemail.getText();
        String jelszo = textjelszo.getText();
        
        int counter = 0;
        JPADAO jpadao = new JPADAO();
        List<FelhasznaloSzemely> felhasznalo = jpadao.GetFelhasznaloSzemelyek();
        for(int i = 0; i < felhasznalo.size(); i++) {
            System.out.println("Email: " + felhasznalo.get(i).getEmail());
            System.out.println("Jelszo: " + felhasznalo.get(i).getJelszo());
            if(felhasznalo.get(i).getEmail().equals(email) && felhasznalo.get(i).getJelszo().equals(jelszo)) 
            {
                
                belepett = felhasznalo.get(i);
                
                JOptionPane.showMessageDialog(null,
                "Sikeres bejelentkezés!. Az átirányítás folyamatban",
                "Üzenet",
                JOptionPane.PLAIN_MESSAGE);
                SceneExtentions sc = new SceneExtentions();
                List<OltasEsemeny> oltasok = SceneExtentions.CheckPastOltasEsemenyek();
                if (oltasok.size() > 0)
                {   
                    sc.ChangeScene(event, "BeoltottsagParbeszedAblak");
                }
                else{
                    sc.ChangeScene(event, "FXMLindexScene");
                }
                counter = 1;
                break;
            }
        }
        if(counter == 0) {
            JOptionPane.showMessageDialog(null,
            "Hibás E-mail cím / Jelszó. Ha nem rendelkezik fiókkal, a regisztrációhoz kattintson a Regisztráció gombra",
            "Hiba",
            JOptionPane.ERROR_MESSAGE);
        }      
    }

    @FXML
    void handleRegisztracioButtonPressed(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLRegisterScene");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        textemail.setText("miki@oltsatokbe.com");
        textjelszo.setText("Jelszo123");
        textemail.setStyle("-fx-background-color: BEIGE;");
        */
    }

}

