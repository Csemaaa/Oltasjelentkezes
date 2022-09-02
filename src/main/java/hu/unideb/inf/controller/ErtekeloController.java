package hu.unideb.inf.controller;

import Extensions.SceneExtentions;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.OltasEsemeny;
import hu.unideb.inf.model.VakcinaErtekeles;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.controlsfx.control.Rating;

public class ErtekeloController implements Initializable{
            
    JPADAO dao = new JPADAO();
    @FXML
    private Label kerdesLabel;

    @FXML
    private Label orvosErtekeloLabel;

    @FXML
    private TextArea erteklesTextArea;
    
    @FXML
    private Rating orvosertekeles;
    
    @FXML
    private Rating vakcinaertekeles;
    
    @FXML
    private Button megsembtn;

    @FXML
    void MegsemButtonCllicked(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "BeoltottsagParbeszedAblak");
    }

    /*@FXML
    void BackToIndexButtonClicked(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLindexScene");
    }*/

    
    
    @FXML
    void ErtekelesElkuldesButtonCllicked(ActionEvent event) throws IOException {
        var orvos = SceneExtentions.aktualis_oltasesemeny.orvos;
        var vakcina = SceneExtentions.aktualis_oltasesemeny.vakcina;
        
        if (orvosertekeles.getRating() > 0)
        {
            System.out.println("orvos értékelés: " + orvos.getErtekeles());
            orvos.setErtekeles_dbszam(orvos.getErtekeles_dbszam() + 1);
            if (orvos.getErtekeles_dbszam() >= 1)
            {
                orvos.setErtekeles((float)(orvos.getErtekeles() * (orvos.getErtekeles_dbszam() - 1) + orvosertekeles.getRating()) / orvos.getErtekeles_dbszam());
            }
            else
            {
                orvos.setErtekeles((float)orvosertekeles.getRating());
            }  
            
            dao.update(orvos);
            System.out.println("orvos értékelés: " + orvos.getErtekeles());
        }
        
        if (vakcinaertekeles.getRating() > 0)
        {
            
            vakcina.setErtekeles_dbszam(vakcina.getErtekeles_dbszam() + 1);
            if (vakcina.getErtekeles_dbszam() >= 1)
            {
                vakcina.setErtekeles((float)(vakcina.getErtekeles() * (vakcina.getErtekeles_dbszam()-1) + vakcinaertekeles.getRating()) / vakcina.getErtekeles_dbszam());
            }
            else
            {
                vakcina.setErtekeles((float)vakcinaertekeles.getRating());
            }
            
            dao.update(vakcina);
            System.out.println("Vakcina értékelés: " + vakcina.getErtekeles());
        }
        
        if (erteklesTextArea.getText().length() > 0)
        {
            VakcinaErtekeles vakc_ert = new VakcinaErtekeles();
            vakc_ert.vakcina = vakcina;
            vakc_ert.setErtekeles(erteklesTextArea.getText());
            dao.save(vakc_ert);
        }
        
        RedirectHandler(event);
    }
    
    /*@FXML
    void BackToIndexButtonClicked(ActionEvent event) throws IOException
    {
        SceneExtentions sc  = new SceneExtentions();
        sc.ChangeScene(event, "FXMLindexScene");
    }
    */
    
    private void RedirectHandler(ActionEvent event) throws IOException
    {
        List<OltasEsemeny> oltasok = SceneExtentions.CheckPastOltasEsemenyek();
        SceneExtentions sc = new SceneExtentions();
        if (oltasok.size() != 0){
            sc.ChangeScene(event, "BeoltottsagParbeszedAblak");
        }
        else
        {
            sc.ChangeScene(event, "FXMLindexScene");
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        orvosertekeles.setRating(0);
        vakcinaertekeles.setRating(0);
    }
        
    
  
}
