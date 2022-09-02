/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import Extensions.SceneExtentions;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.OltasEsemeny;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class BeoltottsagParbeszedAblakController implements Initializable {
    
    OltasEsemeny currentoltasesemeny;
    
    @FXML
    private Label kerdesLabel;

    @FXML
    void OnIgenButtonClicked(ActionEvent event) throws IOException {
        SceneExtentions.aktualis_oltasesemeny = currentoltasesemeny;
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLErtekelesScene");
    }

    @FXML
    void onNemButtonClicked(ActionEvent event) throws IOException {
        List<OltasEsemeny> oltasok = SceneExtentions.CheckPastOltasEsemenyek();
        if (oltasok.size() > 0)
        {   
            Init();
        }
        else
        {
            SceneExtentions sc = new SceneExtentions();
            sc.ChangeScene(event, "FXMLindexScene");
        }
    }

    /**
     * Initializes the controller class.
     */    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        try {
            Init();
        } catch (IOException ex) {
            Logger.getLogger(BeoltottsagParbeszedAblakController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Init() throws IOException
    {
        List<OltasEsemeny> oltasok = SceneExtentions.CheckPastOltasEsemenyek();
        for (int i = 0; i < oltasok.size(); i++) {
            System.out.println("oltás: " + oltasok.get(i).vakcina.getNev());
        }
        if (oltasok.size() != 0)
        {
            JPADAO dao = new JPADAO();
            currentoltasesemeny = oltasok.get(0);
            currentoltasesemeny.setVizsgalva(true);
            String title = String.format("Megkapta a(z) %s oltást ", currentoltasesemeny.vakcina.getNev()) + currentoltasesemeny.getIdopont() + " időpontban?";
            kerdesLabel.setText(title);
            dao.update(currentoltasesemeny);
        }
    }

}

