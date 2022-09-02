package hu.unideb.inf;

import Extensions.SceneExtentions;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.h2.tools.Server;
import hu.unideb.inf.TablaFeltoltes;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        TablaFeltoltes.feltolt(); //betolti az orvosokat és a vakcinákat 

        SceneExtentions.RenderOrvosIdopont();
        
        //FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLOltasok.fxml"));
        //FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLVakcinak.fxml"));
                          
        /*JPADAO dao = new JPADAO();
        Orvos o1 = new Orvos();
        o1.setNev("Dr. Kis István");
        
        Orvos o2 = new Orvos();
        o2.setNev("Dr. Nagy István");
                
        Orvos o3 = new Orvos();
        o3.setNev("Dr. Közepes István");
        dao.save(o3);
        dao.save(o2);
        dao.save(o1);
        
        Vakcina v1 = new Vakcina();
        v1.setNev("vakcina1");
        
        Vakcina v2 = new Vakcina();
        v2.setNev("vakcina2");
        dao.save(v1);
        dao.save(v2);
        
        Szemely f1 = new Szemely();
        f1.setNev("Anitaa");
        dao.save(f1);*/
        
        //SceneExtentions.GenerateTestOltasEsemeny();
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLLoginScene.fxml"));
        MenuBar menuBar = new MenuBar();
        
        Scene scene = new Scene(loader.load());
        
        scene.getStylesheets().add("style.css");
        stage.setTitle("olTáska");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            startDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        launch(args);
        stopDatabase();
    }

    private static Server s = new Server();
    
    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        System.out.println("stopdb");
        s.shutdown();
    }
    
}
