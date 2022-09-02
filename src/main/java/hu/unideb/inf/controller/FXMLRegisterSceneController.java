/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import Extensions.SceneExtentions;
import hu.unideb.inf.model.FelhasznaloSzemely;
import hu.unideb.inf.model.JPADAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class FXMLRegisterSceneController implements Initializable {

    ObservableList nemek = FXCollections.observableArrayList();

    @FXML
    private TextField textemail;

    @FXML
    private TextField textnev;

    @FXML
    private TextField texttaj;

    @FXML
    private PasswordField textjelszo;

    @FXML
    private PasswordField textjelszoujra;

    @FXML
    private ChoiceBox<String> textnem;

    @FXML
    private Label adatvedelemtext;

    @FXML
    private CheckBox adatvedelemelfogadas;

    @FXML
    private DatePicker BDPicker;

    @FXML
    void handleRegisztracioButtonPressed(ActionEvent event) throws IOException {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        //Felhasználó adatai
        String email = textemail.getText();
        System.out.println(email);
        String jelszo = textjelszo.getText();
        String jelszoujra = textjelszo.getText();

        //Személy adatai
        String nev = textnev.getText();
        int taj = Integer.parseInt(texttaj.getText());
        String nem = textnem.getValue();
        LocalDate szuletesidatum = BDPicker.getValue();

        boolean f = true;
        JPADAO jpadao = new JPADAO();
        List<FelhasznaloSzemely> felhasznalo = jpadao.GetFelhasznaloSzemelyek();
        System.out.println("A felhasználók száma: " + felhasznalo.size());
        for (int i = 0; i < felhasznalo.size(); i++) {
            System.out.println("Email: " + felhasznalo.get(i).getEmail());
            if (felhasznalo.get(i).getEmail() != null) {
                if (felhasznalo.get(i).getEmail().equals(email)) {
                    f = false;
                }
            }
        }

        System.out.println("Felhasználók beolvasva ");
        //Adatok tesztelése
        boolean a = ErvenyesEmail(email);
        boolean b = ErvenyesJelszo(jelszo, jelszoujra);
        boolean c = ErvenyesNev(nev);
        boolean d = ErvenyesTaj(taj);
        boolean g = adatvedelemelfogadas.isSelected();

        String hiba = "";
        if (a == false) {
            hiba = hiba + ("Az e-mail cím érvénytelen. \n");
        }
        if (b == false) {
            hiba = hiba + ("A jelszó, minimum 8 karakter tartalmaznia kell kis és nagybetűket. \n");
        }
        if (c == false) {
            hiba = hiba + ("A név nem megfelelő. \n");
        }
        if (d == false) {
            hiba = hiba + ("A tajszám nem megfelelő. \n");
        }
        if (f == false) {
            hiba = hiba + ("Ez az e-mail-cím már használatban van. Ha már rendelkezk fiókkal, kérem, jelentkezzen be. \n");
        }
        if (g == false) {
            hiba = hiba + ("A regisztációhoz fogadja el az adatkezelési tájékoztatóban foglaltakat.\n");
        }

        if (a && b && c && d && f && g) {
            System.err.println("Adatok elfogadva, Felhasználó létrhozása");
            //Felhasználó példányosítása
            FelhasznaloSzemely uriember = new FelhasznaloSzemely();
            uriember.setEmail(email);
            uriember.setJelszo(jelszo);
            uriember.setNev(nev);
            uriember.setTAJ(taj);
            uriember.setSzuletesiDatum(szuletesidatum);
            if (nem.equals("Nő")) {
                uriember.setNem(FelhasznaloSzemely.NemTipus.NO);
            } else {
                uriember.setNem(FelhasznaloSzemely.NemTipus.FERFI);
            }

            entityManager.getTransaction().begin();
            entityManager.persist(uriember);
            entityManager.getTransaction().commit();

            JOptionPane.showMessageDialog(null,
                    "Az adatok sikeresen feltöltve. \n",
                    "Üzenet",
                    JOptionPane.PLAIN_MESSAGE);
            SceneExtentions sc = new SceneExtentions();
            sc.ChangeScene(event, "FXMLLoginScene");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Hiba történt az adatok megadásakor. \n" + hiba,
                    "Hiba",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void handleVisszaButtonPressed(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLLoginScene");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nemBetolto();

        adatvedelemelfogadas.setText("Megismertem és elfogadom az adatkezelési \n"
                + "tájékoztatóban foglaltakat az adataim továbbítása céljából");
        adatvedelemtext.setText("ADATKEZELÉSI TÁJÉKOZTATÓ\n"
                + "A szoftver célja, hogy az Oltáska csapat kommunikációs\n"
                + "feladatkörében eljárva tájékoztatást nyújtson a vírusok \n"
                + "elleni védőoltás igénylésének lehetőségéről, és \n"
                + "lehetőséget biztosítson a 18. életévét betöltött magyar\n"
                + "állampolgárok és a Magyarországon jogszerűen tartózkodó\n"
                + "18. életévét betöltött, társadalombiztosítási azonosító\n"
                + "jellel (a továbbiakban: taj-szám) rendelkező természetes \n"
                + "személyek (a továbbiakban együtt: érintettek) számára\n"
                + "annak jelzésére, hogy igényt tartanak az oltóanyagra.\n"
                + "A korlátozottan cselekvőképes vagy cselekvőképtelen\n"
                + "érintett esetében az igény jelzését a személyes adatok\n"
                + "megadásával a törvényes képviselő teheti meg.\n"
                + "Az igényléshez név, e-mail-cím, életkor és TAJ-szám\n"
                + "(a továbbiakban együtt: személyes adatok) megadása\n"
                + "szükséges.\n"
                + "A személyes adatok megadásával és a „Megismertem és\n"
                + "elfogadom az adatkezelési tájékoztatóban foglaltakat \n"
                + "az adataim továbbítása céljából” gombra kattintással,\n"
                + "továbbá az űrlap végén található „Regisztráció” gombra\n"
                + "kattintással Ön önkéntes hozzájárulását adja, hogy a\n"
                + "közölt személyes adatokat (név, e-mail-cím, életkor és \n"
                + "TAJ-szám) a természetes személyeknek a személyes adatok\n"
                + "kezelése tekintetében történő védelméről és az ilyen\n"
                + "adatok szabad áramlásáról, hozzájáruló nyilatkozata\n"
                + "visszavonásáig, az Oltáska csapatnak átadja. \n"
                + "személyes adatokat az Oltáska csapat titkosított\n"
                + "adatbázisban tárolja, nyilvánosságra nem hozza,\n"
                + "külföldre és harmadik félnek nem továbbítja.");
    }

    private void nemBetolto() {
        nemek.removeAll(nemek);
        nemek.addAll("Férfi", "Nő");
        textnem.getItems().addAll(nemek);
    }

    public boolean ErvenyesEmail(String email) {
        return email.contains("@") && email.lastIndexOf("@") < email.lastIndexOf(".");
    }

    public boolean ErvenyesJelszo(String jelszo1, String jelszo2) {
        boolean a;
        int nagybetuk = 0;
        int kisbetuk = 0;
        int szamok = 0;
        for (int i = 0; i < jelszo1.length(); i++) {
            if (Character.isUpperCase(jelszo1.charAt(i))) {
                nagybetuk++;
            } else if (Character.isLowerCase(jelszo1.charAt(i))) {
                kisbetuk++;
            } else if (Character.isDigit(jelszo1.charAt(i))) {
                szamok++;
            }
        }

        return (jelszo1.equals(jelszo2)) && kisbetuk > 0 && nagybetuk > 0 && szamok > 0 && jelszo1.length() > 8;
    }

    public boolean ErvenyesNev(String nev) {
        int szamok = 0;
        for (int i = 0; i < nev.length(); i++) {
            if (Character.isDigit(nev.charAt(i))) {
                szamok++;
            }
        }
        return (nev.contains(" ") && nev.length() >= 5 && szamok == 0);
    }

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

    public boolean ErvenyesTaj(int taj) {
        String TAJ = "" + taj;
        return isNumeric(TAJ) && TAJ.length() == 9 && TAJ.charAt(0) != '0';
    }
}
