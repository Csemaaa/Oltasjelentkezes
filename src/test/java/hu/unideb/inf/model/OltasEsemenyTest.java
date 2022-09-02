/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.time.LocalDateTime;
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
public class OltasEsemenyTest {
    
    public OltasEsemenyTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of isVizsgalva method, of class OltasEsemeny.
     */
    @Test
    public void testIsVizsgalva()
    {
        OltasEsemeny esemeny = new OltasEsemeny();
        esemeny.setVizsgalva(true);
        boolean expResult = true;
        boolean result = esemeny.isVizsgalva();
        assertEquals(expResult, result);

    }


    @Test
    public void testIsMegkapta() {
        OltasEsemeny esemeny = new OltasEsemeny();
        
        esemeny.setMegkapta(true);
        boolean expResult = true;
        boolean result = esemeny.isMegkapta();
        assertEquals(expResult, result);

    }

    
}
