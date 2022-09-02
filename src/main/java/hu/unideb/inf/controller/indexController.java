/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;
import Extensions.SceneExtentions;
import hu.unideb.inf.model.DAO;
import hu.unideb.inf.model.FelhasznaloSzemely;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.KorTortenet;
import hu.unideb.inf.model.OltasEsemeny;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class indexController implements Initializable {
     @FXML
    private Button indexmenu;

    @FXML
    private Button jelentkezesmenu;

    @FXML
    private Button orvosokmenu;

    @FXML
    private Button vakcinainfomenu;

    @FXML
    private Button kilelpesmenu;


    public static FelhasznaloSzemely belepett = FXMLLoginSceneController.belepett;

    public static int ev = 0;
    public static int honap = 0;
    public static int nap = 0;

    @FXML
    private Label nevLabel;

    @FXML
    private Label SzuletesiDatumLabel;

    @FXML
    private Label TAJLabel;

    @FXML
    private Label nemLabel;

    @FXML
    private TextField nevTextField;

    @FXML
    private TextField TAJTextField;

    @FXML
    private DatePicker BDPicker;

    @FXML
    private ChoiceBox<FelhasznaloSzemely.NemTipus> nemChoiceBox;

    @FXML
    private Button Change;

    @FXML
    private Button OKButton;
    
    @FXML
    private TableView<KorTortenet> korTortenetTabla;
    
    @FXML
    private TableColumn<KorTortenet, LocalDateTime> idopontOszlop;

    @FXML
    private TableColumn<KorTortenet, String> vakcinaOszlop;

    @FXML
    private TableColumn<KorTortenet, String> orvosOszlop;

    @FXML
    private TableColumn<KorTortenet, String> megkaptaOszlop;

    public static boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
    }
    
    private boolean ErvenyesNev(String nev) {
        int szamok = 0;
        for (int i = 0; i < nev.length(); i++) {
            if (Character.isDigit(nev.charAt(i))) {
                szamok++;
            }
        }
        return (nev.contains(" ") && nev.length() >= 5 && szamok == 0);
    }

    @FXML
    void OKButtonPushed(ActionEvent event) {
        String nev = nevTextField.getText();
        String TAJ=TAJTextField.getText();
        LocalDate BirthDay=BDPicker.getValue();
        if(!isNumeric(TAJ) || TAJ.length()!=9 || TAJ.charAt(0)=='0')
        {
            JOptionPane.showMessageDialog(null,"Hibás adatot adtál meg.","Hiba",JOptionPane.ERROR_MESSAGE);
            return;
        }


        if (!ErvenyesNev(nev)) 
        {
            JOptionPane.showMessageDialog(null,"Hibás nevet adtál meg.","Hiba",JOptionPane.ERROR_MESSAGE);
            return;
        } 
        else {
        JOptionPane.showMessageDialog(null,
                    "Az adatok sikeresen módosítva.",
                    "Ăśzenet",
                    JOptionPane.PLAIN_MESSAGE);

            Change.setVisible(true);
            nevLabel.setVisible(true);
            TAJLabel.setVisible(true);
            SzuletesiDatumLabel.setVisible(true);
            nemLabel.setVisible(true);
            nevTextField.setVisible(false);
            TAJTextField.setVisible(false);
            BDPicker.setVisible(false);
            nemChoiceBox.setVisible(false);
            OKButton.setVisible(false);

            nevLabel.setText(nevTextField.getText());
            TAJLabel.setText("" + TAJTextField.getText());
            SzuletesiDatumLabel.setText(BDPicker.getValue().toString());
            nemLabel.setText(nemChoiceBox.getValue().toString());

            DAO dao = new JPADAO();

            belepett.setNem(nemChoiceBox.getValue());
            belepett.setNev(nevTextField.getText());
            belepett.setSzuletesiDatum(BDPicker.getValue()); //innen kiszedtem a --honap, mert ha tovabbmegy az oltasra, Ă©s vissza
                                                                                //akkor a beallitott honapot 1-el csĂ¶kkenti, Ă­gy viszont rendben lesz databaseben is jo
            belepett.setTAJ(Integer.parseInt(TAJTextField.getText()));
            dao.update(belepett);
            dao.save(belepett);
        }
    }

    @FXML
    void handleChangeButton(ActionEvent event) {
        Change.setVisible(false);
        nevLabel.setVisible(false);
        TAJLabel.setVisible(false);
        SzuletesiDatumLabel.setVisible(false);
        nemLabel.setVisible(false);
        nevTextField.setVisible(true);
        TAJTextField.setVisible(true);
        BDPicker.setVisible(true);
        nemChoiceBox.setVisible(true);
        OKButton.setVisible(true);
        nevTextField.setText(nevLabel.getText());
        TAJTextField.setText(TAJLabel.getText());
        String nemRaw=nevLabel.getText();
        if(nemRaw=="FERFI"){
        nemChoiceBox.setValue(FelhasznaloSzemely.NemTipus.FERFI);}
        else {
        nemChoiceBox.setValue(FelhasznaloSzemely.NemTipus.NO);}

    }

    @FXML
    void handleToOrvosok(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "OrvosOsszesitoLap");
    }

    @FXML
    void handleToVakcinak(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLVakcinak");
    }

    @FXML
    void handleToJelentkezes(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLOltasok");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        DAO dao = new JPADAO();
        nevLabel.setText(belepett.getNev());
        nemLabel.setText(belepett.getNem().toString());
        SzuletesiDatumLabel.setText(belepett.getSzuletesiDatum().toString());
        TAJLabel.setText("" + belepett.getTAJ());

        ObservableList nemek = FXCollections.observableArrayList();
        nemek.add(FelhasznaloSzemely.NemTipus.FERFI);
        nemek.add(FelhasznaloSzemely.NemTipus.NO);
        nemChoiceBox.setItems(nemek);
        nemChoiceBox.getSelectionModel().selectFirst();
        
        
        
        /*
    private TableColumn<OltasEsemeny, LocalDateTime> idopontOszlop;
    private TableColumn<Vakcina, String> vakcinaOszlop;
    private TableColumn<Orvos, String> orvosOszlop;
    private TableColumn<OltasEsemeny, String> megkaptaOszlop;
        */
        
        
        List<OltasEsemeny> osszes=dao.GetUserOltasEsemenyei(belepett.getID());
        
        idopontOszlop.setCellValueFactory(new PropertyValueFactory<KorTortenet, LocalDateTime>("idopont"));
        vakcinaOszlop.setCellValueFactory(new PropertyValueFactory<KorTortenet, String>("vakcina"));
        orvosOszlop.setCellValueFactory(new PropertyValueFactory<KorTortenet, String>("orvos"));
        //megkaptaOszlop.setCellValueFactory(new PropertyValueFactory<KorTortenet, String>("megkapta"));
        
        
        ObservableList<KorTortenet> adatok= FXCollections.observableArrayList();
        
        for(OltasEsemeny egyik : osszes)
        {
            KorTortenet uj=new KorTortenet();
            uj.setIdopont(egyik.getIdopont());
            uj.setOrvos(egyik.orvos.getNev());
            uj.setVakcina(egyik.vakcina.getNev());
            uj.setMegkapta(egyik.isMegkapta());
            adatok.add(uj);
        }
        korTortenetTabla.setItems(adatok);
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
