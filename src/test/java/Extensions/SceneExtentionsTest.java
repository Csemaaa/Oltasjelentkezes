/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extensions;

import hu.unideb.inf.model.OltasEsemeny;
import hu.unideb.inf.model.OrvosBeosztas;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tamás Ádám
 */
public class SceneExtentionsTest {


    @Test
    public void testIdopontUtkozes() {
        System.out.println("IdopontUtkozes");
        OrvosBeosztas beo = new OrvosBeosztas();
        OrvosBeosztas beo2 = new OrvosBeosztas();
        
        beo.setKezdesIdo(LocalDateTime.of(2020, 06, 10, 8, 30));
        beo.setVegzesIdo(LocalDateTime.of(2020, 06, 10, 12, 30));
        beo2.setKezdesIdo(LocalDateTime.of(2020, 06, 10, 8, 30));
        beo2.setVegzesIdo(LocalDateTime.of(2020, 06, 10, 15, 20));
        
        
        List<OrvosBeosztas> beosztaslista = new ArrayList<>();
        beosztaslista.add(beo2);
        
        boolean expResult = true;
        boolean result = SceneExtentions.IdopontUtkozes(beo, beosztaslista);
        assertEquals(expResult, result);

    }

    
}
