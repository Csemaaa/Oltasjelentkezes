/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

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
public class VakcinaTest {

    
    @Test
    public void testGetErtekeles_dbszam() {
        Vakcina instance = new Vakcina();
        instance.setErtekeles_dbszam(10);
        int expResult = instance.getErtekeles_dbszam();
        assertEquals(expResult, 10);

    }


    @Test
    public void testGetLeiras() {

        Vakcina instance = new Vakcina();
        instance.setLeiras("leiras");
        String result = instance.getLeiras();
        assertEquals(result, "leiras");

    }


    @Test
    public void testGetErtekeles() {
        Vakcina instance = new Vakcina();
        instance.setErtekeles((float)4.5);
        float result = instance.getErtekeles();
        assertEquals(result, 4, 5);
        

    }

    
}
