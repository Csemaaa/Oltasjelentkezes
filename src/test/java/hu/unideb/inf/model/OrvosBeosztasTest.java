/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.time.LocalDateTime;
import java.time.Month;
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
public class OrvosBeosztasTest {

    @Test
    public void testGetKezdesIdo() {
        OrvosBeosztas beo = new OrvosBeosztas();
        beo.setKezdesIdo(LocalDateTime.of(2020, 02, 11, 10, 20));
        LocalDateTime result = beo.getKezdesIdo();
        assertEquals(result, LocalDateTime.of(2020, 02, 11, 10, 20));

    }


    @Test
    public void testGetVegzesIdo() {

        OrvosBeosztas beo = new OrvosBeosztas();
        beo.setVegzesIdo(LocalDateTime.of(2020, 02, 14, 10, 20));

        LocalDateTime result = beo.getVegzesIdo();
        assertEquals(result, LocalDateTime.of(2020, 02, 14, 10, 20));

    }

    
}
