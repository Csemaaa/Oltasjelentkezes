package Extensions;

import static hu.unideb.inf.controller.indexController.belepett;
import hu.unideb.inf.model.DAO;
import hu.unideb.inf.model.FelhasznaloSzemely;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.OltasEsemeny;
import hu.unideb.inf.model.Orvos;
import hu.unideb.inf.model.OrvosBeosztas;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author karal
 */
public class SceneExtentions {    
    
    static DAO dao = new JPADAO();
    
    public static FelhasznaloSzemely user;
    public static OltasEsemeny aktualis_oltasesemeny;
    
    public static DateTimeFormatter getFormatter()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return formatter;
    }
    
    
    
    public void ChangeScene(ActionEvent event, String scene_name) throws IOException
    {
        String file = "/fxml/" + scene_name + ".fxml";
        Parent par_scene = FXMLLoader.load(getClass().getResource(file));
        
        Scene scene = new Scene(par_scene);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        window.setScene(scene);
        window.show();
    }
    
    
       public static OrvosBeosztas GenerateRandomDateTime()
    {
        Random rand = new Random();   
        LocalDateTime idopont = LocalDateTime.now();
        LocalDate nap;
        
        //random hĂ©tkĂ¶znap generĂˇlĂˇsa
                 
        do
        {
            LocalDate today = LocalDate.now();
            int randint = rand.nextInt(Nevesitett_konst.MAX_DAY_TO_ADD) + 1;
            nap = today.plusDays(randint);                
        }
        while(nap.getDayOfWeek() == DayOfWeek.SUNDAY || nap.getDayOfWeek() == DayOfWeek.SATURDAY);
                
            
        //System.out.println("generĂˇlt: " + nap);
        
        //random kezdĹ‘- Ă©s vĂ©gidĹ‘pont generĂˇlĂˇsa
        int randKezdo = rand.nextInt(9) + 7;       //8 Ă©s 17 kĂ¶zĂ¶tti random szĂˇm
        int init = rand.nextInt() % 3;
        int randintervall = rand.nextInt(18 - randKezdo + 1) + 1;
        OrvosBeosztas _orvosbeosztas = new OrvosBeosztas();
        
        if(init == 0)
        {
        _orvosbeosztas.setKezdesIdo(LocalDateTime.of(nap, LocalTime.of(randKezdo, 30, 0, 0)));
        _orvosbeosztas.setVegzesIdo(LocalDateTime.of(nap, LocalTime.of(randKezdo, 59, 0, 0)));          
        }
        else
        {
        _orvosbeosztas.setKezdesIdo(LocalDateTime.of(nap, LocalTime.of(randKezdo, 0, 0, 0)));
        _orvosbeosztas.setVegzesIdo(LocalDateTime.of(nap, LocalTime.of(randKezdo, 29, 0, 0)));
        }
        
        return _orvosbeosztas;        
    }
    
    public static boolean IdopontUtkozes(OrvosBeosztas beo, List<OrvosBeosztas> beosztaslista)
    {
        for (int i = 0; i < beosztaslista.size(); i++) {
            if (beosztaslista.get(i).getVegzesIdo().isAfter(beo.getKezdesIdo()))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public static void RenderOrvosIdopont()
    {
        List<Orvos> _orvosok = dao.getAllOrvos(); 
        
        for (Orvos item : _orvosok) {
            System.out.println("Orvos: " + item.getNev());
            List<OrvosBeosztas> tmp = dao.GetOrvosBeosztas(item);
            tmp.removeIf(p -> p.getKezdesIdo().isBefore(LocalDateTime.now()));
            if (tmp.size() < 10)
            {                
                for (int i = 0; i < 10 - tmp.size(); i++) {                    
                    OrvosBeosztas beo;
                    //do
                    //{
                        beo = SceneExtentions.GenerateRandomDateTime();
                    /*}
                    while(IdopontUtkozes(beo, tmp));*/
                    //item.beosztas.add(beo);                       
                    System.out.println(tmp.size() + "  " + item.getNev());
                    beo.orvos = item;
                    dao.save(beo);
                    tmp.add(beo);
                }                                
            }
        }            
    }
    
    public static void GenerateTestOltasEsemeny()
    {
        /*OltasEsemeny oltas = new OltasEsemeny();
        oltas.setIdopont(LocalDateTime.now().minusHours(1));
        oltas.setMegkapta(false);
        oltas.vakcina = dao.GetVakcinaById(54);
        oltas.orvos = (Orvos) dao.GetOrvosById(34);
        oltas.user = dao.GetUserById(33);
        oltas.setVizsgalva(false);
        dao.save(oltas);        
        
        System.out.println("GenerĂˇlt esemĂ©ny: " + oltas.vakcina.getNev() + "  " + oltas.orvos.getNev());*/
    }
    
    public static List<OltasEsemeny> CheckPastOltasEsemenyek()
    {
        List<OltasEsemeny> esemenyek = dao.GetUserOltasEsemenyei(belepett.getID());
        esemenyek.removeIf(e -> e.getIdopont().isAfter(LocalDateTime.now()) || e.isVizsgalva());
        System.out.println("vizsg");
        return esemenyek;            }
    
    
}
        
